package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户查云仓
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627814Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
