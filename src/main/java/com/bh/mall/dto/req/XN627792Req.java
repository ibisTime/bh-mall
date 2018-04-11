package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改换货价
 * @author: nyc 
 * @since: 2018年4月9日 下午4:01:24 
 * @history:
 */
public class XN627792Req {

    // （必填）申请单编号
    @NotBlank(message = "申请单编号不能为空")
    private String code;

    // （必填）审核人
    @NotBlank(message = "审核人不能为空")
    private String approver;

    // （必填）结果 0 不通过，1 通过
    @NotBlank(message = "结果不能为空")
    private String result;

    // （选填）审核备注
    @NotBlank(message = "审核备注不能为空")
    private String approveNote;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

}
