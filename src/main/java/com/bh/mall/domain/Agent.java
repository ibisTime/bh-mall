package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 代理等级表 agent
 * @author: nyc 
 * @since: 2018年1月31日 下午2:04:03 
 * @history:
 */
public class Agent extends ABaseDO {

    /**
     * tbh
     */
    private static final long serialVersionUID = -8708365669948607235L;

    // 等级
    private Integer level;

    // 等级名称
    private String name;

    // 首次授权发送金额
    private Long amount;

    // 红线金额
    private Long redAmount;

    // 本等级每次最低充值金额
    private Long minChargeAmount;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getRedAmount() {
        return redAmount;
    }

    public void setRedAmount(Long redAmount) {
        this.redAmount = redAmount;
    }

    public Long getMinChargeAmount() {
        return minChargeAmount;
    }

    public void setMinChargeAmount(Long minChargeAmount) {
        this.minChargeAmount = minChargeAmount;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
