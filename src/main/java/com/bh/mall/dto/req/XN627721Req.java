package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付订单
 * @author: nyc 
 * @since: 2018年3月27日 下午4:18:50 
 * @history:
 */
public class XN627721Req {

    // （必填）渠道编号
    @NotBlank(message = "渠道编号不能为空")
    private String payCode;

    // （必填）支付组号
    @NotBlank(message = "支付组号不能为空")
    private String payGroup;

    // 支付渠道
    @NotBlank(message = "支付渠道不能为空")
    private String payType;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
