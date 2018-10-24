package com.xn.sdhh.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 录入业务
 * @author: 55484 
 * @since: 2018年10月24日 下午5:47:10 
 * @history:
 */
public class XN627130Req {

    // 区域负责人名称
    @NotBlank(message = "区域负责人名称不能为空")
    private String qyfzrmc;

    // 汽车种类
    @NotBlank(message = "汽车种类不能为空")
    private String qczl;

    // 客户名称
    @NotBlank(message = "客户名称不能为空")
    private String khmc;

    // 贷款金额
    @NotBlank(message = "贷款金额不能为空")
    private Long dkje;

    // 综合利率
    @NotBlank(message = "综合利率不能为空")
    private Double zhll;

    // 打款日期
    @NotBlank(message = "打款日期 不能为空")
    private Date dkrq;

    public String getQyfzrmc() {
        return qyfzrmc;
    }

    public void setQyfzrmc(String qyfzrmc) {
        this.qyfzrmc = qyfzrmc;
    }

    public String getQczl() {
        return qczl;
    }

    public void setQczl(String qczl) {
        this.qczl = qczl;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public Long getDkje() {
        return dkje;
    }

    public void setDkje(Long dkje) {
        this.dkje = dkje;
    }

    public Double getZhll() {
        return zhll;
    }

    public void setZhll(Double zhll) {
        this.zhll = zhll;
    }

    public Date getDkrq() {
        return dkrq;
    }

    public void setDkrq(Date dkrq) {
        this.dkrq = dkrq;
    }

}
