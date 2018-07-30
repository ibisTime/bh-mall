package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IExchangeProductAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IExchangeProductBO;
import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.IWareLogBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.ExchangeProduct;
import com.bh.mall.domain.Charge;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.dto.res.XN627805Res;
import com.bh.mall.enums.EAccountStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChangeProductStatus;
import com.bh.mall.enums.EChargeStatus;
import com.bh.mall.enums.ECheckStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;

@Service
public class ExchangeProductAOImpl implements IExchangeProductAO {

    @Autowired
    IExchangeProductBO exchangeProductBO;

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
    IOrderBO orderBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IChargeBO chargeBO;

    @Override
    public String addChangeProduct(XN627790Req req) {
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

        Specs specs = specsBO
            .getProductSpecs(req.getProductSpecsCode());
        Product product = productBO.getProduct(specs.getProductCode());

        AgentPrice specsPrice = agentPriceBO
            .getPriceByLevel(specs.getCode(), uData.getLevel());

        Specs changeSpecs = specsBO
            .getProductSpecs(req.getChangeSpecsCode());
        Product exchangeProduct = productBO
            .getProduct(changeSpecs.getProductCode());
        AgentPrice changeSpecsPrice = agentPriceBO
            .getPriceByLevel(changeSpecs.getCode(), uData.getLevel());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ChangeProduct.getCode());

        ExchangeProduct data = new ExchangeProduct();
        data.setCode(code);
        data.setProductCode(product.getCode());
        data.setProductName(product.getName());
        data.setProductSpecsCode(specs.getCode());
        data.setProductSpecsName(specs.getName());

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
        data.setChangeSpecsName(changeSpecs.getName());
        data.setChangeSpecsCode(changeSpecs.getCode());
        data.setCanChangeQuantity(canChangeQuantity);

        data.setApplyUser(req.getApplyUser());
        data.setRealName(uData.getRealName());
        data.setLevel(uData.getLevel());
        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());

        data.setStatus(EChangeProductStatus.TO_CHANGE.getCode());
        exchangeProductBO.saveChangeProduct(data);

        wareBO.changeWare(whData.getCode(),
            -StringValidater.toInteger(req.getQuantity()), EBizType.AJ_YCZH,
            "[" + product.getName() + "]申请置换为[" + exchangeProduct.getName() + "]",
            code);
        return code;

    }

    @Override
    public int editChangeProduct(ExchangeProduct data) {
        return exchangeProductBO.refreshChangeProduct(data);

    }

    @Override
    public int dropChangeProduct(String code) {
        return exchangeProductBO.removeChangeProduct(code);

    }

    @Override
    public Paginable<ExchangeProduct> queryChangeProductPage(int start, int limit,
            ExchangeProduct condition) {
        if (condition.getApplyStartDatetime() != null
                && condition.getApplyEndDatetime() != null
                && condition.getApplyStartDatetime()
                    .after(condition.getApplyEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        long totalCount = exchangeProductBO.getTotalCount(condition);
        Page<ExchangeProduct> page = new Page<>(start, limit, totalCount);
        List<ExchangeProduct> list = exchangeProductBO.queryChangeProductPage(
            page.getStart(), page.getPageSize(), condition);

        for (ExchangeProduct changeProduct : list) {
            String approveName = this.getName(changeProduct.getApprover());
            changeProduct.setApproveName(approveName);
            // 补充下单代理的信息
            Agent agent = agentBO.getAgent(changeProduct.getApplyUser());
            changeProduct.setAgent(agent);
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<ExchangeProduct> queryChangeProductList(ExchangeProduct condition) {
        if (condition.getApplyStartDatetime() != null
                && condition.getApplyEndDatetime() != null
                && condition.getApplyStartDatetime()
                    .after(condition.getApplyEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<ExchangeProduct> list = exchangeProductBO
            .queryChangeProductList(condition);

        for (ExchangeProduct changeProduct : list) {
            String approveName = this.getName(changeProduct.getApprover());
            changeProduct.setApproveName(approveName);
            Agent agent = agentBO.getAgent(changeProduct.getApplyUser());
            changeProduct.setAgent(agent);
        }

        return list;
    }

    @Override
    public ExchangeProduct getChangeProduct(String code) {
        ExchangeProduct data = exchangeProductBO.getChangeProduct(code);
        String approveName = this.getName(data.getApprover());
        data.setApproveName(approveName);
        Agent agent = agentBO.getAgent(data.getApplyUser());
        data.setAgent(agent);
        return data;
    }

    @Override
    public void editChangePrice(String code, String changePrice,
            String approver, String approveNote) {

        ExchangeProduct data = exchangeProductBO.getChangeProduct(code);
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
            data.getStatus(), "[" + data.getChangeProductName() + "]的换货价由["
                    + data.getChangePrice() + "]变为[" + changePrice + "]");
        whData.setLastChangeCode(logCode);
        wareBO.refreshLogCode(whData);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setChangePrice(StringValidater.toLong(changePrice));

        data.setCanChangeQuantity(canChangeQuantity);

        exchangeProductBO.refreshChangePrice(data);
    }

    @Override
    public void approveChange(String code, String approver, String approveNote,
            String result) {
        ExchangeProduct data = exchangeProductBO.getChangeProduct(code);
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
                .getProduct(data.getChangeProductCode());
            Specs psData = specsBO
                .getProductSpecs(data.getProductSpecsCode());

            int quantity = data.getQuantity() * psData.getNumber();
            pData.setRealNumber(pData.getRealNumber() - quantity);
            productBO.refreshRealNumber(pData);

            specsLogBO.saveChangeProductLog(pData,
                EProductLogType.ChangeProduct.getCode(), pData.getRealNumber(),
                quantity, approver);
            specsLogBO.saveChangeProductLog(changeData,
                EProductLogType.ChangeProduct.getCode(), pData.getRealNumber(),
                -quantity, approver);

            // 云仓新增产品
            Ware whData = wareBO.getWareByProductSpec(data.getApplyUser(),
                data.getChangeSpecsCode());
            if (whData == null) {
                String whCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Ware.getCode());
                Ware ware = new Ware();
                ware.setCode(whCode);
                ware.setProductCode(data.getChangeProductCode());
                ware.setProductName(data.getChangeProductName());
                ware.setProductSpecsCode(data.getChangeSpecsCode());
                ware.setProductSpecsName(data.getChangeSpecsName());

                ware.setCurrency(ECurrency.YC_CNY.getCode());
                ware.setUserId(data.getApplyUser());
                ware.setRealName(data.getRealName());
                ware.setCreateDatetime(new Date());
                AgentPrice pspData = agentPriceBO.getPriceByLevel(
                    data.getChangeSpecsCode(), data.getLevel());

                ware.setPrice(pspData.getPrice());

                ware.setQuantity(data.getCanChangeQuantity());
                Long amount = data.getCanChangeQuantity() * pspData.getPrice();
                ware.setAmount(amount);
                ware.setLastChangeCode(data.getCode());
                ware.setStatus(EAccountStatus.NORMAL.getCode());
                ware.setCompanyCode(ESystemCode.BH.getCode());
                ware.setSystemCode(ESystemCode.BH.getCode());
                wareBO.saveWare(ware, data.getQuantity(), EBizType.AJ_YCZH,
                    "[" + data.getProductName() + "]置换为["
                            + data.getChangeProductName() + "]",
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
        exchangeProductBO.approveChange(data);
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
    public ExchangeProduct getChangeProductMessage(XN627790Req req) {
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

        Specs specs = specsBO
            .getProductSpecs(req.getProductSpecsCode());

        AgentPrice specsPrice = agentPriceBO
            .getPriceByLevel(specs.getCode(), uData.getLevel());

        Specs changeSpecs = specsBO
            .getProductSpecs(req.getChangeSpecsCode());
        AgentPrice changeSpecsPrice = agentPriceBO
            .getPriceByLevel(changeSpecs.getCode(), uData.getLevel());

        ExchangeProduct cpData = new ExchangeProduct();
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

    @Override
    public XN627805Res checkAmount(String userId) {
        XN627805Res res = new XN627805Res();
        Agent user = agentBO.getAgent(userId);
        // 门槛所需充值金额
        Long chargeAmount = 0L;
        String result = ECheckStatus.NORMAL.getCode();
        String isWare = EBoolean.YES.getCode();

        // 代理已通过审核
        if (null != user.getLevel() && 0 != user.getLevel()) {
            AgentLevel agent = agentLevelBO.getAgentByLevel(user.getLevel());
            AgentLevel impower = agentLevelBO.getAgentByLevel(user.getLevel());

            // 检查云仓红线
            Long whAmount = 0L;
            List<Ware> list = wareBO.getWareByUser(userId);
            for (Ware ware : list) {
                if (null != ware.getAmount()) {
                    whAmount = whAmount + ware.getAmount();
                }
            }

            // 检查开启云仓代理的红线，
            if (EBoolean.YES.getCode().equals(agent.getIsWare())) {

                // 是否完成授权单
                if (0 != impower.getMinCharge() && orderBO.checkImpowerOrder(
                    user.getUserId(), user.getImpowerDatetime())) {
                    result = ECheckStatus.NO_Impwoer.getCode();
                }

                // 红线设置为零视为无限制
                if (0 < agent.getRedAmount()) {
                    // 订单金额
                    Long orderAmount = orderBO.getOrderByUser(user.getUserId());
                    // 没有过任何订单，或者购买云仓数量少于首次授权发货金额，继续购买云仓
                    if (orderAmount < agent.getAmount()) {
                        // result = ECheckStatus.TO_BUY.getCode();
                        result = ECheckStatus.RED_LOW.getCode();
                    }
                }
                // 未开启云仓，只检查是否完成授权单
            } else if (0 != impower.getMinCharge() && orderBO.checkImpowerOrder(
                user.getUserId(), user.getImpowerDatetime())) {
                // 未完成授权单
                result = ECheckStatus.RED_LOW.getCode();
            }

            // 检查门槛余额
            Account account = accountBO.getAccountNocheck(user.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (null != account) {
                // 如果可剩余余额为零，不考虑等于的情况
                if (0 == agent.getMinSurplus() && account.getAmount() > 0) {
                    result = ECheckStatus.MIN_LOW.getCode();
                } else if (0 != agent.getMinSurplus()
                        && account.getAmount() >= agent.getMinSurplus()) {
                    result = ECheckStatus.MIN_LOW.getCode();
                }

            }

            // 是否有过充值
            Long cAmount = 0L;
            List<Charge> charge = chargeBO.getChargeByUser(user.getUserId(),
                user.getImpowerDatetime());
            for (Charge charge2 : charge) {
                cAmount = cAmount + charge2.getAmount();
            }

            // 没有过充值，前去充值
            if (CollectionUtils.isEmpty(charge)) {
                result = ECheckStatus.To_Charge.getCode();
                chargeAmount = impower.getMinCharge() - cAmount;

                // 有过充值，但是钱在审核中
            } else if (cAmount.equals(impower.getMinCharge())) {
                Charge condition = new Charge();
                condition.setApplyUser(user.getUserId());
                condition.setStatus(EChargeStatus.TO_Cancel.getCode());
                condition.setApplyDatetimeStart(user.getImpowerDatetime());
                charge = chargeBO.queryChargeList(condition);
                if (CollectionUtils.isNotEmpty(charge)) {
                    result = ECheckStatus.Charging.getCode();
                }
            }
            res = new XN627805Res(result, agent.getRedAmount(),
                agent.getMinSurplus(), agent.getAmount(), chargeAmount,
                user.getLevel(), isWare);
        }
        return res;
    }

}
