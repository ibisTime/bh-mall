package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627037Req {

    // 编号（选填）
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
