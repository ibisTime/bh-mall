package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
* 购物车
* @author: chenshan 
* @since: 2018-03-27 11:18:10
* @history:
*/
public class Cart extends ABaseDO {

    private static final long serialVersionUID = -8252737737985469825L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 产品编号
    private String productCode;

    // 规格编号
    private String specsCode;

    // 数量
    private Integer quantity;

    // 价格
    private Long price;

    // **************************db properties **************************
    // 产品
    private Product product;

    // 规格名称
    private String specsName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
