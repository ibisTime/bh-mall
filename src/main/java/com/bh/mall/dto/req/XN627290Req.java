package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 升级申请
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627290Req {
    // （必填）用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // （必填） 升级等级
    @NotBlank(message = " 升级等级不能为空")
    private String highLevel;

    // （必填）打款截图
    @NotBlank(message = "打款截图不能为空")
    private String payPdf;

    // （必填）打款金额
    @NotBlank(message = "打款金额不能为空")
    private String padAmount;

    // (选填) 团队名称
    private String teamName;

    // （选填）证件类型
    private String idKind;

    // (选填) 身份证号
    private String idNo;

    // (选填) 手持身份证
    private String idHand;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel;
    }

    public String getPayPdf() {
        return payPdf;
    }

    public void setPayPdf(String payPdf) {
        this.payPdf = payPdf;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPadAmount() {
        return padAmount;
    }

    public void setPadAmount(String padAmount) {
        this.padAmount = padAmount;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdKind() {
        return idKind;
    }

    public String getIdHand() {
        return idHand;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public void setIdHand(String idHand) {
        this.idHand = idHand;
    }

}
