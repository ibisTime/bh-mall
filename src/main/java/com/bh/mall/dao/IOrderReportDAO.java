package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.OrderReport;

//dao层 
public interface IOrderReportDAO extends IBaseDAO<OrderReport> {
	String NAMESPACE = IOrderReportDAO.class.getName().concat(".");
}