package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情
 * @author: nyc 
 * @since: 2018年4月10日 下午8:35:28 
 * @history:
 */
public class XN627803Req {

    // 编号 必填
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
