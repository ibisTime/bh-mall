package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取消授权申请
 * @author: clockorange 
 * @since: Jul 17, 2018 9:44:49 AM 
 * @history:
 */

public class XN6272731Req {

    // （必填）用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
