package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627274Req {

    // （必填）用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    @NotBlank(message = "用户编号不能为空")
    private String result;

    @NotBlank(message = "用户编号不能为空")
    private String approver;

    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
