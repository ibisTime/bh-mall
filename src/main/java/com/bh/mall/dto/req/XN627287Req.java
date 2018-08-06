package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查意向代理
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627287Req {

    // 用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
