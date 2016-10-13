package com.std.user.dto.req;

public class XN806050Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 种类（选填）
    private String name;

    // 内容标题（选填）
    private String location;

    // 菜单编号（选填）
    private String status;

    // 种类（选填）
    private String updater;

    // 内容标题（选填）
    private String companyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
