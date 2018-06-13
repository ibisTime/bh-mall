package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareHouseLogDAO;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.domain.WareHouseLog;
import com.bh.mall.domain.WareHouseSpecs;
import com.bh.mall.enums.EBizType;
import com.bh.mall.exception.BizException;

@Component
public class WareHouseLogBOImpl extends PaginableBOImpl<WareHouseLog>
        implements IWareHouseLogBO {

    @Autowired
    private IWareHouseLogDAO wareHouseLogDAO;

    @Override
    public String saveWareHouseLog(Order data, WareHouse whData,
            Integer tranNumber, String bizType, String bizNote) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareHouseLog logData = new WareHouseLog();
        logData.setCode(code);
        logData.setRefNo(data.getCode());
        logData.setWareHouseCode(whData.getCode());
        logData.setProductCode(data.getProductCode());
        logData.setProductName(data.getProductName());

        logData.setProductSpecsCode(data.getProductSpecsCode());
        logData.setProductSpecsName(data.getProductSpecsName());
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
    public int removeWareHouseLog(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            WareHouseLog data = new WareHouseLog();
            data.setCode(code);
            count = wareHouseLogDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshWareHouseLog(WareHouseLog data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
        }
        return count;
    }

    @Override
    public List<WareHouseLog> queryWareHouseLogList(WareHouseLog condition) {
        return wareHouseLogDAO.selectList(condition);
    }

    @Override
    public WareHouseLog getWareHouseLog(String code) {
        WareHouseLog data = null;
        if (StringUtils.isNotBlank(code)) {
            WareHouseLog condition = new WareHouseLog();
            condition.setCode(code);
            data = wareHouseLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "仓库流水不存在");
            }
        }
        return data;
    }

    @Override
    public String saveWareHouseLog(WareHouse dbData, Integer quantity,
            EBizType bizType, String bizNote, String refNo) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareHouseLog logData = new WareHouseLog();
        logData.setCode(code);
        logData.setRefNo(refNo);
        logData.setWareHouseCode(dbData.getCode());
        logData.setProductCode(dbData.getProductCode());
        logData.setProductName(dbData.getProductName());

        logData.setProductSpecsCode(dbData.getProductSpecsCode());
        logData.setProductSpecsName(dbData.getProductSpecsName());
        logData.setPrice(dbData.getPrice());
        logData.setTranNumber(quantity);
        logData.setBeforeNumber(dbData.getQuantity());
        logData.setStatus(dbData.getStatus());

        logData.setApplyUser(dbData.getUserId());
        logData.setRealName(dbData.getRealName());
        logData.setStatus(dbData.getStatus());
        logData.setAfterNumber(dbData.getQuantity() + quantity);
        logData.setAmount(dbData.getAmount());
        logData.setBizType(bizType.getCode());
        logData.setBizNote(bizNote);
        wareHouseLogDAO.insert(logData);
        return code;
    }

    @Override
    public String refreshChangePrice(ChangeProduct data, WareHouse dbData,
            Long changePirce, int canChangeQuantity, String status,
            String bizNote) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareHouseLog logData = new WareHouseLog();
        logData.setCode(code);
        logData.setRefNo(data.getCode());
        logData.setWareHouseCode(dbData.getCode());
        logData.setProductCode(data.getProductCode());
        logData.setProductName(data.getProductName());

        logData.setProductSpecsCode(data.getProductSpecsCode());
        logData.setProductSpecsName(data.getProductSpecsName());
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
        wareHouseLogDAO.insert(logData);
        return code;
    }

    @Override
    public String saveWareHouseLog(WareHouse wareHouse, WareHouseSpecs data,
            EBizType bizType, String bizNote, String refNo) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareHouseLog logData = new WareHouseLog();
        logData.setCode(code);
        logData.setRefNo(data.getCode());
        logData.setWareHouseCode(wareHouse.getCode());
        logData.setProductCode(wareHouse.getProductCode());

        logData.setProductSpecsCode(data.getProductSpecsCode());
        logData.setPrice(data.getPrice());
        logData.setTranNumber(data.getQuantity());
        logData.setBeforeNumber(0);
        logData.setAfterNumber(data.getQuantity());
        logData.setApplyUser(wareHouse.getUserId());

        logData.setRealName(wareHouse.getRealName());
        Date date = new Date();
        logData.setApplyDatetime(date);
        logData.setAmount(data.getAmount());
        logData.setBizNote(bizNote);
        wareHouseLogDAO.insert(logData);
        return code;
    }

    @Override
    public String saveWareHouseLog(Order order, String wareHouseCode,
            Integer beforeNumber, Integer changeNumber, String bizType,
            String bizNote, String refNo) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareHouseLog logData = new WareHouseLog();
        logData.setCode(code);
        logData.setRefNo(refNo);
        logData.setWareHouseCode(wareHouseCode);
        logData.setProductCode(order.getProductCode());
        logData.setProductName(order.getProductName());

        logData.setProductSpecsCode(order.getProductSpecsCode());
        logData.setProductSpecsName(order.getProductSpecsName());
        logData.setPrice(order.getPrice());
        logData.setTranNumber(changeNumber);
        logData.setBeforeNumber(beforeNumber);

        Long amount = AmountUtil.mul(order.getPrice(),
            (beforeNumber + changeNumber));
        logData.setAfterNumber(beforeNumber + changeNumber);
        logData.setAmount(amount);
        logData.setBizType(bizType);
        logData.setBizNote(bizNote);
        return code;
    }

}
