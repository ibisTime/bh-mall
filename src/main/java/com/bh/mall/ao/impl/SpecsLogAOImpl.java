package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISpecsLogAO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SpecsLog;
import com.bh.mall.exception.BizException;

@Service
public class SpecsLogAOImpl implements ISpecsLogAO {

    @Autowired
    private ISpecsLogBO specsLogBO;

    @Override
    public void editSpecsLog(SpecsLog data) {
        specsLogBO.refreshSpecsLog(data);
    }

    @Override
    public void dropSpecstLog(String code) {
        specsLogBO.removeSpecsLog(code);
    }

    @Override
    public Paginable<SpecsLog> querySpecsLogPage(int start, int limit,
            SpecsLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        return specsLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SpecsLog> querySpecsLogList(SpecsLog condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return specsLogBO.querySpecsLogList(condition);
    }

    @Override
    public SpecsLog getSpecsLog(String code) {
        return specsLogBO.getSpecsLog(code);
    }
}
