package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IWareHouseAO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.User;
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
        Paginable<WareHouse> page = wareHouseBO.getPaginable(start, limit,
            condition);
        List<WareHouse> list = page.getList();
        for (WareHouse wareHouse : list) {
            User user = userBO.getUser(wareHouse.getUserId());
            wareHouse.setUser(user);
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<WareHouse> queryWareHouseList(WareHouse condition) {
        List<WareHouse> list = wareHouseBO.queryWareHouseList(condition);
        for (WareHouse wareHouse : list) {
            User user = userBO.getUser(wareHouse.getUserId());
            wareHouse.setUser(user);
        }
        return list;
    }

    @Override
    public WareHouse getWareHouse(String code) {
        WareHouse data = wareHouseBO.getWareHouse(code);
        User user = userBO.getUser(data.getUserId());
        data.setUser(user);
        return data;
    }

    @Override
    public Paginable<WareHouse> queryWareHouseFrontPage(int start, int limit,
            WareHouse condition) {
        userBO.getCheckUser(condition.getUserId());
        return wareHouseBO.getPaginable(start, limit, condition);
    }

    @Override
    public WareHouse getWareHouseByUser(String userId) {

        return wareHouseBO.getWareHouseByUser(userId);
    }
}
