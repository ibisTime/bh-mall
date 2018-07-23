package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SecurityTrace;

@Component
public interface ISecurityTraceAO {
    static final String DEFAULT_ORDER_COLUMN = "security_code";

    public Paginable<SecurityTrace> querySecurityTracePage(int start, int limit,
            SecurityTrace condition);

    public List<SecurityTrace> querySecurityTraceList(SecurityTrace condition);

    public SecurityTrace getSecurityTrace(String code);

    public void addSecurityTrace(int number);

    public int getSecurity(String securityCode);

    public SecurityTrace getTrace(String traceCode);

}
