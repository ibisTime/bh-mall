package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发货
 * @author: nyc 
 * @since: 2018年3月27日 下午5:36:26 
 * @history:
 */
public class XN627723Req {
    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）发货人
    @NotBlank(message = "发货人不能为空")
    private String deliverer;

    // （必填）物流编号
    @NotBlank(message = "物流编号不能为空")
    private String logisticsCode;

    // （必填）物流公司
    @NotBlank(message = "物流公司不能为空")
    private String logisticsCompany;

    // （必填）物流单
    private String pdf;

    // （选填）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
