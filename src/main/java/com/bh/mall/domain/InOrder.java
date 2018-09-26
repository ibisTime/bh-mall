package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
* 云仓订单
* @author: chenshan 
* @since: 2018-03-28 10:30:22
* @history:
*/
public class InOrder extends ABaseDO {

    private static final long serialVersionUID = -2257248845684488091L;

    // 编号
    private String code;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String specsCode;

    // 规格名称
    private String specsName;

    // 图片
    private String pic;

    // 数量
    private Integer quantity;

    // 单价
    private Long price;

    // 向谁提货
    private String toUserId;

    // 订单归属人名字
    private String toUserName;

    // 等级
    private Integer level;

    // 单价
    private Long amount;

    // 状态
    private String status;

    // 支付渠道
    private String payType;

    // 支付组号
    private String payGroup;

    // 支付金额
    private Long payAmount;

    // 支付时间
    private Date payDatetime;

    // 渠道编号
    private String payCode;

    // 下单人
    private String applyUser;

    // 下单人姓名
    private String realName;

    // 团队名称
    private String teamName;

    // 团队长
    private String teamLeader;

    // 下单备注
    private String applyNote;

    // 下单时间
    private Date applyDatetime;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String remark;

    // 是否已支付奖励
    private String isPay;

    // **************************db properties **************************
    // 下单开始时间
    private Date startDatetime;

    // 下单结束时间
    private Date endDatetime;

    // 支付开始时间
    private Date payStartDatetime;

    // 支付结束时间
    private Date payEndDatetime;

    // 状态
    private List<String> statusList;

    // 状态
    private List<String> fialStatus;

    // 关键词
    private String keyword;

    // 更信人名字
    private String updateName;

    // 用户
    private Agent agent;

    // 产品
    private Product product;

    // 订单类型
    private List<String> kindList;

    // 团队长名称
    private String leaderName;

    // 队长手机号
    private String leaderMobile;

    // 等级list
    private List<Integer> levelList;

    // 总金额
    private Long allAmount;

    // 上级
    private String highUserName;

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

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUser) {
        this.toUserId = toUser;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
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

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getFialStatus() {
        return fialStatus;
    }

    public void setFialStatus(List<String> fialStatus) {
        this.fialStatus = fialStatus;
    }

    public List<String> getKindList() {
        return kindList;
    }

    public void setKindList(List<String> kindList) {
        this.kindList = kindList;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public String getLeaderMobile() {
        return leaderMobile;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public void setLeaderMobile(String leaderMobile) {
        this.leaderMobile = leaderMobile;
    }

    public List<Integer> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Integer> levelList) {
        this.levelList = levelList;
    }

    public String getRealName() {
        return realName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Long getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(Long allAmount) {
        this.allAmount = allAmount;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public Date getPayStartDatetime() {
        return payStartDatetime;
    }

    public Date getPayEndDatetime() {
        return payEndDatetime;
    }

    public void setPayStartDatetime(Date payStartDatetime) {
        this.payStartDatetime = payStartDatetime;
    }

    public void setPayEndDatetime(Date payEndDatetime) {
        this.payEndDatetime = payEndDatetime;
    }

    public String getHighUserName() {
        return highUserName;
    }

    public void setHighUserName(String highUserName) {
        this.highUserName = highUserName;
    }

}
