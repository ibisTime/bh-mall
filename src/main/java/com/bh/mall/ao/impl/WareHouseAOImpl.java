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
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.IWareHouseSpecsBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecsPrice;
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

    @Autowired
    IWareHouseSpecsBO wareHouseSpecsBO;

    @Autowired
    IProductSpecsBO productSpecsBO;

    @Autowired
    IProductSpecsPriceBO productSpecsPriceBO;

    @Override
    public Paginable<WareHouse> queryWareHousePage(int start, int limit,
            WareHouse condition) {
        long count = wareHouseBO.getTotalCountByProduct(condition);
        Page<WareHouse> page = new Page<WareHouse>(start, limit, count);
        List<WareHouse> list = wareHouseBO.queryWareHousePorductList(condition);

        WareHouse specsCondition = new WareHouse();
        for (WareHouse wareHouse : list) {
            User user = userBO.getUser(wareHouse.getUserId());
            wareHouse.setUser(user);
            Product product = productBO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
            specsCondition.setUserId(wareHouse.getUserId());
            specsCondition.setProductCode(wareHouse.getProductCode());
            wareHouse
                .setWhsList(wareHouseBO.queryWareHouseList(specsCondition));

        }
        page.setList(list);
        return page;
    }

    @Override
    public List<WareHouse> queryWareHouseList(WareHouse condition) {
        List<WareHouse> list = wareHouseBO.queryWareHouseList(condition);
        WareHouse specsCondition = new WareHouse();
        for (WareHouse wareHouse : list) {
            User user = userBO.getUser(wareHouse.getUserId());
            wareHouse.setUser(user);
            Product product = productBO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
            specsCondition.setUserId(wareHouse.getUserId());
            specsCondition.setProductCode(wareHouse.getProductCode());
            wareHouse
                .setWhsList(wareHouseBO.queryWareHouseList(specsCondition));
        }
        return list;
    }

    @Override
    public WareHouse getWareHouse(String code) {

        WareHouse data = wareHouseBO.getWareHouse(code);
        WareHouse condition = new WareHouse();
        condition.setUserId(data.getUserId());
        condition.setProductCode(data.getProductCode());
        List<WareHouse> specsList = wareHouseBO.queryWareHouseList(condition);
        for (WareHouse wh : specsList) {
            ProductSpecsPrice price = productSpecsPriceBO
                .getPriceByLevel(wh.getProductSpecsCode(), 6);
            wh.setPrice(price.getPrice());
        }
        data.setWhsList(specsList);
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        return data;
    }

    @Override
    public Paginable<WareHouse> queryWareHouseFrontPage(int start, int limit,
            WareHouse condition) {
        long count = wareHouseBO.getTotalCountByProduct(condition);
        Page<WareHouse> page = new Page<WareHouse>(start, limit, count);
        List<WareHouse> list = wareHouseBO.queryWareHousePorductList(condition);

        WareHouse specsCondition = new WareHouse();
        for (WareHouse wareHouse : list) {
            User user = userBO.getUser(wareHouse.getUserId());
            wareHouse.setUser(user);
            Product product = productBO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
            specsCondition.setUserId(wareHouse.getUserId());
            specsCondition.setProductCode(wareHouse.getProductCode());
            wareHouse
                .setWhsList(wareHouseBO.queryWareHouseList(specsCondition));

            // WareHouseSpecs whsCondition = new WareHouseSpecs();
            // whsCondition.setWareHouseCode(wareHouse.getCode());
            // List<WareHouseSpecs> whsList = wareHouseSpecsBO
            // .queryWareHouseSpecsList(whsCondition);
            // for (WareHouseSpecs wareHouseSpecs : whsList) {
            // ProductSpecs specs = productSpecsBO
            // .getProductSpecs(wareHouseSpecs.getProductSpecsCode());
            // wareHouseSpecs.setSpecsName(specs.getName());
            // }
            // wareHouse.setWhsList(whsList);
        }
        page.setList(list);
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
            // WareHouseSpecs whsCondition = new WareHouseSpecs();
            // whsCondition.setWareHouseCode(wareHouse.getCode());
            // List<WareHouseSpecs> whsList = wareHouseSpecsBO
            // .queryWareHouseSpecsList(whsCondition);
            // for (WareHouseSpecs wareHouseSpecs : whsList) {
            // ProductSpecs specs = productSpecsBO
            // .getProductSpecs(wareHouseSpecs.getProductSpecsCode());
            // wareHouseSpecs.setSpecsName(specs.getName());
            // }
            // wareHouse.setWhsList(whsList);
        }
        allAmount = AmountUtil.eraseLiUp(allAmount);
        res = new XN627814Res(list, allAmount);
        return res;
    }

    @Override
    public void deliveProject(XN627815Req req) {
        WareHouse data = wareHouseBO.getWareHouseByProductSpec(req.getUserId(),
            req.getProductSpecsCode());
        if (null == data) {
            throw new BizException("xn00000", "您仓库中没有该规格的产品");
        }
        Product product = productBO.getProduct(data.getProductCode());
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
        order.setProductCode(data.getProductCode());
        order.setProductName(data.getProductName());
        order.setPic(product.getPic());

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

        order.setSigner(req.getSigner());
        order.setMobile(req.getMobile());
        order.setAddress(req.getAddress());
        order.setArea(req.getArea());
        order.setCity(req.getCity());

        order.setKind(kind);
        order.setProvince(req.getProvince());

        order.setStatus(EOrderStatus.TO_Apprvoe.getCode());
        order.setIsSendHome(EBoolean.YES.getCode());
        orderBO.saveOrder(order);

        // 减少云仓库存
        wareHouseBO.changeWareHouse(data.getCode(),
            -StringValidater.toInteger(req.getQuantity()), EBizType.AJ_YCTH,
            EBizType.AJ_YCTH.getValue(), orderCode);
    }

    @Override
    public Paginable<WareHouse> queryWareHouseCFrontPage(int start, int limit,
            WareHouse condition) {
        long count = wareHouseBO.getTotalCountByProduct(condition);
        Page<WareHouse> page = new Page<WareHouse>(start, limit, count);
        List<WareHouse> list = wareHouseBO.queryWareHousePorductList(condition);
        WareHouse specsCondition = new WareHouse();
        Product product = null;
        for (WareHouse wareHouse : list) {
            product = productBO.getProduct(wareHouse.getProductCode());
            wareHouse.setProduct(product);
            specsCondition.setUserId(wareHouse.getUserId());
            specsCondition.setProductCode(wareHouse.getProductCode());
            List<WareHouse> whList = wareHouseBO
                .queryWareHouseList(specsCondition);
            for (WareHouse wh : whList) {
                ProductSpecsPrice price = productSpecsPriceBO
                    .getPriceByLevel(wh.getProductSpecsCode(), 6);
                wh.setPrice(price.getPrice());
            }
            wareHouse.setWhsList(whList);
        }
        page.setList(list);
        return page;
    }

    @Override
    public WareHouse getWareHouseByCustomer(String code) {
        WareHouse data = wareHouseBO.getWareHouse(code);
        WareHouse condition = new WareHouse();
        condition.setUserId(data.getUserId());
        condition.setProductCode(data.getProductCode());
        List<WareHouse> specsList = wareHouseBO.queryWareHouseList(condition);
        for (WareHouse wareHouse : specsList) {
            ProductSpecsPrice price = productSpecsPriceBO
                .getPriceByLevel(wareHouse.getProductSpecsCode(), 6);
            wareHouse.setPrice(price.getPrice());
        }
        data.setWhsList(specsList);
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        return data;
    }
}
