package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ExchangeOrder;

public interface IExchangeOrderDAO extends IBaseDAO<ExchangeOrder> {
    String NAMESPACE = IExchangeOrderDAO.class.getName().concat(".");

    /**
     * 更新换货价
     * @param data 
     * @create: 2018年7月31日 下午2:37:49 LENOVO
     * @history:
     */
    void updateChangePrice(ExchangeOrder data);

    /**
     * @param data 
     * @create: 2018年7月31日 下午2:38:56 LENOVO
     * @history:
     */
    void approveChange(ExchangeOrder data);

    /**
     * 
     * @param start
     * @param pageSize
     * @param condition
     * @return 
     * @create: 2018年7月31日 下午2:39:30 LENOVO
     * @history:
     */
    List<ExchangeOrder> queryChangeOrderPage(int start, int pageSize,
            ExchangeOrder condition);
}
