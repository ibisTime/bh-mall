package com.xn.sdhh.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午4:09:34 
 * @history:
 * 
 * 修改系统参数
 */
public class XN627081Req {
    // 编号
    @NotNull
    private Long id;

    // 值（必填）
    @NotBlank
    private String cvalue;

    // 更新人（必填）
    @NotBlank
    private String updater;

    // 备注（选填）
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
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
