package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午2:17:57 
 * @history:
 */
public class XN627701Req {

    // （必填）产品编号
    @NotBlank(message = "产品编号不能为空")
    private String code;

    // （必填）产品名称
    @NotBlank(message = "产品名称不能为空")
    private String name;

    // （必填）广告语
    @NotBlank(message = "广告语不能为空")
    private String slogan;

    // （必填）广告图
    @NotBlank(message = "广告图不能为空")
    private String advPic;

    // （必填）缩略图
    @NotBlank(message = "缩略图不能为空")
    private String pic;

    // （必填）价格
    @NotBlank(message = "价格不能为空")
    private String price;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

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

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
