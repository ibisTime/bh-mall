package com.xn.sdhh.dto.res;

public class XN627302Res {
    // 用户编号
    private String userId;

    // 用户状态
    private String status;

    // 是否关注公众号
    private String subscribe;

    public XN627302Res() {
    }

    public XN627302Res(String userId, String status) {
        super();
        this.userId = userId;
        this.status = status;
    }

    public XN627302Res(String userId, String status, String subscribe) {
        super();
        this.userId = userId;
        this.status = status;
        this.subscribe = subscribe;
    }

    public XN627302Res(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

}
