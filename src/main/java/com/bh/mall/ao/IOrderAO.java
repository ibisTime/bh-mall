package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.req.XN627645Req;

@Component
public interface IOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public List<String> addOrder(XN627640Req req);

    public void editOrder(XN627643Req req);

    public Paginable<Order> queryOrderPage(int start, int limit,
            Order condition);

    public List<Order> queryOrderList(Order condition);

    public Order getOrder(String code);

    public String addOrderNoCart(XN627641Req req);

    // 付款
    public Object payOrder(List<String> codeList, String payType);

    // 发货
    public void deliverOrder(XN627645Req req);

    // 批量审单
    public void approveOrder(List<String> codeList, String approver,
            String approveNote);

    // 取消订单
    public void cancelOrder(String code);

    // 审核取消
    public void approveCancel(String code, String result, String updater,
            String remark);

    // 确认收货
    public void receivedOrder(String code);

    void paySuccess(String result);

    // 检查是否完成授权单
    public boolean CheckImpowerOrder(User user);

    // 检查是否完成升级单
    public boolean CheckUpgradeOrder(User user);

}
