package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;

public interface IAgentLogBO extends IPaginableBO<AgentLog> {

    public List<AgentLog> queryAgentLogList(AgentLog condition);

    public AgentLog getAgentLog(String code);

    public String saveAgentLog(Agent data, String toUser);

    public String acceptIntention(AgentLog log, String status);

    public String ignore(Agent data);

    public String cancelImpower(Agent data);

    public String approveImpower(AgentLog log, Agent user);

    public String approveCanenl(Agent data, String status);

    public String upgradeLevel(Agent data, String payPdf, String toUserId);

    public String approveUpgrade(Agent data, String status);

    public List<AgentLog> queryAgentLogPage(int start, int limit,
            AgentLog condition);

    public String toApply(Agent dbUser, String payPdf, String status);

    public String refreshHighUser(Agent data);

}
