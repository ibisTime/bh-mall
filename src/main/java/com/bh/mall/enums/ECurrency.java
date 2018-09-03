/**
 * @Title ECurrency.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午4:41:06 
 * @version V1.0   
 */
package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:51:38 
 * @history:
 */
public enum ECurrency {
    TX_CNY("TX_CNY", "可提现账户"), YC_CNY("YC_CNY", "云仓账户"), MK_CNY("MK_CNY",
            "门槛账户"), C_CNY("C_CNY", "C端账户"), TG_CNY("TG_CNY", "托管账户");

    public static Map<String, ECurrency> getCurrencyMap() {
        Map<String, ECurrency> map = new HashMap<String, ECurrency>();
        for (ECurrency currency : ECurrency.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static String getCurrency(String currency) {
        Map<String, ECurrency> map = ECurrency.getCurrencyMap();
        ECurrency value = map.get(currency);
        return value.getValue();
    }

    ECurrency(String code, String value) {
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
