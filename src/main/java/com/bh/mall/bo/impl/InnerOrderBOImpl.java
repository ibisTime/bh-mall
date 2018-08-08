package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IInnerOrderDAO;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.enums.EInnerOrderStatus;
import com.bh.mall.exception.BizException;

@Component
public class InnerOrderBOImpl extends PaginableBOImpl<InnerOrder>
        implements IInnerOrderBO {

    @Autowired
    private IInnerOrderDAO innerOrderDAO;

    @Override
    public void saveInnerOrder(InnerOrder data) {
        innerOrderDAO.insert(data);
    }

    @Override
    public void removeInnerOrder(String code) {
    }

    @Override
    public void refreshInnerOrder(InnerOrder data) {
        innerOrderDAO.update(data);
    }

    @Override
    public List<InnerOrder> queryInnerOrderList(InnerOrder condition) {
        return innerOrderDAO.selectList(condition);
    }

    @Override
    public InnerOrder getInnerOrder(String code) {
        InnerOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            InnerOrder condition = new InnerOrder();
            condition.setCode(code);
            data = innerOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void deliverInnerProduct(InnerOrder data) {
        innerOrderDAO.deliverInnerProduct(data);
    }

    @Override
    public void cancelInnerOrder(InnerOrder data) {
        innerOrderDAO.updateStatus(data);
    }

    @Override
    public void approveInnerOrder(InnerOrder data) {
        innerOrderDAO.approveInnerOrder(data);
    }

    @Override
    public void receiveInnerOrder(InnerOrder data) {
        innerOrderDAO.updateStatus(data);
    }

    @Override
    public long selectCount(InnerOrder ioCondition) {
        return innerOrderDAO.selectTotalCount(ioCondition);
    }

    @Override
    public InnerOrder getInnerOrderByPayGroup(String payGroup) {
        InnerOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            InnerOrder condition = new InnerOrder();
            condition.setPayGroup(payGroup);
            data = innerOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public String addPayGroup(InnerOrder data, String payType) {
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.InnerOrder.getCode());
        data.setPayGroup(payGroup);
        data.setPayType(payType);
        innerOrderDAO.addPayGroup(data);
        return payGroup;
    }

    @Override
    public void paySuccess(InnerOrder data) {
        data.setStatus(EInnerOrderStatus.Paid.getCode());
        innerOrderDAO.paySuccess(data);
    }

    @Override
    public List<InnerOrder> queryInnerOrderPage(int start, int pageSize,
            InnerOrder condition) {

        return innerOrderDAO.selectInnerOrderPage(start, pageSize, condition);
    }

    @Override
    public void payNo(InnerOrder data) {
        data.setStatus(EInnerOrderStatus.Pay_No.getCode());
        innerOrderDAO.payNo(data);

    }

}
