package com.std.user.dto.res;

public class XN805154Res {
    private String userId;

    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public XN805154Res() {
    }

    public XN805154Res(String userId) {
        this.userId = userId;
    }

    public XN805154Res(String userId, String amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
