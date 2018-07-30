package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IInOrderDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.InOrder;

@Repository("inOrderDAOImpl")
public class InOrderDAOImpl extends AMybatisTemplate implements IInOrderDAO {

    @Override
    public int insert(InOrder data) {
        return super.insert(NAMESPACE.concat("insert_inOrder"), data);
    }

    @Override
    public int delete(InOrder data) {
        return super.delete(NAMESPACE.concat("delete_inOrder"), data);
    }

    @Override
    public InOrder select(InOrder condition) {
        return super.select(NAMESPACE.concat("select_inOrder"), condition,
            InOrder.class);
    }

    @Override
    public long selectTotalCount(InOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_inOrder_count"),
            condition);
    }

    @Override
    public List<InOrder> selectList(InOrder condition) {
        return super.selectList(NAMESPACE.concat("select_inOrder"), condition,
            InOrder.class);
    }

    @Override
    public List<InOrder> selectList(InOrder condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_inOrder"), start,
            count, condition, InOrder.class);
    }

    @Override
    public void payInOrder(InOrder data) {
        super.update(NAMESPACE.concat("pay_inOrder"), data);

    }

    @Override
    public void approveOrder(InOrder data) {
        super.update(NAMESPACE.concat("approve_inOrder"), data);

    }

    @Override
    public void cancelOrder(InOrder data) {
        super.update(NAMESPACE.concat("update_status"), data);

    }

    @Override
    public void approveCancel(InOrder data) {
        super.update(NAMESPACE.concat("approve_cancel"), data);
    }

    @Override
    public List<InOrder> queryToDealList(int pageNo, int pageSize,
            InOrder condition) {
        return super.selectList(NAMESPACE.concat("select_to_deal"), pageNo,
            pageSize, condition, InOrder.class);
    }

    @Override
    public void addPayGroup(InOrder data) {
        super.update(NAMESPACE.concat("add_payGroup"), data);

    }

    @Override
    public List<InOrder> selectOrderPage(int pageNO, int pageSize,
            InOrder condition) {
        return super.selectList(NAMESPACE.concat("select_order"), pageNO,
            pageSize, condition, InOrder.class);
    }

    @Override
    public void payNo(InOrder data) {
        super.update(NAMESPACE.concat("pay_no"), data);
    }

    @Override
    public void paySuccess(InOrder data) {
        super.update(NAMESPACE.concat("pay_success"), data);
    }

    @Override
    public void invalidOrder(InOrder data) {
        super.update(NAMESPACE.concat("invalid_inOrder"), data);
    }

}
