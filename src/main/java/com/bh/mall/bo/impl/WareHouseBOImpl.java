package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IWareHouseDAO;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.exception.BizException;

@Component
public class WareHouseBOImpl extends PaginableBOImpl<WareHouse>
        implements IWareHouseBO {

    @Autowired
    private IWareHouseDAO wareHouseDAO;

    @Override
    public void saveWareHouse(WareHouse data, String logCode) {
        wareHouseDAO.insert(data);

    }

    @Override
    public void removeWareHouse(String code) {
    }

    @Override
    public void refreshWareHouse(WareHouse data) {
        wareHouseDAO.updateQuantity(data);
    }

    @Override
    public List<WareHouse> queryWareHouseList(WareHouse condition) {
        return wareHouseDAO.selectList(condition);
    }

    @Override
    public WareHouse getWareHouse(String userId) {
        WareHouse data = null;
        if (StringUtils.isNotBlank(userId)) {
            WareHouse condition = new WareHouse();
            condition.setUserId(userId);
            data = wareHouseDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "云仓不存在");
            }
        }
        return data;
    }

    @Override
    public WareHouse getWareHouseByProductSpec(String userId,
            String productSpecsCode) {
        WareHouse data = null;
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(productSpecsCode)) {
            WareHouse condition = new WareHouse();
            condition.setUserId(userId);
            condition.setProductSpecsCode(productSpecsCode);
            data = wareHouseDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "云仓不存在");
            }
        }
        return data;
    }

    @Override
    public WareHouse getWareHouseByProduct(String userId) {

        return null;
    }

}
