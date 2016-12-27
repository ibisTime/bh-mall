package com.std.user.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.user.bo.IAccountBO;
import com.std.user.dto.req.XN802450Req;
import com.std.user.http.BizConnecter;
import com.std.user.http.JsonUtils;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    @Override
    public void distributeAccount(String userId, String realName, String type,
            String currency, String systemCode) {
        XN802450Req req = new XN802450Req();
        req.setUserId(userId);
        req.setRealName(realName);
        req.setType(type);
        List<String> currencyList = new ArrayList<String>();
        currencyList.add(currency);
        req.setCurrencyList(currencyList);
        req.setSystemCode(systemCode);
        BizConnecter.getBizData("802450", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public void distributeAccountList(String userId, String realName,
            String type, List<String> currencyList, String systemCode) {
        XN802450Req req = new XN802450Req();
        req.setUserId(userId);
        req.setRealName(realName);
        req.setType(type);
        req.setCurrencyList(currencyList);
        req.setSystemCode(systemCode);
        BizConnecter.getBizData("802450", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public void refreshRealName(String userId, String realName) {
        // TODO Auto-generated method stub
    }
}
