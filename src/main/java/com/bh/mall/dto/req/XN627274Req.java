package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 申请退出
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627274Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
