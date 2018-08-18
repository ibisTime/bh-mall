package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.OrderReport;
import com.bh.mall.domain.OutOrder;

public interface IOrderReportBO extends IPaginableBO<OrderReport> {

    public List<OrderReport> queryOrderReportList(OrderReport condition);

    public OrderReport getOrderReport(String code);

    public void saveInOrderReport(InOrder data);

    public void saveInOrderReport(OutOrder data);

}
