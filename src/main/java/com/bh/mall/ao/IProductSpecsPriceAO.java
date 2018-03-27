package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductSpecsPrice;

@Component
public interface IProductSpecsPriceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProductSpecsPrice(ProductSpecsPrice data);

    public Paginable<ProductSpecsPrice> queryProductSpecsPricePage(int start,
            int limit, ProductSpecsPrice condition);

    public List<ProductSpecsPrice> queryProductSpecsPriceList(
            ProductSpecsPrice condition);

    public ProductSpecsPrice getProductSpecsPrice(String code);

}
