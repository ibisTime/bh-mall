package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IOrderReportDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.OrderReport;

//CHECK 。。。 
@Repository("orderReportDAOImpl")
public class OrderReportDAOImpl extends AMybatisTemplate
        implements IOrderReportDAO {

    @Override
    public int insert(OrderReport data) {
        return super.insert(NAMESPACE.concat("insert_orderReport"), data);
    }

    @Override
    public int delete(OrderReport data) {
        return super.delete(NAMESPACE.concat("delete_orderReport"), data);
    }

    @Override
    public OrderReport select(OrderReport condition) {
        return super.select(NAMESPACE.concat("select_orderReport"), condition,
            OrderReport.class);
    }

    @Override
    public long selectTotalCount(OrderReport condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_orderReport_count"), condition);
    }

    @Override
    public List<OrderReport> selectList(OrderReport condition) {
        return super.selectList(NAMESPACE.concat("select_orderReport"),
            condition, OrderReport.class);
    }

    @Override
    public List<OrderReport> selectList(OrderReport condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_orderReport"), start,
            count, condition, OrderReport.class);
    }

}
