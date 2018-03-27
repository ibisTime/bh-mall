package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加购物车
 * @author: nyc 
 * @since: 2018年3月27日 上午11:36:20 
 * @history:
 */
public class XN627620Req {

    // （必填）用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // （必填）产品编号
    @NotBlank(message = "产品编号不能为空")
    private String productCode;

    // （选填）规格编号
    private String productSpecsCode;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
