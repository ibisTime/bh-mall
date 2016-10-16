package com.std.user.dto.req;

public class XN806141Req {

    // 资质编号（必填）
    private String code;

    // 所属公司编号（必填）
    private String status;

    // 申请人（必填）
    private String approveUser;

    // 申请人（必填）
    private String approveNote;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }
}
