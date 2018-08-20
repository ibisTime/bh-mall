package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加库存
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:49 
 * @history:
 */
public class XN627926Req extends APageReq {

    private static final long serialVersionUID = -6461911185806898589L;

    // （必填）产品编号
    @NotBlank(message = "不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
