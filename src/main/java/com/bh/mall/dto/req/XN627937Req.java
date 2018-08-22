package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情出货订单
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:21:23 
 * @history:
 */
public class XN627937Req {

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
