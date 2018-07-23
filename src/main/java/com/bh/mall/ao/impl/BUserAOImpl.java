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

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.IAgentAllotBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.IImpowerApplyBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IIntroBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IReportBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IUpLevelApplyBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentAllot;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.ImpowerApply;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.domain.UpLevelApply;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.req.XN627362Req;
import com.bh.mall.dto.res.XN627303Res;
import com.bh.mall.enums.EAddressType;
import com.bh.mall.enums.EAfterSaleStatus;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EConfigType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ELoginType;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserLevel;
import com.bh.mall.enums.EUserPwd;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.PostSimulater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class BUserAOImpl implements IBuserAO {

    private static Logger logger = Logger.getLogger(UserAOImpl.class);

    @Autowired
    IBuserBO buserBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IAgentAllotBO agentAllotBO;

    @Autowired
    IImpowerApplyBO impowerApplyBO;

    @Autowired
    IUpLevelApplyBO uplevelApplyBO;

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

    /*************** 微信注册**********************/
    // 微信注册
    private XN627303Res doWxLoginReg(String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo, String userKind,
            String highUser, String status) {
        buserBO.doCheckOpenId(unionId, h5OpenId, appOpenId);
        Integer level = 0;
        if (EUserKind.Customer.getCode().equals(userKind)) {
            level = 6;
        }
        String userId = buserBO.doRegister(unionId, h5OpenId, appOpenId, null,
            userKind, EUserPwd.InitPwd.getCode(), nickname, photo, status,
            level, highUser);
        XN627303Res result = new XN627303Res(userId, status);
        return result;
    }

    /*************** 登录**********************/
    // 用户名登录
    @Override
    public String doLogin(String loginName, String loginPwd, String kind) {
        BUser condition = new BUser();
        if (EUserKind.Customer.getCode().equals(kind)
                || EUserKind.Merchant.getCode().equals(kind)) {
            condition.setLoginName(loginName);
            condition.setLoginType(ELoginType.MOBILE.getCode());
        } else {
            condition.setLoginName(loginName);
        }
        condition.setKind(kind);
        List<BUser> userList1 = buserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<BUser> userList2 = buserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        BUser buser = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(buser.getStatus())) {
            throw new BizException("xn805050", "该用户操作存在异常");
        }
        return buser.getUserId();
    }

    // 微信登录
    @Override
    public XN627303Res doLoginWeChatByMerchant(String code, String userKind,
            String highUserId) {
        String status = EUserStatus.TO_MIND.getCode(); // 待申请意向代理
        if (StringUtils.isNotBlank(highUserId)) {
            status = EUserStatus.IMPOWERO_INFO.getCode(); // 待填写授权资料
        }
        return doLoginWeChatH(code, userKind, highUserId, status);
    }

    // doLoginWeChatH
    @Transactional
    private XN627303Res doLoginWeChatH(String code, String userKind,
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
        XN627303Res result = null;
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
            BUser dbUser = buserBO.doGetUserByOpenId(h5OpenId);

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
                result = new XN627303Res(dbUser.getUserId(), dbUser.getStatus(),
                    subscribe);
            } else {
                String nickname = (String) wxRes.get("nickname");
                String photo = (String) wxRes.get("headimgurl");

                result = doWxLoginReg(unionId, null, h5OpenId, nickname, photo,
                    userKind, highUserId, status);
                result = new XN627303Res(result.getUserId(), result.getStatus(),
                    subscribe);
            }
            // result.setSubscribe(subscribe);
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

    /*************** 分配账号**********************/
    // 分配账号
    private List<String> distributeAccount(String userId, String mobile,
            String kind) {
        List<String> currencyList = new ArrayList<String>();
        if (EUserKind.Customer.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
        } else if (EUserKind.Merchant.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
            currencyList.add(ECurrency.MK_CNY.getCode());
        }
        return currencyList;
    }

    /*************** 注销，激活**********************/
    // 注销 | 激活
    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        BUser buser = buserBO.getUser(userId);
        if (buser == null) {
            throw new BizException("li01004", "用户不存在");
        }

        String mobile = buser.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(buser.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = EUserStatus.NORMAL;
        }
        buserBO.refreshStatus(userId, userStatus, updater, remark);
        if (!EUserKind.Plat.getCode().equals(buser.getKind())
                && PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile,
                "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "805091");
        }
    }

    /*************** 设置角色**********************/
    // 设置角色
    @Override
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark) {
        BUser buser = buserBO.getUser(userId);
        if (buser == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        buserBO.refreshRole(userId, roleCode, updater, remark);
    }

    /*************** 检查**********************/
    // 检查登录密码
    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        BUser condition = new BUser();
        condition.setUserId(userId);
        List<BUser> userList1 = buserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<BUser> userList2 = buserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    /*************** 更新信息 **********************/
    // 重置登录密码
    @Override
    public void resetLoginPwd(String userId, String newLoginPwd) {
        BUser buser = buserBO.getCheckUser(userId);
        buserBO.resetLoginPwd(buser, newLoginPwd);
    }

    // 修改头像
    @Override
    public void doModifyPhoto(String userId, String photo) {
        buserBO.refreshPhoto(userId, photo);
    }

    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha) {
        BUser buser = buserBO.getCheckUser(userId);
        String oldMobile = buser.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        buserBO.isMobileExist(newMobile);
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "627310");
        buserBO.resetBindMobile(buser, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已将手机号码改为" + newMobile + "，您的登录名更改为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "631072");

    }

    // 补全授权信息
    @Override
    public void updateInformation(XN627255Req req) {
        BUser data = buserBO.getUser(req.getUserId());
        data.setLevel(StringValidater.toInteger(req.getLevel()));
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setAddress(req.getAddress());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setTeamName(req.getTeamName());
        buserBO.updateInformation(data);
    }

    /*************** 数据库查询 **********************/

    /*************** 查询 **********************/
    // 查询下级代理
    @Override
    public Paginable<BUser> queryLowUser(int start, int limit,
            BUser condition) {
        return buserBO.getPaginable(start, limit, condition);
    }

    // 查询下级代理分页
    @Override
    public Paginable<BUser> queryMyLowUserPage(int start, int limit,
            BUser condition) {
        long totalCount = buserBO.getTotalCount(condition);
        Page<BUser> page = new Page<BUser>(start, limit, totalCount);
        List<BUser> list = buserBO.selectList(condition, page.getPageNo(),
            page.getPageSize());
        page.setList(list);
        return page;
    }

    // 意向代理查询
    @Override
    public Paginable<BUser> queryIntentionAgentPageFront(int start, int limit,
            BUser condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .before(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        condition.setKind(EUserKind.Merchant.getCode());
        long totalCount = buserBO.getTotalCount(condition);
        Page<BUser> page = new Page<BUser>(start, limit, totalCount);
        List<BUser> list = buserBO.selectAgentFront(condition, page.getPageNo(),
            page.getPageSize());

        for (BUser data : list) {
            if (StringUtils.isNotBlank(data.getHighUserId())) {
                BUser highUser = buserBO.getUser(data.getHighUserId());
                data.setHighUser(highUser);
            }
        }
        page.setList(list);
        return page;
    }

    /*************** 代理申请 **********************/
    // 代理申请 （无推荐人）
    @Override
    @Transactional
    public void applyIntent(XN627250Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        buserBO.isMobileExist(req.getMobile());

        // 确认申请等级
        AgentImpower aiData = agentImpowerBO.getAgentImpowerByLevel(
            StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.NO.getCode().equals(aiData.getIsIntent())) {
            throw new BizException("xn00000", "本等级不可被意向");
        }

        // 确认申请id
        BUser data = buserBO.getCheckUser(req.getUserId());

        // 获取申请信息
        data.setRealName(req.getRealName());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setStatus(EUserStatus.MIND.getCode()); // 有意愿
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        data.setApplyDatetime(new Date());

        data.setSource(req.getFromInfo());

        // 数据库
        buserBO.applyIntent(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());

        // insert new agent allot log
        AgentAllot alData = new AgentAllot();
        alData.setApplyUser(req.getUserId());
        alData.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        alData.setApplyDatetime(new Date());
        alData.setStatus(EUserStatus.MIND.getCode());

        agentAllotBO.addAgentAllot(alData);
    }

    /*************** 代理申请，有推荐人 **********************/
    // 申请代理， 有推荐人
    @Override
    @Transactional
    public XN627303Res applyHaveUserReferee(XN627251Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        String introducer = req.getIntroducer();
        // 校验介绍人
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            BUser buser = buserBO.getUserByMobile(req.getIntroducer());
            introducer = buser.getUserId();
            if (buser.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于推荐人哦！");
            }
        }
        // 校验手机号
        buserBO.isMobileExist(req.getMobile());
        // 校验身份证
        if (StringUtils.isNotBlank(req.getIdNo())) {
            buserBO.getUserByIdNo(req.getIdNo());
        }

        XN627303Res result = null;
        // 是否可被意向
        AgentImpower impower = agentImpowerBO.getAgentImpowerByLevel(
            StringValidater.toInteger(req.getApplyLevel()));

        if (EBoolean.NO.getCode().equals(impower.getIsIntent())) {
            throw new BizException("xn0000", "本等级不可被意向");
        }
        // 是否需要实名制
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            if (StringUtils.isBlank(req.getIdNo())
                    || StringUtils.isBlank(req.getIdHand())) {
                throw new BizException("xn0000", "本等级需要实名认证，请完成实名认证");
            }
        }

        BUser data = buserBO.getUser(req.getUserId());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        String status = EUserStatus.TO_APPROVE.getCode(); // 待审核授权
        String toUser = data.getUserReferee();

        BUser userReferee = buserBO.getUser(data.getUserReferee());
        data.setTeamName(userReferee.getTeamName());
        if (data.getApplyLevel() < userReferee.getLevel()) {
            throw new BizException("xn0000", "申请等级不能高于推荐代理的等级");
        }
        if (data.getApplyLevel() == userReferee.getLevel()) {
            toUser = userReferee.getHighUserId();

        }
        // 是否需要公司审核
        if (1 == data.getApplyLevel()) {
            status = EUserStatus.TO_COMPANYAPPROVE.getCode();
            // 防止团队名称重复
            buserBO.checkTeamName(req.getTeamName());
            data.setTeamName(req.getTeamName());
        }
        if (data.getApplyLevel() > userReferee.getLevel()) {
            data.setUserReferee(null);
            toUser = userReferee.getUserId();
        }
        System.out.println("toUser:" + toUser);
        System.out.println("UserReferee:" + data.getUserReferee());

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
        // data.setPayPdf(req.getPayPdf());

        data.setAddress(req.getAddress());
        data.setSource(req.getFromInfo());

        // String logCode = agentAllotBO.toApply(data, toUser,
        // EUserStatus.TO_APPROVE.getCode());
        // data.setLastAgentLog(logCode);

        buserBO.toApply(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
        result = new XN627303Res(data.getUserId(), EBoolean.NO.getCode());

        // insert new agent allot log
        AgentAllot alData = new AgentAllot();
        alData.setApplyUser(req.getUserId());
        alData.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        alData.setApplyDatetime(new Date());
        alData.setStatus(EUserStatus.MIND.getCode());

        agentAllotBO.addAgentAllot(alData);
        return result;

    }

    /***************  向下分配 **********************/

    /***************  接受意向分配 **********************/

    @Override
    public void acceptIntention(String userId, String approver, String remark) {
        BUser data = buserBO.getUser(userId);
        if (!(EUserStatus.MIND.getCode().equals(data.getStatus())
                || EUserStatus.ALLOTED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }

        AgentAllot log = agentAllotBO.getAgentAllot(data.getLastAgentLog());
        data.setHighUserId(log.getToUserName());
        data.setApprover(approver);
        data.setApplyDatetime(new Date());
        data.setRemark(remark);

        data.setStatus(EUserStatus.ADD_INFO.getCode()); // 补全授权资料
        String logCode = agentAllotBO.acceptIntention(data);
        data.setLastAgentLog(logCode);
        buserBO.acceptIntention(data);

        // insert new agent allot log
        AgentAllot imData = new AgentAllot();
        imData.setApplyUser(userId);
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        imData.setStatus(EUserStatus.ADD_INFO.getCode());

        agentAllotBO.addAgentAllot(imData);

    }

    /***************   补全信息 **********************/
    // 补全授权所需资料
    @Override
    public void addInfo(XN627362Req req) {
        BUser data = buserBO.getUser(req.getUserId());
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            BUser user = buserBO.getUserByMobile(req.getIntroducer());
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

        buserBO.checkTeamName(req.getTeamName());

        // 校验身份证
        if (StringUtils.isNotBlank(req.getIdNo())) {
            buserBO.getUserByIdNo(req.getIdNo());
        }

        AgentImpower impower = agentImpowerBO
            .getAgentImpowerByLevel(data.getApplyLevel());
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            if (StringUtils.isBlank(req.getIdNo())
                    || StringUtils.isBlank(req.getIdHand())) {
                throw new BizException("xn0000", "本等级需要实名认证，请完成实名认证");
            } else {
                buserBO.getUserByIdNo(req.getIdNo());
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

        // 需添加等级以及to user审核
        // get last agency log
        // check approver
        // if kind equals b - to approve
        // if kind equals p - to company approve
        data.setStatus(EUserStatus.TO_COMPANYAPPROVE.getCode());
        buserBO.addInfo(data);

        // insert new impower log
        ImpowerApply imData = new ImpowerApply();
        imData.setApplyUser(req.getUserId());
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(EUserStatus.TO_COMPANYAPPROVE.getCode());
        impowerApplyBO.addImpowerApply(imData);

    }

    /*************** 授权申请 **********************/

    /*************** 取消升级申请 **********************/
    @Override
    public void cancelUplevel(String userId) {
        UpLevelApply upData = new UpLevelApply();
        upData.setApplyUser(userId);
        upData.setStatus(EUserStatus.TO_CANCEL.getCode());
        upData.setApplyDatetime(new Date());

        uplevelApplyBO.addUplevelApply(upData);

    }

    /*************** 取消授权申请 **********************/
    @Override
    public void cancelImpower(String userId) {
        BUser data = buserBO.getUser(userId);

        // 是否有下级
        BUser uCondition = new BUser();
        uCondition.setHighUserId(data.getUserId());
        List<BUser> list = buserBO.queryUserList(uCondition);
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
            BUser buser = buserBO.getUser(data.getHighUserId());
            if (!EUserKind.Plat.getCode().equals(buser.getKind())) {
                status = EUserStatus.TO_CANCEL.getCode();
            }
        }
        data.setStatus(status);
        String logCode = impowerApplyBO.cancelImpower(data);
        data.setLastAgentLog(logCode);

        buserBO.cancelImpower(data);

        // insert new impower log
        ImpowerApply imData = new ImpowerApply();
        imData.setApplyUser(userId);
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(EUserStatus.TO_CANCEL.getCode());
        impowerApplyBO.addImpowerApply(imData);
    }

    /*************** 升级申请 **********************/
    // 升级申请
    @Override
    public void upgradeLevel(String userId, String highLevel, String payPdf,
            String payAmount) {
        BUser data = buserBO.getUser(userId);
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

        // 查看升级所需
        AgentUpgrade upgrade = agentUpgradeBO
            .getAgentUpgradeByLevel(StringValidater.toInteger(highLevel));
        Agent agent = agentBO
            .getAgentByLevel(StringValidater.toInteger(highLevel));

        // 推荐人数是否满足半门槛
        List<BUser> userReferee = buserBO
            .getUsersByUserReferee(data.getUserId());
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
            BUser highUser = buserBO.getUser(data.getHighUserId());
            if (EUserKind.Merchant.getCode().equals(highUser.getKind())) {
                status = EUserStatus.TO_UPGRADE.getCode();
            }

        }

        data.setApplyLevel(StringValidater.toInteger(highLevel));
        data.setStatus(status);
        // data.setApplyDatetime(new Date());
        // data.setPayAmount(StringValidater.toLong(payAmount));

        // String logCode = agentAllotBO.upgradeLevel(data, payPdf);
        // data.setLastAgentLog(logCode);
        buserBO.upgradeLevel(data);

        // 新增升级申请记录
        UpLevelApply upData = new UpLevelApply();
        upData.setApplyUser(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(status);
        upData.setApplyDatetime(new Date());
        upData.setPayAmount(StringValidater.toLong(payAmount));

        uplevelApplyBO.addUplevelApply(upData);

    }

    @Override
    public BUser doGetUser(String userId) {
        BUser user = buserBO.getUser(userId);
        if (EUserKind.Merchant.getCode().equals(user.getKind())) {
            // 推荐人、上级、介绍人转义
            if (StringUtils.isNotBlank(user.getUserReferee())) {
                // BUser userReferee =
                // buserBO.getUserName(user.getUserReferee());
                // if (userReferee != null) {
                // user.setRefereeUser(userReferee);
                // }
            }
            // 上级
            if (StringUtils.isNotBlank(user.getHighUserId())) {
                // BUser highUser = buserBO.getUserName(user.getHighUserId());
                // if (highUser != null) {
                // user.setHighUser(highUser);
                // }//
            }

            // 介绍人
            if (StringUtils.isNotBlank(user.getIntroducer())) {
                // BUser introduceName =
                // buserBO.getUserName(user.getIntroducer());
                // if (introduceName != null) {
                // user.setIntroduceName(introduceName.getRealName());
                // }
            }

            // 关联管理员
            if (StringUtils.isNotBlank(user.getManager())) {
                BUser manageName = buserBO.getUserName(user.getManager());
                if (manageName != null) {
                    user.setManager(manageName.getLoginName());
                }
            }

            // 意向归属人
            if (StringUtils.isNotBlank(user.getLastAgentLog())) {
                AgentAllot log = agentAllotBO
                    .getAgentAllot(user.getLastAgentLog());
                if (StringUtils.isNotBlank(log.getToUserName())) {
                    BUser toUser = buserBO.getUser(log.getToUserName());
                    // user.setToTeamName(toUser.getTeamName());
                    // user.setToLevel(toUser.getLevel());
                    // user.setToUserName(toUser.getRealName());
                    // user.setToUserMobile(toUser.getToUserMobile());
                }

            } else if (StringUtils.isNotBlank(user.getUserReferee())) {
                // 有推荐人的时候，同步推荐人的团队等信息
                BUser userReferee = buserBO.getUser(user.getUserReferee());
                // user.setToTeamName(userReferee.getTeamName());
                // user.setToLevel(userReferee.getLevel());
                // user.setToUserName(userReferee.getRealName());
                // user.setToUserMobile(userReferee.getToUserMobile());
            }

            // 授权金额
            if (null != user.getApplyLevel()) {
                Agent agent = agentBO.getAgentByLevel(user.getApplyLevel());
                // user.setImpowerAmount(agent.getAmount());
            }
            if (null != user.getPayAmount() && 0 != user.getPayAmount()) {
                // user.setResult(false);
            }
            // 该等级授权规则
            if (EUserKind.Merchant.getCode().equals(user.getKind())) {
                if (null != user.getApplyLevel()) {
                    AgentImpower impower = agentImpowerBO
                        .getAgentImpowerByLevel(user.getApplyLevel());
                    // user.setImpower(impower);
                }
            }

            // 门槛余额
            Long mkAmount = 0L;
            Account account = accountBO.getAccountNocheck(user.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (null != account) {
                mkAmount = account.getAmount();
            }
            // user.setMkAmount(mkAmount);
            // 云仓余额
            List<WareHouse> whList = wareHouseBO
                .getWareHouseByUser(user.getUserId());
            Long whAmount = 0L;
            for (WareHouse wareHouse : whList) {
                whAmount = whAmount + wareHouse.getAmount();
            }
            // user.setWhAmount(whAmount);
        }

        return user;
    }

    @Override
    public BUser getUserName(String userReferee) {
        return buserBO.getUserName(userReferee);
    }

}
