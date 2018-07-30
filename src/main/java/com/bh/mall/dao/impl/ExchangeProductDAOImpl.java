package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IExchangeProductDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ExchangeProduct;

@Repository("exchangeProductDAOImpl")
public class ExchangeProductDAOImpl extends AMybatisTemplate
        implements IExchangeProductDAO {

    @Override
    public int insert(ExchangeProduct data) {
        return super.insert(NAMESPACE.concat("insert_exchangeProduct"), data);
    }

    @Override
    public int delete(ExchangeProduct data) {
        return super.delete(NAMESPACE.concat("delete_exchangeProduct"), data);
    }

    @Override
    public ExchangeProduct select(ExchangeProduct condition) {
        return super.select(NAMESPACE.concat("select_exchangeProduct"),
            condition, ExchangeProduct.class);
    }

    @Override
    public long selectTotalCount(ExchangeProduct condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_exchangeProduct_count"), condition);
    }

    @Override
    public List<ExchangeProduct> selectList(ExchangeProduct condition) {
        return super.selectList(NAMESPACE.concat("select_exchangeProduct"),
            condition, ExchangeProduct.class);
    }

    @Override
    public List<ExchangeProduct> selectList(ExchangeProduct condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_exchangeProduct"),
            start, count, condition, ExchangeProduct.class);
    }

    @Override
    public void updateChangePrice(ExchangeProduct data) {
        super.update(NAMESPACE.concat("update_exchangePrice"), data);
    }

    @Override
    public void approveChange(ExchangeProduct data) {
        super.update(NAMESPACE.concat("approve_exchange"), data);
    }

    @Override
    public List<ExchangeProduct> queryChangeProductPage(int start, int pageSize,
            ExchangeProduct condition) {
        return super.selectList(NAMESPACE.concat("select_exchangeProduct"),
            start, pageSize, condition, ExchangeProduct.class);
    }

}
