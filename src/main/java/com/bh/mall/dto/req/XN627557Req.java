package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 产品详情查询
 * @author: nyc 
 * @since: 2018年3月25日 下午4:43:33 
 * @history:
 */
public class XN627557Req {

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
