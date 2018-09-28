package com.bh.mall.ao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAccountAO;
import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.ao.IWeChatAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.ICUserBO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.IDeliveOrderBO;
import com.bh.mall.bo.IInnerProductBO;
import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.IMiniCodeBO;
import com.bh.mall.bo.IOrderReportBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.IProCodeBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.ITjAwardBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.callback.CallbackBzdhConroller;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.domain.CUser;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.ChAward;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.ProCode;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.TjAward;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.dto.res.XN627666Res;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EInOrderStatus;
import com.bh.mall.enums.EIsImpower;
import com.bh.mall.enums.EIsPay;
import com.bh.mall.enums.EOutOrderKind;
import com.bh.mall.enums.EOutOrderStatus;
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.EProductSpecsType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EWareLogType;
import com.bh.mall.exception.BizException;
import com.bh.mall.util.wechat.XMLUtil;

@Service
public class OutOrderAOImpl implements IOutOrderAO {

    private static Logger logger = Logger
        .getLogger(CallbackBzdhConroller.class);

    @Autowired
    IOutOrderBO outOrderBO;

    @Autowired
    ICartBO cartBO;

    @Autowired
    IProductBO productBO;

    @Autowired
    ISpecsBO specsBO;

    @Autowired
    IAgentPriceBO agentPriceBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    ITjAwardBO tjAwardBO;

    @Autowired
    IWareBO wareBO;

    @Autowired
    IWeChatAO weChatAO;

    @Autowired
    ISpecsLogBO specsLogBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IChAwardBO chAwardBO;

    @Autowired
    IProCodeBO proCodeBO;

    @Autowired
    IMiniCodeBO miniCodeBO;

    @Autowired
    ICUserBO cuserBO;

    @Autowired
    ISYSUserBO sysUserBO;

    @Autowired
    IAgentReportBO agentReportBO;

    @Autowired
    IAgentLogBO agentLogBO;

    @Autowired
    ISjFormBO sjFormBO;

    @Autowired
    IOrderReportBO orderReportBO;

    @Autowired
    IDeliveOrderBO deliveOrderBO;

    @Autowired
    IInnerProductBO innerProductBO;

    @Autowired
    IInnerSpecsBO innerSpecsBO;

    @Autowired
    IAccountAO accountAO;

    @Override
    @Transactional
    public List<String> addOutOrder(XN627640Req req) {
        List<String> list = new ArrayList<String>();
        Cart data = cartBO.getCart(req.getCartList().get(0));
        Agent applyUser = agentBO.getAgent(data.getUserId());

        // 团队长
        Agent teamLeader = agentBO.getTeamLeader(applyUser.getTeamName());

        // 获取上级
        String toUserName = null;
        if (agentBO.isHighest(applyUser.getUserId())) {
            SYSUser sysUser = sysUserBO.getSYSUser(applyUser.getHighUserId());
            toUserName = sysUser.getRealName();
        } else {
            Agent highUser = agentBO.getAgent(applyUser.getHighUserId());
            toUserName = highUser.getRealName();
        }

        // 是否为授权单
        AgentLevel agentLevel = agentLevelBO
            .getAgentByLevel(applyUser.getLevel());
        String kind = EOutOrderKind.Normal_Order.getCode();

        Long amount = 0L;
        for (String code : req.getCartList()) {
            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            Specs specs = specsBO.getSpecs(cart.getSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }

            AgentPrice price = agentPriceBO.getPriceByLevel(specs.getCode(),
                applyUser.getLevel());

            // 检查是否可购买
            if (EBoolean.NO.getCode().equals(price.getIsBuy())) {
                throw new BizException("xn0000", "您的等级无法购买该规格的产品");
            }

            // 检查限购
            this.checkLimitNumber(applyUser, specs, price, cart.getQuantity());

            // 检查起购数量
            if (price.getStartNumber() > cart.getQuantity()) {
                throw new BizException("xn0000",
                    "您购买的数量不能低于" + price.getStartNumber() + "]");
            }
            // 检查是否可买
            kind = this.checkOrder(applyUser, specs);

            amount = amount + price.getPrice() * cart.getQuantity();
            // 订单拆单
            if (EBoolean.YES.getCode().equals(specs.getIsSingle())) {

                int nowNumber = cart.getQuantity() / specs.getSingleNumber();
                int singleNumber = specs.getSingleNumber();

                if ((cart.getQuantity() % specs.getSingleNumber()) != 0) {
                    nowNumber = nowNumber + 1;
                }

                for (int i = 0; i < nowNumber; i++) {

                    // 下单数量与查拆单数量不能整除时，最后一单取余
                    if (i == nowNumber - 1) {
                        if ((cart.getQuantity()
                                % specs.getSingleNumber()) != 0) {
                            singleNumber = cart.getQuantity()
                                    % specs.getSingleNumber();
                        }
                    }

                    // 订单金额已满足授权单或升级单，剩余订单为正常单
                    if (!EOutOrderKind.Normal_Order.getCode().equals(kind)
                            && agentLevel.getAmount() <= amount) {
                        kind = EOutOrderKind.Normal_Order.getCode();
                    }

                    list.add(outOrderBO.saveOutOrder(applyUser,
                        applyUser.getHighUserId(), toUserName,
                        teamLeader.getRealName(), pData, specs,
                        price.getPrice(), singleNumber, kind, req));
                }

            } else {
                amount = price.getPrice() * cart.getQuantity();

                // 不可拆单
                list.add(outOrderBO.saveOutOrder(applyUser,
                    applyUser.getHighUserId(), toUserName,
                    teamLeader.getRealName(), pData, specs, price.getPrice(),
                    cart.getQuantity(), kind, req));
            }
            // 删除购物车记录
            cartBO.removeCart(cart);

        }

        // 订单金额不能低于授权单金额
        if (EOutOrderKind.Impower_Order.getCode().equals(kind)) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn00000", agentLevel.getName()
                        + "授权单金额为[" + agentLevel.getAmount() / 1000 + "]元");
            }
        } else if (EOutOrderKind.Upgrade_Order.getCode().equals(kind)) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn00000", agentLevel.getName()
                        + "升级单金额为[" + agentLevel.getAmount() / 1000 + "]元");
            }
        }

        // 检查门槛余额
        this.checkAmount(applyUser, agentLevel, amount);
        return list;
    }

    @Override
    public List<String> addOutOrderC(XN627640Req req) {
        List<String> list = new ArrayList<String>();

        // 下单人及订单归属人
        CUser cUser = cuserBO.getUser(req.getApplyUser());
        Agent agent = agentBO.getAgent(req.getToUser());

        for (String code : req.getCartList()) {
            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            Specs specs = specsBO.getSpecs(cart.getSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }

            AgentPrice price = agentPriceBO.getPriceByLevel(specs.getCode(), 6);
            // 订单拆单
            if (EBoolean.YES.getCode().equals(specs.getIsSingle())) {
                int nowNumber = cart.getQuantity() / specs.getSingleNumber();
                int singleNumber = specs.getSingleNumber();

                if ((cart.getQuantity() % specs.getSingleNumber()) != 0) {
                    nowNumber = nowNumber + 1;
                }

                for (int i = 0; i < nowNumber; i++) {

                    // 下单数量与查拆单数量不能整除时，最后一单取余
                    if (i == nowNumber - 1) {
                        if ((cart.getQuantity()
                                % specs.getSingleNumber()) != 0) {
                            singleNumber = cart.getQuantity()
                                    % specs.getSingleNumber();
                        }
                    }

                    list.add(outOrderBO.saveOutOrder(cUser, agent.getUserId(),
                        agent.getRealName(), pData, specs, price.getPrice(),
                        singleNumber, req, EOutOrderKind.C_ORDER.getCode()));
                }
            } else {
                // 不可拆单
                list.add(outOrderBO.saveOutOrder(cUser, agent.getUserId(),
                    agent.getRealName(), pData, specs, price.getPrice(),
                    cart.getQuantity(), req, EOutOrderKind.C_ORDER.getCode()));
            }
            // 删除购物车记录
            cartBO.removeCart(cart);
        }
        return list;
    }

    @Override
    @Transactional
    public List<String> addOutOrderNoCart(XN627641Req req) {
        List<String> list = new ArrayList<String>();
        Agent applyUser = agentBO.getAgent(req.getApplyUser());
        Specs specs = specsBO.getSpecs(req.getSpecsCode());
        Product pData = productBO.getProduct(specs.getProductCode());

        if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
            throw new BizException("xn0000", "产品包含未上架商品,不能下单");
        }
        // 团队长
        Agent teamLeader = agentBO.getTeamLeader(applyUser.getTeamName());

        // 获取上级
        String toUserName = null;
        if (agentBO.isHighest(applyUser.getUserId())) {
            SYSUser sysUser = sysUserBO.getSYSUser(applyUser.getHighUserId());
            toUserName = sysUser.getRealName();
        } else {
            Agent highUser = agentBO.getAgent(applyUser.getHighUserId());
            toUserName = highUser.getRealName();
        }

        // 该等级对应的规则及价格
        AgentLevel agentLevel = agentLevelBO
            .getAgentByLevel(applyUser.getLevel());
        AgentPrice price = agentPriceBO.getPriceByLevel(specs.getCode(),
            applyUser.getLevel());

        Long amount = StringValidater.toInteger(req.getQuantity())
                * price.getPrice();

        // 判断是否可购买
        String kind = this.checkOrder(applyUser, specs);

        if (EOutOrderKind.Impower_Order.getCode().equals(kind)) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn00000", agentLevel.getName()
                        + "授权单金额为[" + agentLevel.getAmount() / 1000 + "]元");
            }
        } else if (EOutOrderKind.Upgrade_Order.getCode().equals(kind)) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn00000", agentLevel.getName()
                        + "升级单金额为[" + agentLevel.getAmount() / 1000 + "]元");
            }
        }

        // 门槛余额是否高于限制
        this.checkAmount(applyUser, agentLevel, amount);

        // 检查限购
        this.checkLimitNumber(applyUser, specs, price,
            StringValidater.toInteger(req.getQuantity()));

        // 检查起购数量
        if (price.getStartNumber() > StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn0000",
                "您购买的数量不能低于" + price.getStartNumber() + "]");
        }

        // 订单拆单
        if (EBoolean.YES.getCode().equals(specs.getIsSingle())) {

            int nowNumber = StringValidater.toInteger(req.getQuantity())
                    / specs.getSingleNumber();
            int singleNumber = specs.getSingleNumber();

            if ((StringValidater.toInteger(req.getQuantity())
                    % specs.getSingleNumber()) != 0) {
                nowNumber = nowNumber + 1;
            }

            for (int i = 0; i < nowNumber; i++) {

                // 下单数量与查拆单数量不能整除时，最后一单取余
                if (i == nowNumber - 1) {
                    if ((StringValidater.toInteger(req.getQuantity())
                            % specs.getSingleNumber()) != 0) {
                        singleNumber = StringValidater.toInteger(
                            req.getQuantity()) % specs.getSingleNumber();
                    }
                }

                list.add(outOrderBO.saveOutOrder(applyUser,
                    applyUser.getHighUserId(), toUserName,
                    teamLeader.getRealName(), pData, specs, price.getPrice(),
                    singleNumber, kind, req));
            }
        } else {
            // 不可拆单
            list.add(outOrderBO.saveOutOrder(applyUser,
                applyUser.getHighUserId(), toUserName, teamLeader.getRealName(),
                pData, specs, price.getPrice(),
                StringValidater.toInteger(req.getQuantity()), kind, req));
        }
        return list;
    }

    @Override
    @Transactional
    public List<String> addOutOrderNoCartC(XN627641Req req) {
        List<String> list = new ArrayList<String>();
        // 下单人及订单归属人
        CUser cUser = cuserBO.getUser(req.getApplyUser());
        Agent agent = agentBO.getAgent(req.getToUser());

        Specs specs = specsBO.getSpecs(req.getSpecsCode());
        Product pData = productBO.getProduct(specs.getProductCode());

        if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
            throw new BizException("xn0000", "产品包含未上架商品,不能下单");
        }
        // 该等级对应的规则及价格
        AgentPrice price = agentPriceBO.getPriceByLevel(specs.getCode(), 6);

        // 订单拆单
        if (EBoolean.YES.getCode().equals(specs.getIsSingle())) {
            int nowNumber = StringValidater.toInteger(req.getQuantity())
                    / specs.getSingleNumber();
            int singleNumber = specs.getSingleNumber();

            if ((StringValidater.toInteger(req.getQuantity())
                    % specs.getSingleNumber()) != 0) {
                nowNumber = nowNumber + 1;
            }

            for (int i = 0; i < nowNumber; i++) {

                // 下单数量与查拆单数量不能整除时，最后一单取余
                if (i == nowNumber - 1) {
                    if ((StringValidater.toInteger(req.getQuantity())
                            % specs.getSingleNumber()) != 0) {
                        singleNumber = StringValidater.toInteger(
                            req.getQuantity()) % specs.getSingleNumber();
                    }
                }

                list.add(outOrderBO.saveOutOrder(cUser, agent.getUserId(),
                    agent.getRealName(), pData, specs, price.getPrice(),
                    singleNumber, req, EOutOrderKind.C_ORDER.getCode()));
            }
        } else {
            // 不可拆单
            list.add(outOrderBO.saveOutOrder(cUser, agent.getUserId(),
                agent.getRealName(), pData, specs, price.getPrice(),
                StringValidater.toInteger(req.getQuantity()), req,
                EOutOrderKind.C_ORDER.getCode()));
        }
        return list;
    }

    @Override
    @Transactional
    public Object payOutOrder(List<String> codeList, String payType) {
        Object result = null;
        OutOrder outOrder = outOrderBO.getOutOrder(codeList.get(0));

        Long amount = 0L;
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());

        // 未开启云仓账户的代理用门槛款支付
        for (String code : codeList) {
            OutOrder data = outOrderBO.getOutOrder(code);
            if (!EOutOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单未处于待支付状态");
            }
            // 账户扣钱
            String status = EOutOrderStatus.TO_SEND.getCode();
            if (EPayType.RMB_YE.getCode().equals(payType)) {
                data.setPayDatetime(new Date());
                data.setPayCode(data.getCode());
                data.setPayAmount(data.getAmount() + data.getYunfei());
                data.setPayGroup(payGroup);
                data.setStatus(status);
                outOrderBO.paySuccess(data);
                result = new BooleanRes(true);

                // 统计出货
                orderReportBO.saveOutOrderReport(data);
            } else if (EOutOrderKind.C_ORDER.getCode()
                .equals(outOrder.getKind())) {
                data.setPayGroup(payGroup);
                outOrderBO.addPayGroup(data, payGroup,
                    EChannelType.WeChat_XCX.getCode());
            } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
                data.setPayGroup(payGroup);
                outOrderBO.addPayGroup(data, payGroup,
                    EChannelType.WeChat_H5.getCode());
            }

            amount = amount + data.getAmount() + data.getYunfei();
        }

        // C端下单支付
        if (EOutOrderKind.C_ORDER.getCode().equals(outOrder.getKind())) {
            result = this.payWXH5(outOrder.getApplyUser(), payGroup, payGroup,
                amount, EChannelType.WeChat_XCX.getCode(),
                PropertiesUtil.Config.WECHAT_XCX_ORDER_BACKURL);

        } else if (EPayType.RMB_YE.getCode().equals(payType)) {
            Account mkAccount = accountBO.getAccountByUser(
                outOrder.getApplyUser(), ECurrency.MK_CNY.getCode());
            accountBO.changeAmount(mkAccount.getAccountNumber(),
                EChannelType.NBZ, null, payGroup, payGroup, EBizType.AJ_GMYC,
                EBizType.AJ_GMYC.getValue(), -amount);

            // 更新用户状态
            Agent agent = agentBO.getAgent(outOrder.getApplyUser());
            if (!EIsImpower.Normal.getCode().equals(agent.getIsImpower())) {
                agentBO.refreshIsImpower(agent, EIsImpower.Normal.getCode());
            }
        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
            result = this.payWXH5(outOrder.getApplyUser(), payGroup, payGroup,
                amount, EChannelType.WeChat_H5.getCode(),
                PropertiesUtil.Config.WECHAT_H5_OUT_ORDER_BACKURL);

        }
        return result;
    }

    private Object payWXH5(String applyUser, String payGroup, String refNo,
            Long amount, String payType, String callBackUrl) {
        return weChatAO.getPrepayIdH5(applyUser, payGroup, refNo.toString(),
            EBizType.AJ_GMCP.getCode(), EBizType.AJ_GMCP.getValue(), amount,
            callBackUrl, payType);
    }

    @Override
    @Transactional
    public void paySuccess(String result) {
        Map<String, String> map = null;
        try {
            logger.info("========出货订单回调信息=======");
            map = XMLUtil.doXMLParse(result);

            String wechatOrderNo = map.get("transaction_id");
            String outTradeNo = map.get("out_trade_no");

            List<OutOrder> list = outOrderBO.getOutOrderByPayGroup(outTradeNo);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException("xn00000", "出货订单不存在");
            }
            OutOrder outOrder = list.get(0);

            // 此处调用订单查询接口验证是否交易成功
            boolean isSucc = weChatAO.reqOrderquery(map, outOrder.getPayType());
            if (isSucc) {

                Long amount = 0L;

                for (OutOrder data : list) {
                    if (!EInOrderStatus.Unpaid.getCode()
                        .equals(data.getStatus())) {
                        throw new BizException("xn0000", "订单已支付");
                    }

                    data.setPayCode(wechatOrderNo);
                    data.setStatus(EOutOrderStatus.TO_SEND.getCode());
                    outOrderBO.paySuccess(data);

                    amount = amount + data.getAmount();
                }

                String currency = ECurrency.C_CNY.getCode();
                accountBO.changeAmount(ESysUser.TG_BH.getCode(),
                    EChannelType.getEChannelType(outOrder.getPayType()),
                    wechatOrderNo, outOrder.getPayGroup(),
                    outOrder.getPayGroup(), EBizType.AJ_GMYC,
                    EBizType.AJ_GMYC.getValue(), amount);
                if (!EOutOrderKind.C_ORDER.getCode()
                    .equals(outOrder.getKind())) {
                    accountAO.doBizCallBack(outOrder.getApplyUser(),
                        outOrder.getPayCode(), outOrder.getPayGroup(),
                        EBizType.AJ_XJCZ.getCode(), amount,
                        ECurrency.TX_CNY.getCode());

                    currency = ECurrency.TX_CNY.getCode();
                }

                Account account = accountBO
                    .getAccountByUser(outOrder.getToUserId(), currency);
                // 收款方账户价钱
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.getEChannelType(outOrder.getPayType()),
                    wechatOrderNo, outOrder.getPayGroup(),
                    outOrder.getPayGroup(), EBizType.AJ_GMYC,
                    EBizType.AJ_GMYC.getValue(), amount);

                Agent agent = agentBO.getAgent(outOrder.getApplyUser());
                agentBO.refreshIsImpower(agent, EIsImpower.Normal.getCode());

            } else {
                for (OutOrder data : list) {
                    data.setPayType(EChannelType.WeChat_H5.getCode());
                    data.setStatus(EOutOrderStatus.PAY_FAIL.getCode());
                    data.setPayDatetime(new Date());
                    outOrderBO.payNo(data);
                }
            }

        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }
    }

    @Override
    public Paginable<OutOrder> queryOutOrderPage(int start, int limit,
            OutOrder condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<OutOrder> page = outOrderBO.getPaginable(start, limit,
            condition);

        for (OutOrder data : page.getList()) {

            if (!EOutOrderKind.C_ORDER.getCode().equals(data.getKind())) {
                Agent agent = agentBO.getAgent(data.getApplyUser());
                data.setAgent(agent);
                if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
                    .getLevel()
                        && StringUtils.isNotBlank(data.getHighUserId())) {
                    SYSUser sysUser = sysUserBO
                        .getSYSUser(data.getHighUserId());
                    data.setHighUserName(sysUser.getRealName());
                } else if (StringUtils.isNotBlank(data.getHighUserId())) {
                    Agent highAgent = agentBO.getAgent(data.getHighUserId());
                    data.setHighUserName(highAgent.getRealName());
                }
            }

            // 产品信息
            Product product = productBO.getProduct(data.getProductCode());
            data.setProduct(product);
            data.setPic(product.getAdvPic());

            // 发货人转义
            if (StringUtils.isNotBlank(data.getDeliver())) {
                if (EBoolean.YES.getCode().equals(data.getIsWareSend())) {
                    SYSUser sysUser = sysUserBO.getSYSUser(data.getDeliver());
                    data.setHighUserName(sysUser.getRealName());
                } else {
                    Agent highAgent = agentBO.getAgent(data.getDeliver());
                    data.setHighUserName(highAgent.getRealName());
                }
            }

        }
        return page;
    }

    @Override
    public List<OutOrder> queryOutOrderList(OutOrder condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        // 获取开放云仓的等级
        List<Integer> levelList = new ArrayList<Integer>();
        List<AgentLevel> agentList = agentLevelBO.getAgentHaveWH();
        for (AgentLevel agent : agentList) {
            levelList.add(agent.getLevel());
        }
        condition.setLevelList(levelList);

        List<OutOrder> list = outOrderBO.queryOutOrderList(condition);
        for (OutOrder data : list) {
            // 下单人

            if (!EOutOrderKind.C_ORDER.getCode().equals(data.getKind())) {
                if (agentBO.isHighest(data.getApplyUser())) {
                    Agent agent = agentBO.getAgent(data.getApplyUser());
                    SYSUser sysUser = sysUserBO
                        .getSYSUser(agent.getHighUserId());
                    data.setHighUserName(sysUser.getRealName());
                } else {
                    Agent agent = agentBO.getAgent(data.getApplyUser());
                    data.setAgent(agent);
                    data.setHighUserName(agent.getRealName());
                }
            }

            // 产品信息
            Product product = productBO.getProduct(data.getProductCode());
            data.setProduct(product);

            // 发货人转义
            if (StringUtils.isNotBlank(data.getDeliver())) {
                if (EBoolean.YES.getCode().equals(data.getIsWareSend())) {
                    SYSUser sysUser = sysUserBO.getSYSUser(data.getDeliver());
                    data.setDeliveName(sysUser.getRealName());
                } else {
                    Agent deliveAgent = agentBO.getAgent(data.getDeliver());
                    data.setDeliveName(deliveAgent.getRealName());
                }
            }
        }
        return list;
    }

    @Override
    public OutOrder getOutOrder(String code) {
        OutOrder data = outOrderBO.getOutOrder(code);
        // 下单人

        if (!EOutOrderKind.C_ORDER.getCode().equals(data.getKind())) {
            if (agentBO.isHighest(data.getApplyUser())) {
                Agent agent = agentBO.getAgent(data.getApplyUser());
                SYSUser sysUser = sysUserBO.getSYSUser(agent.getHighUserId());
                data.setHighUserName(sysUser.getRealName());
            } else {
                Agent agent = agentBO.getAgent(data.getApplyUser());
                data.setAgent(agent);
                data.setHighUserName(agent.getRealName());
            }
        }

        // 产品信息
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);

        // 发货人转义
        if (StringUtils.isNotBlank(data.getDeliver())) {
            if (EBoolean.YES.getCode().equals(data.getIsWareSend())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getDeliver());
                data.setDeliveName(sysUser.getRealName());
            } else {
                Agent deliveAgent = agentBO.getAgent(data.getDeliver());
                data.setDeliveName(deliveAgent.getRealName());
            }
        }
        return data;
    }

    @Override
    public void editOutOrder(XN627643Req req) {
        OutOrder data = outOrderBO.getOutOrder(req.getCode());
        if (EOutOrderStatus.TO_SEND.getCode().equals(data.getStatus())
                || EOutOrderStatus.TO_RECEIVE.getCode().equals(data.getStatus())
                || EOutOrderStatus.RECEIVED.getCode()
                    .equals(data.getStatus())) {
            throw new BizException("xn00000", "订单无法修改");
        }
        data.setSigner(req.getSigner());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setAddress(req.getAddress());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setUpdateNote(req.getUpdateNote());

        // 产品不包邮，计算运费
        Product product = productBO.getProduct(data.getProductCode());
        if (EBoolean.NO.getCode().equals(product.getIsFree())) {

            Account txAccount = accountBO.getAccountByUser(data.getApplyUser(),
                ECurrency.MK_CNY.getCode());
            SYSConfig sysConfig = sysConfigBO.getConfig(data.getProvince(),
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            Long yunfei = StringValidater.toLong(sysConfig.getCvalue());

            // 运费增加
            if (data.getYunfei() < yunfei) {
                if (txAccount.getAmount() < (yunfei - data.getYunfei())) {
                    throw new BizException("xn00000",
                        "代理" + txAccount.getRealName() + "可提账户中余额不足以支付"
                                + yunfei / 1000.0 + "元的运费");
                }
                accountBO.changeAmount(txAccount.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getApplyUser(),
                    EBizType.YUNFEI, EBizType.YUNFEI.getValue(),
                    yunfei - data.getYunfei());

                // 运费减少
            } else {
                accountBO.changeAmount(txAccount.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getApplyUser(),
                    EBizType.YUNFEI, EBizType.YUNFEI.getValue(),
                    data.getYunfei() - yunfei);
            }

            data.setYunfei(yunfei);
            data.setPayAmount(data.getPayAmount() + yunfei);
        }
        outOrderBO.refreshOutOrder(data);
    }

    private void payAward(OutOrder data) {
        Agent applyUser = agentBO.getAgent(data.getApplyUser());
        Long orderAmount = data.getAmount();

        // 计算差额利润
        AgentReport report = null;
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != applyUser
            .getLevel()) {
            AgentPrice price = agentPriceBO.getPriceByLevel(data.getSpecsCode(),
                applyUser.getLevel());
            Agent toUser = agentBO.getAgent(data.getToUserId());
            AgentPrice highPrice = agentPriceBO
                .getPriceByLevel(data.getSpecsCode(), toUser.getLevel());
            Long profit = (price.getPrice() - highPrice.getPrice())
                    * data.getQuantity();

            if (profit > 0) {

                // 统计差额利润
                report = agentReportBO
                    .getAgentReportByUser(applyUser.getHighUserId());
                report.setProfitAward(report.getProfitAward() + profit);
                agentReportBO.refreshAward(report);
            }
        }

        //
        // **********推荐奖**********
        // 是否有推荐人
        if (this.checkAward(applyUser)) {
            if (StringUtils.isNotBlank(applyUser.getReferrer())) {
                // 直接推荐人
                Agent firstReferee = agentBO.getAgent(applyUser.getReferrer());
                TjAward tjAward = tjAwardBO.getAwardByLevel(
                    applyUser.getLevel(), data.getProductCode());

                Long amount = 0L;
                String fromUserId = ESysUser.SYS_USER_BH.getCode();

                if (StringValidater.toInteger(
                    EAgentLevel.ONE.getCode()) != applyUser.getLevel()) {
                    fromUserId = applyUser.getHighUserId();
                }
                // 直接推荐奖
                if (null != firstReferee) {
                    amount = AmountUtil.mul(orderAmount,
                        tjAward.getValue1() / 100);
                    accountBO.transAmountCZB(fromUserId,
                        ECurrency.TX_CNY.getCode(), firstReferee.getUserId(),
                        ECurrency.TX_CNY.getCode(), amount, EBizType.AJ_TJJL_IN,
                        EBizType.AJ_TJJL_IN.getValue(),
                        EBizType.AJ_TJJL_IN.getValue(), data.getPayGroup());

                    // 统计推荐奖
                    report = agentReportBO
                        .getAgentReportByUser(firstReferee.getUserId());
                    report.setRefreeAward(report.getRefreeAward() + amount);
                    agentReportBO.refreshAward(report);

                    // 间接推荐奖
                    if (StringUtils.isNotBlank(firstReferee.getReferrer())) {
                        Agent secondReferee = agentBO
                            .getAgent(firstReferee.getReferrer());
                        amount = AmountUtil.mul(orderAmount,
                            tjAward.getValue2() / 100);
                        accountBO.transAmountCZB(fromUserId,
                            ECurrency.TX_CNY.getCode(),
                            secondReferee.getUserId(),
                            ECurrency.TX_CNY.getCode(), amount,
                            EBizType.AJ_TJJL_IN, EBizType.AJ_TJJL_IN.getValue(),
                            EBizType.AJ_TJJL_IN.getValue(), data.getPayGroup());

                        // 统计推荐奖
                        report = agentReportBO
                            .getAgentReportByUser(secondReferee.getUserId());
                        report.setRefreeAward(report.getRefreeAward() + amount);
                        agentReportBO.refreshAward(report);
                        // 次推荐奖
                        if (StringUtils
                            .isNotBlank(secondReferee.getReferrer())) {
                            Agent thirdReferee = agentBO
                                .getAgent(secondReferee.getReferrer());
                            amount = AmountUtil.mul(orderAmount,
                                tjAward.getValue3() / 100);
                            accountBO.transAmountCZB(fromUserId,
                                ECurrency.TX_CNY.getCode(),
                                thirdReferee.getUserId(),
                                ECurrency.TX_CNY.getCode(), amount,
                                EBizType.AJ_TJJL_IN,
                                EBizType.AJ_TJJL_IN.getValue(),
                                EBizType.AJ_TJJL_IN.getValue(),
                                data.getPayGroup());

                            // 统计推荐奖
                            report = agentReportBO
                                .getAgentReportByUser(thirdReferee.getUserId());
                            report.setRefreeAward(
                                report.getRefreeAward() + amount);
                            agentReportBO.refreshAward(report);
                        }
                    }
                }
            }

        }
    }

    @Override
    @Transactional
    public void deliverOutOrder(OutOrder data, String proCode, String deliver,
            String logisticsCode, String logisticsCompany, String remark) {

        if (!EOutOrderStatus.TO_SEND.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该订单不是待发货的订单");
        }

        // 订单与箱码关联（整箱发货）
        if (StringUtils.isNotBlank(proCode)) {
            data.setProCode(proCode);
            // 修改箱码状态
            ProCode proData = proCodeBO.getProCode(proCode);
            if (ECodeStatus.USE_YES.getCode().equals(proData.getStatus())) {
                throw new BizException("xn00000", "该箱码已经使用过");
            }
            if (ECodeStatus.SPLIT_SINGLE.getCode()
                .equals(proData.getStatus())) {
                throw new BizException("xn00000", "该箱码已拆分");
            }
            proData.setStatus(ECodeStatus.USE_YES.getCode());
            proData.setUseDatetime(new Date());
            proCodeBO.refreshProCode(proData);

            // 更新箱码关联的盒码与订单编号
            miniCodeBO.refreshStatusByProCode(proData.getCode(),
                data.getCode());
        }
        Agent agent = agentBO.getAgent(data.getApplyUser());
        AgentLevel agentLevel = agentLevelBO.getAgentByLevel(agent.getLevel());
        // 没有开启云仓的发放奖励
        if (EBoolean.NO.getCode().equals(agentLevel.getIsWare())) {
            // 出货以及推荐奖励
            this.payAward(data);
        }

        outOrderBO.deliverOutOrder(data, EOutOrderStatus.TO_RECEIVE.getCode(),
            deliver, logisticsCode, logisticsCompany, new Date(), remark);

    }

    @Override
    public void approveOutOrder(List<String> codeList, String approver,
            String approveNote) {
        for (String code : codeList) {
            OutOrder data = outOrderBO.getOutOrder(code);
            if (!EOutOrderStatus.TO_APPROVE.getCode()
                .equals(data.getStatus())) {
                throw new BizException("xn00000", "该订单不处于待审核状态");
            }
            data.setStatus(EOutOrderStatus.TO_SEND.getCode());
            data.setApprover(approver);
            data.setApproveDatetime(new Date());
            data.setApproveNote(approveNote);
            data.setIsPay(EIsPay.PAY_NO.getCode());
            outOrderBO.approveOutOrder(data);

            // 添加至发货的字段
            deliveOrderBO.saveDeliveOrder(data);
            // 统计出货
            orderReportBO.saveOutOrderReport(data);
        }

    }

    @Override
    public void cancelOutOrder(String code) {
        OutOrder data = outOrderBO.getOutOrder(code);

        // 订单已申请取消或已取消
        if (EOutOrderStatus.TO_CANECL.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已申请取消喽，请勿重复申请！");
        }
        // 非待支付和待审单的订单无法取消
        if (!(EOutOrderStatus.Unpaid.getCode().equals(data.getStatus())
                || EOutOrderStatus.TO_APPROVE.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn00000", "订单已发货或已收货，无法申请取消");
        }

        // 授权单/升级单无法取消
        if (EOutOrderKind.Impower_Order.getCode().equals(data.getKind())) {
            throw new BizException("xn00000", "授权单无法取消哦！");
        }

        if (EOutOrderKind.Upgrade_Order.getCode().equals(data.getKind())) {
            throw new BizException("xn00000", "授权单无法取消哦！");
        }

        data.setStatus(EOutOrderStatus.TO_CANECL.getCode());
        outOrderBO.cancelOutOrder(data);
    }

    @Override
    public void approveCancel(String code, String result, String updater,
            String remark) {
        OutOrder data = outOrderBO.getOutOrder(code);
        if (!EOutOrderStatus.TO_CANECL.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该订单未申请取消");
        }

        // 非云仓发货初始状态
        String status = EOutOrderStatus.TO_APPROVE.getCode();
        // 初始状态是未支付
        if (!EBoolean.YES.getCode().equals(data.getIsWareSend())
                && null == data.getPayAmount()) {
            status = EOutOrderStatus.Unpaid.getCode();
        } else {
            status = EOutOrderStatus.TO_APPROVE.getCode();
        }

        if (EResult.Result_YES.getCode().equals(result)) {
            status = EOutOrderStatus.CANCELED.getCode();

            // 没开启云仓用户取消，归还门槛款
            if (!(EBoolean.YES.getCode().equals(data.getIsWareSend())
                    || EOutOrderKind.C_ORDER.getCode()
                        .equals(data.getKind()))) {
                if (null != data.getPayAmount()) {
                    Account account = accountBO.getAccountByUser(
                        data.getApplyUser(), ECurrency.MK_CNY.getCode());
                    accountBO.changeAmount(account.getAccountNumber(),
                        EChannelType.NBZ, null, null, data.getCode(),
                        EBizType.AJ_GMCP_TK, EBizType.AJ_GMCP_TK.getValue(),
                        data.getPayAmount());
                }
            } else if (EBoolean.YES.getCode().equals(data.getIsWareSend())) {
                // 云仓提货归还库存
                Agent agent = agentBO.getAgent(data.getApplyUser());
                wareBO.buyWare(data.getCode(), data.getProductCode(),
                    data.getProductName(), data.getSpecsCode(),
                    data.getSpecsName(), data.getQuantity(), data.getPrice(),
                    agent, EWareLogType.QXDD,
                    "购买产品：[" + data.getProductName() + "]");
            }
        }

        data.setStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        outOrderBO.approveCancel(data);

    }

    @Override
    public void receivedOutOrder(String code) {
        OutOrder data = outOrderBO.getOutOrder(code);
        data.setStatus(EOutOrderStatus.RECEIVED.getCode());
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        outOrderBO.receivedOutOrder(data);
    }

    private String checkOrder(Agent applyUser, Specs specs) {
        String kind = EOutOrderKind.Normal_Order.getCode();

        // 是否完成授权单
        if (EIsImpower.NO_Impwoer.getCode().equals(applyUser.getIsImpower())) {
            kind = EOutOrderKind.Impower_Order.getCode();
            if (EProductSpecsType.Apply_NO.getCode()
                .equals(specs.getIsSqOrder())) {
                throw new BizException("xn0000", "该产品规格不允许授权单下单");
            }

        } else if (EIsImpower.NO_Upgrade.getCode()
            .equals(applyUser.getIsImpower())) {
            kind = EOutOrderKind.Upgrade_Order.getCode();
            if (EProductSpecsType.Apply_NO.getCode()
                .equals(specs.getIsSjOrder())) {
                throw new BizException("xn0000", "该产品规格不允许升级单下单");
            }

        } else if (EProductSpecsType.Apply_NO.getCode()
            .equals(specs.getIsNormalOrder())) {
            throw new BizException("xn0000", "该产品规格不允许普通单下单");
        }

        return kind;

    }

    // 检查介绍奖与推荐奖是否同时存在
    private boolean checkAward(Agent agent) {
        // 介绍人与推荐人同时存在
        if (StringUtils.isNotBlank(agent.getIntroducer())
                && StringUtils.isNotBlank(agent.getReferrer())) {
            // 下单金额是否超过授权金额
            List<String> statusList = new ArrayList<String>();
            statusList.add(EOutOrderStatus.TO_APPROVE.getCode());
            statusList.add(EOutOrderStatus.TO_SEND.getCode());
            statusList.add(EOutOrderStatus.TO_RECEIVE.getCode());
            statusList.add(EOutOrderStatus.RECEIVED.getCode());

            OutOrder condition = new OutOrder();
            condition.setApplyUser(agent.getUserId());
            condition.setStatusList(statusList);

            List<OutOrder> list = outOrderBO.queryOutOrderList(condition);
            Long amount = 0L;
            for (OutOrder OutOrder : list) {
                amount = amount + OutOrder.getAmount();
            }

            AgentLevel impower = agentLevelBO.getAgentByLevel(agent.getLevel());

            if (impower.getMinCharge() >= amount) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1、开启云仓的代理订单作废，回退代理云仓库存
     * 2、未开启云仓代理，由云仓代发的订单，归还下单人的门槛余额
     * 
     */
    @Override
    @Transactional
    public void invalidOutOrder(String code, String updater, String remark) {

        OutOrder data = outOrderBO.getOutOrder(code);
        // 非待支付、支付失败、未审核订单无法作废
        if (!(EOutOrderStatus.Unpaid.getCode().equals(data.getStatus())
                || EOutOrderStatus.TO_APPROVE.getCode().equals(data.getStatus())
                || EOutOrderStatus.TO_SEND.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn00000", "该订单无法作废");
        }

        // 云仓提货订单归还库存
        if (EBoolean.YES.getCode().equals(data.getIsWareSend()) && data
            .getToUserId().equals(sysUserBO.getSYSUser().getUserId())) {

            Agent agent = agentBO.getAgent(data.getApplyUser());
            wareBO.buyWare(data.getCode(), data.getProductCode(),
                data.getProductName(), data.getSpecsCode(), data.getSpecsName(),
                data.getQuantity(), data.getPrice(), agent, EWareLogType.QXDD,
                "取消订单");

            // 运费不为零时退还运费
            if (null != data.getYunfei() && 0 > data.getYunfei().longValue()) {
                Account account = accountBO.getAccountByUser(
                    data.getApplyUser(), ECurrency.TX_CNY.getCode());
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getCode(),
                    EBizType.AJ_GMCP_TK, EBizType.AJ_GMCP_TK.getCode(),
                    data.getYunfei());
            }
        } else if (!EOutOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
            Account account = accountBO.getAccountByUser(data.getApplyUser(),
                ECurrency.MK_CNY.getCode());
            accountBO.changeAmount(account.getAccountNumber(), EChannelType.NBZ,
                null, null, data.getCode(), EBizType.AJ_GMCP_TK,
                EBizType.AJ_GMCP_TK.getCode(),
                data.getAmount() + data.getYunfei());
        }

        outOrderBO.invalidOutOrder(data, updater, remark);

    }

    @Override
    public void checkLimitNumber(Agent agent, Specs specs, AgentPrice price,
            Integer quantity) {

        // 今日已购数量
        int number = getNumber(agent.getUserId(), specs.getCode(),
            DateUtil.getTodayStart(), DateUtil.getTodayEnd()) + quantity;
        // 日限购
        if (0 != price.getDailyNumber() && number > price.getDailyNumber()) {
            throw new BizException("xn00000",
                "您今日的购买数量不能多于[" + price.getDailyNumber() + "]");
        }

        // 本周已购数量
        number = getNumber(agent.getUserId(), specs.getCode(),
            DateUtil.getWeeklyStart(), DateUtil.getWeeklyEnd()) + quantity;
        // 周限购
        if (0 != price.getWeeklyNumber() && number > price.getWeeklyNumber()) {
            throw new BizException("xn00000",
                "您今日的购买数量不能多于[" + price.getWeeklyNumber() + "]");
        }

        // 本月已购数量
        number = getNumber(agent.getUserId(), specs.getCode(),
            DateUtil.getMonthStart(), DateUtil.getMonthEnd()) + quantity;
        // 月限购
        if (0 != price.getMonthlyNumber()
                && number > price.getMonthlyNumber()) {
            throw new BizException("xn00000",
                "您今日的购买数量不能多于[" + price.getMonthlyNumber() + "]");
        }
    }

    // 日、周、月已购数量
    private int getNumber(String agentId, String specsCode, Date startDatetime,
            Date endDatetime) {
        int number = 0;
        List<OutOrder> list = outOrderBO.getProductQuantity(agentId,
            startDatetime, endDatetime);
        for (OutOrder outOrder : list) {
            number = number + outOrder.getQuantity();
        }
        return number;
    }

    private void checkAmount(Agent agent, AgentLevel agentLevel, Long amount) {
        // 门槛账户
        Account account = accountBO.getAccountByUser(agent.getUserId(),
            ECurrency.MK_CNY.getCode());
        // 检查门槛余额
        Long restAmount = account.getAmount() - amount;
        if (0 == agentLevel.getMinSurplus()) {
            if (restAmount > agentLevel.getMinSurplus()) {
                throw new BizException("xn0000",
                    "剩余门槛不能大于[" + agentLevel.getMinSurplus() / 1000
                            + "]元，目前余额还有[" + restAmount / 1000 + "]元");
            }

        } else if (restAmount >= agentLevel.getMinSurplus()) {
            throw new BizException("xn0000",
                "剩余门槛不能大于[" + agentLevel.getMinSurplus() / 1000 + "]元，目前余额还有["
                        + restAmount / 1000 + "]元");
        }
    }

    @Override
    public XN627666Res getYunFei(String productCode, String speccCode,
            String province, String quantity, String kind) {
        // 产品不包邮，计算运费
        Long yunfei = 0L;
        int orderNumber = 1;
        SYSConfig sysConfig = null;

        if (EBoolean.NO.getCode().equals(kind)) {
            Product product = productBO.getProduct(productCode);
            Specs specs = specsBO.getSpecs(speccCode);
            if (EBoolean.NO.getCode().equals(product.getIsFree())) {
                sysConfig = sysConfigBO.getConfig(province,
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                yunfei = StringValidater.toLong(sysConfig.getCvalue());
            }

            if (EBoolean.YES.getCode().equals(specs.getIsSingle())) {
                orderNumber = StringValidater.toInteger(quantity)
                        / specs.getSingleNumber();
                // 防止下单数量小于拆单数量
                if (StringValidater.toInteger(quantity)
                        % specs.getSingleNumber() > 0) {
                    orderNumber = orderNumber + 1;
                }
            }
        } else {
            InnerProduct innerProduct = innerProductBO
                .getInnerProduct(productCode);
            InnerSpecs innerSpecs = innerSpecsBO.getInnerSpecs(speccCode);
            if (EBoolean.NO.getCode().equals(innerProduct.getIsFree())) {
                sysConfig = sysConfigBO.getConfig(province,
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                yunfei = StringValidater.toLong(sysConfig.getCvalue());
            }

            if (EBoolean.YES.getCode().equals(innerSpecs.getIsSingle())) {

                orderNumber = StringValidater.toInteger(quantity)
                        / innerSpecs.getSingleNumber();

                // 防止下单数量小于拆单数量
                if (StringValidater.toInteger(quantity)
                        % innerSpecs.getSingleNumber() > 0) {
                    orderNumber = orderNumber + 1;
                }
            }
        }
        yunfei = yunfei * orderNumber;
        return new XN627666Res(yunfei);

    }

    @Override
    @Transactional
    public void deliverOutOrder(String code, String isWareSend, String deliver,
            String logisticsCode, String logisticsCompany, String remark) {
        OutOrder data = outOrderBO.getOutOrder(code);

        String status = EOutOrderStatus.TO_RECEIVE.getCode();
        if (!EOutOrderStatus.TO_SEND.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该订单不是待发货");
        }

        Date date = new Date();

        // 云仓发货扣减库存
        if (EBoolean.YES.getCode().equals(isWareSend)) {
            Ware ware = wareBO.getWareByProductSpec(data.getToUserId(),
                data.getSpecsCode());

            if (null == ware) {
                throw new BizException("xn00000", "您的云仓中没改产品");
            }
            if (ware.getQuantity() < data.getQuantity()) {
                throw new BizException("xn00000", "您的云仓中该规格的数量不足");
            }

            if (EOutOrderKind.C_ORDER.getCode().equals(data.getKind())) {
                throw new BizException("xn00000", "非下级的订单无法公司发货哦");
            }

            // 检查起购数量
            Agent agent = agentBO.getAgent(data.getToUserId());
            AgentPrice price = agentPriceBO.getPriceByLevel(ware.getSpecsCode(),
                agent.getLevel());
            if (price.getMinNumber() > data.getQuantity()) {
                throw new BizException("xn00000",
                    ware.getProductName() + "云仓提货不能低于：" + price.getMinNumber());
            }

            wareBO.changeWare(ware.getCode(), EWareLogType.OUT.getCode(),
                -data.getQuantity(), EWareLogType.OUT,
                "下级代理：" + agent.getRealName() + "[" + agent.getLevel()
                        + "]，订单云仓发货",
                data.getCode());

            if (!EOutOrderKind.C_ORDER.getCode().equals(data.getKind())) {
                // 支付运费
                Agent applyAagent = agentBO.getAgent(data.getApplyUser());
                Account txAccount = accountBO.getAccountByUser(
                    applyAagent.getUserId(), ECurrency.TX_CNY.getCode());

                // 产品不包邮，计算运费
                Product product = productBO.getProduct(data.getProductCode());
                if (EBoolean.NO.getCode().equals(product.getIsFree())) {
                    SYSConfig sysConfig = sysConfigBO.getConfig(
                        data.getProvince(), ESystemCode.BH.getCode(),
                        ESystemCode.BH.getCode());
                    Long yunfei = StringValidater.toLong(sysConfig.getCvalue());
                    if (txAccount.getAmount() < yunfei) {
                        throw new BizException("xn00000",
                            "代理" + txAccount.getRealName() + "可提账户中余额不足以支付"
                                    + yunfei / 1000.0 + "元的运费");
                    }
                    data.setYunfei(yunfei);
                    data.setPayAmount(data.getAmount() + yunfei);

                    accountBO.changeAmount(txAccount.getAccountNumber(),
                        EChannelType.NBZ, null, null, applyAagent.getUserId(),
                        EBizType.YUNFEI, EBizType.YUNFEI.getValue(), -yunfei);
                }

                // 订单状态会退回待审单
                status = EOutOrderStatus.TO_APPROVE.getCode();
                data.setIsWareSend(EBoolean.YES.getCode());
                date = null;
                deliver = null;
                logisticsCode = null;
                logisticsCompany = null;
            }
        }
        if (!EOutOrderKind.C_ORDER.getCode().equals(data.getKind())) {
            Agent applyAagent = agentBO.getAgent(data.getApplyUser());
            AgentLevel agentLevel = agentLevelBO
                .getAgentByLevel(applyAagent.getLevel());
            if (EBoolean.NO.getCode().equals(agentLevel.getIsWare())) {
                // 出货以及推荐奖励
                this.payAward(data);
            }
        }

        outOrderBO.deliverOutOrder(data, status, deliver, logisticsCode,
            logisticsCompany, date, remark);

    }

    /**
     *  统计出货奖励
     * @create: 2018年9月2日 下午3:45:28 nyc
     * @history:
     */
    public void outOrderChAward() {
        logger.info("============出货订单统计开始==========");
        OutOrder condition = new OutOrder();
        condition.setIsPay(EIsPay.PAY_NO.getCode());
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOutOrderStatus.TO_SEND.getCode());
        statusList.add(EOutOrderStatus.TO_RECEIVE.getCode());
        statusList.add(EOutOrderStatus.RECEIVED.getCode());
        condition.setStatusList(statusList);

        List<OutOrder> list = outOrderBO.queryOutOrderListCount(condition);

        String fromUserId = ESysUser.SYS_USER_BH.getCode();
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());

        Long allAward = 0L;
        for (OutOrder data : list) {

            // 获取本等级所有区间奖励，已开始金额排序
            List<ChAward> awardList = chAwardBO
                .getChAwardByLevel(data.getLevel());
            for (ChAward award : awardList) {

                // 出货总金额大于某个区间最高金额
                if (data.getAllAmount() > award.getEndAmount()) {
                    allAward = allAward + (long) ((award.getEndAmount()
                            - award.getStartAmount())
                            * (award.getPercent() / 100));

                    // 出货总金额位于某个区间之间
                } else if (award.getStartAmount() < data.getAllAmount()
                        && data.getAllAmount() <= award.getEndAmount()) {
                    allAward = allAward + (long) ((data.getAllAmount()
                            - award.getStartAmount())
                            * (award.getPercent() / 100));
                }
            }

            // 发放奖励
            if (0 != allAward) {
                Agent agent = agentBO.getAgent(data.getApplyUser());

                if (StringValidater
                    .toInteger(EAgentLevel.ONE.getCode()) != agent.getLevel()) {
                    fromUserId = agent.getHighUserId();
                }

                Account account = accountBO.getAccountByUser(fromUserId,
                    ECurrency.TX_CNY.getCode());

                if (EAccountType.Plat.getCode().equals(account.getType())
                        || account.getAmount() > allAward) {
                    accountBO.transAmountCZB(fromUserId,
                        ECurrency.TX_CNY.getCode(), agent.getUserId(),
                        ECurrency.TX_CNY.getCode(), allAward,
                        EBizType.AJ_CHJL_OUT, EBizType.AJ_CHJL_OUT.getValue(),
                        EBizType.AJ_CHJL_OUT.getValue(), payGroup);

                    AgentReport report = agentReportBO
                        .getAgentReportByUser(data.getApplyUser());
                    report.setSendAward(report.getSendAward() + allAward);
                    agentReportBO.refreshSendAward(report);

                    data.setPayGroup(payGroup);
                    data.setIsPay(EIsPay.PAY_YES.getCode());
                    outOrderBO.updatePayGroup(data);
                }

            }

        }
        logger.info("============出货订单统计结束==========");
    }

    @Override
    public void eidtProCode(String code, String proCode) {
        OutOrder data = outOrderBO.getOutOrder(code);
        if (!(EOutOrderStatus.TO_RECEIVE.getCode().equals(data.getStatus())
                || EOutOrderStatus.TO_RECEIVE.getCode()
                    .equals(data.getStatus()))) {
        }
    }

    public void removeOutOrder() {

        logger.info("==========开始删除未支付的订单===========");
        long start = System.currentTimeMillis();
        OutOrder data = new OutOrder();
        data.setStartDatetime(DateUtil.getBeforeTime(0));
        outOrderBO.removeOutOrder(data);
        long end = System.currentTimeMillis();
        logger.info("耗时：" + (end - start));
    }
}
