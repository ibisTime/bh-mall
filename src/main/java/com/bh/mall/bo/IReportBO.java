package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Report;
import com.bh.mall.domain.User;

public interface IReportBO extends IPaginableBO<Report> {

    public void saveReport(User data);

    public int removeReport(String code);

    public int refreshReport(Report data);

    public List<Report> queryReportList(Report condition);

    public Report getReport(String code);

}
