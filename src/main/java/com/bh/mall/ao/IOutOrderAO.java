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
import com.bh.mall.dto.res.XN627666Res;

@Component
public interface IOutOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 代理下单
    public List<String> addOutOrder(XN627640Req req);

    // C端下单
    public List<String> addOutOrderC(XN627640Req req);

    // 修改收货地址及运费
    public void editOutOrder(XN627643Req req);

    // 无购物车下单
    public List<String> addOutOrderNoCart(XN627641Req req);

    // 无购物车下单（C端）
    public List<String> addOutOrderNoCartC(XN627641Req req);

    // 付款
    public Object payOutOrder(List<String> codeList, String payType);

    // 发货
    public void deliverOutOrder(OutOrder outOrder, String proCode,
            String deliver, String logisticsCode, String logisticsCompany,
            String remark);

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
    public void paySuccess(String result);

    // 检查限购
    public void checkLimitNumber(Agent agent, Specs psData, AgentPrice pspData,
            Integer quantity);

    // 订单作废
    public void invalidOutOrder(String code, String updater, String remark);

    // 分页查询订单
    public Paginable<OutOrder> queryOutOrderPage(int start, int limit,
            OutOrder condition);

    // 列表查询订单
    public List<OutOrder> queryOutOrderList(OutOrder condition);

    // 详情查询订单
    public OutOrder getOutOrder(String code);

    // 查询运费
    public XN627666Res getYunFei(String productCode, String speccCode,
            String province, String quantity, String kind);

    public void deliverOutOrder(XN627645Req req);

    public void eidtProCode(String code, String proCode);

}
