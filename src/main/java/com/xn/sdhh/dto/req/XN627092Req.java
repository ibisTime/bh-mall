package com.xn.sdhh.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627092Req {
    // 公司编号(必填)
    @NotBlank
    private String companyCode;

    // 系统编号(必填)
    @NotBlank
    private String systemCode;

    // url(必填)
    @NotBlank
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
