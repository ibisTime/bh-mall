package com.xn.sdhh.dto.req;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午4:09:09 
 * @history:
 */
public class XN627075Req extends APageReq {
    private static final long serialVersionUID = 5780013307270124748L;

    // 类型（第一层/第二层）（选填）
    private String type;

    // 父key（选填）
    private String parentKey;

    // key（选填）
    private String dkey;

    /*
     * // 公司编号（必填）
     * @NotBlank private String companyCode; // 系统编号（必填）
     * @NotBlank private String systemCode;
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getDkey() {
        return dkey;
    }

    public void setDkey(String dkey) {
        this.dkey = dkey;
    }

    /*
     * public String getSystemCode() { return systemCode; } public void
     * setSystemCode(String systemCode) { this.systemCode = systemCode; } public
     * String getCompanyCode() { return companyCode; } public void
     * setCompanyCode(String companyCode) { this.companyCode = companyCode; }
     */
}
