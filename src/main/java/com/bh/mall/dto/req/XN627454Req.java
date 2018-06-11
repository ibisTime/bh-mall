package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 检查余额是否可以低于红线
 * @author: nyc 
 * @since: 2018年6月11日 下午8:03:48 
 * @history:
 */
public class XN627454Req {

    // 用户编号(必填)
    @NotBlank(message = "userId不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
