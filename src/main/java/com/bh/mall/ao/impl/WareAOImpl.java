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
import com.bh.mall.bo.IAgentLogBO;
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
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentLog;
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
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.EChargeStatus;
import com.bh.mall.enums.ECheckStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EIsImpower;
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

    @Autowired
    IAgentLogBO agentLogBO;

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

        String kind = EOutOrderKind.Pick_Up.getCode();

        // 用户已授权且没有完成授权单，所下订单为授权单
        if (EAgentStatus.IMPOWERED.getCode().equals(agent.getStatus())
                && EIsImpower.NO_Impwoer.getCode()
                    .equals(agent.getIsImpower())) {
            kind = EOutOrderKind.Impower_Order.getCode();

            // 用户已升级且没有完成升级单，所下订单为升级单
        } else if (EAgentStatus.UPGRADED.getCode().equals(agent.getStatus())
                && EIsImpower.NO_Upgrade.getCode()
                    .equals(agent.getIsImpower())) {

            kind = EOutOrderKind.Upgrade_Order.getCode();
        }

        AgentLevel agentLevel = agentLevelBO.getAgentByLevel(agent.getLevel());

        // 产品不包邮，计算运费
        Long allYunfei = 0L;

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
        Long amount = 0L;
        if (EBoolean.YES.getCode().equals(psData.getIsSingle())) {
            int orderNumber = StringValidater.toInteger(req.getQuantity())
                    / psData.getSingleNumber();

            int quantity = psData.getSingleNumber();
            // 防止下单数量小于拆单数量
            if (StringValidater.toInteger(req.getQuantity())
                    % psData.getSingleNumber() > 0) {
                orderNumber = orderNumber + 1;
            }

            for (int i = 0; i < orderNumber; i++) {

                if (i == orderNumber - 1) {
                    if (StringValidater.toInteger(req.getQuantity())
                            % psData.getSingleNumber() > 0) {
                        quantity = StringValidater.toInteger(req.getQuantity())
                                % psData.getSingleNumber();
                    }
                }

                // 防止多出的订单为授权单或升级单
                if (!EOutOrderKind.Pick_Up.getCode().equals(kind)) {
                    if (amount > agentLevel.getAmount()) {
                        kind = EOutOrderKind.Pick_Up.getCode();
                    }
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

                allYunfei = allYunfei + yunfei;
            }
        } else {
            amount = StringValidater.toInteger(req.getQuantity())
                    * data.getPrice();

            String code = outOrderBO.pickUpGoods(data.getProductCode(),
                data.getProductName(), product.getPic(), data.getSpecsCode(),
                data.getSpecsName(),
                StringValidater.toInteger(req.getQuantity()), data.getPrice(),
                amount, yunfei, agent.getHighUserId(), agent,
                teamLeader.getRealName(), sysUser.getUserId(),
                sysUser.getRealName(), EBoolean.YES.getCode(), req.getSigner(),
                req.getMobile(), req.getProvince(), req.getCity(),
                req.getArea(), req.getAddress(), kind);
            sb.append(code);
            allYunfei = yunfei;
        }

        Account yjAccount = accountBO.getAccountByUser(data.getUserId(),
            ECurrency.TX_CNY.getCode());
        if (allYunfei > yjAccount.getAmount()) {
            throw new BizException("xn00000",
                "您的业绩账户余额不足，无法支付" + allYunfei / 1000.0 + "元");
        } else if (0 != allYunfei) {
            accountBO.changeAmount(yjAccount.getAccountNumber(),
                EChannelType.NBZ, null, null, data.getUserId(), EBizType.YUNFEI,
                EBizType.YUNFEI.getValue(), -allYunfei);
        }

        // 减少云仓库存
        wareBO.changeWare(data.getCode(), EWareLogType.OUT.getCode(),
            -StringValidater.toInteger(req.getQuantity()), ESpecsLogType.Order,
            ESpecsLogType.Order.getValue(), data.getUserId());

        // 订单类型为授权单，获取类型为授权单的订单金额与本次下单金额，金额不满足该等级授权单金额是，用户状态为未完成授权单
        String isImpower = EIsImpower.Normal.getCode();
        if (EOutOrderKind.Impower_Order.getCode().equals(kind)) {
            // 获取所有授权单金额
            Long orderAmount = outOrderBO.checkImpowerOrder(agent.getUserId(),
                agent.getImpowerDatetime());
            // 授权单金额不满足授权金额
            long allAmount = orderAmount + amount;
            if (agentLevel.getAmount().longValue() > allAmount) {
                isImpower = EIsImpower.NO_Impwoer.getCode();
            }

            // 订单类型为升级单，获取最后一次升级后类型为升级单的订单金额与本次金额，金额不满足该等级升级单金额时，用户状态为未完成升级单
        } else if (EOutOrderKind.Upgrade_Order.getCode().equals(kind)) {
            AgentLog log = agentLogBO.getAgentLog(agent.getLastAgentLog());
            Long orderAmount = outOrderBO.checkUpgradeOrder(agent.getUserId(),
                log.getApproveDatetime());
            long allAmount = orderAmount + amount;
            if (agentLevel.getAmount().longValue() > allAmount) {
                isImpower = EIsImpower.NO_Upgrade.getCode();
            }
        }
        agentBO.refreshIsImpower(agent, isImpower);
    }

    @Override
    public Paginable<Ware> queryWareCFrontPage(int start, int limit,
            Ware condition) {
        Paginable<Ware> page = wareBO.getPaginable(start, limit, condition);

        // Ware specsCondition = new Ware();
        Product product = null;
        for (Ware ware : page.getList()) {
            product = productBO.getProduct(ware.getProductCode());
            ware.setProduct(product);

            AgentPrice price = agentPriceBO.getPriceByLevel(ware.getSpecsCode(),
                6);
            ware.setPrice(price.getPrice());
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
        if (EAgentStatus.IMPOWERED.getCode().equals(agent.getStatus())
                || EAgentStatus.TO_UPGRADE.getCode().equals(agent.getStatus())
                || EAgentStatus.UPGRADE_COMPANY.getCode()
                    .equals(agent.getStatus())
                || EAgentStatus.UPGRADED.getCode().equals(agent.getStatus())) {

            AgentLevel agentLevel = agentLevelBO
                .getAgentByLevel(agent.getLevel());

            Long chargeAmount = agentLevel.getMinCharge();
            Long redAmount = agentLevel.getRedAmount();
            Long amount = agentLevel.getAmount();
            String result = ECheckStatus.NORMAL.getCode();
            String isWareHouse = agentLevel.getIsWare();

            // 云仓余额
            Long whAmount = 0L;
            List<Ware> list = wareBO.getWareByUser(userId);
            for (Ware wareHouse : list) {
                if (null != wareHouse.getAmount()) {
                    whAmount = whAmount + wareHouse.getAmount();
                }
            }

            // 代理授权单或升级单是否已完成，若已完成状态为正常
            if (EIsImpower.NO_Impwoer.getCode().equals(agent.getIsImpower())) {
                result = ECheckStatus.NO_Impower.getCode();

            } else if (EIsImpower.NO_Upgrade.getCode()
                .equals(agent.getIsImpower())) {
                result = ECheckStatus.NO_Upgrade.getCode();
            }

            AgentLog log = agentLogBO.getAgentLog(agent.getLastAgentLog());

            // 3、判断开启云仓用户买入云仓金额是否满足授权单或升级单的金额
            if (EBoolean.YES.getCode().equals(agentLevel.getIsWare())) {
                if (inOrderBO.getInOrderByUser(agent.getUserId(),
                    log.getApproveDatetime())
                        && whAmount < agentLevel.getAmount()) {
                    redAmount = agentLevel.getAmount();
                    result = ECheckStatus.RED_LOW.getCode();
                }
            }

            // *******************所有状态*************
            // 1、检查红线
            if (EBoolean.YES.getCode().equals(agentLevel.getIsWare())) {
                if (whAmount < agentLevel.getRedAmount()) {
                    redAmount = agentLevel.getRedAmount() - whAmount;
                    result = ECheckStatus.RED_LOW.getCode();
                }
            }

            // 2、 检查门槛可有余额
            Account account = accountBO.getAccountByUser(agent.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (account.getAmount() > agentLevel.getMinSurplus()) {
                result = ECheckStatus.MIN_LOW.getCode();
            }

            // 3、检查是否有过充值或升级后门槛余额是否满足升级单（授权单金额为0，门槛款为0，红线为0，不去检查）
            if (0 != agentLevel.getAmount() || 0 != agentLevel.getMinCharge()
                    || 0 != agentLevel.getRedAmount()) {
                if (EAgentStatus.IMPOWERED.getCode()
                    .equals(agent.getStatus())) {
                    // 获取充值金额
                    Long cAmount = 0L;
                    List<Charge> charge = chargeBO.getChargeByUser(
                        agent.getUserId(), agent.getImpowerDatetime());
                    for (Charge charge2 : charge) {
                        cAmount = cAmount + charge2.getAmount();
                    }

                    // 4、 没有充值过，前去充值
                    if (CollectionUtils.isEmpty(charge)) {
                        result = ECheckStatus.To_Charge.getCode();

                        if (0 == agentLevel.getMinCharge()) {
                            chargeAmount = agentLevel.getAmount();
                        } else if (agentLevel.getAmount() > agentLevel
                            .getMinCharge()) {
                            chargeAmount = agentLevel.getAmount();
                        }

                        // 是否有待审核的充值订单
                        Charge condition = new Charge();
                        condition.setApplyUser(agent.getUserId());
                        condition.setStatus(EChargeStatus.TO_Cancel.getCode());
                        condition
                            .setApplyDatetimeStart(agent.getImpowerDatetime());
                        charge = chargeBO.queryChargeList(condition);
                        if (CollectionUtils.isNotEmpty(charge)) {
                            result = ECheckStatus.Charging.getCode();
                        }
                    }

                    // 5、升级的代理，且未完成升级单检查门槛与云仓余额是否满足升级单
                } else if (EAgentStatus.UPGRADED.getCode()
                    .equals(agent.getStatus())
                        && ECheckStatus.NO_Upgrade.getCode().equals(result)) {
                    if (whAmount < agentLevel.getAmount()) {
                        redAmount = agentLevel.getAmount() - whAmount;
                        result = ECheckStatus.RED_LOW.getCode();
                    }
                }
            }

            res = new XN627805Res(result, redAmount, agentLevel.getMinSurplus(),
                amount, chargeAmount, agent.getLevel(), isWareHouse);
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
            data.setQuantity(StringValidater.toInteger(quantity));
            data.setAmount(
                StringValidater.toInteger(quantity) * data.getPrice());
            wareBO.refreshWare(data);
        }
    }

}
