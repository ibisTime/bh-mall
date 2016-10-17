package com.std.user.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ISmsOutAO;
import com.std.user.bo.ISmsOutBO;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    /** 
     * @see com.std.user.ao.ISmsOutAO#sendCaptcha(java.lang.String, java.lang.String)
     */
    @Override
    public void sendCaptcha(String mobile, String bizType) {
        smsOutBO.sendCaptcha(mobile, bizType);
    }

}
