package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IInnerProductDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.InnerProduct;

@Repository("innerProductDAOImpl")
public class InnerProductDAOImpl extends AMybatisTemplate
        implements IInnerProductDAO {

    @Override
    public int insert(InnerProduct data) {
        return super.insert(NAMESPACE.concat("insert_innerProduct"), data);
    }

    @Override
    public int delete(InnerProduct data) {
        return super.delete(NAMESPACE.concat("delete_innerProduct"), data);
    }

    @Override
    public InnerProduct select(InnerProduct condition) {
        return super.select(NAMESPACE.concat("select_innerProduct"), condition,
            InnerProduct.class);
    }

    @Override
    public long selectTotalCount(InnerProduct condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_innerProduct_count"), condition);
    }

    @Override
    public List<InnerProduct> selectList(InnerProduct condition) {
        return super.selectList(NAMESPACE.concat("select_innerProduct"),
            condition, InnerProduct.class);
    }

    @Override
    public List<InnerProduct> selectList(InnerProduct condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_innerProduct"), start,
            count, condition, InnerProduct.class);
    }

    @Override
    public void update(InnerProduct data) {
        super.update(NAMESPACE.concat("update_innerProduct"), data);
    }

    @Override
    public void putOnInnerProduct(InnerProduct data) {
        super.update(NAMESPACE.concat("puton_innerProduct"), data);
    }

    @Override
    public void putdownInnerProduct(InnerProduct data) {
        super.update(NAMESPACE.concat("putdown_innerProduct"), data);
    }

    @Override
    public void changeQuantity(InnerProduct data) {
        super.update(NAMESPACE.concat("changeQuantity"), data);
    }

}
