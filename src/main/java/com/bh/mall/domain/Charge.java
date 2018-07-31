package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 充值订单
 * @author: nyc 
 * @since: 2018年7月29日 下午7:58:51 
 * @history:
 */
public class Charge extends ABaseDO {

    private static final long serialVersionUID = 340253581181024692L;

    // 充值编号
    private String code;

    // 订单分组组号（信息代表）---核心字段1
    private String payGroup;

    // 流水分组组号（橙账本代表）---核心字段2
    private String refNo;

    // 针对账号---核心字段4
    private String accountNumber;

    // 充值金额---核心字段5
    private Long amount;

    // 针对户名
    private String accountName;

    // 类别（C端账号/B端账号/P平台账号）
    private String type;

    // 币种
    private String currency;

    // 充值pdf
    private String chargePdf;

    // 支付渠道
    private String channelType;

    // 支付渠道编号
    private String channelOrder;

    // 业务类型编号（因为什么业务类型而充值）
    private String bizType;

    // 业务类型说明（因为什么业务类型而充值）
    private String bizNote;

    // 状态（0 待审核/1 审核通过/2 审核不通过）
    private String status;

    // 申请人
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

    // 支付回录人
    private String payUser;

    // 支付渠道的说明
    private String payNote;

    // 支付时间
    private Date payDatetime;

    // *******************************
    // 申请时间起
    private Date applyDatetimeStart;

    // 申请时间止
    private Date applyDatetimeEnd;

    // 支付时间起
    private Date payDatetimeStart;

    // 支付时间止
    private Date payDatetimeEnd;

    // 用户信息
    private Agent agent;

    // 上级
    private String highAgentId;

    // 充值人等级
    private Integer level;

    // 状态
    private List<String> statusList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizNote() {
        return bizNote;
    }

    public void setBizNote(String bizNote) {
        this.bizNote = bizNote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getHighAgentId() {
        return highAgentId;
    }

    public void setHighAgentId(String highAgentId) {
        this.highAgentId = highAgentId;
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public Date getPayDatetimeStart() {
        return payDatetimeStart;
    }

    public void setPayDatetimeStart(Date payDatetimeStart) {
        this.payDatetimeStart = payDatetimeStart;
    }

    public Date getPayDatetimeEnd() {
        return payDatetimeEnd;
    }

    public void setPayDatetimeEnd(Date payDatetimeEnd) {
        this.payDatetimeEnd = payDatetimeEnd;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getChannelOrder() {
        return channelOrder;
    }

    public void setChannelOrder(String channelOrder) {
        this.channelOrder = channelOrder;
    }

    public String getChargePdf() {
        return chargePdf;
    }

    public void setChargePdf(String chargePdf) {
        this.chargePdf = chargePdf;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

}
