package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IInnerOrderDAO;
import com.bh.mall.domain.InnerOrder;
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
    public void payOrder(InnerOrder data) {
        innerOrderDAO.payOrder(data);
    }

    @Override
    public void deliverInnerProduct(InnerOrder data) {
        innerOrderDAO.deliverInnerProduct(data);
    }

    @Override
    public void cancelInnerOrder(InnerOrder data) {
        innerOrderDAO.cancelInnerOrder(data);
    }

    @Override
    public void approveInnerOrder(InnerOrder data) {
        innerOrderDAO.approveInnerOrder(data);
    }
}
