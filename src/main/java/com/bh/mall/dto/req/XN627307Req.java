package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627307Req {

    // （必填）userId
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
