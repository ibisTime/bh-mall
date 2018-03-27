package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ProductSpecsPrice;

public interface IProductSpecsPriceDAO extends IBaseDAO<ProductSpecsPrice> {

    String NAMESPACE = IProductSpecsPriceDAO.class.getName().concat(".");

    void update(ProductSpecsPrice data);
}
