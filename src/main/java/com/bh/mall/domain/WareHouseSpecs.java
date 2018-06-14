package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
* 云仓产品规格
* @author: nyc 
* @since: 2018-06-12 23:55:33
* @history:
*/
public class WareHouseSpecs extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 关联云仓编号
    private String wareHouseCode;

    // 规格编号
    private String productSpecsCode;

    // 价格
    private Long price;

    // 数量
    private Integer quantity;

    // 总价
    private Long amount;

    // ***************db*****************

    // 规格名称
    private String specsName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setWareHouseCode(String wareHouseCode) {
        this.wareHouseCode = wareHouseCode;
    }

    public String getWareHouseCode() {
        return wareHouseCode;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public Long getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getAmount() {
        return amount;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

}
