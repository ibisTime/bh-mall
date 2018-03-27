package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductSpecs;

@Component
public interface IProductSpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProductSpecs(ProductSpecs data);

    public void dropProductSpecs(String code);

    public void editProductSpecs(ProductSpecs data);

    public Paginable<ProductSpecs> queryProductSpecsPage(int start, int limit,
            ProductSpecs condition);

    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition);

    public ProductSpecs getProductSpecs(String code);

}
