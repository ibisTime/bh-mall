package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:41:26 
 * @history:
 */
public class XN627400Req {

    // 用户编号（必填）
    @NotBlank
    private String userId;

    // 类型（必填）
    @NotBlank
    private String type;

    // 收件人名称（必填）
    @NotBlank
    private String receiver;

    // 手机号（必填）
    @NotBlank
    private String mobile;

    // 省份（必填）
    @NotBlank
    private String province;

    // 城市（必填）
    @NotBlank
    private String city;

    // 区（必填）
    @NotBlank
    private String area;

    // 具体地址（必填）
    @NotBlank
    private String address;

    // 是否默认地址
    @NotBlank
    private String isDefault;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
