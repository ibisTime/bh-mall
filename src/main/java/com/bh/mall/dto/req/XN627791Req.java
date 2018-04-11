package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改换货价
 * @author: nyc 
 * @since: 2018年4月9日 下午4:01:24 
 * @history:
 */
public class XN627791Req {

    // （必填）审核单编号
    @NotBlank(message = "审核单编号不能为空")
    private String code;

    // （必填）换货价
    @NotBlank(message = "换货价不能为空")
    private String changePrice;

    // （必填）审核人
    @NotBlank(message = "审核人不能为空")
    private String approver;

    // （选填） 审核备注
    private String approveNote;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

}
