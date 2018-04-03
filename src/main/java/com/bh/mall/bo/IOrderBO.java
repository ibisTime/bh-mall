package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Order;

public interface IOrderBO extends IPaginableBO<Order> {

    public void saveOrder(Order data);

    public void removeOrder(String code);

    public void refreshOrder(Order data);

    public List<Order> queryOrderList(Order condition);

    public Order getOrder(String code);

    public void payOrder(Order data);

    public void deliverOrder(Order data);

    public void approveOrder(Order data);

    public void cancelOrder(Order data);

    public void approveCancel(Order data);

    public void receivedOrder(Order data);

    public Order getCheckOrder(String userId, String kind);

    public long selectCount(Order oCondition);

    public List<Order> queryToDealList(int pageNo, int pageSize,
            Order condition);

}
