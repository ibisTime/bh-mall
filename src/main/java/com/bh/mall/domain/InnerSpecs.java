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

    // 内购产品编号
    private String innerProductCode;

    // 规格名称
    private String name;

    // 规格包含数量
    private Integer number;

    // 重量
    private Integer weight;

    // 单价
    private Integer price;

    // 关联规格编号
    private String refCode;

    // 是否可拆单
    private String isSingle;

    // 拆单数量
    private Integer singleNumber;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(String isSingle) {
        this.isSingle = isSingle;
    }

    public Integer getSingleNumber() {
        return singleNumber;
    }

    public void setSingleNumber(Integer singleNumber) {
        this.singleNumber = singleNumber;
    }

}
