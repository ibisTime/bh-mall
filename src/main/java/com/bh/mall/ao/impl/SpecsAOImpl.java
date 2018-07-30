package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISpecsAO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Specs;

@Service
public class SpecsAOImpl implements ISpecsAO {

    @Autowired
    private ISpecsBO specsBO;

    @Override
    public void dropProductSpecs(String code) {
        specsBO.removeProductSpecs(code);
    }

    @Override
    public Paginable<Specs> queryProductSpecsPage(int start, int limit,
            Specs condition) {
        return specsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Specs> queryProductSpecsList(Specs condition) {
        return specsBO.queryProductSpecsList(condition);
    }

    @Override
    public Specs getProductSpecs(String code) {
        return specsBO.getProductSpecs(code);
    }

    @Override
    public String addProductSpecs(Specs data) {
        return null;
    }

    @Override
    public void editProductSpecs(Specs data) {
    }

}
