package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改购物车数量
 * @author: nyc 
 * @since: 2018年3月27日 上午11:48:31 
 * @history:
 */
public class XN627621Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // 类型
    @NotBlank(message = "类型不能为空")
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
