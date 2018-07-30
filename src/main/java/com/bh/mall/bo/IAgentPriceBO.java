package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.dto.req.XN627547Req;

public interface IAgentPriceBO extends IPaginableBO<AgentPrice> {

    public boolean isProductSpecsPriceExist(String code);

    public String saveProductSpecsPrice(AgentPrice data);

    public int removeProductSpecsPrice(String code);

    public void refreshProductSpecsPrice(List<XN627547Req> list);

    public List<AgentPrice> queryProductSpecsPriceList(
            AgentPrice pspConditon);

    public AgentPrice getProductSpecsPrice(String code);

    public AgentPrice getPriceByLevel(String productSpecsCode,
            Integer level);

    public List<AgentPrice> getPspBySpecsCode(String productSpecs);

    // 检查机构数量
    public int checkMinQuantity(String productSpecsCode, Integer level);

}
