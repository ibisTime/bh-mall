package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取消申请
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627256Req {

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
