package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.ao.IWareAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627815Req;
import com.bh.mall.dto.res.XN627814Res;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class WareAOImpl implements IWareAO {

    @Autowired
    IWareBO wareBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IProductBO productBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IOrderBO orderBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    ISpecsBO specsBO;

    @Autowired
    IAgentPriceBO agentPriceBO;

    @Autowired
    IOrderAO orderAO;

    @Autowired
    IAddressBO addressBO;

    @Override
    public Paginable<Ware> queryWarePage(int start, int limit, Ware condition) {
        long count = wareBO.getTotalCountByProduct(condition);
        Page<Ware> page = new Page<Ware>(start, limit, count);
        List<Ware> list = wareBO.queryWareProductList(condition);

        Ware specsCondition = new Ware();
        for (Ware ware : list) {
            Agent agent = agentBO.getAgent(ware.getUserId());
            ware.setAgent(agent);
            Product product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);
            specsCondition.setUserId(ware.getUserId());
            specsCondition.setProductCode(ware.getProductCode());
            ware.setWhsList(wareBO.queryWareList(specsCondition));

        }

        page.setList(list);
        return page;
    }

    @Override
    public List<Ware> queryWareList(Ware condition) {
        List<Ware> list = wareBO.queryWareList(condition);
        Ware specsCondition = new Ware();
        for (Ware ware : list) {
            Agent agent = agentBO.getAgent(ware.getUserId());
            ware.setAgent(agent);
            Product product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);
            specsCondition.setUserId(ware.getUserId());
            specsCondition.setProductCode(ware.getProductCode());
            ware.setWhsList(wareBO.queryWareList(specsCondition));
            int minSpecsNumber = specsBO
                .getMinSpecsNumber(product.getCode());
            ware.setAllQuantity(minSpecsNumber * ware.getQuantity());
        }
        return list;
    }

    @Override
    public Ware getWare(String code) {

        Ware data = wareBO.getWare(code);
        Agent agent = agentBO.getAgent(data.getUserId());
        Ware condition = new Ware();
        condition.setUserId(data.getUserId());
        condition.setProductCode(data.getProductCode());
        List<Ware> specsList = wareBO.queryWareList(condition);
        for (Ware wh : specsList) {
            AgentPrice price = agentPriceBO
                .getPriceByLevel(wh.getProductSpecsCode(), agent.getLevel());
            wh.setPrice(price.getPrice());
        }
        data.setWhsList(specsList);
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        return data;
    }

    @Override
    public Paginable<Ware> queryWareFrontPage(int start, int limit,
            Ware condition) {
        long count = wareBO.getTotalCountByProduct(condition);
        Page<Ware> page = new Page<Ware>(start, limit, count);
        List<Ware> list = wareBO.queryWareProductList(condition);

        Ware specsCondition = new Ware();
        for (Ware ware : list) {
            Agent agent = agentBO.getAgent(ware.getUserId());
            ware.setAgent(agent);
            Product product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);
            specsCondition.setUserId(ware.getUserId());
            specsCondition.setProductCode(ware.getProductCode());
            ware.setWhsList(wareBO.queryWareList(specsCondition));
            int minSpecsNumber = specsBO
                .getMinSpecsNumber(product.getCode());
            ware.setAllQuantity(minSpecsNumber * ware.getQuantity());

        }
        page.setList(list);
        return page;
    }

    @Override
    public XN627814Res getWareByUser(String userId) {
        XN627814Res res = null;
        Long allAmount = 0L;
        List<Ware> list = wareBO.getWareByUser(userId);
        for (Ware ware : list) {
            if (StringUtils.isNotBlank(ware.getProductCode())) {
                Product product = productBO.getProduct(ware.getProductCode());
                ware.setProduct(product);
                allAmount = allAmount + ware.getAmount();
            }
        }
        allAmount = AmountUtil.eraseLiUp(allAmount);
        res = new XN627814Res(list, allAmount);
        return res;
    }

    @Override
    @Transactional
    public void deliveProject(XN627815Req req) {
        Agent agent = agentBO.getAgent(req.getUserId());
        Ware data = wareBO.getWareByProductSpec(req.getUserId(),
            req.getProductSpecsCode());
        if (null == data) {
            throw new BizException("xn00000", "您仓库中没有该规格的产品");
        }

        Specs psData = specsBO
            .getProductSpecs(data.getProductSpecsCode());
        AgentPrice pspData = agentPriceBO
            .getPriceByLevel(data.getProductSpecsCode(), agent.getLevel());

        // 检查限购
        orderAO.checkLimitNumber(agent, psData, pspData,
            StringValidater.toInteger(req.getQuantity()));
        if (pspData.getMinNumber() > data.getQuantity()) {
            throw new BizException("xn00000",
                "该产品云仓提货不能少于" + pspData.getMinNumber() + psData.getName());
        }

        // 剩余产品是否充足
        Product product = productBO.getProduct(data.getProductCode());
        if (data.getQuantity() < StringValidater.toInteger(req.getQuantity())) {
            throw new BizException("xn00000", "您仓库中该规格的产品数量不足");
        }

        // 获取授权单
        String kind = EOrderKind.Pick_Up.getCode();
        AgentLevel agentLevel = agentLevelBO.getAgentByLevel(agent.getLevel());

        // 是否完成授权单
        Long amount = data.getPrice()
                * StringValidater.toInteger(req.getQuantity());

        if (orderBO.checkImpowerOrder(agent.getUserId(),
            agent.getImpowerDatetime())) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn00000", agentLevel.getName()
                        + "授权单金额为[" + agentLevel.getAmount() / 1000 + "]元");
            } else {
                kind = EOrderKind.Impower_Order.getCode();
            }

        } else {
            kind = EOrderKind.Pick_Up.getCode();
        }

        // 是否完成升级单
        // if (EUserStatus.UPGRADED.getCode().equals(user.getStatus())) {
        // if (!orderBO.checkUpgradeOrder(user.getUserId())) {
        // kind = EOrderKind.Upgrade_Order.getCode();
        // }
        // }

        // 产品不包邮，计算运费
        Long yunfei = 0L;
        if (EBoolean.NO.getCode().equals(product.getIsFree())) {
            SYSConfig sysConfig = sysConfigBO.getConfig(req.getProvince(),
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            yunfei = StringValidater.toLong(sysConfig.getCvalue());
        }

        // 订单拆单
        if (EBoolean.YES.getCode().equals(psData.getIsSingle())) {

            int singleNumber = StringValidater.toInteger(req.getQuantity())
                    / psData.getSingleNumber();

            for (int i = 0; i < singleNumber; i++) {

                String code = orderBO.pickUpGoods(data.getProductCode(),
                    data.getProductName(), product.getPic(),
                    data.getProductSpecsCode(), data.getProductSpecsName(),
                    psData.getSingleNumber(), data.getPrice(),
                    psData.getSingleNumber() * data.getPrice(), yunfei,
                    agent.getHighUserId(), data.getUserId(), req.getSigner(),
                    req.getMobile(), req.getProvince(), req.getCity(),
                    req.getArea(), req.getAddress(), kind);

                // 减少云仓库存
                wareBO.changeWare(data.getCode(),
                    -StringValidater.toInteger(req.getQuantity()),
                    EBizType.AJ_YCTH, EBizType.AJ_YCTH.getValue(), code);
            }
        } else {
            String code = orderBO.pickUpGoods(data.getProductCode(),
                data.getProductName(), product.getPic(),
                data.getProductSpecsCode(), data.getProductSpecsName(),
                StringValidater.toInteger(req.getQuantity()), data.getPrice(),
                psData.getSingleNumber() * data.getPrice(), yunfei,
                agent.getHighUserId(), data.getUserId(), req.getSigner(),
                req.getMobile(), req.getProvince(), req.getCity(),
                req.getArea(), req.getAddress(), kind);
            // 减少云仓库存
            wareBO.changeWare(data.getCode(),
                -StringValidater.toInteger(req.getQuantity()), EBizType.AJ_YCTH,
                EBizType.AJ_YCTH.getValue(), code);
        }

    }

    @Override
    public Paginable<Ware> queryWareCFrontPage(int start, int limit,
            Ware condition) {
        long count = wareBO.getTotalCountByProduct(condition);
        Page<Ware> page = new Page<Ware>(start, limit, count);
        List<Ware> list = wareBO.queryWareProductList(condition);
        Ware specsCondition = new Ware();
        Product product = null;
        for (Ware ware : list) {
            product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);
            specsCondition.setUserId(ware.getUserId());
            specsCondition.setProductCode(ware.getProductCode());
            List<Ware> whList = wareBO.queryWareList(specsCondition);
            for (Ware wh : whList) {
                AgentPrice price = agentPriceBO
                    .getPriceByLevel(wh.getProductSpecsCode(), 6);
                wh.setPrice(price.getPrice());
            }
            ware.setWhsList(whList);
        }
        page.setList(list);
        return page;
    }

    @Override
    public Ware getWareByCustomer(String code) {
        Ware data = wareBO.getWare(code);
        Ware condition = new Ware();
        condition.setUserId(data.getUserId());
        condition.setProductCode(data.getProductCode());
        List<Ware> specsList = wareBO.queryWareList(condition);
        for (Ware ware : specsList) {
            AgentPrice price = agentPriceBO
                .getPriceByLevel(ware.getProductSpecsCode(), 6);
            ware.setPrice(price.getPrice());
        }
        data.setWhsList(specsList);
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        return data;
    }
}
