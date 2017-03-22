/**
 * @Title XN805920Req.java 
 * @Package com.std.user.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月22日 下午2:36:47 
 * @version V1.0   
 */
package com.std.user.dto.req;

/** 
 * 将用户拉入黑名单
 * @author: haiqingzheng 
 * @since: 2017年2月22日 下午2:36:47 
 * @history:
 */
public class XN805920Req {

    // 用户编号（必填）
    private String userId;

    // 拉黑类型（必填)
    private String type;

    // 备注（选填）
    private String remark;

    // 系统编号（必填）
    private String systemCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
