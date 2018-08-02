package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.dao.IAgentLogDAO;
import com.bh.mall.dao.IYxFormDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.YxForm;
import com.bh.mall.enums.EAgentType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class YxFormBOImpl extends PaginableBOImpl<YxForm> implements IYxFormBO {

    @Autowired
    private IYxFormDAO yxFormDAO;

    @Autowired
    private IAgentLogDAO agentLogDAO;

    @Autowired
    private IAgentDAO agentDAO;

    @Autowired
    private IAgentBO agentBO;

    // 代理申请
    @Override
    public String applyYxForm(YxForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        YxForm alData = new YxForm();
        alData.setCode(code);
        yxFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(EUserStatus.MIND.getCode());
        log.setType(EAgentType.Allot.getCode()); // 意向分配
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);

        return code;
    }

    // 意向分配
    @Override
    public String allotYxForm(YxForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        yxFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(EUserStatus.MIND.getCode());
        log.setType(EAgentType.Allot.getCode()); // 意向分配
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);

        return code;
    }

    // 忽略意向分配
    @Override
    public String ignore(YxForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        YxForm alData = new YxForm();
        alData.setCode(code);
        yxFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setStatus(EUserStatus.IGNORED.getCode());
        log.setType(EAgentType.Allot.getCode()); // 意向分配
        log.setApprover(data.getApprover());
        log.setApproveDatetime(data.getApproveDatetime());
        agentLogDAO.insert(log);
        return code;

    }

    // 接受意向分配
    @Override
    public String accepYxForm(YxForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        yxFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(EUserStatus.ADD_INFO.getCode());
        log.setType(EAgentType.Allot.getCode()); // 意向分配
        log.setApprover(data.getApprover());
        log.setApproveDatetime(data.getApproveDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 保存意向分配表
    @Override
    public String saveYxForm(YxForm data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(toUserId);
        alData.setStatus(EAgentType.Allot.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        yxFormDAO.insert(alData);
        return code;
    }

    // 详细查询
    @Override
    public YxForm getYxForm(String code) {
        YxForm data = null;
        if (StringUtils.isNotBlank(code)) {
            YxForm condition = new YxForm();
            condition.setCode(code);
            data = yxFormDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理授权编号不存在");
            }
        }
        return data;
    }

    // 列表查询
    @Override
    public List<YxForm> queryYxFormList(YxForm condition) {
        return yxFormDAO.selectList(condition);
    }

    // 根据等级查询
    @Override
    public YxForm getYxFormByLevel(Integer level) {
        YxForm condition = new YxForm();
        condition.setLevel(level);
        return yxFormDAO.select(condition);

    }

    // 分页查询
    @Override
    public List<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition) {
        long totalCount = yxFormDAO.selectTotalCount(condition);
        Page<YxForm> page = new Page<YxForm>(start, limit, totalCount);
        return yxFormDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

}
