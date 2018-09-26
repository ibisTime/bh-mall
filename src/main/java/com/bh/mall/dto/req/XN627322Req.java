package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627322Req {

    // 用户编号
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
