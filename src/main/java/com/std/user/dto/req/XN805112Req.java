package com.std.user.dto.req;

public class XN805112Req {

    // 编号（必填）
    private String code;

    // 等级（必填）
    private String level;

    // 数量最小值（必填）
    private String amountMin;

    // 数量最大值（必填）
    private String amountMax;

    // 备注（选填）
    private String remark;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(String amountMin) {
        this.amountMin = amountMin;
    }

    public String getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(String amountMax) {
        this.amountMax = amountMax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
