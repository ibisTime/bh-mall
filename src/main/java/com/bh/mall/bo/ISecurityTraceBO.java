package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.SecurityTrace;

public interface ISecurityTraceBO extends IPaginableBO<SecurityTrace> {

    public void saveSecurityTrace(SecurityTrace data);

    public List<SecurityTrace> querySecurityTraceList(SecurityTrace condition);

    public SecurityTrace getSecurityTrace(String code);

    public List<SecurityTrace> getSecurityTraceByBarCode(String code);

    public List<SecurityTrace> queryCodeList();

    public SecurityTrace getNoUseSecurityTrace();

    public void refreshSecurityTrace(SecurityTrace trace, String barCode);

    void refreshStatus(SecurityTrace data, String orderCode);

    public SecurityTrace getSecurity(String securityCode);

    public SecurityTrace getTrace(String traceCode);

    public int refreshSecurity(SecurityTrace data);

}
