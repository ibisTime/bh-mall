package com.bh.mall.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISmsOutAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.domain.Agent;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IAgentBO agentBO;

    @Override
    public void sendCaptcha(String mobile, String bizType, String companyCode,
            String systemCode) {
        smsOutBO.sendCaptcha(mobile, bizType, systemCode);
    }

    @Override
    public void sendContent(String tokenId, String userId, String content) {
        Agent agent = agentBO.getAgent(userId);
        if (agent == null) {
            throw new BizException("xn0000", "该用户编号不存在");
        }
        smsOutBO.sendSmsOut(agent.getMobile(), content, "001200",
            ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
    }

    @Override
    public void sendContent(String tokenId, String mobile, String content,
            String companyCode, String systemCode) {
        PhoneUtil.checkMobile(mobile);
        smsOutBO.sendSmsOut(mobile, content, "001201", companyCode, systemCode);
    }
}
