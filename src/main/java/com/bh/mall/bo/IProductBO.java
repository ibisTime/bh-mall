package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Product;

public interface IProductBO extends IPaginableBO<Product> {

    void saveProduct(Product data);

    void refreshProduct(Product data);

    Product getProduct(String code);

    void removeProduct(Product data);

    void putdownProduct(Product data, String updater);

    void refreshRepertory(Product data);

    List<Product> selectProductList(Product condition);

    long selectTotalCount(Product condition);

    void putOnProduct(Product data);

}
