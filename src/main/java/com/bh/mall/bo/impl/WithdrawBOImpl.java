package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IBankcardBO;
import com.bh.mall.bo.IChannelBankBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IWithdrawBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWithdrawDAO;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Bankcard;
import com.bh.mall.domain.ChannelBank;
import com.bh.mall.domain.Withdraw;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EWithdrawStatus;
import com.bh.mall.exception.BizException;

@Component
public class WithdrawBOImpl extends PaginableBOImpl<Withdraw>
        implements IWithdrawBO {
    @Autowired
    private IBankcardBO bankcardBO; // 取现银行卡户名

    @Autowired
    private IWithdrawDAO withdrawDAO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IChannelBankBO channelBankBO;

    @Override
    public String applyOrder(Account account, Long amount, Long fee,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote) {
        if (amount == 0) {
            throw new BizException("xn000000", "取现金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Withdraw.getCode());
        Withdraw data = new Withdraw();
        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());
        data.setType(account.getType());
        data.setAmount(amount);
        data.setFee(fee);

        data.setChannelType(EChannelType.Offline.getCode());
        data.setPayCardInfo(payCardInfo);
        // 取现户名，应该和银行卡户名一致
        Bankcard bankcard = bankcardBO.getBankcardByBankcardNumber(payCardNo);
        if (null == bankcard) {
            data.setAccountName(account.getRealName());
        } else {
            // 设置银行名称和银行名称
            data.setAccountName(bankcard.getRealName());
            data.setPayCardInfo(bankcard.getBankName());
            // 获取银行编号
            ChannelBank channelBank = channelBankBO
                .getChannelBank(bankcard.getBankCode());
            if (null != channelBank) {
                data.setChannelBank(channelBank.getChannelBank());
            }
        }
        data.setPayCardNo(payCardNo);
        data.setStatus(EWithdrawStatus.toApprove.getCode());
        data.setApplyUser(applyUser);

        data.setApplyNote(applyNote);
        data.setApplyDatetime(new Date());
        withdrawDAO.insert(data);
        return code;
    }

    @Override
    public void approveOrder(Withdraw data, EWithdrawStatus status,
            String approveUser, String approveNote) {
        data.setStatus(status.getCode());
        data.setApproveUser(approveUser);
        data.setApproveNote(approveNote);
        data.setApproveDatetime(new Date());
        withdrawDAO.approveOrder(data);

    }

    @Override
    public void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
            String payNote, String channelOrder) {
        data.setStatus(status.getCode());
        data.setPayUser(payUser);
        data.setPayNote(payNote);
        data.setPayGroup(null);
        data.setChannelOrder(channelOrder);
        data.setPayDatetime(new Date());
        withdrawDAO.payOrder(data);
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        return withdrawDAO.selectList(condition);
    }

    @Override
    public Withdraw getWithdraw(String code) {
        Withdraw order = null;
        if (StringUtils.isNotBlank(code)) {
            Withdraw condition = new Withdraw();
            condition.setCode(code);
            order = withdrawDAO.select(condition);
        }
        return order;
    }

    /** 
     * @see com.std.account.bo.IWithdrawBO#doCheckTimes(java.lang.String)
     */
    @Override
    public void doCheckTimes(Account account) {
        // 判断本月申请次数是否达到上限
        Map<String, String> argsMap = sysConfigBO.getConfigsMap(null,
            ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
        String monthTimesKey = null;
        if (EAccountType.Customer.getCode().equals(account.getType())) {
            monthTimesKey = SysConstant.CUSERMONTIMES;
        } else if (EAccountType.Business.getCode().equals(account.getType())) {
            monthTimesKey = SysConstant.BUSERMONTIMES;
        }
        String monthTimesValue = argsMap.get(monthTimesKey);
        if (StringUtils.isNotBlank(monthTimesValue)) {// 月取现次数判断
            Withdraw condition = new Withdraw();
            condition.setAccountNumber(account.getAccountNumber());
            condition.setApplyDatetimeStart(DateUtil.getCurrentMonthFirstDay());
            condition.setApplyDatetimeEnd(DateUtil.getCurrentMonthLastDay());
            long totalCount = withdrawDAO.selectTotalCount(condition);
            long maxMonthTimes = Long.valueOf(monthTimesValue);
            if (totalCount >= maxMonthTimes) {
                throw new BizException("xn0000",
                    "每月取现最多" + maxMonthTimes + "次,本月申请次数已用尽");
            }
        }

        // 判断是否还有未处理的取现记录
        Withdraw condition = new Withdraw();
        condition.setAccountNumber(account.getAccountNumber());
        condition.setStatus("13");// 待申请，审核成功待支付
        if (withdrawDAO.selectTotalCount(condition) > 0) {
            throw new BizException("xn000000", "上笔取现申请还未处理成功，不能再次申请");
        }
    }

    @Override
    public String backRecord(Account account, Long amount, Date payDatetime,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote) {
        if (amount <= 0) {
            throw new BizException("xn000000", "回录金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Withdraw.getCode());
        Withdraw data = new Withdraw();
        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());
        data.setAccountName(account.getRealName());
        data.setType(account.getType());
        data.setAmount(amount);

        data.setChannelType(EChannelType.Offline.getCode());
        data.setPayCardInfo(payCardInfo);

        data.setPayCardNo(payCardNo);
        data.setStatus(EWithdrawStatus.Pay_YES.getCode());
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setApplyNote(applyNote);
        data.setPayUser(applyUser);
        data.setPayDatetime(payDatetime);
        withdrawDAO.insertBackRecord(data);
        return code;
    }

}
