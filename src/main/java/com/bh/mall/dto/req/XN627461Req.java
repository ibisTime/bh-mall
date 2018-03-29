package com.bh.mall.dto.req;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 批量审批线下充值订单
 * @author: xieyj 
 * @since: 2017年5月12日 上午9:58:24 
 * @history:
 */
public class XN627461Req {

    // 充值订单编号(必填)
    @NotNull
    private List<String> codeList;

    // 支付回录人(必填)
    @NotBlank
    private String payUser;

    // 审核结果1 通过 0 不通过(必填)
    @NotBlank
    private String payResult;

    // 支付渠道的说明(选填)
    private String payNote;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
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
