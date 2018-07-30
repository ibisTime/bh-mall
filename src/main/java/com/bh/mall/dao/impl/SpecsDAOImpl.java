package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISpecsDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Specs;

@Repository("specsDAOImpl")
public class SpecsDAOImpl extends AMybatisTemplate implements ISpecsDAO {

    @Override
    public int insert(Specs data) {
        return super.insert(NAMESPACE.concat("insert_specs"), data);
    }

    @Override
    public int delete(Specs data) {
        return super.delete(NAMESPACE.concat("delete_byProductCode"), data);
    }

    @Override
    public Specs select(Specs condition) {
        return super.select(NAMESPACE.concat("select_specs"), condition,
            Specs.class);
    }

    @Override
    public long selectTotalCount(Specs condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_specs_count"),
            condition);
    }

    @Override
    public List<Specs> selectList(Specs condition) {
        return super.selectList(NAMESPACE.concat("select_specs"), condition,
            Specs.class);
    }

    @Override
    public List<Specs> selectList(Specs condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_specs"), start, count,
            condition, Specs.class);
    }

    @Override
    public void update(Specs data) {
        super.update(NAMESPACE.concat("update_specs"), data);
    }

}
