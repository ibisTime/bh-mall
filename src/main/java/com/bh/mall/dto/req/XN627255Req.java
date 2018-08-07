package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 通过
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627255Req {
    // （必填） 编号
    @NotBlank(message = "代理编号不能为空")
    private String userId;

    // （必填）审核人
    @NotBlank(message = "审核人不能为空")
    private String approver;

    // （选填）备注
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
