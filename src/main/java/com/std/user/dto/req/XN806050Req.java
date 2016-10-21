package com.std.user.dto.req;

public class XN806050Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 名称（选填）
    private String name;

    // 显示状态（选填）
    private String status;

    // 类型（选填）
    private String type;

    // 属于（选填）
    private String Belong;

    // 父编号（选填）
    private String parentCode;

    // 公司编号（选填）
    private String companyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBelong() {
        return Belong;
    }

    public void setBelong(String belong) {
        Belong = belong;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
