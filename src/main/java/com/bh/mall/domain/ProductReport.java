package com.bh.mall.domain;

import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
* 团队销售数量统计
* @author: nyc 
* @since: 2018-08-13 19:23:53
* @history:
*/
public class ProductReport extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 团队名称
    private String teamName;

    // 团队长
    private String teamLeader;

    // 产品编号
    private String productCode;

    // 产品编号
    private String productName;

    // 数量
    private Integer quantity;

    // **************db******************

    private List<ProductReport> list;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<ProductReport> getList() {
        return list;
    }

    public void setList(List<ProductReport> list) {
        this.list = list;
    }

}
