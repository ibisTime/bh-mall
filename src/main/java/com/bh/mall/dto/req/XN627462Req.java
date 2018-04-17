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
 * @author: haiqingzheng 
 * @since: 2017年5月18日 上午9:49:57 
 * @history:
 */
public class XN627462Req {

    // 发起人(必填)
    @NotBlank(message = "不能为空")
    private String applyUser;

    // 充值账户(必填)
    @NotBlank(message = "不能为空")
    private String accountNumber;

    // 支付资金(必填)
    @NotBlank(message = "不能为空")
    private String amount;

    // 线上充值渠道
    private String channelType;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
