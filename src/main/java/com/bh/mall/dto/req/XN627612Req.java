package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询库存记录
 * @author: nyc 
 * @since: 2018年3月26日 下午2:02:45 
 * @history:
 */
public class XN627612Req {
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
