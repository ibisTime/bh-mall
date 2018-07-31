
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ExchangeOrder;

public interface IExchangeOrderBO extends IPaginableBO<ExchangeOrder> {

<<<<<<< HEAD
    public void saveExchangeOrder(ExchangeOrder data);

    public int removeExchangeOrder(String code);

    public int refreshExchangeOrder(ExchangeOrder data);

    public List<ExchangeOrder> queryExchangeOrderList(
            ExchangeOrder condition);

    public ExchangeOrder getExchangeOrder(String code);

    public void refreshChangePrice(ExchangeOrder data);

    public void approveChange(ExchangeOrder data);

    public List<ExchangeOrder> queryExchangeOrderPage(int start, int pageSize,
=======
    public void saveChangeOrder(ExchangeOrder data);

    public int removeChangeOrder(String code);

    public int refreshChangeOrder(ExchangeOrder data);

    public List<ExchangeOrder> queryChangeOrderList(
            ExchangeOrder condition);

    public ExchangeOrder getChangeOrder(String code);

    public void refreshChangePrice(ExchangeOrder data);

    public void approveChange(ExchangeOrder data);

    public List<ExchangeOrder> queryChangeOrderPage(int start, int pageSize,
>>>>>>> refs/remotes/origin/master
            ExchangeOrder condition);

}
