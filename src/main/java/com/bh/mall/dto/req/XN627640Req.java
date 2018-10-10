package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 提交订单
 * @author: nyc 
 * @since: 2018年3月28日 上午10:34:21 
 * @history:
 */
public class XN627640Req {

    // 购物车订单编号列表
    @NotEmpty(message = "订单编号不能为空")
    private List<String> cartList;

    // （必填）下单人
    @NotBlank(message = "下单人不能为空")
    private String applyUser;

    // （选填）下单备注
    private String applyNote;

    // （必填）订单归属人
    @NotBlank(message = "订单归属人不能为空")
    private String toUser;

    // （必填）收件人
    @NotBlank(message = "收件人不能为空")
    private String signer;

    // （必填）收件人电话
    @NotBlank(message = "收件人电话不能为空")
    @Length(max=16,message="电话长度不能超过16位")
    private String mobile;

    // （必填）收货地址
    @NotBlank(message = "收货地址不能为空")
    private String address;

    // （必填）区
    @NotBlank(message = "区不能为空")
    private String area;

    // （必填）市
    @NotBlank(message = "市不能为空")
    private String city;

    // （必填）省
    @NotBlank(message = "省不能为空")
    private String province;


    public List<String> getCartList() {
        return cartList;
    }

    public void setCartList(List<String> cartList) {
        this.cartList = cartList;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
