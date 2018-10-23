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
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IWareDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Ware;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EProductStatus;
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
            EWareLogType bizType, String bizNote, String refNo) {
        String logCode = wareLogBO.saveWareLog(data, type, quantity,
            bizType.getCode(), bizNote, refNo);
        data.setLastChangeCode(logCode);
        wareDAO.insert(data);

    }

    @Override
    public void refreshWare(Ware data, String updater, String remark) {
        wareDAO.updateQuantity(data);
        wareLogBO.saveWareLog(data, EWareLogType.PLAT_UPDATE.getCode(),
            data.getQuantity(), EWareLogType.PLAT_UPDATE.getCode(), remark,
            null);
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
    @Transactional
    public void changeWare(String code, String type, Integer quantity,
            EWareLogType bizType, String bizNote, String refNo) {

        Ware dbData = this.getWare(code);
        Integer nowQuantity = dbData.getQuantity() + quantity;
        if (nowQuantity < 0) {
            throw new BizException("xn0000", "上级云仓该规格的产品数量不足");
        }
        Long nowAmount = nowQuantity * dbData.getPrice();
        dbData.setQuantity(nowQuantity);
        // 记录流水
        String logCode = wareLogBO.saveWareLog(dbData, type, quantity,
            bizType.getCode(), bizNote, refNo);

        // 改变数量
        dbData.setCode(code);
        dbData.setAmount(nowAmount);
        dbData.setLastChangeCode(logCode);
        // 该规格产品数量为零时，从云仓删除该产品
        wareDAO.updateQuantity(dbData);

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
            Integer quantity, Long price, Agent agent, EWareLogType bizType,
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
            data.setQuantity(0);
            data.setPrice(psPrice.getPrice());
            data.setAmount(0L);
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
        wareDAO.delete(data);
        wareLogBO.saveWareLog(data, EWareLogType.PLAT_UPDATE.getCode(),
            data.getQuantity(), null, "平台调整库存", null);
    }

    @Override
    public List<Ware> getWareByProductList(String productCode) {
        Ware condition = new Ware();
        condition.setProductCode(productCode);
        return wareDAO.selectByProduct(condition);
    }

}
