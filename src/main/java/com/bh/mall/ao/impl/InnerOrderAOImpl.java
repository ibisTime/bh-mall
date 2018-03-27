package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.ao.ISYSConfigAO;
import com.bh.mall.ao.IUserAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627720Req;
import com.bh.mall.dto.req.XN627722Req;
import com.bh.mall.dto.req.XN627723Req;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EInnerOrderResult;
import com.bh.mall.enums.EInnerOrderStatus;
import com.bh.mall.enums.EInnerProductKind;
import com.bh.mall.enums.EInnerProductStatus;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class InnerOrderAOImpl implements IInnerOrderAO {

    @Autowired
    private IInnerOrderBO innerOrderBO;

    @Autowired
    private IInnerProductAO innerProductAO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserAO userAO;

    @Autowired
    private ISYSConfigAO sysConfigAO;

    @Override
    public String addInnerOrder(XN627720Req req) {
        InnerOrder data = new InnerOrder();
        User uData = userAO.doGetUser(req.getApplyUser());

        InnerProduct ipData = innerProductAO
            .getInnerProduct(req.getProductCode());
        if (EInnerProductStatus.Shelf_NO.getCode().equals(data.getCode())
                || EInnerProductStatus.TO_Shelf.getCode()
                    .equals(data.getCode())) {
            throw new BizException("xn00000", "产品未上架，无法下单");
        }
        if (ipData.getQuantity() < StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn0000", "产品数量不足");
        }

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.InnerOrder.getCode());
        data.setCode(code);
        data.setProductCode(req.getProductCode());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));

        Long amount = AmountUtil.eraseLiUp(
            StringValidater.toInteger(req.getQuantity()) * ipData.getPrice());
        // 是否包邮
        if (EInnerProductKind.Free_NO.getCode().equals(ipData.getIsFree())) {
            SYSConfig sysData = sysConfigAO.getSYSConfig(req.getProvince(),
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            amount = amount + StringValidater.toLong(sysData.getCvalue());
        }

        data.setAmount(amount);

        data.setApplyUser(req.getApplyUser());
        data.setLevel(uData.getLevel());
        data.setTeamName(uData.getTeamName());

        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());
        data.setLevel(uData.getLevel());
        data.setProvince(req.getProvince());

        data.setArea(req.getArea());
        data.setCity(req.getCity());
        data.setAddress(req.getAddress());
        data.setMobile(req.getMobile());
        data.setSigner(req.getSigner());

        data.setStatus(EInnerOrderStatus.Unpaid.getCode());
        innerOrderBO.saveInnerOrder(data);
        return code;
    }

    @Override
    public void toPay(String code, String userId) {
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        if (EInnerProductStatus.Shelf_NO.getCode().equals(data.getCode())
                || EInnerProductStatus.TO_Shelf.getCode()
                    .equals(data.getCode())) {
            throw new BizException("xn00000", "产品未上架，无法完成支付");
        }
        Account aData = accountBO.getAccountByUserId(userId, ECurrency.YJ_CNY);
        if (aData.getAmount() < data.getAmount()) {
            throw new BizException("xn0000", "账户余额不足");
        }
        // 支付订单，更新订单状态TODO...........................

        data.setStatus(EInnerOrderStatus.Paid.getCode());
        innerOrderBO.payOrder(data);
    }

    @Override
    public void editInnerOrder(XN627722Req req) {
        InnerOrder data = innerOrderBO.getInnerOrder(req.getCode());
        if (EInnerOrderStatus.TO_Deliver.getCode().equals(data.getStatus())
                || EInnerOrderStatus.Delivered.getCode()
                    .equals(data.getStatus())) {
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
        data.setRemark(req.getRemark());
        innerOrderBO.refreshInnerOrder(data);

    }

    @Override
    public void dropInnerOrder(String code) {
    }

    @Override
    public Paginable<InnerOrder> queryInnerOrderPage(int start, int limit,
            InnerOrder condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().before(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return innerOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<InnerOrder> queryInnerOrderList(InnerOrder condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().before(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return innerOrderBO.queryInnerOrderList(condition);
    }

    @Override
    public InnerOrder getInnerOrder(String code) {
        return innerOrderBO.getInnerOrder(code);
    }

    @Override
    public void deliverInnerProduct(XN627723Req req) {
        InnerOrder data = innerOrderBO.getInnerOrder(req.getCode());
        if (!EInnerOrderStatus.Paid.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单未支付或已发货");
        }
        data.setDeliver(req.getDeliverer());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCode());
        data.setLogisticsCompany(req.getLogisticsCompany());
        data.setPdf(req.getPdf());
        data.setStatus(EInnerOrderStatus.TO_Deliver.getCode());
        data.setRemark(req.getRemark());
        innerOrderBO.deliverInnerProduct(data);

    }

    @Override
    public void cancelInnerOrder(String code) {
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        if (!EInnerOrderStatus.Unpaid.getCode().equals(data.getStatus())
                || !EInnerOrderStatus.Paid.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该订单无法申请取消");
        }
        data.setStatus(EInnerOrderStatus.TO_Cancel.getCode());
        innerOrderBO.cancelInnerOrder(data);
    }

    @Override
    public void approveInnerOrder(String code, String result, String updater,
            String remark) {
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        if (!EInnerOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不处于待审核状态");
        }
        // 审核通过取消订单，退钱
        if (EInnerOrderResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EInnerOrderStatus.Canceled.getCode());

        } else {
            data.setStatus(EInnerOrderStatus.Unpaid.getCode());
        }
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        innerOrderBO.approveInnerOrder(data);
    }
}
