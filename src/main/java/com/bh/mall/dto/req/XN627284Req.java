package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627284Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
