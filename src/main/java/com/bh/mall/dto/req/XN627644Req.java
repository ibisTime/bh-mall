package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.bh.mall.domain.Order;

/**
 * 修改收货地址以及运费
 * @author: nyc 
 * @since: 2018年3月28日 下午1:59:11 
 * @history:
 */
public class XN627644Req {
    // （必填）编号List
    @NotBlank(message = "编号不能为空")
    private List<Order> codeList;

    // （必填）审核人
    @NotBlank(message = "审核人不能为空")
    private String approver;

    // （选填）审核备注
    private String approveNote;

    public List<Order> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<Order> codeList) {
        this.codeList = codeList;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

}
