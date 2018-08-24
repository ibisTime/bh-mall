package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAgentPriceDAO;
import com.bh.mall.dao.ISpecsDAO;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Specs;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.dto.req.XN627547Req;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.ESpecsLogType;
import com.bh.mall.exception.BizException;

@Component
public class SpecsBOImpl extends PaginableBOImpl<Specs> implements ISpecsBO {

    @Autowired
    private ISpecsDAO specsDAO;

    @Autowired
    private IAgentPriceDAO agentPriceDAO;

    @Autowired
    private IAgentPriceBO agentPriceBO;

    @Autowired
    private ISpecsLogBO specsLogBO;

    @Override
    public void saveSpecsList(String productCode, String productName,
            List<XN627546Req> specList, String updater) {
        // 添加产品规格
        for (XN627546Req productSpec : specList) {
            if (StringUtils.isBlank(productSpec.getCode())) {
                Specs data = new Specs();
                String psCode = OrderNoGenerater
                    .generate(EGeneratePrefix.ProductSpecs.getCode());
                data.setCode(psCode);
                data.setProductCode(productCode);
                data.setName(productSpec.getName());
                data.setStockNumber(
                    StringValidater.toInteger(productSpec.getStockNumber()));

                data.setNumber(
                    StringValidater.toInteger(productSpec.getNumber()));
                data.setWeight(
                    StringValidater.toDouble(productSpec.getWeight()));
                data.setIsUpgradeOrder(productSpec.getIsSjOrder());
                data.setIsImpowerOrder(productSpec.getIsSqOrder());
                data.setIsSingle(productSpec.getIsSingle());

                if (EBoolean.YES.getCode().equals(productSpec.getIsSingle())
                        && EBoolean.NO.getCode()
                            .equals(productSpec.getSingleNumber())) {
                    throw new BizException("xn00000", "拆单数量不能为零");
                }
                if (StringUtils.isNotBlank(productSpec.getSingleNumber())) {
                    data.setSingleNumber(StringValidater
                        .toInteger(productSpec.getSingleNumber()));
                }

                data.setIsNormalOrder(productSpec.getIsNormalOrder());

                specsLogBO.saveSpecsLog(productCode, productName, data,
                    ESpecsLogType.Input.getCode(), 0, updater);
                specsDAO.insert(data);

                List<XN627547Req> specsPriceList = productSpec
                    .getSpecsPriceList();
                if (CollectionUtils.isEmpty(specsPriceList)) {
                    throw new BizException("xn00000", "规格价格不能为空");
                }

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
                        StringValidater.toInteger(specsPrice.getStartNumber()));
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
    public void removeSpecsByProduct(String productCode) {
        if (StringUtils.isNotBlank(productCode)) {
            Specs data = new Specs();
            data.setProductCode(productCode);

            AgentPrice pspData = new AgentPrice();
            pspData.setSpecsCode(productCode);
            specsDAO.deleteByProdut(data);
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
        if (psCode.equals(psReq.getRefCode())) {
            throw new BizException("xn000000", "同一个规格之间不能关联哦！");
        }
        data.setCode(psCode);
        if (EBoolean.YES.getCode().equals(psReq.getIsSingle())
                && EBoolean.NO.getCode().equals(psReq.getSingleNumber())) {
            throw new BizException("xn00000", "拆单数量不能为零");
        }
        if (StringUtils.isNotBlank(psReq.getSingleNumber())) {
            data.setSingleNumber(
                StringValidater.toInteger(psReq.getSingleNumber()));
        }
        data.setRefCode(psReq.getRefCode());

        data.setProductCode(code);
        data.setName(psReq.getName());
        data.setNumber(StringValidater.toInteger(psReq.getNumber()));
        data.setWeight(StringValidater.toDouble(psReq.getWeight()));
        data.setIsUpgradeOrder(psReq.getIsSjOrder());

        data.setIsImpowerOrder(psReq.getIsSqOrder());
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

            agentPrice.setPrice(StringValidater.toLong(specsPrice.getPrice()));
            agentPrice.setMinNumber(
                StringValidater.toInteger(specsPrice.getMinNumber()));
            agentPrice.setStartNumber(
                StringValidater.toInteger(specsPrice.getStartNumber()));

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
        if (psData.getCode().equals(psReq.getRefCode())) {
            throw new BizException("xn000000", "同一个规格之间不能关联哦！");
        }
        psData.setIsSingle(psReq.getIsSingle());
        if (EBoolean.YES.getCode().equals(psReq.getIsSingle())
                && EBoolean.NO.getCode().equals(psReq.getSingleNumber())) {
            throw new BizException("xn00000", "拆单数量不能为零");
        }
        if (StringUtils.isNotBlank(psReq.getSingleNumber())) {
            psData.setSingleNumber(
                StringValidater.toInteger(psReq.getSingleNumber()));
        }

        psData.setName(psReq.getName());
        psData.setNumber(StringValidater.toInteger(psReq.getNumber()));
        psData.setWeight(StringValidater.toDouble(psReq.getWeight()));
        psData.setIsUpgradeOrder(psReq.getIsSjOrder());

        psData.setIsImpowerOrder(psReq.getIsSqOrder());
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
            pspData.setStartNumber(
                StringValidater.toInteger(specsPrice.getStartNumber()));

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
                number = getMinSpecsNumber(data, number);
            } else {
                // 各规格之前没有关联
                number = data.getNumber();
            }
        }
        return number;
    }

    private Integer getMinSpecsNumber(Specs data, int number) {
        if (StringUtils.isNotBlank(data.getRefCode())) {
            Specs productSpecs = this.getSpecs(data.getRefCode());
            number = number * productSpecs.getNumber();
            getMinSpecsNumber(productSpecs, number);
        }
        return number;
    }

    @Override
    public void removeSpecs(Specs data) {
        specsDAO.delete(data);
    }

    @Override
    @Transactional
    public void refreshRepertory(String productName, Specs data, String type,
            Integer number, String updater) {
        specsLogBO.saveSpecsLog(productName, data.getProductCode(), data, type,
            number, updater);

        Integer nowNumber = data.getStockNumber() + number;
        if (0 > nowNumber) {
            throw new BizException("xn00000", "该规格的数量不足");
        }
        data.setStockNumber(nowNumber);
        specsDAO.updateRepertory(data);
    }

}
