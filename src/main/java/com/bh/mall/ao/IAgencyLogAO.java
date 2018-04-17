package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgencyLog;

@Component
public interface IAgencyLogAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addAgencyLog(AgencyLog data);

    public Paginable<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition);

    public List<AgencyLog> queryAgencyLogList(AgencyLog condition);

    public AgencyLog getAgencyLog(String code);

    public Paginable<AgencyLog> queryIntentionAgentFrontPage(int start,
            int limit, AgencyLog condition);

}
