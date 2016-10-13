package com.std.user.domain;

import java.util.Date;

import com.std.user.dao.base.ABaseDO;

public class URead extends ABaseDO {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String smsCode;

    private Integer userId;

    private String status;

    private Date readDatetime;

    private B2cSms b2cSms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReadDatetime() {
        return readDatetime;
    }

    public void setReadDatetime(Date readDatetime) {
        this.readDatetime = readDatetime;
    }

    public B2cSms getB2cSms() {
        return b2cSms;
    }

    public void setB2cSms(B2cSms b2cSms) {
        this.b2cSms = b2cSms;
    }
}
