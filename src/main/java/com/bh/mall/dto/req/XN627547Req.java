package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 规格定价
 * @author: nyc 
 * @since: 2018年3月23日 下午2:18:04 
 * @history:
 */
public class XN627547Req {

    // 编码
    private String code;

    // （必填）等级
    @NotBlank(message = "等级不能为空")
    private String level;

    // （必填）价格
    @NotBlank(message = "价格不能为空")
    private String price;

    // （必填）换货价
    @NotBlank(message = "换货不能为空")
    private String changePrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

}
