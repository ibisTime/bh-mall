package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统用户修改头像
 * @author: clockorange 
 * @since: Jul 17, 2018 11:30:29 AM 
 * @history:
 */
public class XN627279Req {
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
