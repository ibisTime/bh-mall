package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IProductDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Product;

@Repository("productDAOImpl")
public class ProductDAOImpl extends AMybatisTemplate implements IProductDAO {

    @Override
    public int insert(Product data) {
        return super.insert(NAMESPACE.concat("insert_product"), data);
    }

    @Override
    public int delete(Product data) {
        return super.delete(NAMESPACE.concat("delete_product"), data);
    }

    @Override
    public Product select(Product condition) {
        return super.select(NAMESPACE.concat("select_product"), condition,
            Product.class);
    }

    @Override
    public long selectTotalCount(Product condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_product_count"),
            condition);
    }

    @Override
    public List<Product> selectList(Product condition) {
        return super.selectList(NAMESPACE.concat("select_product"), condition,
            Product.class);
    }

    @Override
    public List<Product> selectList(Product condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_product"), start,
            limit, condition, Product.class);
    }

    @Override
    public void update(Product data) {
        super.update(NAMESPACE.concat("update_product"), data);
    }

    @Override
    public void putonProduct(Product data) {
        super.update(NAMESPACE.concat("puton_product"), data);
    }

    @Override
    public void putdownProduct(Product data) {
        super.update(NAMESPACE.concat("putdown_product"), data);
    }

    @Override
    public List<Product> selectProductPage(Product condition, int start,
            int limit) {
        return super.selectList(NAMESPACE.concat("select_product"), start,
            limit, condition, Product.class);
    }

}
