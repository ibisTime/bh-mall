package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 内购订单详情
 * @author: nyc 
 * @since: 2018年3月27日 下午7:24:18 
 * @history:
 */
public class XN627733Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
