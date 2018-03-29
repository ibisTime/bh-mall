package com.bh.mall.enums;

public enum EProduuctSend {
    Send_NO("0", "不送货"), Send_YES("1", "送货");

    EProduuctSend(String code, String value) {
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
