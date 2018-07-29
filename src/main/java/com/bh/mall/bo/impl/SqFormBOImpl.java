package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IBuserDAO;
import com.bh.mall.dao.ISqFormDAO;
import com.bh.mall.domain.SqForm;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class SqFormBOImpl extends PaginableBOImpl<SqForm> implements ISqFormBO {

    @Autowired
    private ISqFormDAO impowerApplyDAO;

    @Autowired
    private IBuserDAO buserDAO;

    /********************** 授权申请 ***************************/

    @Override
    public String toApply(SqForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(data.getToUserId());
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setUserId(data.getUserId());
        Date date = new Date();
        alData.setApplyDatetime(date);

        impowerApplyDAO.insert(alData);
        return code;
    }

    // 新增授权申请
    @Override
    public String impowerApply(SqForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());

        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApplyLevel(data.getApplyLevel());

        // alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        impowerApplyDAO.insert(alData);
        return code;
    }

    // 通过该授权申请
    @Override
    public String approveImpower(SqForm user) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
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
        impowerApplyDAO.insert(alData);
        return code;
    }

    // 代理取消申请
    @Override
    public String cancelImpower(SqForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.CANCELED.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());
        impowerApplyDAO.insert(alData);
        return code;

    }

    @Override
    public String cancelImpower(SqForm data, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.CANCELED.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());
        impowerApplyDAO.insert(alData);
        return code;

    }

    @Override
    public String addInfo(SqForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());

        // save new agencylog to buser
        SqForm buser = new SqForm();
        buser.setUserId(data.getUserId());

        // add new impower apply log
        data.setCode(code);
        impowerApplyDAO.insert(data);

        return code;
    }

    /********************** 保存日志 ***************************/

    @Override
    public String addImpowerApply(SqForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());

        // save new agencylog to buser
        SqForm buser = new SqForm();
        buser.setUserId(data.getUserId());

        // add new impower apply log
        data.setCode(code);
        impowerApplyDAO.insert(data);

        return code;
    }

    @Override
    public String saveImpowerApply(SqForm data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        SqForm alData = new SqForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        impowerApplyDAO.insert(alData);

        return code;
    }

    /********************** 查询 ***************************/

    @Override
    public SqForm getImpowerApply(String code) {
        SqForm data = null;
        if (StringUtils.isNotBlank(code)) {
            SqForm condition = new SqForm();
            condition.setCode(code);
            data = impowerApplyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    @Override
    public List<SqForm> queryImpowerApplyPage(int start, int limit,
            SqForm condition) {
        long totalCount = impowerApplyDAO.selectTotalCount(condition);
        Page<SqForm> page = new Page<SqForm>(start, limit, totalCount);
        return impowerApplyDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    @Override
    public List<SqForm> queryAgencyLogList(SqForm condition) {
        return impowerApplyDAO.selectList(condition);
    }

}
