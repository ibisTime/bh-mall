package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Order;

public interface IOrderDAO extends IBaseDAO<Order> {
    String NAMESPACE = IOrderDAO.class.getName().concat(".");

    void payOrder(Order data);

    void update(Order data);

    void deliverOrder(Order data);

    void approveOrder(Order data);

    void cancelOrder(Order data);

    void approveCancel(Order data);

    void receivedOrder(Order data);
}
