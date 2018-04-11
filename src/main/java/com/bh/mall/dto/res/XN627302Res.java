package com.bh.mall.dto.res;

public class XN627302Res {
    // 用户编号
    private String userId;

    // 是否绑定手机号
    private String status;

    public XN627302Res() {
    }

    public XN627302Res(String userId) {
        this.userId = userId;
    }

    public XN627302Res(String userId, String status) {
        this.userId = userId;
        this.status = status;
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

}
