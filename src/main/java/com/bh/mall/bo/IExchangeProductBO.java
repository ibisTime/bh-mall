
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ExchangeProduct;

public interface IExchangeProductBO extends IPaginableBO<ExchangeProduct> {

    public void saveChangeProduct(ExchangeProduct data);

    public int removeChangeProduct(String code);

    public int refreshChangeProduct(ExchangeProduct data);

    public List<ExchangeProduct> queryChangeProductList(
            ExchangeProduct condition);

    public ExchangeProduct getChangeProduct(String code);

    public void refreshChangePrice(ExchangeProduct data);

    public void approveChange(ExchangeProduct data);

    public List<ExchangeProduct> queryChangeProductPage(int start, int pageSize,
            ExchangeProduct condition);

}
