package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627033Req {

    // 编号（必填）
    @NotBlank(message = "编号不能为空")
    private String code;

    // 更新人（必填）
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注(选填)
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
