package com.bh.mall.dto.res;

public class XN627302Res {
    // 用户编号
    private String userId;

    // 是否绑定手机号
    private String subscribe;

    public XN627302Res() {
    }

    public XN627302Res(String userId) {
        this.userId = userId;
    }

    public XN627302Res(String userId, String subscribe) {
        this.userId = userId;
        this.subscribe = subscribe;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

}
