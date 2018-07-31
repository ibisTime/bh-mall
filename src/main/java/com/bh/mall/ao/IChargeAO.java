package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Charge;

/**
 * 充值订单
 * @author: LENOVO 
 * @since: 2018年7月31日 下午9:03:40 
 * @history:
 */
public interface IChargeAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 线下充值订单录入（front/oss）
    public String applyOrder(String accountNumber, String type, Long amount,
            String applyUser, String applyNote, String chhargePdf);

    // 审批线下充值订单
    public void payOrder(String code, String payUser, String payResult,
            String payNote);

    // 分页查询充值订单列表(oss)
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition);

    // 列表查询
    public List<Charge> queryChargeList(Charge condition);

    // 根据编号查询
    public Charge getCharge(String code);

    // 分页查询充值订单列表(front)
    public Paginable<Charge> queryFrontChargePage(int start, int limit,
            Charge condition);

}
