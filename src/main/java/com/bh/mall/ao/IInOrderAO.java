package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InOrder;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;

@Component
public interface IInOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public List<String> addInOrder(XN627640Req req);

    public Paginable<InOrder> queryInOrderPage(int start, int limit,
            InOrder condition);

    public List<InOrder> queryInOrderList(InOrder condition);

    public InOrder getInOrder(String code);

    public String addInOrderNoCart(XN627641Req req);

    // 付款
    public Object payInOrder(List<String> codeList, String payType);

    // 取消订单
    public void cancelInOrder(String code);

    // 审核取消
    public void approveCancel(String code, String result, String updater,
            String remark);

    void paySuccess(String result);

    // 订单作废
    public void invalidInOrder(String code, String approver, String remark);

}
