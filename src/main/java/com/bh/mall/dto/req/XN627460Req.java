package com.bh.mall.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 线下充值申请
 * @author: xieyj 
 * @since: 2017年5月12日 上午9:58:02 
 * @history:
 */
public class XN627460Req {

    // 针对账号（必填）
    @NotBlank
    private String accountNumber;

    // 充值金额（必填）
    @NotNull
    private String amount;

    // 申请人（必填）
    @NotBlank
    private String applyUser;

    // 申请说明（选填）
    private String applyNote;

    // 打款pdf
    private String chargePdf;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

}
