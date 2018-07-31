package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IOutOrderDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.OutOrder;

@Repository("outOrderDAOImpl")
public class OutOrderDAOImpl extends AMybatisTemplate implements IOutOrderDAO {

    @Override
    public int insert(OutOrder data) {
        return super.insert(NAMESPACE.concat("insert_outOrder"), data);
    }

    @Override
    public int delete(OutOrder data) {
        return super.delete(NAMESPACE.concat("delete_outOrder"), data);
    }

    @Override
    public OutOrder select(OutOrder condition) {
        return super.select(NAMESPACE.concat("select_outOrder"), condition,
            OutOrder.class);
    }

    @Override
    public long selectTotalCount(OutOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_outOrder_count"),
            condition);
    }

    @Override
    public List<OutOrder> selectList(OutOrder condition) {
        return super.selectList(NAMESPACE.concat("select_outOrder"), condition,
            OutOrder.class);
    }

    @Override
    public List<OutOrder> selectList(OutOrder condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_outOrder"), start,
            count, condition, OutOrder.class);
    }

    @Override
    public void update(OutOrder data) {
        super.update(NAMESPACE.concat("update_outOrder"), data);
    }

    @Override
    public void deliverOutOrder(OutOrder data) {
        super.update(NAMESPACE.concat("deliver_outOrder"), data);

    }

    @Override
    public void approveOutOrder(OutOrder data) {
        super.update(NAMESPACE.concat("approve_outOrder"), data);

    }

    @Override
    public void cancelOutOrder(OutOrder data) {
        super.update(NAMESPACE.concat("update_status"), data);

    }

    @Override
    public void approveCancel(OutOrder data) {
        super.update(NAMESPACE.concat("approve_cancel"), data);
    }

    @Override
    public void receivedOutOrder(OutOrder data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public List<OutOrder> queryToDealList(int pageNo, int pageSize,
            OutOrder condition) {
        return super.selectList(NAMESPACE.concat("select_to_deal"), pageNo,
            pageSize, condition, OutOrder.class);
    }

    @Override
    public void addPayGroup(OutOrder data) {
        super.update(NAMESPACE.concat("add_payGroup"), data);

    }

    @Override
    public List<OutOrder> selectOutOrderPage(int pageNO, int pageSize,
            OutOrder condition) {
        return super.selectList(NAMESPACE.concat("select_outOrder"), pageNO,
            pageSize, condition, OutOrder.class);
    }

    @Override
    public void payNo(OutOrder data) {
        super.update(NAMESPACE.concat("pay_no"), data);
    }

    @Override
    public void paySuccess(OutOrder data) {
        super.update(NAMESPACE.concat("pay_success"), data);
    }

    @Override
    public void invalidOutOrder(OutOrder data) {
        super.update(NAMESPACE.concat("invalid_outOrder"), data);
    }

    @Override
    public void payOutOrder(OutOrder data) {
    }

}
