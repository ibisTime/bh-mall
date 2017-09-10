package com.std.user.dto.req;

public class XN805211Req {
    // 编号（必填）
    private String code;

    // 审核结果（必填）
    private String approveResult;

    // 审核结果（必填）
    private String approveUser;

    // 毕业时间（必填）
    private String gradDatetime;

    // 选填（选填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getGradDatetime() {
        return gradDatetime;
    }

    public void setGradDatetime(String gradDatetime) {
        this.gradDatetime = gradDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
