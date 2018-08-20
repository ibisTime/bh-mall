package com.bh.mall.dto.req;

import java.util.List;

public class XN627554Req extends APageReq {

    private static final long serialVersionUID = 3984893152143265011L;

    // （选填）产品编号
    private String code;

    // （选填）名称
    private String name;

    // （选填）状态
    private String status;

    // 账户
    private List<String> currencyList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

}
