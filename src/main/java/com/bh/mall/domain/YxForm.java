package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 代理意向单
 * api接口： 
 * 627250 ：申请代理 （无推荐人）
 * @author: clockorange 
 * @since: Jul 5, 2018 8:25:10 PM 
 * @history:
 */

public class YxForm extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 申请人
    private String userId;

    // 微信号
    private String wxId;

    // 真实姓名
    private String realName;

    // 手机号
    private String mobile;

    // 申请等级
    private Integer applyLevel;

    // 意向归属人名字
    private String toUserId;

    // 来源
    private String source;

    // 省
    private String province;

    // 市
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

    // 最后审核人
    private String approver;

    // 申请时间
    private Date applyDatetime;

    // 审核时间
    private Date approveDatetime;

    // 状态
    private String status;

    // 备注
    private String remark;

    /***************** DB **************************/

    private Agent user;

    // 当前等级
    private Integer level;

    private Long impowerAmount;

    private Date applyDatetimeStart;

    private Date applyDatetimeEnd;

    private Date approveDatetimeStart;

    private Date approveDatetimeEnd;

    private String userIdForQuery;

    private String keyWord;

    /***********************************/

    // 获取编号
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // 获取微信号
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public Integer getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(Integer applyLevel) {
        this.applyLevel = applyLevel;
    }

    // 获取意向归属人信息
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    // 获取手机号
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    // 省， 市， 区 & 详细地址
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // 来源
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    // 获取申请时间
    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    // 最后审核人
    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    // 状态
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 备注
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    // 获取数据库 buser
    public Agent getUser() {
        return user;
    }

    public void setUser(Agent user) {
        this.user = user;
    }

    // 获取升级金额
    public Long getImpowerAmount() {
        return impowerAmount;
    }

    public void setImpowerAmount(Long impowerAmount) {
        this.impowerAmount = impowerAmount;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public Date getApproveDatetimeStart() {
        return approveDatetimeStart;
    }

    public void setApproveDatetimeStart(Date approveDatetimeStart) {
        this.approveDatetimeStart = approveDatetimeStart;
    }

    public Date getApproveDatetimeEnd() {
        return approveDatetimeEnd;
    }

    public void setApproveDatetimeEnd(Date approveDatetimeEnd) {
        this.approveDatetimeEnd = approveDatetimeEnd;
    }

    public String getUserIdForQuery() {
        return userIdForQuery;
    }

    public void setUserIdForQuery(String userIdForQuery) {
        this.userIdForQuery = userIdForQuery;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
