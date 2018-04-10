package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 授权证书
* @author: chenshan 
* @since: 2018-04-09 14:47:55
* @history:
*/
public class ChangeProduct extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String productSpecsCode;

    // 规格名称
    private String productSpecsName;

    // 置换数量
    private Integer quantity;

    // 产品单价
    private Long price;

    // 置换总价
    private Long amount;

    // 置换产品编号
    private String changeProductCode;

    // 置换产品名称
    private String changeProductName;

    // 置换产品规格编号
    private String changeSpecsCode;

    // 置换规格名称
    private String changeSpecsName;

    // 置换价格
    private Long changePrice;

    // 申请人
    private String applyUser;

    // 申请人等级
    private Integer level;

    // 可换数量
    private Integer canChangeQuantity;

    // 申请时间
    private Date applyDatetime;

    // 申请备注
    private String applyNote;

    // 状态
    private String status;

    // 审核人
    private String approver;

    // 审核时间
    private String approveDatetime;

    // 审核备注
    private String approveNote;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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

    public String getChangeSpecsName() {
        return changeSpecsName;
    }

    public void setChangeSpecsName(String changeSpecsName) {
        this.changeSpecsName = changeSpecsName;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(Long changePrice) {
        this.changePrice = changePrice;
    }

    public Integer getCanChangeQuantity() {
        return canChangeQuantity;
    }

    public void setCanChangeQuantity(Integer canChangeQuantity) {
        this.canChangeQuantity = canChangeQuantity;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public void setChangeProductCode(String changeProductCode) {
        this.changeProductCode = changeProductCode;
    }

    public String getChangeProductCode() {
        return changeProductCode;
    }

    public void setChangeProductName(String changeProductName) {
        this.changeProductName = changeProductName;
    }

    public String getChangeProductName() {
        return changeProductName;
    }

    public void setChangeSpecsCode(String changeSpecsCode) {
        this.changeSpecsCode = changeSpecsCode;
    }

    public String getChangeSpecsCode() {
        return changeSpecsCode;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public Integer getLevel() {
        return level;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getApplyNote() {
        return applyNote;
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

    public void setApproveDatetime(String approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getApproveNote() {
        return approveNote;
    }

}
