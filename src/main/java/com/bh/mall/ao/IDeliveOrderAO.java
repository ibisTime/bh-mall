package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.DeliveOrder;
import com.bh.mall.dto.req.XN627930Req;

@Component
public interface IDeliveOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<DeliveOrder> queryDeliveOrderPage(int start, int limit,
            DeliveOrder condition);

    public List<DeliveOrder> queryDeliveOrderList(DeliveOrder condition);

    public DeliveOrder getDeliveOrder(String code);

    public void deliverOrder(XN627930Req req);

}
