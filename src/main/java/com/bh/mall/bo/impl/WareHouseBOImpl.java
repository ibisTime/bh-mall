package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.dao.IWareHouseDAO;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;

@Component
public class WareHouseBOImpl extends PaginableBOImpl<WareHouse>
        implements IWareHouseBO {

    @Autowired
    private IWareHouseDAO wareHouseDAO;

    @Autowired
    private IWareHouseLogBO wareHouseLogBO;

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
        WareHouse fromWareHouse = this.getWareHouseByProductSpec(fromUser,
            fromSpecs);
        WareHouse toWareHouse = this.getWareHouseByProductSpec(toUser, toSpecs);

        transQuantity(fromWareHouse, toWareHouse, quantity, fromBizType,
            toBizType, fromBizNote, toBizNote, refNo);
    }

    private void transQuantity(WareHouse fromWareHouse, WareHouse toWareHouse,
            Integer quantity, EBizType fromBizType, EBizType toBizType,
            String fromBizNote, String toBizNote, String refNo) {
        String fromCode = fromWareHouse.getCode();
        String toCode = toWareHouse.getCode();
        if (fromCode.equals(toCode)) {
            throw new BizException("xn0000", "来去账户一致");
        }
        this.changeWareHouse(fromCode, -quantity, fromBizType, fromBizNote,
            refNo);
        this.changeWareHouse(toCode, quantity, toBizType, toBizNote, refNo);
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
        wareHouseDAO.updateQuantity(data);
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

}
