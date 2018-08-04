package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/** 
 * 地址
 * @author: xieyj 
 * @since: 2015年8月26日 下午10:45:36 
 * @history:
 */
public class Address extends ABaseDO {

    private static final long serialVersionUID = -2168684738489818621L;

    // 收件编号
    private String code;

    // 用户编号
    private String userId;

    // 收件人名称
    private String receiver;

    // 手机号
    private String mobile;

    // 省份
    private String province;

    // 城市
    private String city;

    // 区
    private String area;

    // 具体地址
    private String address;

    // 是否默认地址
    private String isDefault;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
}
