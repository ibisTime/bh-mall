package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.IWareHouseLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareHouseDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.ESystemCode;
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
    IProductSpecsPriceBO productSpecsPriceBO;

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
    public long getTotalCountByProduct(WareHouse condition) {
        return wareHouseDAO.selectTotalCountProduct(condition);
    }

    @Override
    public List<WareHouse> queryWareHousePorductList(WareHouse condition) {
        return wareHouseDAO.selectPorductList(condition);
    }

    @Override
    public void buyWareHouse(Order data, Agent agent) {
        WareHouse wareHouse = this.getWareHouseByProductSpec(agent.getUserId(),
            data.getProductSpecsCode());

        // 没有该产品
        if (null == wareHouse) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.WareHouse.getCode());
            WareHouse whData = new WareHouse();
            whData.setCode(code);
            whData.setProductCode(data.getProductCode());
            whData.setProductName(data.getProductName());
            whData.setProductSpecsCode(data.getProductSpecsCode());
            whData.setProductSpecsName(data.getProductSpecsName());

            whData.setCurrency(ECurrency.YC_CNY.getCode());
            whData.setUserId(agent.getUserId());
            whData.setRealName(agent.getRealName());
            whData.setCreateDatetime(new Date());
            whData.setPrice(data.getPrice());

            whData.setQuantity(data.getQuantity());
            Long amount = data.getQuantity() * data.getPrice();
            whData.setAmount(amount);
            whData.setStatus(EProductStatus.Shelf_YES.getCode());

            whData.setCompanyCode(ESystemCode.BH.getCode());
            whData.setSystemCode(ESystemCode.BH.getCode());
            this.saveWareHouse(whData, data.getQuantity(), EBizType.AJ_GMYC,
                "购买" + data.getProductName(), data.getCode());

        } else {
            this.changeWareHouse(wareHouse.getCode(), data.getQuantity(),
                EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(), data.getCode());
        }
    }

    @Override
    public void changeWareHousePrice(List<WareHouse> whList, Integer level) {
        for (WareHouse data : whList) {
            ProductSpecsPrice psPrice = productSpecsPriceBO
                .getPriceByLevel(data.getProductSpecsCode(), level);
            data.setPrice(psPrice.getPrice());
            data.setAmount(data.getQuantity() * psPrice.getPrice());
            wareHouseDAO.changePrice(data);
        }
    }

}
