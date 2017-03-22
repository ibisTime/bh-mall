/**
 * @Title SYSConfig.java 
 * @Package com.xnjr.moom.domain 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月16日 下午9:45:46 
 * @version V1.0   
 */
package com.std.user.domain;

import java.util.Date;

import com.std.user.dao.base.ABaseDO;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月16日 下午9:45:46 
 * @history:
 */
public class SYSConfig extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -6136818068168453506L;

    // 编号（自增长）
    private Long id;

    // key值
    private String ckey;

    // value值
    private String cvalue;

    // 参数说明
    private String note;

    // 最近更新人
    private String updater;

    // 最近更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 系统编号（必填）
    private String systemCode;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    // ************* 模糊查询 *****************
    private String ckeyForQuery;

    private String dhhlFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCkeyForQuery() {
        return ckeyForQuery;
    }

    public void setCkeyForQuery(String ckeyForQuery) {
        this.ckeyForQuery = ckeyForQuery;
    }

    public String getDhhlFlag() {
        return dhhlFlag;
    }

    public void setDhhlFlag(String dhhlFlag) {
        this.dhhlFlag = dhhlFlag;
    }
}
