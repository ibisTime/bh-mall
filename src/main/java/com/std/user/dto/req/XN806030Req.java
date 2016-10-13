package com.std.user.dto.req;

public class XN806030Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 种类（选填）
    private String type;

    // 内容标题（选填）
    private String account;

    // 菜单编号（选填）
    private String companyCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
