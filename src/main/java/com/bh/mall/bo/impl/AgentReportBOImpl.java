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
import com.bh.mall.domain.SqForm;

@Component
public class AgentReportBOImpl extends PaginableBOImpl<AgentReport>
        implements IAgentReportBO {

    @Autowired
    private IAgentReportDAO agentReportDAO;

    public void saveAgentReport(SqForm sqForm, Agent agent) {
        AgentReport data = new AgentReport();
        data.setUserId(sqForm.getUserId());
        data.setRealName(sqForm.getRealName());
        data.setWxId(sqForm.getWxId());
        data.setMobile(sqForm.getMobile());

        data.setLevel(agent.getLevel());
        data.setUserReferee(sqForm.getUserReferee());
        data.setIntroducer(agent.getIntroducer());
        data.setHighUserId(agent.getHighUserId());
        data.setManager(agent.getManager());

        data.setProvince(sqForm.getProvince());
        data.setCity(sqForm.getCity());
        data.setArea(sqForm.getArea());
        data.setAddress(sqForm.getAddress());
        data.setImpowerDatetime(agent.getImpowerDatetime());

        agentReportDAO.insert(data);

    }

    @Override
    public int removeAgentReport(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            AgentReport data = new AgentReport();
            count = agentReportDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshAgentReport(AgentReport data) {
        int count = 0;
        return count;
    }

    @Override
    public List<AgentReport> queryAgentReportList(AgentReport condition) {
        return agentReportDAO.selectList(condition);
    }

    @Override
    public AgentReport getAgentReport(String code) {
        AgentReport data = null;
        if (StringUtils.isNotBlank(code)) {
            AgentReport condition = new AgentReport();
            data = agentReportDAO.select(condition);
        }
        return data;
    }
}
