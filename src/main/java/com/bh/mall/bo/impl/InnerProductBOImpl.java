package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IInnerProductBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IInnerProductDAO;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.exception.BizException;

@Component
public class InnerProductBOImpl extends PaginableBOImpl<InnerProduct>
        implements IInnerProductBO {

    @Autowired
    private IInnerProductDAO innerProductDAO;

    @Override
    public void saveInnerProduct(InnerProduct data) {
        innerProductDAO.insert(data);
    }

    @Override
    public void removeInnerProduct(InnerProduct data) {
        innerProductDAO.delete(data);
    }

    @Override
    public void refreshInnerProduct(InnerProduct data) {
        innerProductDAO.update(data);
    }

    @Override
    public List<InnerProduct> queryInnerProductList(InnerProduct condition) {
        return innerProductDAO.selectList(condition);
    }

    @Override
    public InnerProduct getInnerProduct(String code) {
        InnerProduct data = null;
        if (StringUtils.isNotBlank(code)) {
            InnerProduct condition = new InnerProduct();
            condition.setCode(code);
            data = innerProductDAO.select(condition);
            if (null == data) {
                throw new BizException("xn0000", "产品不存在");
            }
        }
        return data;
    }

    @Override
    public void putOnInnerProduct(InnerProduct data) {
        innerProductDAO.putOnInnerProduct(data);
    }

    @Override
    public void putdownInnerProduct(InnerProduct data) {
        innerProductDAO.putdownInnerProduct(data);
    }

    @Override
    public void changeQuantity(InnerProduct data) {
        innerProductDAO.changeQuantity(data);
    }
}
