package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 内购产品上架
 * @author: nyc 
 * @since: 2018年3月26日 下午2:45:59 
 * @history:
 */
public class XN627703Req {
    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）排序
    @NotBlank(message = "排序不能为空")
    private String orderNo;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
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
