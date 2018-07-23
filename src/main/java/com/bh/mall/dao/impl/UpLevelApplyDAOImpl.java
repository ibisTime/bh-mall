package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IUpLevelApplyDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.UpLevelApply;

@Repository("upLevelApplyDAOImpl")
public class UpLevelApplyDAOImpl extends AMybatisTemplate
        implements IUpLevelApplyDAO {

    @Override
    public int insert(UpLevelApply data) {
        return super.insert(NAMESPACE.concat("insert_upLevelApply"), data);
    }

    @Override
    public int delete(UpLevelApply data) {
        return super.delete(NAMESPACE.concat("delete_upLevelApply"), data);
    }

    @Override
    public UpLevelApply select(UpLevelApply condition) {
        return super.select(NAMESPACE.concat("select_upLevelApply"), condition,
            UpLevelApply.class);
    }

    @Override
    public long selectTotalCount(UpLevelApply condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_upLevelApply_count"), condition);
    }

    @Override
    public List<UpLevelApply> selectList(UpLevelApply condition) {
        return super.selectList(NAMESPACE.concat("select_upLevelApply"),
            condition, UpLevelApply.class);
    }

    @Override
    public List<UpLevelApply> selectList(UpLevelApply condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_upLevelApply"), start,
            count, condition, UpLevelApply.class);
    }

}
