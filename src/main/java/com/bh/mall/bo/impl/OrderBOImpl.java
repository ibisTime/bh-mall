package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IOrderDAO;
import com.bh.mall.domain.Order;
import com.bh.mall.exception.BizException;

@Component
public class OrderBOImpl extends PaginableBOImpl<Order> implements IOrderBO {

    @Autowired
    private IOrderDAO orderDAO;

    @Override
    public void saveOrder(Order data) {
        orderDAO.insert(data);
    }

    @Override
    public void removeOrder(String code) {
    }

    @Override
    public void refreshOrder(Order data) {
        orderDAO.update(data);
    }

    @Override
    public List<Order> queryOrderList(Order condition) {
        return orderDAO.selectList(condition);
    }

    @Override
    public Order getOrder(String code) {
        Order data = null;
        if (StringUtils.isNotBlank(code)) {
            Order condition = new Order();
            condition.setCode(code);
            data = orderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void payOrder(Order data) {
        orderDAO.payOrder(data);
    }

    @Override
    public void deliverOrder(Order data) {
        orderDAO.deliverOrder(data);
    }

    @Override
    public void approveOrder(Order data) {
        orderDAO.approveOrder(data);
    }

    @Override
    public void cancelOrder(Order data) {
        orderDAO.cancelOrder(data);

    }

    @Override
    public void approveCancel(Order data) {
        orderDAO.approveCancel(data);
    }

    @Override
    public void receivedOrder(Order data) {
        orderDAO.receivedOrder(data);

    }

    @Override
    public Order getCheckOrder(String userId, String kind) {
        Order condition = new Order();
        condition.setApplyUser(userId);
        condition.setKind(kind);
        return orderDAO.select(condition);
    }

    @Override
    public long selectCount(Order oCondition) {
        return orderDAO.selectTotalCount(oCondition);
    }

    @Override
    public List<Order> queryToDealList(int pageNo, int pageSize,
            Order condition) {
        return orderDAO.queryToDealList(pageNo, pageSize, condition);
    }

    @Override
    public String addPayGroup(Order order) {
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());
        order.setPayGroup(payGroup);
        orderDAO.addPayGroup(order);
        return payGroup;
    }

    @Override
    public Order getInnerOrderByPayGroup(String payGroup) {
        Order data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            Order condition = new Order();
            condition.setPayGroup(payGroup);
            data = orderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void paySuccess(Order data) {
        orderDAO.paySuccess(data);
    }

    @Override
    public void payNo(Order data) {
        orderDAO.payNo(data);
    }

}
