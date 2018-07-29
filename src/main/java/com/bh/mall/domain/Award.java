package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 推荐奖励表
 * @author: nyc 
 * @since: 2018年3月23日 上午11:26:04 
 * @history:
 */
public class Award extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 4595892553641554152L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 产品编号
    private String productCode;

    // 等级
    private Integer level;

    // 直接推荐
    private Double value1;

    // 间接推荐奖励
    private Double value2;

    // 次推荐奖励
    private Double value3;

    // *******************db*****************

    private String productName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public Double getValue3() {
        return value3;
    }

    public void setValue3(Double value3) {
        this.value3 = value3;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
