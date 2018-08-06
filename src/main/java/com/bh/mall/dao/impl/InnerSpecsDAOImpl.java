package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IInnerSpecsDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.InnerSpecs;

@Repository("innerSpecsDAOImpl")
public class InnerSpecsDAOImpl extends AMybatisTemplate
        implements IInnerSpecsDAO {

    @Override
    public int insert(InnerSpecs data) {
        return super.insert(NAMESPACE.concat("insert_innerSpecs"), data);
    }

    @Override
    public int delete(InnerSpecs data) {
        return super.delete(NAMESPACE.concat("delete_innerSpecs"), data);
    }

    @Override
    public InnerSpecs select(InnerSpecs condition) {
        return super.select(NAMESPACE.concat("select_innerSpecs"), condition,
            InnerSpecs.class);
    }

    @Override
    public long selectTotalCount(InnerSpecs condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_innerSpecs_count"), condition);
    }

    @Override
    public List<InnerSpecs> selectList(InnerSpecs condition) {
        return super.selectList(NAMESPACE.concat("select_innerSpecs"),
            condition, InnerSpecs.class);
    }

    @Override
    public List<InnerSpecs> selectList(InnerSpecs condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_innerSpecs"), start,
            count, condition, InnerSpecs.class);
    }

    @Override
    public int update(InnerSpecs data) {
        return super.update(NAMESPACE.concat("update_innerSpecs"), data);
    }

}
