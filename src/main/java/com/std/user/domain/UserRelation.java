/**
 * @Title UserRelation.java 
 * @Package com.std.user.domain 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午9:37:08 
 * @version V1.0   
 */
package com.std.user.domain;

import java.util.Date;

import com.std.user.dao.base.ABaseDO;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 上午9:37:08 
 * @history:
 */
public class UserRelation extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 关系人编号(下家，关注人)
    private String toUser;

    // 状态(1 正常 0 假删除定时器删除 预留)
    private String status;

    // 更新时间
    private Date updateDatetime;

    // ***************** db properties *****************
    // 手机号
    private String mobileForLikeQuery;

    // 手机号
    private String mobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getMobileForLikeQuery() {
        return mobileForLikeQuery;
    }

    public void setMobileForLikeQuery(String mobileForLikeQuery) {
        this.mobileForLikeQuery = mobileForLikeQuery;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
