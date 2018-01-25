/**
 * @Title EBusinessCode.java 
 * @Package com.std.user.enums 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月3日 下午5:15:55 
 * @version V1.0   
 */
package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月3日 下午5:15:55 
 * @history:
 */
public enum EBusinessCode {
    // 0表示错；1表示对
    Register("805041", "注册");
    public static Map<String, EBusinessCode> getBusinessCodeResultMap() {
        Map<String, EBusinessCode> map = new HashMap<String, EBusinessCode>();
        for (EBusinessCode status : EBusinessCode.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EBusinessCode(String code, String value) {
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
