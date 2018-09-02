package com.bh.mall.dto.req;

/**
 * 分页查询云仓订单
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:21:23 
 * @history:
 */
public class XN627915Req extends APageReq {

    private static final long serialVersionUID = -645972324512910733L;

    // （选填）产品名称
    private String applyUser;

    // （选填）产品名称
    private String productName;

    // （选填）状态
    private String status;

    // （选填）下单人名称
    private String realName;

    // （选填）等级
    private String level;

    // （选填）团队名称
    private String teamName;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getProductName() {
        return productName;
    }

    public String getStatus() {
        return status;
    }

    public String getRealName() {
        return realName;
    }

    public String getLevel() {
        return level;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
