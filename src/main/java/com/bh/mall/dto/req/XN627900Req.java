package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 内购产品规格-新增
 * @author: LENOVO 
 * @since: 2018年8月1日 下午4:01:42 
 * @history:
 */
public class XN627900Req extends APageReq {

    private static final long serialVersionUID = 673666854499244469L;

    // 编号(必填)
    private String code;

    // 内购产品编号
    @NotBlank
    private String innerProductCode;

    // 规格名称
    @NotBlank
    private String name;

    // 规格包含数量
    @NotBlank
    private String number;

    // 重量
    @NotBlank
    private String weight;

    // 单价
    @NotBlank
    private String price;

    // 关联规格编号
    private String refCode;

    // 是否可拆单
    @NotBlank
    private String isSingle;

    // 拆单数量
    @NotBlank
    private String singleNumber;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getSingleNumber() {
        return singleNumber;
    }

    public void setSingleNumber(String singleNumber) {
        this.singleNumber = singleNumber;
    }

}
