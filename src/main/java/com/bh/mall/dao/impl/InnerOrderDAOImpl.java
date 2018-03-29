package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IInnerOrderDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.InnerOrder;

@Repository("innerOrderDAOImpl")
public class InnerOrderDAOImpl extends AMybatisTemplate
        implements IInnerOrderDAO {

    @Override
    public int insert(InnerOrder data) {
        return super.insert(NAMESPACE.concat("insert_innerOrder"), data);
    }

    @Override
    public int delete(InnerOrder data) {
        return super.delete(NAMESPACE.concat("delete_innerOrder"), data);
    }

    @Override
    public InnerOrder select(InnerOrder condition) {
        return super.select(NAMESPACE.concat("select_innerOrder"), condition,
            InnerOrder.class);
    }

    @Override
    public long selectTotalCount(InnerOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_innerOrder_count"), condition);
    }

    @Override
    public List<InnerOrder> selectList(InnerOrder condition) {
        return super.selectList(NAMESPACE.concat("select_innerOrder"),
            condition, InnerOrder.class);
    }

    @Override
    public List<InnerOrder> selectList(InnerOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_innerOrder"), start,
            count, condition, InnerOrder.class);
    }

    @Override
    public void payOrder(InnerOrder data) {
        super.update(NAMESPACE.concat("pay_order"), data);
    }

    @Override
    public void update(InnerOrder data) {
        super.update(NAMESPACE.concat("update_innerOrder"), data);
    }

    @Override
    public void deliverInnerProduct(InnerOrder data) {
        super.update(NAMESPACE.concat("deliver_innerProduct"), data);
    }

    @Override
    public void updateStatus(InnerOrder data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public void approveInnerOrder(InnerOrder data) {
        super.update(NAMESPACE.concat("approve_cancle"), data);
    }

}
