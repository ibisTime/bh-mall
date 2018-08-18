package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.dto.res.XN627852Res;
import com.bh.mall.dto.res.XN627853Res;

/**
 * 代理统计
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:44:08 
 * @history:
 */
@Component
public interface IAgentReportAO {

    static final String DEFAULT_ORDER_COLUMN = "user_id";

    // 分页查询代理统计
    public Paginable<AgentReport> queryAgentReportPage(int start, int limit,
            AgentReport condition);

    // 列表查询代理统计
    public List<AgentReport> queryAgentReportList(AgentReport condition);

    // 根据编号查询代理统计
    public AgentReport getAgentReport(String code);

    public XN627853Res queryAgentReportNumber(int start, int limit,
            AgentReport condition);

    public XN627852Res queryAgentReportPageByP(int start, int limit,
            AgentReport condition);

}
