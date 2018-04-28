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
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IIntroBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IUserBO;
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
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.res.XN627262Res;
import com.bh.mall.dto.res.XN627263Res;
import com.bh.mall.dto.res.XN627302Res;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EAddressType;
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

    @Autowired
    private IIntroBO introBO;

    @Autowired
    private IAgentBO agentBO;

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
    public XN627302Res doLoginWeChatByMerchant(String code, String userKind) {

        return doLoginWeChat(code, userKind);
    }

    @Override
    public XN627302Res doLoginWeChatByCustomer(String code, String userKind) {
        return doLoginWeChat(code, userKind);
    }

    @Transactional
    private XN627302Res doLoginWeChat(String code, String userKind) {
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
        fromProperties.put("code", code);// 微信H5
        // fromProperties.put("js_code", code);// 微信小程序
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
                result = new XN627302Res(dbUser.getUserId(),
                    dbUser.getStatus());
            } else {
                String nickname = (String) wxRes.get("nickname");
                String photo = (String) wxRes.get("headimgurl");
                // Step5：判断注册是否传手机号，有则注册，无则反馈
                // if (EBoolean.YES.getCode().equals(isNeedMobile)) {
                //
                // doWxLoginRegMobile(mobile, isLoginStatus, smsCaptcha,
                // smsCaptchaNumber, companyCode, systemCode, unionId,
                // null, h5OpenId, nickname, photo, userKind);
                //
                // } else {
                //
                // }

                result = doWxLoginReg(companyCode, systemCode, unionId, null,
                    h5OpenId, nickname, photo, userKind);
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
            String photo, String userKind) {
        XN627302Res result = new XN627302Res();
        userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
            systemCode);
        String status = null;
        // 插入用户信息
        if (EUserKind.Customer.getCode().equals(userKind)) {
            status = EUserStatus.NORMAL.getCode();
            result.setStatus(status);
        } else {
            status = EUserStatus.TO_WILL.getCode();
            result.setStatus(status);
        }
        String userId = userBO.doRegister(unionId, h5OpenId, appOpenId, null,
            userKind, EUserPwd.InitPwd.getCode(), nickname, photo, status,
            companyCode, systemCode);
        // distributeAccount(userId, nickname, userKind, companyCode,
        // systemCode);
        result.setUserId(userId);

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
            currencyList.add(ECurrency.YC_CNY.getCode());
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
        // 根据手机号和类型判断手机号是否存在
        userBO.isMobileExist(newMobile, kind, user.getCompanyCode(),
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
                    + "提交的更改绑定手机号码服务已审核通过，现绑定手机号码为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "805061", user.getCompanyCode(), user.getSystemCode());

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
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        } else {
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
            // 授权金额
            if (null != user.getApplyLevel()) {
                Agent agent = agentBO.getAgentByLevel(user.getApplyLevel());
                user.setImpowerAmount(agent.getAmount());
            }
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
    public void applyIntent(XN627250Req req) {

        AgentImpower aiData = agentImpowerBO
            .getAgentImpowerByLevel(StringValidater.toInteger(req.getLevel()));
        if (EBoolean.NO.getCode().equals(aiData.getIsIntent())) {
            throw new BizException("xn0000", "本等级不可被意向");
        }
        User data = userBO.getCheckUser(req.getUserId());
        if (EUserStatus.TO_WILL.getCode().equals(data.getStatus())) {

        }

        data.setRealName(req.getRealName());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setStatus(EUserStatus.TO_WILL.getCode());
        data.setApplyLevel(StringValidater.toInteger(req.getLevel()));
        data.setApplyDatetime(new Date());
        userBO.applyIntent(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());

        // XN627302Res result = null;
        // Map<String, String> wxRes = getUserInfo(req.getCode());
        // String unionId = (String) wxRes.get("unionid");
        // String h5OpenId = (String) wxRes.get("openid");
        // // Step4：根据openId，unionId从数据库中查询用户信息
        // User dbUser = userBO.doGetUserByOpenId(h5OpenId,
        // ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
        // if (null != dbUser) {// 如果user存在
        // throw new BizException("xn0000", "您已经申请过代理");
        // } else {
        // String nickname = (String) wxRes.get("nickname");
        // String photo = (String) wxRes.get("headimgurl");
        // // 是否可被意向
        //
        //
        // result = doWxLoginReg(req, ESystemCode.BH.getCode(),
        // ESystemCode.BH.getCode(), unionId, null, h5OpenId, nickname,
        // photo);
        // }
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

            // distributeAccount(userId, nickname, EUserKind.Merchant.getCode(),
            // ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            addressBO.saveAddress(userId, EAddressType.User_Address.getCode(),
                req.getMobile(), req.getRealName(), req.getProvince(),
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
        User toUser = userBO.getUser(toUserId);
        if (data.getApplyLevel() <= toUser.getLevel()) {
            throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setManager(manager);

        String logCode = agencyLogBO.saveAgencyLog(data, toUserId,
            EUserStatus.ALLOTED.getCode());
        data.setStatus(EUserStatus.ALLOTED.getCode());
        data.setLastAgentLog(logCode);
        userBO.allotAgency(data);
    }

    @Override
    public void acceptIntention(String userId, String approver, String remark) {
        User data = userBO.getUser(userId);
        if (!EUserStatus.TO_WILL.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }

        if (EUser.ADMIN.getCode().equals(approver)) {
            User highUser = userBO.getSysUser();
            approver = highUser.getUserId();
        }

        data.setApprover(approver);
        data.setApplyDatetime(new Date());
        data.setRemark(remark);
        data.setStatus(EUserStatus.TO_APPROVE.getCode());
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
        data.setStatus(EUserStatus.IGNORED.getCode());
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
        data.setStatus(EUserStatus.TO_CANCEL.getCode());
        String logCode = agencyLogBO.cancelImpower(data);
        data.setLastAgentLog(logCode);
        userBO.cancelImpower(data);
    }

    @Override
    public void approveImpower(String userId, String approver, String result,
            String remark) {
        User data = userBO.getUser(userId);
        AgencyLog log = agencyLogBO.getAgencyLog(data.getLastAgentLog());
        if (!(EUserStatus.TO_APPROVE.getCode().equals(log.getStatus())
                || EUserStatus.TO_COMPANYAPPROVE.getCode()
                    .equals(log.getStatus()))) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        String status = EUserStatus.IMPOWERED.getCode();
        String manager = null;
        AgencyLog alData = agencyLogBO.getAgencyLog(data.getLastAgentLog());
        String highUserId = alData.getToUserId();

        if (EResult.Result_YES.getCode().equals(result)) {
            data.setHighUserId(approver);
            AgentImpower aiData = agentImpowerBO
                .getAgentImpowerByLevel(data.getApplyLevel());
            if (EBoolean.YES.getCode().equals(aiData.getIsRealName())) {
                if (StringUtils.isBlank(data.getRealName())) {
                    throw new BizException("xn000", "本等级授权需要实名，请实名后再授权");
                }
            }

            // 需要公司授权
            if (EBoolean.YES.getCode().equals(aiData.getIsCompanyImpower())
                    && !EUser.ADMIN.getCode().equals(approver)) {
                status = EUserStatus.TO_COMPANYAPPROVE.getCode();
            } else {
                status = EUserStatus.NORMAL.getCode();
                // 根据用户类型获取账户列表
                List<String> currencyList = distributeAccount(data.getUserId(),
                    data.getRealName(), EUserKind.Merchant.getCode(),
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                // 分配账户
                accountBO.distributeAccount(data.getUserId(),
                    data.getRealName(), EAccountType.Business, currencyList,
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                String fromUser = null;
                if (EUser.ADMIN.getCode().equals(approver)) {
                    fromUser = ESysUser.SYS_USER_BH.getCode();
                    highUserId = EUser.ADMIN.getCode();
                } else {
                    User approveUser = userBO.getUser(approver);
                    fromUser = approveUser.getUserId();
                }

                // 介绍奖
                if (StringUtils.isNotBlank(data.getIntroducer())) {
                    Intro iData = introBO.getIntroByLevel(data.getLevel());
                    long amount = AmountUtil.mul(aiData.getMinCharge(),
                        iData.getPercent());

                    if (data.getLevel() == StringValidater
                        .toInteger(EUserLevel.ONE.getCode())) {
                        fromUser = ESysUser.SYS_USER_BH.getCode();
                    }

                    accountBO.transAmountCZB(fromUser,
                        ECurrency.YJ_CNY.getCode(), data.getIntroducer(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_JSJL,
                        "介绍代理[" + data.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getCode() + "支出",
                        "介绍代理[" + data.getRealName() + data.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getCode() + "收入",
                        data.getUserId());
                }
            }

        } else {
            status = EUserStatus.NO_THROUGH.getCode();
        }
        data.setManager(manager);
        data.setApprover(approver);
        data.setApplyDatetime(new Date());
        data.setRemark(remark);
        data.setStatus(status);

        data.setLevel(data.getApplyLevel());
        data.setHighUserId(highUserId);
        String logCode = agencyLogBO.approveImpower(data, status);
        data.setLastAgentLog(logCode);
        userBO.approveImpower(data);

    }

    @Override
    public void approveCanenl(String userId, String approver, String result,
            String remark) {
        User data = userBO.getUser(userId);
        if (!EUserStatus.TO_CANCEL.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该代理未处于申请取消状态");
        }

        String status = EUserStatus.NO_THROUGH.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            status = EUserStatus.CANCELED.getCode();
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
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);
        String logCode = agencyLogBO.approveCanenl(data, status);
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
                || !EUserStatus.IMPOWERED.getCode().equals(data.getStatus())
                || !EUserStatus.UPGRADED.getCode().equals(data.getStatus())) {
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
        data.setStatus(EUserStatus.TO_UPGRADE.getCode());
        data.setApplyDatetime(new Date());
        String logCode = agencyLogBO.upgradeLevel(data, payPdf);
        data.setLastAgentLog(logCode);
        userBO.upgradeLevel(data);
        return res;
    }

    @Override
    public XN627263Res approveUpgrade(String userId, String approver,
            String remark, String result) {
        XN627263Res res = null;
        User data = userBO.getUser(userId);
        String status = EUserStatus.NO_THROUGH.getCode();
        String message = "您的升级申请未通过";
        if (EBoolean.YES.getCode().equals(result)) {
            status = EUserStatus.UPGRADED.getCode();
            AgentUpgrade auData = agentUpgradeBO
                .getAgentUpgradeByLevel(data.getLevel());

            if (EBoolean.YES.getCode().equals(auData.getIsCompanyApprove())
                    && EUser.ADMIN.getCode().equals(approver)) {
                status = EUserStatus.TO_COMPANYAPPROVE.getCode();
            } else {
                message = "升级申请已通过,不要忘记修改上级哦！";
                Account condition = new Account();
                condition.setUserId(data.getUserId());
                List<Account> list = accountBO.queryAccountList(condition);
                // 账户清零
                if (CollectionUtils.isNotEmpty(list)) {
                    for (Account account : list) {
                        if ((ECurrency.MK_CNY.getCode()
                            .equals(account.getCurrency())
                                && account.getAmount() > 0)) {
                            throw new BizException("xn000", "该代理的账户仍有余额");
                        }
                    }
                }
            }

        }
        res = new XN627263Res(message);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);
        String logCode = agencyLogBO.approveUpgrade(data, status);
        data.setLastAgentLog(logCode);
        userBO.approveUpgrade(data);
        return res;
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

        return userBO.getPaginable(start, limit, condition);
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
    public String addUser(String kind, String mobile, String loginPwd,
            String userReferee, String fromInfo) {
        userBO.isMobileExist(mobile, kind, ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());
        User data = new User();
        String userId = OrderNoGenerater.generate("U");
        String status = EUserStatus.NORMAL.getCode();
        if (EUserKind.Merchant.getCode().equals(kind)) {
            status = EUserStatus.TO_WILL.getCode();
        }
        data.setUserId(userId);
        data.setMobile(mobile);
        data.setLoginName(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));

        data.setUserReferee(userReferee);
        data.setSource(fromInfo);
        data.setStatus(status);
        userBO.doSaveUser(data);
        return userId;
    }

}
