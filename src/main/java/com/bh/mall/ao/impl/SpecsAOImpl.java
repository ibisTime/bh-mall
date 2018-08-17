package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISpecsAO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.enums.ESpecsLogType;

@Service
public class SpecsAOImpl implements ISpecsAO {

    @Autowired
    private ISpecsBO specsBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IAgentPriceBO agentPriceBO;

    @Override
    public void editRepertory(String code, String type, Integer number,
            String updater) {
        Specs specs = specsBO.getSpecs(code);
        if (ESpecsLogType.Output.getCode().equals(type)) {
            number = -number;
        }
        Product product = productBO.getProduct(specs.getProductCode());
        specsBO.refreshRepertory(product.getName(), specs, type, number,
            updater);
    }

    @Override
    public Paginable<Specs> querySpecsPage(int start, int limit,
            Specs condition) {
        return specsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Specs> querySpecsList(Specs condition) {
        return specsBO.querySpecsList(condition);
    }

    @Override
    public Specs getSpecs(String code) {
        return specsBO.getSpecs(code);
    }

    @Override
    public Specs getSpecsByLevel(String code, String level) {
        Specs data = specsBO.getSpecs(code);
        AgentPrice price = agentPriceBO.getPriceByLevel(data.getCode(),
            StringValidater.toInteger(level));
        Product product = productBO.getProduct(data.getProductCode());
        data.setPrice(price);
        data.setProduct(product);
        return data;
    }

}
