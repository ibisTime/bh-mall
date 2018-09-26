package com.bh.mall.dto.req;

import java.util.List;

/**
 * 分页查意向代理
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627326Req extends APageReq {

    private static final long serialVersionUID = 1793986894415261389L;

    // （选填）用户名
    private String realName;

    // （选填） 申请结束时间
    private String applyEndDatetime;

    // (选填） 申请开始时间
    private String applyStartDatetime;

    // （选填） 上级
    private String highUserId;

    // （选填） 等级
    private String level;

    // (选填)上级代理
    private String highLevel;

    // （选填） 手机号
    private String mobile;

    // （选填） 状态
    private String status;

    // （选填） 团队名称
    private String teamName;

    // （选填） 推荐人
    private String userReferee;

    // （选填）微信号
    private String wxId;

    // 注册时间起(选填)
    private String createDatetimeStart;

    // 注册时间止(选填)
    private String createDatetimeEnd;

    // 开放平台和公众平台唯一号
    private String unionId;

    // 微信h5第三方登录开放编号
    private String h5OpenId;

    // 微信app第三方登录开放编号
    private String appOpenId;

    // 用户Id
    private String noUserId;

    // （选填） 状态
    private List<String> statusList;

    // （选填） 状态
    private List<String> noStatusList;

    // 是否是操盘手
    private String isTrader;

    public String getApplyEndDatetime() {
        return applyEndDatetime;
    }

    public void setApplyEndDatetime(String applyEndDatetime) {
        this.applyEndDatetime = applyEndDatetime;
    }

    public String getApplyStartDatetime() {
        return applyStartDatetime;
    }

    public void setApplyStartDatetime(String applyStartDatetime) {
        this.applyStartDatetime = applyStartDatetime;
    }

    public String getHighUserId() {
        return highUserId;
    }

    public void setHighUserId(String highUserId) {
        this.highUserId = highUserId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(String createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public String getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(String createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getH5OpenId() {
        return h5OpenId;
    }

    public void setH5OpenId(String h5OpenId) {
        this.h5OpenId = h5OpenId;
    }

    public String getAppOpenId() {
        return appOpenId;
    }

    public void setAppOpenId(String appOpenId) {
        this.appOpenId = appOpenId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel;
    }

    public String getNoUserId() {
        return noUserId;
    }

    public void setNoUserId(String noUserId) {
        this.noUserId = noUserId;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public List<String> getNoStatusList() {
        return noStatusList;
    }

    public String getIsTrader() {
        return isTrader;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public void setNoStatusList(List<String> noStatusList) {
        this.noStatusList = noStatusList;
    }

    public void setIsTrader(String isTrader) {
        this.isTrader = isTrader;
    }

}
