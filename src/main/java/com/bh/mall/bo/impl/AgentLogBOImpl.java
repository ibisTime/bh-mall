package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentLogDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.SqForm;
import com.bh.mall.domain.YxForm;
import com.bh.mall.exception.BizException;

@Component
public class AgentLogBOImpl extends PaginableBOImpl<AgentLog>
        implements IAgentLogBO {

    @Autowired
    private IAgentLogDAO agentLogDAO;

    @Override
    public String applyYxForm(YxForm yxForm) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog data = new AgentLog();

        data.setCode(code);
        data.setApplyUser(yxForm.getUserId());
        data.setRealName(yxForm.getRealName());
        data.setWxId(yxForm.getWxId());
        data.setMobile(yxForm.getMobile());

        data.setApplyLevel(data.getApplyLevel());
        data.setToUserId(yxForm.getToUserId());
        data.setApplyDatetime(yxForm.getApplyDatetime());
        data.setProvince(yxForm.getProvince());
        data.setCity(yxForm.getCity());

        data.setArea(yxForm.getAddress());
        data.setAddress(yxForm.getAddress());
        data.setStatus(data.getStatus());
        agentLogDAO.insert(data);

        return code;
    }

    @Override
    public String applySqForm(SqForm sqForm) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog data = new AgentLog();

        data.setCode(code);
        data.setApplyUser(sqForm.getUserId());
        data.setRealName(sqForm.getRealName());
        data.setWxId(sqForm.getWxId());
        data.setMobile(sqForm.getMobile());

        data.setApplyLevel(data.getApplyLevel());
        data.setToUserId(sqForm.getToUserId());
        data.setTeamName(sqForm.getTeamName());
        data.setIntroducer(sqForm.getIntroducer());
        data.setReferrer(sqForm.getReferrer());

        data.setApplyDatetime(sqForm.getApplyDatetime());
        data.setProvince(sqForm.getProvince());
        data.setCity(sqForm.getCity());
        data.setArea(sqForm.getAddress());
        data.setAddress(sqForm.getAddress());

        data.setStatus(sqForm.getStatus());
        data.setApplyDatetime(sqForm.getApplyDatetime());
        data.setApprover(sqForm.getApprover());
        data.setApproveName(sqForm.getApproveName());
        data.setApproveDatetime(sqForm.getApproveDatetime());

        data.setImpowerDatetime(sqForm.getImpowerDatetime());
        data.setRemark(sqForm.getRemark());

        agentLogDAO.insert(data);
        return code;
    }

    @Override
    public String applySjForm(SjForm sjForm, Agent agent) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog data = new AgentLog();

        data.setCode(code);
        data.setApplyUser(sjForm.getUserId());
        data.setRealName(sjForm.getRealName());
        data.setWxId(agent.getWxId());
        data.setMobile(agent.getMobile());

        data.setLevel(sjForm.getLevel());
        data.setApplyLevel(data.getApplyLevel());
        data.setToUserId(sjForm.getToUserId());
        data.setTeamName(sjForm.getTeamName());
        data.setIntroducer(agent.getIntroducer());

        data.setProvince(agent.getProvince());
        data.setCity(agent.getCity());
        data.setArea(agent.getAddress());
        data.setAddress(agent.getAddress());

        data.setPayAmount(sjForm.getPayAmount());
        data.setPayPdf(sjForm.getPayPdf());
        data.setStatus(sjForm.getStatus());
        data.setApplyDatetime(sjForm.getApplyDatetime());
        data.setApprover(sjForm.getApprover());

        data.setApproveName(sjForm.getApproveName());
        data.setApproveDatetime(sjForm.getApproveDatetime());
        data.setImpowerDatetime(agent.getImpowerDatetime());
        data.setRemark(sjForm.getRemark());
        agentLogDAO.insert(data);
        return code;
    }

    @Override
    public List<AgentLog> queryAgentLogList(AgentLog condition) {
        return agentLogDAO.selectList(condition);
    }

    @Override
    public AgentLog getAgentLog(String code) {
        AgentLog data = null;
        if (StringUtils.isNotBlank(code)) {
            AgentLog condition = new AgentLog();
            condition.setCode(code);
            data = agentLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    @Override
    public String refreshAgent(Agent agent) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog data = new AgentLog();

        data.setCode(code);
        data.setApplyUser(agent.getUserId());
        data.setRealName(agent.getRealName());
        data.setWxId(agent.getWxId());
        data.setMobile(agent.getMobile());

        data.setLevel(agent.getLevel());
        data.setApplyLevel(data.getApplyLevel());
        data.setTeamName(agent.getTeamName());
        data.setIntroducer(agent.getIntroducer());
        data.setReferrer(agent.getReferrer());

        data.setHighUserId(agent.getHighUserId());
        data.setProvince(agent.getProvince());
        data.setCity(agent.getCity());
        data.setArea(agent.getAddress());
        data.setAddress(agent.getAddress());

        data.setStatus(agent.getStatus());
        data.setApprover(agent.getApprover());
        data.setApproveName(agent.getApproveName());
        data.setApproveDatetime(agent.getApproveDatetime());
        data.setImpowerDatetime(agent.getImpowerDatetime());
        data.setRemark(agent.getRemark());

        agentLogDAO.insert(data);
        return code;
    }

}
