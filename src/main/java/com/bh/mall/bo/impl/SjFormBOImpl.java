<<<<<<< HEAD
package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.dao.IAgentLogDAO;
import com.bh.mall.dao.ISjFormDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SjForm;
import com.bh.mall.enums.EAgentType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class SjFormBOImpl extends PaginableBOImpl<SjForm> implements ISjFormBO {

    @Autowired
    private ISjFormDAO sjFormDAO;

    @Autowired
    private IAgentLogDAO agentLogDAO;

    @Autowired
    private IAgentDAO agentDAO;

    @Autowired
    private IAgentBO agentBO;

    // 升级申请
    @Override
    public String applySjForm(SjForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        SjForm alData = new SjForm();
        alData.setCode(code);
        sjFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(data.getStatus());
        log.setType(EAgentType.Upgrade.getCode()); // 升级
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 升级审核通过
    @Override
    public String approveSjForm(SjForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.UPGRADED.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());

        alData.setRemark(data.getRemark());
        sjFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(data.getStatus());
        log.setType(EAgentType.Upgrade.getCode()); // 升级
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 取消升级申请
    @Override
    public String approveCanenlSjForm(SjForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();

        alData.setCode(code);
        alData.setUserId(data.getUserId());
        alData.setStatus(EUserStatus.CANCELED.getCode());

        alData.setApplyLevel(data.getApplyLevel());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());

        sjFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(data.getStatus());
        log.setType(EAgentType.Upgrade.getCode()); // 升级
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 新增升级表
    @Override
    public String addSjForm(SjForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        // SjForm buser = new SjForm();

        // UpLevelApply alData = new UpLevelApply();
        data.setCode(code);
        sjFormDAO.insert(data);
        return code;
    }

    // 保存升级表
    @Override
    public String saveSjForm(SjForm data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        sjFormDAO.insert(alData);

        return code;
    }

    // 详细查询
    @Override
    public SjForm getSjForm(String code) {
        SjForm data = null;
        if (StringUtils.isNotBlank(code)) {
            SjForm condition = new SjForm();
            condition.setCode(code);
            data = sjFormDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    // 分页查询
    @Override
    public List<SjForm> querySjFormPage(int start, int limit,
            SjForm condition) {
        long totalCount = sjFormDAO.selectTotalCount(condition);
        Page<SjForm> page = new Page<SjForm>(start, limit, totalCount);
        return sjFormDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    // 列表查询
    @Override
    public List<SjForm> querySjFormList(SjForm condition) {
        return sjFormDAO.selectList(condition);
    }

}
=======
package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.dao.IAgentLogDAO;
import com.bh.mall.dao.ISjFormDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SjForm;
import com.bh.mall.enums.EAgentType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class SjFormBOImpl extends PaginableBOImpl<SjForm> implements ISjFormBO {

    @Autowired
    private ISjFormDAO sjFormDAO;

    @Autowired
    private IAgentLogDAO agentLogDAO;

    @Autowired
    private IAgentDAO agentDAO;

    @Autowired
    private IAgentBO agentBO;

    // 升级申请
    @Override
    public String applySjForm(SjForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        SjForm alData = new SjForm();
        alData.setCode(code);
        sjFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(data.getStatus());
        log.setType(EAgentType.Upgrade.getCode()); // 升级
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 升级审核通过
    @Override
    public String approveSjForm(SjForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.UPGRADED.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());

        alData.setRemark(data.getRemark());
        sjFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(data.getStatus());
        log.setType(EAgentType.Upgrade.getCode()); // 升级
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 取消升级申请
    @Override
    public String approveCanenlSjForm(SjForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();

        alData.setCode(code);
        alData.setUserId(data.getUserId());
        alData.setStatus(EUserStatus.CANCELED.getCode());

        alData.setApplyLevel(data.getApplyLevel());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());

        sjFormDAO.insert(alData);

        // update agent last log code
        Agent agent = agentBO.getAgent(data.getUserId());
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(data.getStatus());
        log.setType(EAgentType.Upgrade.getCode()); // 升级
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 新增升级表
    @Override
    public String addSjForm(SjForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        // SjForm buser = new SjForm();

        // UpLevelApply alData = new UpLevelApply();
        data.setCode(code);
        sjFormDAO.insert(data);
        return code;
    }

    // 保存升级表
    @Override
    public String saveSjForm(SjForm data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        sjFormDAO.insert(alData);

        return code;
    }

    // 详细查询
    @Override
    public SjForm getSjForm(String code) {
        SjForm data = null;
        if (StringUtils.isNotBlank(code)) {
            SjForm condition = new SjForm();
            condition.setCode(code);
            data = sjFormDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    // 分页查询
    @Override
    public List<SjForm> querySjFormPage(int start, int limit,
            SjForm condition) {
        long totalCount = sjFormDAO.selectTotalCount(condition);
        Page<SjForm> page = new Page<SjForm>(start, limit, totalCount);
        return sjFormDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    // 列表查询
    @Override
    public List<SjForm> querySjFormList(SjForm condition) {
        return sjFormDAO.selectList(condition);
    }

}
>>>>>>> refs/remotes/origin/master
