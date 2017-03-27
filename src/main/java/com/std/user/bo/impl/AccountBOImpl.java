package com.std.user.bo.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.user.bo.IAccountBO;
import com.std.user.dto.req.XN002100Req;
import com.std.user.dto.req.XN802450Req;
import com.std.user.dto.req.XN802451Req;
import com.std.user.enums.EBizType;
import com.std.user.enums.ECurrency;
import com.std.user.http.BizConnecter;
import com.std.user.http.JsonUtils;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

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
    public void refreshRealName(String userId, String realName,
            String systemCode) {
        XN802451Req req = new XN802451Req();
        req.setUserId(userId);
        req.setRealName(realName);
        req.setSystemCode(systemCode);
        BizConnecter.getBizData("802451", JsonUtils.object2Json(req),
            Object.class);
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doTransferAmountRemote(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote) {
        if (amount != null && amount != 0) {
            XN002100Req req = new XN002100Req();
            req.setFromUserId(fromUserId);
            req.setToUserId(toUserId);
            req.setCurrency(currency.getCode());
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType.getCode());
            req.setFromBizNote(fromBizNote);
            req.setToBizNote(toBizNote);
            BizConnecter.getBizData("002100", JsonUtils.object2Json(req),
                Object.class);
        }
    }
}
