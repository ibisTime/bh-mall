package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.dto.req.XN627022Req;

public interface IAgentUpgradeAO {

    /**
     * 修改代理升级
     * @param data
     * @return 
     * @create: 2018年1月31日 下午3:42:45 nyc
     * @history:
     */
    public void editAgentUpgrade(XN627022Req req);

    /**
     * 代理升级列表查询
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午4:00:46 nyc
     * @history:
     */
    public List<AgentUpgrade> queryAgentUpgradeList(AgentUpgrade condition);

    /**
     * 代理升级详情
     * @param code
     * @return 
     * @create: 2018年1月31日 下午4:01:00 nyc
     * @history:
     */
    public AgentUpgrade getAgentUpgrade(String code);

    /**
     * 代理升级分页查询
     * @param condition
     * @param start
     * @param limit
     * @return
     */
    public Paginable<AgentUpgrade> queryAgentUpgradeListPage(int start,
            int limit, AgentUpgrade condition);

}
