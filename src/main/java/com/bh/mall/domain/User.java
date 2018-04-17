package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
* 代理
* @author: chenshan 
* @since: 2018-01-29 15:45:50
* @history:
*/
public class User extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // userId,登陆名,手机号,微信号,头像,
    // userId,login_name,mobile,wx_id,photo,

    // userId
    private String userId;

    // 登陆名
    private String loginName;

    // 手机号
    private String mobile;

    // 微信号
    private String wxId;

    // 头像
    private String photo;

    // 昵称,登陆密码,登陆密码强度,交易密码，交易密码强度，
    // nickname,password,pwd_strength,trade_pwd,trade_pwd_strength,

    // 昵称
    private String nickname;

    // 登陆密码
    private String loginPwd;

    // 登陆密码强度
    private String loginPwdStrength;

    // 交易密码
    private String tradePwd;

    // 交易密码强度
    private String tradePwdStrength;

    // 身份标识,目前用户等级,需升等级，推荐人,介绍人，
    // kind,level,apply_level,user_referee,introducer,

    // 身份标识
    private String kind;

    // 目前用户等级
    private Integer level;

    // 需升等级
    private Integer applyLevel;

    // 推荐人
    private String userReferee;

    // 介绍人
    private String introducer;

    // 上级用户编号，团队名称，证件类型,证件号码,身份证正面照片，
    // high_user_id,team_name,id_kind,id_no,id_front,

    // 上级用户编号
    private String highUserId;

    // 团队名称
    private String teamName;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 身份证正面照片
    private String idFront;

    // 身份证背面照片，手持身份证照片，真实姓名,角色编号,状态，
    // id_behind,id_hand,real_name,role_code,status,

    // 身份证背面照片
    private String idBehind;

    // 手持身份证照片
    private String idHand;

    // 真实姓名
    private String realName;

    // 角色编号
    private String roleCode;

    // 状态
    private String status;

    // 来源，最开始申请时间，关联管理员，红线金额，开放平台和公众平台唯一号,
    // source,apply_datetime,manager,red_amount,union_id,

    // 来源
    private String source;

    // 最开始申请时间
    private Date applyDatetime;

    // 关联管理员
    private String manager;

    // 红线金额
    private Long redAmount;

    // 开放平台和公众平台唯一号
    private String unionId;

    // 微信h5第三方登录开放编号,微信app第三方登录开放编号,省,市,区(县),
    // h5_open_id,app_open_id,province,city,area,

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

    // 具体地址,注册时间,最后更新人,最后更新时间,最后审核人，
    // address,create_datetime,updater,update_datetime,approver,
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

    // 最后审核时间，最后一条代理轨迹记录，备注
    // approv_datetime,last_agent_log,remark
    // 最后审核时间
    private Date approveDatetime;

    // 最后一条代理轨迹记录
    private String lastAgentLog;

    // 备注
    private String remark;

    // 公司编号
    private String companyCode;

    // 系统编号
    private String systemCode;

    private List<User> userList;

    private List<AgencyLog> logList;

    // ******db******
    // 手机号和登录名都可登录(1 手机号 2 登录名，3 手机号和登录名)
    private String loginType;

    // 注册时间起
    private Date createDatetimeStart;

    // 注册时间止
    private Date createDatetimeEnd;

    // 用户推荐人
    private User refereeUser;

    private Integer refeereLevel;

    /***** 模糊查询使用字段 ******/
    // 登录名模糊查询
    private String loginNameForQuery;

    // 手机号模糊查询
    private String mobileForQuery;

    // 真实姓名模糊查询
    private String realNameForQuery;

    // 省份
    private String provinceForQuery;

    // 城市
    private String cityForQuery;

    // 县区
    private String areaForQuery;

    // 上级代理
    private Integer highLevel;

    private User highUser;

    // 申请时间起(选填)
    private Date applyDatetimeStart;

    // 申请时间止(选填)
    private Date applyDatetimeEnd;

    private String keyWord;

    // 介绍人姓名
    private String introduceName;

    private String manageName;

    private Long impowerAmount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<AgencyLog> getLogList() {
        return logList;
    }

    public void setLogList(List<AgencyLog> logList) {
        this.logList = logList;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getImpowerAmount() {
        return impowerAmount;
    }

    public void setImpowerAmount(Long impowerAmount) {
        this.impowerAmount = impowerAmount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
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
        this.tradePwdStrength = tradePwdStrength;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(Integer applyLevel) {
        this.applyLevel = applyLevel;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getHighUserId() {
        return highUserId;
    }

    public void setHighUserId(String highUserId) {
        this.highUserId = highUserId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    public String getIdBehind() {
        return idBehind;
    }

    public void setIdBehind(String idBehind) {
        this.idBehind = idBehind;
    }

    public String getIdHand() {
        return idHand;
    }

    public void setIdHand(String idHand) {
        this.idHand = idHand;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Long getRedAmount() {
        return redAmount;
    }

    public void setRedAmount(Long redAmount) {
        this.redAmount = redAmount;
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

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getLastAgentLog() {
        return lastAgentLog;
    }

    public void setLastAgentLog(String lastAgentLog) {
        this.lastAgentLog = lastAgentLog;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
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

    public User getRefereeUser() {
        return refereeUser;
    }

    public void setRefereeUser(User refereeUser) {
        this.refereeUser = refereeUser;
    }

    public Integer getRefeereLevel() {
        return refeereLevel;
    }

    public void setRefeereLevel(Integer refeereLevel) {
        this.refeereLevel = refeereLevel;
    }

    public String getLoginNameForQuery() {
        return loginNameForQuery;
    }

    public void setLoginNameForQuery(String loginNameForQuery) {
        this.loginNameForQuery = loginNameForQuery;
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(Integer highLevel) {
        this.highLevel = highLevel;
    }

    public User getHighUser() {
        return highUser;
    }

    public void setHighUser(User highUser) {
        this.highUser = highUser;
    }

    public String getIntroduceName() {
        return introduceName;
    }

    public void setIntroduceName(String introduceName) {
        this.introduceName = introduceName;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

}
