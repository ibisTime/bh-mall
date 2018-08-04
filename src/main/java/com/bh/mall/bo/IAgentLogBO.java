package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.SqForm;
import com.bh.mall.domain.YxForm;

/**
 * 代理轨迹
 * @author: LENOVO 
 * @since: 2018年8月1日 下午1:56:49 
 * @history:
 */
public interface IAgentLogBO extends IPaginableBO<AgentLog> {

    // 意向单记录
    public String applyYxForm(YxForm data);

    // 升级单记录
    public String applySjForm(SjForm sjForm, Agent data);

    // 授权单记录
    public String applySqForm(SqForm data);

    // 列表查询
    public List<AgentLog> queryAgentLogList(AgentLog condition);

    // 详情查询
    public AgentLog getAgentLog(String code);

}
