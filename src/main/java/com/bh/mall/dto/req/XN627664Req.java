package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单详情
 * @author: nyc 
 * @since: 2018年4月8日 上午11:38:21 
 * @history:
 */
public class XN627664Req {

    // 编号（必填）
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
