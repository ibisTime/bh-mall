package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IDeliveOrderAO;
import com.bh.mall.bo.IDeliveOrderBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.DeliveOrder;

@Service
public class DeliveOrderAOImpl implements IDeliveOrderAO {

    @Autowired
    private IDeliveOrderBO deliveOrderBO;

    @Override
    public String addDeliveOrder(DeliveOrder data) {
        return null;
    }

    @Override
    public void dropDeliveOrder(String code) {
    }

    @Override
    public Paginable<DeliveOrder> queryDeliveOrderPage(int start, int limit,
            DeliveOrder condition) {
        return deliveOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<DeliveOrder> queryDeliveOrderList(DeliveOrder condition) {
        return deliveOrderBO.queryDeliveOrderList(condition);
    }

    @Override
    public DeliveOrder getDeliveOrder(String code) {
        return deliveOrderBO.getDeliveOrder(code);
    }
}
