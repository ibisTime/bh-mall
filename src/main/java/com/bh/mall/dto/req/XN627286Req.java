package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627286Req {
    // 用户编号
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // 短信验证码
    @NotBlank(message = "用户Id不能为空")
    private String photo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
