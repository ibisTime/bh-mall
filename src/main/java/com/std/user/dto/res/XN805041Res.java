package com.std.user.dto.res;

public class XN805041Res {
    private String userId;

    private Long amount;

    public XN805041Res() {
    }

    public XN805041Res(String userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
