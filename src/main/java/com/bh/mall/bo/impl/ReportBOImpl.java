package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IReportBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IReportDAO;
import com.bh.mall.domain.Report;
import com.bh.mall.exception.BizException;

@Component
public class ReportBOImpl extends PaginableBOImpl<Report> implements IReportBO {

    @Autowired
    private IReportDAO reportDAO;

    public String saveReport(Report data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.Report.getCode());
            reportDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeReport(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Report data = new Report();
            count = reportDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshReport(Report data) {
        int count = 0;
        return count;
    }

    @Override
    public List<Report> queryReportList(Report condition) {
        return reportDAO.selectList(condition);
    }

    @Override
    public Report getReport(String code) {
        Report data = null;
        if (StringUtils.isNotBlank(code)) {
            Report condition = new Report();
            data = reportDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
