package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 
 * @author: nyc 
 * @since: 2018年3月23日 上午11:38:30 
 * @history:
 */
public class ProductLog extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -5583267902594820904L;

    // 编号
    private String code;

    // 产品编号
    private String productCode;

    // 类型
    private String type;

    // 变动数量
    private Integer tranCount;

    // 变动前数量
    private Integer preCount;

    // 变动后数量
    private Integer postCount;

    // 变动人
    private String updater;

    // 变动时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ********************db****************
    private Date startDatetime;

    private Date endDatetime;

    private String productName;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTranCount() {
        return tranCount;
    }

    public void setTranCount(Integer tranCount) {
        this.tranCount = tranCount;
    }

    public Integer getPreCount() {
        return preCount;
    }

    public void setPreCount(Integer preCount) {
        this.preCount = preCount;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
