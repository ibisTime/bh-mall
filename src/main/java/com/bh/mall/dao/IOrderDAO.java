package com.bh.mall.dao;

import java.util.List;

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

    List<Order> queryToDealList(int pageNo, int pageSize, Order condition);

    void addPayGroup(Order data);

    List<Order> selectOrderPage(int pageNO, int pageSize, Order condition);

    void payNo(Order data);

    void paySuccess(Order data);
}
