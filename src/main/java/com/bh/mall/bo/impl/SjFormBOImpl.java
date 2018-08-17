package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.ISjFormDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SjForm;
import com.bh.mall.enums.ESjFormStatus;

@Component
public class SjFormBOImpl extends PaginableBOImpl<SjForm> implements ISjFormBO {

    @Autowired
    private ISjFormDAO sjFormDAO;

    @Autowired
    private IAgentLogBO agentLogBO;

    // 升级申请
    @Override
    public String applySjForm(Agent data, String toUserId, String newLevel,
            String idKind, String idNo, String idHand, String payPdf,
            String payAmount, String status) {

        SjForm sjForm = new SjForm();
        sjForm.setUserId(data.getUserId());
        sjForm.setToUserId(toUserId);
        sjForm.setRealName(data.getRealName());
        sjForm.setTeamName(data.getTeamName());
        sjForm.setLevel(data.getLevel());

        sjForm.setApplyLevel(StringValidater.toInteger(newLevel));
        sjForm.setIdKind(idKind);
        sjForm.setIdNo(idNo);
        sjForm.setIdHand(idHand);

        sjForm.setPayPdf(payPdf);
        sjForm.setPayAmount(StringValidater.toLong(payAmount));
        Date date = new Date();
        sjForm.setApplyDatetime(date);
        sjFormDAO.insert(sjForm);
        return agentLogBO.applySjForm(sjForm, data);
    }

    @Override
    public String refreshSjForm(SjForm sjForm, Agent data, String toUserId,
            String newLevel, String idKind, String idNo, String idHand,
            String payPdf, String payAmount, String status) {

        sjForm.setToUserId(toUserId);
        sjForm.setRealName(data.getRealName());
        sjForm.setTeamName(data.getTeamName());
        sjForm.setLevel(data.getLevel());

        sjForm.setApplyLevel(StringValidater.toInteger(newLevel));
        sjForm.setIdKind(idKind);
        sjForm.setIdNo(idNo);
        sjForm.setIdHand(idHand);

        sjForm.setPayPdf(payPdf);
        sjForm.setPayAmount(StringValidater.toLong(payAmount));
        Date date = new Date();
        sjForm.setApplyDatetime(date);
        sjFormDAO.update(sjForm);

        return agentLogBO.applySjForm(sjForm, data);
    }

    @Override
    public String approveSjForm(SjForm sjForm, Agent agent, String approver,
            String approveName, String remark, String status) {
        sjForm.setApprover(approver);
        sjForm.setApproveName(approveName);
        Date date = new Date();
        sjForm.setApproveDatetime(date);
        sjForm.setRemark(remark);
        sjForm.setStatus(status);

        sjFormDAO.approveSjForm(sjForm);
        return agentLogBO.applySjForm(sjForm, agent);
    }

    // 详细查询
    @Override
    public SjForm getSjForm(String userId) {
        SjForm data = null;
        if (StringUtils.isNotBlank(userId)) {
            SjForm condition = new SjForm();
            condition.setUserId(userId);
            data = sjFormDAO.select(condition);
        }
        return data;
    }

    // 列表查询
    @Override
    public List<SjForm> querySjFormList(SjForm condition) {
        return sjFormDAO.selectList(condition);
    }

    @Override
    public boolean checkIsSj(String userId) {
        SjForm condition = new SjForm();
        condition.setUserId(userId);
        condition.setStatus(ESjFormStatus.THROUGH_YES.getCode());
        SjForm data = sjFormDAO.select(condition);
        if (null == data) {
            return false;
        }

        return true;
    }

}
