package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.IWareHouseSpecsBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.dao.IWareHouseDAO;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.domain.WareHouseSpecs;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;

@Component
public class WareHouseBOImpl extends PaginableBOImpl<WareHouse>
        implements IWareHouseBO {

    @Autowired
    IWareHouseDAO wareHouseDAO;

    @Autowired
    IWareHouseLogBO wareHouseLogBO;

    @Autowired
    IWareHouseSpecsBO wareHouseSpecsBO;

    @Override
    public void saveWareHouse(WareHouse data, Integer quantity,
            EBizType bizType, String bizNote, String refNo) {
        String logCode = wareHouseLogBO.saveWareHouseLog(data, quantity,
            bizType, bizNote, refNo);
        data.setType(EUserKind.Merchant.getCode());
        data.setLastChangeCode(logCode);
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
    public WareHouse getWareHouse(String code) {
        WareHouse data = null;
        if (StringUtils.isNotBlank(code)) {
            WareHouse condition = new WareHouse();
            condition.setCode(code);
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
        WareHouse condition = new WareHouse();
        condition.setUserId(userId);
        condition.setProductSpecsCode(productSpecsCode);
        return wareHouseDAO.select(condition);
    }

    @Override
    public List<WareHouse> getWareHouseByProduct(String productCode) {
        WareHouse condition = new WareHouse();
        condition.setProductCode(productCode);
        return wareHouseDAO.selectList(condition);
    }

    @Override
    public void transQuantity(String fromUser, String fromSpecs, String toUser,
            String toSpecs, Integer quantity, EBizType fromBizType,
            EBizType toBizType, String fromBizNote, String toBizNote,
            String refNo) {
        System.out.println("fromWareHouse:" + fromUser + "===" + fromSpecs);
        WareHouse fromWareHouse = this.getWareHouseByProductSpec(fromUser,
            fromSpecs);
        System.out.println("toWareHouse:" + toUser + "===" + toSpecs);
        WareHouse toWareHouse = this.getWareHouseByProductSpec(toUser, toSpecs);

        transQuantity(fromWareHouse, toWareHouse, quantity, fromBizType,
            toBizType, fromBizNote, toBizNote, refNo);
    }

    private void transQuantity(WareHouse fromWareHouse, WareHouse toWareHouse,
            Integer quantity, EBizType fromBizType, EBizType toBizType,
            String fromBizNote, String toBizNote, String refNo) {
        String fromCode = fromWareHouse.getCode();
        String toCode = toWareHouse.getCode();
        this.changeWareHouse(fromCode, quantity, fromBizType, fromBizNote,
            refNo);
        this.changeWareHouse(toCode, -quantity, toBizType, toBizNote, refNo);
    }

    @Override
    @Transactional
    public void changeWareHouse(String code, Integer quantity, EBizType bizType,
            String bizNote, String refNo) {

        WareHouse dbData = this.getWareHouse(code);
        Integer nowQuantity = dbData.getQuantity() + quantity;
        Long nowAmount = AmountUtil.eraseLiUp(nowQuantity * dbData.getPrice());
        if (nowQuantity < 0) {
            throw new BizException("xn0000", "该规格的产品数量不足");
        }

        // 记录流水
        String logCode = wareHouseLogBO.saveWareHouseLog(dbData, quantity,
            bizType, bizNote, refNo);
        // 改变数量
        WareHouse data = new WareHouse();
        data.setCode(code);
        data.setQuantity(nowQuantity);
        data.setAmount(nowAmount);
        data.setLastChangeCode(logCode);
        // 该规格产品数量为零时，从云仓删除该产品
        if (nowQuantity == 0) {
            wareHouseDAO.delete(data);
        } else {
            wareHouseDAO.updateQuantity(data);
        }

    }

    @Override
    public void refreshLogCode(WareHouse data) {
        wareHouseDAO.updateLogCode(data);

    }

    @Override
    public List<WareHouse> getWareHouseByUser(String userId) {
        WareHouse condition = new WareHouse();
        condition.setUserId(userId);
        return wareHouseDAO.selectList(condition);
    }

    @Override
    public void changeWareHouse(String code, Order order, Integer quantity,
            EBizType bizType, String bizNote) {
        WareHouse dbData = this.getWareHouse(code);
        WareHouseSpecs specs = wareHouseSpecsBO.getWareHouseSpecsByCode(
            dbData.getCode(), order.getProductSpecsCode());
        Integer nowQuantity = specs.getQuantity() + quantity;
        Long nowAmount = AmountUtil.eraseLiUp(nowQuantity * specs.getPrice());
        if (nowQuantity < 0) {
            throw new BizException("xn0000", "该规格的产品数量不足");
        }
        // 记录流水
        String logCode = wareHouseLogBO.saveWareHouseLog(order,
            dbData.getCode(), specs.getQuantity(), quantity, bizType.getCode(),
            bizNote, order.getCode());
        // 改变数量
        specs.setQuantity(nowQuantity);
        specs.setAmount(nowAmount);
        wareHouseSpecsBO.refreshQuantity(specs);
        dbData.setLastChangeCode(logCode);
        wareHouseDAO.updateLogCode(dbData);

    }

    @Override
    public WareHouse getWareHouseByUser(String userId, String productCode) {
        WareHouse data = null;
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(productCode)) {
            WareHouse condition = new WareHouse();
            condition.setUserId(userId);
            condition.setProductCode(productCode);
            data = wareHouseDAO.select(condition);
            if (data == null) {
                throw new BizException("xn00000", "该代理云仓中没有该产品");
            }
        }
        return data;
    }

    @Override
    public long getTotalCountByProduct(WareHouse condition) {
        return wareHouseDAO.selectTotalCountProduct(condition);
    }

    @Override
    public List<WareHouse> queryWareHousePorductList(WareHouse condition) {
        return wareHouseDAO.selectPorductList(condition);
    }

}
