package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IWareLogAO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.IWareLogBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.WareLog;
import com.bh.mall.exception.BizException;

@Service
public class WareLogAOImpl implements IWareLogAO {

    @Autowired
    private IWareLogBO wareLogBO;

    @Autowired
    ISpecsBO specsBO;

    // 分页查询
    @Override
    public Paginable<WareLog> queryWareLogPage(int start, int limit,
            WareLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<WareLog> page = wareLogBO.getPaginable(start, limit,
            condition);
        for (WareLog data : page.getList()) {
            Specs specs = specsBO.getSpecs(data.getSpecsCode());
            data.setSpecsName(specs.getName());

        }
        return page;
    }

    // 列表查询
    @Override
    public List<WareLog> queryWareLogList(WareLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<WareLog> list = wareLogBO.queryWareLogList(condition);
        for (WareLog data : list) {
            Specs specs = specsBO.getSpecs(data.getSpecsCode());
            data.setSpecsName(specs.getName());

        }
        return list;
    }

    // 详细查询
    @Override
    public WareLog getWareLog(String code) {
        WareLog data = wareLogBO.getWareLog(code);
        Specs specs = specsBO.getSpecs(data.getSpecsCode());
        data.setSpecsName(specs.getName());
        return data;
    }
}
