package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 内购产品规格列表查询
 * @author: LENOVO 
 * @since: 2018年8月2日 下午7:50:06 
 * @history:
 */
public class XN627906Req {

    // 内购产品编号
    @NotBlank(message = "内购产品编号不能为空")
    private String innerProductCode;

    public String getInnerProductCode() {
        return innerProductCode;
    }

    public void setInnerProductCode(String innerProductCode) {
        this.innerProductCode = innerProductCode;
    }

}
