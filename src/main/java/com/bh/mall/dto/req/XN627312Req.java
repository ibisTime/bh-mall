package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改上架
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627312Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String userId;

    // （必填）上级
    @NotBlank(message = "上级不能为空")
    private String highUser;

    // （选填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHighUser() {
        return highUser;
    }

    public void setHighUser(String highUser) {
        this.highUser = highUser;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
