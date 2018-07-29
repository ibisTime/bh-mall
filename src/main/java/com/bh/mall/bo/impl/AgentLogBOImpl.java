package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentLogDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.enums.EAgentType;
import com.bh.mall.exception.BizException;

@Component
public class AgentLogBOImpl extends PaginableBOImpl<AgentLog>
        implements IAgentLogBO {

    @Autowired
    private IAgentLogDAO agentLogDAO;

    @Override
    public String saveAgentLog(Agent data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(toUserId);
        alData.setType(EAgentType.Allot.getCode());
        alData.setTeamName(data.getTeamName());

        alData.setUserReferee(data.getUserReferee());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(new Date());
        alData.setStatus(data.getStatus());
        agentLogDAO.insert(alData);

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
    public String acceptIntention(AgentLog log, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setType(EAgentType.Imporder.getCode());
        alData.setApplyUser(log.getApplyUser());
        alData.setApplyDatetime(new Date());
        alData.setToUserId(log.getToUserId());

        alData.setApplyLevel(log.getApplyLevel());
        alData.setTeamName(log.getTeamName());
        alData.setUserReferee(log.getUserReferee());
        alData.setApprover(log.getApprover());
        alData.setApproveDatetime(new Date());

        alData.setStatus(log.getStatus());
        agentLogDAO.insert(alData);
        return code;
    }

    @Override
    public String ignore(Agent data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setType(EAgentType.Allot.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());

        alData.setApplyLevel(data.getApplyLevel());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(new Date());

        alData.setStatus(data.getStatus());
        agentLogDAO.insert(alData);
        return code;

    }

    @Override
    public String cancelImpower(Agent data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setType(EAgentType.OUT.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setLevel(data.getLevel());

        alData.setToUserId(data.getHighUserId());
        alData.setHighUserId(data.getHighUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setStatus(data.getStatus());
        agentLogDAO.insert(alData);
        return code;

    }

    @Override
    public String approveImpower(AgentLog log, Agent user) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();

        alData.setCode(code);
        alData.setApplyUser(user.getUserId());
        alData.setApplyDatetime(user.getApplyDatetime());
        alData.setType(EAgentType.Imporder.getCode());
        alData.setApplyLevel(user.getApplyLevel());

        alData.setToUserId(log.getToUserId());
        alData.setTeamName(user.getTeamName());
        alData.setUserReferee(user.getUserReferee());
        alData.setApprover(user.getApprover());

        alData.setApplyLevel(user.getApplyLevel());
        alData.setLevel(user.getLevel());
        alData.setHighUserId(user.getHighUserId());
        alData.setApproveDatetime(new Date());
        alData.setStatus(user.getStatus());

        alData.setRemark(log.getRemark());
        agentLogDAO.insert(alData);
        return code;
    }

    @Override
    public String approveCanenl(Agent data, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();

        alData.setCode(code);
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setType(EAgentType.OUT.getCode());
        alData.setLevel(data.getLevel());
        alData.setHighUserId(data.getHighUserId());

        alData.setApplyLevel(data.getApplyLevel());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(new Date());
        alData.setStatus(status);
        alData.setRemark(data.getRemark());

        agentLogDAO.insert(alData);
        return code;
    }

    @Override
    public String upgradeLevel(Agent data, String payPdf, String toUserId) {
        AgentLog agentLog = this.getAgentLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setType(EAgentType.Upgrade.getCode());

        alData.setToUserId(toUserId);
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agentLog.getApplyDatetime());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setLevel(data.getLevel());
        alData.setHighUserId(data.getHighUserId());

        alData.setTeamName(data.getTeamName());
        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        agentLogDAO.insert(alData);
        return code;
    }

    @Override
    public String approveUpgrade(Agent data, String status) {
        AgentLog agentLog = this.getAgentLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setType(EAgentType.Upgrade.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agentLog.getApplyDatetime());

        alData.setLevel(data.getLevel());
        alData.setHighUserId(data.getHighUserId());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(new Date());
        alData.setStatus(status);
        alData.setRemark(data.getRemark());
        agentLogDAO.insert(alData);
        return code;
    }

    @Override
    public List<AgentLog> queryAgentLogPage(int start, int limit,
            AgentLog condition) {
        long totalCount = agentLogDAO.selectTotalCount(condition);
        Page<AgentLog> page = new Page<AgentLog>(start, limit, totalCount);
        return agentLogDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    @Override
    public String toApply(Agent data, String toUser, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(toUser);

        alData.setType(EAgentType.Imporder.getCode());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApplyUser(data.getUserId());
        Date date = new Date();
        alData.setApplyDatetime(date);

        alData.setStatus(status);
        agentLogDAO.insert(alData);
        return code;
    }

    @Override
    public String refreshHighUser(Agent data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        AgentLog alData = new AgentLog();
        alData.setCode(code);
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApplyUser(data.getUserId());

        alData.setType(EAgentType.Update.getCode());
        Date date = new Date();
        alData.setApplyDatetime(date);
        alData.setStatus(data.getStatus());
        agentLogDAO.insert(alData);
        return code;
    }

}
