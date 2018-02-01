package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Agent;

public interface IAgentDAO extends IBaseDAO<Agent> {

    String NAMESPACE = IAgentDAO.class.getName().concat(".");

    /**
     * 修改代理
     * @param data 
     * @create: 2018年1月31日 下午2:54:30 nyc
     * @history:
     */
    public void update(Agent data);

    /**
     * 代理列表
     */
    public List<Agent> selectList(Agent condition);

    /**
     * 根据代理等级查找代理
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午6:19:48 nyc
     * @history:
     */
    public Agent selectByLevel(Agent condition);
}
