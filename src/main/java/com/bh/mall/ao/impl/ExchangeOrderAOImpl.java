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
import com.bh.mall.bo.ISYSUserBO;
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
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChangeProductStatus;
import com.bh.mall.enums.ESpecsLogType;
import com.bh.mall.enums.EWareLogType;
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

    @Autowired
    ISYSUserBO sysUserBO;

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

        data.setChangeProductCode(exchangeProduct.getCode());
        data.setChangeProductName(exchangeProduct.getName());
        data.setExchangeSpecsName(changeSpecs.getName());
        data.setChangeSpecsCode(changeSpecs.getCode());
        data.setCanChangeQuantity(canChangeQuantity);
        data.setChangePrice(changeSpecsPrice.getChangePrice());

        data.setApplyUser(req.getApplyUser());
        data.setRealName(uData.getRealName());
        data.setLevel(uData.getLevel());
        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());

        data.setStatus(EChangeProductStatus.TO_CHANGE.getCode());
        exchangeOrderBO.saveChangeOrder(data);

        wareBO.changeWare(whData.getCode(), EWareLogType.CHANGE.getCode(),
            -StringValidater.toInteger(req.getQuantity()), EBizType.AJ_YCZH,
            "[" + product.getName() + "]申请置换为[" + exchangeProduct.getName()
                    + "]",
            code);
        return code;

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

        for (ExchangeOrder data : list) {
            // 补充下单代理的信息
            Agent agent = agentBO.getAgent(data.getApplyUser());
            data.setAgent(agent);
            if (StringUtils.isNotBlank(data.getApprover())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getApprover());
                data.setApproveName(sysUser.getRealName());
            }
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

        for (ExchangeOrder data : list) {
            Agent agent = agentBO.getAgent(data.getApplyUser());
            data.setAgent(agent);
            if (StringUtils.isNotBlank(data.getApprover())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getApprover());
                data.setApproveName(sysUser.getRealName());
            }
        }

        return list;
    }

    @Override
    public ExchangeOrder getExchangeOrder(String code) {
        ExchangeOrder data = exchangeOrderBO.getChangeOrder(code);
        Agent agent = agentBO.getAgent(data.getApplyUser());
        data.setAgent(agent);
        if (StringUtils.isNotBlank(data.getApprover())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getApprover());
            data.setApproveName(sysUser.getRealName());
        }
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
        Product changeData = productBO.getProduct(data.getChangeProductCode());
        Specs changeSpecs = specsBO.getSpecs(data.getSpecsCode());

        if (0 < (changeData.getRealNumber() - data.getCanChangeQuantity())) {
            throw new BizException("xn00000", "产品[" + changeData.getName() + "-"
                    + changeSpecs.getName() + "]的数量不足");
        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setChangePrice(StringValidater.toLong(changePrice));
        data.setCanChangeQuantity(canChangeQuantity);

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
            // 产品
            Specs specs = specsBO.getSpecs(data.getSpecsCode());
            specsBO.refreshRepertory(data.getProductName(), specs,
                ESpecsLogType.ChangeProduct.getCode(), data.getQuantity(),
                approver);

            // 要置换的产品
            Product changeData = productBO
                .getProduct(data.getChangeProductCode());
            Specs changeSpecs = specsBO.getSpecs(data.getSpecsCode());

            if (0 < (changeData.getRealNumber()
                    - data.getCanChangeQuantity())) {
                throw new BizException("xn00000", "产品[" + changeData.getName()
                        + "-" + changeSpecs.getName() + "]的数量不足");
            }

            // 保存要置换的产品库存记录
            specsBO.refreshRepertory(changeData.getName(), specs,
                ESpecsLogType.ChangeProduct.getCode(),
                -data.getCanChangeQuantity(), approver);

            Agent agent = agentBO.getAgent(data.getApplyUser());
            wareBO.buyWare(data, agent);

        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setStatus(status);
        exchangeOrderBO.approveChange(data);
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
        cpData.setCanChangeQuantity(canChangeQuantity);

        return cpData;
    }

}
