package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 列表查询奖励
 * @author: nyc 
 * @since: 2018年3月26日 上午11:06:08 
 * @history:
 */
public class XN627591Req {
    // （必填）类型
    @NotBlank(message = "类型不能为空")
    private String type;

    // （选填）产品编号
    private String productCode;

    // （选填）等级
    private String level;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
