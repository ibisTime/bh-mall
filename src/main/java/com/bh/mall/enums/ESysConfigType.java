package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数
 * @author: xieyj 
 * @since: 2017年9月4日 下午8:00:03 
 * @history:
 */
public enum ESysConfigType {
    QINIU("qiniu", "七牛参数"), WX_H5("wx_h5", "微信h5参数"), GIFT("gift",
            "推广送积分参数"), SYS_TXT("sys_txt", "文本参数"), URL("fwsy_url", "防伪溯源");

    public static Map<String, ESysConfigType> getMap() {
        Map<String, ESysConfigType> map = new HashMap<String, ESysConfigType>();
        for (ESysConfigType domain : ESysConfigType.values()) {
            map.put(domain.getCode(), domain);
        }
        return map;
    }

    ESysConfigType(String code, String value) {
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
