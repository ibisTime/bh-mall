package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 产品上架
 * @author: nyc 
 * @since: 2018年3月24日 下午6:01:00 
 * @history:
 */
public class XN627543Req {
    // （必填） 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填） 排序
    @NotBlank(message = "排序不能为空")
    private String orderNo;

    // （必填） 更新人
    @NotBlank(message = " 更新人不能为空")
    private String updater;

    // 是否包邮
    @NotBlank(message = "是否包邮不能为空")
    private String isFree;

    // 换货价
    @NotBlank(message = "换货价不能为空")
    private String changePrice;

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

}
