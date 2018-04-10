package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareHouseLogDAO;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.domain.WareHouseLog;
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
        logData.setBeforeNumber(whData.getQuantity());

        logData.setAfterNumber(whData.getQuantity() + tranNumber);
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
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
