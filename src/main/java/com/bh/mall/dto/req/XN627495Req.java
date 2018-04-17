package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627495Req {

    // 流水所属账号(必填)
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
