package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.dto.req.XN627547Req;

public interface IAgentPriceBO extends IPaginableBO<AgentPrice> {

    public boolean isAgentPriceExist(String code);

    public String saveAgentPrice(AgentPrice data);

    public int removeAgentPrice(String code);

    public void refreshAgentPrice(List<XN627547Req> list);

    public List<AgentPrice> queryAgentPriceList(
            AgentPrice pspConditon);

    public AgentPrice getAgentPrice(String code);

    public AgentPrice getPriceByLevel(String productSpecsCode,
            Integer level);

    public List<AgentPrice> getApBySpecsCode(String productSpecs);

    // 检查机构数量
    public int checkMinQuantity(String productSpecsCode, Integer level);

}
