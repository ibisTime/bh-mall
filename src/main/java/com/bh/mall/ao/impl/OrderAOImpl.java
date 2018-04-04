package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.Award;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.req.XN627645Req;
import com.bh.mall.enums.EAwardType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EInnerOrderStatus;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderSendType;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.EProductIsTotal;
import com.bh.mall.enums.EProductSpecsType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EProductYunFei;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserLevel;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class OrderAOImpl implements IOrderAO {

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private ICartBO cartBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IProductSpecsPriceBO productSpecsPriceBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IAwardBO awardBO;

    @Autowired
    private IAgencyLogBO agencyLogBO;

    // C端下单
    @Override
    public void addOrder(XN627640Req req) {
        for (Cart cart : req.getCartList()) {
            Order order = new Order();
            Product pData = productBO.getProduct(cart.getProductCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }
            ProductSpecs psData = productSpecsBO
                .getProductSpecs(cart.getProductSpecsCode());
            ProductSpecsPrice pspData = productSpecsPriceBO
                .getPriceBySpecsCode(psData.getCode());
            order.setKind(EOrderKind.Normal_Order.getCode());

            int quantity = cart.getQuantity() * psData.getNumber();

            if (quantity > pData.getRealNumber()) {
                throw new BizException("xn0000", "产品库存不足");
            }

            String code = OrderNoGenerater
                .generate(EGeneratePrefix.Order.getCode());

            order.setCode(code);

            order.setProductCode(pData.getCode());
            order.setProductName(pData.getName());
            order.setPic(pData.getAdvPic());

            order.setProductSpecsCode(psData.getCode());
            order.setProductSpecsName(pData.getName());
            order.setQuantity(cart.getQuantity());
            order.setPrice(pspData.getPrice());
            Long amount = cart.getQuantity() * pspData.getPrice();

            order.setAmount(amount);
            order.setApplyUser(req.getApplyUser());
            order.setApplyDatetime(new Date());
            order.setApplyNote(req.getApplyNote());
            order.setToUser(req.getToUser());

            order.setSigner(req.getSigner());
            order.setMobile(req.getMobile());
            order.setAddress(req.getAddress());
            order.setArea(req.getArea());
            order.setCity(req.getCity());

            order.setKind(EOrderKind.Normal_Order.getCode());
            order.setProvince(req.getProvince());
            order.setStatus(EOrderStatus.Unpaid.getCode());
            order.setIsSendHome(req.getIsSendHome());
            orderBO.saveOrder(order);
            // 删除购物车记录，修改产品数量
            cartBO.removeCart(cart);
            pData.setRealNumber(pData.getRealNumber() - quantity);
            productBO.refreshRepertory(pData);
        }
    }

    @Override
    public String addOrderNoCart(XN627641Req req) {
        ProductSpecs psData = productSpecsBO
            .getProductSpecs(req.getProductSpecsCode());
        Product pData = productBO.getProduct(psData.getProductCode());
        ProductSpecsPrice pspData = productSpecsPriceBO
            .getPriceBySpecsCode(psData.getCode());

        // 判断代理状态
        String kind = EOrderKind.Normal_Order.getCode();
        // 是否有过授权单
        Order condition = new Order();
        condition.setApplyUser(req.getApplyUser());
        condition.setKind(EOrderKind.Impower_Order.getCode());
        long count = orderBO.getTotalCount(condition);
        if (count == 0) {
            if (EProductSpecsType.Apply_NO.getCode()
                .equals(psData.getIsImpowerOrder())) {
                throw new BizException("xn0000", "该产品规格不予许授权单下单");
            } else {
                kind = EOrderKind.Impower_Order.getCode();
            }
        }
        // 是否升级
        User user = userBO.getUser(req.getApplyUser());
        AgencyLog agencyLog = agencyLogBO.getAgencyLog(user.getLastAgentLog());

        if (EUserStatus.UPGRADED.getCode().equals(agencyLog.getStatus())) {
            condition.setKind(EOrderKind.Upgrade_Order.getCode());
            count = orderBO.getTotalCount(condition);
            if (count == 0) {
                if (EProductSpecsType.Apply_NO.getCode()
                    .equals(psData.getIsUpgradeOrder())) {
                    throw new BizException("xn0000", "该产品规格不予许升级单下单");
                } else {
                    kind = EOrderKind.Upgrade_Order.getCode();
                }
            }
        }

        // 判断是否允许：普通，授权，升级下单
        if (EProductSpecsType.Apply_NO.getCode()
            .equals(psData.getIsNormalOrder())) {
            throw new BizException("xn0000", "该产品规格不予许普通单下单");
        }

        // 产品总数量
        int quantity = StringValidater.toInteger(req.getQuantity())
                * psData.getNumber();

        if (quantity > pData.getRealNumber()) {
            throw new BizException("xn0000", "产品库存不足");
        }
        if (EProductStatus.Shelf_NO.getCode().equals(pData.getStatus())
                || EProductStatus.Shelf_YES.getCode()
                    .equals(pData.getStatus())) {
            throw new BizException("xn0000", "产品已下架无法下单");
        }

        pData.setRealNumber(pData.getRealNumber() - quantity);
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        Order data = new Order();
        data.setCode(code);

        data.setProductCode(pData.getCode());
        data.setProductName(pData.getName());
        data.setPic(pData.getAdvPic());

        data.setProductSpecsCode(psData.getCode());
        data.setProductSpecsName(pData.getName());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        data.setPrice(pspData.getPrice());

        Long amount = StringValidater.toInteger(req.getQuantity())
                * pspData.getPrice();
        data.setAmount(amount);

        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());
        data.setToUser(req.getToUser());

        data.setSigner(req.getSigner());
        data.setMobile(req.getMobile());
        data.setAddress(req.getAddress());
        data.setArea(req.getArea());
        data.setCity(req.getCity());

        data.setKind(kind);
        data.setProvince(req.getProvince());
        data.setStatus(EOrderStatus.Unpaid.getCode());
        orderBO.saveOrder(data);
        pData.setRealNumber(pData.getRealNumber() - quantity);
        productBO.refreshRepertory(pData);
        return code;

    }

    @Override
    public void payOrder(String payCode, String payGroup, String payType) {
        String[] group = payGroup.split(",");

        if (StringUtils.isNotBlank(payGroup)) {
            for (String code : group) {
                Order data = orderBO.getOrder(code);
                if (!EOrderStatus.Paid.getCode().equals(data.getStatus())) {
                    throw new BizException("xn0000", "订单未处于待支付状态");
                }

                data.setPayCode(payCode);
                data.setPayGroup(code);
                data.setPayType(EPayType.RMB_YE.getCode());
                data.setPayDatetime(new Date());
                data.setPayAmount(data.getAmount());
                data.setStatus(EOrderStatus.Paid.getCode());
                orderBO.payOrder(data);

                User uData = userBO.getUser(data.getApplyUser());
                Account accountData = accountBO.getAccountByUser(
                    data.getApplyUser(), ECurrency.YJ_CNY.getCode());
                // C端用户从余额中扣除，代理下单从门槛账户扣除
                if (EUserKind.Customer.getCode().equals(uData.getKind())) {
                    accountBO.changeAmountNotJour(
                        accountData.getAccountNumber(), data.getAmount(),
                        data.getCode());
                } else {
                    accountBO.transAmountCZB(data.getApplyUser(),
                        ECurrency.MK_CNY.getCode(), ESystemCode.BH.getCode(),
                        ECurrency.MK_CNY.getCode(), data.getAmount(),
                        EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                        EBizType.AJ_GMYC.getValue(), data.getCode());
                }

                // 进价
                User toUser = userBO.getUser(data.getToUser());
                Long inPrice = productSpecsPriceBO.getPriceByLevel(
                    data.getProductSpecsCode(), toUser.getLevel());

                // 总利润
                Long awardAmount = (data.getPrice() - inPrice)
                        * data.getQuantity();

                // 差价利润
                accountBO.changeAmount(accountData.getAccountNumber(),
                    EChannelType.NBZ, null, data.getCode(), data.getCode(),
                    EBizType.AJ_CELR, EBizType.AJ_CELR.getValue(), awardAmount);

                // 出货以及推荐奖励
                this.payAward(data);
            }
        }

    }

    @Override
    public void dropOrder(String code) {
    }

    @Override
    public Paginable<Order> queryOrderPage(int start, int limit,
            Order condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().before(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        return orderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Order> queryOrderList(Order condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().before(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        String[] statusList = condition.getStatus().split(",");
        if (statusList.length > 1) {
            condition.setStatusList(condition.getStatus());
        }
        return orderBO.queryOrderList(condition);
    }

    @Override
    public Order getOrder(String code) {
        return orderBO.getOrder(code);
    }

    @Override
    public void editOrder(XN627643Req req) {
        Order data = orderBO.getOrder(req.getCode());
        if (EOrderStatus.TO_Deliver.getCode().equals(data.getStatus())
                || EOrderStatus.Received.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已发货");
        }
        data.setSigner(req.getSigner());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setAddress(req.getAddress());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setUpdateNote(req.getUpdateNote());
        orderBO.refreshOrder(data);
    }

    private void payAward(Order data) {
        Product product = productBO.getProduct(data.getProductCode());
        User user = userBO.getUser(data.getToUser());
        // 获取出货人的上级
        User highUser = userBO.getUser(user.getHighUserId());
        String fromUser = highUser.getUserId();
        if (isOne(user.getLevel())) {
            fromUser = ESysUser.SYS_USER_BH.getCode();
        }
        // 出货奖励,且产品计入出货
        if (EProductIsTotal.YES.getCode().equals(product.getIsTotal())) {
            Award aData = awardBO.getAwardByType(user.getLevel(),
                data.getProductCode(), EAwardType.SendAward.getCode());
            Long awardAmount = AmountUtil.mul(data.getAmount(),
                aData.getValue1());
            accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                data.getToUser(), ECurrency.YJ_CNY.getCode(), awardAmount,
                EBizType.AJ_CHJL, EBizType.AJ_CHJL.getValue(),
                EBizType.AJ_CHJL.getValue(), data.getCode());
        }

        // 直接推荐人
        User firstUser = userBO.getUser(user.getUserReferee());

        Award aData = awardBO.getAwardByType(user.getLevel(),
            data.getProductCode(), EAwardType.DirectAward.getCode());

        Long amount = 0L;
        // 直接推荐奖
        if (firstUser != null) {
            amount = AmountUtil.mul(data.getAmount(), aData.getValue1());
            accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                firstUser.getUserId(), ECurrency.YJ_CNY.getCode(), amount,
                EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                EBizType.AJ_TJJL.getValue(), data.getCode());

            // 间接推荐奖
            User secondUser = userBO.getUser(firstUser.getUserReferee());
            if (secondUser != null) {
                amount = AmountUtil.mul(data.getAmount(), aData.getValue2());
                accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                    secondUser.getUserId(), ECurrency.YJ_CNY.getCode(), amount,
                    EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                    EBizType.AJ_TJJL.getValue(), data.getCode());
                // 次推荐奖
                User thirdUser = userBO.getUser(secondUser.getUserReferee());
                if (thirdUser != null) {
                    amount = AmountUtil.mul(data.getAmount(),
                        aData.getValue3());
                    accountBO.transAmountCZB(fromUser,
                        ECurrency.YJ_CNY.getCode(), thirdUser.getUserId(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_TJJL,
                        EBizType.AJ_TJJL.getValue(),
                        EBizType.AJ_TJJL.getValue(), data.getCode());
                }
            }
        }

    }

    @Override
    public void deliverOrder(XN627645Req req) {
        Order data = orderBO.getOrder(req.getCode());
        if (!EOrderStatus.TO_Apprvoe.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单未支付或已发货");
        }
        Product product = productBO.getProduct(data.getProductCode());

        User uData = userBO.getUser(data.getToUser());
        String fromUser = uData.getUserId();
        if (isOne(uData.getLevel())) {
            fromUser = ESysUser.SYS_USER_BH.getCode();
        }
        if (EOrderSendType.Company_YES.getCode()
            .equals(req.getIsCompanySend())) {
            if (EProductYunFei.YunFei_YES.getCode()
                .equals(product.getIsFree())) {
                SYSConfig sysData = sysConfigBO.getConfig(data.getProvince(),
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());

                accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                    ESysUser.SYS_USER_BH.getCode(), ECurrency.YJ_CNY.getCode(),
                    StringValidater.toLong(sysData.getCvalue()),
                    EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                    EBizType.AJ_TJJL.getValue(), data.getCode());
            }
        }

        data.setDeliver(req.getDeliver());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCode());
        data.setLogisticsCompany(req.getLogisticsCompany());
        data.setPdf(req.getPdf());
        data.setStatus(EInnerOrderStatus.TO_Deliver.getCode());
        data.setRemark(req.getRemark());
        orderBO.deliverOrder(data);
    }

    @Override
    public void approveOrder(List<Order> codeList, String approver,
            String approveNote) {
        for (Order order : codeList) {
            Order data = orderBO.getOrder(order.getCode());
            data.setApprover(approver);
            data.setApproveDatetime(new Date());
            data.setApproveNote(approveNote);
            orderBO.approveOrder(data);
        }

    }

    @Override
    public void cancelOrder(String code) {
        Order data = orderBO.getOrder(code);
        User user = userBO.getCheckUser(data.getApplyUser());
        if (EUserKind.Customer.getCode().equals(user.getKind())) {
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "该订单无法申请取消");
            }
            data.setStatus(EOrderStatus.Canceled.getCode());
        } else {
            if (EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                data.setStatus(EOrderStatus.Canceled.getCode());
            } else
                data.setStatus(EOrderStatus.TO_Cancel.getCode());
        }

        orderBO.cancelOrder(data);
    }

    @Override
    public void approveCancel(String code, String result, String updater,
            String remark) {
        Order data = orderBO.getOrder(code);
        if (!EOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不处于待审核状态");
        }
        // 审核通过取消订单，退钱
        data.setStatus(EOrderStatus.Paid.getCode());
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EOrderStatus.Canceled.getCode());
            accountBO.transAmountCZB(ESysUser.SYS_USER_BH.getCode(),
                ECurrency.YJ_CNY.getCode(), data.getApplyUser(),
                ECurrency.YJ_CNY.getCode(), data.getAmount(),
                EBizType.AJ_GMCP_TK, EBizType.AJ_GMCP_TK.getValue(),
                EBizType.AJ_GMCP_TK.getValue(), data.getCode());
        }
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        orderBO.approveCancel(data);

    }

    @Override
    public void invalidOrder(String code, String updater, String updateNote) {
        Order data = orderBO.getOrder(code);
        User user = userBO.getCheckUser(data.getApplyUser());
        if (EUserKind.Customer.getCode().equals(user.getKind())) {
            throw new BizException("xn0000", "不是代理订单不能作废");
        }
        Product product = productBO.getProduct(data.getProductCode());
        if (EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {

        } else if (EOrderStatus.Paid.getCode().equals(data.getStatus())) {
            product.setRealNumber(product.getRealNumber() + data.getQuantity());
            productBO.refreshRepertory(product);
            accountBO.transAmountCZB(ESysUser.SYS_USER_BH.getCode(),
                ECurrency.YJ_CNY.getCode(), data.getApplyUser(),
                ECurrency.YJ_CNY.getCode(), data.getAmount(),
                EBizType.AJ_GMCP_TK, EBizType.AJ_GMCP_TK.getValue(),
                EBizType.AJ_GMCP_TK.getValue(), data.getCode());
        } else {
            throw new BizException("xn0000", "该状态下不能取消订单");
        }
        data.setStatus(EOrderStatus.Canceled.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setUpdateNote(updateNote);
        orderBO.approveCancel(data);
    }

    @Override
    public void receivedOrder(String code) {
        Order data = orderBO.getOrder(code);
        data.setStatus(EOrderStatus.Received.getCode());
        orderBO.receivedOrder(data);
    }

    protected boolean isOne(Integer level) {
        boolean flag = false;
        if (StringValidater.toInteger(EUserLevel.ONE.getCode()) == level) {
            flag = true;
        }
        return flag;
    }

}
