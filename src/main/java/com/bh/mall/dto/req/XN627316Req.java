package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改头像
 * @author: nyc 
 * @since: 2018年4月26日 下午5:54:31 
 * @history:
 */
public class XN627316Req {
    // 用户编号
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    @NotBlank(message = "备注不能为空")
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

    public String getRemark() {
        return remark;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
