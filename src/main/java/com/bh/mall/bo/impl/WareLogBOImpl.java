package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IWareLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareLogDAO;
import com.bh.mall.domain.Ware;
import com.bh.mall.domain.WareLog;
import com.bh.mall.enums.ESpecsLogType;
import com.bh.mall.exception.BizException;

@Component
public class WareLogBOImpl extends PaginableBOImpl<WareLog>
        implements IWareLogBO {

    @Autowired
    private IWareLogDAO wareLogDAO;

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
    public String saveWareLog(Ware dbData, String type, Integer quantity,
            ESpecsLogType bizType, String bizNote, String refNo) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseLog.getCode());
        WareLog logData = new WareLog();
        logData.setCode(code);
        logData.setType(type);
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

}
