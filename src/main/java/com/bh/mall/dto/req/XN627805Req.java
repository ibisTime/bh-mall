package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询余额红线
 * @author: nyc 
 * @since: 2018年4月23日 上午10:45:56 
 * @history:
 */
public class XN627805Req {

    // （必填）申请人
    @NotBlank(message = "userId不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
