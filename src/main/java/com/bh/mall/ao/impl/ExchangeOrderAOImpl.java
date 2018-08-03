package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IExchangeOrderAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.IExchangeOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.IWareLogBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.ExchangeOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.enums.EAccountStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChangeProductStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;

@Service
public class ExchangeOrderAOImpl implements IExchangeOrderAO {

    @Autowired
    IExchangeOrderBO exchangeOrderBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IWareBO wareBO;

    @Autowired
    IWareLogBO wareLogBO;

    @Autowired
    ISpecsBO specsBO;

    @Autowired
    IProductBO productBO;

    @Autowired
    IAgentPriceBO agentPriceBO;

    @Autowired
    ISpecsLogBO specsLogBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IChargeBO chargeBO;

    @Override
    public String addExchangeOrder(XN627790Req req) {
        Agent uData = agentBO.getAgent(req.getApplyUser());
        Ware whData = wareBO.getWareByProductSpec(uData.getUserId(),
            req.getProductSpecsCode());
        if (whData == null) {
            throw new BizException("xn000", "您的仓库中没有该规格的产品");
        }
        if (whData.getQuantity() < StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn000", "该规格的产品数量不足");
        }

        Specs specs = specsBO.getSpecs(req.getProductSpecsCode());
        Product product = productBO.getProduct(specs.getProductCode());

        AgentPrice specsPrice = agentPriceBO.getPriceByLevel(specs.getCode(),
            uData.getLevel());

        Specs changeSpecs = specsBO.getSpecs(req.getChangeSpecsCode());
        Product exchangeProduct = productBO
            .getProduct(changeSpecs.getProductCode());
        AgentPrice changeSpecsPrice = agentPriceBO
            .getPriceByLevel(changeSpecs.getCode(), uData.getLevel());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ChangeProduct.getCode());

        ExchangeOrder data = new ExchangeOrder();
        data.setCode(code);
        data.setProductCode(product.getCode());
        data.setProductName(product.getName());
        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());

        data.setPrice(specsPrice.getPrice());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        Long amount = AmountUtil.eraseLiUp(specsPrice.getPrice()
                * StringValidater.toInteger(req.getQuantity()));
        data.setAmount(amount);
        int canChangeQuantity = 0;
        if (changeSpecsPrice.getChangePrice() == null
                || changeSpecsPrice.getChangePrice() == 0) {
            throw new BizException("xn000", "该产品的换货价为空");
        } else {
            canChangeQuantity = (int) (amount
                    / changeSpecsPrice.getChangePrice());
        }

        data.setExchangeProductCode(exchangeProduct.getCode());
        data.setExchangeProductName(exchangeProduct.getName());
        data.setExchangeSpecsName(changeSpecs.getName());
        data.setExchangeSpecsCode(changeSpecs.getCode());
        data.setCanExchangeQuantity(canChangeQuantity);

        data.setApplyUser(req.getApplyUser());
        data.setRealName(uData.getRealName());
        data.setLevel(uData.getLevel());
        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());

        data.setStatus(EChangeProductStatus.TO_CHANGE.getCode());
        exchangeOrderBO.saveChangeOrder(data);

        wareBO.changeWare(whData.getCode(),
            -StringValidater.toInteger(req.getQuantity()), EBizType.AJ_YCZH,
            "[" + product.getName() + "]申请置换为[" + exchangeProduct.getName()
                    + "]",
            code);
        return code;

    }

    @Override
    public int editExchangeOrder(ExchangeOrder data) {
        return exchangeOrderBO.refreshChangeOrder(data);

    }

    @Override
    public Paginable<ExchangeOrder> queryExchangeOrderPage(int start, int limit,
            ExchangeOrder condition) {
        if (condition.getApplyStartDatetime() != null
                && condition.getApplyEndDatetime() != null
                && condition.getApplyStartDatetime()
                    .after(condition.getApplyEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        long totalCount = exchangeOrderBO.getTotalCount(condition);
        Page<ExchangeOrder> page = new Page<>(start, limit, totalCount);
        List<ExchangeOrder> list = exchangeOrderBO.queryChangeOrderPage(
            page.getStart(), page.getPageSize(), condition);

        for (ExchangeOrder ecchangeProduct : list) {
            String approveName = this.getName(ecchangeProduct.getApprover());
            ecchangeProduct.setApproveName(approveName);
            // 补充下单代理的信息
            Agent agent = agentBO.getAgent(ecchangeProduct.getApplyUser());
            ecchangeProduct.setAgent(agent);
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<ExchangeOrder> queryExchangeOrderList(ExchangeOrder condition) {
        if (condition.getApplyStartDatetime() != null
                && condition.getApplyEndDatetime() != null
                && condition.getApplyStartDatetime()
                    .after(condition.getApplyEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<ExchangeOrder> list = exchangeOrderBO
            .queryChangeOrderList(condition);

        for (ExchangeOrder changeProduct : list) {
            String approveName = this.getName(changeProduct.getApprover());
            changeProduct.setApproveName(approveName);
            Agent agent = agentBO.getAgent(changeProduct.getApplyUser());
            changeProduct.setAgent(agent);
        }

        return list;
    }

    @Override
    public ExchangeOrder getExchangeOrder(String code) {
        ExchangeOrder data = exchangeOrderBO.getChangeOrder(code);
        String approveName = this.getName(data.getApprover());
        data.setApproveName(approveName);
        Agent agent = agentBO.getAgent(data.getApplyUser());
        data.setAgent(agent);
        return data;
    }

    @Override
    public void editExchangePrice(String code, String changePrice,
            String approver, String approveNote) {

        ExchangeOrder data = exchangeOrderBO.getChangeOrder(code);
        if (!EChangeProductStatus.TO_CHANGE.getCode()
            .equals(data.getStatus())) {
            throw new BizException("xn0000", "该置换单已经审核完成,无需修改换货价");
        }
        if (StringValidater.toLong(changePrice) == 0) {
            throw new BizException("xn000", "该产品的换货价不能等于0");
        }

        int canChangeQuantity = (int) (data.getAmount()
                / StringValidater.toLong(changePrice));
        Ware whData = wareBO.getWareByProductSpec(data.getApplyUser(),
            data.getProductSpecsCode());

        String logCode = wareLogBO.refreshChangePrice(data, whData,
            StringValidater.toLong(changePrice), canChangeQuantity,
            data.getStatus(), "[" + data.getExchangeProductName() + "]的换货价由["
                    + data.getExchangePrice() + "]变为[" + changePrice + "]");
        whData.setLastChangeCode(logCode);
        wareBO.refreshLogCode(whData);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setExchangePrice(StringValidater.toLong(changePrice));

        data.setCanExchangeQuantity(canChangeQuantity);

        exchangeOrderBO.refreshChangePrice(data);
    }

    @Override
    public void approveExchange(String code, String approver,
            String approveNote, String result) {
        ExchangeOrder data = exchangeOrderBO.getChangeOrder(code);
        if (!EChangeProductStatus.TO_CHANGE.getCode()
            .equals(data.getStatus())) {
            throw new BizException("xn000", "该置换单未处于待审核状态");
        }
        String status = EChangeProductStatus.THROUGH_NO.getCode();
        // 审核通过
        if (EBoolean.YES.getCode().equals(result)) {
            status = EChangeProductStatus.THROUGH_YES.getCode();
            Product pData = productBO.getProduct(data.getProductCode());
            Product changeData = productBO
                .getProduct(data.getExchangeProductCode());
            Specs psData = specsBO.getSpecs(data.getProductSpecsCode());

            int quantity = data.getQuantity() * psData.getNumber();
            pData.setRealNumber(pData.getRealNumber() - quantity);
            productBO.refreshRealNumber(pData);

            specsLogBO.saveExchangeProductLog(pData,
                EProductLogType.ChangeProduct.getCode(), pData.getRealNumber(),
                quantity, approver);

            // 云仓新增产品
            Ware whData = wareBO.getWareByProductSpec(data.getApplyUser(),
                data.getExchangeSpecsCode());
            if (whData == null) {
                String whCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Ware.getCode());
                Ware ware = new Ware();
                ware.setCode(whCode);
                ware.setProductCode(data.getExchangeProductCode());
                ware.setProductName(data.getExchangeProductName());
                ware.setSpecsCode(data.getExchangeSpecsCode());
                ware.setSpecsName(data.getExchangeSpecsName());

                ware.setCurrency(ECurrency.YC_CNY.getCode());
                ware.setUserId(data.getApplyUser());
                ware.setRealName(data.getRealName());
                ware.setCreateDatetime(new Date());
                AgentPrice pspData = agentPriceBO.getPriceByLevel(
                    data.getExchangeSpecsCode(), data.getLevel());

                ware.setPrice(pspData.getPrice());

                ware.setQuantity(data.getExcanChangeQuantity());
                Long amount = data.getExcanChangeQuantity()
                        * pspData.getPrice();
                ware.setAmount(amount);
                ware.setLastChangeCode(data.getCode());
                ware.setStatus(EAccountStatus.NORMAL.getCode());
                ware.setCompanyCode(ESystemCode.BH.getCode());
                ware.setSystemCode(ESystemCode.BH.getCode());
                wareBO.saveWare(ware, data.getQuantity(), EBizType.AJ_YCZH,
                    "[" + data.getProductName() + "]置换为["
                            + data.getExchangeProductName() + "]",
                    data.getCode());
            } else {
                wareBO.changeWare(whData.getCode(), data.getQuantity(),
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                    data.getCode());
            }

        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setStatus(status);
        exchangeOrderBO.approveChange(data);
    }

    private String getName(String agentCode) {

        if (StringUtils.isBlank(agentCode)) {
            return null;
        }
        String name = null;
        Agent data = agentBO.getAgent(agentCode);
        if (data != null) {
            name = data.getRealName();
            if (EUserKind.Plat.getCode().equals(data.getKind())
                    && StringUtils.isBlank(data.getRealName())) {
                name = data.getLoginName();
            }
        }
        return name;
    }

    @Override
    public ExchangeOrder getExchangeOrderMessage(XN627790Req req) {
        Agent uData = agentBO.getAgent(req.getApplyUser());
        Ware whData = wareBO.getWareByProductSpec(uData.getUserId(),
            req.getProductSpecsCode());
        if (whData == null) {
            throw new BizException("xn000", "您的云仓中该规格的产品不存在");
        }
        if (whData.getQuantity() < StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn000", "该规格的产品数量不足");
        }

        Specs specs = specsBO.getSpecs(req.getProductSpecsCode());

        AgentPrice specsPrice = agentPriceBO.getPriceByLevel(specs.getCode(),
            uData.getLevel());

        Specs changeSpecs = specsBO.getSpecs(req.getChangeSpecsCode());
        AgentPrice changeSpecsPrice = agentPriceBO
            .getPriceByLevel(changeSpecs.getCode(), uData.getLevel());

        ExchangeOrder cpData = new ExchangeOrder();
        cpData.setPrice(specsPrice.getPrice());
        cpData.setQuantity(StringValidater.toInteger(req.getQuantity()));
        Long amount = AmountUtil.eraseLiUp(specsPrice.getPrice()
                * StringValidater.toInteger(req.getQuantity()));
        cpData.setAmount(amount);
        int canChangeQuantity = 0;
        if (changeSpecsPrice.getChangePrice() == null
                || changeSpecsPrice.getChangePrice() == 0) {
            throw new BizException("xn000", "该产品的换货价为空");
        } else {
            canChangeQuantity = (int) (amount
                    / changeSpecsPrice.getChangePrice());
        }
        cpData.setCanExchangeQuantity(canChangeQuantity);

        return cpData;
    }

}
