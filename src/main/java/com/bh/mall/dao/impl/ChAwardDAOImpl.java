package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IChAwardDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ChAward;

@Repository("chAwardDAOImpl")
public class ChAwardDAOImpl extends AMybatisTemplate implements IChAwardDAO {

    @Override
    public int insert(ChAward data) {
        return super.insert(NAMESPACE.concat("insert_chAward"), data);
    }

    @Override
    public int delete(ChAward data) {
        return super.delete(NAMESPACE.concat("delete_chAward"), data);
    }

    @Override
    public ChAward select(ChAward condition) {
        return super.select(NAMESPACE.concat("select_chAward"), condition,
            ChAward.class);
    }

    @Override
    public long selectTotalCount(ChAward condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_chAward_count"),
            condition);
    }

    @Override
    public List<ChAward> selectList(ChAward condition) {
        return super.selectList(NAMESPACE.concat("select_chAward"), condition,
            ChAward.class);
    }

    @Override
    public List<ChAward> selectList(ChAward condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_chAward"), start,
            count, condition, ChAward.class);
    }

    @Override
    public void update(ChAward data) {
        super.update(NAMESPACE.concat("update_chAward"), data);
    }

}
