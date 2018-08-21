package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 提交订单
 * @author: nyc 
 * @since: 2018年3月27日 下午3:05:44 
 * @history:
 */
public class XN627720Req {

    // （必填）规格编号
    @NotBlank(message = "规格编号不能为空")
    private String specsCode;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （必填）下单人
    @NotBlank(message = "下单人不能为空")
    private String applyUser;

    // 省
    @NotBlank(message = "省不能为空")
    private String province;

    // 市
    @NotBlank(message = "市不能为空")
    private String area;

    // 区
    @NotBlank(message = "区不能为空")
    private String city;

    // （必填）收货地址
    @NotBlank(message = "收货地址不能为空")
    private String address;

    // （必填）收件人电话
    @NotBlank(message = "收件人电话不能为空")
    private String mobile;

    // （必填）收件人
    @NotBlank(message = "收件人不能为空")
    private String signer;

    // （选填）下单备注
    private String applyNote;

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

}
