package com.std.user.ao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ISmsOutAO;
import com.std.user.bo.ICompanyBO;
import com.std.user.bo.ISmsOutBO;
import com.std.user.bo.IUserBO;
import com.std.user.core.EGeneratePrefix;
import com.std.user.domain.Company;
import com.std.user.domain.User;
import com.std.user.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    ICompanyBO companyBO;

    /** 
     * @see com.std.user.ao.ISmsOutAO#sendCaptcha(java.lang.String, java.lang.String)
     */
    @Override
    public void sendCaptcha(String mobile, String bizType, String kind,
            String systemCode) {
        smsOutBO.sendCaptcha(mobile, bizType, systemCode);
    }

    @Override
    public void sendContent(String tokenId, String ownerId, String content) {
        // 判读公司还是个人用户
        if (StringUtils.isNotBlank(ownerId)) {
            String systemCode = null;
            String mobile = null;
            if (ownerId.contains(EGeneratePrefix.COM.getCode())) {
                Company company = companyBO.getCompany(ownerId);
                mobile = company.getMobile();
                systemCode = company.getSystemCode();
            } else {
                User user = userBO.getUser(ownerId);
                if (user == null) {
                    throw new BizException("xn0000", "该用户编号不存在");
                }
                mobile = user.getMobile();
                systemCode = user.getSystemCode();
            }
            smsOutBO.sendSmsOut(mobile, content, null, systemCode);
        }
    }
}
