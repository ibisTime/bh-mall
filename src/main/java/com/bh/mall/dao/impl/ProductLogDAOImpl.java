package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IProductLogDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ProductLog;

@Repository("productLogDAOImpl")
public class ProductLogDAOImpl extends AMybatisTemplate
        implements IProductLogDAO {

    @Override
    public int insert(ProductLog data) {
        return super.insert(NAMESPACE.concat("insert_productLog"), data);
    }

    @Override
    public int delete(ProductLog data) {
        return super.delete(NAMESPACE.concat("delete_byProductCode"), data);
    }

    @Override
    public ProductLog select(ProductLog condition) {
        return super.select(NAMESPACE.concat("select_productLog"), condition,
            ProductLog.class);
    }

    @Override
    public long selectTotalCount(ProductLog condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_productLog_count"), condition);
    }

    @Override
    public List<ProductLog> selectList(ProductLog condition) {
        return super.selectList(NAMESPACE.concat("select_productLog"),
            condition, ProductLog.class);
    }

    @Override
    public List<ProductLog> selectList(ProductLog condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_productLog"), start,
            count, condition, ProductLog.class);
    }

}
