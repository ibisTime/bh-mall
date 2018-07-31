package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ExchangeOrder;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.dto.res.XN627805Res;

@Component
public interface IExchangeOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

<<<<<<< HEAD
    public String addExchangeOrder(XN627790Req req);

    public int dropExchangeOrder(String code);

    public int editExchangeOrder(ExchangeOrder data);

    public Paginable<ExchangeOrder> queryExchangeOrderPage(int start, int limit,
            ExchangeOrder condition);

    public List<ExchangeOrder> queryExchangeOrderList(ExchangeOrder condition);

    public ExchangeOrder getExchangeOrder(String code);

    public void editChangePrice(String code, String changePrice,
            String approver, String string);

    public void approveChange(String code, String aprrover, String approveNote,
            String result);

    public ExchangeOrder getExchangeOrderMessage(XN627790Req req);
=======
    public String addChangeOrder(XN627790Req req);

    public int editChangeOrder(ExchangeOrder data);

    public Paginable<ExchangeOrder> queryChangeOrderPage(int start, int limit,
            ExchangeOrder condition);

    public List<ExchangeOrder> queryChangeOrderList(ExchangeOrder condition);

    public ExchangeOrder getChangeOrder(String code);

    public void editChangePrice(String code, String changePrice,
            String approver, String string);

    public void approveChange(String code, String aprrover, String approveNote,
            String result);

    public ExchangeOrder getChangeOrderMessage(XN627790Req req);
>>>>>>> refs/remotes/origin/master

    public XN627805Res checkAmount(String userId);

}
