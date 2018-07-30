package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.ISjFormDAO;
import com.bh.mall.domain.SjForm;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class SjFormBOImpl extends PaginableBOImpl<SjForm> implements ISjFormBO {

    @Autowired
    private ISjFormDAO sjFormDAO;

    /***************** 升级 *******************
     * 新增 代理升级申请审核表
     * 改变状态 （待审核，已升级，审核未通过）
     * 新增记录
     */
    // 升级申请
    @Override
    public String applySjForm(SjForm data) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setPaymentPdf(data.getPaymentPdf());

        alData.setApplyUser(data.getApplyUser());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApplyLevel(data.getApplyLevel());

        alData.setStatus(data.getStatus());
        alData.setRemark(data.getRemark());
        sjFormDAO.insert(alData);
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
        alData.setApplyUser(data.getApplyUser());
        alData.setApplyDatetime(data.getApplyDatetime());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());

        alData.setRemark(data.getRemark());
        sjFormDAO.insert(alData);
        return code;
    }

    // 取消升级申请
    @Override
    public String approveCanenlSjForm(SjForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();

        alData.setCode(code);
        alData.setApplyUser(data.getApplyUser());
        alData.setStatus(EUserStatus.CANCELED.getCode());

        alData.setApplyLevel(data.getApplyLevel());

        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setRemark(data.getRemark());

        sjFormDAO.insert(alData);
        return code;
    }

    /***************** 保存日志 *******************/
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

    @Override
    public String saveSjForm(SjForm data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());
        SjForm alData = new SjForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyUser(data.getApplyUser());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApprover(data.getApprover());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        sjFormDAO.insert(alData);

        return code;
    }

    /***************** 查询日志 *******************/
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

    @Override
    public List<SjForm> querySjFormPage(int start, int limit,
            SjForm condition) {
        long totalCount = sjFormDAO.selectTotalCount(condition);
        Page<SjForm> page = new Page<SjForm>(start, limit, totalCount);
        return sjFormDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    @Override
    public List<SjForm> querySjFormList(SjForm condition) {
        return sjFormDAO.selectList(condition);
    }

}
