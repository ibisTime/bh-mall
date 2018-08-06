package com.bh.mall.dto.req;

/**
 * 我的下级
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627125Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 9092885295612580388L;

    // （选填）用户名
    private String realName;

    // （选填）角色
    private String roleCode;
    
    // （选填）备注
    private String keyword;

    public String getRealName() {
        return realName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
   

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
