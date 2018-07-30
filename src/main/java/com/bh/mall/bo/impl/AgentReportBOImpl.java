package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentReportDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;

@Component
public class AgentReportBOImpl extends PaginableBOImpl<AgentReport> implements IAgentReportBO {

    @Autowired
    private IAgentReportDAO reportDAO;

    public void saveReport(Agent user) {
        AgentReport data = new AgentReport();
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
            AgentReport data = new AgentReport();
            count = reportDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshReport(AgentReport data) {
        int count = 0;
        return count;
    }

    @Override
    public List<AgentReport> queryReportList(AgentReport condition) {
        return reportDAO.selectList(condition);
    }

    @Override
    public AgentReport getReport(String code) {
        AgentReport data = null;
        if (StringUtils.isNotBlank(code)) {
            AgentReport condition = new AgentReport();
            data = reportDAO.select(condition);
        }
        return data;
    }
}
