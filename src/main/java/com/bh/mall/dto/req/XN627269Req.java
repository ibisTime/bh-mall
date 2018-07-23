package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 *  忽略意向分配
 * @author: clockorange 
 * @since: Jul 16, 2018 4:38:36 PM 
 * @history:
 */
public class XN627269Req {

    // （必填） 编号
    @NotBlank(message = "代理编号不能为空")
    private String userId;

    // （必填）审核人
    @NotBlank(message = "审核人不能为空")
    private String approver;

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
}
