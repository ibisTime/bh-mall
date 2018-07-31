package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.IWareLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Ware;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;

@Component
public class WareBOImpl extends PaginableBOImpl<Ware> implements IWareBO {

    @Autowired
    IWareDAO wareDAO;

    @Autowired
    IWareLogBO wareLogBO;

    @Autowired
    IAgentPriceBO agentPriceBO;

    @Override
    public void saveWare(Ware data, Integer quantity, EBizType bizType,
            String bizNote, String refNo) {
        String logCode = wareLogBO.saveWareLog(data, quantity, bizType, bizNote,
            refNo);
        data.setType(EUserKind.Merchant.getCode());
        data.setLastChangeCode(logCode);
        wareDAO.insert(data);

    }

    @Override
    public void refreshWare(Ware data) {
        wareDAO.updateQuantity(data);
    }

    @Override
    public List<Ware> queryWareList(Ware condition) {
        return wareDAO.selectList(condition);
    }

    @Override
    public Ware getWare(String code) {
        Ware data = null;
        if (StringUtils.isNotBlank(code)) {
            Ware condition = new Ware();
            condition.setCode(code);
            data = wareDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "云仓不存在");
            }
        }
        return data;
    }

    @Override
    public Ware getWareByProductSpec(String userId, String productSpecsCode) {
        Ware condition = new Ware();
        condition.setUserId(userId);
        condition.setSpecsCode(productSpecsCode);
        return wareDAO.select(condition);
    }

    @Override
    public List<Ware> getWareByProduct(String productCode) {
        Ware condition = new Ware();
        condition.setProductCode(productCode);
        return wareDAO.selectList(condition);
    }

    @Override
    public void transQuantity(String fromUser, String fromSpecs, String toUser,
            String toSpecs, Integer quantity, EBizType fromBizType,
            EBizType toBizType, String fromBizNote, String toBizNote,
            String refNo) {
        Ware fromWare = this.getWareByProductSpec(fromUser, fromSpecs);
        Ware toWare = this.getWareByProductSpec(toUser, toSpecs);

        transQuantity(fromWare, toWare, quantity, fromBizType, toBizType,
            fromBizNote, toBizNote, refNo);
    }

    private void transQuantity(Ware fromWare, Ware toWare, Integer quantity,
            EBizType fromBizType, EBizType toBizType, String fromBizNote,
            String toBizNote, String refNo) {
        String fromCode = fromWare.getCode();
        String toCode = toWare.getCode();
        this.changeWare(fromCode, quantity, fromBizType, fromBizNote, refNo);
        this.changeWare(toCode, -quantity, toBizType, toBizNote, refNo);
    }

    @Override
    @Transactional
    public void changeWare(String code, Integer quantity, EBizType bizType,
            String bizNote, String refNo) {

        Ware dbData = this.getWare(code);
        Integer nowQuantity = dbData.getQuantity() + quantity;
        Long nowAmount = AmountUtil.eraseLiUp(nowQuantity * dbData.getPrice());
        if (nowQuantity < 0) {
            throw new BizException("xn0000", "该规格的产品数量不足");
        }

        // 记录流水
        String logCode = wareLogBO.saveWareLog(dbData, quantity, bizType,
            bizNote, refNo);
        // 改变数量
        Ware data = new Ware();
        data.setCode(code);
        data.setQuantity(nowQuantity);
        data.setAmount(nowAmount);
        data.setLastChangeCode(logCode);
        // 该规格产品数量为零时，从云仓删除该产品
        if (nowQuantity == 0) {
            wareDAO.delete(data);
        } else {
            wareDAO.updateQuantity(data);
        }

    }

    @Override
    public void refreshLogCode(Ware data) {
        wareDAO.updateLogCode(data);

    }

    @Override
    public List<Ware> getWareByUser(String userId) {
        Ware condition = new Ware();
        condition.setUserId(userId);
        return wareDAO.selectList(condition);
    }

    @Override
    public long getTotalCountByProduct(Ware condition) {
        return wareDAO.selectTotalCountProduct(condition);
    }

    @Override
    public List<Ware> queryWareProductList(Ware condition) {
        return wareDAO.selectPorductList(condition);
    }

    @Override
    public void buyWare(InOrder data, Agent agent) {
        Ware ware = this.getWareByProductSpec(agent.getUserId(),
            data.getSpecsCode());

        // 没有该产品
        if (null == ware) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.Ware.getCode());
            Ware whData = new Ware();
            whData.setCode(code);
            whData.setProductCode(data.getProductCode());
            whData.setProductName(data.getProductName());
            whData.setSpecsCode(data.getSpecsCode());
            whData.setSpecsName(data.getSpecsName());

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
            this.saveWare(whData, data.getQuantity(), EBizType.AJ_GMYC,
                "购买" + data.getProductName(), data.getCode());

        } else {
            this.changeWare(ware.getCode(), data.getQuantity(),
                EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(), data.getCode());
        }
    }

    @Override
    public void changeWarePrice(List<Ware> whList, Integer level) {
        for (Ware data : whList) {
            AgentPrice psPrice = agentPriceBO
                .getPriceByLevel(data.getSpecsCode(), level);
            data.setPrice(psPrice.getPrice());
            data.setAmount(data.getQuantity() * psPrice.getPrice());
            wareDAO.changePrice(data);
        }
    }

    @Override
    public void checkProduct(String userId, String specsCode) {
        Ware data = new Ware();
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(specsCode)) {
            Ware condition = new Ware();
            condition.setUserId(userId);
            condition.setSpecsCode(specsCode);
            data = wareDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "上级云仓中没有改产品");
            }
        }

    }

}
