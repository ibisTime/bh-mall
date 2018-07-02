package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SecurityTrace;

public interface ISecurityTraceDAO extends IBaseDAO<SecurityTrace> {
    String NAMESPACE = ISecurityTraceDAO.class.getName().concat(".");

    void update(SecurityTrace data);

    void updateStatus(SecurityTrace data);

    List<SecurityTrace> selectCodeList(SecurityTrace condition);

}
