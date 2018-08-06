package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IYxFormDAO;
import com.bh.mall.domain.YxForm;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.enums.EYxFormStatus;

@Component
public class YxFormBOImpl extends PaginableBOImpl<YxForm> implements IYxFormBO {

    @Autowired
    private IYxFormDAO yxFormDAO;

    @Autowired
    private IAgentLogBO agentLogBO;

    // 代理申请
    @Override
    public String applyYxForm(String userId, String realName, String wxId,
            String mobile, String applyLevel, String province, String city,
            String area, String address, String fromInfo) {

        YxForm data = new YxForm();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setWxId(wxId);
        data.setMobile(mobile);

        data.setApplyLevel(StringValidater.toInteger(applyLevel));
        data.setProvince(province);
        data.setCity(city);
        data.setArea(area);
        data.setAddress(address);

        data.setStatus(EUserStatus.MIND.getCode()); // 有意愿
        data.setApplyDatetime(new Date());
        data.setSource(fromInfo);
        yxFormDAO.insert(data);

        // 生成代理轨迹
        return agentLogBO.applyYxForm(data);
    }

    @Override
    public String updateYxForm(YxForm data, String realName, String wxId,
            String mobile, String applyLevel, String province, String city,
            String area, String address, String fromInfo) {

        data.setRealName(realName);
        data.setWxId(wxId);
        data.setMobile(mobile);

        data.setApplyLevel(StringValidater.toInteger(applyLevel));
        data.setProvince(province);
        data.setCity(city);
        data.setArea(area);
        data.setAddress(address);

        data.setStatus(EUserStatus.MIND.getCode()); // 有意愿
        data.setApplyDatetime(new Date());
        data.setSource(fromInfo);
        yxFormDAO.insert(data);

        // 生成代理轨迹
        return agentLogBO.applyYxForm(data);
    }

    // 意向分配
    @Override
    public String allotYxForm(YxForm data, String toUserId, String approver,
            String approveName, String remark) {
        data.setToUserId(toUserId);
        data.setStatus(EYxFormStatus.ALLOTED.getCode());
        data.setApprover(approver);
        data.setApproveName(approveName);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        yxFormDAO.allotYxForm(data);

        // 生成代理轨迹
        return agentLogBO.applyYxForm(data);
    }

    // 忽略意向分配
    @Override
    public String ignoreYxForm(YxForm data, String approver, String approveName,
            String remark) {
        data.setStatus(EYxFormStatus.IGNORED.getCode());
        data.setApprover(approver);
        data.setApproveName(approveName);
        data.setRemark(remark);
        yxFormDAO.insert(data);
        // 生成代理轨迹
        return agentLogBO.applyYxForm(data);
    }

    @Override
    public String acceptYxForm(YxForm data, String approver, String approveName,
            String remark) {
        data.setToUserId(approver);
        data.setStatus(EYxFormStatus.ACCEPT.getCode());
        data.setApprover(approver);
        data.setApproveName(approveName);
        data.setRemark(remark);
        yxFormDAO.acceptYxForm(data);

        // 生成代理轨迹
        return agentLogBO.applyYxForm(data);
    }

    // 详细查询
    @Override
    public YxForm getYxForm(String code) {
        YxForm data = null;
        if (StringUtils.isNotBlank(code)) {
            YxForm condition = new YxForm();
            data = yxFormDAO.select(condition);
        }
        return data;
    }

    // 列表查询
    @Override
    public List<YxForm> queryYxFormList(YxForm condition) {
        return yxFormDAO.selectList(condition);
    }

    // 根据等级查询
    @Override
    public YxForm getYxFormByLevel(Integer level) {
        YxForm condition = new YxForm();
        condition.setLevel(level);
        return yxFormDAO.select(condition);

    }

    // 分页查询
    @Override
    public List<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition) {
        long totalCount = yxFormDAO.selectTotalCount(condition);
        Page<YxForm> page = new Page<YxForm>(start, limit, totalCount);
        return yxFormDAO.selectList(condition, page.getPageNO(),
            page.getPageSize());
    }

}
