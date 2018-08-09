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

    // 是否包邮
    private String isFree;

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

    // 备注
    private String remark;

    // ***********************db******************
    // 当前等级
    private Integer level;

    // 当前用户id
    private String userId;

    // 产品规格
    private List<Specs> specsList;

    // 推荐奖励
    private List<TjAward> directAwardList;

    // 各级代理云仓该产品最小规格数量
    private Integer whNumber;

    // 产品规格
    private Specs specs;

    // 规格价格
    private AgentPrice specsPrice;

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

    public List<Specs> getSpecsList() {
        return specsList;
    }

    public void setSpecsList(List<Specs> specsList) {
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

    public List<TjAward> getDirectAwardList() {
        return directAwardList;
    }

    public void setDirectAwardList(List<TjAward> directAwardList) {
        this.directAwardList = directAwardList;
    }

    public Integer getWhNumber() {
        return whNumber;
    }

    public void setWhNumber(Integer whNumber) {
        this.whNumber = whNumber;
    }

    public Specs getSpecs() {
        return specs;
    }

    public AgentPrice getSpecsPrice() {
        return specsPrice;
    }

    public void setSpecs(Specs specs) {
        this.specs = specs;
    }

    public void setSpecsPrice(AgentPrice specsPrice) {
        this.specsPrice = specsPrice;
    }

}
