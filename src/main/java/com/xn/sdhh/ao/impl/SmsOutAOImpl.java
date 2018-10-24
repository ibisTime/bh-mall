package com.xn.sdhh.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.sdhh.ao.ISmsOutAO;
import com.xn.sdhh.bo.ISYSUserBO;
import com.xn.sdhh.bo.ISmsOutBO;
import com.xn.sdhh.common.PhoneUtil;
import com.xn.sdhh.domain.SYSUser;
import com.xn.sdhh.enums.ESystemCode;
import com.xn.sdhh.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ISYSUserBO sysUserBO;

    @Override
    public void sendCaptcha(String mobile, String bizType, String companyCode,
            String systemCode) {
        smsOutBO.sendCaptcha(mobile, bizType, systemCode);
    }

    @Override
    public void sendContent(String tokenId, String userId, String content) {
        SYSUser agent = sysUserBO.getSYSUser(userId);
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
