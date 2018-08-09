package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IWareLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareLogDAO;
import com.bh.mall.domain.ExchangeOrder;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Ware;
import com.bh.mall.domain.WareLog;
import com.bh.mall.enums.EBizType;
import com.bh.mall.exception.BizException;

@Component
public class WareLogBOImpl extends PaginableBOImpl<WareLog>
        implements IWareLogBO {

    @Autowired
    private IWareLogDAO wareLogDAO;

    @Override
    public String saveWareLog(InOrder data, Ware whData, Integer tranNumber,
            String bizType, String bizNote) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareLog logData = new WareLog();
        logData.setCode(code);
        logData.setRefNo(data.getCode());
        logData.setWareCode(whData.getCode());
        logData.setProductCode(data.getProductCode());
        logData.setProductName(data.getProductName());

        logData.setSpecsCode(data.getSpecsCode());
        logData.setSpecsName(data.getSpecsName());
        logData.setPrice(data.getPrice());
        logData.setTranNumber(tranNumber);
        logData.setBeforeNumber(0);

        logData.setAfterNumber(tranNumber);
        logData.setAmount(data.getAmount());
        logData.setBizType(bizType);
        logData.setBizNote(bizNote);
        return code;
    }

    @Override
    public List<WareLog> queryWareLogList(WareLog condition) {
        return wareLogDAO.selectList(condition);
    }

    @Override
    public WareLog getWareLog(String code) {
        WareLog data = null;
        if (StringUtils.isNotBlank(code)) {
            WareLog condition = new WareLog();
            condition.setCode(code);
            data = wareLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "仓库流水不存在");
            }
        }
        return data;
    }

    @Override
    public String saveWareLog(Ware dbData, Integer quantity, EBizType bizType,
            String bizNote, String refNo) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareLog logData = new WareLog();
        logData.setCode(code);
        logData.setRefNo(refNo);
        logData.setWareCode(dbData.getCode());
        logData.setProductCode(dbData.getProductCode());
        logData.setProductName(dbData.getProductName());

        logData.setSpecsCode(dbData.getSpecsCode());
        logData.setSpecsName(dbData.getSpecsName());
        logData.setPrice(dbData.getPrice());
        logData.setTranNumber(quantity);
        logData.setBeforeNumber(dbData.getQuantity());
        logData.setStatus(dbData.getStatus());

        logData.setApplyUser(dbData.getUserId());
        logData.setApplyDatetime(new Date());
        logData.setRealName(dbData.getRealName());
        logData.setStatus(dbData.getStatus());
        logData.setAfterNumber(dbData.getQuantity() + quantity);
        logData.setAmount(dbData.getAmount());
        logData.setBizType(bizType.getCode());
        logData.setBizNote(bizNote);
        wareLogDAO.insert(logData);
        return code;
    }

    @Override
    public String refreshChangePrice(ExchangeOrder data, Ware dbData,
            Long changePirce, int canChangeQuantity, String status,
            String bizNote) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareLog logData = new WareLog();
        logData.setCode(code);
        logData.setRefNo(data.getCode());
        logData.setWareCode(dbData.getCode());
        logData.setProductCode(data.getProductCode());
        logData.setProductName(data.getProductName());

        logData.setSpecsCode(data.getSpecsCode());
        logData.setSpecsName(data.getSpecsName());
        logData.setPrice(data.getPrice());
        logData.setTranNumber(0);
        logData.setBeforeNumber(dbData.getQuantity());

        logData.setAfterNumber(dbData.getQuantity());
        logData.setChangeNumber(canChangeQuantity);
        logData.setChangePrice(changePirce);
        logData.setApplyUser(data.getApplyUser());
        logData.setRealName(data.getRealName());

        logData.setApplyDatetime(data.getApplyDatetime());
        logData.setApplyNote(data.getApplyNote());
        logData.setApprover(data.getApprover());
        logData.setApproveDatetime(data.getApproveDatetime());

        logData.setApproveNote(data.getApproveNote());
        logData.setAmount(dbData.getAmount());
        logData.setBizNote(bizNote);
        wareLogDAO.insert(logData);
        return code;
    }

    @Override
    public String saveWareLog(InOrder inOrder, String wareCode,
            Integer beforeNumber, Integer changeNumber, String bizType,
            String bizNote, String refNo) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareLog logData = new WareLog();
        logData.setCode(code);
        logData.setRefNo(refNo);
        logData.setWareCode(wareCode);
        logData.setProductCode(inOrder.getProductCode());
        logData.setProductName(inOrder.getProductName());

        logData.setSpecsCode(inOrder.getSpecsCode());
        logData.setSpecsName(inOrder.getSpecsName());
        logData.setPrice(inOrder.getPrice());
        logData.setTranNumber(changeNumber);
        logData.setBeforeNumber(beforeNumber);

        Long amount = AmountUtil.mul(inOrder.getPrice(),
            (beforeNumber + changeNumber));
        logData.setAfterNumber(beforeNumber + changeNumber);
        logData.setAmount(amount);
        logData.setBizType(bizType);
        logData.setBizNote(bizNote);
        return code;
    }

}
