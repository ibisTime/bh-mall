package com.bh.mall.dto.res;

import java.util.List;

import com.bh.mall.domain.OrderReport;

public class XN627854Res {

    private List<OrderReport> page;

    private Long allAmount;

    public XN627854Res(List<OrderReport> page, Long allAmount) {
        this.page = page;
        this.allAmount = allAmount;
    }

    public List<OrderReport> getPage() {
        return page;
    }

    public Long getAllAmount() {
        return allAmount;
    }

    public void setPage(List<OrderReport> page) {
        this.page = page;
    }

    public void setAllAmount(Long allAmount) {
        this.allAmount = allAmount;
    }

}
