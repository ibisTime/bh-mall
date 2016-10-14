package com.std.user.dto.req;

public class XN806151Req {

    // 类别（选填）
    private String certificateCode;

    // 名称（选填）
    private String companyCode;

    // 状态（选填）
    private String status;

    // 更新人（选填）
    private String applyUser;

    // 更新人（选填）
    private String approveUser;

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
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

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }
}
