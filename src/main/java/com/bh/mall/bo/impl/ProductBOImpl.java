package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IProductDAO;
import com.bh.mall.domain.Product;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.exception.BizException;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product>
        implements IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Override
    public void saveProduct(Product data) {
        productDAO.insert(data);
    }

    @Override
    public void refreshProduct(Product data) {
        productDAO.update(data);
    }

    @Override
    public Product getProduct(String code) {
        Product data = null;
        if (StringUtils.isNotBlank(code)) {
            Product condition = new Product();
            condition.setCode(code);
            data = productDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "产品不存在");
            }
        }
        return data;
    }

    @Override
    public void removeProduct(Product data) {
        productDAO.delete(data);
    }

    @Override
    public void putOnProduct(Product data) {
        productDAO.putonProduct(data);
    }

    @Override
    public void putdownProduct(Product data, String updater) {
        data.setStatus(EProductStatus.Shelf_NO.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        productDAO.putdownProduct(data);
    }

    @Override
    public void refreshRepertory(Product data) {
        productDAO.update(data);
    }

    @Override
    public List<Product> selectProductList(Product condition) {
        return productDAO.selectList(condition);
    }

    @Override
    public long selectTotalCount(Product condition) {
        return productDAO.selectTotalCount(condition);
    }

    @Override
    public List<Product> selectProductPageByFront(Product condition, int start,
            int limit) {
        return productDAO.selectProductPageByFront(condition, start, limit);
    }

}
