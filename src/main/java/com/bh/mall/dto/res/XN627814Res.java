package com.bh.mall.dto.res;

import java.util.List;

import com.bh.mall.domain.WareHouse;

public class XN627814Res {

    private List<WareHouse> list;

    private Long amount;

    public XN627814Res(List<WareHouse> list, Long amount) {
        this.list = list;
        this.amount = amount;
    }

    public List<WareHouse> getList() {
        return list;
    }

    public void setList(List<WareHouse> list) {
        this.list = list;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
