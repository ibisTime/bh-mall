package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 
 * @author: nyc 
 * @since: 2018年1月31日 下午2:08:40 
 * @history:
 */
public class AgentUpgrade extends ABaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = 7360253837274350094L;

    // 编号
    private String code;

    // 代理编号
    private Integer level;

    // 本等级升级是否公司审核
    private String isCompanyApprove;

    // 半门槛推荐人数
    private Integer reNumber;

    // 本等级升级是否余额清零
    private String isReset;

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

    public String getIsCompanyApprove() {
        return isCompanyApprove;
    }

    public void setIsCompanyApprove(String isCompanyApprove) {
        this.isCompanyApprove = isCompanyApprove;
    }

    public String getIsReset() {
        return isReset;
    }

    public void setIsReset(String isReset) {
        this.isReset = isReset;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getReNumber() {
        return reNumber;
    }

    public void setReNumber(Integer reNumber) {
        this.reNumber = reNumber;
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
