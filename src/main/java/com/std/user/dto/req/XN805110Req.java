package com.std.user.dto.req;

public class XN805110Req {

    // 等级（必填）
    private String name;

    // 数量最小值（必填）
    private String amountMin;

    // 数量最大值（必填）
    private String amountMax;

    // 作用
    private String effect;

    // 备注（选填）
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
