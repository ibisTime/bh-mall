/**
 * @Title XN805921Req.java 
 * @Package com.std.user.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月22日 下午2:36:47 
 * @version V1.0   
 */
package com.std.user.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月22日 下午2:36:47 
 * @history:
 */
public class XN805921Req {

    // 用户编号（必填）
    private String userId;

    // 拉黑类型（必填)
    private String type;

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

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
