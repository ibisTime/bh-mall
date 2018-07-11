package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgencyLogAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.enums.EAddressType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class AgencyLogAOImpl implements IAgencyLogAO {

    @Autowired
    IAgencyLogBO agencyLogBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Autowired
    IAddressBO addressBO;

    @Override
    public String addAgencyLog(AgencyLog data) {
        return null;
    }

    @Override
    public Paginable<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<AgencyLog> page = agencyLogBO.getPaginable(start, limit,
            condition);
        List<AgencyLog> list = page.getList();

        for (AgencyLog agencyLog : list) {
            User userReferee = null;
            User user = userBO.getUser(agencyLog.getApplyUser());
            agencyLog.setUser(user);
            if (StringUtils.isNotBlank(agencyLog.getUserReferee())) {
                userReferee = userBO.getUserName(agencyLog.getUserReferee());
                if (userReferee != null) {
                    agencyLog.setRefereeName(userReferee.getRealName());
                }
            }
            // 补全授权金额
            if (null != user.getApplyLevel()) {
                Agent agent = agentBO.getAgentByLevel(user.getApplyLevel());
                agencyLog.setImpowerAmount(agent.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprovName(agencyLog.getApprover());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    User aprrvoeName = userBO.getUser(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = userBO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setApprovName(userReferee.getRealName());
                        }
                    }
                }

            }
        }
        page.setList(list);
        return page;

    }

    @Override
    public List<AgencyLog> queryAgencyLogList(AgencyLog condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<AgencyLog> list = agencyLogBO.queryAgencyLogList(condition);
        for (AgencyLog agencyLog : list) {
            User userReferee = null;
            User user = userBO.getUser(agencyLog.getApplyUser());
            agencyLog.setUser(user);
            if (StringUtils.isNotBlank(agencyLog.getUserReferee())) {
                userReferee = userBO.getUser(agencyLog.getUserReferee());
                if (userReferee != null) {
                    agencyLog.setRefereeName(userReferee.getRealName());
                }
                // 审核人
                if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                    agencyLog.setApprovName(agencyLog.getApprover());
                } else {
                    if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                        User aprrvoeName = userBO
                            .getUser(agencyLog.getApprover());
                        if (null != aprrvoeName) {
                            userReferee = userBO
                                .getUserName(aprrvoeName.getUserId());
                            if (userReferee != null) {
                                agencyLog
                                    .setApprovName(userReferee.getRealName());
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public AgencyLog getAgencyLog(String code) {
        AgencyLog data = agencyLogBO.getAgencyLog(code);
        User user = userBO.getUser(data.getApplyUser());
        data.setUser(user);
        User userReferee = null;
        data.setUser(user);
        if (StringUtils.isNotBlank(data.getUserReferee())) {
            userReferee = userBO.getUser(data.getUserReferee());
            if (userReferee != null) {
                data.setRefereeName(userReferee.getRealName());
            }
        }
        // 审核人
        if (EUser.ADMIN.getCode().equals(data.getApprover())) {
            data.setApprovName(data.getApprover());
        } else {
            if (StringUtils.isNotBlank(data.getApprover())) {
                User aprrvoeName = userBO.getUser(data.getApprover());
                if (null != aprrvoeName) {
                    userReferee = userBO.getUserName(aprrvoeName.getUserId());
                    if (userReferee != null) {
                        data.setApprovName(userReferee.getRealName());
                    }
                }
            }

        }
        return data;
    }

    @Override
    public Paginable<AgencyLog> queryIntentionAgentFrontPage(int start,
            int limit, AgencyLog condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        // 如果是已忽略或已接受状态，根据审核人去查
        if (EUserStatus.IGNORED.getCode().equals(condition.getStatus())
                || EUserStatus.ALLOTED.getCode()
                    .equals(condition.getStatus())) {
            condition.setApprover(condition.getUserIdForQuery());
        } else {
            condition.setToUserId(condition.getUserIdForQuery());
        }

        Paginable<AgencyLog> page = agencyLogBO.getPaginable(start, limit,
            condition);
        User userReferee = null;
        User user = null;
        for (Iterator<AgencyLog> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            AgencyLog agencyLog = iterator.next();
            user = userBO.getUser(agencyLog.getApplyUser());
            if (!user.getLastAgentLog().equals(agencyLog.getCode())) {
                iterator.remove();
                continue;
            }
            agencyLog.setUser(user);
            if (StringUtils.isNotBlank(user.getUserReferee())) {
                userReferee = userBO.getUser(user.getUserReferee());
                agencyLog.setRefereeName(userReferee.getRealName());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprovName(EUser.ADMIN.getCode());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    User aprrvoeName = userBO.getUser(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = userBO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setApprovName(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return page;
    }

    @Override
    public void applyIntent(XN627250Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        userBO.isMobileExist(req.getMobile(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());

        AgentImpower aiData = agentImpowerBO.getAgentImpowerByLevel(
            StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.NO.getCode().equals(aiData.getIsIntent())) {
            throw new BizException("xn00000", "本等级不可被意向");
        }

        User data = userBO.getCheckUser(req.getUserId());

        data.setRealName(req.getRealName());
        data.setWxId(req.getWxId());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        data.setApplyDatetime(new Date());

        userBO.applyIntent(data);
        addressBO.saveAddress(data.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
    }

}
