package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IProductSpecsPriceAO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductSpecsPrice;

@Service
public class ProductSpecsPriceAOImpl implements IProductSpecsPriceAO {

    @Autowired
    IProductSpecsPriceBO productSpecsPriceBO;

    @Override
    public String addProductSpecsPrice(ProductSpecsPrice data) {
        return productSpecsPriceBO.saveProductSpecsPrice(data);
    }

    @Override
    public Paginable<ProductSpecsPrice> queryProductSpecsPricePage(int start,
            int limit, ProductSpecsPrice condition) {
        return productSpecsPriceBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProductSpecsPrice> queryProductSpecsPriceList(
            ProductSpecsPrice condition) {
        return productSpecsPriceBO.queryProductSpecsPriceList(condition);
    }

    @Override
    public ProductSpecsPrice getProductSpecsPrice(String code) {
        return productSpecsPriceBO.getProductSpecsPrice(code);
    }
}
