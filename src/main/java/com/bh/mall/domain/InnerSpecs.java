package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 内购产品规格
 * @author: LENOVO 
 * @since: 2018年8月1日 下午3:29:14 
 * @history:
 */
public class InnerSpecs extends ABaseDO {

    private static final long serialVersionUID = 7101938404662891257L;

    // 编号
    private String code;

    // 产品编号
    private String innerProductCode;

    // 是否可拆单
    private String isSingle;

    // 拆单数量
    private Integer singleNumber;

    // 关联规格
    private String refCode;

    // 规格名称
    private String name;

    // 对应规格数量
    private Integer number;

    // 库存
    private Integer stockNumber;

    // 重量
    private Double weight;

    // 价格
    private String price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInnerProductCode() {
        return innerProductCode;
    }

    public void setInnerProductCode(String innerProductCode) {
        this.innerProductCode = innerProductCode;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsSingle() {
        return isSingle;
    }

    public Integer getSingleNumber() {
        return singleNumber;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setIsSingle(String isSingle) {
        this.isSingle = isSingle;
    }

    public void setSingleNumber(Integer singleNumber) {
        this.singleNumber = singleNumber;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

}
