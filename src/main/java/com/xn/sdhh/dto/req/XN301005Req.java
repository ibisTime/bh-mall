package com.xn.sdhh.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询角色
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:49:45 
 * @history:
 */
public class XN301005Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 角色名称(选填)
    private String name;

    // 角色等级(选填)
    private String level;

    // 更新人(选填)
    private String updater;

    // 系统编号（必填）
    @NotBlank
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
