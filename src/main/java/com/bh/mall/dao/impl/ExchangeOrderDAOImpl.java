package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IExchangeOrderDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ExchangeOrder;

@Repository("exchangeOrderDAOImpl")
public class ExchangeOrderDAOImpl extends AMybatisTemplate
        implements IExchangeOrderDAO {

    @Override
    public int insert(ExchangeOrder data) {
        return super.insert(NAMESPACE.concat("insert_exchangeOrder"), data);
    }

    @Override
    public int delete(ExchangeOrder data) {
        return super.delete(NAMESPACE.concat("delete_exchangeOrder"), data);
    }

    @Override
    public ExchangeOrder select(ExchangeOrder condition) {
        return super.select(NAMESPACE.concat("select_exchangeOrder"), condition,
            ExchangeOrder.class);
    }

    @Override
    public long selectTotalCount(ExchangeOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_exchangeOrder_count"), condition);
    }

    @Override
    public List<ExchangeOrder> selectList(ExchangeOrder condition) {
        return super.selectList(NAMESPACE.concat("select_exchangeOrder"),
            condition, ExchangeOrder.class);
    }

    @Override
    public List<ExchangeOrder> selectList(ExchangeOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_exchangeOrder"), start,
            count, condition, ExchangeOrder.class);
    }

    @Override
    public void updateChangePrice(ExchangeOrder data) {
        super.update(NAMESPACE.concat("update_exchangePrice"), data);
    }

    @Override
    public void approveChange(ExchangeOrder data) {
        super.update(NAMESPACE.concat("approve_exchange"), data);
    }

    @Override
<<<<<<< HEAD
    public List<ExchangeOrder> queryExchangeOrderPage(int start, int pageSize,
=======
    public List<ExchangeOrder> queryChangeOrderPage(int start, int pageSize,
>>>>>>> refs/remotes/origin/master
            ExchangeOrder condition) {
        return super.selectList(NAMESPACE.concat("select_exchangeOrder"), start,
            pageSize, condition, ExchangeOrder.class);
    }

}
