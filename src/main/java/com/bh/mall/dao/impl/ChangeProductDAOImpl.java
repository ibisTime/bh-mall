package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IChangeProductDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ChangeProduct;

@Repository("changeProductDAOImpl")
public class ChangeProductDAOImpl extends AMybatisTemplate
        implements IChangeProductDAO {

    @Override
    public int insert(ChangeProduct data) {
        return super.insert(NAMESPACE.concat("insert_changeProduct"), data);
    }

    @Override
    public int delete(ChangeProduct data) {
        return super.delete(NAMESPACE.concat("delete_changeProduct"), data);
    }

    @Override
    public ChangeProduct select(ChangeProduct condition) {
        return super.select(NAMESPACE.concat("select_changeProduct"), condition,
            ChangeProduct.class);
    }

    @Override
    public long selectTotalCount(ChangeProduct condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_changeProduct_count"), condition);
    }

    @Override
    public List<ChangeProduct> selectList(ChangeProduct condition) {
        return super.selectList(NAMESPACE.concat("select_changeProduct"),
            condition, ChangeProduct.class);
    }

    @Override
    public List<ChangeProduct> selectList(ChangeProduct condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_changeProduct"), start,
            count, condition, ChangeProduct.class);
    }

}
