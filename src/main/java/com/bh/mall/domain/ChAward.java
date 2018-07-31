package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 出货奖励
* @author: nyc 
* @since: 2018-06-28 16:54:46
* @history:
*/
public class ChAward extends ABaseDO {

    private static final long serialVersionUID = 150949363895516912L;

    // 编号
    private String code;

    // 等级
    private Integer level;

    // 起始金额
    private Long startAmount;

    // 终止金额
    private Long endAmount;

    // 奖励百分比
    private Double percent;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // *******************DB******************

    // 等级名称
    private String name;

    // 出货金额
    private Long amount;

    public String getCode() {
        return code;
    }

    public Integer getLevel() {
        return level;
    }

    public Long getStartAmount() {
        return startAmount;
    }

    public Long getEndAmount() {
        return endAmount;
    }

    public Double getPercent() {
        return percent;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setStartAmount(Long startAmount) {
        this.startAmount = startAmount;
    }

    public void setEndAmount(Long endAmount) {
        this.endAmount = endAmount;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
