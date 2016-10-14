package com.std.user.dto.req;

public class XN806090Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 公司编号（选填）
    private String name;

    // userId（选填）
    private String location;

    // 状态（选填）
    private String status;

    // 公司名称（选填）
    private String parentCode;

    // 公司简称（选填）
    private String contentType;

    // 类型（选填）
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
