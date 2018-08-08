package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 我的出货
 * @author: nyc 
 * @since: 2018年6月30日 下午7:10:33 
 * @history:
 */
public class XN627850Req extends APageReq {

    private static final long serialVersionUID = -2391610940732263759L;

    // 代理编号
    @NotBlank(message = "代理编号不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
