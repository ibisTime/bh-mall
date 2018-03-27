package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IProductSpecsPriceDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ProductSpecsPrice;

@Repository("productSpecsPriceDAOImpl")
public class ProductSpecsPriceDAOImpl extends AMybatisTemplate
        implements IProductSpecsPriceDAO {

    @Override
    public int insert(ProductSpecsPrice data) {
        return super.insert(NAMESPACE.concat("insert_productSpecsPrice"), data);
    }

    @Override
    public int delete(ProductSpecsPrice data) {
        return super.delete(NAMESPACE.concat("delete_productSpecsPrice"), data);
    }

    @Override
    public ProductSpecsPrice select(ProductSpecsPrice condition) {
        return super.select(NAMESPACE.concat("select_productSpecsPrice"),
            condition, ProductSpecsPrice.class);
    }

    @Override
    public long selectTotalCount(ProductSpecsPrice condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_productSpecsPrice_count"), condition);
    }

    @Override
    public List<ProductSpecsPrice> selectList(ProductSpecsPrice condition) {
        return super.selectList(NAMESPACE.concat("select_productSpecsPrice"),
            condition, ProductSpecsPrice.class);
    }

    @Override
    public List<ProductSpecsPrice> selectList(ProductSpecsPrice condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_productSpecsPrice"),
            start, count, condition, ProductSpecsPrice.class);
    }

    @Override
    public void update(ProductSpecsPrice data) {
        super.update(NAMESPACE.concat("update_productSpecsPrice"), data);

    }

}
