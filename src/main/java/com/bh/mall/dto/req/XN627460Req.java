package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 线下充值申请
 * @author: xieyj 
 * @since: 2017年5月12日 上午9:58:02 
 * @history:
 */
public class XN627460Req {

    // 针对账号（必填）
    @NotBlank(message = "针对账号不能为空")
    private String accountNumber;

    // 类型（必填）0_充值，1_扣款
    private String type;

    // 充值金额（必填）
    @NotBlank(message = "充值金额不能为空")
    private String chargeAmount;

    // 申请人（必填）
    @NotBlank(message = "申请人不能为空")
    private String applyUser;

    // 申请说明（选填）
    private String applyNote;

    // 打款pdf
    @NotBlank(message = "打款pdf不能为空")
    private String chargePdf;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getChargePdf() {
        return chargePdf;
    }

    public void setChargePdf(String chargePdf) {
        this.chargePdf = chargePdf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

}
