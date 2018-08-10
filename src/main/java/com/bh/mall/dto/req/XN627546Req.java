package com.bh.mall.dto.req;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 产品规格
 * @author: nyc 
 * @since: 2018年3月23日 下午2:18:04 
 * @history:
 */
public class XN627546Req {

    // 编号
    private String code;

    // （必填）规格名称
    @NotBlank(message = "规格名称不能为空")
    private String name;

    // （必填）是否可拆单
    @NotBlank(message = "是否可拆单不能为空")
    private String isSingle;

    // （必填）拆单数量
    @NotBlank(message = "拆单数量不能为空")
    @Min(0)
    private String singleNumber;

    // （必填）关联拆单编号
    @NotBlank(message = "关联拆单编号不能为空")
    private String refCode;

    // （必填）规格包含数量
    @NotBlank(message = "规格包含数量不能为空")
    private String number;

    // （必填）规格库存
    @NotBlank(message = "不能为空")
    private String stockNumber;

    // （必填）重量
    @NotBlank(message = "重量不能为空")
    private String weight;

    // （必填）是否允许升级单下单
    @NotBlank(message = "是否允许升级单下单不能为空")
    private String isUpgradeOrder;

    // （必填）是否允许授权单下单
    @NotBlank(message = "是否允许授权单下单不能为空")
    private String isImpowerOrder;

    // （必填）是否允许普通单下单
    @NotBlank(message = "是否允许普通单下单不能为空")
    private String isNormalOrder;

    // (必填) 产品规格定价
    private List<XN627547Req> specsPriceList;

    public List<XN627547Req> getSpecsPriceList() {
        return specsPriceList;
    }

    public void setSpecsPriceList(List<XN627547Req> specsPriceList) {
        this.specsPriceList = specsPriceList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsNormalOrder() {
        return isNormalOrder;
    }

    public void setIsNormalOrder(String isNormalOrder) {
        this.isNormalOrder = isNormalOrder;
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

    public String getIsSingle() {
        return isSingle;
    }

    public String getSingleNumber() {
        return singleNumber;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setIsSingle(String isSingle) {
        this.isSingle = isSingle;
    }

    public void setSingleNumber(String singleNumber) {
        this.singleNumber = singleNumber;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

}
