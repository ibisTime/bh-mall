package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 团队销售数量统计
* @author: nyc 
* @since: 2018-08-18 15:54:29
* @history:
*/
public class OrderReport extends ABaseDO {

    private static final long serialVersionUID = -1962280389230443825L;

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
    private String pic;

    // 图片
    private Integer quantity;

    // 数量
    private Long price;

    // 单价
    private String toUserId;

    // 订单归属人
    private String toUserName;

    // 订单归属人名字
    private Long amount;

    // 总价
    private String yunfei;

    // 运费
    private String status;

    // 状态
    private String payType;

    // 支付渠道
    private String payGroup;

    // 支付金额
    private Long payAmount;

    // 支付时间
    private Date payDatetime;

    // 渠道编号
    private String payCode;

    // 下单人
    private String applyUser;

    // 上级
    private String highUserId;

    // 下单人等级
    private Integer level;

    // 真实姓名
    private String realName;

    // 团队名称
    private String teamName;

    // 团队长名称
    private String teamLeader;

    // 下单备注
    private String applyNote;

    // 下单时间
    private Date applyDatetime;

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

    // 箱码
    private String barCode;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 更新备注
    private String updateNote;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 审核备注
    private String approveNote;

    // 备注
    private String remark;

    // **************db***************

    // 下单开始时间
    private Date startDatetime;

    // 下单结束时间
    private Date endDatetime;

    // 代理
    private Agent agent;

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

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setYunfei(String yunfei) {
        this.yunfei = yunfei;
    }

    public String getYunfei() {
        return yunfei;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setHighUserId(String highUserId) {
        this.highUserId = highUserId;
    }

    public String getHighUserId() {
        return highUserId;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
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

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateNote(String updateNote) {
        this.updateNote = updateNote;
    }

    public String getUpdateNote() {
        return updateNote;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApprover() {
        return approver;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public Integer getLevel() {
        return level;
    }

    public Date getDeliveDatetime() {
        return deliveDatetime;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setDeliveDatetime(Date deliveDatetime) {
        this.deliveDatetime = deliveDatetime;
    }

}
