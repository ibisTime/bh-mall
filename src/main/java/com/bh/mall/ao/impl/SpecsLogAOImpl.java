package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISpecsLogAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.SpecsLog;
import com.bh.mall.enums.ESpecsLogType;
import com.bh.mall.exception.BizException;

@Service
public class SpecsLogAOImpl implements ISpecsLogAO {

    @Autowired
    private ISpecsLogBO specsLogBO;

    @Autowired
    private ISpecsBO specsBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAgentBO agentBO;

    @Override
    public void editSpecsLog(SpecsLog data) {
        specsLogBO.refreshSpecsLog(data);
    }

    @Override
    public void dropSpecstLog(String code) {
        specsLogBO.removeSpecsLog(code);
    }

    @Override
    public Paginable<SpecsLog> querySpecsLogPage(int start, int limit,
            SpecsLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<SpecsLog> page = specsLogBO.getPaginable(start, limit,
            condition);
        SYSUser sysUser = null;
        Agent agent = null;
        for (SpecsLog data : page.getList()) {
            if (ESpecsLogType.Order.getCode().equals(data.getType())
                    || ESpecsLogType.QX_ORDER.getCode()
                        .equals(data.getType())) {
                agent = agentBO.getAgent(data.getUpdater());
                data.setUpdateName(agent.getRealName());
            } else {
                sysUser = sysUserBO.getSYSUser(data.getUpdater());
                data.setUpdateName(sysUser.getRealName());
            }
            Specs specs = specsBO.getSpecs(data.getSpecsCode());
            data.setSpecsName(specs.getName());
        }
        return page;
    }

    @Override
    public List<SpecsLog> querySpecsLogList(SpecsLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return specsLogBO.querySpecsLogList(condition);
    }

    @Override
    public SpecsLog getSpecsLog(String code) {
        SpecsLog data = specsLogBO.getSpecsLog(code);
        if (ESpecsLogType.Input.getCode().equals(data.getType())
                || ESpecsLogType.Output.getCode().equals(data.getType())
                || ESpecsLogType.ChangeProduct.getCode()
                    .equals(data.getType())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
            data.setUpdateName(sysUser.getRealName());
        } else {
            Agent agent = agentBO.getAgent(data.getUpdater());
            data.setUpdateName(agent.getRealName());
        }
        return data;
    }
}
