
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ExchangeOrder;

public interface IExchangeOrderBO extends IPaginableBO<ExchangeOrder> {

    public void saveChangeOrder(ExchangeOrder data);

    public int removeChangeOrder(String code);

    public int refreshChangeOrder(ExchangeOrder data);

    public List<ExchangeOrder> queryChangeOrderList(
            ExchangeOrder condition);

    public ExchangeOrder getChangeOrder(String code);

    public void refreshChangePrice(ExchangeOrder data);

    public void approveChange(ExchangeOrder data);

    public List<ExchangeOrder> queryChangeOrderPage(int start, int pageSize,
            ExchangeOrder condition);

}
