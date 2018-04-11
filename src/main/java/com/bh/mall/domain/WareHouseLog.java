package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 售后单
* @author: chenshan 
* @since: 2018-04-04 15:24:57
* @history:
*/
public class WareHouseLog extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 关联编号
    private String refNo;

    // 所属云仓编号
    private String wareHouseCode;

    // 类型
    private String type;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String productSpecsCode;

    // 规格名称
    private String productSpecsName;

    // 变动数量
    private Integer tranNumber;

    // 变动钱数量
    private Integer beforeNumber;

    // 变动后数量
    private Integer afterNumber;

    // 单价
    private Long price;

    // 总价
    private Long amount;

    // 申请时间
    private Date applyDatetime;

    // 申请备注
    private String applyNote;

    // 可置换数量
    private Integer changeNumber;

    // 置换价格
    private Long changePrice;

    // 置换总价
    private Long changeAmount;

    // 状态
    private String status;

    // 用户编号
    private String applyUser;

    // 用户名称
    private String realName;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String approveNote;

    // 业务类型
    private String bizType;

    // 业务说明
    private String bizNote;

    // *************db**************
    private Date startDatetime;

    private Date endDatetime;

    private String keyword;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setWareHouseCode(String wareHouseCode) {
        this.wareHouseCode = wareHouseCode;
    }

    public String getWareHouseCode() {
        return wareHouseCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getProductSpecsName() {
        return productSpecsName;
    }

    public void setProductSpecsName(String productSpecsName) {
        this.productSpecsName = productSpecsName;
    }

    public Integer getTranNumber() {
        return tranNumber;
    }

    public void setTranNumber(Integer tranNumber) {
        this.tranNumber = tranNumber;
    }

    public Integer getBeforeNumber() {
        return beforeNumber;
    }

    public void setBeforeNumber(Integer beforeNumber) {
        this.beforeNumber = beforeNumber;
    }

    public Integer getAfterNumber() {
        return afterNumber;
    }

    public void setAfterNumber(Integer afterNumber) {
        this.afterNumber = afterNumber;
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

    public Integer getChangeNumber() {
        return changeNumber;
    }

    public Long getChangePrice() {
        return changePrice;
    }

    public Long getChangeAmount() {
        return changeAmount;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setChangeNumber(Integer changeNumber) {
        this.changeNumber = changeNumber;
    }

    public void setChangePrice(Long changePrice) {
        this.changePrice = changePrice;
    }

    public void setChangeAmount(Long changeAmount) {
        this.changeAmount = changeAmount;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApprover() {
        return approver;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizNote() {
        return bizNote;
    }

    public void setBizNote(String bizNote) {
        this.bizNote = bizNote;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
