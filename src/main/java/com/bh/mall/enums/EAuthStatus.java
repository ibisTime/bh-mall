/**
 * @Title EBlacklistStatus.java 
 * @Package com.std.user.enums 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月22日 下午12:16:45 
 * @version V1.0   
 */
package com.bh.mall.enums;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月22日 下午12:16:45 
 * @history:
 */
public enum EAuthStatus {
    TO_APPROVE("0", "待审核"), APPROVE_YES("1", "审核中"), APPROVE_NO("2", "审核失败");

    EAuthStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
