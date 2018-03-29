package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付订单
 * @author: nyc 
 * @since: 2018年3月27日 下午4:18:50 
 * @history:
 */
public class XN627721Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 支付渠道
    @NotBlank(message = "支付渠道不能为空")
    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
