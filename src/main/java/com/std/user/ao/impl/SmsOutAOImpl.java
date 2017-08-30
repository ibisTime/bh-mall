package com.std.user.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ISmsOutAO;
import com.std.user.bo.ISmsOutBO;
import com.std.user.bo.IUserBO;
import com.std.user.common.PhoneUtil;
import com.std.user.domain.User;
import com.std.user.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void sendCaptcha(String mobile, String bizType, String companyCode,
            String systemCode) {
        smsOutBO.sendCaptcha(mobile, bizType, companyCode, systemCode);
    }

    @Override
    public void sendContent(String tokenId, String userId, String content) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn0000", "该用户编号不存在");
        }
        smsOutBO.sendSmsOut(user.getMobile(), content, "001200",
            user.getCompanyCode(), user.getSystemCode());
    }

    @Override
    public void sendContent(String tokenId, String mobile, String content,
            String companyCode, String systemCode) {
        PhoneUtil.checkMobile(mobile);
        smsOutBO.sendSmsOut(mobile, content, "001201", companyCode, systemCode);
    }
}
