/**
 * @Title XN802710Req.java 
 * @Package com.std.account.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年5月18日 上午9:49:57 
 * @version V1.0   
 */
package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author: nyc 
 * @since: 2018年4月17日 下午9:25:37 
 * @history:
 */
public class XN627463Req {
    // 充值订单编号(必填)
    @NotBlank(message = "充值订单编号不能为空")
    private String code;

    // 支付回录人(必填)
    @NotBlank(message = " 支付回录人不能为空")
    private String payUser;

    // 审核结果1 通过 0 不通过(必填)
    @NotBlank(message = "审核结果不能为空")
    private String payResult;

    // 支付渠道的说明(选填)
    private String payNote;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }

}
