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
    @NotBlank(message = "不能为空")
    private String code;

    // （必填） 排序
    @NotBlank(message = "不能为空")
    private String orderNo;

    // （必填） 更新人
    @NotBlank(message = "不能为空")
    private String updater;

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

}
