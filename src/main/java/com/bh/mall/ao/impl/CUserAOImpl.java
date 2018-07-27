package com.bh.mall.ao.impl;

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

import com.bh.mall.ao.ICUserAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.ICUserBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IIntroBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IReportBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.domain.CUser;
import com.bh.mall.dto.res.XN627304Res;
import com.bh.mall.enums.EConfigType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserPwd;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.PostSimulater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class CUserAOImpl implements ICUserAO {

    private static Logger logger = Logger.getLogger(CUserAOImpl.class);

    @Autowired
    ICUserBO cuserBO;

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
    IAccountBO accountBO;

    @Autowired
    IOrderBO orderBO;

    @Autowired
    IInnerOrderBO innerOrderBO;

    @Autowired
    IAfterSaleBO afterSaleBO;


    @Autowired
    IIntroBO introBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IWareHouseBO wareHouseBO;

    @Autowired
    IReportBO reportBO;

    /***************** 登录 **********************/
    @Override
    public String doLogin(String loginName, String loginPwd) {
        CUser condition = new CUser();

        condition.setLoginName(loginName);
        // condition.setLoginType(ELoginType.MOBILE.getCode());
        condition.setLoginName(loginName);

        List<CUser> userList1 = cuserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<CUser> userList2 = cuserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        CUser user = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050", "该用户操作存在异常");
        }
        return user.getUserId();
    }

    @Override
    public XN627304Res doLoginWeChatByCustomer(String code, String nickname,
            String avatarUrl, String kind) {
        return doLoginWeChatM(code, nickname, avatarUrl, kind);
    }

    @Transactional
    private XN627304Res doLoginWeChatM(String code, String nickname,
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
        XN627304Res result = null;
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
            CUser dbUser = cuserBO.doGetUserByOpenId(openId);
            if (null != dbUser) {// 如果user存在，说明用户授权登录过，直接登录
                // result = new XN627302Res(dbUser.getUserId(),
                // dbUser.getStatus());
            } else {
                result = doWxLoginReg(openId, nickname, photo, kind, null,
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

    /************************ 注册 ******************/
    private XN627304Res doWxLoginReg(String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo, String status) {
        cuserBO.doCheckOpenId(unionId, h5OpenId, appOpenId);
        Integer level = 0;

        String userId = cuserBO.doRegister(unionId, h5OpenId, appOpenId, null,
            EUserPwd.InitPwd.getCode(), nickname, photo, null);
        XN627304Res result = new XN627304Res(userId, status);
        return result;
    }

    /******************** 登录检测 ********************/
    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        CUser user = cuserBO.getUser(userId);
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
        cuserBO.refreshStatus(userId, userStatus);
        if (PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile,
                "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "805091");
        }
    }

    /******************** 更新信息********************/
    // 设置交易密码
    @Override
    public void setTradePwd(String userId, String smsCaptcha, String tradePwd) {
        CUser user = cuserBO.getCheckUser(userId);
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "627306");
        if (user.getTradePwd().equals(MD5Util.md5(tradePwd))) {
            throw new BizException("xn0000", "新旧密码不能相同");
        }
        cuserBO.setTradePwd(user, tradePwd);
    }

    @Override
    public void doModifyPhoto(String userId, String photo) {
        cuserBO.refreshPhoto(userId, photo);
    }

    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        CUser condition = new CUser();
        condition.setUserId(userId);
        List<CUser> userList1 = cuserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<CUser> userList2 = cuserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String newMobile,
            String smsCaptcha) {
        CUser user = cuserBO.getCheckUser(userId);
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        cuserBO.isMobileExist(newMobile);
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "627287");
        cuserBO.resetBindMobile(user, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已将手机号码改为" + newMobile + "，您的登录名更改为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "631072");

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

}
