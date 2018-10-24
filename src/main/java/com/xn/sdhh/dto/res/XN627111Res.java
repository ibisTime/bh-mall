package com.xn.sdhh.dto.res;

public class XN627111Res {
    private String userId;

    private String status;

    public XN627111Res() {
    }

    public XN627111Res(String userId) {
        this.userId = userId;
    }

    public XN627111Res(String userId, String status) {
        super();
        this.userId = userId;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
