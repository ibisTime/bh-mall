package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IReportBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IReportDAO;
import com.bh.mall.domain.Report;
import com.bh.mall.domain.User;
import com.bh.mall.exception.BizException;

@Component
public class ReportBOImpl extends PaginableBOImpl<Report> implements IReportBO {

    @Autowired
    private IReportDAO reportDAO;

    public void saveReport(User user) {
        Report data = new Report();
        data.setUserId(user.getUserId());
        data.setRealName(user.getRealName());
        data.setWxId(user.getWxId());
        data.setMobile(user.getMobile());

        data.setLevel(user.getLevel());
        data.setUserReferee(user.getUserReferee());
        data.setIntroducer(user.getIntroducer());
        data.setHighUserId(user.getHighUserId());
        data.setManager(user.getManager());

        data.setProvince(user.getProvince());
        data.setCity(user.getCity());
        data.setArea(user.getArea());
        data.setAddress(user.getAddress());
        data.setImpowerDatetime(user.getImpowerDatetime());

        reportDAO.insert(data);

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
