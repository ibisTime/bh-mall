package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 产品表
 * @author: nyc 
 * @since: 2018年3月23日 上午10:47:11 
 * @history:
 */
public class Product extends ABaseDO {

    private static final long serialVersionUID = -4344211070031929412L;

    // 编号
    private String code;

    // 产品名称
    private String name;

    // 建议微信价
    private Long adPrice;

    // 市场价
    private Long price;

    // 虚拟数量
    private Integer virNumber;

    // 实际数量
    private Integer realNumber;

    // 排序
    private Integer orderNo;

    // 缩略图
    private String pic;

    // 广告图
    private String advPic;

    // 广告语
    private String slogan;

    // 是否计入出货
    private String isTotal;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 是否包邮
    private String isFree;

    // 备注
    private String remark;

    // 产品规格
    private List<ProductSpecs> specsList;

    // 推荐奖励
    private List<Award> directAwardList;

    // 介绍奖励
    private List<Award> sendAwardList;

    // ***********************db******************
    private Integer level;

    private String userId;

    // 各级代理云仓该产品最小规格数量
    private Integer whNumber;

    // 产品规格
    private ProductSpecs specs;

    // 规格价格
    private ProductSpecsPrice specsPrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(Long adPrice) {
        this.adPrice = adPrice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getVirNumber() {
        return virNumber;
    }

    public void setVirNumber(Integer virNumber) {
        this.virNumber = virNumber;
    }

    public Integer getRealNumber() {
        return realNumber;
    }

    public void setRealNumber(Integer realNumber) {
        this.realNumber = realNumber;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getIsTotal() {
        return isTotal;
    }

    public void setIsTotal(String isTotal) {
        this.isTotal = isTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
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

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ProductSpecs> getSpecsList() {
        return specsList;
    }

    public void setSpecsList(List<ProductSpecs> specsList) {
        this.specsList = specsList;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Award> getDirectAwardList() {
        return directAwardList;
    }

    public void setDirectAwardList(List<Award> directAwardList) {
        this.directAwardList = directAwardList;
    }

    public List<Award> getSendAwardList() {
        return sendAwardList;
    }

    public void setSendAwardList(List<Award> sendAwardList) {
        this.sendAwardList = sendAwardList;
    }

    public Integer getWhNumber() {
        return whNumber;
    }

    public void setWhNumber(Integer whNumber) {
        this.whNumber = whNumber;
    }

    public ProductSpecs getSpecs() {
        return specs;
    }

    public ProductSpecsPrice getSpecsPrice() {
        return specsPrice;
    }

    public void setSpecs(ProductSpecs specs) {
        this.specs = specs;
    }

    public void setSpecsPrice(ProductSpecsPrice specsPrice) {
        this.specsPrice = specsPrice;
    }

}
