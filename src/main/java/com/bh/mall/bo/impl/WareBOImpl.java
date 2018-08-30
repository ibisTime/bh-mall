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
import com.bh.mall.domain.Ware;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.ESpecsLogType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EWareLogType;
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
    public void saveWare(Ware data, String type, Integer quantity,
            ESpecsLogType bizType, String bizNote, String refNo) {
        String logCode = wareLogBO.saveWareLog(data, type, quantity, bizType,
            bizNote, refNo);
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
    public void transQuantity(String fromUser, String fromSpecs,
            String fromType, String toUser, String toSpecs, String toType,
            Integer quantity, ESpecsLogType fromBizType,
            ESpecsLogType toBizType, String fromBizNote, String toBizNote,
            String refNo) {
        Ware fromWare = this.getWareByProductSpec(fromUser, fromSpecs);
        Ware toWare = this.getWareByProductSpec(toUser, toSpecs);

        transQuantity(fromWare, fromType, toWare, toType, quantity, fromBizType,
            toBizType, fromBizNote, toBizNote, refNo);
    }

    private void transQuantity(Ware fromWare, String fromType, Ware toWare,
            String toType, Integer quantity, ESpecsLogType fromBizType,
            ESpecsLogType toBizType, String fromBizNote, String toBizNote,
            String refNo) {
        String fromCode = fromWare.getCode();
        String toCode = toWare.getCode();
        this.changeWare(fromCode, fromType, quantity, fromBizType, fromBizNote,
            refNo);
        this.changeWare(toCode, toType, -quantity, toBizType, toBizNote, refNo);
    }

    @Override
    @Transactional
    public void changeWare(String code, String type, Integer quantity,
            ESpecsLogType bizType, String bizNote, String refNo) {

        Ware dbData = this.getWare(code);
        Integer nowQuantity = dbData.getQuantity() + quantity;
        Long nowAmount = AmountUtil.eraseLiUp(nowQuantity * dbData.getPrice());
        if (nowQuantity < 0) {
            throw new BizException("xn0000", "该规格的产品数量不足");
        }

        // 记录流水
        String logCode = wareLogBO.saveWareLog(dbData, type, quantity, bizType,
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
    public void buyWare(String orderCode, String productCode,
            String productName, String specsCode, String specsName,
            Integer quantity, Long price, Agent agent, ESpecsLogType bizType,
            String bizNote) {
        Ware ware = this.getWareByProductSpec(agent.getUserId(), specsCode);

        // 没有该产品
        if (null == ware) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.Ware.getCode());
            Ware whData = new Ware();
            whData.setCode(code);
            whData.setProductCode(productCode);
            whData.setProductName(productName);
            whData.setSpecsCode(specsCode);
            whData.setSpecsName(specsName);

            whData.setCurrency(ECurrency.YC_CNY.getCode());
            whData.setUserId(agent.getUserId());
            whData.setRealName(agent.getRealName());
            whData.setCreateDatetime(new Date());
            whData.setPrice(price);

            whData.setQuantity(quantity);
            whData.setAmount(quantity * price);
            whData.setStatus(EProductStatus.Shelf_YES.getCode());

            whData.setCompanyCode(ESystemCode.BH.getCode());
            whData.setSystemCode(ESystemCode.BH.getCode());
            this.saveWare(whData, EWareLogType.IN.getCode(), quantity, bizType,
                bizNote, orderCode);

        } else {
            this.changeWare(ware.getCode(), EWareLogType.IN.getCode(), quantity,
                bizType, bizNote, orderCode);
        }
    }

    @Override
    public void changeWarePrice(String userId, Integer level) {
        Ware condition = new Ware();
        condition.setUserId(userId);
        List<Ware> list = wareDAO.selectList(condition);
        for (Ware data : list) {
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
                throw new BizException("xn00000", "上级云仓中没有该产品");
            }
        }

    }

    @Override
    public void removeByAgent(String userId) {
        Ware condition = new Ware();
        condition.setUserId(userId);
        List<Ware> list = wareDAO.selectList(condition);
        for (Ware data : list) {
            wareDAO.delete(data);
        }
    }

    @Override
    public void removeWare(Ware data) {
        // wareLogBO.saveWareLog(data, type, quantity, remark);
    }

}
