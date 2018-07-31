package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Withdraw;

/**
 * 取现单
 * @author: clockorange 
 * @since: Jul 31, 2018 4:48:16 PM 
 * @history:
 */
public interface IWithdrawAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 申请取现（需要密码）
    public String applyOrderTradePwd(String accountNumber, Long amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote, String tradePwd);

    // 申请取现
    public String applyOrder(String accountNumber, Long amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    // 接受取现
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote);

    // 付款
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder);

    // 分页查询
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition);

    // 列表查询
    public List<Withdraw> queryWithdrawList(Withdraw condition);

    // 详细查询
    public Withdraw getWithdraw(String code);

    // 取现回录
    public String backRecord(String accountNumber, Long amount,
            String payDatetime, String payCardInfo, String payCardNo,
            String applyUser, String applyNote);
}
