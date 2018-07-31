package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.YxForm;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.enums.EAddressType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class YxFormAOImpl implements IYxFormAO {

    @Autowired
    private IYxFormBO yxFormBO;

    @Autowired
    private IAgentAO agentAO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    // 代理申请 （无推荐人）
    @Override
    @Transactional
    public void applyYxForm(XN627250Req req) {
        PhoneUtil.checkMobile(req.getMobile());

        // check mobile exist
        agentBO.isMobileExist(req.getMobile());

        // 确认申请等级
        AgentLevel aiData = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.NO.getCode().equals(aiData.getIsIntent())) {
            throw new BizException("xn00000", "本等级不可被意向");
        }

        // 确认申请id
        agentBO.getCheckUser(req.getUserId());

        YxForm data = new YxForm();
        // 获取申请信息
        data.setRealName(req.getRealName());
        data.setUserId(req.getUserId());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setStatus(EUserStatus.MIND.getCode()); // 有意愿
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        data.setApplyDatetime(new Date());

        data.setSource(req.getFromInfo());

        // 数据库
        yxFormBO.applyYxForm(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
    }

    // 意向分配
    @Override
    public void allotYxForm(String userId, String toUserId, String approver) {

        YxForm data = new YxForm();
        // set status
        String status = EUserStatus.TO_APPROVE.getCode();

        if (StringUtils.isNotBlank(toUserId)) {
            Agent toUser = agentBO.getAgent(toUserId);
            if (data.getApplyLevel() <= toUser.getLevel()) {
                throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
            }
            status = EUserStatus.ALLOTED.getCode(); // 已分配
        } else {
            status = EUserStatus.ADD_INFO.getCode();
        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setStatus(status);
        yxFormBO.addYxForm(data);

    }

    // 忽略意向
    @Override
    public void ignore(String userId, String aprrover) {
        YxForm data = new YxForm();
        if (!(EUserStatus.MIND.getCode().equals(data.getStatus())
                || EUserStatus.ALLOTED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }
        data.setApprover(aprrover);
        data.setApproveDatetime(new Date());
        data.setStatus(EUserStatus.TO_MIND.getCode());
        yxFormBO.ignore(data);
    }

    // 接受意向
    @Override
    public void acceptYxForm(String userId, String approver, String remark) {
        YxForm data = new YxForm();
        if (!(EUserStatus.MIND.getCode().equals(data.getStatus())
                || EUserStatus.ALLOTED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }

        data.setToUserId(approver);
        data.setApprover(approver);
        data.setApplyDatetime(new Date());
        data.setRemark(remark);

        data.setStatus(EUserStatus.ADD_INFO.getCode()); // 补全授权资料
        // String logCode = yxFormBO.accepYxForm(data);
        // insert new agent allot log
        YxForm imData = new YxForm();
        imData.setUserId(userId);
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        imData.setStatus(EUserStatus.ADD_INFO.getCode());

        yxFormBO.addYxForm(imData);

    }

    // 列表查询意向代理
    @Override
    public List<YxForm> queryYxFormList(YxForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<YxForm> list = yxFormBO.queryYxFormList(condition);
        return list;
    }

    // 详细查询意向代理
    @Override
    public YxForm getYxForm(String code) {
        YxForm data = yxFormBO.getYxForm(code);
        return data;
    }

    // 分页查询意向代理
    @Override
    public Paginable<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        if (EUserStatus.IGNORED.getCode().equals(condition.getStatus())) {
            condition.setApprover(condition.getUserIdForQuery());
        } else {
            condition.setToUserId(condition.getUserIdForQuery()); // 意向归属人
        }

        Paginable<YxForm> page = yxFormBO.getPaginable(start, limit, condition);

        Agent agent = null;
        for (Iterator<YxForm> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            YxForm yxForm = iterator.next();
            agent = agentAO.getAgent(yxForm.getUserId());
            if (!agent.getLastAgentLog().equals(yxForm.getCode())) {
                iterator.remove();
                continue;
            }
            yxForm.setUser(agent);

        }
        return page;
    }

}
