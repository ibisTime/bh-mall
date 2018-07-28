
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.YxForm;

public interface IYxFormBO extends IPaginableBO<YxForm> {

    // public void editAgentAllot(AgentAllot data);
    public String applyIntent(YxForm data);

    public List<YxForm> queryAgentAllotList(YxForm condition);

    public YxForm getAgentAllot(String code);

    public YxForm getAgentAllotByLevel(Integer integer);

    public String acceptIntention(YxForm data);

    public String ignore(YxForm data);

    public String addAgentAllot(YxForm data);

    public String refreshHighUser(YxForm data);

    public String saveAgentAllot(YxForm data, String toUserId);

    public List<YxForm> queryAgentAllotPage(int start, int limit,
            YxForm condition);

}
