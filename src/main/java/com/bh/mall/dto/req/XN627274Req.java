package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627274Req {

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
