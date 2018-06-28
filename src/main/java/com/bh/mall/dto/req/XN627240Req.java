package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.bh.mall.domain.Intro;

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

    // （必填）介绍等级
    @NotBlank(message = "可介绍等级不能为空")
    private List<Intro> introList;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getLevel() {
        return level;
    }

    public List<Intro> getIntroList() {
        return introList;
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

    public void setIntroList(List<Intro> introList) {
        this.introList = introList;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
