package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Product;

public interface IProductDAO extends IBaseDAO<Product> {

    String NAMESPACE = IProductDAO.class.getName().concat(".");

    void update(Product data);

    void putonProduct(Product data);

    void putdownProduct(Product data);

    List<Product> selectProductPage(Product condition, int start, int limit);

}
