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
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IIntroBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IReportBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.PwdUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.Intro;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.domain.User;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.req.XN627362Req;
import com.bh.mall.dto.res.XN627302Res;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EAddressType;
import com.bh.mall.enums.EAfterSaleStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
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

@Service
public class UserAOImpl implements IUserAO {

    private static Logger logger = Logger.getLogger(UserAOImpl.class);

    @Autowired
    IUserBO userBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IAgencyLogBO agencyLogBO;

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IOrderBO orderBO;

    @Autowired
    IInnerOrderBO innerOrderBO;

    @Autowired
    IAfterSaleBO afterSaleBO;

    @Autowired
    IAgentUpgradeBO agentUpgradeBO;

    @Autowired
    IIntroBO introBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IWareHouseBO wareHouseBO;

    @Autowired
    IReportBO reportBO;

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
    public XN627302Res doLoginWeChatByMerchant(String code, String userKind,
            String highUserId) {
        String status = EUserStatus.TO_MIND.getCode();
        if (StringUtils.isNotBlank(highUserId)) {
            status = EUserStatus.IMPOWERO_INFO.getCode();
        }
        return doLoginWeChatH(code, userKind, highUserId, status);
    }

    @Override
    public XN627302Res doLoginWeChatByCustomer(String code, String nickname,
            String avatarUrl, String kind) {
        return doLoginWeChatM(code, nickname, avatarUrl, kind);
    }

    @Transactional
    private XN627302Res doLoginWeChatM(String code, String nickname,
            String photo, String kind) {
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
        String sessionKey = "";
        Map<String, Object> res = new HashMap<>();
        Properties fromProperties = new Properties();
        fromProperties.put("grant_type", "authorization_code");
        fromProperties.put("appid", appId);
        fromProperties.put("secret", appSecret);
        // fromProperties.put("code", code);// 微信H5
        fromProperties.put("js_code", code);// 微信小程序
        logger.info(
            "appId:" + appId + ",appSecret:" + appSecret + ",js_code:" + code);
        XN627302Res result = null;
        try {
            String response = PostSimulater.requestPostForm(
                WechatConstant.WXXCX_TOKEN_URL, fromProperties);
            res = getMapFromResponse(response);
            sessionKey = (String) res.get("session_key");
            if (res.get("error") != null) {
                throw new BizException("XN000000",
                    "微信登录失败原因：" + res.get("error"));
            }
            if (StringUtils.isBlank(sessionKey)) {
                throw new BizException("XN000000", "sessionKey不能为空");
            }
            // Step3：使用Access Token来获取用户的OpenID
            String openId = (String) res.get("openid");
            if (StringUtils.isBlank(openId)) {
                throw new BizException("XN000000", "opind不能为空");
            }
            // Step4：根据openId，unionId从数据库中查询用户信息
            User dbUser = userBO.doGetUserByOpenId(openId, companyCode,
                systemCode);
            if (null != dbUser) {// 如果user存在，说明用户授权登录过，直接登录
                result = new XN627302Res(dbUser.getUserId(),
                    dbUser.getStatus());
            } else {
                result = doWxLoginReg(companyCode, systemCode, null, null,
                    openId, nickname, photo, kind, null,
                    EUserStatus.NORMAL.getCode());
                // Step5：判断注册是否传手机号，有则注册，无则反馈
                // if (EBoolean.YES.getCode().equals(req.getIsNeedMobile())) {
                // result = doWxLoginRegMobile(req, companyCode, systemCode,
                // unionId, appOpenId, h5OpenId, nickname, photo, gender);
                // } else {
                // result = doWxLoginReg(req, companyCode, systemCode, unionId,
                // appOpenId, h5OpenId, nickname, photo, gender);
                // }
            }
        } catch (Exception e) {
            throw new BizException("xn000000", e.getMessage());
        }
        return result;
    }

    @Transactional
    private XN627302Res doLoginWeChatH(String code, String userKind,
            String highUserId, String status) {
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
        Map<String, Object> res = new HashMap<>();
        Properties fromProperties = new Properties();
        fromProperties.put("grant_type", "authorization_code");
        fromProperties.put("appid", appId);
        fromProperties.put("secret", appSecret);
        fromProperties.put("code", code);// 微信H5
        logger.info(
            "appId:" + appId + ",appSecret:" + appSecret + ",js_code:" + code);
        XN627302Res result = null;
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
            Map<String, Object> wxRes = new HashMap<>();
            Properties queryParas = new Properties();
            queryParas.put("access_token", accessToken);
            queryParas.put("openid", openId);
            queryParas.put("lang", "zh_CN");
            wxRes = getMapFromResponse(PostSimulater
                .requestPostForm(WechatConstant.WX_USER_INFO_URL, queryParas));
            String unionId = (String) wxRes.get("unionid");
            String h5OpenId = (String) wxRes.get("openid");
            System.out.println("unionId:" + unionId);
            // Step4：根据openId，unionId从数据库中查询用户信息
            User dbUser = userBO.doGetUserByOpenId(h5OpenId, companyCode,
                systemCode);

            // 用户是否关注了公众号
            // 1、获取全局token
            Map<String, Object> resMap1 = new HashMap<>();
            Properties properties1 = new Properties();
            properties1.put("grant_type", "client_credential");
            properties1.put("appid", appId);
            properties1.put("secret", appSecret);
            resMap1 = getMapFromResponse(PostSimulater.requestPostForm(
                WechatConstant.WXXCX_GLOBAL_TOKEN_URL, properties1));
            String token = (String) resMap1.get("access_token");
            if (StringUtils.isBlank(token)) {
                throw new BizException("XN000000", "accessToken不能为空");
            }

            Map<String, Object> resMap2 = new HashMap<>();
            Properties properties2 = new Properties();
            properties2.put("access_token", token);
            properties2.put("openid", openId);
            resMap2 = getMapFromResponse(PostSimulater
                .requestPostForm(WechatConstant.WX_USER_CGI_URL, properties2));

            String subscribe = String.valueOf(resMap2.get("subscribe"));

            if (null != dbUser) {// 如果user存在，说明用户授权登录过，直接登录
                result = new XN627302Res(dbUser.getUserId(), dbUser.getStatus(),
                    subscribe);
            } else {
                String nickname = (String) wxRes.get("nickname");
                String photo = (String) wxRes.get("headimgurl");

                result = doWxLoginReg(companyCode, systemCode, unionId, null,
                    h5OpenId, nickname, photo, userKind, highUserId, status);
                result = new XN627302Res(result.getUserId(), result.getStatus(),
                    subscribe);
            }
            // result.setSubscribe(subscribe);
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
    public Map<String, Object> getMapFromResponse(String response) {
        if (StringUtils.isBlank(response)) {
            return new HashMap<>();
        }
        Map<String, Object> result = new HashMap<>();
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

    /*
     * private XN627302Res doWxLoginRegMobile(String mobile, String
     * isLoginStatus, String smsCaptcha, String smsCaptchaNumber, String
     * companyCode, String systemCode, String unionId, String appOpenId, String
     * h5OpenId, String nickname, String photo, String userKind) { XN627302Res
     * result; if (StringUtils.isNotBlank(mobile)) { // 判断是否需要验证码验证码,登录前一定要验证 if
     * (!EBoolean.YES.getCode().equals(isLoginStatus)) { if
     * (StringUtils.isBlank(smsCaptcha)) { throw new BizException("xn702002",
     * "请输入短信验证码"); } // 短信验证码是否正确 smsOutBO.checkCaptcha(mobile, smsCaptcha,
     * smsCaptchaNumber, companyCode, systemCode); } String mobileUserId =
     * userBO.getUserId(mobile, userKind, companyCode, systemCode); if
     * (StringUtils.isBlank(mobileUserId)) { userBO.doCheckOpenId(unionId,
     * h5OpenId, appOpenId, companyCode, systemCode); // 插入用户信息 String userId =
     * userBO.doRegister(unionId, h5OpenId, appOpenId, mobile, userKind,
     * EUserPwd.InitPwd.getCode(), nickname, photo, companyCode, systemCode);
     * distributeAccount(userId, mobile, userKind, companyCode, systemCode);
     * result = new XN627302Res(userId); } else {
     * userBO.refreshWxInfo(mobileUserId, unionId, h5OpenId, appOpenId,
     * nickname, photo); result = new XN627302Res(mobileUserId); } } else {
     * result = new XN627302Res(null, EBoolean.YES.getCode()); } return result;
     * }
     */

    private XN627302Res doWxLoginReg(String companyCode, String systemCode,
            String unionId, String appOpenId, String h5OpenId, String nickname,
            String photo, String userKind, String highUser, String status) {
        userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
            systemCode);
        Integer level = 0;
        if (EUserKind.Customer.getCode().equals(userKind)) {
            level = 6;
        }
        String userId = userBO.doRegister(unionId, h5OpenId, appOpenId, null,
            userKind, EUserPwd.InitPwd.getCode(), nickname, photo, status,
            level, companyCode, systemCode, highUser);
        XN627302Res result = new XN627302Res(userId, status);
        return result;
    }

    // 分配账号
    private List<String> distributeAccount(String userId, String mobile,
            String kind, String companyCode, String systemCode) {
        List<String> currencyList = new ArrayList<String>();
        if (EUserKind.Customer.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
        } else if (EUserKind.Merchant.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
            currencyList.add(ECurrency.MK_CNY.getCode());
        }
        return currencyList;
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
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha) {
        User user = userBO.getCheckUser(userId);
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        userBO.isMobileExist(newMobile, user.getCompanyCode(),
            user.getSystemCode());
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "627310",
            user.getCompanyCode(), user.getSystemCode());
        userBO.resetBindMobile(user, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已将手机号码改为" + newMobile + "，您的登录名更改为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "631072", user.getCompanyCode(), user.getSystemCode());

    }

    @Override
    public Paginable<User> queryUserPage(int start, int limit, User condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        List<User> list = page.getList();
        for (User user : list) {
            // 推荐人、上级、介绍人转义
            if (StringUtils.isNotBlank(user.getUserReferee())) {
                User userReferee = userBO.getUserName(user.getUserReferee());
                if (userReferee != null) {
                    user.setRefereeUser(userReferee);
                }
            }

            if (StringUtils.isNotBlank(user.getHighUserId())) {
                User highUser = userBO.getUserName(user.getHighUserId());
                if (highUser != null) {
                    user.setHighUser(highUser);
                }
            }
            if (StringUtils.isNotBlank(user.getIntroducer())) {
                User introduceName = userBO.getUserName(user.getIntroducer());
                if (introduceName != null) {
                    user.setIntroduceName(introduceName.getRealName());
                }
            }
            // 关联管理员
            if (StringUtils.isNotBlank(user.getManager())) {
                User manageName = userBO.getUserName(user.getManager());
                if (manageName != null) {
                    user.setManageName(manageName.getLoginName());
                }
            }
            // 最后一条代理轨迹
            if (StringUtils.isNotBlank(user.getLastAgentLog())) {
                AgencyLog log = agencyLogBO
                    .getAgencyLog(user.getLastAgentLog());
                if (StringUtils.isNotBlank(log.getToUserId())) {
                    User toUser = userBO.getUser(log.getToUserId());
                    user.setToUserName(toUser.getRealName());
                    user.setToUserMobile(toUser.getMobile());
                }
            }

            // 门槛余额
            Long mkAmount = 0L;
            Account account = accountBO.getAccountNocheck(user.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (null != account) {
                mkAmount = account.getAmount();
            }
            user.setMkAmount(mkAmount);
            // 云仓余额
            List<WareHouse> whList = wareHouseBO
                .getWareHouseByUser(user.getUserId());
            Long whAmount = 0L;
            for (WareHouse wareHouse : whList) {
                whAmount = whAmount + wareHouse.getAmount();
            }
            user.setWhAmount(whAmount);
        }
        return page;
    }

    @Override
    public List<User> queryUserList(User condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<User> list = userBO.queryUserList(condition);
        for (User user : list) {
            // 推荐人、上级、介绍人转义
            if (StringUtils.isNotBlank(user.getUserReferee())) {
                User userReferee = userBO.getUserName(user.getUserReferee());
                if (userReferee != null) {
                    user.setRefereeUser(userReferee);
                }
            }

            if (StringUtils.isNotBlank(user.getHighUserId())) {
                User highUser = userBO.getUserName(user.getHighUserId());
                if (highUser != null) {
                    user.setHighUser(highUser);
                }
            }
            if (StringUtils.isNotBlank(user.getIntroducer())) {
                User introduceName = userBO.getUserName(user.getIntroducer());
                if (introduceName != null) {
                    user.setIntroduceName(introduceName.getRealName());
                }
            }
            // 关联管理员
            if (StringUtils.isNotBlank(user.getManager())) {
                User manageName = userBO.getUserName(user.getManager());
                if (manageName != null) {
                    user.setManageName(manageName.getLoginName());
                }
            }
        }
        return list;
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
        if (EUserKind.Merchant.getCode().equals(user.getKind())) {
            // 推荐人、上级、介绍人转义
            if (StringUtils.isNotBlank(user.getUserReferee())) {
                User userReferee = userBO.getUserName(user.getUserReferee());
                if (userReferee != null) {
                    user.setRefereeUser(userReferee);
                }
            }
            // 上级
            if (StringUtils.isNotBlank(user.getHighUserId())) {
                User highUser = userBO.getUserName(user.getHighUserId());
                if (highUser != null) {
                    user.setHighUser(highUser);
                }
            }

            // 介绍人
            if (StringUtils.isNotBlank(user.getIntroducer())) {
                User introduceName = userBO.getUserName(user.getIntroducer());
                if (introduceName != null) {
                    user.setIntroduceName(introduceName.getRealName());
                }
            }

            // 关联管理员
            if (StringUtils.isNotBlank(user.getManager())) {
                User manageName = userBO.getUserName(user.getManager());
                if (manageName != null) {
                    user.setManageName(manageName.getLoginName());
                }
            }

            // 意向归属人
            if (StringUtils.isNotBlank(user.getLastAgentLog())) {
                AgencyLog log = agencyLogBO
                    .getAgencyLog(user.getLastAgentLog());
                if (StringUtils.isNotBlank(log.getToUserId())) {
                    User toUser = userBO.getUser(log.getToUserId());
                    user.setToTeamName(toUser.getTeamName());
                    user.setToLevel(toUser.getLevel());
                    user.setToUserName(toUser.getRealName());
                    user.setToUserMobile(toUser.getToUserMobile());
                }

            } else if (StringUtils.isNotBlank(user.getUserReferee())) {
                // 有推荐人的时候，同步推荐人的团队等信息
                User userReferee = userBO.getUser(user.getUserReferee());
                user.setToTeamName(userReferee.getTeamName());
                user.setToLevel(userReferee.getLevel());
                user.setToUserName(userReferee.getRealName());
                user.setToUserMobile(userReferee.getToUserMobile());
            }

            // 授权金额
            if (null != user.getApplyLevel()) {
                Agent agent = agentBO.getAgentByLevel(user.getApplyLevel());
                user.setImpowerAmount(agent.getAmount());
            }
            if (null != user.getPayAmount() && 0 != user.getPayAmount()) {
                user.setResult(false);
            }
            // 该等级授权规则
            if (EUserKind.Merchant.getCode().equals(user.getKind())) {
                if (null != user.getApplyLevel()) {
                    AgentImpower impower = agentImpowerBO
                        .getAgentImpowerByLevel(user.getApplyLevel());
                    user.setImpower(impower);
                }
            }

            // 门槛余额
            Long mkAmount = 0L;
            Account account = accountBO.getAccountNocheck(user.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (null != account) {
                mkAmount = account.getAmount();
            }
            user.setMkAmount(mkAmount);
            // 云仓余额
            List<WareHouse> whList = wareHouseBO
                .getWareHouseByUser(user.getUserId());
            Long whAmount = 0L;
            for (WareHouse wareHouse : whList) {
                whAmount = whAmount + wareHouse.getAmount();
            }
            user.setWhAmount(whAmount);
        }
        return user;
    }

    @Override
    public void doUpLevel(String userId, String level) {
        User data = new User();
        data.setUserId(userId);
        data.setLevel(StringValidater.toInteger(level));
        userBO.refreshLevel(data);
    }

    @Override
    @Transactional
    public void applyIntent(XN627250Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        userBO.isMobileExist(req.getMobile(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());

        AgentImpower aiData = agentImpowerBO.getAgentImpowerByLevel(
            StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.NO.getCode().equals(aiData.getIsIntent())) {
            throw new BizException("xn00000", "本等级不可被意向");
        }

        User data = userBO.getCheckUser(req.getUserId());

        data.setRealName(req.getRealName());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setStatus(EUserStatus.MIND.getCode());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        data.setApplyDatetime(new Date());

        userBO.applyIntent(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
    }

    @Override
    @Transactional
    public XN627302Res applyHaveUserReferee(XN627251Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        String introducer = req.getIntroducer();
        // 校验介绍人
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            User user = userBO.getUserByMobile(req.getIntroducer());
            introducer = user.getUserId();
            if (user.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于推荐人哦！");
            }
        }
        // 校验手机号
        userBO.isMobileExist(req.getMobile(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());
        // 校验身份证
        if (StringUtils.isNotBlank(req.getIdNo())) {
            userBO.getUserByIdNo(req.getIdNo());
        }

        XN627302Res result = null;
        // 是否可被意向
        AgentImpower impower = agentImpowerBO.getAgentImpowerByLevel(
            StringValidater.toInteger(req.getApplyLevel()));

        if (EBoolean.NO.getCode().equals(impower.getIsIntent())) {
            throw new BizException("xn0000", "本等级不可被意向");
        }
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            if (StringUtils.isBlank(req.getIdNo())
                    || StringUtils.isBlank(req.getIdHand())) {
                throw new BizException("xn0000", "本等级需要实名认证，请完成实名认证");
            }
        }

        User data = userBO.getUser(req.getUserId());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        String status = EUserStatus.TO_APPROVE.getCode();

        if (StringUtils.isNotBlank(data.getUserReferee())) {
            User userReferee = userBO.getUser(data.getUserReferee());
            data.setTeamName(userReferee.getTeamName());
            if (data.getApplyLevel() < userReferee.getLevel()) {
                throw new BizException("xn0000", "申请等级不能高于推荐代理的等级");
            }
            if (data.getApplyLevel() == userReferee.getLevel()) {
                status = EUserStatus.TO_COMPANYAPPROVE.getCode();
                // 防止团队名称重复
                userBO.checkTeamName(req.getTeamName());
            }

        }

        data.setRealName(req.getRealName());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setTeamName(req.getTeamName());
        data.setIdKind(req.getIdKind());
        data.setIdNo(req.getIdNo());
        data.setIdHand(req.getIdHand());

        data.setIntroducer(introducer);
        data.setStatus(status);
        data.setArea(req.getArea());
        data.setPayPdf(req.getPayPdf());

        data.setAddress(req.getAddress());
        data.setSource(req.getFromInfo());

        String logCode = agencyLogBO.toApply(data, req.getPayPdf(),
            EUserStatus.TO_APPROVE.getCode());
        data.setLastAgentLog(logCode);

        userBO.toApply(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
        result = new XN627302Res(data.getUserId(), EBoolean.NO.getCode());
        return result;

    }

    private Map<String, Object> getUserInfo(String code) {
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
        Map<String, Object> res = new HashMap<>();
        Properties fromProperties = new Properties();
        fromProperties.put("grant_type", "authorization_code");
        fromProperties.put("appid", appId);
        fromProperties.put("secret", appSecret);
        fromProperties.put("code", code);
        logger.info(
            "appId:" + appId + ",appSecret:" + appSecret + ",js_code:" + code);
        Map<String, Object> wxRes = new HashMap<>();
        try {
            String response = PostSimulater
                .requestPostForm(WechatConstant.WX_TOKEN_URL, fromProperties);
            logger.info(response);
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

    @Override
    public void allotAgency(String userId, String toUserId, String manager,
            String approver) {
        User data = userBO.getUser(userId);
        String status = EUserStatus.TO_APPROVE.getCode();
        if (!EUserKind.Merchant.getCode().equals(data.getKind())) {
            throw new BizException("xn0000", "该用户不是代理，无法分配");
        }
        if (StringUtils.isNotBlank(toUserId)) {
            User toUser = userBO.getUser(toUserId);
            if (data.getApplyLevel() <= toUser.getLevel()) {
                throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
            }
            status = EUserStatus.ALLOTED.getCode();
        } else {
            status = EUserStatus.ADD_INFO.getCode();
        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setManager(manager);
        data.setStatus(status);

        String logCode = agencyLogBO.saveAgencyLog(data, toUserId);
        data.setLastAgentLog(logCode);
        userBO.allotAgency(data);
    }

    @Override
    public void acceptIntention(String userId, String approver, String remark) {
        User data = userBO.getUser(userId);
        if (!(EUserStatus.MIND.getCode().equals(data.getStatus())
                || EUserStatus.ALLOTED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }

        AgencyLog log = agencyLogBO.getAgencyLog(data.getLastAgentLog());
        data.setHighUserId(log.getToUserId());
        data.setApprover(approver);
        data.setApplyDatetime(new Date());
        data.setRemark(remark);

        data.setStatus(EUserStatus.ADD_INFO.getCode());
        String logCode = agencyLogBO.acceptIntention(data);
        data.setLastAgentLog(logCode);
        userBO.acceptIntention(data);
    }

    @Override
    public void ignore(String userId, String aprrover) {
        User data = userBO.getUser(userId);
        if (!(EUserStatus.MIND.getCode().equals(data.getStatus())
                || EUserStatus.ALLOTED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }

        data.setApprover(aprrover);
        data.setApproveDatetime(new Date());
        data.setStatus(EUserStatus.TO_MIND.getCode());
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
        data.setTeamName(req.getTeamName());
        userBO.updateInformation(data);
    }

    @Override
    public void cancelImpower(String userId) {
        User data = userBO.getUser(userId);

        // 是否有下级
        User uCondition = new User();
        uCondition.setHighUserId(data.getUserId());
        List<User> list = userBO.queryUserList(uCondition);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn000", "您还有下级，无法申请退出");
        }

        // 可提现账户是否余额
        Account txAccount = accountBO.getAccountByUser(data.getUserId(),
            ECurrency.YJ_CNY.getCode());
        if (txAccount.getAmount() > 0) {
            throw new BizException("xn000", "您的可提现账户中还有余额，请取出后再申请退出");
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

        String status = EUserStatus.TO_COMPANYCANCEL.getCode();
        if (StringUtils.isNotBlank(data.getHighUserId())) {
            User user = userBO.getUser(data.getHighUserId());
            if (!EUserKind.Plat.getCode().equals(user.getKind())) {
                status = EUserStatus.TO_CANCEL.getCode();
            }
        }
        data.setStatus(status);
        String logCode = agencyLogBO.cancelImpower(data);
        data.setLastAgentLog(logCode);

        userBO.cancelImpower(data);
    }

    @Override
    @Transactional
    public boolean approveImpower(String userId, String approver, String result,
            String remark) {
        User data = userBO.getUser(userId);
        AgencyLog log = agencyLogBO.getAgencyLog(data.getLastAgentLog());
        if (!(EUserStatus.TO_APPROVE.getCode().equals(data.getStatus())
                || EUserStatus.TO_COMPANYAPPROVE.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        String status = EUserStatus.IMPOWERED.getCode();
        String fromUser = ESysUser.SYS_USER_BH.getCode();
        User highUser = userBO.getSysUser();

        if (EResult.Result_YES.getCode().equals(result)) {
            AgentImpower impower = agentImpowerBO
                .getAgentImpowerByLevel(data.getApplyLevel());

            if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
                if (StringUtils.isBlank(data.getIdNo())
                        || StringUtils.isBlank(data.getIdHand())) {
                    throw new BizException("xn0000", "本等级需要实名认证，该代理还未完成实名认证");
                }
            }
            // 需要公司授权
            if (EBoolean.YES.getCode().equals(impower.getIsCompanyImpower())
                    && !EUser.ADMIN.getCode().equals(approver)) {
                status = EUserStatus.TO_COMPANYAPPROVE.getCode();
            } else {
                data.setLevel(data.getApplyLevel());
                data.setImpowerDatetime(new Date());

                // 申请的等级是否高于意向归属人的等级
                if (StringUtils.isNotBlank(log.getToUserId())) {
                    User toUser = userBO.getUser(log.getToUserId());
                    highUser = toUser;
                    fromUser = highUser.getUserId();
                }

                // 根据用户类型获取账户列表
                List<String> currencyList = distributeAccount(data.getUserId(),
                    data.getRealName(), EUserKind.Merchant.getCode(),
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                // 分配账户
                accountBO.distributeAccount(data.getUserId(),
                    data.getRealName(), EAccountType.Business, currencyList,
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());

                // 介绍奖
                long amount = 0L;
                if (StringUtils.isNotBlank(data.getIntroducer())) {
                    User user = userBO.getUser(data.getIntroducer());
                    Intro iData = introBO.getIntroByLevel(user.getLevel(),
                        data.getApplyLevel());
                    amount = AmountUtil.mul(impower.getMinCharge(),
                        iData.getPercent());

                    accountBO.transAmountCZB(fromUser,
                        ECurrency.YJ_CNY.getCode(), user.getUserId(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_JSJL,
                        "介绍代理[" + data.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getCode() + "支出",
                        "介绍代理[" + data.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getValue() + "收入",
                        data.getUserId());
                }

                // 统计
                // reportBO.saveReport(data);
            }
            // 未通过，有推荐人
        } else if (StringUtils.isNotBlank(data.getUserReferee())) {
            status = EUserStatus.IMPOWERO_INFO.getCode();
        } else {
            status = EUserStatus.TO_MIND.getCode();
        }

        Date date = new Date();
        if (EUserStatus.IMPOWERED.getCode().equals(status)) {
            data.setImpowerDatetime(date);
        }

        data.setApprover(approver);
        data.setApplyDatetime(date);

        data.setRemark(remark);
        data.setStatus(status);
        String logCode = agencyLogBO.approveImpower(log, data);
        data.setLastAgentLog(logCode);
        userBO.approveImpower(data);
        return true;

    }

    @Override
    public void approveCanenl(String userId, String approver, String result,
            String remark) {
        User data = userBO.getUser(userId);
        if (!(EUserStatus.TO_CANCEL.getCode().equals(data.getStatus())
                || EUserStatus.TO_COMPANYCANCEL.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn000", "该代理未处于申请取消状态");
        }

        String status = EUserStatus.IMPOWERED.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            status = EUserStatus.CANCELED.getCode();
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            // 账户清零
            accountBO.changeAmount(account.getAccountNumber(), EChannelType.NBZ,
                null, null, data.getUserId(), EBizType.AJ_QXSQ,
                EBizType.AJ_QXSQ.getValue(), -account.getAmount());

        }
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);
        String logCode = agencyLogBO.approveCanenl(data, status);
        data.setLastAgentLog(logCode);
        userBO.approveCanenl(data);
    }

    @Override
    @Transactional
    public void editHighUser(String userId, String highUserId, String updater) {
        User data = userBO.getUser(userId);
        User newHighUser = userBO.getUser(highUserId);

        // if (!(EUserStatus.TO_UPGRADE.getCode().equals(data.getStatus())
        // || EUserStatus.TO_COMPANYUPGRADE.getCode()
        // .equals(data.getStatus()))) {
        // throw new BizException("xn0000", "该代理未申请升级");
        // }
        if (!EUserKind.Plat.getCode().equals(newHighUser.getKind())) {
            if (data.getLevel() <= newHighUser.getLevel()) {
                throw new BizException("xn0000", "不能大于上级等级");
            }
        }
        if (data.getHighUserId().equals(highUserId)) {
            throw new BizException("xn0000", "新上级不能与旧上级相同");
        }

        Account account = accountBO.getAccountByUser(userId,
            ECurrency.MK_CNY.getCode());
        // 旧上级是空（平台）,新上级是代理，直接增加新上级的门槛
        if (StringUtils.isBlank(data.getHighUserId())
                && EUserKind.Merchant.getCode().equals(newHighUser.getKind())) {
            Account newHighAccount = accountBO.getAccountByUser(
                newHighUser.getUserId(), ECurrency.MK_CNY.getCode());
            accountBO.changeAmount(newHighAccount.getAccountNumber(),
                EChannelType.NBZ, null, null, data.getUserId(),
                EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                account.getAmount());
        }

        // 旧上级非空（平台）
        if (StringUtils.isNotBlank(data.getHighUserId())) {
            User oldHighUser = userBO.getUser(data.getHighUserId());
            // 旧上级是平台
            if (EUserKind.Plat.getCode().equals(newHighUser.getKind())) {
                Account newHighAccount = accountBO.getAccountByUser(
                    newHighUser.getUserId(), ECurrency.MK_CNY.getCode());
                accountBO.changeAmount(newHighAccount.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    account.getAmount());

                // 旧上级不是平台，新上级是平台，直接减少旧上级门槛
            } else if (EUserKind.Merchant.getCode()
                .equals(oldHighUser.getKind())
                    && EUserKind.Plat.getCode().equals(oldHighUser.getKind())) {
                Account oldHighAccount = accountBO.getAccountByUser(
                    oldHighUser.getUserId(), ECurrency.MK_CNY.getCode());
                accountBO.changeAmount(oldHighAccount.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    -account.getAmount());

                // 新旧上级不是平台，旧上级门槛转入新上级门槛
            } else if (EUserKind.Merchant.getCode()
                .equals(oldHighUser.getKind())
                    && EUserKind.Merchant.getCode()
                        .equals(newHighUser.getKind())) {
                accountBO.transAmountCZB(oldHighUser.getUserId(),
                    ECurrency.MK_CNY.getCode(), newHighUser.getUserId(),
                    ECurrency.MK_CNY.getCode(), account.getAmount(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    EBizType.AJ_XGSH.getValue(), data.getUserId());
            }
        }

        data.setHighUserId(newHighUser.getUserId());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        String logCode = agencyLogBO.refreshHighUser(data);
        data.setLastAgentLog(logCode);
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
    public void upgradeLevel(String userId, String highLevel, String payPdf,
            String payAmount) {
        User data = userBO.getUser(userId);
        if (!(EUserStatus.IMPOWERED.getCode().equals(data.getStatus())
                || EUserStatus.UPGRADED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn000", "您的状态无法申请升级");
        }

        if (data.getLevel() <= StringValidater.toInteger(highLevel)) {
            throw new BizException("xn0000", "升级等级要大于当前等级");
        }
        if (StringValidater.toInteger(EUserLevel.ONE.getCode()) == data
            .getLevel()) {
            throw new BizException("xn0000", "您的等级已经为最高等级，无法继续升级");
        }

        AgentUpgrade upgrade = agentUpgradeBO
            .getAgentUpgradeByLevel(StringValidater.toInteger(highLevel));
        Agent agent = agentBO
            .getAgentByLevel(StringValidater.toInteger(highLevel));

        // 推荐人数是否满足半门槛
        List<User> userReferee = userBO.getUsersByUserReferee(data.getUserId());
        if (upgrade.getReNumber() >= userReferee.size()) {
            if (StringValidater.toLong(payAmount) <= agent
                .getMinChargeAmount()) {
                throw new BizException("xn00000", "您的直推人数不满足半门槛人数，打款金额不能低于"
                        + StringValidater.toLong(payAmount) / 1000);
            }
        }

        AgentUpgrade auData = agentUpgradeBO
            .getAgentUpgradeByLevel(data.getLevel());
        // 余额是否清零
        if (EBoolean.YES.getCode().equals(auData.getIsReset())) {
            // 云仓是否有余额
            List<WareHouse> list = wareHouseBO
                .getWareHouseByUser(data.getUserId());
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BizException("xn00000", "本等级升级云仓中不允许有余额");
            }

        }
        String status = EUserStatus.TO_COMPANYUPGRADE.getCode();
        if (StringUtils.isNotBlank(data.getHighUserId())) {
            User highUser = userBO.getUser(data.getHighUserId());
            if (EUserKind.Merchant.getCode().equals(highUser.getKind())) {
                status = EUserStatus.TO_UPGRADE.getCode();
            }

        }

        data.setApplyLevel(StringValidater.toInteger(highLevel));
        data.setStatus(status);
        data.setApplyDatetime(new Date());
        data.setPayAmount(StringValidater.toLong(payAmount));

        String logCode = agencyLogBO.upgradeLevel(data, payPdf);
        data.setLastAgentLog(logCode);
        userBO.upgradeLevel(data);
    }

    @Override
    @Transactional
    public void approveUpgrade(String userId, String approver, String remark,
            String result) {
        User data = userBO.getUser(userId);
        if (!(EUserStatus.TO_COMPANYUPGRADE.getCode().equals(data.getStatus())
                || EUserStatus.TO_UPGRADE.getCode().equals(data.getStatus()))) {
            throw new BizException("xn00000", "代理未申请升级");
        }
        String status = EUserStatus.IMPOWERED.getCode();
        Integer level = data.getLevel();
        if (EBoolean.YES.getCode().equals(result)) {
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            status = EUserStatus.UPGRADED.getCode();
            AgentUpgrade auData = agentUpgradeBO
                .getAgentUpgradeByLevel(data.getApplyLevel());

            // 是否推荐的代理 TODO
            if (EBoolean.YES.getCode().equals(auData.getIsCompanyApprove())) {
                if (!EUser.ADMIN.getCode().equals(approver)) {
                    User approveUser = userBO.getUser(approver);
                    if (!EUserKind.Plat.getCode()
                        .equals(approveUser.getKind())) {
                        status = EUserStatus.TO_COMPANYUPGRADE.getCode();
                    }

                } else {
                    level = data.getApplyLevel();
                    // 增加账户余额
                    accountBO.changeAmount(account.getAccountNumber(),
                        EChannelType.NBZ, null, null, data.getUserId(),
                        EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                        data.getPayAmount());

                    // 推荐人的上级变成自己
                    changeHighUser(data.getUserId(), data.getUserId(), approver,
                        remark);
                    // 推荐人的上级的门槛转给自己
                    changeAmount(data);
                }
            } else {
                level = data.getApplyLevel();
                // 增加账户余额
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                    data.getPayAmount());

                // 推荐人的上级变成自己
                changeHighUser(data.getUserId(), data.getUserId(), approver,
                    remark);
                // 推荐人的上级的门槛转给自己
                changeAmount(data);

            }
        }

        data.setLevel(level);
        data.setStatus(status);
        data.setPayAmount(0L);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        String logCode = agencyLogBO.approveUpgrade(data, status);
        data.setLastAgentLog(logCode);
        userBO.approveUpgrade(data);
    }

    @Override
    public Paginable<User> queryLowUser(int start, int limit, User condition) {
        return userBO.getPaginable(start, limit, condition);
    }

    @Override
    public Paginable<User> queryMyLowUserPage(int start, int limit,
            User condition) {
        long totalCount = userBO.getTotalCount(condition);
        Page<User> page = new Page<User>(start, limit, totalCount);
        List<User> list = userBO.selectList(condition, page.getPageNo(),
            page.getPageSize());
        page.setList(list);
        return page;
    }

    public List<User> getAllLowUser(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            User condition = new User();
            condition.setHighUserId(user.getUserId());
            List<User> userList = userBO.queryUserList(condition);
            if (CollectionUtils.isNotEmpty(userList)) {
                user.setUserList(userList);
                getAllLowUser(userList);
            }
        }
        return list;
    }

    @Override
    public List<User> queryAgentPage(User condition) {
        if (StringUtils.isBlank(condition.getHighUserId())) {
            condition.setLevel(1);
        }
        List<User> list = userBO.queryUserList(condition);
        if (StringUtils.isNotBlank(condition.getHighUserId())) {
            list = getAllLowUser(list);
        }
        return list;
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

        for (User data : list) {
            if (StringUtils.isNotBlank(data.getHighUserId())) {
                User highUser = userBO.getUser(data.getHighUserId());
                data.setHighUser(highUser);
            }
        }
        page.setList(list);
        return page;
    }

    @Override
    public Paginable<User> queryIntentionAgentPage(int start, int limit,
            User condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        User highUser = null;
        for (User data : page.getList()) {
            if (StringUtils.isNotBlank(data.getHighUserId())) {
                highUser = userBO.getUser(data.getHighUserId());
                data.setHighUser(highUser);
            }
        }

        return page;
    }

    @Override
    public User getUserLog(int start, int limit, User condition) {
        User data = userBO.getUser(condition.getUserId());
        AgencyLog alCondition = new AgencyLog();
        alCondition.setApplyUser(data.getUserId());
        List<AgencyLog> list = agencyLogBO.queryAgencyLogPage(start, limit,
            alCondition);
        for (AgencyLog agencyLog : list) {
            User userReferee = null;
            User user = userBO.getUser(agencyLog.getApplyUser());
            if (user != null) {
                agencyLog.setUser(user);
                if (StringUtils.isNotBlank(user.getUserReferee())) {
                    userReferee = userBO.getUser(user.getUserReferee());
                }
                if (userReferee != null) {
                    agencyLog.setRefereeName(userReferee.getRealName());
                }
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprovName(agencyLog.getApprover());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    User aprrvoeName = userBO.getUser(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = userBO.getUser(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setApprovName(userReferee.getRealName());
                        }
                    }
                }
            }

        }
        data.setLogList(list);
        return data;
    }

    @Override
    public User getUserName(String userReferee) {
        return userBO.getUserName(userReferee);
    }

    @Override
    public String addUser(String mobile, String loginPwd, String realName) {
        userBO.isMobileExist(mobile, ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());
        User data = new User();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setMobile(mobile);

        data.setLoginName(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setStatus(EUserStatus.NORMAL.getCode());
        data.setKind(EUserKind.Plat.getCode());

        data.setRealName(realName);
        data.setSystemCode(ESystemCode.BH.getCode());
        data.setCompanyCode(ESystemCode.BH.getCode());
        userBO.doSaveUser(data);
        return userId;
    }

    @Override
    public void addInfo(XN627362Req req) {
        User data = userBO.getUser(req.getUserId());
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            User user = userBO.getUserByMobile(req.getIntroducer());
            if (user.getUserId().equals(req.getUserId())) {
                throw new BizException("xn0000", "推荐人不能填自己哦！");
            }
            if (!EUserKind.Merchant.getCode().equals(user.getKind())) {
                throw new BizException("xn0000", "您填写的推荐人不是我们的代理哦！");
            }

            if (user.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于介绍人哦！");
            }
        }

        userBO.checkTeamName(req.getTeamName());

        // 校验身份证
        if (StringUtils.isNotBlank(req.getIdNo())) {
            userBO.getUserByIdNo(req.getIdNo());
        }

        AgentImpower impower = agentImpowerBO
            .getAgentImpowerByLevel(data.getApplyLevel());
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            if (StringUtils.isBlank(req.getIdNo())
                    || StringUtils.isBlank(req.getIdHand())) {
                throw new BizException("xn0000", "本等级需要实名认证，请完成实名认证");
            } else {
                userBO.getUserByIdNo(req.getIdNo());
            }
        }

        // data.setRealName(req.getRealName());
        // data.setWxId(req.getWxId());
        // data.setMobile(req.getMobile());
        // data.setProvince(req.getProvince());
        // data.setCity(req.getCity());
        //
        // data.setArea(req.getArea());
        // data.setAddress(req.getAddress());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        data.setTeamName(req.getTeamName());

        data.setIdKind(req.getIdKind());
        data.setIdNo(req.getIdNo());
        data.setIdHand(req.getIdHand());
        data.setIntroducer(req.getIntroducer());
        data.setStatus(EUserStatus.TO_COMPANYAPPROVE.getCode());

        userBO.addInfo(data);
    }

    @Override
    public boolean isRealName(String userId) {
        User user = userBO.getUser(userId);
        if (null != user.getApplyLevel()) {
            AgentImpower impower = agentImpowerBO
                .getAgentImpowerByLevel(user.getApplyLevel());
            if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
                return true;
            }

        }
        return false;
    }

    private Long getAmount(String userId) {
        Long amount = 0L;
        User user = userBO.getUser(userId);
        if (StringUtils.isNotBlank(user.getUserReferee())) {
            Account account = accountBO.getAccountByUser(userId,
                ECurrency.MK_CNY.getCode());
            amount = amount + account.getAmount();
        }
        return amount;

    }

    private void changeAmount(User data) {
        User highUser = userBO.getUser(data.getUserId());
        // 推荐人的上级门槛转入新上级
        List<User> list = userBO.getUsersByUserReferee(data.getUserId());
        User oldHighUser = null;
        for (User user : list) {
            // 被推荐代理门槛款
            Account refreeAccount = accountBO.getAccountByUser(user.getUserId(),
                ECurrency.MK_CNY.getCode());
            Account account = accountBO.getAccountByUser(highUser.getUserId(),
                ECurrency.MK_CNY.getCode());
            oldHighUser = userBO.getCheckUser(user.getUserId());

            // 旧代理上级是代理
            if (null == oldHighUser
                    || EUserKind.Plat.getCode().equals(oldHighUser.getKind())) {
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    refreeAccount.getAmount());
            }
            if (EUserKind.Merchant.getCode().equals(oldHighUser.getKind())) {
                String oldHighUserId = oldHighUser.getUserId();
                accountBO.transAmountCZB(oldHighUserId,
                    ECurrency.MK_CNY.getCode(), highUser.getUserId(),
                    ECurrency.MK_CNY.getCode(), refreeAccount.getAmount(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    EBizType.AJ_XGSH.getValue(), data.getUserId());
            }

            changeAmount(user);

        }
    }

    @Override
    public void addHighAccount(User user, Long amount) {
        if (StringUtils.isNotBlank(user.getHighUserId())) {
            User highUser = userBO.getUser(user.getHighUserId());
            if (EUserKind.Merchant.getCode().equals(highUser.getKind())) {
                Account account = accountBO.getAccountByUser(
                    highUser.getUserId(), ECurrency.MK_CNY.getCode());
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.WeChat_H5, null, null, user.getUserId(),
                    EBizType.AJ_XJCZ, EBizType.AJ_XJCZ.getValue(), amount);
                if (StringUtils.isNotBlank(highUser.getHighUserId())) {
                    addHighAccount(highUser, amount);
                }
            }
        }
    }

    private void changeHighUser(String highUserId, String userId,
            String approver, String remark) {

        List<User> list = userBO.getUsersByUserReferee(userId);
        for (User user : list) {
            Date date = new Date();
            user.setHighUserId(userId);
            user.setUpdater(approver);
            user.setUpdateDatetime(date);

            user.setRemark(remark);
            String logCode = agencyLogBO.refreshHighUser(user);
            user.setLastAgentLog(logCode);
            userBO.refreshHigh(user);

            List<User> list2 = userBO.getUsersByUserReferee(user.getUserId());
            for (User user2 : list2) {
                changeHighUser(highUserId, user2.getUserId(), approver, remark);
            }

        }

    }

    @Override
    public User doGetUserByMobile(String mobile) {
        return userBO.getUserByMobile(mobile);
    }

}
