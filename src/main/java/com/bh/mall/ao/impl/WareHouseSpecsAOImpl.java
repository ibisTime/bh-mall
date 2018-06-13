package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IWareHouseSpecsAO;
import com.bh.mall.bo.IWareHouseSpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareHouseSpecs;

@Service
public class WareHouseSpecsAOImpl implements IWareHouseSpecsAO {

    @Autowired
    private IWareHouseSpecsBO wareHouseSpecsBO;

    @Override
    public void editWareHouseSpecs(WareHouseSpecs data) {
    }

    @Override
    public void dropWareHouseSpecs(String code) {
    }

    @Override
    public Paginable<WareHouseSpecs> queryWareHouseSpecsPage(int start,
            int limit, WareHouseSpecs condition) {
        return wareHouseSpecsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WareHouseSpecs> queryWareHouseSpecsList(
            WareHouseSpecs condition) {
        return wareHouseSpecsBO.queryWareHouseSpecsList(condition);
    }

    @Override
    public WareHouseSpecs getWareHouseSpecs(String code) {
        return wareHouseSpecsBO.getWareHouseSpecs(code);
    }

    @Override
    public String addWareHouseSpecs(WareHouseSpecs data) {
        // TODO Auto-generated method stub
        return null;
    }
}
