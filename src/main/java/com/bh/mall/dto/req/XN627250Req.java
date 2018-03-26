package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 代理申请
 * @author: chenshan 
 * @since: 2018年3月26日 下午2:41:43 
 * @history:
 */
public class XN627250Req {
    // 微信编号
    @NotBlank
    private String code;

    // （必填） 姓名
    @NotBlank
    private String realName;

    // （必填）
    @NotBlank
    private String wxId;

    // (必填） 申请等级
    @NotBlank
    private String level;

    // （必填） 电话
    @NotBlank
    private String mobile;

    // （必填） 省
    @NotBlank
    private String province;

    // （必填） 市
    @NotBlank
    private String city;

    // （必填） 区
    @NotBlank
    private String area;

    // （必填） 详细地址
    @NotBlank
    private String address;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
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

}
