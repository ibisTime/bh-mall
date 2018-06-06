package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 审核
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627252Req {
    // （必填） 编号
    @NotBlank(message = "代理编号不能为空")
    private String userId;

    // （必填）管理员
    @NotBlank(message = "管理员不能为空")
    private String manager;

    // （必填）分配给谁
    private String toUserId;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String approver;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

}
