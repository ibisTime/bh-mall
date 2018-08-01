package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.req.XN627645Req;

/**
 * 出货订单
 * @author: LENOVO 
 * @since: 2018年8月1日 上午9:52:04 
 * @history:
 */
@Component
public interface IOutOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 提交订单
    public List<String> addOutOrder(XN627640Req req);

    // 修改收货地址及运费
    public void editOutOrder(XN627643Req req);

    // 分页查询
    public Paginable<OutOrder> queryOutOrderPage(int start, int limit,
            OutOrder condition);

    // 列表查询
    public List<OutOrder> queryOutOrderList(OutOrder condition);

    // 详情查询订单
    public OutOrder getOutOrder(String code);

    // 提交订单（无购物车）
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

    // 支付成功
    void paySuccess(String result);

    // 订单作废
    public void invalidOutOrder(String code, String updater, String remark);
}
