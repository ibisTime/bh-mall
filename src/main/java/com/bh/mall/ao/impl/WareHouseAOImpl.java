package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IWareHouseAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.User;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627815Req;
import com.bh.mall.dto.res.XN627814Res;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EProductYunFei;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class WareHouseAOImpl implements IWareHouseAO {

    @Autowired
    IWareHouseBO wareHouseBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IProductBO productBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IOrderBO orderBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public Paginable<WareHouse> queryWareHousePage(int start, int limit,
            WareHouse condition) {
        Paginable<WareHouse> page = wareHouseBO.getPaginable(start, limit,
            condition);
        List<WareHouse> list = page.getList();
        for (WareHouse wareHouse : list) {
            User user = userBO.getUser(wareHouse.getUserId());
            wareHouse.setUser(user);
            Product product = productBO.getProduct(wareHouse.getProductCode());
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
            Product product = productBO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
        }
        return list;
    }

    @Override
    public List<WareHouse> getWareHouse(String code) {
        WareHouse condition = new WareHouse();
        condition.setCode(code);

        List<WareHouse> list = wareHouseBO.queryWareHouseList(condition);
        for (WareHouse wareHouse : list) {
            if (StringUtils.isNotBlank(wareHouse.getUserId())) {
                User user = userBO.getUser(wareHouse.getUserId());
                wareHouse.setUser(user);
            }
            Product product = productBO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
        }

        return list;
    }

    @Override
    public Paginable<WareHouse> queryWareHouseFrontPage(int start, int limit,
            WareHouse condition) {
        Paginable<WareHouse> page = wareHouseBO.getPaginable(start, limit,
            condition);
        for (WareHouse wareHouse : page.getList()) {
            if (StringUtils.isNotBlank(wareHouse.getProductCode())) {
                Product product = productBO
                    .getProduct(wareHouse.getProductCode());
                wareHouse.setProduct(product);
            }
        }
        return page;
    }

    @Override
    public XN627814Res getWareHouseByUser(String userId) {
        XN627814Res res = null;
        Long allAmount = 0L;
        List<WareHouse> list = wareHouseBO.getWareHouseByUser(userId);
        for (WareHouse wareHouse : list) {
            if (StringUtils.isNotBlank(wareHouse.getProductCode())) {
                Product product = productBO
                    .getProduct(wareHouse.getProductCode());
                wareHouse.setProduct(product);
                allAmount = allAmount + wareHouse.getAmount();
            }
        }
        allAmount = AmountUtil.eraseLiUp(allAmount);
        res = new XN627814Res(list, allAmount);
        return res;
    }

    @Override
    public void deliveProject(XN627815Req req) {
        WareHouse data = wareHouseBO.getWareHouseByProductSpec(req.getUserId(),
            req.getProductSpecsCode());
        Product product = productBO.getProduct(data.getProductCode());
        if (null == data) {
            throw new BizException("xn00000", "您仓库中没有该规格的产品");
        }

        if (data.getQuantity() < StringValidater.toInteger(req.getQuantity())) {
            throw new BizException("xn00000", "您仓库中该规格的产品数量不足");
        }
        Long amount = AmountUtil.mul(data.getPrice(),
            StringValidater.toInteger(req.getQuantity()));

        String kind = EOrderKind.Normal_Order.getCode();

        Order order = new Order();
        String orderCode = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        order.setCode(orderCode);
        order.setProductCode(data.getCode());
        order.setProductName(data.getProductName());
        // order.setPic(data.get);

        order.setProductSpecsCode(data.getProductSpecsCode());
        order.setProductSpecsName(data.getProductSpecsName());
        order.setQuantity(StringValidater.toInteger(req.getQuantity()));
        order.setPrice(data.getPrice());

        // 是否包邮
        Long yunfei = 0L;
        if (EProductYunFei.YunFei_NO.getCode().equals(product.getIsFree())) {
            SYSConfig sysConfig = sysConfigBO.getConfig(req.getProvince(),
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            yunfei = StringValidater.toLong(sysConfig.getCvalue());
            amount = amount + StringValidater.toLong(sysConfig.getCvalue());
        }

        order.setYunfei(yunfei);
        order.setAmount(amount);
        order.setApplyUser(data.getUserId());

        order.setApplyDatetime(new Date());
        order.setToUser(data.getUserId());

        order.setSigner(req.getSigner());
        order.setMobile(req.getMobile());
        order.setAddress(req.getAddress());
        order.setArea(req.getArea());
        order.setCity(req.getCity());

        order.setKind(kind);
        order.setProvince(req.getProvince());

        order.setStatus(EOrderStatus.Unpaid.getCode());
        order.setIsSendHome(EBoolean.YES.getCode());
        orderBO.saveOrder(order);

        // 减少云仓库存
        wareHouseBO.changeWareHouse(data.getCode(),
            -StringValidater.toInteger(req.getQuantity()), EBizType.AJ_YCTH,
            EBizType.AJ_YCTH.getValue(), orderCode);
    }
}
