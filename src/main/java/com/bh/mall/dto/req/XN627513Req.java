package com.bh.mall.dto.req;

/**
 * 取现分页查询
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:03:24 
 * @history:
 */
public class XN627513Req extends APageReq {

    private static final long serialVersionUID = -2015437210978527476L;

    // 支付渠道
    private String channelType;

    // 状态（待审核/审核不通过/审核通过待支付/支付成功/支付失败）
    private String status;

    // 申请时间起
    private String applyDateStart;

    // 申请时间止
    private String applyDateEnd;

    // 审批时间起
    private String approveDateStart;

    // 审批时间止
    private String approveDateEnd;

    // 支付时间起
    private String payDateStart;

    // 支付时间止
    private String payDateEnd;

    // 针对用户编号
    private String userId;

    public String getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(String applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public String getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(String applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

    public String getApproveDateStart() {
        return approveDateStart;
    }

    public void setApproveDateStart(String approveDateStart) {
        this.approveDateStart = approveDateStart;
    }

    public String getApproveDateEnd() {
        return approveDateEnd;
    }

    public void setApproveDateEnd(String approveDateEnd) {
        this.approveDateEnd = approveDateEnd;
    }

    public String getPayDateStart() {
        return payDateStart;
    }

    public void setPayDateStart(String payDateStart) {
        this.payDateStart = payDateStart;
    }

    public String getPayDateEnd() {
        return payDateEnd;
    }

    public void setPayDateEnd(String payDateEnd) {
        this.payDateEnd = payDateEnd;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
