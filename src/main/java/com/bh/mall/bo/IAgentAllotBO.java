
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentAllot;
import com.bh.mall.domain.BUser;

public interface IAgentAllotBO extends IPaginableBO<AgentAllot> {

    // public void editAgentAllot(AgentAllot data);

    public List<AgentAllot> queryAgentAllotList(AgentAllot condition);

    public AgentAllot getAgentAllot(String code);

    public AgentAllot getAgentAllotByLevel(Integer integer);

    public String acceptIntention(BUser data);

    public String ignore(BUser data);

    public String addAgentAllot(AgentAllot data);

    public String toApply(BUser data, String toUser, String status);

    public String refreshHighUser(BUser data);

    public String saveAgentAllot(BUser data, String toUserId);

    public List<AgentAllot> queryAgentAllotPage(int start, int limit,
            AgentAllot condition);
}
