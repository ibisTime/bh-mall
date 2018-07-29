package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.dao.IYxFormDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.YxForm;
import com.bh.mall.enums.EAgencyType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class YxFormBOImpl extends PaginableBOImpl<YxForm> implements IYxFormBO {

    @Autowired
    private IYxFormDAO agentAllotDAO;

    @Autowired
    private IAgentDAO agentDAO;

    /************************ 意向分配 ***********************/
    @Override
    public String applyIntent(YxForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(data.getToUserId());
        alData.setStatus(EUserStatus.TO_APPROVE.getCode());
        alData.setUserId(data.getUserId());
        Date date = new Date();
        alData.setApplyDatetime(date);

        agentAllotDAO.insert(alData);
        return code;
    }

    /************ 忽略意向分配 *************/

    @Override
    public String ignore(YxForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.IGNORED.getCode()); // 已忽略
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setToUserId(data.getApprover());
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApproveDatetime(data.getApproveDatetime());
        // alData.setStatus(data.getStatus());
        agentAllotDAO.insert(alData);
        return code;

    }

    /************ 接受意向分配 *************/
    @Override
    public String acceptIntention(YxForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setStatus(EUserStatus.TO_APPROVE.getCode()); // 等待授权审核
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setToUserId(data.getApprover());
        alData.setApplyLevel(data.getApplyLevel());
        // alData.setStatus(data.getStatus());
        alData.setApproveDatetime(data.getApproveDatetime());
        agentAllotDAO.insert(alData);
        return code;
    }

    /********************* 保存 ************************/

    @Override
    public String addAgentAllot(YxForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());

        // save new agencylog to buser
        YxForm buser = new YxForm();
        Agent user = new Agent();
        buser.setUserId(data.getUserId());
        agentDAO.updateLog(user);

        // add new agent allot log
        data.setCode(code);
        agentAllotDAO.insert(data);
        return code;
    }

    @Override
    public String saveAgentAllot(YxForm data, String toUserId) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setToUserId(toUserId);
        alData.setStatus(EAgencyType.Allot.getCode());
        alData.setUserId(data.getUserId());
        alData.setApplyDatetime(data.getApplyDatetime());
        alData.setApproveDatetime(data.getApproveDatetime());
        alData.setStatus(data.getStatus());
        agentAllotDAO.insert(alData);
        return code;
    }

    /********************* 查询 ***********************/

    @Override
    public YxForm getAgentAllot(String code) {
        YxForm data = null;
        if (StringUtils.isNotBlank(code)) {
            YxForm condition = new YxForm();
            condition.setCode(code);
            data = agentAllotDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理授权编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<YxForm> queryAgentAllotList(YxForm condition) {
        return agentAllotDAO.selectList(condition);
    }

    @Override
    public YxForm getAgentAllotByLevel(Integer level) {
        YxForm condition = new YxForm();
        // condition.setLevel(level);
        return agentAllotDAO.select(condition);

    }

    @Override
    public List<YxForm> queryAgentAllotPage(int start, int limit,
            YxForm condition) {
        long totalCount = agentAllotDAO.selectTotalCount(condition);
        Page<YxForm> page = new Page<YxForm>(start, limit, totalCount);
        return agentAllotDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

    /*********************** 向下分配 *************************/
    @Override
    public String refreshHighUser(YxForm data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgencyLog.getCode());
        YxForm alData = new YxForm();
        alData.setCode(code);
        alData.setUserId(data.getUserId());
        alData.setStatus(EAgencyType.Update.getCode());
        Date date = new Date();
        alData.setApplyDatetime(date);
        agentAllotDAO.insert(alData);
        return code;
    }

}
