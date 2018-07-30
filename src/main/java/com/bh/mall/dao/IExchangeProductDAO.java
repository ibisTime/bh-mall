package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ExchangeProduct;

public interface IExchangeProductDAO extends IBaseDAO<ExchangeProduct> {
    String NAMESPACE = IExchangeProductDAO.class.getName().concat(".");

    void updateChangePrice(ExchangeProduct data);

    void approveChange(ExchangeProduct data);

    List<ExchangeProduct> queryChangeProductPage(int start, int pageSize,
            ExchangeProduct condition);
}
