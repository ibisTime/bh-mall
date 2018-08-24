package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户查云仓
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627815Req {

    // id
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // 编号
    @NotBlank(message = "编号不能为空")
    private String productSpecsCode;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （必填）收件人
    @NotBlank(message = "收件人不能为空")
    private String signer;

    // （必填）收件人电话
    @NotBlank(message = "收件人电话不能为空")
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSigner() {
        return signer;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
