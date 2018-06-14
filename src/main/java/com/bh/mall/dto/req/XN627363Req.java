package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 本等级是够需要实名
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627363Req {

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
