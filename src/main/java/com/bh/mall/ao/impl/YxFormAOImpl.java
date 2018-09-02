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
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAgentStatus;
import com.bh.mall.enums.EBoolean;
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
    * 申请意向代理(无头的代理)
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

        // 之前有意向单更新，没有新增
        YxForm yxForm = yxFormBO.getYxForm(req.getUserId());
        String logCode = null;
        if (null == yxForm) {
            logCode = yxFormBO.applyYxForm(data.getUserId(), req.getRealName(),
                req.getWxId(), req.getMobile(), req.getApplyLevel(),
                req.getProvince(), req.getCity(), req.getArea(),
                req.getAddress(), req.getFromInfo());
        } else {
            logCode = yxFormBO.updateYxForm(yxForm, req.getRealName(),
                req.getWxId(), req.getMobile(), req.getApplyLevel(),
                req.getProvince(), req.getCity(), req.getArea(),
                req.getAddress(), req.getFromInfo());
        }

        // 更新最后一条日志
        agentBO.applyAgent(data, req.getRealName(), req.getWxId(),
            req.getMobile(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), logCode);

    }

    /**
     * 平台分配意向代理，向下分配给其他代理
     * 
     */
    @Override
    public void allotYxFormByP(String userId, String toUserId, String approver,
            String remark) {

        // 确认申请id
        YxForm data = yxFormBO.getYxForm(userId);
        Agent toUser = agentBO.getAgent(toUserId);
        if (!EAgentStatus.IMPOWERED.getCode().equals(toUser.getStatus())) {
            throw new BizException("xn0000", "该代理还未完成授权，不能分配给他");
        }

        if (data.getApplyLevel() <= toUser.getLevel()) {
            throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
        }
        SYSUser sysUser = sysUserBO.getSYSUser(approver);

        String logCode = yxFormBO.allotYxForm(data, toUserId, approver,
            sysUser.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshYx(agent, EYxFormStatus.ALLOTED.getCode(),
            sysUser.getUserId(), sysUser.getRealName(), logCode);
    }

    /**
     * 代理分配意向代理
     */
    @Override
    public void allotYxFormByB(String userId, String toUserId, String approver,
            String remark) {
        YxForm data = yxFormBO.getYxForm(userId);
        Agent toUser = agentBO.getAgent(toUserId);
        if (!EAgentStatus.IMPOWERED.getCode().equals(toUser.getStatus())) {
            throw new BizException("xn0000", "该代理还未完成授权，不能分配给他");
        }
        if (data.getApplyLevel() <= toUser.getLevel()) {
            throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
        }

        Agent agent = agentBO.getAgent(approver);
        String logCode = yxFormBO.allotYxForm(data, toUserId, approver,
            agent.getRealName(), remark);

        // 更新最后一条轨迹
        agentBO.refreshYx(agent, EYxFormStatus.ALLOTED.getCode(), approver,
            agent.getRealName(), logCode);

    }

    /**
     * 平台忽略意向代理
     */
    @Override
    public void ignoreYxFormByP(String userId, String approver, String remark) {
        YxForm yxForm = yxFormBO.getYxForm(userId);
        SYSUser sysUser = sysUserBO.getSYSUser(approver);
        String logCode = yxFormBO.ignoreYxForm(yxForm, sysUser.getUserId(),
            sysUser.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshYx(agent, EAgentStatus.IGNORED.getCode(),
            sysUser.getUserId(), sysUser.getRealName(), logCode);
        yxFormBO.removeYxForm(yxForm);
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
        agentBO.refreshYx(agent, EAgentStatus.MIND.getCode(), agent.getUserId(),
            agent.getRealName(), logCode);

        // 删除意向单
        yxFormBO.removeYxForm(yxForm);
    }

    /**
     * 平台接受意向代理
     * @see com.bh.mall.ao.IYxFormAO#applyYxForm(com.bh.mall.dto.req.XN627250Req)
     */
    @Override
    public void acceptYxFormByP(String userId, String approver, String remark) {

        YxForm yxForm = yxFormBO.getYxForm(userId);
        // 不能接受非最高等级代理
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != yxForm
            .getApplyLevel()) {
            throw new BizException("xn00000", "该代理申请的不是最高等级，请分配给其他代理吧");
        }

        SYSUser sysUser = sysUserBO.getSYSUser();
        String logCode = yxFormBO.acceptYxForm(yxForm, sysUser.getUserId(),
            sysUser.getRealName(), remark);

        // 更新最后一条轨迹
        Agent agent = agentBO.getAgent(userId);
        agentBO.refreshYx(agent, EYxFormStatus.ACCEPT.getCode(),
            agent.getUserId(), agent.getRealName(), logCode);

        // 删除意向单
        yxFormBO.removeYxForm(yxForm);

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
        agentBO.refreshYx(agent, EYxFormStatus.ACCEPT.getCode(),
            agent.getUserId(), agent.getRealName(), logCode);

        // 删除意向单
        yxFormBO.removeYxForm(yxForm);
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
        return yxFormBO.queryYxFormList(condition);
    }

    // 详细查询意向代理
    @Override
    public YxForm getYxForm(String code) {
        YxForm data = yxFormBO.getYxForm(code);
        Agent agent = agentBO.getAgent(data.getUserId());
        data.setNickname(agent.getNickname());
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
        Paginable<YxForm> page = yxFormBO.getPaginable(start, limit, condition);
        for (YxForm data : page.getList()) {
            Agent agent = agentBO.getAgent(data.getUserId());
            data.setNickname(agent.getNickname());
        }

        return page;
    }

    @Override
    public void editYxForm(String userId, String realName, String wxId,
            String mobile, Integer level, String province, String city,
            String area, String address) {
        YxForm data = yxFormBO.getYxForm(userId);
        if (!EYxFormStatus.MIND.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该代理已经被接受喽，无法修改资料了");
        }
        yxFormBO.refreshYxForm(data, realName, wxId, mobile, level, province,
            city, area, address);
        Agent agent = agentBO.getAgent(data.getUserId());
        agentBO.refreshAgent(agent, wxId, mobile, realName, null, province,
            city, area, address);

    }

}
