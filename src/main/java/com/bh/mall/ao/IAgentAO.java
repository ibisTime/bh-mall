package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;

public interface IAgentAO {

    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 修改代理
     * @param code
     * @param level
     * @param name 
     * @create: 2018年1月31日 上午9:45:54 chenshan
     * @history:
     */
    public void editAgent(String code, String level, String name);

    /**
     * 分页查询代理
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午2:30:59 nyc
     * @history:
     */
    public Paginable<Agent> queryAgentListPage(int start, int limit,
            Agent condition);

    /**
     * 查询代理详情
     * @param code
     * @return 
     * @create: 2018年1月31日 下午2:31:14 nyc
     * @history:
     */
    public Agent getAgent(String code);

    /**
     * 列表查询代理
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午2:31:23 nyc
     * @history:
     */
    public List<Agent> queryAgentList(Agent condition);

    /**
     * 根据名称和等级查询
     * @param name
     * @param Level
     * @return 
     * @create: 2018年2月1日 下午6:48:43 nyc
     * @history:
     */
    public Agent checkByNameOrLevel(String name, String level);

}
