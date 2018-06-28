package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAwardIntervalDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AwardInterval;

@Repository("awardIntervalDAOImpl")
public class AwardIntervalDAOImpl extends AMybatisTemplate
        implements IAwardIntervalDAO {

    @Override
    public int insert(AwardInterval data) {
        return super.insert(NAMESPACE.concat("insert_awardInterval"), data);
    }

    @Override
    public int delete(AwardInterval data) {
        return super.delete(NAMESPACE.concat("delete_awardInterval"), data);
    }

    @Override
    public AwardInterval select(AwardInterval condition) {
        return super.select(NAMESPACE.concat("select_awardInterval"), condition,
            AwardInterval.class);
    }

    @Override
    public long selectTotalCount(AwardInterval condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_awardInterval_count"), condition);
    }

    @Override
    public List<AwardInterval> selectList(AwardInterval condition) {
        return super.selectList(NAMESPACE.concat("select_awardInterval"),
            condition, AwardInterval.class);
    }

    @Override
    public List<AwardInterval> selectList(AwardInterval condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_awardInterval"), start,
            count, condition, AwardInterval.class);
    }

    @Override
    public void update(AwardInterval data) {
        super.update(NAMESPACE.concat("update_awardInterval"), data);
    }

}
