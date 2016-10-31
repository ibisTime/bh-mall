package com.std.user.domain;

import java.util.Date;

import com.std.user.dao.base.ABaseDO;

public class Company extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型(1 公司 2个体户)
    private String type;

    // 真实姓名/公司名称
    private String name;

    // 缩写
    private String abbrName;

    // 工商营业执照号/身份证正反照
    private String gsyyzzh;

    // 域名
    private String domain;

    // logo
    private String logo;

    // 图标
    private String icon;

    // 广告语
    private String slogan;

    // 法人
    private String corporation;

    // 二维码
    private String qrCode;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 具体地址
    private String address;

    // 经度
    private String longitude;

    // 纬度
    private String latitude;

    // 底部图片
    private String copyright;

    // 描述
    private String description;

    // 规模
    private String scale;

    // 联系人姓名
    private String contacts;

    // 电话
    private String mobile;

    // 传真
    private String fax;

    // 邮箱
    private String email;

    // qq
    private String qq;

    // 微信
    private String weChat;

    // 用户编号
    private String userId;

    // UI位置（0 禁用 1 正常）
    private String location;

    // 序号
    private Integer orderNo;

    // 是否默认（1 是 0 否）
    private String isDefault;

    // 是否热门（1 是 0 否）
    private String isHot;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 登录名
    private String loginName;

    // 密码
    private String passWord;

    // ******************db properties *******************
    // 名称
    private String nameForQuery;

    // 简称
    private String abbrNameForQuery;

    // 省份
    private String provinceForQuery;

    // 城市
    private String cityForQuery;

    // 县区
    private String areaForQuery;

    public String getNameForQuery() {
        return nameForQuery;
    }

    public void setNameForQuery(String nameForQuery) {
        this.nameForQuery = nameForQuery;
    }

    public String getAbbrNameForQuery() {
        return abbrNameForQuery;
    }

    public void setAbbrNameForQuery(String abbrNameForQuery) {
        this.abbrNameForQuery = abbrNameForQuery;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getGsyyzzh() {
        return gsyyzzh;
    }

    public void setGsyyzzh(String gsyyzzh) {
        this.gsyyzzh = gsyyzzh;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
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

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }
}
