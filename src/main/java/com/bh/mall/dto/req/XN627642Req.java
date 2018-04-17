package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付订单
 * @author: nyc 
 * @since: 2018年3月28日 下午1:59:11 
 * @history:
 */
public class XN627642Req {

    // （必填）渠道编号
    @NotBlank(message = "渠道编号不能为空")
    private String codeList;

    // （必填）支付渠道
    @NotBlank(message = "支付渠道不能为空")
    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCodeList() {
        return codeList;
    }

    public void setCodeList(String codeList) {
        this.codeList = codeList;
    }

}
