package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发货
 * @author: nyc 
 * @since: 2018年3月28日 下午1:59:11 
 * @history:
 */
public class XN627930Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）发货人
    @NotBlank(message = "发货人不能为空")
    private String deliver;

    // （必填）物流编号
    @NotBlank(message = "物流单号不能为空")
    private String logisticsCode;

    // （必填）物流公司
    @NotBlank(message = "物流公司不能为空")
    private String logisticsCompany;

    // （选填 ） 箱码
    private String proCode;

    // （选填）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProCode() {
        return proCode;
    }

    public void setproCode(String proCode) {
        this.proCode = proCode;
    }

}
