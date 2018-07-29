package com.bh.mall.dto.res;

import java.util.List;

import com.bh.mall.domain.Ware;

public class XN627814Res {

    private List<Ware> list;

    private Long amount;

    public XN627814Res(List<Ware> list, Long amount) {
        this.list = list;
        this.amount = amount;
    }

    public List<Ware> getList() {
        return list;
    }

    public void setList(List<Ware> list) {
        this.list = list;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
