package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISpecsAO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Specs;
import com.bh.mall.enums.ESpecsLogType;

@Service
public class SpecsAOImpl implements ISpecsAO {

    @Autowired
    private ISpecsBO specsBO;

    @Override
    public void editRepertory(String code, String type, Integer number,
            String updater) {
        Specs specs = specsBO.getSpecs(code);
        if (ESpecsLogType.Output.getCode().equals(type)) {
            number = -number;
        }
        specsBO.refreshRepertory(specs, type, number, updater);
    }

    @Override
    public Paginable<Specs> querySpecsPage(int start, int limit,
            Specs condition) {
        return specsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Specs> querySpecsList(Specs condition) {
        return specsBO.querySpecsList(condition);
    }

    @Override
    public Specs getSpecs(String code) {
        return specsBO.getSpecs(code);
    }

}
