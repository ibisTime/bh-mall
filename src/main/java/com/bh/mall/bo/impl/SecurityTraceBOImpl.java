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
import com.bh.mall.enums.EBoolean;
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
        List<SecurityTrace> list = securityTraceDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "该箱码对应的盒码不存在，请重新下载");
        }
        return list;
    }

    @Override
    public void refreshSecurityTrace(SecurityTrace data) {
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
    public void refreshStatus(SecurityTrace data) {
        data.setStatus(EBoolean.YES.getCode());
        data.setUseDatetime(new Date());
        securityTraceDAO.updateStatus(data);

    }

}
