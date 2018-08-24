package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 代理
 * @author: clockorange 
 * @since: Jul 5, 2018 8:29:39 PM 
 * @history:
 */

public class Agent extends ABaseDO {

    private static final long serialVersionUID = 502999162977851542L;

    // 代理用户id
    private String userId;

    // 分享二维码代理
    private String fromUserId;

    // 手机号
    private String mobile;

    // 微信号
    private String wxId;

    // 头像
    private String photo;

    // 昵称
    private String nickname;

    // 交易密码
    private String tradePwd;

    // 交易密码强度
    private String tradePwdStrength;

    // 目前用户等级
    private Integer level;

    // 推荐人
    private String referrer;

    // 介绍人
    private String introducer;

    // 上级用户编号
    private String highUserId;

    // 团队名称
    private String teamName;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 手持身份证照片
    private String idHand;

    // 真实姓名
    private String realName;

    // 状态
    private String status;

    // 关联管理员
    private String manager;

    // 开放平台和公众平台唯一号
    private String unionId;

    // 微信h5第三方登录开放编号
    private String h5OpenId;

    // 微信app第三方登录开放编号
    private String appOpenId;

    // 省
    private String province;

    // 市
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

    // 注册时间
    private Date createDatetime;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 最后审核人
    private String approver;

    // 审核人姓名
    private String approveName;

    // 最后审核时间
    private Date approveDatetime;

    // 最后审核时间
    private Date impowerDatetime;

    // 最后一条代理轨迹记录
    private String lastAgentLog;

    // 备注
    private String remark;

    // ****************DB*******************

    // 手机号模糊查询
    private String mobileForQuery;

    // 真实姓名模糊查询
    private String realNameForQuery;

    // 省份模糊查询
    private String provinceForQuery;

    // 城市模糊查询
    private String cityForQuery;

    // 县区模糊查询
    private String areaForQuery;

    // 代理列表
    private List<Agent> agentList;

    // 上级用户
    private String highUserName;

    // 上级用户
    private String highUserMobile;

    // 注册时间起
    private Date createDatetimeStart;

    // 注册时间止
    private Date createDatetimeEnd;

    // 关键词
    private String keyWord;

    // 上级
    private Integer highLevel;

    // 推荐人名字
    private String userRefreeName;

    // 推荐人手机号
    private String userRefreeMobile;

    // 介绍人姓名
    private String introduceName;

    // 门槛余额
    private Long mkAmount;

    // 云仓余额
    private Long wareAmount;

    // 管理员名称
    private String manageName;

    // 轨迹列表
    private List<AgentLog> logList;

    // 归属人的团队名称
    private String toTeamName;

    // 归属人的团队名称
    private List<String> statusList;

    // 获取ID
    public String getUserId() {
        return userId;
    }

    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobileForQuery() {
        return mobileForQuery;
    }

    public void setMobileForQuery(String mobileForQuery) {
        this.mobileForQuery = mobileForQuery;
    }

    public String getRealNameForQuery() {
        return realNameForQuery;
    }

    public void setRealNameForQuery(String realNameForQuery) {
        this.realNameForQuery = realNameForQuery;
    }

    public String getProvinceForQuery() {
        return provinceForQuery;
    }

    public void setProvinceForQuery(String provinceForQuery) {
        this.provinceForQuery = provinceForQuery;
    }

    public String getCityForQuery() {
        return cityForQuery;
    }

    public void setCityForQuery(String cityForQuery) {
        this.cityForQuery = cityForQuery;
    }

    public String getAreaForQuery() {
        return areaForQuery;
    }

    public void setAreaForQuery(String areaForQuery) {
        this.areaForQuery = areaForQuery;
    }

    // 获取手机号
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // 获取微信号
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    // 获取照片
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // 获取昵称
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // 获取目前用户等级
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    // 获取推荐人 & 介绍人
    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    // 获取上级用户编号
    public String getHighUserId() {
        return highUserId;
    }

    public void setHighUserId(String highUserId) {
        this.highUserId = highUserId;
    }

    public String getHighUserName() {
        return highUserName;
    }

    public String getHighUserMobile() {
        return highUserMobile;
    }

    public void setHighUserName(String highUserName) {
        this.highUserName = highUserName;
    }

    public void setHighUserMobile(String highUserMobile) {
        this.highUserMobile = highUserMobile;
    }

    // 团队名称
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    // 证件类型
    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    // 证件号码
    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    // 手持身份证照片
    public String getIdHand() {
        return idHand;
    }

    public void setIdHand(String idHand) {
        this.idHand = idHand;
    }

    // 真实姓名
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    // 状态
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 关联管理员
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    // 开放平台和公众号平台唯一号
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    // 微信h5第三方登录开放编号
    public String getH5OpenId() {
        return h5OpenId;
    }

    public void setH5OpenId(String h5OpenId) {
        this.h5OpenId = h5OpenId;
    }

    // 微信app第三方登录开放编号
    public String getAppOpenId() {
        return appOpenId;
    }

    public void setAppOpenId(String appOpenId) {
        this.appOpenId = appOpenId;
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

    // 注册时间
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    // 最后更新人
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    // 最后更新时间
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    // 最后审核人
    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    // 最后审核时间
    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    // 最后审核时间
    public Date getImpowerDatetime() {
        return impowerDatetime;
    }

    public void setImpowerDatetime(Date impowerDatetime) {
        this.impowerDatetime = impowerDatetime;
    }

    // 最后一条代理轨迹记录
    public String getLastAgentLog() {
        return lastAgentLog;
    }

    public void setLastAgentLog(String lastAgentLog) {
        this.lastAgentLog = lastAgentLog;
    }

    // 备注
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getTradePwdStrength() {
        return tradePwdStrength;
    }

    public void setTradePwdStrength(String tradePwdStrength) {
        this.tradePwd = tradePwdStrength;
    }

    public Integer getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(Integer highLevel) {
        this.highLevel = highLevel;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getUserRefreeName() {
        return userRefreeName;
    }

    public String getUserRefreeMobile() {
        return userRefreeMobile;
    }

    public String getIntroduceName() {
        return introduceName;
    }

    public void setUserRefreeName(String userRefreeName) {
        this.userRefreeName = userRefreeName;
    }

    public void setUserRefreeMobile(String userRefreeMobile) {
        this.userRefreeMobile = userRefreeMobile;
    }

    public void setIntroduceName(String introduceName) {
        this.introduceName = introduceName;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public Long getMkAmount() {
        return mkAmount;
    }

    public Long getWareAmount() {
        return wareAmount;
    }

    public void setMkAmount(Long mkAmount) {
        this.mkAmount = mkAmount;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public void setWareAmount(Long wareAmount) {
        this.wareAmount = wareAmount;
    }

    public List<AgentLog> getLogList() {
        return logList;
    }

    public void setLogList(List<AgentLog> logList) {
        this.logList = logList;
    }

    public String getToTeamName() {
        return toTeamName;
    }

    public void setToTeamName(String toTeamName) {
        this.toTeamName = toTeamName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
