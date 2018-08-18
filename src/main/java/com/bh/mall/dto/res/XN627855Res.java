package com.bh.mall.dto.res;

import java.util.List;

import com.bh.mall.domain.Jour;

public class XN627855Res {

    private List<Jour> list;

    private Long amount;

    public XN627855Res(List<Jour> list, Long amount) {
        this.list = list;
        this.amount = amount;

    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<Jour> getList() {
        return list;
    }

    public void setList(List<Jour> list) {
        this.list = list;
    }

}
