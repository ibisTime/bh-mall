package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:34:58 
 * @history:
 */
public class XN627867Req {

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
