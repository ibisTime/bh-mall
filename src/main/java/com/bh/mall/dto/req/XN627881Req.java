package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询溯源
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627881Req {

    // 溯源码
    @NotBlank(message = "溯源码不能为空")
    private String traceCode;

    public String getTraceCode() {
        return traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

}
