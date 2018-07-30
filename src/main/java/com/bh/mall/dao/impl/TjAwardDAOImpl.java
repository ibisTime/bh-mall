package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ITjAwardDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.TjAward;

@Repository("tjAwardDAOImpl")
public class TjAwardDAOImpl extends AMybatisTemplate implements ITjAwardDAO {

    @Override
    public int insert(TjAward data) {
        return super.insert(NAMESPACE.concat("insert_tjAward"), data);
    }

    @Override
    public int delete(TjAward data) {
        return super.delete(NAMESPACE.concat("delete_tjAward"), data);
    }

    @Override
    public TjAward select(TjAward condition) {
        return super.select(NAMESPACE.concat("select_tjAward"), condition,
            TjAward.class);
    }

    @Override
    public long selectTotalCount(TjAward condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_tjAward_count"),
            condition);
    }

    @Override
    public List<TjAward> selectList(TjAward condition) {
        return super.selectList(NAMESPACE.concat("select_tjAward"), condition,
            TjAward.class);
    }

    @Override
    public List<TjAward> selectList(TjAward condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_tjAward"), start,
            count, condition, TjAward.class);
    }

    @Override
    public void update(TjAward data) {
        super.update(NAMESPACE.concat("update_tjAward"), data);
    }

}
