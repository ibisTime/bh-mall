
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.YxForm;

public interface IYxFormBO extends IPaginableBO<YxForm> {

    // public void editAgentAllot(AgentAllot data);
    public String applyYxForm(YxForm data);

    public List<YxForm> queryYxFormList(YxForm condition);

    public YxForm getYxForm(String code);

    public YxForm getYxFormByLevel(Integer integer);

    public String accepYxForm(YxForm data);

    public String ignore(YxForm data);

    public String addYxForm(YxForm data);

    public String refreshHighUser(YxForm data);

    public String saveYxForm(YxForm data, String toUserId);

    public List<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition);

}
