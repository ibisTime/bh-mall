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

    // 规格编号
    private String specsCode;

    // 规格编号
    private String specsName;

    // 数量
    private Integer quantity;

    // **************db******************

    // 商产品数量
    private List<String> productQuantity;

    // 产品

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

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<String> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(List<String> productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

}
