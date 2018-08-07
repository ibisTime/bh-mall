package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 支付云仓订单
 * @author: LENOVO 
 * @since: 2018年8月1日 下午9:54:16 
 * @history:
 */
public class XN627902Req {

    // （必填）订单编号
    @NotEmpty(message = "订单编号不能为空")
    private List<String> codeList;

    // （必填）支付渠道
    @NotBlank(message = "支付渠道不能为空")
    private String payType;

    public List<String> getCodeList() {
        return codeList;
    }

    public String getPayType() {
        return payType;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
