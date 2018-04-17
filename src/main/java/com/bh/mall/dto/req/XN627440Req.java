package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 可提现账户转云仓
 * @author: nyc 
 * @since: 2018年4月12日 下午1:40:25 
 * @history:
 */
public class XN627440Req {

    // 用户ID
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    // 转账金额
    @NotBlank(message = "转账金额不能为空")
    private String amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
