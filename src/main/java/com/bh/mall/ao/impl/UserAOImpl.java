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
import java.util.Date;
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
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627201Req;
import com.bh.mall.dto.req.XN805043Req;
import com.bh.mall.dto.req.XN805095Req;
import com.bh.mall.dto.req.XN805170Req;
import com.bh.mall.dto.res.XN805041Res;
import com.bh.mall.dto.res.XN805170Res;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EConfigType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ELoginType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserPwd;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.PostSimulater;
import com.bh.mall.third.hx.impl.InstantMsgImpl;
import com.bh.mall.util.RandomUtil;
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
    protected InstantMsgImpl instantMsgImpl;

    @Autowired
    protected ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    protected ISYSConfigBO sysConfigBO;

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
        return user.getUserId();
    }

    @Override
    @Transactional
    public XN805041Res doRegister(String mobile, String loginPwd,
            String userReferee, String userRefereeKind, String smsCaptcha,
            String kind, String isRegHx, String province, String city,
            String area, String companyCode, String systemCode) {
        // 1、参数校验
        userBO.isMobileExist(mobile, kind, companyCode, systemCode);
        String userRefereeId = userBO.getUserId(userReferee, userRefereeKind,
            companyCode, systemCode);
        if (StringUtils.isBlank(userRefereeId)) {
            throw new BizException("xn702002", userReferee + "用户不存在");
        }
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805041", companyCode,
            systemCode);
        // 2、注册用户
        String userId = userBO.doRegister(mobile, loginPwd, userRefereeId,
            kind, province, city, area, companyCode, systemCode);
        // 3、分配账户
        distributeAccount(userId, mobile, kind, companyCode, systemCode);
        // 5、第三方账号注册
        thirdRegist(userId, isRegHx, companyCode, systemCode);
        return new XN805041Res(userId);
    }

    // 分配账号
    private void distributeAccount(String userId, String mobile, String kind,
            String companyCode, String systemCode) {
        List<String> currencyList = new ArrayList<String>();
        if (ESystemCode.BH.getCode().equals(systemCode)) {
            currencyList.add(ECurrency.YE_CNY.getCode());
            currencyList.add(ECurrency.TJ_CNY.getCode());
            currencyList.add(ECurrency.CH_CNY.getCode());
            currencyList.add(ECurrency.JS_CNY.getCode());
        }
    }

    // 第三方注册
    private void thirdRegist(String userId, String isRegHx, String companyCode,
            String systemCode) {
        // 即时通信注册
        if (EBoolean.YES.getCode().equals(isRegHx)) {
            instantMsgImpl.doRegisterUser(userId, systemCode);
        }
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

    @Override
    @Transactional
    public String doAddUser(XN627201Req req) {
        String userId = null;
        if (ESystemCode.BH.getCode().equals(req.getSystemCode())) {
            userId = doAddUserBH(req);
        } else {
            throw new BizException("xn805000", "系统对应用户编号不存在");
        }
        return userId;
    }

    private String doAddUserBH(XN627201Req req) {
        String userId = null;
        if (EUserKind.Customer.getCode().equals(req.getKind())
                || EUserKind.Merchant.getCode().equals(req.getKind())) {
            // 验证手机号
            userBO.isMobileExist(req.getMobile(), req.getKind(),
                req.getCompanyCode(), req.getSystemCode());
            // 判断登录密码是否为空
            if (StringUtils.isBlank(req.getLoginPwd())) {
                req.setLoginPwd(RandomUtil.generate6());
            }
            userId = userBO.doAddUser(req);

            // 分配账户
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.YE_CNY.getCode());
            currencyList.add(ECurrency.TJ_CNY.getCode());
            currencyList.add(ECurrency.CH_CNY.getCode());
            currencyList.add(ECurrency.JS_CNY.getCode());
            // 发送短信
            smsOutBO.sendSmsOut(req.getMobile(),
                "尊敬的" + PhoneUtil.hideMobile(req.getMobile())
                        + "用户，您已成功注册。初始化登录密码为" + req.getLoginPwd()
                        + "，请及时登录网站更改密码。", "805042", req.getCompanyCode(),
                req.getSystemCode());
        } else if (EUserKind.Plat.getCode().equals(req.getKind())) {
            // 验证登录名
            userBO.isLoginNameExist(req.getLoginName(), req.getKind(),
                req.getCompanyCode(), req.getSystemCode());
            userId = userBO.doAddUser(req);
        } else {
            throw new BizException("xn805042", "用户类型" + req.getKind() + "未能识别");
        }
        return userId;
    }

    @Override
    @Transactional
    public String doApplyRegUser(XN805043Req req) {
        String userId = null;
        return userId;
    }

    @Override
    public String doCaptchaLoginReg(String mobile, String kind,
            String smsCaptcha, String companyCode, String systemCode) {
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805051", companyCode,
            systemCode);
        String userId = userBO.getUserId(mobile, kind, companyCode, systemCode);
        if (StringUtils.isBlank(userId)) {
            userId = userBO.saveUser(mobile, kind, companyCode, systemCode);
        }
        return userId;
    }

    @Override
    public void doModifyPhoto(String userId, String photo) {
        userBO.refreshPhoto(userId, photo);
    }

    @Override
    @Transactional
    public void doModifyUser(XN805095Req req) {
        User dbUser = userBO.getUser(req.getUserId());
        if (dbUser == null) {
            throw new BizException("xn000000", "该用户编号不存在！");
        }
        User condition = new User();
        condition.setKind(dbUser.getKind());
        condition.setProvince(req.getProvince());
        condition.setCity(req.getCity());
        condition.setArea(req.getArea());
        List<User> list = userBO.queryUserList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            User user = list.get(0);
            if (!user.getUserId().equals(req.getUserId())) {
                throw new BizException("xn000000", "该辖区已存在合伙人！");
            }
        }

        dbUser.setMobile(req.getMobile());
        dbUser.setIdKind(req.getIdKind());
        dbUser.setIdNo(req.getIdNo());
        dbUser.setRealName(req.getRealName());
        dbUser.setUpdater(req.getUpdater());

        dbUser.setUpdateDatetime(new Date());
        dbUser.setRemark(req.getRemark());
        dbUser.setProvince(req.getProvince());
        dbUser.setCity(req.getCity());
        dbUser.setArea(req.getArea());
        userBO.refreshUser(dbUser);
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

    @Override
    @Transactional
    public XN805170Res doLoginWeChat(XN805170Req req) {
        String companyCode = req.getCompanyCode();
        String systemCode = req.getSystemCode();
        // Step1：获取密码参数信息
        Map<String, String> configPwd = sysConfigBO.getConfigsMap(
            EConfigType.WEIXIN_H5.getCode(), companyCode, systemCode);
        String appId = null;
        String appSecret = null;
        if (EConfigType.WEIXIN_APP.getCode().equals(req.getType())) {
            appId = configPwd.get(SysConstant.WX_APP_ACCESS_KEY);
            appSecret = configPwd.get(SysConstant.WX_APP_SECRET_KEY);
        } else if (EConfigType.WEIXIN_H5.getCode().equals(req.getType())) {
            appId = configPwd.get(SysConstant.WX_H5_ACCESS_KEY);
            appSecret = configPwd.get(SysConstant.WX_H5_SECRET_KEY);
        } else {
            throw new BizException("XN000000", "登录类型不支持");
        }
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
        fromProperties.put("code", req.getCode());
        logger.info("appId:" + appId + ",appSecret:" + appSecret + ",code:"
                + req.getCode());
        XN805170Res result = null;
        try {
            String response = PostSimulater.requestPostForm(
                WechatConstant.WX_TOKEN_URL, fromProperties);
            res = getMapFromResponse(response);
            accessToken = (String) res.get("access_token");
            if (res.get("error") != null) {
                throw new BizException("XN000000", "微信登录失败原因："
                        + res.get("error"));
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
            wxRes = getMapFromResponse(PostSimulater.requestPostForm(
                WechatConstant.WX_USER_INFO_URL, queryParas));
            String unionId = (String) wxRes.get("unionid");
            String appOpenId = null;
            String h5OpenId = null;
            if (EConfigType.WEIXIN_APP.getCode().equals(req.getType())) {
                appOpenId = (String) wxRes.get("openid");
            } else if (EConfigType.WEIXIN_H5.getCode().equals(req.getType())) {
                h5OpenId = (String) wxRes.get("openid");
            }
            // Step4：根据openId，unionId从数据库中查询用户信息
            User dbUser = userBO.doGetUserByOpenId(appOpenId, h5OpenId,
                companyCode, systemCode);
            if (null != dbUser) {// 如果user存在，说明用户授权登录过，直接登录
                result = new XN805170Res(dbUser.getUserId());
            } else {
                String nickname = (String) wxRes.get("nickname");
                String photo = (String) wxRes.get("headimgurl");
                // Step5：判断注册是否传手机号，有则注册，无则反馈
                if (EBoolean.YES.getCode().equals(req.getIsNeedMobile())) {
                    result = doWxLoginRegMobile(req, companyCode, systemCode,
                        unionId, appOpenId, h5OpenId, nickname, photo, null);
                } else {
                    result = doWxLoginReg(req, companyCode, systemCode,
                        unionId, appOpenId, h5OpenId, nickname, photo, null);
                }
            }
        } catch (Exception e) {
            throw new BizException("xn000000", e.getMessage());
        }
        return result;
    }

    private XN805170Res doWxLoginReg(XN805170Req req, String companyCode,
            String systemCode, String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo, String gender) {
        XN805170Res result;
        // 验证推荐人,将userReferee手机号转为用户编号
        String userRefereeId = userBO.getUserId(req.getUserReferee(),
            req.getUserRefereeKind(), companyCode, systemCode);
        if (StringUtils.isBlank(userRefereeId)) {
            throw new BizException("xn702002", req.getUserReferee() + "用户不存在");
        }
        userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
            systemCode);
        // 插入用户信息
        String userId = userBO.doRegister(unionId, h5OpenId, appOpenId, null,
            req.getKind(), EUserPwd.InitPwd.getCode(), nickname, photo, gender,
            userRefereeId, companyCode, systemCode);
        distributeAccount(userId, nickname, req.getKind(), companyCode,
            systemCode);
        result = new XN805170Res(userId, EBoolean.NO.getCode());
        return result;
    }

    private XN805170Res doWxLoginRegMobile(XN805170Req req, String companyCode,
            String systemCode, String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo, String gender) {
        XN805170Res result;
        if (StringUtils.isNotBlank(req.getMobile())) {
            // 判断是否需要验证码验证码,登录前一定要验证
            if (!EBoolean.YES.getCode().equals(req.getIsLoginStatus())) {
                if (StringUtils.isBlank(req.getSmsCaptcha())) {
                    throw new BizException("xn702002", "请输入短信验证码");
                }
                // 短信验证码是否正确
                smsOutBO.checkCaptcha(req.getMobile(), req.getSmsCaptcha(),
                    "805170", companyCode, systemCode);
            }
            String mobileUserId = userBO.getUserId(req.getMobile(),
                req.getKind(), companyCode, systemCode);
            if (StringUtils.isBlank(mobileUserId)) {
                // 验证推荐人,将userReferee手机号转为用户编号
                String userRefereeId = userBO.getUserId(req.getUserReferee(),
                    req.getUserRefereeKind(), companyCode, systemCode);
                userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
                    systemCode);
                // 插入用户信息
                String userId = userBO.doRegister(unionId, h5OpenId, appOpenId,
                    req.getMobile(), req.getKind(), EUserPwd.InitPwd.getCode(),
                    nickname, photo, gender, userRefereeId, companyCode,
                    systemCode);
                distributeAccount(userId, req.getMobile(), req.getKind(),
                    companyCode, systemCode);
                result = new XN805170Res(userId);
            } else {
                userBO.refreshWxInfo(mobileUserId, unionId, h5OpenId,
                    appOpenId, nickname, photo, gender);
                result = new XN805170Res(mobileUserId);
            }
        } else {
            result = new XN805170Res(null, EBoolean.YES.getCode());
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

    /** 
     * @see com.bh.mall.ao.IUserAO#doUpLevel(java.lang.String, java.lang.String)
     */
    @Override
    public void doUpLevel(String userId, String level) {
        User data = new User();
        data.setUserId(userId);
        data.setNowLevel(level);
        userBO.refreshLevel(data);
    }
}
