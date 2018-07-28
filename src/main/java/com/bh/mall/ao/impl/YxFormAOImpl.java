package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.YxForm;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.enums.EAddressType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class YxFormAOImpl implements IYxFormAO {

    @Autowired
    private IYxFormBO agentAllotBO;

    @Autowired
    private IBuserAO buserAO;

    @Autowired
    private IBuserBO buserBO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    /*************** 代理申请 **********************/
    // 代理申请 （无推荐人）
    @Override
    @Transactional
    public void applyIntent(XN627250Req req) {
        PhoneUtil.checkMobile(req.getMobile());

        // check mobile exist
        buserBO.isMobileExist(req.getMobile());

        // 确认申请等级
        AgentLevel aiData = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.NO.getCode().equals(aiData.getIsIntent())) {
            throw new BizException("xn00000", "本等级不可被意向");
        }

        // 确认申请id
        buserBO.getCheckUser(req.getUserId());

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
        agentAllotBO.applyIntent(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
    }

    /*************** 意向分配 **********************/

    @Override
    public void allotAgency(String userId, String toUserId, String approver) {

        YxForm data = new YxForm();
        // set status
        String status = EUserStatus.TO_APPROVE.getCode();

        if (StringUtils.isNotBlank(toUserId)) {
            BUser toUser = buserBO.getUser(toUserId);
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
        agentAllotBO.addAgentAllot(data);

    }

    /*************** 忽略意向分配 **********************/
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
        agentAllotBO.ignore(data);
    }

    /***************  接受意向分配 **********************/

    @Override
    public void acceptIntention(String userId, String approver, String remark) {
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
        String logCode = agentAllotBO.acceptIntention(data);
        // insert new agent allot log
        YxForm imData = new YxForm();
        imData.setUserId(userId);
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        imData.setStatus(EUserStatus.ADD_INFO.getCode());

        agentAllotBO.addAgentAllot(imData);

    }

    /*********************** 查询 *************************/

    @Override
    public List<YxForm> queryAgentAllotList(YxForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<YxForm> list = agentAllotBO.queryAgentAllotList(condition);
        return list;
    }

    /************************************************/

    @Override
    public YxForm getAgentAllot(String code) {
        YxForm data = agentAllotBO.getAgentAllot(code);
        return data;
    }

    /***********************************************/

    @Override
    public Paginable<YxForm> queryIntentionAgentFrontPage(int start, int limit,
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

        Paginable<YxForm> page = agentAllotBO.getPaginable(start, limit,
            condition);

        BUser buser = null;
        for (Iterator<YxForm> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            YxForm agencyLog = iterator.next();
            buser = buserAO.doGetUser(agencyLog.getUserId());
            if (!buser.getLastAgentLog().equals(agencyLog.getCode())) {
                iterator.remove();
                continue;
            }
            agencyLog.setUser(buser);

            // 审核人

        }
        return page;
    }

    /*********************** 新增日志 *************************/
    @Override
    public String addAgentAllot(YxForm data) {
        // insert new data
        agentAllotBO.addAgentAllot(data);
        return null;
    }

    /*********************** 查询是否需要补全金额 *************************/
    @Override
    public Paginable<YxForm> queryAgentAllotPage(int start, int limit,
            YxForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<YxForm> page = agentAllotBO.getPaginable(start, limit,
            condition);
        List<YxForm> list = page.getList();

        for (YxForm agentAllot : list) {

            BUser buser = buserAO.doGetUser(agentAllot.getUserId());
            agentAllot.setUser(buser);
            // 补全授权金额
            if (null != buser.getApplyLevel()) {
                // 代理等级表
                AgentLevel agent = agentLevelBO
                    .getAgentByLevel(buser.getApplyLevel());
                agentAllot.setImpowerAmount(agent.getAmount());
            }
            // 审核人

        }
        page.setList(list);
        return page;
    }
}
