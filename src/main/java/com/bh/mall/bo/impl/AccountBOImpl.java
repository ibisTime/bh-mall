package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAccountBO;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN001303Req;
import com.bh.mall.dto.req.XN002000Req;
import com.bh.mall.dto.req.XN002001Req;
import com.bh.mall.dto.req.XN002100Req;
import com.bh.mall.dto.res.XN001303Res;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.BizConnecter;
import com.bh.mall.http.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    @Override
    public void distributeAccountList(String userId, String realName,
            String type, List<String> currencyList, String companyCode,
            String systemCode) {
        if (StringUtils.isNotBlank(userId)
                && CollectionUtils.isNotEmpty(currencyList)) {
            XN002000Req req = new XN002000Req();
            req.setUserId(userId);
            req.setRealName(realName);
            req.setType(type);
            req.setCurrencyList(currencyList);
            req.setCompanyCode(companyCode);
            req.setSystemCode(systemCode);
            BizConnecter.getBizData("002000", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public void refreshRealName(String userId, String realName,
            String systemCode) {
        if (!ESystemCode.SERVICE.getCode().equals(systemCode)) {
            XN002001Req req = new XN002001Req();
            req.setUserId(userId);
            req.setRealName(realName);
            BizConnecter.getBizData("002001", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doTransferAmountRemote(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote) {
        if (amount != null && amount.longValue() != 0) {
            XN002100Req req = new XN002100Req();
            req.setFromUserId(fromUserId);
            req.setToUserId(toUserId);
            req.setFromCurrency(currency.getCode());
            req.setToCurrency(currency.getCode());
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType.getCode());
            req.setFromBizNote(fromBizNote);
            req.setToBizNote(toBizNote);
            req.setRefNo("refNo");
            BizConnecter.getBizData("002100", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public Long getAccountByUserId(String userId, ECurrency type) {
        Long amount = 0L;
        XN001303Req req = new XN001303Req();
        req.setUserId(userId);
        req.setCurrency(type.getCode());
        String jsonStr = BizConnecter.getBizData("002050",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN001303Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN001303Res>>() {
            }.getType());
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "账户不存在");
        }
        XN001303Res res = list.get(0);
        amount = StringValidater.toLong(res.getAddAmount());
        return amount;
    }
}
