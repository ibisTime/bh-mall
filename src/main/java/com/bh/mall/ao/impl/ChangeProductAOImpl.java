package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IChangeProductAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IChangeProductBO;
import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.domain.Charge;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.User;
import com.bh.mall.domain.WareHouse;
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
public class ChangeProductAOImpl implements IChangeProductAO {

    @Autowired
    IChangeProductBO changeProductBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IWareHouseBO wareHouseBO;

    @Autowired
    IWareHouseLogBO wareHouseLogBO;

    @Autowired
    IProductSpecsBO productSpecsBO;

    @Autowired
    IProductBO productBO;

    @Autowired
    IProductSpecsPriceBO productSpecsPriceBO;

    @Autowired
    IProductLogBO productLogBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IOrderBO orderBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IChargeBO chargeBO;

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Autowired
    IAgencyLogBO agencyLogBO;

    @Override
    public String addChangeProduct(XN627790Req req) {
        User uData = userBO.getUser(req.getApplyUser());
        WareHouse whData = wareHouseBO.getWareHouseByProductSpec(
            uData.getUserId(), req.getProductSpecsCode());
        if (whData == null) {
            throw new BizException("xn000", "您的仓库中没有该规格的产品");
        }
        if (whData.getQuantity() < StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn000", "该规格的产品数量不足");
        }

        ProductSpecs specs = productSpecsBO
            .getProductSpecs(req.getProductSpecsCode());
        Product product = productBO.getProduct(specs.getProductCode());

        ProductSpecsPrice specsPrice = productSpecsPriceBO
            .getPriceByLevel(specs.getCode(), uData.getLevel());

        ProductSpecs changeSpecs = productSpecsBO
            .getProductSpecs(req.getChangeSpecsCode());
        Product changeProduct = productBO
            .getProduct(changeSpecs.getProductCode());
        ProductSpecsPrice changeSpecsPrice = productSpecsPriceBO
            .getPriceByLevel(changeSpecs.getCode(), uData.getLevel());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ChangeProduct.getCode());

        ChangeProduct data = new ChangeProduct();
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

        data.setChangeProductCode(changeProduct.getCode());
        data.setChangeProductName(changeProduct.getName());
        data.setChangeSpecsName(changeSpecs.getName());
        data.setChangeSpecsCode(changeSpecs.getCode());
        data.setCanChangeQuantity(canChangeQuantity);

        data.setApplyUser(req.getApplyUser());
        data.setRealName(uData.getRealName());
        data.setLevel(uData.getLevel());
        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());

        data.setStatus(EChangeProductStatus.TO_CHANGE.getCode());
        changeProductBO.saveChangeProduct(data);

        wareHouseBO.changeWareHouse(whData.getCode(),
            -StringValidater.toInteger(req.getQuantity()), EBizType.AJ_YCZH,
            "[" + product.getName() + "]申请置换为[" + changeProduct.getName() + "]",
            code);
        return code;

    }

    @Override
    public int editChangeProduct(ChangeProduct data) {
        return changeProductBO.refreshChangeProduct(data);

    }

    @Override
    public int dropChangeProduct(String code) {
        return changeProductBO.removeChangeProduct(code);

    }

    @Override
    public Paginable<ChangeProduct> queryChangeProductPage(int start, int limit,
            ChangeProduct condition) {
        if (condition.getApplyStartDatetime() != null
                && condition.getApplyEndDatetime() != null
                && condition.getApplyStartDatetime()
                    .after(condition.getApplyEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        long totalCount = changeProductBO.getTotalCount(condition);
        Page<ChangeProduct> page = new Page<>(start, limit, totalCount);
        List<ChangeProduct> list = changeProductBO.queryChangeProductPage(
            page.getStart(), page.getPageSize(), condition);

        for (ChangeProduct changeProduct : list) {
            String approveName = this.getName(changeProduct.getApprover());
            changeProduct.setApproveName(approveName);
            // 补充下单代理的信息
            User user = userBO.getCheckUser(changeProduct.getApplyUser());
            changeProduct.setUser(user);
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<ChangeProduct> queryChangeProductList(ChangeProduct condition) {
        if (condition.getApplyStartDatetime() != null
                && condition.getApplyEndDatetime() != null
                && condition.getApplyStartDatetime()
                    .after(condition.getApplyEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<ChangeProduct> list = changeProductBO
            .queryChangeProductList(condition);

        for (ChangeProduct changeProduct : list) {
            String approveName = this.getName(changeProduct.getApprover());
            changeProduct.setApproveName(approveName);
            User user = userBO.getCheckUser(changeProduct.getApplyUser());
            changeProduct.setUser(user);
        }

        return list;
    }

    @Override
    public ChangeProduct getChangeProduct(String code) {
        ChangeProduct data = changeProductBO.getChangeProduct(code);
        String approveName = this.getName(data.getApprover());
        data.setApproveName(approveName);
        User user = userBO.getCheckUser(data.getApplyUser());
        data.setUser(user);
        return data;
    }

    @Override
    public void editChangePrice(String code, String changePrice,
            String approver, String approveNote) {

        ChangeProduct data = changeProductBO.getChangeProduct(code);
        if (!EChangeProductStatus.TO_CHANGE.getCode()
            .equals(data.getStatus())) {
            throw new BizException("xn0000", "该置换单已经审核完成,无需修改换货价");
        }
        if (StringValidater.toLong(changePrice) == 0) {
            throw new BizException("xn000", "该产品的换货价不能等于0");
        }

        int canChangeQuantity = (int) (data.getAmount()
                / StringValidater.toLong(changePrice));
        WareHouse whData = wareHouseBO.getWareHouseByProductSpec(
            data.getApplyUser(), data.getProductSpecsCode());

        String logCode = wareHouseLogBO.refreshChangePrice(data, whData,
            StringValidater.toLong(changePrice), canChangeQuantity,
            data.getStatus(), "[" + data.getChangeProductName() + "]的换货价由["
                    + data.getChangePrice() + "]变为[" + changePrice + "]");
        whData.setLastChangeCode(logCode);
        wareHouseBO.refreshLogCode(whData);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setChangePrice(StringValidater.toLong(changePrice));

        data.setCanChangeQuantity(canChangeQuantity);

        changeProductBO.refreshChangePrice(data);
    }

    @Override
    public void approveChange(String code, String approver, String approveNote,
            String result) {
        ChangeProduct data = changeProductBO.getChangeProduct(code);
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
            ProductSpecs psData = productSpecsBO
                .getProductSpecs(data.getProductSpecsCode());

            int quantity = data.getQuantity() * psData.getNumber();
            pData.setRealNumber(pData.getRealNumber() - quantity);
            productBO.refreshRealNumber(pData);

            productLogBO.saveChangeProductLog(pData,
                EProductLogType.ChangeProduct.getCode(), pData.getRealNumber(),
                quantity, approver);
            productLogBO.saveChangeProductLog(changeData,
                EProductLogType.ChangeProduct.getCode(), pData.getRealNumber(),
                -quantity, approver);

            // 云仓新增产品
            WareHouse whData = wareHouseBO.getWareHouseByProductSpec(
                data.getApplyUser(), data.getChangeSpecsCode());
            if (whData == null) {
                String whCode = OrderNoGenerater
                    .generate(EGeneratePrefix.WareHouse.getCode());
                WareHouse wareHouse = new WareHouse();
                wareHouse.setCode(whCode);
                wareHouse.setProductCode(data.getChangeProductCode());
                wareHouse.setProductName(data.getChangeProductName());
                wareHouse.setProductSpecsCode(data.getChangeSpecsCode());
                wareHouse.setProductSpecsName(data.getChangeSpecsName());

                wareHouse.setCurrency(ECurrency.YC_CNY.getCode());
                wareHouse.setUserId(data.getApplyUser());
                wareHouse.setRealName(data.getRealName());
                wareHouse.setCreateDatetime(new Date());
                ProductSpecsPrice pspData = productSpecsPriceBO.getPriceByLevel(
                    data.getChangeSpecsCode(), data.getLevel());

                wareHouse.setPrice(pspData.getPrice());

                wareHouse.setQuantity(data.getCanChangeQuantity());
                Long amount = data.getCanChangeQuantity() * pspData.getPrice();
                wareHouse.setAmount(amount);
                wareHouse.setLastChangeCode(data.getCode());
                wareHouse.setStatus(EAccountStatus.NORMAL.getCode());
                wareHouse.setCompanyCode(ESystemCode.BH.getCode());
                wareHouse.setSystemCode(ESystemCode.BH.getCode());
                wareHouseBO.saveWareHouse(wareHouse, data.getQuantity(),
                    EBizType.AJ_YCZH,
                    "[" + data.getProductName() + "]置换为["
                            + data.getChangeProductName() + "]",
                    data.getCode());
            } else {
                wareHouseBO.changeWareHouse(whData.getCode(),
                    data.getQuantity(), EBizType.AJ_GMYC,
                    EBizType.AJ_GMYC.getValue(), data.getCode());
            }

        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setStatus(status);
        changeProductBO.approveChange(data);
    }

    private String getName(String user) {

        if (StringUtils.isBlank(user)) {
            return null;
        }
        String name = null;
        User data = userBO.getUserNoCheck(user);
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
    public ChangeProduct getChangeProductMessage(XN627790Req req) {
        User uData = userBO.getUser(req.getApplyUser());
        WareHouse whData = wareHouseBO.getWareHouseByProductSpec(
            uData.getUserId(), req.getProductSpecsCode());
        if (whData == null) {
            throw new BizException("xn000", "您的云仓中该规格的产品不存在");
        }
        if (whData.getQuantity() < StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn000", "该规格的产品数量不足");
        }

        ProductSpecs specs = productSpecsBO
            .getProductSpecs(req.getProductSpecsCode());

        ProductSpecsPrice specsPrice = productSpecsPriceBO
            .getPriceByLevel(specs.getCode(), uData.getLevel());

        ProductSpecs changeSpecs = productSpecsBO
            .getProductSpecs(req.getChangeSpecsCode());
        ProductSpecsPrice changeSpecsPrice = productSpecsPriceBO
            .getPriceByLevel(changeSpecs.getCode(), uData.getLevel());

        ChangeProduct cpData = new ChangeProduct();
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
        User user = userBO.getUser(userId);
        // 需要补充红线金额
        Long redAmount = 0L;
        // 门槛所需充值金额
        Long chargeAmount = 0L;
        String result = ECheckStatus.NORMAL.getCode();
        String isWareHouse = EBoolean.YES.getCode();

        // 代理已通过审核
        if (null != user.getLevel() && 0 != user.getLevel()) {
            Agent agent = agentBO.getAgentByLevel(user.getLevel());
            AgentImpower impower = agentImpowerBO
                .getAgentImpowerByLevel(user.getLevel());

            // 检查云仓红线
            Long whAmount = 0L;
            List<WareHouse> list = wareHouseBO.getWareHouseByUser(userId);
            for (WareHouse wareHouse : list) {
                if (null != wareHouse.getAmount()) {
                    whAmount = whAmount + wareHouse.getAmount();
                }
            }

            // 开启云仓,且红线限制为零，红线限制不为零时，不会出现直接跳到下授权单
            if (EBoolean.YES.getCode().equals(agent.getIsWareHouse())) {

                // 没有过任何订单，或者购买云仓数量少于首次授权发货金额，购买云仓
                Long orderAmount = orderBO.getOrderByUser(user.getUserId());
                if (0 == orderAmount || orderAmount < agent.getAmount()) {
                    // result = ECheckStatus.TO_BUY.getCode();
                    result = ECheckStatus.RED_LOW.getCode();
                } else if (orderBO.checkImpowerOrder(user.getUserId())) {
                    // 是否完成授权单
                    result = ECheckStatus.NO_Impwoer.getCode();
                }

            } else if (orderBO.checkImpowerOrder(user.getUserId())) {
                // 未完成授权单
                // result = ECheckStatus.NO_WAREHOUSE.getCode();
                result = ECheckStatus.RED_LOW.getCode();
            }

            // 最后一条轨迹为升级时，检查升级单
            // AgencyLog log = agencyLogBO.getAgencyLog(user.getLastAgentLog());
            // if (EUserStatus.UPGRADED.getCode().equals(log.getStatus())) {
            // amount = agent.getAmount();
            // result = ECheckStatus.NO_Upgrae.getCode();
            // }

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
            // 是否有过充值,且充值金额大于最低授权充值
            Long cAmount = 0L;
            List<Charge> charge = chargeBO.getChargeByUser(user.getUserId());
            for (Charge charge2 : charge) {
                cAmount = cAmount + charge2.getAmount();
            }

            // 没有过充值，前去充值
            if (CollectionUtils.isEmpty(charge)
                    || impower.getMinCharge() > cAmount) {
                result = ECheckStatus.To_Charge.getCode();
                chargeAmount = impower.getMinCharge() - cAmount;

                // 有过充值，但是钱在审核中
            } else if (cAmount.equals(impower.getMinCharge())) {
                Charge condition = new Charge();
                condition.setApplyUser(user.getUserId());
                condition.setStatus(EChargeStatus.TO_Cancel.getCode());
                charge = chargeBO.queryChargeList(condition);
                if (CollectionUtils.isNotEmpty(charge)) {
                    result = ECheckStatus.Charging.getCode();
                }
            }
            res = new XN627805Res(result, redAmount, agent.getMinSurplus(),
                agent.getAmount(), chargeAmount, user.getLevel(), isWareHouse);
        }
        return res;
    }

}
