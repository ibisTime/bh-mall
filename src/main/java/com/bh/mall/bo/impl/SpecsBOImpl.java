package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.ISpecsDAO;
import com.bh.mall.dao.IAgentPriceDAO;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.dto.req.XN627547Req;
import com.bh.mall.exception.BizException;

@Component
public class SpecsBOImpl extends PaginableBOImpl<Specs>
        implements ISpecsBO {

    @Autowired
    private ISpecsDAO specsDAO;

    @Autowired
    private IAgentPriceDAO agentPriceDAO;

    @Autowired
    private IAgentPriceBO agentPriceBO;

    @Override
    public void saveSpecsList(String code, List<XN627546Req> specList) {
        // 添加产品规格
        for (XN627546Req productSpec : specList) {
            if (StringUtils.isBlank(productSpec.getCode())) {
                Specs data = new Specs();
                String psCode = OrderNoGenerater
                    .generate(EGeneratePrefix.ProductSpecs.getCode());
                data.setCode(psCode);
                data.setProductCode(code);
                data.setName(productSpec.getName());

                data.setNumber(
                    StringValidater.toInteger(productSpec.getNumber()));
                data.setWeight(
                    StringValidater.toDouble(productSpec.getWeight()));
                data.setIsUpgradeOrder(productSpec.getIsUpgradeOrder());
                data.setIsImpowerOrder(productSpec.getIsImpowerOrder());
                data.setIsNormalOrder(productSpec.getIsNormalOrder());

                specsDAO.insert(data);

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
                    agentPrice.setMinQuantity(
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
    public void removeSpecs(String productCode) {
        if (StringUtils.isNotBlank(productCode)) {
            Specs data = new Specs();
            data.setProductCode(productCode);

            AgentPrice pspData = new AgentPrice();
            pspData.setSpecsCode(productCode);
            specsDAO.delete(data);
            agentPriceDAO.delete(pspData);
        }
    }

    @Override
    public List<Specs> querySpecsList(Specs condition) {
        return specsDAO.selectList(condition);
    }

    @Override
    public Specs getSpecs(String code) {
        Specs data = null;
        if (StringUtils.isNotBlank(code)) {
            Specs condition = new Specs();
            condition.setCode(code);
            data = specsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "产品规格不存在");
            }
        }
        return data;
    }

    @Override
    public void saveSpecs(String code, XN627546Req psReq) {
        Specs data = new Specs();
        String psCode = OrderNoGenerater
            .generate(EGeneratePrefix.ProductSpecs.getCode());
        data.setCode(psCode);
        data.setIsSingle(psReq.getIsSingle());
        data.setSingleNumber(
            StringValidater.toInteger(psReq.getSingleNumber()));
        data.setRefCode(psReq.getRefCode());

        data.setProductCode(code);
        data.setName(psReq.getName());
        data.setNumber(StringValidater.toInteger(psReq.getNumber()));
        data.setWeight(StringValidater.toDouble(psReq.getWeight()));
        data.setIsUpgradeOrder(psReq.getIsUpgradeOrder());

        data.setIsImpowerOrder(psReq.getIsImpowerOrder());
        data.setIsNormalOrder(psReq.getIsNormalOrder());
        specsDAO.insert(data);

        List<XN627547Req> specsPriceList = psReq.getSpecsPriceList();
        // 新增价格体系
        for (XN627547Req specsPrice : specsPriceList) {
            AgentPrice agentPrice = new AgentPrice();
            String pspCode = OrderNoGenerater
                .generate(EGeneratePrefix.ProductSpecsPrice.getCode());
            agentPrice.setCode(pspCode);
            agentPrice.setSpecsCode(psCode);
            agentPrice
                .setLevel(StringValidater.toInteger(specsPrice.getLevel()));

            agentPrice
                .setPrice(StringValidater.toLong(specsPrice.getPrice()));
            agentPrice.setMinNumber(
                StringValidater.toInteger(specsPrice.getMinNumber()));
            agentPrice.setMinQuantity(
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
    public List<Specs> getSpecsByProduct(String productCode) {
        Specs condition = new Specs();
        condition.setProductCode(productCode);
        return specsDAO.selectList(condition);
    }

    @Override
    public void refreshSpecs(XN627546Req psReq,
            List<XN627547Req> specsPriceList) {
        Specs psData = this.getSpecs(psReq.getCode());
        psData.setIsSingle(psReq.getIsSingle());
        psData.setSingleNumber(
            StringValidater.toInteger(psReq.getSingleNumber()));
        psData.setRefCode(psReq.getRefCode());

        psData.setName(psReq.getName());
        psData.setNumber(StringValidater.toInteger(psReq.getNumber()));
        psData.setWeight(StringValidater.toDouble(psReq.getWeight()));
        psData.setIsUpgradeOrder(psReq.getIsUpgradeOrder());

        psData.setIsImpowerOrder(psReq.getIsImpowerOrder());
        psData.setIsNormalOrder(psReq.getIsNormalOrder());

        specsDAO.update(psData);
        List<XN627547Req> pspList = specsPriceList;

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
            pspData.setMinQuantity(
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
    public Integer getMinSpecsNumber(String productCode) {
        Specs condition = new Specs();
        condition.setProductCode(productCode);
        List<Specs> list = specsDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "该产品的规格不存在");
        }

        // 只要一个规格
        Integer number = 0;
        if (list.size() == 1) {
            Specs specs = list.get(0);
            return specs.getNumber();
        }

        // 两种以及以上规格
        for (Specs data : list) {
            // 规格之间有关联
            if (StringUtils.isNotBlank(data.getRefCode())) {
                Specs productSpecs = this
                    .getSpecs(data.getRefCode());
                number = getMinSpecsNumber(productSpecs, number);
            } else {
                // 各规格之前没有关联
                number = data.getNumber();
            }
        }
        return number;
    }

    private Integer getMinSpecsNumber(Specs data, int number) {
        Specs productSpecs = this.getSpecs(data.getRefCode());
        number = number * productSpecs.getNumber();
        if (StringUtils.isNotBlank(productSpecs.getRefCode())) {
            getMinSpecsNumber(productSpecs, number);
        }
        return number;
    }

}