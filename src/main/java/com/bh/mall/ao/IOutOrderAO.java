package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.Specs;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.req.XN627645Req;

@Component
public interface IOutOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public List<String> addOutOrder(XN627640Req req);

    public void editOutOrder(XN627643Req req);

    public Paginable<OutOrder> queryOutOrderPage(int start, int limit,
            OutOrder condition);

    public List<OutOrder> queryOutOrderList(OutOrder condition);

    public OutOrder getOutOrder(String code);

    public List<String> addOutOrderNoCart(XN627641Req req);

    // 付款
    public Object payOutOrder(List<String> codeList, String payType);

    // 发货
    public void deliverOutOrder(XN627645Req req);

    // 批量审单
    public void approveOutOrder(List<String> codeList, String approver,
            String approveNote);

    // 取消订单
    public void cancelOutOrder(String code);

    // 审核取消
    public void approveCancel(String code, String result, String updater,
            String remark);

    // 确认收货
    public void receivedOutOrder(String code);

    void paySuccess(String result);

    void checkLimitNumber(Agent agent, Specs psData, AgentPrice pspData,
            Integer quantity);

    // 订单作废
    public void invalidOutOrder(String code, String updater, String remark);

}
