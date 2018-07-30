package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IExchangeProductBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IExchangeProductDAO;
import com.bh.mall.domain.ExchangeProduct;
import com.bh.mall.exception.BizException;

@Component
public class ExchangeProductBOImpl extends PaginableBOImpl<ExchangeProduct>
        implements IExchangeProductBO {

    @Autowired
    private IExchangeProductDAO changeProductDAO;

    @Override
    public void saveChangeProduct(ExchangeProduct data) {
        changeProductDAO.insert(data);

    }

    @Override
    public int removeChangeProduct(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ExchangeProduct data = new ExchangeProduct();
            data.setCode(code);
            count = changeProductDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshChangeProduct(ExchangeProduct data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
        }
        return count;
    }

    @Override
    public List<ExchangeProduct> queryChangeProductList(ExchangeProduct condition) {
        return changeProductDAO.selectList(condition);
    }

    @Override
    public ExchangeProduct getChangeProduct(String code) {
        ExchangeProduct data = null;
        if (StringUtils.isNotBlank(code)) {
            ExchangeProduct condition = new ExchangeProduct();
            condition.setCode(code);
            data = changeProductDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "置换单不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshChangePrice(ExchangeProduct data) {
        changeProductDAO.updateChangePrice(data);
    }

    @Override
    public void approveChange(ExchangeProduct data) {
        changeProductDAO.approveChange(data);
    }

    @Override
    public List<ExchangeProduct> queryChangeProductPage(int start, int pageSize,
            ExchangeProduct condition) {
        return changeProductDAO.queryChangeProductPage(start, pageSize,
            condition);
    }
}
