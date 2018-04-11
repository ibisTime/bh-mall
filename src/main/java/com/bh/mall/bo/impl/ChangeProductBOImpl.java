package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IChangeProductBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IChangeProductDAO;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.exception.BizException;

@Component
public class ChangeProductBOImpl extends PaginableBOImpl<ChangeProduct>
        implements IChangeProductBO {

    @Autowired
    private IChangeProductDAO changeProductDAO;

    @Override
    public void saveChangeProduct(ChangeProduct data) {
        changeProductDAO.insert(data);

    }

    @Override
    public int removeChangeProduct(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ChangeProduct data = new ChangeProduct();
            data.setCode(code);
            count = changeProductDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshChangeProduct(ChangeProduct data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
        }
        return count;
    }

    @Override
    public List<ChangeProduct> queryChangeProductList(ChangeProduct condition) {
        return changeProductDAO.selectList(condition);
    }

    @Override
    public ChangeProduct getChangeProduct(String code) {
        ChangeProduct data = null;
        if (StringUtils.isNotBlank(code)) {
            ChangeProduct condition = new ChangeProduct();
            condition.setCode(code);
            data = changeProductDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "置换单不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshChangePrice(ChangeProduct data) {
        changeProductDAO.updateChangePrice(data);
    }

    @Override
    public void approveChange(ChangeProduct data) {
        changeProductDAO.approveChange(data);
    }
}
