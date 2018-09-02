package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IOrderReportAO;
import com.bh.mall.bo.IOrderReportBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.OrderReport;
import com.bh.mall.domain.Product;
import com.bh.mall.dto.res.XN627854Res;

@Service
public class OrderReportAOImpl implements IOrderReportAO {

    @Autowired
    private IOrderReportBO orderReportBO;

    @Autowired
    private IProductBO productBO;

    @Override
    public XN627854Res queryOrderReportPage(int start, int limit,
            OrderReport condition) {

        Paginable<OrderReport> page = orderReportBO.getPaginable(start, limit,
            condition);

        Long amount = 0L;
        for (OrderReport data : page.getList()) {
            amount = amount + data.getAmount();
            Product product = productBO.getProduct(data.getProductCode());
            data.setPic(product.getPic());

        }
        XN627854Res res = new XN627854Res(page.getList(), amount);
        return res;
    }

    @Override
    public List<OrderReport> queryOrderReportList(OrderReport condition) {
        return orderReportBO.queryOrderReportList(condition);
    }

    @Override
    public OrderReport getOrderReport(String code) {
        OrderReport data = orderReportBO.getOrderReport(code);
        Product product = productBO.getProduct(data.getProductCode());
        data.setPic(product.getPic());
        return data;
    }
}
