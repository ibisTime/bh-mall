package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.dto.req.XN627250Req;

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

    // 申请意向代理
    public void applyIntent(XN627250Req req);

}
