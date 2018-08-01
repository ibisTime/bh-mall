package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InOrder;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;

/**
 * 云仓订单
 * @author: LENOVO 
 * @since: 2018年7月31日 下午9:50:31 
 * @history:
 */
@Component
public interface IInOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 提交订单
    public List<String> addInOrder(XN627640Req req);

    // 分页查询
    public Paginable<InOrder> queryInOrderPage(int start, int limit,
            InOrder condition);

    // 列表查询
    public List<InOrder> queryInOrderList(InOrder condition);

    // 获取详情
    public InOrder getInOrder(String code);

    // 提交订单（无购物车）
    public String addInOrderNoCart(XN627641Req req);

    // 付款
    public Object payInOrder(List<String> codeList, String payType);

    // 取消订单
    public void cancelInOrder(String code);

    // 审核取消
    public void approveCancel(String code, String result, String updater,
            String remark);

    // 支付成功
    void paySuccess(String result);

    // 订单作废
    public void invalidInOrder(String code, String approver, String remark);

}
