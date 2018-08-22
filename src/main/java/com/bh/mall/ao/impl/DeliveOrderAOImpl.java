package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IDeliveOrderAO;
import com.bh.mall.bo.IDeliveOrderBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.DeliveOrder;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.dto.req.XN627930Req;
import com.bh.mall.enums.EOrderKind;

@Service
public class DeliveOrderAOImpl implements IDeliveOrderAO {

    @Autowired
    private IDeliveOrderBO deliveOrderBO;

    @Autowired
    private IOutOrderBO outOrderBO;

    @Autowired
    private IInnerOrderBO innerOrderBO;

    @Override
    public Paginable<DeliveOrder> queryDeliveOrderPage(int start, int limit,
            DeliveOrder condition) {
        return deliveOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<DeliveOrder> queryDeliveOrderList(DeliveOrder condition) {
        List<DeliveOrder> list = deliveOrderBO.queryDeliveOrderList(condition);
        for (DeliveOrder data : list) {
            // 发货人转义

        }
        return deliveOrderBO.queryDeliveOrderList(condition);
    }

    @Override
    public DeliveOrder getDeliveOrder(String code) {
        return deliveOrderBO.getDeliveOrder(code);
    }

    @Override
    public void deliverOrder(XN627930Req req) {
        DeliveOrder data = deliveOrderBO.getDeliveOrder(req.getCode());
        if (EOrderKind.OUT_Order.getCode().equals(data.getKind())) {
            OutOrder outOrder = outOrderBO.getOutOrder(data.getCode());
            outOrderBO.deliverOutOrder(outOrder, req.getDeliver(),
                req.getLogisticsCode(), req.getLogisticsCompany(),
                req.getRemark());
        } else {
            InnerOrder innerOrder = innerOrderBO.getInnerOrder(data.getCode());
            innerOrderBO.deliverInnerProduct(innerOrder, req.getDeliver(),
                req.getLogisticsCode(), req.getLogisticsCompany(),
                req.getRemark());
        }
        deliveOrderBO.deliverOrder(data, req.getDeliver(),
            req.getLogisticsCode(), req.getLogisticsCompany(), req.getRemark());

    }
}
