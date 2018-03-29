package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
* 购物车
* @author: chenshan 
* @since: 2018-03-27 11:18:10
* @history:
*/
public class Cart extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 产品编号
    private String productCode;

    // 规格编号
    private String productSpecsCode;

    // 数量
    private Integer quantity;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
