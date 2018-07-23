package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IUpLevelApplyBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IBuserDAO;
import com.bh.mall.dao.IUpLevelApplyDAO;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.UpLevelApply;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class UpLevelApplyBOImpl extends PaginableBOImpl<UpLevelApply>
        implements IUpLevelApplyBO {

    @Autowired
    private IUpLevelApplyDAO uplevelApplyDAO;

    @Autowired
    private IBuserDAO buserDAO;

    /***************** 保存日志 *******************/
    @Override
    public String addUplevelApply(UpLevelApply data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());

        // save new agencylog to buser
        BUser buser = new BUser();
        buser.setUserId(data.getApplyUser());
        buser.setLastAgentLog(code);
        buserDAO.updateLog(buser);

        // UpLevelApply alData = new UpLevelApply();
        data.setCode(code);
        uplevelApplyDAO.insert(data);
        return code;
    }

    @Override
    public String saveUpLevelApply(BUser data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        UpLevelApply alData = new UpLevelApply();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        uplevelApplyDAO.insert(alData);

        return code;
    }

    /***************** 查询日志 *******************/
    @Override
    public UpLevelApply getUpLevelApply(String code) {
        UpLevelApply data = null;
        if (StringUtils.isNotBlank(code)) {
            UpLevelApply condition = new UpLevelApply();
            condition.setCode(code);
            data = uplevelApplyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理记录不存在");
            }
        }
        return data;
    }

    @Override
    public List<UpLevelApply> queryUpLevelApplyPage(int start, int limit,
            UpLevelApply condition) {
        long totalCount = uplevelApplyDAO.selectTotalCount(condition);
        Page<UpLevelApply> page = new Page<UpLevelApply>(start, limit,
            totalCount);
        return uplevelApplyDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    @Override
    public List<UpLevelApply> queryUpLevelApplyList(UpLevelApply condition) {
        return uplevelApplyDAO.selectList(condition);
    }

    /***************** 升级 *******************
     * 新增 代理升级申请审核表
     * 改变状态 （待审核，已升级，审核未通过）
     * 新增记录
     */
    // 升级申请
    @Override
    public String upgradeLevel(BUser data, String payPdf) {
        UpLevelApply agencyLog = this.getUpLevelApply(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        UpLevelApply alData = new UpLevelApply();
        alData.setCode(code);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setPaymentPdf(payPdf);

        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());
        alData.setApplyLevel(data.getApplyLevel());

        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        uplevelApplyDAO.insert(alData);
        return code;
    }

    // 升级审核通过
    @Override
    public String approveUpgrade(BUser data, String status) {
        UpLevelApply agencyLog = this.getUpLevelApply(data.getLastAgentLog());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        UpLevelApply alData = new UpLevelApply();
        alData.setCode(code);
        alData.setStatus(EUserStatus.UPGRADED.getCode());
        alData.setApplyUser(data.getUserId());
        alData.setApplyDatetime(agencyLog.getApplyDatetime());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(status);
        alData.setRemark(data.getRemark());
        uplevelApplyDAO.insert(alData);
        return code;
    }

    // 取消升级申请
    @Override
    public String approveCanenl(BUser data, String status) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        UpLevelApply alData = new UpLevelApply();

        alData.setCode(code);
        alData.setApplyUser(data.getUserId());
        alData.setStatus(EUserStatus.CANCELED.getCode());

        alData.setApplyLevel(data.getApplyLevel());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(status);
        alData.setRemark(data.getRemark());

        uplevelApplyDAO.insert(alData);
        return code;
    }

}
