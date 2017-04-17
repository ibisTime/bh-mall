package com.std.user.dto.req;

public class XN805151Req {

    // 开放编号（必填）
    private String code;

    // 是否注册环信（必填）
    private String isRegHx;

    // 手机号（必填）
    private String mobile;

    // 类型(选填)
    private String type;

    // 公司编号（必填）
    private String companyCode;

    // 系统编号(必填)
    private String systemCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsRegHx() {
        return isRegHx;
    }

    public void setIsRegHx(String isRegHx) {
        this.isRegHx = isRegHx;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
