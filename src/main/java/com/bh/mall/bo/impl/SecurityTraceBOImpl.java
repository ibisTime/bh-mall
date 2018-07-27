package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ISecurityTraceBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.ISecurityTraceDAO;
import com.bh.mall.domain.SecurityTrace;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.exception.BizException;

@Component
public class SecurityTraceBOImpl extends PaginableBOImpl<SecurityTrace>
        implements ISecurityTraceBO {

    @Autowired
    private ISecurityTraceDAO securityTraceDAO;

    public void saveSecurityTrace(SecurityTrace data) {
        securityTraceDAO.insert(data);
    }

    @Override
    public List<SecurityTrace> getSecurityTraceByBarCode(String code) {
        SecurityTrace condition = new SecurityTrace();
        condition.setRefCode(code);
        condition.setStatus(ECodeStatus.USE_NO.getCode());
        List<SecurityTrace> list = securityTraceDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "该箱码已被使用啦！");
        }
        return list;
    }

    @Override
    public void refreshSecurityTrace(SecurityTrace data, String refCode) {
        data.setRefCode(refCode);
        data.setStatus(ECodeStatus.USE_NO.getCode());
        securityTraceDAO.update(data);
    }

    @Override
    public List<SecurityTrace> querySecurityTraceList(SecurityTrace condition) {
        return securityTraceDAO.selectList(condition);
    }

    @Override
    public SecurityTrace getSecurityTrace(String code) {
        SecurityTrace data = null;
        if (StringUtils.isNotBlank(code)) {
            SecurityTrace condition = new SecurityTrace();
            data = securityTraceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "盒码不存在");
            }
        }
        return data;
    }

    @Override
    public List<SecurityTrace> queryCodeList() {
        SecurityTrace condition = new SecurityTrace();
        return securityTraceDAO.selectCodeList(condition);
    }

    @Override
    public void refreshStatus(SecurityTrace data, String orderCode) {
        data.setStatus(ECodeStatus.USE_YES.getCode());
        data.setOrderCode(orderCode);
        data.setUseDatetime(new Date());
        securityTraceDAO.updateStatus(data);

    }

    @Override
    public SecurityTrace getNoUseSecurityTrace() {
        SecurityTrace condition = new SecurityTrace();
        condition.setStatus(ECodeStatus.USE_NO.getCode());
        List<SecurityTrace> list = securityTraceDAO.selectCodeList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn00000", "盒码没有啦");
        }
        return list.get(0);
    }

    @Override
    public int refreshSecurity(SecurityTrace data) {
        data.setNumber(data.getNumber() + 1);
        securityTraceDAO.updateNumber(data);
        return data.getNumber();
    }

    @Override
    public SecurityTrace getTrace(String traceCode) {
        SecurityTrace data = null;
        if (StringUtils.isNotBlank(traceCode)) {
            SecurityTrace condition = new SecurityTrace();
            condition.setTraceCode(traceCode);
            data = securityTraceDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "溯源码错误");
            }
        }
        return data;
    }

    @Override
    public SecurityTrace getSecurity(String securityCode) {
        SecurityTrace data = null;
        if (StringUtils.isNotBlank(securityCode)) {
            SecurityTrace condition = new SecurityTrace();
            condition.setSecurityCode(securityCode);
            data = securityTraceDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "防伪码不存在");
            }
        }
        return data;
    }

}
