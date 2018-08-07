package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN627901Req {

    // （必填）购车编号
    @NotEmpty(message = "购车编号不能为空")
    private List<String> codeList;

    // （必填）下单人
    @NotBlank(message = "下单人不能为空")
    private String applyUser;

    // （选填）下单备注
    private String applyNote;

    public List<String> getCodeList() {
        return codeList;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

}
