package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627091Req {

    // 系统编号(必填)
    @NotBlank
    private String systemCode;

    // 公司编号(必填)
    @NotBlank
    private String companyCode;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
