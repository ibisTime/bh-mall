package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgencyLogAO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgencyLog;

@Service
public class AgencyLogAOImpl implements IAgencyLogAO {

    @Autowired
    private IAgencyLogBO agencyLogBO;

    @Override
    public String addAgencyLog(AgencyLog data) {
        return null;
    }

    @Override
    public Paginable<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition) {
        return agencyLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AgencyLog> queryAgencyLogList(AgencyLog condition) {
        return agencyLogBO.queryAgencyLogList(condition);
    }

    @Override
    public AgencyLog getAgencyLog(String code) {
        return agencyLogBO.getAgencyLog(code);
    }

}
