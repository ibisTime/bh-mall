package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISpecsLogDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.SpecsLog;

@Repository("specsLogDAOImpl")
public class SpecsLogDAOImpl extends AMybatisTemplate implements ISpecsLogDAO {

    @Override
    public int insert(SpecsLog data) {
        return super.insert(NAMESPACE.concat("insert_specsLog"), data);
    }

    @Override
    public int delete(SpecsLog data) {
        return super.delete(NAMESPACE.concat("delete_byProductCode"), data);
    }

    @Override
    public SpecsLog select(SpecsLog condition) {
        return super.select(NAMESPACE.concat("select_specsLog"), condition,
            SpecsLog.class);
    }

    @Override
    public long selectTotalCount(SpecsLog condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_specsLog_count"),
            condition);
    }

    @Override
    public List<SpecsLog> selectList(SpecsLog condition) {
        return super.selectList(NAMESPACE.concat("select_specsLog"), condition,
            SpecsLog.class);
    }

    @Override
    public List<SpecsLog> selectList(SpecsLog condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_specsLog"), start,
            count, condition, SpecsLog.class);
    }

}
