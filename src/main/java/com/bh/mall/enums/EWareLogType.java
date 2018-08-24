/**
 * @Title EWeChatType.java 
 * @Package com.std.account.enums 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月26日 下午3:47:12 
 * @version V1.0   
 */
package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午3:47:12 
 * @history:
 */
public enum EWareLogType {

    IN("0", "购买云仓"), OUT("1", "云仓出货"), CHANGE("2", "云仓置换");

    public static Map<String, EWareLogType> getFromTypeMap() {
        Map<String, EWareLogType> map = new HashMap<String, EWareLogType>();
        for (EWareLogType direction : EWareLogType.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    EWareLogType(String code, String value) {
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
