package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 可提现账户转云仓
 * @author: nyc 
 * @since: 2018年4月12日 下午1:40:25 
 * @history:
 */
public class XN627440Req {

    // 用户Id
    private String userId;

    // 付款账户
    @NotBlank(message = "用户ID不能为空")
    private String fromAccount;

    // 收款账户
    @NotBlank(message = "用户ID不能为空")
    private String toAccount;

    // 转账金额
    @NotBlank(message = "转账金额不能为空")
    private String amount;

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
