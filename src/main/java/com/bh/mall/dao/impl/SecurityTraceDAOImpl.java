package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISecurityTraceDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.SecurityTrace;

@Repository("securityTraceDAOImpl")
public class SecurityTraceDAOImpl extends AMybatisTemplate
        implements ISecurityTraceDAO {

    @Override
    public int insert(SecurityTrace data) {
        return super.insert(NAMESPACE.concat("insert_securityTrace"), data);
    }

    @Override
    public int delete(SecurityTrace data) {
        return super.delete(NAMESPACE.concat("delete_securityTrace"), data);
    }

    @Override
    public SecurityTrace select(SecurityTrace condition) {
        return super.select(NAMESPACE.concat("select_securityTrace"), condition,
            SecurityTrace.class);
    }

    @Override
    public long selectTotalCount(SecurityTrace condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_securityTrace_count"), condition);
    }

    @Override
    public List<SecurityTrace> selectList(SecurityTrace condition) {
        return super.selectList(NAMESPACE.concat("select_securityTrace"),
            condition, SecurityTrace.class);
    }

    @Override
    public List<SecurityTrace> selectList(SecurityTrace condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_securityTrace"), start,
            count, condition, SecurityTrace.class);
    }

    @Override
    public void update(SecurityTrace data) {
        super.update(NAMESPACE.concat("update_securityTrace"), data);
    }

    @Override
    public void updateStatus(SecurityTrace data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public List<SecurityTrace> selectCodeList(SecurityTrace condition) {
        return super.selectList(NAMESPACE.concat("select_code"), condition,
            SecurityTrace.class);
    }

    @Override
    public void updateNumber(SecurityTrace data) {
        super.update(NAMESPACE.concat("update_number"), data);
    }

}
