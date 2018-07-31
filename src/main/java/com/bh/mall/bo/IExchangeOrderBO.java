
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ExchangeOrder;

public interface IExchangeOrderBO extends IPaginableBO<ExchangeOrder> {

    public void saveExchangeOrder(ExchangeOrder data);

    public int removeExchangeOrder(String code);

    public int refreshExchangeOrder(ExchangeOrder data);

    public List<ExchangeOrder> queryExchangeOrderList(
            ExchangeOrder condition);

    public ExchangeOrder getExchangeOrder(String code);

    public void refreshChangePrice(ExchangeOrder data);

    public void approveChange(ExchangeOrder data);

    public List<ExchangeOrder> queryExchangeOrderPage(int start, int pageSize,
            ExchangeOrder condition);

}
