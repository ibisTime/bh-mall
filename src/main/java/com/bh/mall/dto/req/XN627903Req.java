package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 申请取消
 * @author: nyc 
 * @since: 2018年8月7日 下午9:40:02 
 * @history:
 */
public class XN627903Req {

    // （必填）订单编号
    @NotBlank(message = "订单编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
