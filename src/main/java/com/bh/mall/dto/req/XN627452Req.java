package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627452Req {

    // 账户编号(选填)
    @NotBlank(message = "账户编号不能为空")
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
