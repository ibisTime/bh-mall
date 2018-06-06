package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IOrderDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Order;

@Repository("orderDAOImpl")
public class OrderDAOImpl extends AMybatisTemplate implements IOrderDAO {

    @Override
    public int insert(Order data) {
        return super.insert(NAMESPACE.concat("insert_order"), data);
    }

    @Override
    public int delete(Order data) {
        return super.delete(NAMESPACE.concat("delete_order"), data);
    }

    @Override
    public Order select(Order condition) {
        return super.select(NAMESPACE.concat("select_order"), condition,
            Order.class);
    }

    @Override
    public long selectTotalCount(Order condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_order_count"),
            condition);
    }

    @Override
    public List<Order> selectList(Order condition) {
        return super.selectList(NAMESPACE.concat("select_order"), condition,
            Order.class);
    }

    @Override
    public List<Order> selectList(Order condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_order"), start, count,
            condition, Order.class);
    }

    @Override
    public void payOrder(Order data) {
        super.update(NAMESPACE.concat("pay_order"), data);

    }

    @Override
    public void update(Order data) {
        super.update(NAMESPACE.concat("update_order"), data);
    }

    @Override
    public void deliverOrder(Order data) {
        super.update(NAMESPACE.concat("deliver_order"), data);

    }

    @Override
    public void approveOrder(Order data) {
        super.update(NAMESPACE.concat("approve_order"), data);

    }

    @Override
    public void cancelOrder(Order data) {
        super.update(NAMESPACE.concat("update_status"), data);

    }

    @Override
    public void approveCancel(Order data) {
        super.update(NAMESPACE.concat("approve_cancel"), data);
    }

    @Override
    public void receivedOrder(Order data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public List<Order> queryToDealList(int pageNo, int pageSize,
            Order condition) {
        return super.selectList(NAMESPACE.concat("select_to_deal"), pageNo,
            pageSize, condition, Order.class);
    }

    @Override
    public void addPayGroup(Order data) {
        super.update(NAMESPACE.concat("add_payGroup"), data);

    }

    @Override
    public List<Order> selectOrderPage(int pageNO, int pageSize,
            Order condition) {
        return super.selectList(NAMESPACE.concat("select_order"), pageNO,
            pageSize, condition, Order.class);
    }

    @Override
    public void payNo(Order data) {
        super.update(NAMESPACE.concat("pay_no"), data);
    }

    @Override
    public void paySuccess(Order data) {
        super.update(NAMESPACE.concat("pay_success"), data);
    }

}
