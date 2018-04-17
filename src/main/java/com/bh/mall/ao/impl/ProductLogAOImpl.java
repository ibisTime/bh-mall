package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IProductLogAO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductLog;
import com.bh.mall.exception.BizException;

@Service
public class ProductLogAOImpl implements IProductLogAO {

    @Autowired
    private IProductLogBO productLogBO;

    @Override
    public void editProductLog(ProductLog data) {
        productLogBO.refreshProductLog(data);
    }

    @Override
    public void dropProductLog(String code) {
        productLogBO.removeProductLog(code);
    }

    @Override
    public Paginable<ProductLog> queryProductLogPage(int start, int limit,
            ProductLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        return productLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProductLog> queryProductLogList(ProductLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return productLogBO.queryProductLogList(condition);
    }

    @Override
    public ProductLog getProductLog(String code) {
        return productLogBO.getProductLog(code);
    }
}
