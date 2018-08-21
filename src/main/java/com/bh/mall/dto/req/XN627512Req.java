package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取现详情查询
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:03:42 
 * @history:
 */
public class XN627512Req {

    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
