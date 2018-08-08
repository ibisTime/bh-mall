package com.bh.mall.dto.req;

public class XN627492Req extends APageReq {

    private static final long serialVersionUID = 448602193604895025L;

    // 编号 必填
    private String userId;

    // 奖励类型（必填）
    private String bizType;

    // 账户类型（选填）
    private String type;

    // 种类 （选填）
    private String kind;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

}
