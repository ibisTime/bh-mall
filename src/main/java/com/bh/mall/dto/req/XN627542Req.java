package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除产品
 * @author: nyc 
 * @since: 2018年3月24日 上午11:54:10 
 * @history:
 */
public class XN627542Req {

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
