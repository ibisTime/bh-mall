package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentUpgrade;

public interface IAgentUpgradeDAO extends IBaseDAO<AgentUpgrade> {

    String NAMESPACE = IAgentUpgradeDAO.class.getName().concat(".");

    /**
     * 更新代理升级
     * @param data
     * @return 
     * @create: 2018年1月31日 下午3:58:32 nyc
     * @history:
     */
    public void update(AgentUpgrade data);

    /**
     * 查询代理升级列表
     */
    public List<AgentUpgrade> selectList(AgentUpgrade condition);
}
