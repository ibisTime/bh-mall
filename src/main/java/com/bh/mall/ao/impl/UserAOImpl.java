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
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.req.XN627301Req;
import com.bh.mall.dto.req.XN627302Req;
import com.bh.mall.dto.res.XN627262Res;
import com.bh.mall.dto.res.XN627302Res;
import com.bh.mall.enums.EAfterSaleStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EConfigType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ELoginType;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserLevel;
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

    @Autowired
    protected IAgencyLogBO agencyLogBO;

    @Autowired
    private IAgentImpowerBO agentImpowerBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private IInnerOrderBO innerOrderBO;

    @Autowired
    private IAfterSaleBO afterSaleBO;

    @Autowired
    private IAgentUpgradeBO agentUpgradeBO;

    // private Iintro awardBO;

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
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .before(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
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
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .before(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
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
            // 是否可被意向
            AgentImpower data = agentImpowerBO.getAgentImpowerByLevel(
                StringValidater.toInteger(req.getLevel()));
            if (EBoolean.NO.getCode().equals(data.getIsIntent())) {
                throw new BizException("xn0000", "本等级不可被意向");
            }

            result = doWxLoginReg(req, ESystemCode.BH.getCode(),
                ESystemCode.BH.getCode(), unionId, null, h5OpenId, nickname,
                photo);
        }
        return result;
    }

    @Override
    @Transactional
    public XN627302Res applyHaveUserReferee(XN627251Req req) {
        XN627302Res result = null;
        Map<String, String> wxRes = getUserInfo(req.getCode());
        String unionId = (String) wxRes.get("unionId");
        String h5OpenId = (String) wxRes.get("h5OpenId");
        User dbUser = userBO.doGetUserByOpenId(h5OpenId,
            ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
        if (null != dbUser) {
            throw new BizException("xn0000", "您已经申请过代理");
        } else {
            String nickname = (String) wxRes.get("nickname");
            String photo = (String) wxRes.get("photo");

            userBO.doCheckOpenId(unionId, h5OpenId, null,
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            // 是否可被意向
            AgentImpower data = agentImpowerBO.getAgentImpowerByLevel(
                StringValidater.toInteger(req.getLevel()));
            if (EBoolean.NO.getCode().equals(data.getIsIntent())) {
                throw new BizException("xn0000", "本等级不可被意向");
            }

            String userId = userBO.doRegister(req.getRealName(), req.getLevel(),
                req.getWxId(), req.getIdBehind(), req.getIdFront(),
                req.getIntroducer(), req.getPayPdf(), req.getFromInfo(),
                req.getUserReferee(), req.getMobile(), req.getProvince(),
                req.getCity(), req.getArea(), req.getAddress(),
                EUserPwd.InitPwd.getCode(), photo, nickname, unionId, h5OpenId,
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());

            distributeAccount(userId, nickname, EUserKind.Merchant.getCode(),
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            addressBO.saveAddress(userId, req.getRealName(), req.getProvince(),
                req.getCity(), req.getArea(), req.getAddress(),
                EBoolean.YES.getCode());
            result = new XN627302Res(userId, EBoolean.NO.getCode());

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

    @Override
    public void allotAgency(String userId, String toUserId, String manager,
            String approver) {
        User data = userBO.getUser(userId);
        User toUser = userBO.getUser(toUserId);
        if (data.getApplyLevel() <= toUser.getLevel()) {
            throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
        }
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setManager(manager);
        String logCode = agencyLogBO.saveAgencyLog(data, toUserId);
        data.setLastAgentLog(logCode);
        userBO.allotAgency(data);
    }

    @Override
    public void acceptIntention(String userId, String approver, String remark) {
        User data = userBO.getUser(userId);
        if (!EUserStatus.TO_WILL.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }
        data.setApprover(approver);
        data.setApplyDatetime(new Date());
        data.setRemark(remark);
        data.setStatus(EUserStatus.TO_Approve.getCode());
        String logCode = agencyLogBO.acceptIntention(data);
        data.setLastAgentLog(logCode);
        userBO.acceptIntention(data);

    }

    @Override
    public void ignore(String userId, String aprrover) {
        User data = userBO.getUser(userId);
        if (!EUserStatus.TO_WILL.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }
        data.setApprover(aprrover);
        data.setApproveDatetime(new Date());
        data.setStatus(EUserStatus.Ignored.getCode());
        String logCode = agencyLogBO.ignore(data);
        data.setLastAgentLog(logCode);
        userBO.ignore(data);
    }

    @Override
    public void updateInformation(XN627255Req req) {
        User data = userBO.getUser(req.getUserId());
        data.setLevel(StringValidater.toInteger(req.getLevel()));
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());

        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setRealName(req.getRealName());
        userBO.updateInformation(data);
    }

    @Override
    public void cancelImpower(String userId) {
        User data = userBO.getUser(userId);
        Account condition = new Account();
        condition.setUserId(data.getUserId());

        // 账户是否余额
        List<Account> accountList = accountBO.queryAccountList(condition);
        for (Account account : accountList) {
            if (account.getAmount() != 0) {
                throw new BizException("xn0000",
                    "您的" + account.getCurrency() + "账户还有余额,请取出后再申请");
            }
        }
        // 是否有未完成的订单
        Order oCondition = new Order();
        oCondition.setApplyUser(data.getUserId());
        oCondition.setStatusForQuery(EOrderStatus.NO_CallOFF.getCode());
        long count = orderBO.selectCount(oCondition);
        if (count != 0) {
            throw new BizException("xn000", "您还有未完成的订单,请在订单完成后申请");
        }

        InnerOrder ioCondition = new InnerOrder();
        ioCondition.setApplyUser(data.getUserId());
        ioCondition.setStatusForQuery(EOrderStatus.NO_CallOFF.getCode());
        long ioCount = innerOrderBO.selectCount(ioCondition);
        if (ioCount != 0) {
            throw new BizException("xn000", "您还有未完成的内购订单,请在订单完成后申请");
        }
        AfterSale asCondition = new AfterSale();
        asCondition.setApplyUser(data.getUserId());
        asCondition.setStatus(EAfterSaleStatus.NO_CallOff.getCode());
        long asCount = afterSaleBO.selectCount(asCondition);
        if (asCount != 0) {
            throw new BizException("xn000", "您还有未完成的售后单,请在订单完成后申请");
        }

        String logCode = agencyLogBO.cancelImpower(data);
        data.setLastAgentLog(logCode);
        userBO.cancelImpower(data);
    }

    @Override
    public void approveImpower(String userId, String approver, String result,
            String remark) {
        User data = userBO.getUser(userId);
        if (!EUserStatus.TO_Approve.getCode().equals(data.getStatus())
                || !EUserStatus.TO_Company_Impower.getCode()
                    .equals(data.getStatus())) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        User approveUser = userBO.getUser(approver);
        String status = EUserStatus.Impowered.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setHighUserId(approver);
            AgentImpower aiData = agentImpowerBO
                .getAgentImpowerByLevel(data.getApplyLevel());
            if (EBoolean.YES.getCode().equals(aiData.getIsRealName())) {
                if (StringUtils.isBlank(data.getRealName())) {
                    throw new BizException("xn000", "本等级授权需要实名，请实名后再授权");
                }
            }
            // 是否需要公司授权
            if (EBoolean.YES.getCode().equals(aiData.getIsCompanyImpower())) {
                status = EUserStatus.TO_Company_Impower.getCode();
                // 审核人是否是平台
                if (EUserKind.Customer.getCode()
                    .equals(approveUser.getKind())) {
                    status = EUserStatus.Impowered.getCode();
                    // 介绍人不为空且等级低于被介绍人
                    if (StringUtils.isNotBlank(data.getIntroducer())) {
                        if (approveUser.getLevel() < data.getLevel()) {
                            long amount = aiData.getMinCharge();
                            if (data.getLevel() == StringValidater
                                .toInteger(EUserLevel.ONE.getCode())) {
                                // accountBO.transAmountCZB(
                                // ESysUser.SYS_USER_BH.getCode(),
                                // ECurrency.YJ_CNY.getCode(),
                                // data.getIntroducer(),
                                // ECurrency.YJ_CNY.getCode(), ,
                                // bizType, fromBizNote, toBizNote, refNo);
                            } else {
                                // accountBO.transAmountCZB(
                                // ESysUser.SYS_USER_BH.getCode(),
                                // ECurrency.YJ_CNY.getCode(),
                                // data.getIntroducer(),
                                // ECurrency.YJ_CNY.getCode(), ,
                                // bizType, fromBizNote, toBizNote, refNo);
                            }
                        }
                    }
                }
            }

        } else {
            status = EUserStatus.NO_Through.getCode();
        }
        data.setApprover(approver);
        data.setApplyDatetime(new Date());
        data.setRemark(remark);
        data.setStatus(status);
        String logCode = agencyLogBO.approveImpower(data);
        data.setLastAgentLog(logCode);

        userBO.approveImpower(data);

    }

    @Override
    public void approveCanenl(String userId, String approver, String result,
            String remark) {
        User data = userBO.getUser(userId);
        if (!EUserStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该代理未处于申请取消状态");
        }

        String status = EUserStatus.NO_Through.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            status = EUserStatus.Canceled.getCode();
            Account condition = new Account();
            condition.setUserId(data.getUserId());
            List<Account> list = accountBO.queryAccountList(condition);
            // 账户清零
            if (CollectionUtils.isNotEmpty(list)) {
                for (Account account : list) {
                    if (account.getAmount() > 0) {
                        accountBO.transAmountCZB(data.getUserId(),
                            account.getCurrency(),
                            ESysUser.SYS_USER_BH.getCode(),
                            account.getCurrency(), account.getAmount(),
                            EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                            EBizType.AJ_QKYE.getValue(), null);
                    }
                }
            }
        }
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);
        String logCode = agencyLogBO.approveCanenl(data);
        data.setLastAgentLog(logCode);
        userBO.approveCanenl(data);
    }

    @Override
    public void editHighUser(String userId, String highUser, String updater) {
        User data = userBO.getUser(userId);

        User highData = userBO.getUser(highUser);
        if (data.getLevel() <= highData.getLevel()) {
            throw new BizException("xn0000", "不能大于上级等级");
        }
        data.setHighUserId(highData.getUserId());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        userBO.refreshHighUser(data);

    }

    @Override
    public void editUserReferee(String userId, String userReferee,
            String updater) {
        User data = userBO.getUser(userId);
        User defereeData = userBO.getUser(userReferee);
        if (defereeData.getLevel() != data.getLevel()) {
            throw new BizException("xn000", "推荐人只能是平级");
        }
        userBO.refreshUserReferee(data, userReferee, updater);
    }

    @Override
    public void editManager(String userId, String manager, String updater) {
        User data = userBO.getUser(userId);
        userBO.getCheckUser(manager);
        userBO.refreshManager(data, manager, updater);

    }

    @Override
    public XN627262Res upgradeLevel(String userId, String highLevel,
            String payPdf, String teamName) {
        XN627262Res res = null;
        User data = userBO.getUser(userId);

        if (!EUserStatus.NORMAL.getCode().equals(data.getStatus())
                || !EUserStatus.Impowered.getCode().equals(data.getStatus())
                || !EUserStatus.Upgraded.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "您的状态无法申请升级");
        }
        if (data.getLevel() <= StringValidater.toInteger(highLevel)) {
            throw new BizException("xn0000", "升级等级要大于当前等级");
        }
        if (StringValidater.toInteger(EUserLevel.ONE.getCode()) == data
            .getLevel()) {
            throw new BizException("xn0000", "您的等级已经为最高等级，无法继续升级");
        }

        AgentUpgrade auData = agentUpgradeBO
            .getAgentUpgradeByLevel(data.getLevel());
        // 获取推荐人数
        User condition = new User();
        condition.setUserReferee(data.getUserId());
        List<User> list = userBO.queryUserList(condition);

        if (list.size() < auData.getReNumber()) {
            throw new BizException("xn0000", "您所推荐人数不足,无法升级");
        }
        // 余额是否清零
        if (EBoolean.YES.getCode().equals(auData.getIsReset())) {
            res = new XN627262Res("本等级余额将被清零，请确保您的账户中没有余额");
        }

        data.setApplyLevel(StringValidater.toInteger(highLevel));
        data.setTeamName(teamName);
        data.setStatus(EUserStatus.TO_Upgrade.getCode());
        data.setApplyDatetime(new Date());
        String logCode = agencyLogBO.upgradeLevel(data, payPdf);
        data.setLastAgentLog(logCode);
        userBO.upgradeLevel(data);
        return res;
    }

    @Override
    public void approveUpgrade(String userId, String approver, String remark,
            String result) {
        User data = userBO.getUser(userId);
        String status = EUserStatus.NO_Through.getCode();
        if (EBoolean.YES.getCode().equals(result)) {
            status = EUserStatus.Upgraded.getCode();
            AgentUpgrade auData = agentUpgradeBO
                .getAgentUpgradeByLevel(data.getLevel());
            Account condition = new Account();
            condition.setUserId(data.getUserId());
            List<Account> list = accountBO.queryAccountList(condition);
            // 账户清零
            if (CollectionUtils.isNotEmpty(list)) {
                for (Account account : list) {
                    if (account.getAmount() > 0) {
                        accountBO.transAmountCZB(data.getUserId(),
                            account.getCurrency(),
                            ESysUser.SYS_USER_BH.getCode(),
                            account.getCurrency(), account.getAmount(),
                            EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                            EBizType.AJ_QKYE.getValue(), null);
                    }
                }
            }

            if (EBoolean.YES.getCode().equals(auData.getIsCompanyApprove())) {
                status = EUserStatus.TO_Company_Upgrade.getCode();
            }
        }
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);
        String logCode = agencyLogBO.approveUpgrade(data);
        data.setLastAgentLog(logCode);
        userBO.approveUpgrade(data);
    }

    @Override
    public Paginable<User> queryLowUser(int start, int limit, User condition) {
        return userBO.getPaginable(start, limit, condition);
    }

    // **************************************
    @Override
    public Paginable<User> queryAllLowUser(int start, int limit,
            User condition) {
        long totalCount = userBO.getTotalCount(condition);
        Page<User> page = new Page<User>(start, limit, totalCount);
        List<User> list = userBO.selectList(condition, page.getPageNo(),
            page.getPageSize());
        if (CollectionUtils.isNotEmpty(list)) {
            for (User user : list) {
                condition.setHighUserId(user.getUserId());
                List<User> userList = userBO.selectList(condition,
                    page.getPageNo(), page.getPageSize());
                user.setUserList(userList);
            }
            this.queryAllLowUser(page.getPageNo(), page.getPageSize(),
                condition);
        }
        return page;
    }

    @Override
    public Paginable<User> queryAgentPage(int start, int limit,
            User condition) {
        Page<User> page = null;
        for (int i = 1; i <= 5; i++) {
            condition.setLevel(i);
            long totalCount = userBO.getTotalCount(condition);
            page = new Page<User>(start, limit, totalCount);
            List<User> list = userBO.selectList(condition, page.getPageNo(),
                page.getPageSize());
            if (CollectionUtils.isNotEmpty(list)) {
                for (User user : list) {
                    condition.setHighUserId(user.getUserId());
                    List<User> userList = userBO.selectList(condition,
                        page.getPageNo(), page.getPageSize());
                    user.setUserList(userList);
                }
            } else {
                break;
            }
        }
        return page;
    }

    @Override
    public Paginable<User> queryIntentionAgentPageFront(int start, int limit,
            User condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .before(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        condition.setKind(EUserKind.Merchant.getCode());
        long totalCount = userBO.getTotalCount(condition);
        Page<User> page = new Page<User>(start, limit, totalCount);
        List<User> list = userBO.selectAgentFront(condition, page.getPageNo(),
            page.getPageSize());
        page.setList(list);
        return page;
    }

    @Override
    public Paginable<User> queryIntentionAgentPage(int start, int limit,
            User condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .before(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        condition.setKind(EUserKind.Merchant.getCode());
        return userBO.getPaginable(start, limit, condition);
    }

    @Override
    public User getUserHaveReferee(int start, int limit, String userId) {
        User data = userBO.getUser(userId);
        AgencyLog condition = new AgencyLog();
        condition.setApplyUser(userId);
        List<AgencyLog> list = agencyLogBO.queryAgencyLogPage(start, limit,
            condition);
        data.setLogList(list);
        return data;
    }

}
