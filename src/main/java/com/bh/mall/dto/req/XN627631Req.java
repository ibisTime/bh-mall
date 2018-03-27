package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询购物车产品
 * @author: nyc 
 * @since: 2018年3月27日 下午2:21:44 
 * @history:
 */
public class XN627631Req {

    // 产品编号
    @NotBlank(message = "产品编号不能为空")
    private String productCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
