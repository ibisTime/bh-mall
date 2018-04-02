package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgencyLogDAO;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.User;
import com.bh.mall.enums.EAgencyType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class AgencyLogBOImpl extends PaginableBOImpl<AgencyLog>
        implements IAgencyLogBO {

    @Autowired
    private IAgencyLogDAO agencyLogDAO;

    @Override
    public String saveAgencyLog(String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog data = new AgencyLog();
        data.setCode(code);
        data.setToUserId(toUserId);
        data.setType(EAgencyType.Allot.getCode());
        data.setApprover(data.getApprover());
        data.setApproveDatetime(data.getApproveDatetime());
        data.setStatus(EUserStatus.Alloted.getCode());
        agencyLogDAO.insert(data);
        return code;
    }

    @Override
    public List<AgencyLog> queryAgencyLogList(AgencyLog condition) {
        return agencyLogDAO.selectList(condition);
    }

    @Override
    public AgencyLog getAgencyLog(String code) {
        AgencyLog data = null;
        if (StringUtils.isNotBlank(code)) {
            AgencyLog condition = new AgencyLog();
            condition.setCode(code);
            data = agencyLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    @Override
    public String pass(User data, String approver, String remark) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Allot.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());
        alData.setToUserId(agencyLog.getToUserId());
        alData.setApprover(approver);
        alData.setApproveDatetime(new Date());
        alData.setStatus(EUserStatus.TO_Approve.getCode());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String ignore(User data) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Allot.getCode());
        alData.setToUserId(agencyLog.getToUserId());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(EUserStatus.Ignored.getCode());
        agencyLogDAO.insert(alData);
        return code;

    }

    @Override
    public String cancelImpower(User data) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Imporder.getCode());
        alData.setToUserId(agencyLog.getToUserId());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());
        alData.setStatus(EUserStatus.TO_Approve.getCode());
        agencyLogDAO.insert(alData);
        return code;

    }

    @Override
    public String approveImpower(User data) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Imporder.getCode());
        alData.setToUserId(agencyLog.getToUserId());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApplyDatetime());
        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String approveCanenl(User data) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Imporder.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApplyDatetime());
        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String upgradeLevel(User data, String payPdf) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Upgrade.getCode());
        alData.setPayPdf(payPdf);
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());

        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String approveUpgrade(User data) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Upgrade.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public List<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition) {
        long totalCount = agencyLogDAO.selectTotalCount(condition);
        Page<AgencyLog> page = new Page<AgencyLog>(start, limit, totalCount);
        return agencyLogDAO.selectList(condition, page.getPageNo(),
            page.getPageSize());
    }

}
