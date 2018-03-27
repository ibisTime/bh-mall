package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 账户
* @author: chenshan 
* @since: 2018-03-27 16:30:50
* @history:
*/
public class Account extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 账户编号
    private String code;

    // 用户编号
    private String userId;

    // 户名
    private String realName;

    // 类别（B端账号/P平台账号）
    private String type;

    // 状态（0正常/1禁用）
    private String status;

    // 币种
    private String currency;

    // 余额
    private Long amount;

    // 冻结金额
    private Long frozen;

    // 创建时间
    private Date createDatetime;

    // 最近一次变动对应的流水编号
    private String lastJourCode;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFrozen() {
        return frozen;
    }

    public void setFrozen(Long frozen) {
        this.frozen = frozen;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastJourCode() {
        return lastJourCode;
    }

    public void setLastJourCode(String lastJourCode) {
        this.lastJourCode = lastJourCode;
    }

}
