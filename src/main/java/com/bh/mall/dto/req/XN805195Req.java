package com.bh.mall.dto.req;

public class XN805195Req {
    // 用户编号(必填)
    private String userId;

    // 姓名(必填)
    private String realName;

    // 证件号(必填)
    private String idNo;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
