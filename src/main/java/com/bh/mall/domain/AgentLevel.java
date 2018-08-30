package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 代理等级
 * @author: nyc 
 * @since: 2018年1月31日 下午2:04:03 
 * @history:
 */
public class AgentLevel extends ABaseDO {

    private static final long serialVersionUID = -8708365669948607235L;

    // 编号
    private String code;

    // 等级
    private Integer level;

    // 等级名称
    private String name;

    // 本等级是够可被意向
    private String isIntent;

    // 本等级是否可被介绍
    private String isJsAward;

    // 是够需要实名
    private String isRealName;

    // 本等级是够开启云仓
    private String isWare;

    // 本等级授权是否需要公司审核
    private String isCompanyImpower;

    // 授权单是否可以自发
    private String isSend;

    // 本等级门槛款
    private Long minCharge;

    // 授权单金额
    private Long amount;

    // 红线金额
    private Long redAmount;

    // 本等级每次最低充值金额
    private Long minChargeAmount;

    // 本等级门槛可有余额
    private Long minSurplus;

    // 升至本等级是否公司审核
    private String isCompanyApprove;

    // 升至本等级半门槛推荐人数
    private Integer reNumber;

    // 升至本等级是否余额清零
    private String isReset;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // **************************db properties **************************
    // 开始等级
    private Integer lowLevel;

    // 结束等级
    private Integer highLevel;

    // 更新名称
    private String updateName;

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

    public String getIsJsAward() {
        return isJsAward;
    }

    public void setIsJsAward(String isJsAward) {
        this.isJsAward = isJsAward;
    }

    public Long getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(Long minCharge) {
        this.minCharge = minCharge;
    }

    public String getIsCompanyImpower() {
        return isCompanyImpower;
    }

    public void setIsCompanyImpower(String isCompanyImpower) {
        this.isCompanyImpower = isCompanyImpower;
    }

    public String getIsCompanyApprove() {
        return isCompanyApprove;
    }

    public void setIsCompanyApprove(String isCompanyApprove) {
        this.isCompanyApprove = isCompanyApprove;
    }

    public Integer getReNumber() {
        return reNumber;
    }

    public void setReNumber(Integer reNumber) {
        this.reNumber = reNumber;
    }

    public String getIsReset() {
        return isReset;
    }

    public void setIsReset(String isReset) {
        this.isReset = isReset;
    }

    public Integer getLowLevel() {
        return lowLevel;
    }

    public void setLowLevel(Integer lowLevel) {
        this.lowLevel = lowLevel;
    }

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

    public Long getMinSurplus() {
        return minSurplus;
    }

    public void setMinSurplus(Long minSurplus) {
        this.minSurplus = minSurplus;
    }

    public String getIsWare() {
        return isWare;
    }

    public void setIsWare(String isWare) {
        this.isWare = isWare;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public Integer getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(Integer highLevel) {
        this.highLevel = highLevel;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

}
