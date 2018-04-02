package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 代理结构
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627261Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String userId;

    // （必填）管理员
    @NotBlank(message = "管理员不能为空")
    private String manager;

    // （选填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
