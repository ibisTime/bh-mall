package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ExchangeOrder;

public interface IExchangeOrderDAO extends IBaseDAO<ExchangeOrder> {
    String NAMESPACE = IExchangeOrderDAO.class.getName().concat(".");

    void updateChangePrice(ExchangeOrder data);

    void approveChange(ExchangeOrder data);

    List<ExchangeOrder> queryChangeOrderPage(int start, int pageSize,
            ExchangeOrder condition);
}
