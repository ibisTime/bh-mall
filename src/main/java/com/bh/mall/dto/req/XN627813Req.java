package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询云仓产品
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627813Req {

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
