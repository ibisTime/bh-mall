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
            List<XN627546Req> specList, String updater, String remark) {
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
                data.setIsSjOrder(productSpec.getIsSjOrder());
                data.setIsSqOrder(productSpec.getIsSqOrder());
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
                    ESpecsLogType.Input.getCode(), 0, updater, remark);
                specsDAO.insert(data);

                List<XN627547Req> specsPriceList = productSpec
                    .getSpecsPriceList();
                if (CollectionUtils.isEmpty(specsPriceList)) {
                    throw new BizException("xn00000", "规格价格不能为空");
                }

                // 保证所有等级都有价格
                if (specsPriceList.size() < 6) {
                    throw new BizException("xn00000", "请保证所有等级的价格都已添加");
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

        if (EBoolean.YES.getCode().equals(psReq.getIsSingle())
                && EBoolean.NO.getCode().equals(psReq.getSingleNumber())) {
            throw new BizException("xn00000", "拆单数量不能为零");
        }

        if (StringUtils.isNotBlank(psReq.getSingleNumber())) {
            data.setSingleNumber(
                StringValidater.toInteger(psReq.getSingleNumber()));
        }

        data.setCode(psCode);
        data.setIsSingle(psReq.getIsSingle());
        data.setRefCode(psReq.getRefCode());
        data.setProductCode(code);
        data.setName(psReq.getName());

        data.setStockNumber(StringValidater.toInteger(psReq.getStockNumber()));
        data.setNumber(StringValidater.toInteger(psReq.getNumber()));
        data.setWeight(StringValidater.toDouble(psReq.getWeight()));
        data.setIsSjOrder(psReq.getIsSjOrder());

        data.setIsSqOrder(psReq.getIsSqOrder());
        data.setIsNormalOrder(psReq.getIsNormalOrder());
        specsDAO.insert(data);

        List<XN627547Req> specsPriceList = psReq.getSpecsPriceList();
        // 保证所有等级都有价格
        if (specsPriceList.size() < 6) {
            throw new BizException("xn00000", "请保证所有等级的价格都已添加");
        }
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
        condition.setLevel(condition.getLevel());
        condition.setIsBuy(EBoolean.YES.getCode());
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

        if (EBoolean.YES.getCode().equals(psReq.getIsSingle())
                && EBoolean.NO.getCode().equals(psReq.getSingleNumber())) {
            throw new BizException("xn00000", "拆单数量不能为零");
        }
        if (StringUtils.isNotBlank(psReq.getSingleNumber())) {
            psData.setSingleNumber(
                StringValidater.toInteger(psReq.getSingleNumber()));
        }
        if (StringUtils.isNotBlank(psReq.getRefCode())
                && 1 == psData.getNumber()) {
            throw new BizException("xn00000", "数量为1的规格不能关联其他规格");
        }

        psData.setRefCode(psReq.getRefCode());
        psData.setIsSingle(psReq.getIsSingle());
        psData.setName(psReq.getName());
        psData.setNumber(StringValidater.toInteger(psReq.getNumber()));
        psData.setWeight(StringValidater.toDouble(psReq.getWeight()));

        psData
            .setStockNumber(StringValidater.toInteger(psReq.getStockNumber()));
        psData.setIsSjOrder(psReq.getIsSjOrder());
        psData.setIsSqOrder(psReq.getIsSqOrder());
        psData.setIsNormalOrder(psReq.getIsNormalOrder());
        specsDAO.update(psData);

        // 保证所有等级都有价格
        if (specsPriceList.size() < 6) {
            throw new BizException("xn00000", "请保证所有等级的价格都已添加");
        }

        for (XN627547Req specsPrice : specsPriceList) {
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
    public Integer getMinSpecsNumber(Specs data, int number) {
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
            Integer number, String updater, String remark) {
        specsLogBO.saveSpecsLog(data.getProductCode(), productName, data, type,
            number, updater, remark);

        Integer nowNumber = data.getStockNumber() + number;
        if (0 > nowNumber) {
            throw new BizException("xn00000", "该规格的数量不足");
        }
        data.setStockNumber(nowNumber);
        specsDAO.updateRepertory(data);
    }

    @Override
    public List<Specs> querySpecsListByB(Specs condition) {
        return specsDAO.selectSpecsListByB(condition);
    }

}
