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

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IJsAwardBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.res.XN627303Res;
import com.bh.mall.enums.EConfigType;
import com.bh.mall.enums.ELoginType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserPwd;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.PostSimulater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class AgentAOImpl implements IAgentAO {

    private static Logger logger = Logger.getLogger(AgentAOImpl.class);

    @Autowired
    IAgentBO agentBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IYxFormBO agentAllotBO;

    @Autowired
    ISqFormBO sqFormBO;

    @Autowired
    ISjFormBO sjFormBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IInOrderBO inOrderBO;

    @Autowired
    IInnerOrderBO innerOrderBO;

    @Autowired
    IJsAwardBO jsAwardBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IWareBO wareBO;

    @Autowired
    IAgentReportBO agentReportBO;

    /*************** 微信注册**********************/
    // 微信注册
    private XN627303Res doWxLoginReg(String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo, String userKind,
            String highUser, String status) {
        agentBO.doCheckOpenId(unionId, h5OpenId, appOpenId);
        Integer level = 0;
        if (EUserKind.Customer.getCode().equals(userKind)) {
            level = 6;
        }
        String userId = agentBO.doRegister(unionId, h5OpenId, appOpenId, null,
            userKind, EUserPwd.InitPwd.getCode(), nickname, photo, status,
            level, highUser);
        XN627303Res result = new XN627303Res(userId, status);
        return result;
    }

    /*************** 登录**********************/
    // 用户名登录
    @Override
    public String doLogin(String loginName, String loginPwd, String kind) {
        Agent condition = new Agent();
        if (EUserKind.Customer.getCode().equals(kind)
                || EUserKind.Merchant.getCode().equals(kind)) {
            condition.setLoginName(loginName);
            condition.setLoginType(ELoginType.MOBILE.getCode());
        } else {
            condition.setLoginName(loginName);
        }
        condition.setKind(kind);
        List<Agent> userList1 = agentBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<Agent> userList2 = agentBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        Agent buser = userList2.get(0);
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
            Agent dbUser = agentBO.doGetUserByOpenId(h5OpenId);

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

    /*************** 注销，激活**********************/
    // 注销 | 激活
    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        Agent buser = agentBO.getAgent(userId);
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
        agentBO.refreshStatus(userId, userStatus, updater, remark);
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
        Agent buser = agentBO.getAgent(userId);
        if (buser == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        agentBO.refreshRole(userId, roleCode, updater, remark);
    }

    /*************** 检查**********************/
    // 检查登录密码
    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        Agent condition = new Agent();
        condition.setUserId(userId);
        List<Agent> userList1 = agentBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<Agent> userList2 = agentBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    /*************** 更新信息 **********************/
    // 重置登录密码
    @Override
    public void resetLoginPwd(String userId, String newLoginPwd) {
        Agent buser = agentBO.getCheckUser(userId);
        agentBO.resetLoginPwd(buser, newLoginPwd);
    }

    // 修改头像
    @Override
    public void doModifyPhoto(String userId, String photo) {
        agentBO.refreshPhoto(userId, photo);
    }

    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha) {
        Agent buser = agentBO.getCheckUser(userId);
        String oldMobile = buser.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        agentBO.isMobileExist(newMobile);
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "627310");
        agentBO.resetBindMobile(buser, newMobile);
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
        Agent data = agentBO.getAgent(req.getUserId());
        data.setLevel(StringValidater.toInteger(req.getLevel()));
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setAddress(req.getAddress());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setTeamName(req.getTeamName());
        agentBO.updateInformation(data);
    }

    /*************** 数据库查询 **********************/

    /*************** 查询 **********************/
    // 查询下级代理
    @Override
    public Paginable<Agent> queryLowUser(int start, int limit,
            Agent condition) {
        return agentBO.getPaginable(start, limit, condition);
    }

    // 查询下级代理分页
    @Override
    public Paginable<Agent> queryMyLowUserPage(int start, int limit,
            Agent condition) {
        long totalCount = agentBO.getTotalCount(condition);
        Page<Agent> page = new Page<Agent>(start, limit, totalCount);
        List<Agent> list = agentBO.selectList(condition, page.getPageNo(),
            page.getPageSize());
        page.setList(list);
        return page;
    }

    @Override
    public Agent getUserName(String userReferee) {
        return null;
    }

    // 下级
    public List<Agent> getAllLowAgent(List<Agent> list) {
        for (int i = 0; i < list.size(); i++) {
            Agent agent = list.get(i);
            Agent condition = new Agent();
            condition.setHighUserId(agent.getUserId());
            List<Agent> agentList = agentBO.queryUserList(condition);
            if (CollectionUtils.isNotEmpty(agentList)) {
                agent.setAgentList(agentList);
                getAllLowAgent(agentList);
            }
        }
        return list;
    }

    @Override
    public List<Agent> queryAgentList(Agent condition) {
        return null;
    }

    @Override
    public void editHighUser(String userId, String highUser, String updater) {
    }

    @Override
    public void editUserReferee(String userId, String userReferee,
            String updater) {
    }

    @Override
    public Paginable<Agent> queryAgentPage(int start, int limit,
            Agent condition) {
        return null;
    }

    @Override
    public List<Agent> getAgentLog(Agent condition) {
        return null;
    }

    @Override
    public void abolishImpower(String userId, String updater, String remark) {
    }

    @Override
    public Agent doGetAgentByMobile(String mobile) {
        return null;
    }

    @Override
    public Agent getAgent(String userId) {
        return null;
    }

    @Override
    public void editManager(String userId, String manager, String updater) {
    }
}
