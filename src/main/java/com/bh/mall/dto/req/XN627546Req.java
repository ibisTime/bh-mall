package com.bh.mall.dto.req;

import java.util.List;

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

    // （必填）规格包含数量
    @NotBlank(message = "规格包含数量不能为空")
    private String number;

    // （必填）重量
    @NotBlank(message = "重量不能为空")
    private String weight;

    // （必填）是否允许升级单下单
    @NotBlank(message = "是否允许升级单下单不能为空")
    private String isUpgradeOrder;

    // （必填）是否允许授权单下单
    @NotBlank(message = "是否允许授权单下单不能为空")
    private String isPowerOrder;

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

    public String getIsPowerOrder() {
        return isPowerOrder;
    }

    public void setIsPowerOrder(String isPowerOrder) {
        this.isPowerOrder = isPowerOrder;
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

}
