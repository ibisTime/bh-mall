package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.IWareHouseSpecsBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareHouseSpecsDAO;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.domain.WareHouseSpecs;
import com.bh.mall.exception.BizException;

@Component
public class WareHouseSpecsBOImpl extends PaginableBOImpl<WareHouseSpecs>
        implements IWareHouseSpecsBO {

    @Autowired
    IWareHouseSpecsDAO wareHouseSpecsDAO;

    @Autowired
    IWareHouseLogBO wareHouseLogBO;

    public void saveWareHouseSpecs(WareHouseSpecs data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.WareHourseSpecs.getCode());
            data.setCode(code);
            wareHouseSpecsDAO.insert(data);
        }
    }

    @Override
    public void removeWareHouseSpecs(String code) {
    }

    @Override
    public void refreshWareHouseSpecs(String code, Integer quantity) {

        WareHouseSpecs data = this.getWareHouseSpecs(code);
        Integer nowQuantity = data.getQuantity() + quantity;
        Long nowAmount = AmountUtil.eraseLiUp(nowQuantity * data.getPrice());
        if (nowQuantity < 0) {
            throw new BizException("xn0000", "该规格的产品数量不足");
        }
        // 改变数量
        data.setQuantity(nowQuantity);
        data.setAmount(nowAmount);
        wareHouseSpecsDAO.update(data);
    }

    @Override
    public List<WareHouseSpecs> queryWareHouseSpecsList(
            WareHouseSpecs condition) {
        return wareHouseSpecsDAO.selectList(condition);
    }

    @Override
    public WareHouseSpecs getWareHouseSpecs(String code) {
        WareHouseSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            WareHouseSpecs condition = new WareHouseSpecs();
            condition.setCode(code);
            data = wareHouseSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }

    @Override
    public WareHouseSpecs getWareHouseSpecsByCode(String whCode,
            String productSpecsCode) {
        WareHouseSpecs condition = new WareHouseSpecs();
        condition.setWareHouseCode(whCode);
        condition.setProductSpecsCode(productSpecsCode);
        return wareHouseSpecsDAO.select(condition);
    }

    @Override
    public void saveWareHouseSpecs(WareHouse wareHouse, Order order) {
        WareHouseSpecs data = new WareHouseSpecs();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WareHourseSpecs.getCode());
        data.setCode(code);
        data.setWareHouseCode(wareHouse.getCode());
        data.setProductSpecsCode(order.getProductSpecsCode());
        data.setQuantity(order.getQuantity());

        data.setPrice(order.getPrice());
        data.setAmount(order.getAmount());
        wareHouseSpecsDAO.insert(data);

    }

    @Override
    public void refreshQuantity(WareHouseSpecs data) {
        wareHouseSpecsDAO.updateQuantity(data);
    }

}
