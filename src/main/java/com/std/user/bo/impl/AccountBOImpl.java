package com.std.user.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.user.bo.IAccountBO;
import com.std.user.dto.req.XN802000Req;
import com.std.user.dto.req.XN802001Req;
import com.std.user.dto.req.XN802013Req;
import com.std.user.dto.req.XN802317Req;
import com.std.user.dto.res.XN802000Res;
import com.std.user.dto.res.XN802001Res;
import com.std.user.dto.res.XN802013Res;
import com.std.user.enums.ECurrency;
import com.std.user.http.BizConnecter;
import com.std.user.http.JsonUtils;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    @Override
    public String distributeAccount(String userId, String realName,
            String currency) {
        XN802000Req req = new XN802000Req();
        req.setUserId(userId);
        req.setRealName(realName);
        req.setCurrency(currency);
        XN802000Res res = BizConnecter.getBizData("802000",
            JsonUtils.object2Json(req), XN802000Res.class);
        String accountNumber = null;
        if (res != null) {
            accountNumber = res.getAccountNumber();
        }
        return accountNumber;
    }

    @Override
    public String distributeAccountTwo(String userId, String realName,
            String currency, String userReferee) {
        XN802001Req req = new XN802001Req();
        req.setUserId(userId);
        req.setRealName(realName);
        req.setCurrency(currency);
        req.setUserReferee(userReferee);
        XN802001Res res = BizConnecter.getBizData("802001",
            JsonUtils.object2Json(req), XN802001Res.class);
        String accountNumber = null;
        if (res != null) {
            accountNumber = res.getAccountNumber();
        }
        return accountNumber;
    }

    @Override
    public void refreshRealName(String userId, String realName) {
        // TODO Auto-generated method stub
    }

    /** 
     * @see com.std.user.bo.IAccountBO#getAccountDetail(java.lang.String, java.lang.String)
     */
    @Override
    public XN802013Res getAccountDetail(String userId, String currency) {
        XN802013Req req = new XN802013Req();
        req.setUserId(userId);
        req.setCurrency(ECurrency.XNB.getCode());
        return BizConnecter.getBizData("802013", JsonUtils.object2Json(req),
            XN802013Res.class);
    }

    /** 
     * @see com.std.user.bo.IAccountBO#loginAddJf(java.lang.String, java.lang.String)
     */
    @Override
    public void loginAddJf(String userId) {
        XN802317Req req = new XN802317Req();
        // 从菜狗扣除积分
        req.setFromUserId("U201600000000000001");
        req.setToUserId(userId);
        req.setRemark("首次登录送积分");
        BizConnecter.getBizData("802317", JsonUtils.object2Json(req),
            Object.class);
    }
}
