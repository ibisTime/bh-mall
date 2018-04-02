package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAgencyLogDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AgencyLog;

@Repository("agencyLogDAOImpl")
public class AgencyLogDAOImpl extends AMybatisTemplate
        implements IAgencyLogDAO {

    @Override
    public int insert(AgencyLog data) {
        return super.insert(NAMESPACE.concat("insert_agencyLog"), data);
    }

    @Override
    public int delete(AgencyLog data) {
        return super.delete(NAMESPACE.concat("delete_agencyLog"), data);
    }

    @Override
    public AgencyLog select(AgencyLog condition) {
        return super.select(NAMESPACE.concat("select_agencyLog"), condition,
            AgencyLog.class);
    }

    @Override
    public long selectTotalCount(AgencyLog condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_agencyLog_count"), condition);
    }

    @Override
    public List<AgencyLog> selectList(AgencyLog condition) {
        return super.selectList(NAMESPACE.concat("select_agencyLog"), condition,
            AgencyLog.class);
    }

    @Override
    public List<AgencyLog> selectList(AgencyLog condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_agencyLog"), start,
            count, condition, AgencyLog.class);
    }

}
