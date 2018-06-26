package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Report;

//dao层 
public interface IReportDAO extends IBaseDAO<Report> {
    String NAMESPACE = IReportDAO.class.getName().concat(".");

    int update(Report data);
}
