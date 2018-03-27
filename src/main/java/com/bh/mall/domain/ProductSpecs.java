package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 产品规格表
 * @author: nyc 
 * @since: 2018年3月23日 上午10:47:23 
 * @history:
 */
public class ProductSpecs extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 产品编号
    private String productCode;

    // 规格名称
    private String name;

    // 规格包含数量
    private Integer number;

    // 重量
    private Integer weight;

    // 是否允许授权单下单
    private String isImpowerOrder;

    // 是否允许升级单下单
    private String isUpgradeOrder;

    // 是否允许普通单下单
    private String isNormalOrder;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getIsImpowerOrder() {
        return isImpowerOrder;
    }

    public void setIsImpowerOrder(String isImpowerOrder) {
        this.isImpowerOrder = isImpowerOrder;
    }

    public String getIsUpgradeOrder() {
        return isUpgradeOrder;
    }

    public void setIsUpgradeOrder(String isUpgradeOrder) {
        this.isUpgradeOrder = isUpgradeOrder;
    }

    public String getIsNormalOrder() {
        return isNormalOrder;
    }

    public void setIsNormalOrder(String isNormalOrder) {
        this.isNormalOrder = isNormalOrder;
    }

}
