package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 检查余额是否可以低于红线
 * @author: nyc 
 * @since: 2018年6月11日 下午8:03:48 
 * @history:
 */
public class XN627454Req {

    // （必填）账户编号
    @NotBlank(message = "账户编号不能为空")
    private String accountNumber;

    // （必填）变动金额
    @NotBlank(message = "变动金额不能为空")
    private String changeAmount;

    // （必填）备注
    @NotBlank(message = "备注不能为空")
    private String remark;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getChangeAmount() {
        return changeAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setChangeAmount(String changeAmount) {
        this.changeAmount = changeAmount;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
