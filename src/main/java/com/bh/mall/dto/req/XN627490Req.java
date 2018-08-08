package com.bh.mall.dto.req;

public class XN627490Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 流水分组组号（橙账本代表）---核心字段2
    private String refNo;

    // 支付渠道（线下/招商代付/支付宝/内部转账）
    private String channelType;

    // 支付渠道单号（支付渠道代表）---核心字段3
    private String channelOrder;

    // 流水所属账号---核心字段4
    private String accountNumber;

    // 币种
    private String currency;

    // 用户编号
    private String userId;

    // 真实姓名
    private String realName;

    // 账户类型
    private String type;

    // 业务类型
    private String bizType;

    // 状态
    private String status;

    // 开始时间起
    private String createDatetimeStart;

    // 开始时间止
    private String createDatetimeEnd;

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelOrder() {
        return channelOrder;
    }

    public void setChannelOrder(String channelOrder) {
        this.channelOrder = channelOrder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(String createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public String getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(String createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

}
