package com.bh.mall.dto.req;

public class XN627496Req extends APageReq {

    private static final long serialVersionUID = 954834300131559750L;

    // 类型
    private String kind;

    // 参考订单号
    private String refNo;

    // 系统编号
    private String bizType;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
