package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Withdraw;
import com.bh.mall.enums.EWithdrawStatus;

/**
 * 取现单
 * @author: clockorange 
 * @since: Aug 1, 2018 1:46:05 PM 
 * @history:
 */
public interface IWithdrawBO extends IPaginableBO<Withdraw> {

    // 检查取现次数
    public void doCheckTimes(Account account);

    // 申请取现
    String applyOrder(Account account, Long amount, Long fee,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    // 接受取现
    void approveOrder(Withdraw data, EWithdrawStatus status, String approveUser,
            String approveNote);

    // 付款
    void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
            String payNote, String channelOrder);

    // 取现回录
    String backRecord(Account account, Long amount, Date payDatetime,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    // 列表查询
    List<Withdraw> queryWithdrawList(Withdraw condition);

    // 详情查询
    Withdraw getWithdraw(String code);

}
