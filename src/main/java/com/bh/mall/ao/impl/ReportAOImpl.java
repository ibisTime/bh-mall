package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.ao.IReportAO;
import com.bh.mall.bo.IReportBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Report;

@Service
public class ReportAOImpl implements IReportAO {

    @Autowired
    IReportBO reportBO;

    @Autowired
    IOrderAO orderAO;

    @Override
    public String addReport(Report data) {
        return reportBO.saveReport(data);
    }

    @Override
    public void editReport(Report data) {
    }

    @Override
    public Paginable<Report> queryReportPage(int start, int limit,
            Report condition) {
        Report reprot = reportBO.getReport(condition.getUserId());

        return reportBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Report> queryReportList(Report condition) {
        return reportBO.queryReportList(condition);
    }

    @Override
    public Report getReport(String code) {
        return reportBO.getReport(code);
    }
}
