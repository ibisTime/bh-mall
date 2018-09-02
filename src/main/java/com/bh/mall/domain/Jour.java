package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 账户流水
 * @author: xieyj 
 * @since: 2016年11月10日 下午5:48:27 
 * @history:
 */
public class Jour extends ABaseDO {

    private static final long serialVersionUID = 1255747682967604091L;

    // 流水编号
    private String code;

    // 流水分组组号（橙账本代表）---核心字段2
    private String refNo;

    // 订单分组组号（信息代表）---核心字段1
    private String payGroup;

    // 支付渠道单号（支付渠道代表）---核心字段3
    private String channelOrder;

    // 支付渠道（线下/招商代付/支付宝/内部转账）
    private String channelType;

    // 流水所属账号---核心字段4
    private String accountNumber;

    // 变动金额（有正负之分）---核心字段5
    private Long transAmount;

    // 流水所属用户编号
    private String userId;

    // 流水所属真实姓名
    private String realName;

    // 类型(B B端账号，C C端账号，P 平台账号)
    private String type;

    // 币种
    private String currency;

    // 业务类型
    private String bizType;

    // 业务说明
    private String bizNote;

    // 变动前金额
    private Long preAmount;

    // 变动后金额
    private Long postAmount;

    // 状态
    private String status;

    // 备注
    private String remark;

    // 创建时间
    private Date createDatetime;

    // **************************db properties **************************
    // 业务类型列表
    private List<String> bizTypeList;

    // 订单信息
    private InOrder orderInformation;

    // 查询条件1：创建起始时间
    private Date createDatetimeStart;

    // 查询条件2：创建终止时间
    private Date createDatetimeEnd;

    // 转出金额
    private String outAmount;

    // 转入金额
    private String inAmount;

    // 判断支出与收入
    private String kind;

    // 代理电话
    private String mobile;

    // 代理
    private Agent agent;

    // 出货订单
    private OutOrder outOrder;

    // 云仓订单
    private InOrder inOrder;

    // 取现订单
    private Withdraw withdraw;

    // ********db************

    // 云仓订单
    private OrderReport report;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getBizTypeList() {
        return bizTypeList;
    }

    public void setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
    }

    public String getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(String outAmount) {
        this.outAmount = outAmount;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public Long getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(Long preAmount) {
        this.preAmount = preAmount;
    }

    public Long getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(Long postAmount) {
        this.postAmount = postAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getChannelOrder() {
        return channelOrder;
    }

    public void setChannelOrder(String channelOrder) {
        this.channelOrder = channelOrder;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public InOrder getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(InOrder orderInformation) {
        this.orderInformation = orderInformation;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public OrderReport getReport() {
        return report;
    }

    public void setReport(OrderReport report) {
        this.report = report;
    }

    public Withdraw getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Withdraw withdraw) {
        this.withdraw = withdraw;
    }

    public OutOrder getOutOrder() {
        return outOrder;
    }

    public InOrder getInOrdert() {
        return inOrder;
    }

    public void setOutOrder(OutOrder outOrder) {
        this.outOrder = outOrder;
    }

    public void setInOrder(InOrder inOrder) {
        this.inOrder = inOrder;
    }

}
