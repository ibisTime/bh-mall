package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAwardType {

    DirectAward("0", "直接出货奖励"), SendAward("1", "出货奖励");
    public static Map<String, EAwardType> getAwardTypeMap() {
        Map<String, EAwardType> map = new HashMap<String, EAwardType>();
        for (EAwardType awardType : EAwardType.values()) {
            map.put(awardType.getCode(), awardType);
        }
        return map;
    }

    EAwardType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
