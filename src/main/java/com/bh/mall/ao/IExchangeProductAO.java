package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ExchangeProduct;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.dto.res.XN627805Res;

@Component
public interface IExchangeProductAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addChangeProduct(XN627790Req req);

    public int dropChangeProduct(String code);

    public int editChangeProduct(ExchangeProduct data);

    public Paginable<ExchangeProduct> queryChangeProductPage(int start, int limit,
            ExchangeProduct condition);

    public List<ExchangeProduct> queryChangeProductList(ExchangeProduct condition);

    public ExchangeProduct getChangeProduct(String code);

    public void editChangePrice(String code, String changePrice,
            String approver, String string);

    public void approveChange(String code, String aprrover, String approveNote,
            String result);

    public ExchangeProduct getChangeProductMessage(XN627790Req req);

    public XN627805Res checkAmount(String userId);

}
