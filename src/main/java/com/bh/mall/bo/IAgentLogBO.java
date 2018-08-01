package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;

/**
 * 代理轨迹
 * @author: LENOVO 
 * @since: 2018年8月1日 下午1:56:49 
 * @history:
 */
public interface IAgentLogBO extends IPaginableBO<AgentLog> {

    // 列表查询
    public List<AgentLog> queryAgentLogList(AgentLog condition);

    // 详情查询
    public AgentLog getAgentLog(String code);

    // 新增代理轨迹
    public String saveAgentLog(Agent data, String toUser);

    // 意向
    public String acceptIntention(AgentLog log, String status);

    // 忽略
    public String ignore(Agent data);

    // 取消授权
    public String cancelImpower(Agent data);

    // 审核授权
    public String approveImpower(AgentLog log, Agent user);

    // 取消审核
    public String approveCanenl(Agent data, String status);

    // 升级
    public String upgradeLevel(Agent data, String payPdf, String toUserId);

    // 审核升级
    public String approveUpgrade(Agent data, String status);

    // 列表查询
    public List<AgentLog> queryAgentLogPage(int start, int limit,
            AgentLog condition);

    // 声请
    public String toApply(Agent dbUser, String payPdf, String status);

    // 更新
    public String refreshHighUser(Agent data);

}
