package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 产品规格定价表
 * @author: nyc 
 * @since: 2018年3月23日 上午11:23:10 
 * @history:
 */
public class ProductSpecsPrice extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8062643066643775877L;

    // 编号
    private String code;

    // 规格编号
    private String productSpecsCode;

    // 等级
    private Integer level;

    // 价格
    private Long price;

    // 换货价
    private Long changePrice;

    // 本等级云仓最低发货数量
    private Integer minNumber;

    // 本等级是否可购买
    private String isBuy;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(Long changePrice) {
        this.changePrice = changePrice;
    }

    public Integer getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(Integer minNumber) {
        this.minNumber = minNumber;
    }

    public String getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(String isBuy) {
        this.isBuy = isBuy;
    }

}
