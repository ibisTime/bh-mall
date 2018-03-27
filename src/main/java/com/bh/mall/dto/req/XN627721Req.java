package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付订单
 * @author: nyc 
 * @since: 2018年3月27日 下午4:18:50 
 * @history:
 */
public class XN627721Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）用户ID
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
