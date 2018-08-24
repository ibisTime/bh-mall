package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
* 内购订单
* @author: chenshan 
* @since: 2018-03-27 15:14:49
* @history:
*/
public class InnerOrder extends ABaseDO {

    private static final long serialVersionUID = -7946984863965063644L;

    // 编号
    private String code;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 产品编号
    private String specsCode;

    // 产品名称
    private String specsName;

    // 缩略图
    private String pic;

    // 数量
    private Integer quantity;

    // 单价
    private Long price;

    // 下单代理
    private String applyUser;

    // 真实姓名
    private String realName;

    // 下单代理等级
    private Integer level;

    // 团队名称
    private String teamName;

    // 下单备注
    private String applyNote;

    // 下单时间
    private Date applyDatetime;

    // 下单订单金额
    private Long amount;

    // 运费
    private Long yunfei;

    // 状态
    private String status;

    // 支付渠道
    private String payType;

    // 支付组号
    private String payGroup;

    // 支付编号
    private String payCode;

    // 支付金额
    private Long payAmount;

    // 支付时间
    private Date payDatetime;

    // 接收人名称
    private String signer;

    // 接收人手机号
    private String mobile;

    // 接收人省份
    private String province;

    // 接收人城市
    private String city;

    // 接收人区
    private String area;

    // 接收人具体地址
    private String address;

    // 发货人
    private String deliver;

    // 发货时间
    private Date deliveDatetime;

    // 物流单号
    private String logisticsCode;

    // 物流公司
    private String logisticsCompany;

    // 物流单
    private String pdf;

    // 最后审核人
    private String approver;

    // 最后审核时间
    private Date approveDatetime;

    private String approveNote;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // **************************db properties **************************
    // 开始时间
    private Date startDatetime;

    // 结束时间
    private Date endDatetime;

    // 状态查询
    private String statusForQuery;

    // 审核人姓名
    private String approveName;

    // 发货人姓名
    private String deliveName;

    // 更新时间
    private String updateName;

    // 申请人姓名
    private String applyName;

    // 状态
    private List<String> statusList;

    public String getStatusForQuery() {
        return statusForQuery;
    }

    public void setStatusForQuery(String statusForQuery) {
        this.statusForQuery = statusForQuery;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getYunfei() {
        return yunfei;
    }

    public void setYunfei(Long yunfei) {
        this.yunfei = yunfei;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
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

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public Date getDeliveDatetime() {
        return deliveDatetime;
    }

    public void setDeliveDatetime(Date deliveDatetime) {
        this.deliveDatetime = deliveDatetime;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
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

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getDeliveName() {
        return deliveName;
    }

    public void setDeliveName(String deliveName) {
        this.deliveName = deliveName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApprover() {
        return approver;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
