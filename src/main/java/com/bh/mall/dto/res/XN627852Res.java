package com.bh.mall.dto.res;

import java.util.List;

import com.bh.mall.domain.AgentReport;

public class XN627852Res {

    private List<AgentReport> list;

    private Long number;

    public XN627852Res(List<AgentReport> list, Long number) {
        this.list = list;
        this.number = number;
    }

    public List<AgentReport> getList() {
        return list;
    }

    public Long getNumber() {
        return number;
    }

    public void setList(List<AgentReport> list) {
        this.list = list;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

}
