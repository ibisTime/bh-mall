package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentLevel;

public interface IAgentLevelDAO extends IBaseDAO<AgentLevel> {

    String NAMESPACE = IAgentLevelDAO.class.getName().concat(".");

    /**
     * 修改代理
     * @param data 
     * @create: 2018年1月31日 下午2:54:30 nyc
     * @history:
     */
    public void update(AgentLevel data);

    /**
     * 代理列表
     */
    public List<AgentLevel> selectList(AgentLevel condition);

    /**
     * 根据代理等级查找代理
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午6:19:48 nyc
     * @history:
     */
    public AgentLevel selectByLevel(AgentLevel condition);
}
