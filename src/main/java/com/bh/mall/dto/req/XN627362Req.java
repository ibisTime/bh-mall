package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 补充授权所需信息
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627362Req {

    // 用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // （选填）证件类型
    private String idKind;

    // （选填）证件号码
    private String idNo;

    // （选填）身份证背面照片
    private String idBehind;

    // （选填）身份证正面照片
    private String idFront;

    // （选填）手持身份证照片
    private String idHand;

    // （选填）打款金额
    private String payAmount;

    // （选填）打款证明
    private String payPdf;

    public String getUserId() {
        return userId;
    }

    public String getIdBehind() {
        return idBehind;
    }

    public String getIdFront() {
        return idFront;
    }

    public String getIdHand() {
        return idHand;
    }

    public String getIdKind() {
        return idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public String getPayPdf() {
        return payPdf;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIdBehind(String idBehind) {
        this.idBehind = idBehind;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    public void setIdHand(String idHand) {
        this.idHand = idHand;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public void setPayPdf(String payPdf) {
        this.payPdf = payPdf;
    }

}
