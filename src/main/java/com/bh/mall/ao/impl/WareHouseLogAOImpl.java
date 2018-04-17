package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IWareHouseLogAO;
import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareHouseLog;
import com.bh.mall.exception.BizException;

@Service
public class WareHouseLogAOImpl implements IWareHouseLogAO {

    @Autowired
    private IWareHouseLogBO wareHouseLogBO;

    @Override
    public Paginable<WareHouseLog> queryWareHouseLogPage(int start, int limit,
            WareHouseLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return wareHouseLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WareHouseLog> queryWareHouseLogList(WareHouseLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return wareHouseLogBO.queryWareHouseLogList(condition);
    }

    @Override
    public WareHouseLog getWareHouseLog(String code) {
        return wareHouseLogBO.getWareHouseLog(code);
    }
}
