package com.bh.mall.dto.res;

public class XN627302Res {
    // 用户编号
    private String userId;

    // 是否关注了公众号
    private String subscribe;

    // 用户状态
    private String status;

    public XN627302Res() {
    }

    public XN627302Res(String userId) {
        this.userId = userId;
    }

    public XN627302Res(String userId, String subscribe) {
        this.userId = userId;
        this.subscribe = subscribe;
    }

    public XN627302Res(String userId, String subscribe, String status) {
        super();
        this.userId = userId;
        this.subscribe = subscribe;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
