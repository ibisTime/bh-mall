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

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.ao.IAgentLogAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IJsAwardBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.IWithdrawBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.common.WechatConstant;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.Ware;
import com.bh.mall.domain.Withdraw;
import com.bh.mall.dto.res.XN627303Res;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAgentStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EConfigType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EInnerOrderStatus;
import com.bh.mall.enums.EIsTrader;
import com.bh.mall.enums.EOutOrderStatus;
import com.bh.mall.enums.EUserPwd;
import com.bh.mall.enums.EWithdrawStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.PostSimulater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class AgentAOImpl implements IAgentAO {

    private static Logger logger = Logger.getLogger(AgentAOImpl.class);

    private static boolean flag = false;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISYSUserBO sysUserBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IYxFormBO yxFormBO;

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

    @Autowired
    IAgentLogAO agentLogAO;

    @Autowired
    IAgentLogBO agentLogBO;

    @Autowired
    IOutOrderBO outOrderBO;

    @Autowired
    IWithdrawBO withdrawBO;

    // 微信注册
    private XN627303Res doWxLoginReg(String unionId, String appOpenId,
            String h5OpenId, String nickname, String photo, String fromUserId,
            String status) {
        String userId = agentBO.doRegister(unionId, h5OpenId, appOpenId, null,
            EUserPwd.InitPwd.getCode(), nickname, photo, status, fromUserId);
        XN627303Res result = new XN627303Res(userId, status);
        return result;
    }

    // 注册登录
    @Override
    public XN627303Res doLoginWeChatByAgent(String code, String fromUserId) {

        // fromUserId偶尔会有undefind
        if ("undefined".equals(fromUserId)) {
            throw new BizException("xn00000", "该二维码无法识别，请联系上级获取新的二维码");
        }

        String status = EAgentStatus.MIND.getCode();
        if (StringUtils.isNotBlank(fromUserId)) {
            agentBO.getAgent(fromUserId);
            status = EAgentStatus.IMPOWERO_INFO.getCode();
        }
        return doLoginWeChatH(code, fromUserId, status);
    }

    // doLoginWeChatH
    @Transactional
    private XN627303Res doLoginWeChatH(String code, String fromUserId,
            String status) {
        // Step1：获取密码参数信息
        Map<String, String> configPwd = sysConfigBO
            .getConfigsMap(EConfigType.WEIXIN_H5.getCode());

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
                // 重新申请时，更新用户状态
                if (EAgentStatus.IGNORED.getCode().equals(dbUser.getStatus())
                        || EAgentStatus.MIND.getCode()
                            .equals(dbUser.getStatus())) {
                    dbUser.setFromUserId(fromUserId);
                    agentBO.refreshStatus(dbUser, status);
                }

                result = new XN627303Res(dbUser.getUserId(), dbUser.getStatus(),
                    subscribe);
            } else {
                String nickname = (String) wxRes.get("nickname");
                String photo = (String) wxRes.get("headimgurl");

                result = doWxLoginReg(unionId, null, h5OpenId, nickname, photo,
                    fromUserId, status);
                result = new XN627303Res(result.getUserId(), result.getStatus(),
                    subscribe);
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

    // 修改头像
    @Override
    public void doModifyPhoto(String userId, String photo) {
        Agent buser = agentBO.getAgent(userId);
        agentBO.refreshPhoto(buser, photo);
    }

    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String newMobile,
            String smsCaptcha) {
        Agent buser = agentBO.getAgent(userId);
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

    // 修改管理者
    @Override
    public void editManager(String userId, String manager, String updater) {
        Agent data = agentBO.getAgent(userId);
        sysUserBO.getSYSUser(manager);
        agentBO.refreshManager(data, manager, updater);
    }

    // 修改信息
    @Override
    public void editInformation(String userId, String wxId, String mobile,
            String realName, String teamName, String province, String city,
            String area, String address) {
        Agent data = agentBO.getAgent(userId);
        // 一级代理修改团队名称，下级同步
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel()) {
            agentBO.refreshTeamName(data, teamName);
            this.editTeamName(data.getUserId(), teamName);
        } else if (!data.getTeamName().equals(teamName)) {
            // 非一级代理不允许修改团队名称
            throw new BizException("xn00000", "非最高等级代理的团队名称无法修改");
        }
        agentBO.refreshAgent(data, wxId, mobile, realName, teamName, province,
            city, area, address);
    }

    /**
     * 修改上级
     * 1、该代理不是一级代理时，团队名称同步新上级的（包括他的下级），新的上级门槛中增加相应的余额
     * @see com.bh.mall.ao.IAgentAO#editHighUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void editHighUser(String userId, String highUserId, String updater,
            String remark) {
        Agent data = agentBO.getAgent(userId);
        String oldHighUser = data.getHighUserId();
        // 判断上级
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel()) {
            SYSUser sysUser = sysUserBO.getSYSUser(highUserId);
            highUserId = sysUser.getUserId();
            oldHighUser = sysUser.getUserId();
        } else {
            // 非一级代理同步上级团队名称
            Agent highAgent = agentBO.getAgent(highUserId);
            agentBO.refreshTeamName(data, highAgent.getTeamName());
            this.editTeamName(data.getUserId(), highAgent.getTeamName());

            // 增加上级门槛
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());

            if (0 != account.getAmount()) {
                accountBO.transAmountCZB(oldHighUser,
                    ECurrency.MK_CNY.getCode(), highUserId,
                    ECurrency.MK_CNY.getCode(), account.getAmount(),
                    EBizType.AJ_XGSJ, EBizType.AJ_XGSJ.getValue(),
                    EBizType.AJ_XGSJ.getValue(), data.getUserId());
            }
        }

        agentBO.refreshHighUser(data, highUserId, updater, remark);

    }

    // 修改推荐人
    @Override
    public void editUserReferee(String userId, String referrer, String updater,
            String remark) {
        Agent data = agentBO.getAgent(userId);
        if (StringUtils.isNotBlank(referrer)) {
            Agent referrAgent = agentBO.getAgent(referrer);
            if (referrAgent.getLevel() != data.getLevel()) {
                throw new BizException("xn00000", "代理要与推荐人的等级相同哦");
            }
            if (checkReferrer(data, referrAgent.getUserId())
                    || checkReferrer(referrAgent, data.getUserId())) {
                throw new BizException("xn00000", "这两个代理有相同的推荐人，无法绑定推荐关系");
            }

            Agent condition = new Agent();
            condition.setReferrer(referrAgent.getUserId());
            List<Agent> list = agentBO.queryAgentList(condition);

            // 两个都是操盘手的董事无法绑定推荐关系
            if (EIsTrader.TRADER_YES.getCode().equals(referrAgent.getIsTrader())
                    && EIsTrader.TRADER_YES.getCode()
                        .equals(data.getIsTrader())) {
                throw new BizException("xn00000", "该代理与推荐人都是操盘手，无法绑定关系");

                // 推荐人是操盘手，检查该代理所推荐的代理是否有操盘手,推荐该代理的线无需检查
            } else if (EIsTrader.TRADER_YES.getCode()
                .equals(referrAgent.getIsTrader())) {
                condition.setReferrer(data.getUserId());
                list = agentBO.queryAgentList(condition);
                if (checkTrader(list)) {
                    throw new BizException("xn00000",
                        "新推荐人是操盘手，且该代理已属于其他操盘手团队");
                }

                // 代理自己是操盘手，检查新代理所处线上是否有操盘手
            } else if (EIsTrader.TRADER_YES.getCode().equals(data.getIsTrader())
                    && (checkTrader(referrAgent) || checkTrader(list))) {
                throw new BizException("xn00000", "该代理是操盘手，且新推荐人已属于其他操盘手团队");

                // 代理与新推荐人都不是操盘手且不在一条线上，确保两个人中至少有一个人不属于其他操盘手团队
            } else if (StringValidater
                .toInteger(EAgentLevel.ONE.getCode()) == data.getLevel()
                    && !(checkReferrer(data, referrAgent.getUserId())
                            || checkReferrer(referrAgent, data.getUserId()))) {
                condition.setReferrer(data.getUserId());
                List<Agent> agentList = agentBO.queryAgentList(condition);
                if ((checkTrader(list) || checkTrader(referrAgent))
                        && checkTrader(agentList)) {
                    throw new BizException("xn00000", "该代理是操盘手与新推荐人属于不同操盘手团队");
                }
            }
        }

        agentBO.refreshReferee(data, referrer, updater, remark);
        AgentReport report = agentReportBO
            .getAgentReportByUser(data.getUserId());
        report.setUserReferee(referrer);
        agentReportBO.refreshLevel(report);

    }

    // 分页查询代理
    @Override
    public Paginable<Agent> queryAgentPage(int start, int limit,
            Agent condition) {
        Paginable<Agent> page = agentBO.getPaginable(start, limit, condition);

        for (Agent data : page.getList()) {

            // 上级转义
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
                .getLevel() && StringUtils.isNotBlank(data.getHighUserId())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getHighUserId());
                data.setHighUserName(sysUser.getRealName());
            } else if (null != data.getLevel()
                    && StringUtils.isNotBlank(data.getHighUserId())) {
                Agent highAgent = agentBO.getAgent(data.getHighUserId());
                data.setHighUserName(highAgent.getRealName());
                data.setHighUserMobile(highAgent.getMobile());
            }

            // 门槛余额
            Account account = accountBO.getAccountNocheck(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (null != account) {
                data.setMkAmount(account.getAmount());
            }

            // 云仓余额
            List<Ware> list = wareBO.getWareByUser(data.getUserId());
            Long amount = 0L;
            for (Ware ware : list) {
                amount = amount + ware.getAmount();
            }
            data.setWareAmount(amount);
        }
        return page;
    }

    // 列表查询代理
    @Override
    public List<Agent> queryAgentList(Agent condition) {
        List<Agent> list = agentBO.queryAgentList(condition);
        for (Agent data : list) {
            // 介绍人转义
            if (StringUtils.isNotBlank(data.getIntroducer())) {
                Agent introducer = agentBO.getAgent(data.getIntroducer());
                data.setIntroduceName(introducer.getRealName());
                data.setIntroduceMobile(introducer.getMobile());
            }

            // 上级转义
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
                .getLevel() && StringUtils.isNotBlank(data.getHighUserId())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getHighUserId());
                data.setHighUserName(sysUser.getRealName());
            } else if (StringUtils.isNotBlank(data.getHighUserId())) {
                Agent highAgent = agentBO.getAgent(data.getHighUserId());
                data.setHighUserName(highAgent.getRealName());
                data.setHighUserMobile(highAgent.getMobile());
            }

            // 管理员
            if (StringUtils.isNotBlank(data.getManager())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getManager());
                data.setManageName(sysUser.getRealName());
            }

            // 云仓余额
            List<Ware> wareList = wareBO.getWareByUser(data.getUserId());
            Long amount = 0L;
            for (Ware ware : wareList) {
                amount = amount + ware.getAmount();
            }
            data.setWareAmount(amount);
        }
        return list;
    }

    public Paginable<Agent> queryMyLowAgentPage(int start, int limit,
            Agent condition) {
        Paginable<Agent> page = agentBO.getPaginable(start, limit, condition);

        for (Agent data : page.getList()) {
            // 推荐人转义
            if (StringUtils.isNotBlank(data.getReferrer())) {
                Agent userRefree = agentBO.getAgent(data.getReferrer());
                data.setUserRefreeName(userRefree.getRealName());
                data.setUserRefreeMobile(userRefree.getMobile());
            }
            // 介绍人转义
            if (StringUtils.isNotBlank(data.getIntroducer())) {
                Agent introducer = agentBO.getAgent(data.getIntroducer());
                data.setUserRefreeName(introducer.getRealName());
                data.setIntroduceMobile(introducer.getMobile());
            }

            // 上级转义
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
                .getLevel() && StringUtils.isNotBlank(data.getHighUserId())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getHighUserId());
                data.setHighUserName(sysUser.getRealName());
            } else if (StringUtils.isNotBlank(data.getHighUserId())) {
                Agent highAgent = agentBO.getAgent(data.getHighUserId());
                data.setHighUserName(highAgent.getRealName());
                data.setHighUserMobile(highAgent.getMobile());
            }

            // 管理员
            if (StringUtils.isNotBlank(data.getManager())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getManager());
                data.setManageName(sysUser.getRealName());
            }

            // 门槛余额
            Account account = accountBO.getAccountNocheck(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (null != account) {
                data.setMkAmount(account.getAmount());
            }

        }
        return page;
    }

    // 列表查询代理结构
    @Override
    public List<Agent> queryAgentJgList(Agent condition) {
        List<Agent> agentList = agentBO.queryAgentList(condition);
        if (CollectionUtils.isNotEmpty(agentList)) {
            getAgentList(agentList);
        }
        return agentList;
    }

    private void getAgentList(List<Agent> list) {
        for (Agent agent : list) {
            Agent condition = new Agent();
            condition.setHighUserId(agent.getUserId());
            List<Agent> agentList = agentBO.queryAgentList(condition);
            if (CollectionUtils.isNotEmpty(agentList)) {
                agent.setAgentList(agentList);
                getAgentList(agentList);
            }
        }
    }

    @Override
    @Transactional
    public void abolishSqForm(String userId, String updater, String remark) {
        Agent data = agentBO.getAgent(userId);
        agentBO.refreshStatus(data, updater, remark);

        // 检验是否可取消
        checkCancel(data);
        wareBO.removeByAgent(data.getUserId());

    }

    @Override
    public Agent doGetAgentByMobile(String mobile) {
        return agentBO.getAgentByMobile(mobile);
    }

    @Override
    public Agent getAgent(String userId) {
        Agent data = agentBO.getAgent(userId);

        // 推荐人转义
        if (StringUtils.isNotBlank(data.getReferrer())) {
            Agent userRefree = agentBO.getAgent(data.getReferrer());
            data.setUserRefreeName(userRefree.getRealName());
            data.setUserRefreeMobile(userRefree.getMobile());
        }
        // 介绍人转义
        if (StringUtils.isNotBlank(data.getIntroducer())) {
            Agent introducer = agentBO.getAgent(data.getIntroducer());
            data.setIntroduceName(introducer.getRealName());
        }

        // 上级转义
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel() && StringUtils.isNotBlank(data.getHighUserId())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getHighUserId());
            data.setHighUserName(sysUser.getRealName());
        } else if (StringUtils.isNotBlank(data.getHighUserId())) {
            Agent highAgent = agentBO.getAgent(data.getHighUserId());
            data.setHighUserName(highAgent.getRealName());
            data.setHighUserMobile(highAgent.getMobile());
        }

        // 管理员
        if (StringUtils.isNotBlank(data.getManager())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getManager());
            data.setManageName(sysUser.getRealName());
        }

        // 云仓余额
        List<Ware> wareList = wareBO.getWareByUser(data.getUserId());
        Long amount = 0L;
        for (Ware ware : wareList) {
            amount = amount + ware.getAmount();
        }
        data.setWareAmount(amount);

        // 门槛余额
        Account mkAccount = accountBO.getAccountNocheck(data.getUserId(),
            ECurrency.MK_CNY.getCode());
        if (null != mkAccount) {
            data.setMkAmount(mkAccount.getAmount());
        }

        // 代理轨迹
        AgentLog condition = new AgentLog();
        condition.setApplyUser(data.getUserId());
        List<AgentLog> logList = agentLogAO.queryAgentLogList(condition);

        data.setLogList(logList);
        // 无推荐人
        AgentLog log = agentLogBO.getAgentLog(data.getLastAgentLog());

        // 获取归属人的团队名称(有推荐人)
        if (StringUtils.isNotBlank(data.getFromUserId())) {
            Agent fromAgent = agentBO.getAgent(data.getFromUserId());
            data.setToTeamName(fromAgent.getTeamName());
            data.setToLevel(fromAgent.getLevel());
        } else if (null != log && StringValidater
            .toInteger(EAgentLevel.ONE.getCode()) != log.getApplyLevel()) {
            if (StringUtils.isNotBlank(log.getToUserId())) {
                Agent fromAgent = agentBO.getAgent(log.getToUserId());
                data.setToTeamName(fromAgent.getTeamName());
                data.setToLevel(fromAgent.getLevel());
            }
        }

        // 推荐人数
        Agent aCondition = new Agent();
        aCondition.setReferrer(data.getUserId());
        Long reNumber = agentBO.getTotalCount(aCondition);
        data.setReNumber(reNumber);
        return data;
    }

    // 修改下级团队名称
    @Override
    public void editTeamName(String highUserId, String teamName) {
        Agent condition = new Agent();
        condition.setHighUserId(highUserId);
        List<Agent> list = agentBO.queryAgentList(condition);
        for (Agent data : list) {
            agentBO.refreshTeamName(data, teamName);
        }
    }

    @Override
    public void checkCancel(Agent data) {
        // 是否有下级
        Agent uCondition = new Agent();
        uCondition.setHighUserId(data.getUserId());
        List<Agent> list = agentBO.queryAgentList(uCondition);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn000", "您还有下级，无法退出");
        }

        // 清空账户余额
        Account account = accountBO.getAccountNocheck(data.getUserId(),
            ECurrency.MK_CNY.getCode());
        if (null != account) {
            if (0 < account.getAmount()) {
                throw new BizException("xn00000", "您的门槛账户中还有余额");
            }
        }

        Account txAccount = accountBO.getAccountNocheck(data.getUserId(),
            ECurrency.TX_CNY.getCode());
        if (null != txAccount) {
            if (0 < txAccount.getAmount()) {
                throw new BizException("xn00000", "您的业绩账户中还有余额");
            }
        }

        Account cAccount = accountBO.getAccountNocheck(data.getUserId(),
            ECurrency.C_CNY.getCode());
        if (null != cAccount) {
            if (0 < cAccount.getAmount()) {
                // 账户清零
                throw new BizException("xn000", "您的微店账户中还有余额");
            }
        }

        // 是否有未完成的订单
        OutOrder oCondition = new OutOrder();
        oCondition.setApplyUser(data.getUserId());
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOutOrderStatus.TO_SEND.getCode());
        statusList.add(EOutOrderStatus.TO_APPROVE.getCode());
        oCondition.setStatusList(statusList);

        long count = outOrderBO.selectCount(oCondition);
        if (count != 0) {
            throw new BizException("xn000", "您还有代发货的订单,请在订单完成后申请");
        }

        // 是够有未完成的内购订单
        InnerOrder ioCondition = new InnerOrder();
        ioCondition.setApplyUser(data.getUserId());
        ioCondition.setStatusForQuery(EInnerOrderStatus.TO_APPROVE.getCode());
        long ioCount = innerOrderBO.selectCount(ioCondition);
        if (ioCount != 0) {
            throw new BizException("xn000", "您还有代发货的内购订单,请在订单完成后申请");
        }

        // 是否有未完成的取现
        Withdraw withDraw = new Withdraw();
        withDraw.setApplyUser(data.getUserId());
        withDraw.setStatus(EWithdrawStatus.toApprove.getCode());
        List<Withdraw> withDrawList = withdrawBO.queryWithdrawList(withDraw);
        if (CollectionUtils.isNotEmpty(withDrawList)) {
            throw new BizException("xn000", "您还有未完成的取现订单,请在订单完成后申请");
        }

    }

    @Override
    public void doSetTrader(String userId, String updater, String remark) {
        Agent data = agentBO.getAgent(userId);
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != data
            .getLevel()) {
            throw new BizException("xn00000", "非最高级别代理不能设置为操盘手");
        }

        if (EIsTrader.TRADER_YES.getCode().equals(data.getIsTrader())) {
            throw new BizException("xn00000", "该代理已是操盘手，请勿重复操作");
        }

        // 检查该代理所在线上有无已是操盘手的代理
        Agent condition = new Agent();
        condition.setReferrer(data.getUserId());
        List<Agent> list = agentBO.queryAgentList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            if (checkTrader(list) || checkTrader(data)) {
                throw new BizException("xn00000", "该代理已属于其他操盘手的团队，无法成为操盘手");
            }
        }

        agentBO.doSetTrader(data, updater, remark);

    }

    @Override
    public void doCancelTrader(String userId, String updater, String remark) {
        Agent data = agentBO.getAgent(userId);
        if (!EIsTrader.TRADER_YES.getCode().equals(data.getIsTrader())) {
            throw new BizException("xn00000", "该代理不是操盘手，无需取消");
        }

        agentBO.doCancelTrader(data, updater, remark);
    }

    @Override
    public List<Agent> queryTrader(Agent condition) {
        List<Agent> list = agentBO.queryAgentList(condition);
        this.getTrader(list);
        return list;
    }

    private void getTrader(List<Agent> list) {
        for (Agent agent : list) {
            Agent condition = new Agent();
            condition.setReferrer(agent.getUserId());
            List<Agent> refList = agentBO.queryAgentList(condition);
            agent.setAgentList(refList);
            if (StringUtils.isNotBlank(agent.getReferrer())) {
                getTrader(refList);
            }
        }
    }

    private boolean checkTrader(List<Agent> list) {
        for (Agent agent : list) {
            if (EIsTrader.TRADER_YES.getCode().equals(agent.getIsTrader())) {
                flag = true;
                return flag;
            }
            Agent condition = new Agent();
            condition.setReferrer(agent.getUserId());
            List<Agent> refList = agentBO.queryAgentList(condition);
            checkTrader(refList);
        }
        return flag;
    }

    private boolean checkTrader(Agent agent) {
        if (StringUtils.isNotBlank(agent.getReferrer())) {
            Agent referrer = agentBO.getAgent(agent.getReferrer());
            if (EIsTrader.TRADER_YES.getCode().equals(referrer.getIsTrader())) {
                flag = true;
                return flag;
            }
            checkTrader(referrer);
        }
        return flag;
    }

    private boolean checkReferrer(Agent data, String userId) {
        if (StringUtils.isNotBlank(data.getReferrer())) {
            Agent agent = agentBO.getAgent(data.getReferrer());
            if (agent.getUserId().equals(userId)) {
                flag = true;
                return flag;
            }
            checkReferrer(agent, userId);
        }
        return flag;
    }

}
