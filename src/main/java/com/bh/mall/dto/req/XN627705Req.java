package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 改变内购产品数量
 * @author: nyc 
 * @since: 2018年3月26日 下午3:02:17 
 * @history:
 */
public class XN627705Req {
    // （必填）编码
    @NotBlank(message = "编码不能为空")
    private String code;

    // （必填）改变数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （必填）类型
    @NotBlank(message = "类型不能为空")
    private String type;

    // （必填）更新人
    private String updater;

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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
