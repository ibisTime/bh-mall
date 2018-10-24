package com.xn.sdhh.dto.res;

public class XN627301Res {
    private String userId;

    private String message;

    public XN627301Res() {
    }

    public XN627301Res(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
