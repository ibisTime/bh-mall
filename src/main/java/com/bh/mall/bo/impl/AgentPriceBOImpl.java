package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAgentPriceDAO;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.dto.req.XN627547Req;
import com.bh.mall.exception.BizException;

@Component
public class AgentPriceBOImpl extends PaginableBOImpl<AgentPrice>
        implements IAgentPriceBO {

    @Autowired
    private IAgentPriceDAO agentPriceDAO;

    @Override
    public boolean isAgentPriceExist(String code) {
        AgentPrice condition = new AgentPrice();
        condition.setCode(code);
        if (agentPriceDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveAgentPrice(AgentPrice data) {
        return null;
    }

    @Override
    public int removeAgentPrice(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            AgentPrice data = new AgentPrice();
            data.setCode(code);
            count = agentPriceDAO.delete(data);
        }
        return count;
    }

    @Override
    public void refreshAgentPrice(List<XN627547Req> list) {
        AgentPrice data = new AgentPrice();
        for (XN627547Req req : list) {
            data.setCode(req.getCode());
            data.setLevel(StringValidater.toInteger(req.getLevel()));
            data.setPrice(StringValidater.toLong(req.getPrice()));
            agentPriceDAO.update(data);
        }
    }

    @Override
    public List<AgentPrice> queryAgentPriceList(AgentPrice condition) {
        return agentPriceDAO.selectList(condition);
    }

    @Override
    public AgentPrice getAgentPrice(String code) {
        AgentPrice data = null;
        if (StringUtils.isNotBlank(code)) {
            AgentPrice condition = new AgentPrice();
            condition.setCode(code);
            data = agentPriceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "规格价格不存在");
            }
        } else {
            throw new BizException("xn0000", "规格价格编号不能为空");
        }
        return data;
    }

    @Override
    public AgentPrice getPriceByLevel(String specsCode, Integer level) {
        AgentPrice data = null;
        if (StringUtils.isNotBlank(specsCode)) {
            AgentPrice condition = new AgentPrice();
            condition.setSpecsCode(specsCode);
            condition.setLevel(level);
            data = agentPriceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "规格价格不存在");
            }
        }
        return data;
    }

    @Override
    public List<AgentPrice> getAgentPriceBySpecsCode(String specsCode) {
        List<AgentPrice> list = null;
        if (StringUtils.isNotBlank(specsCode)) {
            AgentPrice condition = new AgentPrice();
            condition.setSpecsCode(specsCode);
            list = agentPriceDAO.selectList(condition);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException("xn0000", "该规格价格不存在");
            }
        }
        return list;
    }

    @Override
    public int checkMinQuantity(String productSpecsCode, Integer level) {
        // ProductSpecsPrice psp = this.getPriceByLevel(productSpecsCode,
        // level);
        // if (quantity > psp.getMinQuantity()) {
        // return false;
        // }
        return 0;
    }
}
