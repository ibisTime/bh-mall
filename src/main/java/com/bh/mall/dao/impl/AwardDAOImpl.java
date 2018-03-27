package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAwardDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Award;

@Repository("awardDAOImpl")
public class AwardDAOImpl extends AMybatisTemplate implements IAwardDAO {

    @Override
    public int insert(Award data) {
        return super.insert(NAMESPACE.concat("insert_award"), data);
    }

    @Override
    public int delete(Award data) {
        return super.delete(NAMESPACE.concat("delete_award"), data);
    }

    @Override
    public Award select(Award condition) {
        return super.select(NAMESPACE.concat("select_award"), condition,
            Award.class);
    }

    @Override
    public long selectTotalCount(Award condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_award_count"),
            condition);
    }

    @Override
    public List<Award> selectList(Award condition) {
        return super.selectList(NAMESPACE.concat("select_award"), condition,
            Award.class);
    }

    @Override
    public List<Award> selectList(Award condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_award"), start, count,
            condition, Award.class);
    }

    @Override
    public void update(Award data) {
        super.update(NAMESPACE.concat("update_award"), data);
    }

}
