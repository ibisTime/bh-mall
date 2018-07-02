package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.ao.IReportAO;
import com.bh.mall.bo.IReportBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Report;
import com.bh.mall.domain.User;
import com.bh.mall.exception.BizException;

@Service
public class ReportAOImpl implements IReportAO {

    @Autowired
    IReportBO reportBO;

    @Autowired
    IOrderAO orderAO;

    @Autowired
    IUserBO userBO;

    @Override
    public Paginable<Report> queryReportPage(int start, int limit,
            Report condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<Report> page = reportBO.getPaginable(start, limit, condition);
        User userName = null;
        for (Report report : page.getList()) {
            if (StringUtils.isNotBlank(report.getUserReferee())) {
                // 推荐/介绍人转义
                userName = userBO.getUser(report.getUserReferee());
                report.setUserRefereeName(userName.getRealName());
                userName = userBO.getUser(report.getIntroducer());
                report.setIntroduceName(userName.getRealName());
                userName = userBO.getUser(report.getManager());
                report.setManageName(userName.getRealName());
            }
        }

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
