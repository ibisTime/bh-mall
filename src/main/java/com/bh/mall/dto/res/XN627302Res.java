package com.bh.mall.dto.res;

public class XN627302Res {
    // 用户编号
    private String userId;

    // 是否绑定手机号
    private String isNeedMobile;

    public XN627302Res() {
    }

    public XN627302Res(String userId) {
        this.userId = userId;
    }

    public XN627302Res(String userId, String isNeedMobile) {
        this.userId = userId;
        this.isNeedMobile = isNeedMobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsNeedMobile() {
        return isNeedMobile;
    }

    public void setIsNeedMobile(String isNeedMobile) {
        this.isNeedMobile = isNeedMobile;
    }

}
