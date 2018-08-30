package com.bh.mall.dto.res;

import java.util.List;

import com.bh.mall.domain.AgentReport;

public class XN627852Res {

    private List<AgentReport> list;

    private Long number;

    private Long refreeAward;

    public XN627852Res(List<AgentReport> list, Long number, Long refreeAward) {
        this.list = list;
        this.number = number;
        this.refreeAward = refreeAward;
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

    public Long getRefreeAward() {
        return refreeAward;
    }

    public void setRefreeAward(Long refreeAward) {
        this.refreeAward = refreeAward;
    }

}
