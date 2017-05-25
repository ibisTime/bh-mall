/**
 * @Title XN805184Req.java 
 * @Package com.std.user.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年5月25日 上午10:36:21 
 * @version V1.0   
 */
package com.std.user.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年5月25日 上午10:36:21 
 * @history:
 */
public class XN805184Req {

    // 用户编号
    private String userId;

    // 分成比例
    private String divRate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDivRate() {
        return divRate;
    }

    public void setDivRate(String divRate) {
        this.divRate = divRate;
    }
}
