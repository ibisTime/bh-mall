package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.dao.IAgentLogDAO;
import com.bh.mall.dao.ISqFormDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SqForm;
import com.bh.mall.enums.EAgentType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class SqFormBOImpl extends PaginableBOImpl<SqForm> implements ISqFormBO {

    @Autowired
    private ISqFormDAO sqFormDAO;

    @Autowired
    private IAgentLogDAO agentLogDAO;

    @Autowired
    private IAgentDAO agentDAO;

    // 代理申请 （有推荐人）
    @Override
    public String toApply(SqForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        SqForm alData = new SqForm();
        alData.setCode(code);
        sqFormDAO.insert(alData);

        // update agent last log code
        Agent agent = new Agent();
        agent.setLastAgentLog(code);
        agentDAO.updateLog(agent);

        // insert new agent log
        AgentLog log = new AgentLog();
        log.setCode(code);
        log.setApplyUser(data.getUserId());
        log.setRefereeName(data.getUserReferee());
        log.setApplyLevel(data.getApplyLevel());
        log.setStatus(data.getStatus());
        log.setType(EAgentType.Imporder.getCode()); //  授权
        log.setApplyDatetime(data.getApplyDatetime());
        agentLogDAO.insert(log);
        return code;
    }

    // 新增授权申请
    @Override
    public String applySqForm(SqForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());

        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApplyLevel(data.getApplyLevel());

        // alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        sqFormDAO.insert(alData);
        return code;
    }

    // 通过该授权申请
    @Override
    public String approveSqForm(SqForm user) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SqForm alData = new SqForm();

        alData.setCode(code);
        alData.setUserId(user.getUserId());
        alData.setApplyDatetime(user.getApplyDatetime());
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setApplyLevel(user.getApplyLevel());
        alData.setApprover(user.getApprover());
        alData.setApplyLevel(user.getApplyLevel());
        alData.setApproveDatetime(user.getApproveDatetime());
        alData.setRemark(user.getRemark());
        sqFormDAO.insert(alData);
        return code;
    }

    // 代理取消申请
    @Override
    public String cancelSqForm(SqForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.CANCELED.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());
        sqFormDAO.insert(alData);
        return code;

    }

    @Override
    public String cancelSqForm(SqForm data, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.CANCELED.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());
        sqFormDAO.insert(alData);
        return code;

    }

    @Override
    public String addInfo(SqForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        SqForm buser = new SqForm();
        buser.setUserId(data.getUserId());

        data.setCode(code);
        sqFormDAO.insert(data);

        return code;
    }

    @Override
    public String addSqForm(SqForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        SqForm buser = new SqForm();
        buser.setUserId(data.getUserId());

        data.setCode(code);
        sqFormDAO.insert(data);

        return code;
    }

    @Override
    public String saveSqForm(SqForm data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        sqFormDAO.insert(alData);

        return code;
    }

    @Override
    public SqForm getSqForm(String code) {
        SqForm data = null;
        if (StringUtils.isNotBlank(code)) {
            SqForm condition = new SqForm();
            condition.setCode(code);
            data = sqFormDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    @Override
    public List<SqForm> querySqFormPage(int start, int limit,
            SqForm condition) {
        long totalCount = sqFormDAO.selectTotalCount(condition);
        Page<SqForm> page = new Page<SqForm>(start, limit, totalCount);
        return sqFormDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    @Override
    public List<SqForm> querySqFormList(SqForm condition) {
        return sqFormDAO.selectList(condition);
    }

}
