package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IReportDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Report;

@Repository("reportDAOImpl")
public class ReportDAOImpl extends AMybatisTemplate implements IReportDAO {

    @Override
    public int insert(Report data) {
        return super.insert(NAMESPACE.concat("insert_report"), data);
    }

    @Override
    public int delete(Report data) {
        return super.delete(NAMESPACE.concat("delete_report"), data);
    }

    @Override
    public Report select(Report condition) {
        return super.select(NAMESPACE.concat("select_report"), condition,
            Report.class);
    }

    @Override
    public long selectTotalCount(Report condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_report_count"),
            condition);
    }

    @Override
    public List<Report> selectList(Report condition) {
        return super.selectList(NAMESPACE.concat("select_report"), condition,
            Report.class);
    }

    @Override
    public List<Report> selectList(Report condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_report"), start, count,
            condition, Report.class);
    }

    @Override
    public int update(Report data) {
        return 0;
    }

}
