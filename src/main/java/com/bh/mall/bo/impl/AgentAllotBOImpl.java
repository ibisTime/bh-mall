package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentAllotBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentAllotDAO;
import com.bh.mall.dao.IBuserDAO;
import com.bh.mall.domain.AgentAllot;
import com.bh.mall.domain.BUser;
import com.bh.mall.enums.EAgencyType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class AgentAllotBOImpl extends PaginableBOImpl<AgentAllot>
        implements IAgentAllotBO {

    @Autowired
    private IAgentAllotDAO agentAllotDAO;

    @Autowired
    private IBuserDAO buserDAO;

    /********************* 保存 ************************/

    @Override
    public String addAgentAllot(AgentAllot data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());

        // save new agencylog to buser
        BUser buser = new BUser();
        buser.setUserId(data.getApplyUser());
        buser.setLastAgentLog(code);
        buserDAO.updateLog(buser);

        // add new agent allot log
        data.setCode(code);
        agentAllotDAO.insert(data);
        return code;
    }

    @Override
    public String saveAgentAllot(BUser data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgentAllot alData = new AgentAllot();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserName(toUserId);
        alData.setStatus(EAgencyType.Allot.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        agentAllotDAO.insert(alData);
        return code;
    }

    /********************* 查询 ***********************/

    @Override
    public AgentAllot getAgentAllot(String code) {
        AgentAllot data = null;
        if (StringUtils.isNotBlank(code)) {
            AgentAllot condition = new AgentAllot();
            condition.setCode(code);
            data = agentAllotDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理授权编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<AgentAllot> queryAgentAllotList(AgentAllot condition) {
        return agentAllotDAO.selectList(condition);
    }

    @Override
    public AgentAllot getAgentAllotByLevel(Integer level) {
        AgentAllot condition = new AgentAllot();
        // condition.setLevel(level);
        return agentAllotDAO.select(condition);

    }

    @Override
    public List<AgentAllot> queryAgentAllotPage(int start, int limit,
            AgentAllot condition) {
        long totalCount = agentAllotDAO.selectTotalCount(condition);
        Page<AgentAllot> page = new Page<AgentAllot>(start, limit, totalCount);
        return agentAllotDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    /************************ 意向分配 ***********************/
    @Override
    public String toApply(BUser data, String toUser, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgentAllot alData = new AgentAllot();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserName(toUser);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setApplyUser(data.getUserId());
        Date date = new Date();
        alData.setApplyDatetime(date);

        alData.setStatus(status);
        agentAllotDAO.insert(alData);
        return code;
    }

    /*********************** 向下分配 *************************/
    @Override
    public String refreshHighUser(BUser data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgentAllot alData = new AgentAllot();
        alData.setCode(code);
        alData.setApplyUser(data.getUserId());
        alData.setStatus(EAgencyType.Update.getCode());
        Date date = new Date();
        alData.setApplyDatetime(date);
        agentAllotDAO.insert(alData);
        return code;
    }

    /************ 忽略意向分配 *************/

    @Override
    public String ignore(BUser data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgentAllot alData = new AgentAllot();
        alData.setCode(code);
        alData.setStatus(EUserStatus.IGNORED.getCode()); // 已忽略
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setToUserName(data.getApprover());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApproveDatetime(data.getApproveDatetime());
        // alData.setStatus(data.getStatus());
        agentAllotDAO.insert(alData);
        return code;

    }

    /************ 接受意向分配 *************/
    @Override
    public String acceptIntention(BUser data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgentAllot alData = new AgentAllot();
        alData.setCode(code);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode()); // 等待授权审核
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setToUserName(data.getApprover());
        alData.setApplyLevel(data.getApplyLevel());
        // alData.setStatus(data.getStatus());
        alData.setApproveDatetime(data.getApproveDatetime());
        agentAllotDAO.insert(alData);
        return code;
    }

}
