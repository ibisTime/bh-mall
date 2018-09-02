package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 需要发货的订单(辅助发货)
* @author: nyc 
* @since: 2018-08-21 16:33:23
* @history:
*/
public class DeliveOrder extends ABaseDO {

    private static final long serialVersionUID = -3311732787308940606L;

    // 编号
    private String code;

    // 分类
    private String kind;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String specsCode;

    // 规格名称
    private String specsName;

    // 关联箱码
    private String barCode;

    // 图片
    private String pic;

    // 数量
    private Integer quantity;

    // 单价
    private Long price;

    // 总价
    private Long amount;

    // 运费
    private Long yunfei;

    // 状态
    private String status;

    // 下单人
    private String applyUser;

    // 下单时间
    private Date applyDatetime;

    // 真实姓名
    private String realName;

    // 下单备注
    private String applyNote;

    // 收件人姓名
    private String signer;

    // 收件人电话
    private String mobile;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 收货地址
    private String address;

    // 发货人
    private String deliver;

    // 发货时间
    private Date deliveDatetime;

    // 物流单号
    private String logisticsCode;

    // 物流公司
    private String logisticsCompany;

    // *******************db***************

    // 关键字（下单人名称模糊查）
    private String keyword;

    // 发货人
    private String deliveName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getPrice() {
        return price;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getYunfei() {
        return yunfei;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setYunfei(Long yunfei) {
        this.yunfei = yunfei;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getSigner() {
        return signer;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliveDatetime(Date deliveDatetime) {
        this.deliveDatetime = deliveDatetime;
    }

    public Date getDeliveDatetime() {
        return deliveDatetime;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDeliveName() {
        return deliveName;
    }

    public void setDeliveName(String deliveName) {
        this.deliveName = deliveName;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

}
