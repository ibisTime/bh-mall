/**
 * @Title UserAOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:52:06 
 * @version V1.0   
 */
package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627301Req;
import com.bh.mall.dto.req.XN627302Req;
import com.bh.mall.dto.res.XN627302Res;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EConfigType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ELoginType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserPwd;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.PostSimulater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:52:06 
 * @history:
 */
@Service
public class UserAOImpl implements IUserAO {

    private static Logger logger = Logger.getLogger(UserAOImpl.class);

    @Autowired
    protected IUserBO userBO;

    @Autowired
    protected ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    protected ISYSConfigBO sysConfigBO;

    @Autowired
    protected IAddressBO addressBO;

    @Override
    public String doLogin(String loginName, String loginPwd, String kind,
            String companyCode, String systemCode) {
        User condition = new User();
        if (EUserKind.Customer.getCode().equals(kind)
                || EUserKind.Merchant.getCode().equals(kind)) {
            condition.setLoginName(loginName);
            condition.setLoginType(ELoginType.MOBILE.getCode());
        } else {
            condition.setLoginName(loginName);
        }
        condition.setKind(kind);
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<User> userList1 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        User user = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050", "该用户操作存在异常");
        }
        return user.getUserId();
    }

    @Override
    @Transactional
    public String doAddUser(XN627301Req req) {
        String userId = null;
        if (EUserKind.Plat.getCode().equals(req.getKind())) {
            // 验证登录名
            userBO.isLoginNameExist(req.getLoginName(), req.getKind(),
                req.getCompanyCode(), req.getSystemCode());
            userId = userBO.doAddUser(req);
        } else {
            throw new BizException("xn627301", "用户类型" + req.getKind() + "未能识别");
        }
        return userId;
    }

    @Override
    @Transactional
    public XN627302Res doLoginWeChat(XN627302Req req) {
        String companyCode = ESystemCode.BH.getCode();
        String systemCode = ESystemCode.BH.getCode();
        // Step1：获取密码参数信息
        Map<String, String> configPwd = sysConfigBO.getConfigsMap(
            EConfigType.WEIXIN_XCX.getCode(), companyCode, systemCode);

        String appId = configPwd.get(SysConstant.WX_XCX_ACCESS_KEY);
        String appSecret = configPwd.get(SysConstant.WX_XCX_SECRET_KEY);

        if (StringUtils.isBlank(appId)) {
            throw new BizException("XN000000", "参数appId配置获取失败，请检查配置");
        }
        if (StringUtils.isBlank(appSecret)) {
            throw new BizException("XN000000", "参数appSecret配置获取失败，请检查配置");
        }

        // Step2：通过Authorization Code获取Access Token
        String accessToken = "";
        Map<String, String> res = new HashMap<>();
        Properties fromProperties = new Properties();
        fromProperties.put("grant_type", "authorization_code");
        fromProperties.put("appid", appId);
        fromProperties.put("secret", appSecret);
        // fromProperties.put("code", req.getCode());微信H5
        fromProperties.put("js_code", req.getCode());// 微信小程序
        logger.info("appId:" + appId + ",appSecret:" + appSecret + ",js_code:"
                + req.getCode());
        XN627302Res result = null;
        try {
            String response = PostSimulater.requestPostForm(
                WechatConstant.WXXCX_TOKEN_URL, fromProperties);
            res = getMapFromResponse(response);
            accessToken = (String) res.get("access_token");
            if (res.get("error") != null) {
                throw new BizException("XN000000",
                    "微信登录失败原因：" + res.get("error"));
            }
            if (StringUtils.isBlank(accessToken)) {
                throw new BizException("XN000000", "accessToken不能为空");
            }
            // Step3：使用Access Token来获取用户的OpenID
            String openId = (String) res.get("openid");
            // 获取unionid
            Map<String, String> wxRes = new HashMap<>();
            Properties queryParas = new Properties();
            queryParas.put("access_token", accessToken);
            queryParas.put("openid", openId);
            queryParas.put("lang", "zh_CN");
            wxRes = getMapFromResponse(PostSimulater
                .requestPostForm(WechatConstant.WX_USER_INFO_URL, queryParas));
            String unionId = (String) wxRes.get("unionid");
            String h5OpenId = (String) wxRes.get("openid");
            // Step4：根据openId，unionId从数据库中查询用户信息
            User dbUser = userBO.doGetUserByOpenId(h5OpenId, companyCode,
                systemCode);
            if (null != dbUser) {// 如果user存在，说明用户授权登录过，直接登录
                result = new XN627302Res(dbUser.getUserId());
            } else {
                String nickname = (String) wxRes.get("nickname");
                String photo = (String) wxRes.get("headimgurl");
                // Step5：判断注册是否传手机号，有则注册，无则反馈
                if (EBoolean.YES.getCode().equals(req.getIsNeedMobile())) {
                    result = doWxLoginRegMobile(req, companyCode, systemCode,
                        unionId, null, h5OpenId, nickname, photo);
                } else {
                    result = doWxLoginReg(req, companyCode, systemCode, unionId,
                        null, h5OpenId, nickname, photo);
                }
            }
        } catch (Exception e) {
            throw new BizException("xn000000", e.getMessage());
        }
        return result;
    }

    /**
     * @param response  可能是Json & Jsonp字符串 & urlParas
     *          eg：urlParas：access_token=xxx&expires_in=7776000&refresh_token=xxx
     * @return
     */
    public Map<String, String> getMapFromResponse(String response) {
        if (StringUtils.isBlank(response)) {
            return new HashMap<>();
        }
        Map<String, String> result = new HashMap<>();
        int begin = response.indexOf("{");
        int end = response.lastIndexOf("}") + 1;
        if (begin >= 0 && end > 0) {
            result = new Gson().fromJson(response.substring(begin, end),
                new TypeToken<Map<String, Object>>() {
                }.getType());
        } else {
            String[] paras = response.split("&");
            for (String para : paras) {
                result.put(para.split("=")[0], para.split("=")[1]);
            }
        }

        return result;
    }

    private XN627302Res doWxLoginRegMobile(XN627302Req req, String companyCode,
            String systemCode, String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo) {
        XN627302Res result;
        if (StringUtils.isNotBlank(req.getMobile())) {
            // 判断是否需要验证码验证码,登录前一定要验证
            if (!EBoolean.YES.getCode().equals(req.getIsLoginStatus())) {
                if (StringUtils.isBlank(req.getSmsCaptcha())) {
                    throw new BizException("xn702002", "请输入短信验证码");
                }
                // 短信验证码是否正确
                smsOutBO.checkCaptcha(req.getMobile(), req.getSmsCaptcha(),
                    "627302", companyCode, systemCode);
            }
            String mobileUserId = userBO.getUserId(req.getMobile(),
                EUserKind.Customer.getCode(), companyCode, systemCode);
            if (StringUtils.isBlank(mobileUserId)) {
                // 验证推荐人,将userReferee手机号转为用户编号
                userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
                    systemCode);
                // 插入用户信息
                String userId = userBO.doRegister(unionId, h5OpenId, appOpenId,
                    req.getMobile(), EUserKind.Customer.getCode(),
                    EUserPwd.InitPwd.getCode(), nickname, photo, companyCode,
                    systemCode);
                distributeAccount(userId, req.getMobile(),
                    EUserKind.Customer.getCode(), companyCode, systemCode);
                result = new XN627302Res(userId);
            } else {
                userBO.refreshWxInfo(mobileUserId, unionId, h5OpenId, appOpenId,
                    nickname, photo);
                result = new XN627302Res(mobileUserId);
            }
        } else {
            result = new XN627302Res(null, EBoolean.YES.getCode());
        }
        return result;
    }

    private XN627302Res doWxLoginReg(XN627302Req req, String companyCode,
            String systemCode, String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo) {
        XN627302Res result;
        userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
            systemCode);
        // 插入用户信息

        String userId = userBO.doRegister(unionId, h5OpenId, appOpenId, null,
            EUserKind.Customer.getCode(), EUserPwd.InitPwd.getCode(), nickname,
            photo, companyCode, systemCode);
        distributeAccount(userId, nickname, EUserKind.Customer.getCode(),
            companyCode, systemCode);
        result = new XN627302Res(userId, EBoolean.NO.getCode());
        return result;
    }

    // 分配账号
    private void distributeAccount(String userId, String mobile, String kind,
            String companyCode, String systemCode) {
        List<String> currencyList = new ArrayList<String>();
        if (EUserKind.Customer.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
        } else if (EUserKind.Merchant.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
            currencyList.add(ECurrency.YC_CNY.getCode());
            currencyList.add(ECurrency.MK_CNY.getCode());
        }
        // accountBO.distributeAccountList(userId, mobile, kind, currencyList,
        // companyCode, systemCode);
    }

    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(user.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = EUserStatus.NORMAL;
        }
        userBO.refreshStatus(userId, userStatus, updater, remark);
        if (!EUserKind.Plat.getCode().equals(user.getKind())
                && PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile,
                "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "805091",
                user.getCompanyCode(), user.getSystemCode());
        }
    }

    @Override
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        if (!user.getSystemCode().equals(role.getSystemCode())) {
            throw new BizException("li01004", "用户和角色系统不对应");
        }
        userBO.refreshRole(userId, roleCode, updater, remark);
    }

    // 重置登录密码
    @Override
    public void resetAdminLoginPwd(String userId, String newLoginPwd) {
        User user = userBO.getCheckUser(userId);
        userBO.resetAdminLoginPwd(user, newLoginPwd);
    }

    // 设置交易密码
    @Override
    public void setTradePwd(String userId, String smsCaptcha, String tradePwd) {
        User user = userBO.getCheckUser(userId);
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "627306",
            user.getCompanyCode(), user.getSystemCode());
        if (user.getTradePwd().equals(MD5Util.md5(tradePwd))) {
            throw new BizException("xn0000", "新旧密码不能相同");
        }
        userBO.setTradePwd(user, tradePwd);
    }

    @Override
    public void doModifyPhoto(String userId, String photo) {
        userBO.refreshPhoto(userId, photo);
    }

    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        User condition = new User();
        condition.setUserId(userId);
        List<User> userList1 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    // 更换绑定手机号
    @Override
    public void resetBindMobile(String userId, String kind,
            String oldSmsCaptcha, String newMobile, String newSmsCaptcha) {
        User user = userBO.getCheckUser(userId);
        // 根据手机号和类型判断手机号是否存在
        userBO.isMobileExist(newMobile, kind, user.getCompanyCode(),
            user.getSystemCode());
        // 旧手机号验证
        smsOutBO.checkCaptcha(user.getMobile(), oldSmsCaptcha, "627310",
            user.getCompanyCode(), user.getSystemCode());
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, newSmsCaptcha, "627310",
            user.getCompanyCode(), user.getSystemCode());
        userBO.resetBindMobile(user, newMobile);
    }

    @Override
    public Paginable<User> queryUserPage(int start, int limit, User condition) {
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        List<User> list = page.getList();
        for (User user : list) {
            // 推荐人转义
            User userReferee = userBO.getUser(user.getUserReferee());
            if (userReferee != null) {
                user.setRefereeUser(userReferee);
            }
        }
        return page;
    }

    @Override
    public List<User> queryUserList(User condition) {
        return userBO.queryUserList(condition);
    }

    @Override
    public List<User> getUserRefereeList(String userId) {
        List<User> list = new ArrayList<User>();
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        }
        String refeere = user.getUserReferee();
        // 获取上级
        User userRefeereTop1 = getTopUserRefeere(refeere, -1);
        if (userRefeereTop1 != null) {
            list.add(userRefeereTop1);
        }

        // 获取下级，下下级，下下下级
        List<User> refeeresNext1 = getNextUserRefeere(userId, 1);
        if (CollectionUtils.isNotEmpty(refeeresNext1)) {
            list.addAll(refeeresNext1);
            for (User userNext2 : refeeresNext1) {
                List<User> refeeresNext2 = getNextUserRefeere(
                    userNext2.getUserId(), 2);
                if (CollectionUtils.isNotEmpty(refeeresNext2)) {
                    list.addAll(refeeresNext2);
                }
                for (User userNext3 : refeeresNext2) {
                    List<User> refeeresNext3 = getNextUserRefeere(
                        userNext3.getUserId(), 3);
                    if (CollectionUtils.isNotEmpty(refeeresNext3)) {
                        list.addAll(refeeresNext3);
                    }
                }
            }
        }
        return list;
    }

    private User getTopUserRefeere(String userId, int refeereLevel) {
        User userRefeere = userBO.getUser(userId);
        if (userRefeere != null) {
            userRefeere.setRefeereLevel(refeereLevel);
        }
        return userRefeere;
    }

    private List<User> getNextUserRefeere(String userId, int refeereLevel) {
        List<User> userList = userBO.getUsersByUserReferee(userId);
        if (CollectionUtils.isNotEmpty(userList)) {
            for (User user : userList) {
                user.setRefeereLevel(refeereLevel);
            }
        }
        return userList;
    }

    @Override
    public User doGetUser(String userId) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        } else {
            // 拉取推荐人信息
            User refereeUser = userBO.getUser(user.getUserReferee());
            user.setRefereeUser(refereeUser);
        }
        return user;
    }

    /** 
     * @see com.bh.mall.ao.IUserAO#doUpLevel(java.lang.String, java.lang.String)
     */
    @Override
    public void doUpLevel(String userId, String level) {
        User data = new User();
        data.setUserId(userId);
        data.setLevel(StringValidater.toInteger(level));
        userBO.refreshLevel(data);
    }

    @Override
    @Transactional
    public XN627302Res applyIntent(XN627250Req req) {
        XN627302Res result = null;
        Map<String, String> wxRes = getUserInfo(req.getCode());
        String unionId = (String) wxRes.get("unionid");
        String h5OpenId = (String) wxRes.get("openid");
        // Step4：根据openId，unionId从数据库中查询用户信息
        User dbUser = userBO.doGetUserByOpenId(h5OpenId,
            ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
        if (null != dbUser) {// 如果user存在
            throw new BizException("xn0000", "您已经申请过代理");
        } else {
            String nickname = (String) wxRes.get("nickname");
            String photo = (String) wxRes.get("headimgurl");
            result = doWxLoginReg(req, ESystemCode.BH.getCode(),
                ESystemCode.BH.getCode(), unionId, null, h5OpenId, nickname,
                photo);
        }
        return result;
    }

    private Map<String, String> getUserInfo(String code) {
        String companyCode = ESystemCode.BH.getCode();
        String systemCode = ESystemCode.BH.getCode();
        // Step1：获取密码参数信息
        Map<String, String> configPwd = sysConfigBO.getConfigsMap(
            EConfigType.WEIXIN_H5.getCode(), companyCode, systemCode);

        String appId = configPwd.get(SysConstant.WX_H5_ACCESS_KEY);
        String appSecret = configPwd.get(SysConstant.WX_H5_SECRET_KEY);

        if (StringUtils.isBlank(appId)) {
            throw new BizException("XN000000", "参数appId配置获取失败，请检查配置");
        }
        if (StringUtils.isBlank(appSecret)) {
            throw new BizException("XN000000", "参数appSecret配置获取失败，请检查配置");
        }

        // Step2：通过Authorization Code获取Access Token
        String accessToken = "";
        Map<String, String> res = new HashMap<>();
        Properties fromProperties = new Properties();
        fromProperties.put("grant_type", "authorization_code");
        fromProperties.put("appid", appId);
        fromProperties.put("secret", appSecret);
        fromProperties.put("code", code);
        logger.info(
            "appId:" + appId + ",appSecret:" + appSecret + ",js_code:" + code);
        Map<String, String> wxRes = new HashMap<>();
        try {
            String response = PostSimulater
                .requestPostForm(WechatConstant.WX_TOKEN_URL, fromProperties);
            res = getMapFromResponse(response);
            accessToken = (String) res.get("access_token");
            if (res.get("error") != null) {
                throw new BizException("XN000000",
                    "微信登录失败原因：" + res.get("error"));
            }
            if (StringUtils.isBlank(accessToken)) {
                throw new BizException("XN000000", "accessToken不能为空");
            }
            // Step3：使用Access Token来获取用户的OpenID
            String openId = (String) res.get("openid");
            // 获取unionid
            Properties queryParas = new Properties();
            queryParas.put("access_token", accessToken);
            queryParas.put("openid", openId);
            queryParas.put("lang", "zh_CN");
            wxRes = getMapFromResponse(PostSimulater
                .requestPostForm(WechatConstant.WX_USER_INFO_URL, queryParas));
        } catch (Exception e) {
            throw new BizException("xn000000", e.getMessage());
        }
        return wxRes;
    }

    private XN627302Res doWxLoginReg(XN627250Req req, String companyCode,
            String systemCode, String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo) {
        XN627302Res result;
        userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
            systemCode);
        // 插入用户信息
        String userId = userBO.doRegister(req.getWxId(), req.getLevel(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), unionId, h5OpenId, appOpenId, null,
            EUserKind.Merchant.getCode(), EUserPwd.InitPwd.getCode(), nickname,
            photo, null, null, companyCode, systemCode);
        distributeAccount(userId, nickname, EUserKind.Merchant.getCode(),
            companyCode, systemCode);
        addressBO.saveAddress(userId, req.getRealName(), req.getProvince(),
            req.getCity(), req.getArea(), req.getAddress(),
            EBoolean.YES.getCode());
        result = new XN627302Res(userId, EBoolean.NO.getCode());
        return result;
    }
}
