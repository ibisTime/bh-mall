package com.bh.mall.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增产品
 * @author: nyc 
 * @since: 2018年3月23日 下午1:52:06 
 * @history:
 */
public class XN627540Req {

    // （必填）产品名称
    @NotBlank(message = "产品名称不能为空")
    private String name;

    // （必填）建议价
    @NotBlank(message = "建议价不能为空")
    private String adPrice;

    // （必填）市场价
    @NotBlank(message = "市场价不能为空")
    private String price;

    // （必填）虚拟数量
    @NotBlank(message = "虚拟数量不能为空")
    private String virNumber;

    // （必填）实际数量
    @NotBlank(message = "实际数量不能为空")
    private String realNumber;

    // （必填）广告图
    @NotBlank(message = "广告图不能为空")
    private String advPic;

    // （必填）略缩图
    @NotBlank(message = "略缩图不能为空")
    private String pic;

    // （必填）广告语
    @NotBlank(message = "广告语不能为空")
    private String slogan;

    // （必填）是否计入出货
    @NotBlank(message = "是否计入出货不能为空")
    private String isTotal;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // (必填) 产品规格
    private List<XN627546Req> specList;

    // (必填) 产品规格定价
    private List<XN627547Req> specsPriceList;

    // (必填) 出货/推荐奖励
    private List<XN627548Req> awardList;

    // （选填）备注
    private String remark;

    public String getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(String adPrice) {
        this.adPrice = adPrice;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRealNumber() {
        return realNumber;
    }

    public void setRealNumber(String realNumber) {
        this.realNumber = realNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getVirNumber() {
        return virNumber;
    }

    public void setVirNumber(String virNumber) {
        this.virNumber = virNumber;
    }

    public String getIsTotal() {
        return isTotal;
    }

    public void setIsTotal(String isTotal) {
        this.isTotal = isTotal;
    }

    public List<XN627546Req> getSpecList() {
        return specList;
    }

    public void setSpecList(List<XN627546Req> specList) {
        this.specList = specList;
    }

    public List<XN627547Req> getSpecsPriceList() {
        return specsPriceList;
    }

    public void setSpecsPriceList(List<XN627547Req> specsPriceList) {
        this.specsPriceList = specsPriceList;
    }

    public List<XN627548Req> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<XN627548Req> awardList) {
        this.awardList = awardList;
    }

}
