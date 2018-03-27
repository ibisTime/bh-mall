package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.exception.BizException;

@Component
public class AgentBOImpl extends PaginableBOImpl<Agent> implements IAgentBO {

    @Autowired
    private IAgentDAO agentDAO;

    @Override
    public void editAgent(Agent data, String name, String amount,
            String minChargeAmount, String redAmount, String updater,
            String remark) {
        data.setName(name);
        data.setAmount(Long.valueOf(amount));
        data.setMinChargeAmount(Long.valueOf(minChargeAmount));
        data.setRedAmount(Long.valueOf(redAmount));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        agentDAO.update(data);
    }

    @Override
    public Agent getAgent(String code) {
        Agent data = null;
        if (StringUtils.isNotBlank(code)) {
            Agent condition = new Agent();
            condition.setCode(code);
            data = agentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<Agent> queryAgentList(Agent condition) {
        return agentDAO.selectList(condition);
    }

}
