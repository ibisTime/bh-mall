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
import com.bh.mall.exception.BizException;

@Component
public class AgencyLogBOImpl extends PaginableBOImpl<AgencyLog>
        implements IAgencyLogBO {

    @Autowired
    private IAgencyLogDAO agencyLogDAO;

    @Override
    public String saveAgencyLog(User data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(toUserId);
        alData.setType(EAgencyType.Allot.getCode());
        alData.setTeamName(data.getTeamName());

        alData.setUserReferee(data.getUserReferee());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        agencyLogDAO.insert(alData);

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
    public String acceptIntention(AgencyLog log, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Imporder.getCode());
        alData.setApplyUser(log.getApplyUser());
        alData.setApplyDatetime(new Date());
        alData.setToUserId(log.getToUserId());

        alData.setApplyLevel(log.getApplyLevel());
        alData.setTeamName(log.getTeamName());
        alData.setUserReferee(log.getUserReferee());
        alData.setApprover(log.getApprover());
        alData.setApproveDatetime(new Date());

        alData.setStatus(log.getStatus());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String ignore(User data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Allot.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());

        alData.setApplyLevel(data.getApplyLevel());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());

        alData.setStatus(data.getStatus());
        agencyLogDAO.insert(alData);
        return code;

    }

    @Override
    public String cancelImpower(User data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.OUT.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setLevel(data.getLevel());

        alData.setHighUserId(data.getHighUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApprover(data.getApprover());

        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        agencyLogDAO.insert(alData);
        return code;

    }

    @Override
    public String approveImpower(AgencyLog log, User user) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();

        alData.setCode(code);
        alData.setApplyUser(user.getUserId());
        alData.setApplyDatetime(user.getApplyDatetime());
        alData.setType(EAgencyType.Imporder.getCode());
        alData.setApplyLevel(user.getApplyLevel());

        alData.setToUserId(log.getToUserId());
        alData.setTeamName(user.getTeamName());
        alData.setUserReferee(user.getUserReferee());
        alData.setApprover(user.getApprover());

        alData.setApplyLevel(user.getApplyLevel());
        alData.setLevel(user.getLevel());
        alData.setHighUserId(user.getHighUserId());
        alData.setApproveDatetime(user.getApproveDatetime());
        alData.setStatus(user.getStatus());

        alData.setRemark(log.getRemark());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String approveCanenl(User data, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();

        alData.setCode(code);
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setType(EAgencyType.OUT.getCode());
        alData.setLevel(data.getLevel());
        alData.setHighUserId(data.getHighUserId());

        alData.setApplyLevel(data.getApplyLevel());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(status);
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
        alData.setApplyLevel(data.getApplyLevel());
        alData.setLevel(data.getLevel());
        alData.setHighUserId(data.getHighUserId());

        alData.setTeamName(data.getTeamName());
        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String approveUpgrade(User data, String status) {
        AgencyLog agencyLog = this.getAgencyLog(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setType(EAgencyType.Upgrade.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());

        alData.setLevel(data.getLevel());
        alData.setHighUserId(data.getHighUserId());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(status);
        alData.setRemark(data.getRemark());
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public List<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition) {
        long totalCount = agencyLogDAO.selectTotalCount(condition);
        Page<AgencyLog> page = new Page<AgencyLog>(start, limit, totalCount);
        return agencyLogDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    @Override
    public String toApply(User data, String toUser, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(toUser);

        alData.setType(EAgencyType.Imporder.getCode());
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApplyUser(data.getUserId());
        Date date = new Date();
        alData.setApplyDatetime(date);

        alData.setStatus(status);
        agencyLogDAO.insert(alData);
        return code;
    }

    @Override
    public String refreshHighUser(User data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        AgencyLog alData = new AgencyLog();
        alData.setCode(code);
        alData.setTeamName(data.getTeamName());
        alData.setUserReferee(data.getUserReferee());
        alData.setApplyUser(data.getUserId());

        alData.setType(EAgencyType.Update.getCode());
        Date date = new Date();
        alData.setApplyDatetime(date);
        alData.setStatus(data.getStatus());
        agencyLogDAO.insert(alData);
        return code;
    }

}
