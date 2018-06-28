package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增介绍奖
 * @author: nyc 
 * @since: 2018年6月28日 下午4:27:18 
 * @history:
 */
public class XN627240Req {
    // （必填） 等级
    @NotBlank(message = "等级不能为空")
    private String level;

    // （必填）可介绍 等级
    @NotBlank(message = "可介绍 不能为空")
    private String introLevel;

    // （必填）奖励百分比
    @NotBlank(message = "奖励百分比不能为空")
    private String percent;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getLevel() {
        return level;
    }

    public String getIntroLevel() {
        return introLevel;
    }

    public String getPercent() {
        return percent;
    }

    public String getUpdater() {
        return updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setIntroLevel(String introLevel) {
        this.introLevel = introLevel;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
