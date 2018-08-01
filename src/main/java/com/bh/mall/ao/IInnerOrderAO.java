package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.dto.req.XN627720Req;
import com.bh.mall.dto.req.XN627722Req;
import com.bh.mall.dto.req.XN627723Req;

/**
 * 内购订单
 * @author: LENOVO 
 * @since: 2018年7月31日 下午9:33:10 
 * @history:
 */
@Component
public interface IInnerOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 提交订单
    public String addInnerOrder(XN627720Req req);

    // 修改收货地址及运费
    public void refreshAddress(XN627722Req req);

    // 分页查询
    public Paginable<InnerOrder> queryInnerOrderPage(int start, int limit,
            InnerOrder condition);

    // 列表查询
    public List<InnerOrder> queryInnerOrderList(InnerOrder condition);

    // 详情查询
    public InnerOrder getInnerOrder(String code);

    // 订单支付
    public Object toPay(String code, String payType);

    // 发货
    public void deliverInnerProduct(XN627723Req req);

    // 申请取消
    public void cancelInnerOrder(String code);

    // 取消审核
    public void approveInnerOrder(String code, String result, String updater,
            String remark);

    // 确认收货
    public void receiveInnerOrder(String code);

    // 支付成功
    public void paySuccess(String result);

}
