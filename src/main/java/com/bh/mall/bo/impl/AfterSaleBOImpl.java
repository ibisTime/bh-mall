package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAfterSaleDAO;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.exception.BizException;

@Component
public class AfterSaleBOImpl extends PaginableBOImpl<AfterSale>
        implements IAfterSaleBO {

    @Autowired
    private IAfterSaleDAO afterSaleDAO;

    @Override
    public void saveAfterSale(AfterSale data) {
        afterSaleDAO.insert(data);
    }

    @Override
    public void removeAfterSale(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            AfterSale data = new AfterSale();
            data.setCode(code);
            count = afterSaleDAO.delete(data);
        }
    }

    @Override
    public List<AfterSale> queryAfterSaleList(AfterSale condition) {
        return afterSaleDAO.selectList(condition);
    }

    @Override
    public AfterSale getAfterSale(String code) {
        AfterSale data = null;
        if (StringUtils.isNotBlank(code)) {
            AfterSale condition = new AfterSale();
            condition.setCode(code);
            data = afterSaleDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "售后单不存在");
            }
        }
        return data;
    }

    @Override
    public void approvrAfterSale(AfterSale data) {
        afterSaleDAO.approvreAfterSale(data);
    }

    @Override
    public void changeProduct(AfterSale data) {
        afterSaleDAO.changeProduct(data);

    }

    @Override
    public long selectCount(AfterSale asCondition) {
        return afterSaleDAO.selectTotalCount(asCondition);
    }
}
