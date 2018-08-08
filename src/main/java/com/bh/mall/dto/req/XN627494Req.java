package com.bh.mall.dto.req;

public class XN627494Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 流水所属账号(必填)
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
