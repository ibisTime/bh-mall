package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IProductSpecsAO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductSpecs;

@Service
public class ProductSpecsAOImpl implements IProductSpecsAO {

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public void dropProductSpecs(String code) {
        productSpecsBO.removeProductSpecs(code);
    }

    @Override
    public Paginable<ProductSpecs> queryProductSpecsPage(int start, int limit,
            ProductSpecs condition) {
        return productSpecsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition) {
        return productSpecsBO.queryProductSpecsList(condition);
    }

    @Override
    public ProductSpecs getProductSpecs(String code) {
        return productSpecsBO.getProductSpecs(code);
    }

    @Override
    public String addProductSpecs(ProductSpecs data) {
        return null;
    }

    @Override
    public void editProductSpecs(ProductSpecs data) {
    }

}
