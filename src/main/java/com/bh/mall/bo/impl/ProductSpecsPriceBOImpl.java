package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IProductSpecsPriceDAO;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.dto.req.XN627547Req;
import com.bh.mall.exception.BizException;

@Component
public class ProductSpecsPriceBOImpl extends PaginableBOImpl<ProductSpecsPrice>
        implements IProductSpecsPriceBO {

    @Autowired
    private IProductSpecsPriceDAO productSpecsPriceDAO;

    @Override
    public boolean isProductSpecsPriceExist(String code) {
        ProductSpecsPrice condition = new ProductSpecsPrice();
        condition.setCode(code);
        if (productSpecsPriceDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveProductSpecsPrice(ProductSpecsPrice data) {
        return null;
    }

    @Override
    public int removeProductSpecsPrice(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ProductSpecsPrice data = new ProductSpecsPrice();
            data.setCode(code);
            count = productSpecsPriceDAO.delete(data);
        }
        return count;
    }

    @Override
    public void refreshProductSpecsPrice(List<XN627547Req> list) {
        ProductSpecsPrice data = new ProductSpecsPrice();
        for (XN627547Req req : list) {
            data.setCode(req.getCode());
            data.setLevel(StringValidater.toInteger(req.getLevel()));
            data.setPrice(StringValidater.toLong(req.getPrice()));
            productSpecsPriceDAO.update(data);
        }
    }

    @Override
    public List<ProductSpecsPrice> queryProductSpecsPriceList(
            ProductSpecsPrice condition) {
        return productSpecsPriceDAO.selectList(condition);
    }

    @Override
    public ProductSpecsPrice getProductSpecsPrice(String code) {
        ProductSpecsPrice data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductSpecsPrice condition = new ProductSpecsPrice();
            condition.setCode(code);
            data = productSpecsPriceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "规格价格不存在");
            }
        }
        return data;
    }

    @Override
    public ProductSpecsPrice getPriceBySpecsCode(String spcesCode,
            Integer level) {
        ProductSpecsPrice data = null;
        if (StringUtils.isNotBlank(spcesCode)) {
            ProductSpecsPrice condition = new ProductSpecsPrice();
            condition.setLevel(level);
            condition.setProductSpecsCode(spcesCode);
            data = productSpecsPriceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "规格价格不存在");
            }
        }
        return data;
    }

    @Override
    public ProductSpecsPrice getPriceByLevel(String productSpecsCode,
            Integer level) {
        ProductSpecsPrice data = null;
        if (StringUtils.isNotBlank(productSpecsCode)) {
            ProductSpecsPrice condition = new ProductSpecsPrice();
            condition.setProductSpecsCode(productSpecsCode);
            condition.setLevel(level);
            data = productSpecsPriceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "规格价格不存在");
            }
        }
        return data;
    }

}
