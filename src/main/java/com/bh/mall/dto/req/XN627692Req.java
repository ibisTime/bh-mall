package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查售后
 * @author: nyc 
 * @since: 2018年3月29日 上午11:46:29 
 * @history:
 */

public class XN627692Req {

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
