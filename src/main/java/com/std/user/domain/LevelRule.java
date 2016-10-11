package com.std.user.domain;

import com.std.user.dao.base.ABaseDO;

public class LevelRule extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 等级
    private String level;

    // 数量最小值
    private Long amountMin;

    // 数量最大值
    private Long amountMax;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(Long amountMin) {
        this.amountMin = amountMin;
    }

    public Long getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(Long amountMax) {
        this.amountMax = amountMax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
