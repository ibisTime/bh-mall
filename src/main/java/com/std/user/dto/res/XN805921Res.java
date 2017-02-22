/**
 * @Title XN805921Res.java 
 * @Package com.std.user.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月22日 下午2:54:02 
 * @version V1.0   
 */
package com.std.user.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月22日 下午2:54:02 
 * @history:
 */
public class XN805921Res {

    private boolean isAdded;

    public XN805921Res(boolean isAdded) {
        super();
        this.isAdded = isAdded;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }
}
