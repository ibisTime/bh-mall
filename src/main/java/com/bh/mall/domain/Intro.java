package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 介绍奖
* @author: chenshan 
* @since: 2018-04-03 18:57:33
* @history:
*/
public class Intro extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 等级
    private Integer level;

    // 可介绍等级
    private Integer introLevel;

    // 奖励比例
    private Double percent;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
    

    public Integer getIntroLevel() {
        return introLevel;
    }

    public void setIntroLevel(Integer introLevel) {
        this.introLevel = introLevel;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

}
