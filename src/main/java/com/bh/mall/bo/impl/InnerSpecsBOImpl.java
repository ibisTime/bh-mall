package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAgentPriceDAO;
import com.bh.mall.dao.IInnerSpecsDAO;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627547Req;
import com.bh.mall.dto.req.XN627890Req;

@Component
public class InnerSpecsBOImpl extends PaginableBOImpl<InnerSpecs>
        implements IInnerSpecsBO {

    @Autowired
    private IInnerSpecsDAO innerSpecsDAO;

    @Autowired
    private IAgentPriceDAO agentPriceDAO;

    @Autowired
    private IAgentPriceBO agentPriceBO;

    @Override
    public boolean isInnerSpecsExist(String code) {
        InnerSpecs innerSpecs = new InnerSpecs();
        innerSpecs.setCode(code);
        if (innerSpecsDAO.selectTotalCount(innerSpecs) >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public void saveInnerSpecsList(String code, List<XN627890Req> specList,
            String updater) {
        // 添加产品规格
        for (XN627890Req productSpec : specList) {
            if (StringUtils.isBlank(productSpec.getCode())) {
                InnerSpecs data = new InnerSpecs();
                String psCode = OrderNoGenerater
                    .generate(EGeneratePrefix.ProductSpecs.getCode());
                data.setCode(psCode);
                data.setInnerProductCode(code);
                data.setName(productSpec.getName());

                data.setNumber(
                    StringValidater.toInteger(productSpec.getNumber()));
                data.setWeight(
                    StringValidater.toDouble(productSpec.getWeight()));
                data.setIsUpgradeOrder(productSpec.getIsUpgradeOrder());
                data.setIsImpowerOrder(productSpec.getIsImpowerOrder());
                data.setIsNormalOrder(productSpec.getIsNormalOrder());

                innerSpecsDAO.insert(data);

                List<XN627547Req> specsPriceList = productSpec
                    .getSpecsPriceList();

                // 新增价格体系
                for (XN627547Req specsPrice : specsPriceList) {
                    AgentPrice agentPrice = new AgentPrice();
                    String pspCode = OrderNoGenerater
                        .generate(EGeneratePrefix.ProductSpecsPrice.getCode());
                    agentPrice.setCode(pspCode);
                    agentPrice.setSpecsCode(psCode);
                    agentPrice.setLevel(
                        StringValidater.toInteger(specsPrice.getLevel()));

                    agentPrice.setMinNumber(
                        StringValidater.toInteger(specsPrice.getMinNumber()));
                    agentPrice.setStartNumber(
                        StringValidater.toInteger(specsPrice.getMinQuantity()));
                    agentPrice.setPrice(
                        StringValidater.toLong(specsPrice.getPrice()));
                    agentPrice.setChangePrice(
                        StringValidater.toLong(specsPrice.getChangePrice()));
                    agentPrice.setIsBuy(specsPrice.getIsBuy());

                    agentPrice.setDailyNumber(
                        StringValidater.toInteger(specsPrice.getDailyNumber()));
                    agentPrice.setWeeklyNumber(StringValidater
                        .toInteger(specsPrice.getWeeklyNumber()));
                    agentPrice.setMonthlyNumber(StringValidater
                        .toInteger(specsPrice.getMonthlyNumber()));
                    agentPriceDAO.insert(agentPrice);
                }
            }
        }

    }

    @Override
    public void saveInnerSpecs(String code, XN627890Req innerpsReq) {
        InnerSpecs data = new InnerSpecs();
        String psCode = OrderNoGenerater
            .generate(EGeneratePrefix.ProductSpecs.getCode());
        data.setCode(psCode);
        data.setIsSingle(innerpsReq.getIsSingle());
        data.setSingleNumber(
            StringValidater.toInteger(innerpsReq.getSingleNumber()));
        data.setRefCode(innerpsReq.getRefCode());

        data.setInnerProductCode(code);
        data.setName(innerpsReq.getName());
        data.setNumber(StringValidater.toInteger(innerpsReq.getNumber()));
        data.setWeight(StringValidater.toDouble(innerpsReq.getWeight()));
        data.setIsUpgradeOrder(innerpsReq.getIsUpgradeOrder());

        data.setIsImpowerOrder(innerpsReq.getIsImpowerOrder());
        data.setIsNormalOrder(innerpsReq.getIsNormalOrder());
        innerSpecsDAO.insert(data);

        List<XN627547Req> specsPriceList = innerpsReq.getSpecsPriceList();
        // 新增价格体系
        for (XN627547Req specsPrice : specsPriceList) {
            AgentPrice agentPrice = new AgentPrice();
            String pspCode = OrderNoGenerater
                .generate(EGeneratePrefix.ProductSpecsPrice.getCode());
            agentPrice.setCode(pspCode);
            agentPrice.setSpecsCode(psCode);
            agentPrice
                .setLevel(StringValidater.toInteger(specsPrice.getLevel()));

            agentPrice.setPrice(StringValidater.toLong(specsPrice.getPrice()));
            agentPrice.setMinNumber(
                StringValidater.toInteger(specsPrice.getMinNumber()));
            agentPrice.setStartNumber(
                StringValidater.toInteger(specsPrice.getMinQuantity()));

            agentPrice.setChangePrice(
                StringValidater.toLong(specsPrice.getChangePrice()));
            agentPrice.setIsBuy(specsPrice.getIsBuy());
            agentPrice.setDailyNumber(
                StringValidater.toInteger(specsPrice.getDailyNumber()));
            agentPrice.setWeeklyNumber(
                StringValidater.toInteger(specsPrice.getWeeklyNumber()));
            agentPrice.setMonthlyNumber(
                StringValidater.toInteger(specsPrice.getMonthlyNumber()));
            agentPriceDAO.insert(agentPrice);
        }
    }

    @Override
    public void removeInnerSpecs(InnerSpecs data) {

        innerSpecsDAO.delete(data);

    }

    @Override
    public void refreshInnerSpecs(XN627890Req innerpsReq,
            List<XN627547Req> innerSpecsPriceList) {
        InnerSpecs psData = this.getInnerSpecs(innerpsReq.getCode());
        psData.setIsSingle(innerpsReq.getIsSingle());
        psData.setSingleNumber(
            StringValidater.toInteger(innerpsReq.getSingleNumber()));
        psData.setRefCode(innerpsReq.getRefCode());

        psData.setName(innerpsReq.getName());
        psData.setNumber(StringValidater.toInteger(innerpsReq.getNumber()));
        psData.setWeight(StringValidater.toDouble(innerpsReq.getWeight()));
        psData.setIsUpgradeOrder(innerpsReq.getIsUpgradeOrder());

        psData.setIsImpowerOrder(innerpsReq.getIsImpowerOrder());
        psData.setIsNormalOrder(innerpsReq.getIsNormalOrder());

        innerSpecsDAO.update(psData);
        List<XN627547Req> pspList = innerSpecsPriceList;

        for (XN627547Req specsPrice : pspList) {
            AgentPrice pspData = agentPriceBO
                .getAgentPrice(specsPrice.getCode());
            pspData.setCode(specsPrice.getCode());
            pspData.setPrice(StringValidater.toLong(specsPrice.getPrice()));
            pspData.setChangePrice(
                StringValidater.toLong(specsPrice.getChangePrice()));
            pspData.setIsBuy(specsPrice.getIsBuy());

            pspData.setMinNumber(
                StringValidater.toInteger(specsPrice.getMinNumber()));
            pspData.setStartNumber(
                StringValidater.toInteger(specsPrice.getMinQuantity()));

            pspData.setDailyNumber(
                StringValidater.toInteger(specsPrice.getDailyNumber()));
            pspData.setWeeklyNumber(
                StringValidater.toInteger(specsPrice.getWeeklyNumber()));
            pspData.setMonthlyNumber(
                StringValidater.toInteger(specsPrice.getMonthlyNumber()));
            agentPriceDAO.update(pspData);
        }
    }

    @Override
    public List<InnerSpecs> queryInnerSpecsList(InnerSpecs data) {
        return innerSpecsDAO.selectList(data);
    }

    @Override
    public InnerSpecs getInnerSpecs(String code) {
        InnerSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            InnerSpecs condition = new InnerSpecs();
            condition.setCode(code);
            data = innerSpecsDAO.select(condition);
        }
        return data;
    }
}
