package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.dto.req.XN627720Req;
import com.bh.mall.dto.req.XN627722Req;
import com.bh.mall.dto.req.XN627723Req;

@Component
public interface IInnerOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addInnerOrder(XN627720Req req);

    public void dropInnerOrder(String code);

    public void editInnerOrder(XN627722Req req);

    public Paginable<InnerOrder> queryInnerOrderPage(int start, int limit,
            InnerOrder condition);

    public List<InnerOrder> queryInnerOrderList(InnerOrder condition);

    public InnerOrder getInnerOrder(String code);

    public void toPay(String payCode, String payGroup, String payType);

    public void deliverInnerProduct(XN627723Req req);

    public void cancelInnerOrder(String code);

    public void approveInnerOrder(String code, String result, String updater,
            String remark);

    public void receiveInnerOrder(String code);

}
