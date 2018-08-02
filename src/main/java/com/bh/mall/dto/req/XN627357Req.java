package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查意向单
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627357Req {

    // （必填）用户编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;

    }

    public void setCode(String code) {
        this.code = code;
    }

}
