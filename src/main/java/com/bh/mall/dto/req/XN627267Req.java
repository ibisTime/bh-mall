package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 列表查询意向代理
 * @author: nyc 
 * @since: 2018年8月5日 下午10:07:46 
 * @history:
 */
public class XN627267Req {

    // （选填） userId
    @NotBlank(message = "Id不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
