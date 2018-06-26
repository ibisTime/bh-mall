package com.bh.mall.domain;

import java.sql.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 等级授权设置
 * @author: chenshan 
 * @since: 2018年1月31日 下午1:51:00 
 * @history:
 */
public class AgentImpower extends ABaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = -6038753576544375788L;

    // 编号
    private String code;

    // 代理编号
    private Integer level;

    // 本等级是够可被意向
    private String isIntent;

    // 本等级是否可被介绍
    private String isIntro;

    // 本等级是否需要实名
    private String isRealName;

    // 本等级充值门槛
    private Long minCharge;

    // 本等级是否需要公司审核(授权)
    private String isCompanyImpower;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsIntent() {
        return isIntent;
    }

    public void setIsIntent(String isIntent) {
        this.isIntent = isIntent;
    }

    public String getIsIntro() {
        return isIntro;
    }

    public void setIsIntro(String isIntro) {
        this.isIntro = isIntro;
    }

    public String getIsCompanyImpower() {
        return isCompanyImpower;
    }

    public void setIsCompanyImpower(String isCompanyImpower) {
        this.isCompanyImpower = isCompanyImpower;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
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

    public Long getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(Long minCharge) {
        this.minCharge = minCharge;
    }

}
