package com.xn.sdhh.domain;

import java.util.Date;

import com.xn.sdhh.dao.base.ABaseDO;

/**
 * 业务
 * @author: 55484 
 * @since: 2018年10月24日 下午4:33:11 
 * @history:
 */
public class Business extends ABaseDO {

    private static final long serialVersionUID = -6344063950447428650L;

    // 编号
    private String code;

    // 区域负责人名称
    private String qyfzrmc;

    // 汽车种类
    private String qczl;

    // 客户名称
    private String khmc;

    // 贷款金额
    private Long dkje;

    // 综合利率
    private Double zhll;

    // 打款日期
    private Date dkrq;

    // 银行放款日期
    private Date yhfkrq;

    // 公司回款日期
    private Date gshkrq;

    // 发保合日差
    private Date fbhrc;

    // 温州垫资日期
    private Date wzdzrq;

    // 打件日期
    private Date djrq;

    // 打件日差
    private Date djrc;

    // 调额日期
    private Date terq;

    // 放款日差
    private Date fkrc;

    // 抵押日期
    private Date dyrq;

    // 抵押日差
    private Date dyrc;

    // 返点金额
    private Long fdje;

    // 评估费
    private Long pgf;

    // 垫资利息
    private Long dzlx;

    // 应收返点金额
    private Long ysfdje;

    // 渠道费
    private Long qdf;

    // 绩效
    private Long jx;

    // 其他利润
    private Long qtlr;

    // 保证金贷款额
    private Long bzjdke;

    // 毛利润
    private Long mlr;

    // 状态
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public Date getYhfkrq() {
        return yhfkrq;
    }

    public void setYhfkrq(Date yhfkrq) {
        this.yhfkrq = yhfkrq;
    }

    public Date getGshkrq() {
        return gshkrq;
    }

    public void setGshkrq(Date gshkrq) {
        this.gshkrq = gshkrq;
    }

    public Date getFbhrc() {
        return fbhrc;
    }

    public void setFbhrc(Date fbhrc) {
        this.fbhrc = fbhrc;
    }

    public Date getWzdzrq() {
        return wzdzrq;
    }

    public void setWzdzrq(Date wzdzrq) {
        this.wzdzrq = wzdzrq;
    }

    public Date getDjrq() {
        return djrq;
    }

    public void setDjrq(Date djrq) {
        this.djrq = djrq;
    }

    public Date getDjrc() {
        return djrc;
    }

    public void setDjrc(Date djrc) {
        this.djrc = djrc;
    }

    public Date getTerq() {
        return terq;
    }

    public void setTerq(Date terq) {
        this.terq = terq;
    }

    public Date getFkrc() {
        return fkrc;
    }

    public void setFkrc(Date fkrc) {
        this.fkrc = fkrc;
    }

    public Date getDyrq() {
        return dyrq;
    }

    public void setDyrq(Date dyrq) {
        this.dyrq = dyrq;
    }

    public Date getDyrc() {
        return dyrc;
    }

    public void setDyrc(Date dyrc) {
        this.dyrc = dyrc;
    }

    public Long getFdje() {
        return fdje;
    }

    public void setFdje(Long fdje) {
        this.fdje = fdje;
    }

    public Long getPgf() {
        return pgf;
    }

    public void setPgf(Long pgf) {
        this.pgf = pgf;
    }

    public Long getDzlx() {
        return dzlx;
    }

    public void setDzlx(Long dzlx) {
        this.dzlx = dzlx;
    }

    public Long getYsfdje() {
        return ysfdje;
    }

    public void setYsfdje(Long ysfdje) {
        this.ysfdje = ysfdje;
    }

    public Long getQdf() {
        return qdf;
    }

    public void setQdf(Long qdf) {
        this.qdf = qdf;
    }

    public Long getJx() {
        return jx;
    }

    public void setJx(Long jx) {
        this.jx = jx;
    }

    public Long getQtlr() {
        return qtlr;
    }

    public void setQtlr(Long qtlr) {
        this.qtlr = qtlr;
    }

    public Long getBzjdke() {
        return bzjdke;
    }

    public void setBzjdke(Long bzjdke) {
        this.bzjdke = bzjdke;
    }

    public Long getMlr() {
        return mlr;
    }

    public void setMlr(Long mlr) {
        this.mlr = mlr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
