package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 无购物车提交订单
 * @author: nyc 
 * @since: 2018年3月28日 上午10:34:21 
 * @history:
 */
public class XN627641Req {

    // 购物车订单编号列表
    @NotBlank(message = "产品规格编号不能为空")
    private String productSpecsCode;

    // （必填）下单人
    @NotBlank(message = "下单人不能为空")
    private String applyUser;

    // （选填）下单备注
    private String applyNote;

    // （选填）订单归属人
    private String toUser;

    // 是否送货到家
    @NotBlank(message = "是否送货到家")
    private String isSendHome;

    // 数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （选填）收件人
    private String signer;

    // （选填）收件人电话
    private String mobile;

    // （选填）收货地址
    private String address;

    // （选填）区
    private String area;

    // （选填）市
    private String city;

    // （选填）省
    private String province;

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIsSendHome() {
        return isSendHome;
    }

    public void setIsSendHome(String isSendHome) {
        this.isSendHome = isSendHome;
    }

    public String getSpecsCode() {
        return productSpecsCode;
    }

    public void setSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
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

    public String getQuantity() {
        return quantity;
    }

}
