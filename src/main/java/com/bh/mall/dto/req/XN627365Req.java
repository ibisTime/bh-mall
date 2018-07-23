package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取消授权
 * @author: nyc 
 * @since: 2018年7月21日 下午3:13:17 
 * @history:
 */
public class XN627365Req {

    // （必填） 用户Id
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （必填）备注
    @NotBlank(message = "备注不能为空")
    private String remark;

    public String getUserId() {
        return userId;
    }

    public String getUpdater() {
        return updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
