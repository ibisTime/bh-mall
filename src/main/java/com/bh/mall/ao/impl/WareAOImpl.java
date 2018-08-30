package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.ao.IWareAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Charge;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627815Req;
import com.bh.mall.dto.res.XN627805Res;
import com.bh.mall.dto.res.XN627814Res;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAgentStatus;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChargeStatus;
import com.bh.mall.enums.ECheckStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EOutOrderKind;
import com.bh.mall.enums.ESpecsLogType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EWareLogType;
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
    IInOrderBO inOrderBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    ISpecsBO specsBO;

    @Autowired
    IAgentPriceBO agentPriceBO;

    @Autowired
    IInOrderAO inOrderAO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IOutOrderBO outOrderBO;

    @Autowired
    IChargeBO chargeBO;

    @Autowired
    ISjFormBO sjFormBO;

    @Autowired
    ISYSUserBO sysUserBO;

    // 分页查询
    @Override
    public Paginable<Ware> queryWarePage(int start, int limit, Ware condition) {
        Paginable<Ware> page = wareBO.getPaginable(start, limit, condition);

        Ware specsCondition = new Ware();
        for (Ware ware : page.getList()) {
            Agent agent = agentBO.getAgent(ware.getUserId());
            ware.setAgent(agent);
            Product product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);

            specsCondition.setUserId(ware.getUserId());
            specsCondition.setProductCode(ware.getProductCode());
            ware.setWhsList(wareBO.queryWareList(specsCondition));

            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == agent
                .getLevel()) {
                ware.setTeamLeader(agent.getRealName());
            } else {
                agent = agentBO.getTeamLeader(agent.getTeamName());
                ware.setTeamLeader(agent.getRealName());
            }

        }
        return page;
    }

    // 列表查询
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
            int minSpecsNumber = specsBO.getMinSpecsNumber(product.getCode());
            ware.setAllQuantity(minSpecsNumber * ware.getQuantity());

            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == agent
                .getLevel()) {
                ware.setTeamLeader(agent.getRealName());
            } else {
                agent = agentBO.getTeamLeader(agent.getTeamName());
                ware.setTeamLeader(agent.getRealName());
            }
        }
        return list;
    }

    // 详细查询
    @Override
    public Ware getWare(String code) {

        Ware data = wareBO.getWare(code);
        Agent agent = agentBO.getAgent(data.getUserId());
        Ware condition = new Ware();
        condition.setUserId(data.getUserId());
        condition.setProductCode(data.getProductCode());
        List<Ware> specsList = wareBO.queryWareList(condition);

        for (Ware wh : specsList) {
            AgentPrice price = agentPriceBO.getPriceByLevel(wh.getSpecsCode(),
                agent.getLevel());
            wh.setPrice(price.getPrice());
        }
        data.setWhsList(specsList);
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);

        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == agent
            .getLevel()) {
            data.setTeamLeader(agent.getRealName());
        } else {
            agent = agentBO.getTeamLeader(agent.getTeamName());
            data.setTeamLeader(agent.getRealName());
        }
        return data;
    }

    // 前端分页查询
    @Override
    public Paginable<Ware> queryWareFrontPage(int start, int limit,
            Ware condition) {
        Paginable<Ware> page = wareBO.getPaginable(start, limit, condition);

        Ware specsCondition = new Ware();
        for (Ware ware : page.getList()) {
            Agent agent = agentBO.getAgent(ware.getUserId());
            ware.setAgent(agent);
            Product product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);
            specsCondition.setUserId(ware.getUserId());
            specsCondition.setProductCode(ware.getProductCode());
            ware.setWhsList(wareBO.queryWareList(specsCondition));
            int minSpecsNumber = specsBO.getMinSpecsNumber(product.getCode());
            ware.setAllQuantity(minSpecsNumber * ware.getQuantity());

        }
        return page;
    }

    // 通过用户id查询
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

        Specs psData = specsBO.getSpecs(data.getSpecsCode());
        AgentPrice pspData = agentPriceBO.getPriceByLevel(data.getSpecsCode(),
            agent.getLevel());

        // 检查限购
        if (pspData.getMinNumber() > StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn00000",
                "该产品云仓提货不能少于" + pspData.getMinNumber() + psData.getName());
        }

        // 剩余产品是否充足
        Product product = productBO.getProduct(data.getProductCode());
        if (data.getQuantity() < StringValidater.toInteger(req.getQuantity())) {
            throw new BizException("xn00000", "您仓库中该规格的产品数量不足");
        }

        // 获取授权单
        String kind = EOutOrderKind.Pick_Up.getCode();
        AgentLevel agentLevel = agentLevelBO.getAgentByLevel(agent.getLevel());

        // 是否完成授权单
        if (outOrderBO.checkImpowerOrder(agent.getUserId(),
            agent.getImpowerDatetime())) {
            kind = EOutOrderKind.Impower_Order.getCode();

            // 是否完成升级单
        } else if (sjFormBO.checkIsSj(agent.getUserId())) {
            kind = EOutOrderKind.Upgrade_Order.getCode();
        }

        // 产品不包邮，计算运费
        Long yunfei = 0L;
        if (EBoolean.NO.getCode().equals(product.getIsFree())) {
            SYSConfig sysConfig = sysConfigBO.getConfig(req.getProvince(),
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            yunfei = StringValidater.toLong(sysConfig.getCvalue());
        }

        // 获取团队长
        Agent teamLeader = agentBO.getTeamLeader(agent.getTeamName());
        // 订单归属人
        SYSUser sysUser = sysUserBO.getSYSUser();

        StringBuffer sb = new StringBuffer();

        // 订单拆单
        if (EBoolean.YES.getCode().equals(psData.getIsSingle())) {
            int orderNumber = StringValidater.toInteger(req.getQuantity())
                    / psData.getSingleNumber();

            int quantity = psData.getSingleNumber();
            // 防止下单数量小于拆单数量
            if (StringValidater.toInteger(req.getQuantity())
                    % psData.getSingleNumber() > 0) {
                orderNumber = orderNumber + 1;
            }

            Long amount = 0L;
            for (int i = 0; i < orderNumber; i++) {

                if (i == orderNumber - 1) {
                    if (StringValidater.toInteger(req.getQuantity())
                            % psData.getSingleNumber() > 0) {
                        quantity = StringValidater.toInteger(req.getQuantity())
                                % psData.getSingleNumber();
                    }
                }

                // 防止多出的订单为授权单或升级单
                if (amount > agentLevel.getAmount()) {
                    kind = EOutOrderKind.Pick_Up.getCode();
                }
                amount = amount + psData.getSingleNumber() * data.getPrice();

                String code = outOrderBO.pickUpGoods(data.getProductCode(),
                    data.getProductName(), product.getPic(),
                    data.getSpecsCode(), data.getSpecsName(), quantity,
                    data.getPrice(), quantity * data.getPrice(), yunfei,
                    agent.getHighUserId(), agent, teamLeader.getRealName(),
                    sysUser.getUserId(), sysUser.getRealName(),
                    EBoolean.YES.getCode(), req.getSigner(), req.getMobile(),
                    req.getProvince(), req.getCity(), req.getArea(),
                    req.getAddress(), kind);

                sb.append(code);
                sb.append(",");
            }
        } else {
            String code = outOrderBO.pickUpGoods(data.getProductCode(),
                data.getProductName(), product.getPic(), data.getSpecsCode(),
                data.getSpecsName(),
                StringValidater.toInteger(req.getQuantity()), data.getPrice(),
                StringValidater.toInteger(req.getQuantity()) * data.getPrice(),
                yunfei, agent.getHighUserId(), agent, teamLeader.getRealName(),
                sysUser.getUserId(), sysUser.getRealName(),
                EBoolean.YES.getCode(), req.getSigner(), req.getMobile(),
                req.getProvince(), req.getCity(), req.getArea(),
                req.getAddress(), kind);
            sb.append(code);
        }

        String refNo = null;
        if (sb.lastIndexOf(",") > 0) {
            refNo = sb.substring(0, sb.lastIndexOf(","));
        } else {
            refNo = sb.toString();
        }

        // 减少云仓库存
        wareBO.changeWare(data.getCode(), EWareLogType.OUT.getCode(),
            -StringValidater.toInteger(req.getQuantity()), ESpecsLogType.Order,
            ESpecsLogType.Order.getValue(), refNo);

    }

    @Override
    public Paginable<Ware> queryWareCFrontPage(int start, int limit,
            Ware condition) {
        Paginable<Ware> page = wareBO.getPaginable(start, limit, condition);

        Ware specsCondition = new Ware();
        Product product = null;
        for (Ware ware : page.getList()) {
            product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);
            specsCondition.setUserId(ware.getUserId());
            specsCondition.setProductCode(ware.getProductCode());
            List<Ware> whList = wareBO.queryWareList(specsCondition);
            for (Ware wh : whList) {
                AgentPrice price = agentPriceBO
                    .getPriceByLevel(wh.getSpecsCode(), 6);
                wh.setPrice(price.getPrice());
            }
            ware.setWhsList(whList);
        }
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
            AgentPrice price = agentPriceBO.getPriceByLevel(ware.getSpecsCode(),
                6);
            ware.setPrice(price.getPrice());
        }
        data.setWhsList(specsList);
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        return data;
    }

    @Override
    public XN627805Res checkAmount(String userId) {
        XN627805Res res = new XN627805Res();
        Agent agent = agentBO.getAgent(userId);

        // 代理已通过审核
        if (EAgentStatus.IMPOWERED.getCode().equals(agent.getStatus())) {
            AgentLevel agentLevel = agentLevelBO
                .getAgentByLevel(agent.getLevel());

            Long chargeAmount = agentLevel.getMinCharge();
            Long readAmount = agentLevel.getRedAmount();
            String result = ECheckStatus.NORMAL.getCode();
            String isWareHouse = agentLevel.getIsWare();

            // 检查云仓红线
            Long whAmount = 0L;
            List<Ware> list = wareBO.getWareByUser(userId);
            for (Ware wareHouse : list) {
                if (null != wareHouse.getAmount()) {
                    whAmount = whAmount + wareHouse.getAmount();
                }
            }

            // 检查开启云仓代理的红线，
            if (EBoolean.YES.getCode().equals(agentLevel.getIsWare())) {

                // 是否完成授权单
                if (0 != agentLevel.getAmount() && outOrderBO.checkImpowerOrder(
                    agent.getUserId(), agent.getImpowerDatetime())) {
                    result = ECheckStatus.NO_Impwoer.getCode();
                }

                // 红线设置为零视为无限制
                if (0 < agentLevel.getRedAmount()
                        && whAmount < agentLevel.getRedAmount()) {
                    // 订单金额
                    result = ECheckStatus.RED_LOW.getCode();

                    // 没有过任何订单购买云仓
                } else if (inOrderBO.getInOrderByUser(agent.getUserId(),
                    agent.getImpowerDatetime())) {
                    readAmount = agentLevel.getAmount();
                    result = ECheckStatus.RED_LOW.getCode();
                }

                // 未开启云仓，只检查是否完成授权单
            } else if (0 != agentLevel.getAmount()
                    && outOrderBO.checkImpowerOrder(agent.getUserId(),
                        agent.getImpowerDatetime())) {
                readAmount = agentLevel.getAmount();
                // 未完成授权单
                result = ECheckStatus.RED_LOW.getCode();
            }

            // 检查门槛余额
            Account account = accountBO.getAccountNocheck(agent.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (null != account) {
                // 如果可剩余余额为零，不考虑等于的情况
                if (0 == agentLevel.getMinSurplus()
                        && account.getAmount() > 0) {
                    result = ECheckStatus.MIN_LOW.getCode();
                } else if (0 != agentLevel.getMinSurplus()
                        && account.getAmount() >= agentLevel.getMinSurplus()) {
                    result = ECheckStatus.MIN_LOW.getCode();
                }

            }

            // 是否有过充值
            Long cAmount = 0L;
            List<Charge> charge = chargeBO.getChargeByUser(agent.getUserId(),
                agent.getImpowerDatetime());
            for (Charge charge2 : charge) {
                cAmount = cAmount + charge2.getAmount();
            }
            // 没有过充值，前去充值
            if (CollectionUtils.isEmpty(charge)) {
                result = ECheckStatus.To_Charge.getCode();
                chargeAmount = agentLevel.getMinCharge() - cAmount;

                // 是否有待审核的充值订单
                Charge condition = new Charge();
                condition.setApplyUser(agent.getUserId());
                condition.setStatus(EChargeStatus.TO_Cancel.getCode());
                condition.setApplyDatetimeStart(agent.getImpowerDatetime());
                charge = chargeBO.queryChargeList(condition);
                if (CollectionUtils.isNotEmpty(charge)) {
                    result = ECheckStatus.Charging.getCode();
                }

            }
            res = new XN627805Res(result, readAmount,
                agentLevel.getMinSurplus(), agentLevel.getAmount(),
                chargeAmount, agent.getLevel(), isWareHouse);
        }
        return res;
    }

    @Override
    public void editWare(String code, String quantity, String updater,
            String remark) {
        Ware data = wareBO.getWare(code);
        // 数量为零，删除
        if (EBoolean.NO.getCode().equals(quantity)) {
            wareBO.removeWare(data);
        } else {

        }
    }

}
