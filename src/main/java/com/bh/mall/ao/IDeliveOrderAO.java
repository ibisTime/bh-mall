package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.DeliveOrder;

@Component
public interface IDeliveOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addDeliveOrder(DeliveOrder data);

    public void dropDeliveOrder(String code);

    public Paginable<DeliveOrder> queryDeliveOrderPage(int start, int limit,
            DeliveOrder condition);

    public List<DeliveOrder> queryDeliveOrderList(DeliveOrder condition);

    public DeliveOrder getDeliveOrder(String code);

}
