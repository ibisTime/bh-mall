package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 增加角色
 * @author: xieyj 
 * @since: 2016年5月16日 下午10:53:50 
 * @history:
 */
public class XN627040Req {

    // 角色名称(必填)
	@NotBlank(message="名字不能为空")
    private String name;


    // 更新人(必填)
	@NotBlank(message="更新人不能为空")
    private String updater;

    // 备注(选填)
    private String remark;

    // 系统编号（必填）
    @NotBlank(message="系统编号不能为空")
    private String systemCode;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
