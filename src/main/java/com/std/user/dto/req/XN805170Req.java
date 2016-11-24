package com.std.user.dto.req;

public class XN805170Req {
    // 手机(必填)
    private String mobile;

    // 公司编号(必填)
    private String companyCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
