package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
* 云仓
* @author: chenshan 
* @since: 2018-04-04 14:37:40
* @history:
*/
public class Ware extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型 P平台/B代理
    private String type;

    // 币种
    private String currency;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String productSpecsCode;

    // 规格名称
    private String productSpecsName;

    // 价格
    private Long price;

    // 数量
    private Integer quantity;

    // 总价值
    private Long amount;

    // 用户编号
    private String userId;

    // 用户名称
    private String realName;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 最后一条置换记录
    private String lastChangeCode;

    // 公司编号
    private String companyCode;

    // 公司编号
    private String systemCode;

    // ****************db***************
    private String keyword;

    // 用户
    private Agent agent;

    // 仓库货物总价值
    private Product product;

    // 仓库货物总价值
    private Long allAmount;

    // 最小规格总数量
    private Integer allQuantity;

    // 产品名称
    private List<Ware> specsList;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getProductSpecsName() {
        return productSpecsName;
    }

    public void setProductSpecsName(String productSpecsName) {
        this.productSpecsName = productSpecsName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setLastChangeCode(String lastChangeCode) {
        this.lastChangeCode = lastChangeCode;
    }

    public String getLastChangeCode() {
        return lastChangeCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(Long allAmount) {
        this.allAmount = allAmount;
    }

    public List<Ware> getWhsList() {
        return specsList;
    }

    public void setWhsList(List<Ware> specsList) {
        this.specsList = specsList;
    }

    public Integer getAllQuantity() {
        return allQuantity;
    }

    public List<Ware> getSpecsList() {
        return specsList;
    }

    public void setAllQuantity(Integer allQuantity) {
        this.allQuantity = allQuantity;
    }

    public void setSpecsList(List<Ware> specsList) {
        this.specsList = specsList;
    }

}
