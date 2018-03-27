package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ProductSpecs;

public interface IProductSpecsDAO extends IBaseDAO<ProductSpecs> {
    String NAMESPACE = IProductSpecsDAO.class.getName().concat(".");

    void update(ProductSpecs data);
}
