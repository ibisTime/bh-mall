package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.domain.OrderReport;
import com.bh.mall.dto.res.XN627854Res;

@Component
public interface IOrderReportAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public XN627854Res queryOrderReportPage(int start, int limit,
            OrderReport condition);

    public List<OrderReport> queryOrderReportList(OrderReport condition);

    public OrderReport getOrderReport(String code);

}
