package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IDeliveOrderDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.DeliveOrder;

//CHECK 。。。 
@Repository("deliveOrderDAOImpl")
public class DeliveOrderDAOImpl extends AMybatisTemplate
        implements IDeliveOrderDAO {

    @Override
    public int insert(DeliveOrder data) {
        return super.insert(NAMESPACE.concat("insert_deliveOrder"), data);
    }

    @Override
    public int delete(DeliveOrder data) {
        return super.delete(NAMESPACE.concat("delete_deliveOrder"), data);
    }

    @Override
    public DeliveOrder select(DeliveOrder condition) {
        return super.select(NAMESPACE.concat("select_deliveOrder"), condition,
            DeliveOrder.class);
    }

    @Override
    public long selectTotalCount(DeliveOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_deliveOrder_count"), condition);
    }

    @Override
    public List<DeliveOrder> selectList(DeliveOrder condition) {
        return super.selectList(NAMESPACE.concat("select_deliveOrder"),
            condition, DeliveOrder.class);
    }

    @Override
    public List<DeliveOrder> selectList(DeliveOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_deliveOrder"), start,
            count, condition, DeliveOrder.class);
    }

    @Override
    public void deliverOrder(DeliveOrder data) {
        super.update(NAMESPACE.concat("delive_Order"), data);
    }

}
