package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 批量审单
 * @author: nyc 
 * @since: 2018年3月27日 下午7:24:18 
 * @history:
 */
public class XN627727Req {

    // （必填） 编号
    @NotEmpty(message = "编号不能为空")
    private List<String> codeList;

    // （必填） 审核人
    @NotEmpty(message = "审核人不能为空")
    private String approver;

    // （选填） 备注
    private String remark;

    public List<String> getCodeList() {
        return codeList;
    }

    public String getRemark() {
        return remark;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

}
