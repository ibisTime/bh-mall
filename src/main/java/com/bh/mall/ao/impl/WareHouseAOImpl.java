package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IWareHouseAO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareHouse;

@Service
public class WareHouseAOImpl implements IWareHouseAO {

    @Autowired
    private IWareHouseBO wareHouseBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addWareHouse(WareHouse data) {
        return null;
    }

    @Override
    public void editWareHouse(WareHouse data) {
    }

    @Override
    public void dropWareHouse(String code) {
    }

    @Override
    public Paginable<WareHouse> queryWareHousePage(int start, int limit,
            WareHouse condition) {
        return wareHouseBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WareHouse> queryWareHouseList(WareHouse condition) {
        return wareHouseBO.queryWareHouseList(condition);
    }

    @Override
    public WareHouse getWareHouse(String code) {
        return wareHouseBO.getWareHouse(code);
    }

    @Override
    public Paginable<WareHouse> queryWareHouseFrontPage(int start, int limit,
            WareHouse condition) {
        userBO.getCheckUser(condition.getUserId());
        return wareHouseBO.getPaginable(start, limit, condition);
    }
}
