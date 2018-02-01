package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;

public interface IAgentBO extends IPaginableBO<Agent> {

    public void editAgent(Agent data, String level, String name);

    public Agent getAgent(String code);

    public List<Agent> queryAgentList(Agent condition);

    /**
     * 根据名字或等级检查代理是否存在
     * @param name
     * @param level
     * @return 
     * @create: 2018年2月1日 下午6:59:21 nyc
     * @history:
     */
    public Agent checkByNameOrLevel(String name, String level);
}
