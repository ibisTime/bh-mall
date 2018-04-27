package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.ao.IWareHouseAO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.User;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.res.XN627814Res;

@Service
public class WareHouseAOImpl implements IWareHouseAO {

    @Autowired
    private IWareHouseBO wareHouseBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProductAO productAO;

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
            Product product = productAO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
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
            Product product = productAO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
        }
        return list;
    }

    @Override
    public List<WareHouse> getWareHouse(String code) {
        WareHouse condition = new WareHouse();
        condition.setProductCode(code);

        List<WareHouse> list = wareHouseBO.queryWareHouseList(condition);
        for (WareHouse wareHouse : list) {
            if (StringUtils.isNotBlank(wareHouse.getUserId())) {
                User user = userBO.getUser(wareHouse.getUserId());
                wareHouse.setUser(user);
            }
            Product product = productAO.getProduct(wareHouse.getCode());
            wareHouse.setProduct(product);
        }

        return list;
    }

    @Override
    public Paginable<WareHouse> queryWareHouseFrontPage(int start, int limit,
            WareHouse condition) {
        Paginable<WareHouse> page = wareHouseBO.getPaginable(start, limit,
            condition);
        List<WareHouse> list = page.getList();
        for (WareHouse wareHouse : list) {
            Product product = productAO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
        }
        page.setList(list);
        userBO.getCheckUser(condition.getUserId());
        return wareHouseBO.getPaginable(start, limit, condition);
    }

    @Override
    public XN627814Res getWareHouseByUser(String userId) {
        XN627814Res res = null;
        Long allAmount = 0L;
        List<WareHouse> list = wareHouseBO.getWareHouseByUser(userId);
        for (WareHouse wareHouse : list) {
            Product product = productAO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
            allAmount = allAmount + wareHouse.getAmount();
        }
        allAmount = AmountUtil.eraseLiUp(allAmount);
        res = new XN627814Res(list, allAmount);
        return res;
    }
}
