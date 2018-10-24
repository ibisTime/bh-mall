package com.xn.sdhh.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:39:41 
 * @history:
 */
public class XN627070Req {

    // 类型（第一层/第二层）（必填）
    @NotBlank
    private String type;

    // 父key（第二层必填）
    @NotBlank
    private String parentKey;

    // key（必填）
    @NotBlank
    private String dkey;

    // value（必填）
    @NotBlank
    private String dvalue;

    // 修改人（必填）
    @NotBlank
    private String updater;

    // 备注（选填）
    private String remark;

   /* // 公司编号（必填）
    @NotBlank
    private String companyCode;

    // 系统编号（必填）
    @NotBlank
    private String systemCode;
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

    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
