package com.xn.sdhh.dto.res;

/**
 * c端登录 & 注册
 * @author: clockorange 
 * @since: Jul 16, 2018 10:52:24 AM 
 * @history:
 */

public class XN627304Res {

    // 用户编号
    private String userId;

    // 用户状态
    private String status;

    // 是否关注公众号
    private String subscribe;

    public XN627304Res() {
    }

    public XN627304Res(String userId, String status) {
        super();
        this.userId = userId;
        this.status = status;
    }

    public XN627304Res(String userId, String status, String subscribe) {
        super();
        this.userId = userId;
        this.status = status;
        this.subscribe = subscribe;
    }

    public XN627304Res(String userId) {
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
