package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.YxForm;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.ESqFormStatus;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.enums.EYxFormStatus;
import com.bh.mall.exception.BizException;

@Service
public class YxFormAOImpl implements IYxFormAO {

    @Autowired
    private IYxFormBO yxFormBO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    /**
    * 申请意向代理
    * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
    */
    @Override
    @Transactional
    public void applyYxForm(XN627250Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        // check mobile exist
        agentBO.isMobileExist(req.getMobile());
        // 确认申请等级
        AgentLevel aiData = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.NO.getCode().equals(aiData.getIsIntent())) {
            throw new BizException("xn00000", "本等级不可被意向");
        }

        // 确认申请id
        Agent data = agentBO.getAgent(req.getUserId());
        String logCode = yxFormBO.applyYxForm(data.getUserId(),
            req.getRealName(), req.getWxId(), req.getMobile(),
            req.getApplyLevel(), req.getProvince(), req.getCity(),
            req.getArea(), req.getAddress(), req.getFromInfo());
        // 更新最后一条日志
        agentBO.applyAgent(data, req.getRealName(), req.getWxId(),
            req.getMobile(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), logCode);

    }

    /**
     * 平台分配意向代理
     * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
     */
    @Override
    public void allotYxFormByP(String userId, String toUserId, String approver,
            String remark) {
        // 确认申请id
        YxForm data = yxFormBO.getYxForm(userId);
        Agent toUser = agentBO.getAgent(toUserId);
        if (data.getApplyLevel() <= toUser.getLevel()) {
            throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
        }
        SYSUser sysUser = sysUserBO.getSYSUser(approver);

        String logCode = yxFormBO.allotYxForm(data, toUserId, approver,
            sysUser.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshLastLog(agent, EYxFormStatus.IGNORED.getCode(),
            sysUser.getUserId(), sysUser.getRealName(), logCode);
    }

    /**
     * 代理分配意向代理
     * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
     */
    @Override
    public void allotYxFormByB(String userId, String toUserId, String approver,
            String remark) {
        YxForm data = yxFormBO.getYxForm(userId);

        Agent toUser = agentBO.getAgent(toUserId);
        if (data.getApplyLevel() <= toUser.getLevel()) {
            throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
        }

        Agent agent = agentBO.getAgent(approver);
        String logCode = yxFormBO.allotYxForm(data, toUserId, approver,
            agent.getRealName(), remark);

        // 更新最后一条轨迹
        agentBO.refreshLastLog(agent, null, approver, agent.getRealName(),
            logCode);
    }

    /**
     * 平台忽略意向代理
     * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
     */
    @Override
    public void ignoreYxFormByP(String userId, String approver, String remark) {
        YxForm yxForm = yxFormBO.getYxForm(userId);
        SYSUser sysUser = sysUserBO.getSYSUser(approver);
        String logCode = yxFormBO.ignoreYxForm(yxForm, sysUser.getUserId(),
            sysUser.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshLastLog(agent, EYxFormStatus.IGNORED.getCode(),
            sysUser.getUserId(), sysUser.getRealName(), logCode);

    }

    /**
     * 代理忽略意向代理
     * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
     */
    @Override
    public void ignoreYxFormByB(String userId, String approver, String remark) {
        YxForm yxForm = yxFormBO.getYxForm(userId);
        Agent data = agentBO.getAgent(approver);
        String logCode = yxFormBO.ignoreYxForm(yxForm, data.getUserId(),
            data.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshLastLog(agent, EYxFormStatus.IGNORED.getCode(),
            agent.getUserId(), agent.getRealName(), logCode);
    }

    /**
     * 平台接受意向代理
     * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
     */
    @Override
    public void acceptYxFormByP(String userId, String approver, String remark) {

        YxForm yxForm = yxFormBO.getYxForm(userId);
        SYSUser sysUser = sysUserBO.getSYSUser(approver);
        String logCode = yxFormBO.acceptYxForm(yxForm, sysUser.getUserId(),
            sysUser.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshLastLog(agent, ESqFormStatus.BC_ZL.getCode(),
            agent.getUserId(), agent.getRealName(), logCode);

    }

    /**
     * 代理接受意向代理
     * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
     */
    @Override
    public void acceptYxFormByB(String userId, String approver, String remark) {
        // 确认申请id
        YxForm yxForm = yxFormBO.getYxForm(userId);
        Agent data = agentBO.getAgent(approver);
        String logCode = yxFormBO.acceptYxForm(yxForm, data.getUserId(),
            data.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshLastLog(agent, ESqFormStatus.BC_ZL.getCode(),
            agent.getUserId(), agent.getRealName(), logCode);

    }

    // 列表查询意向代理
    @Override
    public List<YxForm> queryYxFormList(YxForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<YxForm> list = yxFormBO.queryYxFormList(condition);
        return list;
    }

    // 详细查询意向代理
    @Override
    public YxForm getYxForm(String code) {
        YxForm data = yxFormBO.getYxForm(code);
        return data;
    }

    // 分页查询意向代理
    @Override
    public Paginable<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        if (EUserStatus.IGNORED.getCode().equals(condition.getStatus())) {
            condition.setApprover(condition.getUserIdForQuery());
        } else {
            condition.setToUserId(condition.getUserIdForQuery()); // 意向归属人
        }
        return yxFormBO.getPaginable(start, limit, condition);
    }

}
