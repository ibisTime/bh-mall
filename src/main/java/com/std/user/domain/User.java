package com.std.user.domain;

import java.util.Date;

import com.std.user.dao.base.ABaseDO;

/**
 * @author: xieyj 
 * @since: 2017年7月16日 下午4:51:56 
 * @history:
 */
public class User extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1975331351390818527L;

    // userId
    private String userId;

    // 登陆名
    private String loginName;

    // 手机号
    private String mobile;

    // 头像
    private String photo;

    // 昵称
    private String nickname;

    // 登陆密码
    private String loginPwd;

    // 登陆密码强度
    private String loginPwdStrength;

    // 身份标识
    private String kind;

    // 用户等级
    private String level;

    // 推荐人
    private String userReferee;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 真实姓名
    private String realName;

    // 支付密码
    private String tradePwd;

    // 支付密码强度
    private String tradePwdStrength;

    // 芝麻分
    private String zmScore;

    // 芝麻认证时间
    private Date zmAuthDatetime;

    // 角色编号
    private String roleCode;

    // 分成比例
    private Double divRate;

    // 状态
    private String status;

    // 开放平台和公众平台唯一号
    private String unionId;

    // 微信h5第三方登录开放编号
    private String h5OpenId;

    // 微信app第三方登录开放编号
    private String appOpenId;

    // 性别(1 男 0 女)
    private String gender;

    // 介绍
    private String introduce;

    // 生日
    private String birthday;

    // 邮箱
    private String email;

    // 学位
    private String diploma;

    // 职业
    private String occupation;

    // 毕业时间
    private Date gradDatetime;

    // 工作年限
    private String workTime;

    // 附件
    private String pdf;

    // 省
    private String province;

    // 市区
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

    // 经度
    private String longitude;

    // 维度
    private String latitude;

    // 注册时间
    private Date createDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 所属公司
    private String companyCode;

    // 系统编号
    private String systemCode;

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

    /***** 辅助字段 ******/

    // 是否设置交易密码
    private boolean tradepwdFlag;

    // ossUserId
    private String ossUserId;

    // 关注数
    private Long totalFollowNum;

    // 粉丝数
    private Long totalFansNum;

    // 手机号和登录名都可登录(1 手机号 2 登录名，3 手机号和登录名)
    private String loginType;

    // 注册时间起
    private Date createDatetimeStart;

    // 注册时间止
    private Date createDatetimeEnd;

    // 用户推荐人
    private User refereeUser;

    private Integer refeereLevel;

    public Integer getRefeereLevel() {
        return refeereLevel;
    }

    public void setRefeereLevel(Integer refeereLevel) {
        this.refeereLevel = refeereLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Double getDivRate() {
        return divRate;
    }

    public void setDivRate(Double divRate) {
        this.divRate = divRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getOssUserId() {
        return ossUserId;
    }

    public void setOssUserId(String ossUserId) {
        this.ossUserId = ossUserId;
    }

    public Long getTotalFollowNum() {
        return totalFollowNum;
    }

    public void setTotalFollowNum(Long totalFollowNum) {
        this.totalFollowNum = totalFollowNum;
    }

    public Long getTotalFansNum() {
        return totalFansNum;
    }

    public void setTotalFansNum(Long totalFansNum) {
        this.totalFansNum = totalFansNum;
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

    public boolean isTradepwdFlag() {
        return tradepwdFlag;
    }

    public void setTradepwdFlag(boolean tradepwdFlag) {
        this.tradepwdFlag = tradepwdFlag;
    }

    public Date getGradDatetime() {
        return gradDatetime;
    }

    public void setGradDatetime(Date gradDatetime) {
        this.gradDatetime = gradDatetime;
    }

    public String getZmScore() {
        return zmScore;
    }

    public void setZmScore(String zmScore) {
        this.zmScore = zmScore;
    }

    public Date getZmAuthDatetime() {
        return zmAuthDatetime;
    }

    public void setZmAuthDatetime(Date zmAuthDatetime) {
        this.zmAuthDatetime = zmAuthDatetime;
    }
}
