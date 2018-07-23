package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IImpowerApplyBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IBuserDAO;
import com.bh.mall.dao.IImpowerApplyDAO;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.ImpowerApply;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class ImpowerApplyBOImpl extends PaginableBOImpl<ImpowerApply>
        implements IImpowerApplyBO {

    @Autowired
    private IImpowerApplyDAO impowerApplyDAO;

    @Autowired
    private IBuserDAO buserDAO;

    /********************** 保存日志 ***************************/

    @Override
    public String addImpowerApply(ImpowerApply data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());

        // save new agencylog to buser
        BUser buser = new BUser();
        buser.setUserId(data.getApplyUser());
        buser.setLastAgentLog(code);
        buserDAO.updateLog(buser);

        // add new impower apply log
        data.setCode(code);
        impowerApplyDAO.insert(data);

        return code;
    }

    @Override
    public String saveImpowerApply(BUser data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        ImpowerApply alData = new ImpowerApply();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        impowerApplyDAO.insert(alData);

        return code;
    }

    /********************** 查询 ***************************/

    @Override
    public ImpowerApply getImpowerApply(String code) {
        ImpowerApply data = null;
        if (StringUtils.isNotBlank(code)) {
            ImpowerApply condition = new ImpowerApply();
            condition.setCode(code);
            data = impowerApplyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    @Override
    public List<ImpowerApply> queryImpowerApplyPage(int start, int limit,
            ImpowerApply condition) {
        long totalCount = impowerApplyDAO.selectTotalCount(condition);
        Page<ImpowerApply> page = new Page<ImpowerApply>(start, limit,
            totalCount);
        return impowerApplyDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    @Override
    public List<ImpowerApply> queryAgencyLogList(ImpowerApply condition) {
        return impowerApplyDAO.selectList(condition);
    }

    /********************** 授权申请 ***************************/

    // 新增授权申请
    @Override
    public String impowerApply(BUser data, String payPdf) {
        ImpowerApply agencyLog = this.getImpowerApply(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        ImpowerApply alData = new ImpowerApply();
        alData.setCode(code);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setPaymentPdf(payPdf);

        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());
        alData.setApplyLevel(data.getApplyLevel());

        // alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        impowerApplyDAO.insert(alData);
        return code;
    }

    // 通过该授权申请
    @Override
    public String approveImpower(ImpowerApply log, BUser user) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        ImpowerApply alData = new ImpowerApply();

        alData.setCode(code);
        alData.setApplyUser(user.getUserId());
        alData.setApplyDatetime(log.getApplyDatetime());
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setApplyLevel(user.getApplyLevel());
        alData.setApprover(user.getApprover());
        alData.setApplyLevel(user.getApplyLevel());
        alData.setApproveDatetime(user.getApproveDatetime());
        alData.setRemark(log.getRemark());
        impowerApplyDAO.insert(alData);
        return code;
    }

    // 代理取消申请
    @Override
    public String cancelImpower(BUser data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        ImpowerApply alData = new ImpowerApply();
        alData.setCode(code);
        alData.setStatus(EUserStatus.CANCELED.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());
        impowerApplyDAO.insert(alData);
        return code;

    }

    @Override
    public String cancelImpower(BUser data, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        ImpowerApply alData = new ImpowerApply();
        alData.setCode(code);
        alData.setStatus(EUserStatus.CANCELED.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());
        impowerApplyDAO.insert(alData);
        return code;

    }

}
