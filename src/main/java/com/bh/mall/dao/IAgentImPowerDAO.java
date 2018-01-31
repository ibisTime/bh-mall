package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentImpower;

public interface IAgentImPowerDAO extends IBaseDAO<AgentImpower> {

    String NAMESPACE = IAgentImPowerDAO.class.getName().concat(".");

    /**
     * 修改代理升级
     * @param data 
     * @create: 2018年1月31日 下午3:35:28 nyc
     * @history:
     */
    public void update(AgentImpower data);

    /**
     * 插叙代理授权列表
     */
    public List<AgentImpower> selectList(AgentImpower condition);

}
