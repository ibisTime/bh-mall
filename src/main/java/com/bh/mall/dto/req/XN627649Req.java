package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单作废
 * @author: nyc 
 * @since: 2018年3月28日 下午8:46:36 
 * @history:
 */

public class XN627649Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
