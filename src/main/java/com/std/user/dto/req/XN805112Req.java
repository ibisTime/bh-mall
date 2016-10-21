package com.std.user.dto.req;

public class XN805112Req {

    // 编号（必填）
    private String code;

    // 等级名称（必填）
    private String name;

    // 作用（必填）
    private String effect;

    // 备注（选填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
