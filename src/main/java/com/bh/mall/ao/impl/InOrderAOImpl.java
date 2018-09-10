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

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.ao.IWeChatAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IOrderReportBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductReportBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.ITjAwardBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.callback.CallbackBzdhConroller;
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
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.ChAward;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductReport;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.TjAward;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EInOrderStatus;
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESpecsLogType;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.EWareLogType;
import com.bh.mall.exception.BizException;
import com.bh.mall.util.wechat.XMLUtil;

@Service
public class InOrderAOImpl implements IInOrderAO {

    private static Logger logger = Logger
        .getLogger(CallbackBzdhConroller.class);

    @Autowired
    IInOrderBO inOrderBO;

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
    ISYSUserBO sysUserBO;

    @Autowired
    IAgentReportBO agentReportBO;

    @Autowired
    IProductReportBO productReportBO;

    @Autowired
    IOrderReportBO orderReportBO;

    @Override
    @Transactional
    public List<String> addInOrder(List<String> codeList, String applyUser,
            String applyNote) {
        // 下单人及下单代理
        Agent applyAgent = agentBO.getAgent(applyUser);
        AgentLevel agentLevel = agentLevelBO
            .getAgentByLevel(applyAgent.getLevel());
        // 团队长
        Agent teamLeader = agentBO.getTeamLeader(applyAgent.getTeamName());

        // 获取订单归属人
        String toUserName = null;
        if (agentBO.isHighest(applyAgent.getUserId())) {
            SYSUser sysUser = sysUserBO.getSYSUser(applyAgent.getHighUserId());
            toUserName = sysUser.getRealName();
        } else {
            Agent highUser = agentBO.getAgent(applyAgent.getHighUserId());
            toUserName = highUser.getRealName();
        }

        Long amount = 0L;
        List<String> list = new ArrayList<String>();
        for (String code : codeList) {
            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            Specs specs = specsBO.getSpecs(cart.getSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000",
                    "您购车物的[" + pData.getName() + "]商家已经下架喽！去看看其他的吧");
            }
            AgentPrice agentPrice = agentPriceBO
                .getPriceByLevel(specs.getCode(), applyAgent.getLevel());

            // 检查是否可购买
            if (EBoolean.NO.getCode().equals(specs.getIsNormalOrder())) {
                throw new BizException("xn0000", "该规格不允许普通单下单，换个商品看看吧");
            }

            // 非最高等级代理，检查上级云仓
            if (StringValidater.toInteger(
                EAgentLevel.ONE.getCode()) != agentLevel.getLevel()) {
                wareBO.checkProduct(applyAgent.getHighUserId(),
                    cart.getSpecsCode());
            } else {
                // 非最高等级代理，检查产品库存
                if (specs.getStockNumber() < cart.getQuantity()) {
                    throw new BizException("xn00000", "产品库存不足");
                }
            }

            // 检查起购数量
            if (agentPrice.getStartNumber() > cart.getQuantity()) {
                throw new BizException("xn0000", "您购买的数量不能低于["
                        + agentPrice.getStartNumber() + "]" + specs.getName());
            }
            // 检查限购
            this.checkLimitNumber(applyAgent, specs, agentPrice,
                cart.getQuantity());

            String orderCode = inOrderBO.saveInOrder(applyAgent.getUserId(),
                applyAgent.getRealName(), applyAgent.getLevel(),
                applyAgent.getHighUserId(), toUserName,
                applyAgent.getTeamName(), teamLeader.getRealName(),
                pData.getCode(), pData.getName(), specs.getCode(),
                specs.getName(), pData.getPic(), agentPrice.getPrice(),
                cart.getQuantity(), applyNote);
            list.add(orderCode);

            amount = amount + cart.getQuantity() * agentPrice.getPrice();
            // 删除购物车记录
            cartBO.removeCart(cart);
        }

        // 第一次购买，金额不够授权单金额，继续购买
        InOrder condition = new InOrder();
        condition.setApplyUser(applyAgent.getUserId());
        condition.setStartDatetime(applyAgent.getImpowerDatetime());
        condition.setStatus(EInOrderStatus.Received.getCode());
        long count = inOrderBO.getTotalCount(condition);
        if (count == 0) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn0000",
                    "授权单的金额为[" + agentLevel.getAmount() / 1000 + "]，您还需购买"
                            + (agentLevel.getAmount() - amount) / 1000);
            }
        }

        // 检查门槛余额
        Account mkAccount = accountBO.getAccountNocheck(applyAgent.getUserId(),
            ECurrency.MK_CNY.getCode());
        if (mkAccount.getAmount() > agentLevel.getMinSurplus()) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn0000",
                    "剩余门槛不能大于[" + agentLevel.getMinSurplus() / 1000
                            + "]元，目前余额还有[" + mkAccount.getAmount() / 1000
                            + "]元");
            }
        }

        return list;
    }

    @Override
    @Transactional
    public String addInOrderNoCart(String applyUser, String specsCode,
            Integer quantity, String applyNote) {

        Agent applyAgent = agentBO.getAgent(applyUser);
        Specs specs = specsBO.getSpecs(specsCode);
        Product pData = productBO.getProduct(specs.getProductCode());
        AgentLevel agentLevel = agentLevelBO
            .getAgentByLevel(applyAgent.getLevel());

        // 门槛余额是否高于限制
        AgentPrice agentPrice = agentPriceBO.getPriceByLevel(specs.getCode(),
            applyAgent.getLevel());
        Long amount = quantity * agentPrice.getPrice();
        Account account = accountBO.getAccountByUser(applyAgent.getUserId(),
            ECurrency.MK_CNY.getCode());

        // 门槛最低余额为零
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

        // 检查是否可购买
        if (EBoolean.NO.getCode().equals(specs.getIsNormalOrder())) {
            throw new BizException("xn0000", "该规格不允许普通单下单，换个商品看看吧");
        }

        // 检查起购数量
        if (agentPrice.getStartNumber() > quantity) {
            throw new BizException("xn0000", "您购买的数量不能低于["
                    + agentPrice.getStartNumber() + "]" + specs.getName());
        }

        // 检查限购
        this.checkLimitNumber(applyAgent, specs, agentPrice, quantity);

        // 第一次购买，金额不够授权单金额，继续购买
        InOrder condition = new InOrder();
        condition.setApplyUser(applyAgent.getUserId());
        condition.setStartDatetime(applyAgent.getImpowerDatetime());
        long count = inOrderBO.getTotalCount(condition);
        if (count == 0) {
            if (agentLevel.getAmount() > amount) {
                throw new BizException("xn0000",
                    "授权单的金额为[" + agentLevel.getAmount() / 1000 + "]，您还需购买"
                            + (agentLevel.getAmount() - amount) / 1000);
            }
        }

        // 团队长
        Agent teamLeader = agentBO.getTeamLeader(applyAgent.getTeamName());

        // 获取订单归属人
        String toUserName = null;
        if (agentBO.isHighest(applyAgent.getUserId())) {
            SYSUser sysUser = sysUserBO.getSYSUser(applyAgent.getHighUserId());
            toUserName = sysUser.getRealName();
        } else {
            Agent highUser = agentBO.getAgent(applyAgent.getHighUserId());
            toUserName = highUser.getRealName();
        }

        // 非最高等级代理，检查上级云仓
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != agentLevel
            .getLevel()) {
            wareBO.checkProduct(applyAgent.getHighUserId(), specsCode);
        } else {
            // 非最高等级代理，扣减产品库存
            if (specs.getStockNumber() < quantity) {
                throw new BizException("xn00000", "产品库存不足");
            }
        }

        return inOrderBO.saveInOrder(applyAgent.getUserId(),
            applyAgent.getRealName(), applyAgent.getLevel(),
            applyAgent.getHighUserId(), toUserName, applyAgent.getTeamName(),
            teamLeader.getRealName(), pData.getCode(), pData.getName(),
            specs.getCode(), specs.getName(), pData.getPic(),
            agentPrice.getPrice(), quantity, applyNote);
    }

    @Override
    @Transactional
    public Object payInOrder(List<String> codeList, String payType) {

        InOrder inOrder = inOrderBO.getInOrder(codeList.get(0));
        Agent applyUser = agentBO.getAgent(inOrder.getApplyUser());
        Long amount = 0L;
        Long cjAmount = 0L;
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.InOrder.getCode());

        Object result = null;
        for (String code : codeList) {
            InOrder data = inOrderBO.getInOrder(code);
            if (!EInOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单未处于待支付状态");
            }
            // 计算差额利润
            if (StringValidater
                .toInteger(EAgentLevel.ONE.getCode()) != applyUser.getLevel()) {
                AgentPrice price = agentPriceBO
                    .getPriceByLevel(data.getSpecsCode(), applyUser.getLevel());
                Agent toUser = agentBO.getAgent(applyUser.getHighUserId());
                AgentPrice highPrice = agentPriceBO
                    .getPriceByLevel(data.getSpecsCode(), toUser.getLevel());
                cjAmount = (price.getPrice() - highPrice.getPrice())
                        * data.getQuantity();
            }

            if (EPayType.RMB_YE.getCode().equals(payType)) {
                data.setPayType(EPayType.RMB_YE.getCode());

                data.setPayGroup(payGroup);
                inOrderBO.paySuccess(data);

                // 买入云仓
                this.payOrder(data, applyUser);
            } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
                inOrder.setPayType(EChannelType.WeChat_H5.getCode());
                inOrder.setPayGroup(payGroup);
                inOrderBO.addPayGroup(inOrder);
            }
            amount = amount + data.getAmount();
        }

        if (EPayType.RMB_YE.getCode().equals(payType)) {
            // 账户扣钱

            if (StringValidater
                .toInteger(EAgentLevel.ONE.getCode()) == applyUser.getLevel()) {
                accountBO.transAmountCZB(applyUser.getUserId(),
                    ECurrency.MK_CNY.getCode(), ESysUser.SYS_USER_BH.getCode(),
                    ECurrency.TX_CNY.getCode(), amount, EBizType.AJ_GMYC,
                    EBizType.AJ_GMYC.getValue(), EBizType.AJ_GMYC.getValue(),
                    payGroup);
            } else {
                Account mkAccount = accountBO.getAccountByUser(
                    applyUser.getUserId(), ECurrency.MK_CNY.getCode());
                accountBO.changeAmount(mkAccount.getAccountNumber(),
                    EChannelType.NBZ, null, payGroup, payGroup,
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(), -amount);
            }

            this.payAward(applyUser, inOrder.getProductCode(),
                inOrder.getSpecsCode(), amount, payGroup, cjAmount);

            result = new BooleanRes(true);
        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
            Object payResult = this.payWXH5(applyUser.getUserId(), payGroup,
                payGroup, amount,
                PropertiesUtil.Config.WECHAT_H5_IN_ORDER_BACKURL,
                EChannelType.WeChat_H5.getCode());
            result = payResult;
        }
        return result;

    }

    private Object payWXH5(String applyUser, String payGroup, String refNo,
            Long amount, String callBackUrl, String payType) {
        return weChatAO.getPrepayIdH5(applyUser, payGroup, refNo,
            EBizType.AJ_GMCP.getCode(), EBizType.AJ_GMCP.getValue(), amount,
            callBackUrl, payType);
    }

    @Override
    @Transactional
    public void paySuccess(String result) {
        Map<String, String> map = null;
        try {
            logger.info("========云仓订单回调信息========");
            map = XMLUtil.doXMLParse(result);
            String wechatOrderNo = map.get("transaction_id");
            String outTradeNo = map.get("out_trade_no");

            // 此处调用订单查询接口验证是否交易成功
            List<InOrder> list = inOrderBO.getInOrderByPayGroup(outTradeNo);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException("xn0000", "云仓订单订单不存在");
            }
            boolean isSucc = weChatAO.reqOrderquery(map,
                EChannelType.WeChat_H5.getCode());

            if (isSucc) {
                Long amount = 0L;
                Long cjAmount = 0L;
                Agent agent = agentBO.getAgent(list.get(0).getApplyUser());
                for (InOrder data : list) {
                    if (!EInOrderStatus.Unpaid.getCode()
                        .equals(data.getStatus())) {
                        throw new BizException("xn0000", "订单已支付");
                    }
                    this.payOrder(data, agent);

                    data.setPayType(EChannelType.WeChat_H5.getCode());
                    data.setPayCode(wechatOrderNo);
                    inOrderBO.paySuccess(data);

                    amount = amount + data.getAmount();

                    if (StringValidater.toInteger(
                        EAgentLevel.ONE.getCode()) != agent.getLevel()) {
                        AgentPrice price = agentPriceBO.getPriceByLevel(
                            data.getSpecsCode(), agent.getLevel());
                        Agent toUser = agentBO.getAgent(agent.getHighUserId());
                        AgentPrice highPrice = agentPriceBO.getPriceByLevel(
                            data.getSpecsCode(), toUser.getLevel());
                        cjAmount = (price.getPrice() - highPrice.getPrice())
                                * data.getQuantity();
                    }
                }
                InOrder inOrder = list.get(0);
                // 账户收钱
                this.payOrder(agent, EChannelType.WeChat_H5.getCode(),
                    wechatOrderNo, amount);

                // 出货以及推荐奖励
                this.payAward(agent, inOrder.getProductCode(),
                    inOrder.getSpecsCode(), amount, outTradeNo, cjAmount);

            } else {

                for (InOrder data : list) {
                    if (!EInOrderStatus.Unpaid.getCode()
                        .equals(data.getStatus())) {
                        throw new BizException("xn0000", "订单已支付");
                    }
                    data.setStatus(EInOrderStatus.Pay_NO.getCode());
                    data.setPayDatetime(new Date());
                    inOrderBO.payNo(data);
                }

            }

        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }
    }

    @Override
    public Paginable<InOrder> queryInOrderPage(int start, int limit,
            InOrder condition) {
        if (null != condition.getStartDatetime()
                && null != condition.getEndDatetime() && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<InOrder> page = inOrderBO.getPaginable(start, limit,
            condition);
        for (InOrder inOrder : page.getList()) {
            // 下单人
            Agent agent = agentBO.getAgent(inOrder.getApplyUser());
            inOrder.setAgent(agent);

            // 产品信息
            Product product = productBO.getProduct(inOrder.getProductCode());
            inOrder.setProduct(product);

            inOrder.setPic(product.getAdvPic());
        }
        return page;
    }

    @Override
    public List<InOrder> queryInOrderList(InOrder condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<InOrder> list = inOrderBO.queryInOrderList(condition);
        for (InOrder inOrder : list) {
            // 下单人
            Agent agent = agentBO.getAgent(inOrder.getApplyUser());
            inOrder.setAgent(agent);

            // 产品信息
            Product product = productBO.getProduct(inOrder.getProductCode());
            inOrder.setProduct(product);
        }
        return list;
    }

    @Override
    public InOrder getInOrder(String code) {
        InOrder inOrder = inOrderBO.getInOrder(code);
        // 下单人
        Agent agent = agentBO.getAgent(inOrder.getApplyUser());
        inOrder.setAgent(agent);

        // 产品信息
        Product product = productBO.getProduct(inOrder.getProductCode());
        inOrder.setProduct(product);
        return inOrder;
    }

    @Transactional
    private void payAward(Agent applyUser, String productCode, String specsCode,
            Long orderAmount, String payGroup, Long profit) {

        // 统计差额利润
        AgentReport report = null;
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != applyUser
            .getLevel()) {

            // 统计差额利润
            report = agentReportBO
                .getAgentReportByUser(applyUser.getHighUserId());
            report.setProfitAward(report.getProfitAward() + profit);
            agentReportBO.refreshAward(report);
        }

        String fromUserId = ESysUser.SYS_USER_BH.getCode();

        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != applyUser
            .getLevel()) {
            fromUserId = applyUser.getHighUserId();
        }
        // 是否有推荐人
        if (this.checkAward(applyUser)) {
            if (StringUtils.isNotBlank(applyUser.getReferrer())) {
                // 直接推荐人
                TjAward tjAward = tjAwardBO
                    .getAwardByLevel(applyUser.getLevel(), productCode);
                if (StringValidater.toInteger(
                    EAgentLevel.ONE.getCode()) == applyUser.getLevel()) {
                    fromUserId = ESysUser.SYS_USER_BH.getCode();
                }

                // 直接推荐奖
                if (null != applyUser.getReferrer()) {
                    Agent firstReferee = agentBO
                        .getAgent(applyUser.getReferrer());

                    Long amount1 = (long) (orderAmount
                            * (tjAward.getValue1() / 100));
                    if (amount1 > 0) {
                        accountBO.transAmountCZB(fromUserId,
                            ECurrency.TX_CNY.getCode(),
                            firstReferee.getUserId(),
                            ECurrency.TX_CNY.getCode(), amount1,
                            EBizType.AJ_TJJL_IN, EBizType.AJ_TJJL_IN.getValue(),
                            EBizType.AJ_TJJL_IN.getValue(), payGroup);
                        // 统计推荐奖
                        report = agentReportBO
                            .getAgentReportByUser(firstReferee.getUserId());
                        report
                            .setRefreeAward(report.getRefreeAward() + amount1);
                        agentReportBO.refreshAward(report);
                    }

                    // 间接推荐奖
                    if (StringUtils.isNotBlank(firstReferee.getReferrer())) {
                        Agent secondReferee = agentBO
                            .getAgent(firstReferee.getReferrer());
                        Long amount2 = (long) (orderAmount
                                * (tjAward.getValue2() / 100));
                        if (amount2 > 0) {
                            accountBO.transAmountCZB(fromUserId,
                                ECurrency.TX_CNY.getCode(),
                                secondReferee.getUserId(),
                                ECurrency.TX_CNY.getCode(), amount2,
                                EBizType.AJ_TJJL_IN,
                                EBizType.AJ_TJJL_IN.getValue(),
                                EBizType.AJ_TJJL_IN.getValue(), payGroup);

                            // 统计推荐奖
                            report = agentReportBO.getAgentReportByUser(
                                secondReferee.getUserId());
                            report.setRefreeAward(
                                report.getRefreeAward() + amount2);
                            agentReportBO.refreshAward(report);
                        }

                        // 次推荐奖
                        if (StringUtils
                            .isNotBlank(secondReferee.getReferrer())) {
                            Agent thirdReferee = agentBO
                                .getAgent(secondReferee.getReferrer());
                            Long amount3 = (long) (orderAmount
                                    * (tjAward.getValue3() / 100));

                            if (amount3 > 0) {
                                accountBO.transAmountCZB(fromUserId,
                                    ECurrency.TX_CNY.getCode(),
                                    thirdReferee.getUserId(),
                                    ECurrency.TX_CNY.getCode(), amount3,
                                    EBizType.AJ_TJJL_IN,
                                    EBizType.AJ_TJJL_IN.getValue(),
                                    EBizType.AJ_TJJL_IN.getValue(), payGroup);

                                // 统计推荐奖
                                report = agentReportBO.getAgentReportByUser(
                                    thirdReferee.getUserId());
                                report.setRefreeAward(
                                    report.getRefreeAward() + amount3);
                                agentReportBO.refreshAward(report);
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void cancelInOrder(String code) {
        InOrder data = inOrderBO.getInOrder(code);

        // 订单已申请取消或已取消
        if (EInOrderStatus.TO_Cancel.getCode().equals(data.getStatus())
                || EInOrderStatus.Canceled.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已申请取消喽，请勿重复申请！");
        }
        // 订单已发货或已收货无法取消
        if (!EInOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已收货，无法申请取消");
        }

        data.setStatus(EInOrderStatus.TO_Cancel.getCode());
        inOrderBO.cancelInOrder(data);
    }

    @Override
    public void approveCancel(String code, String result, String updater,
            String remark) {
        InOrder data = inOrderBO.getInOrder(code);
        if (!EInOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该订单未申请取消");
        }

        data.setStatus(EInOrderStatus.Unpaid.getCode());
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EInOrderStatus.Canceled.getCode());
            // 云仓提货，归还云仓库存
            if (EChannelType.NBZ.getCode().equals(data.getPayType())) {
                String toUser = data.getToUserId();
                if (StringUtils.isBlank(toUser)) {
                    toUser = ESysUser.SYS_USER_BH.getCode();
                }

                accountBO.transAmountCZB(toUser, ECurrency.TX_CNY.getCode(),
                    data.getApplyUser(), ECurrency.TX_CNY.getCode(),
                    data.getAmount(), EBizType.AJ_GMCP_TK,
                    EBizType.AJ_GMCP_TK.getValue(),
                    EBizType.AJ_GMCP_TK.getValue(), data.getCode());
            }
        }
        data.setRemark(remark);
        inOrderBO.approveCancel(data);

    }

    // 检查介绍奖与推荐奖是否同时存在
    private boolean checkAward(Agent agent) {
        // 介绍人与推荐人同时存在
        if (StringUtils.isNotBlank(agent.getIntroducer())
                && StringUtils.isNotBlank(agent.getReferrer())) {
            // 下单金额是否超过授权金额
            InOrder condition = new InOrder();
            condition.setApplyUser(agent.getUserId());
            condition.setStatus(EInOrderStatus.Received.getCode());
            List<InOrder> list = inOrderBO.queryInOrderList(condition);
            Long amount = 0L;
            for (InOrder inOrder : list) {
                amount = amount + inOrder.getAmount();
            }

            AgentLevel impower = agentLevelBO.getAgentByLevel(agent.getLevel());
            if (impower.getMinCharge() >= amount) {
                return false;
            }

        }
        return true;
    }

    private void payOrder(Agent agent, String payType, String wechatOrderNo,
            Long amount) {
        // 订单归属人是平台，只有托管账户加钱
        accountBO.changeAmount(ESysUser.TG_BH.getCode(),
            EChannelType.getEChannelType(payType), wechatOrderNo, wechatOrderNo,
            wechatOrderNo, EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
            amount);
        // 订单归属人不是平台，托管账户与代理账户同时加钱
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != agent
            .getLevel()) {
            Account account = accountBO.getAccountByUser(agent.getUserId(),
                ECurrency.TX_CNY.getCode());
            // 收款方账户价钱
            accountBO.changeAmount(account.getAccountNumber(),
                EChannelType.WeChat_H5, wechatOrderNo, wechatOrderNo,
                wechatOrderNo, EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                amount);
        }
    }

    // 删除未支付订单
    public void removeOrderTimer() {
        // 每十二个小时执行一次，删除是个小时前未支付的订单
        Date date = new Date();
        InOrder condition = new InOrder();
        condition.setStatus(EInOrderStatus.Unpaid.getCode());
        condition.setEndDatetime(date);
        List<InOrder> list = inOrderBO.queryInOrderList(condition);
        for (InOrder data : list) {
            inOrderBO.removeOrder(data);
        }
    }

    // 变动产品数量
    private void changeProductNumber(Agent agent, Product pData, Specs psData,
            InOrder inOrder, Integer number, String code) {
        // 开启云仓的代理
        AgentLevel agentLevel = agentLevelBO.getAgentByLevel(agent.getLevel());
        if (EBoolean.YES.getCode().equals(agentLevel.getIsWare())) {
            // 非最高等级代理，扣减上级云仓
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != agent
                .getLevel()) {
                Ware toWare = wareBO.getWareByProductSpec(inOrder.getToUserId(),
                    inOrder.getSpecsCode());
                // 上级云仓没有该产品
                if (null == toWare) {
                    throw new BizException("xn00000",
                        "上级代理云仓中没有产品：[" + pData.getName() + "]");
                } else {
                    // 改变上级云仓
                    wareBO.changeWare(toWare.getCode(),
                        EWareLogType.OUT.getCode(), number, ESpecsLogType.Order,
                        EBizType.AJ_GMYC.getValue(), inOrder.getCode());
                }

            } else {
                // 无上级代理,扣减产品实际库存
                specsBO.refreshRepertory(pData.getName(), psData,
                    ESpecsLogType.Order.getCode(), number, agent.getUserId(),
                    inOrder.getApplyNote());
            }
        }
    }

    // 检查限购
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
        List<InOrder> list = inOrderBO.getProductQuantity(agentId, specsCode,
            startDatetime, endDatetime);
        for (InOrder inOrder : list) {
            number = number + inOrder.getQuantity();
        }
        return number;
    }

    private void payOrder(InOrder data, Agent applyUser) {
        // 购买云仓
        wareBO.buyWare(data.getCode(), data.getProductCode(),
            data.getProductName(), data.getSpecsCode(), data.getSpecsName(),
            data.getQuantity(), data.getPrice(), applyUser, ESpecsLogType.Order,
            "购买产品：[" + data.getProductName() + "]");

        // 扣减上级云仓
        Product pData = productBO.getProduct(data.getProductCode());
        Specs specs = specsBO.getSpecs(data.getSpecsCode());
        this.changeProductNumber(applyUser, pData, specs, data,
            -data.getQuantity(), data.getCode());

        // 统计团队产品销售,只统计最高等级代理
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == applyUser
            .getLevel()) {
            Integer number = specsBO.getMinSpecsNumber(specs,
                data.getQuantity() * specs.getNumber());
            ProductReport report = productReportBO.getProductReport(
                applyUser.getTeamName(), data.getProductCode());

            if (null == report) {
                productReportBO.saveProductReport(data, number,
                    applyUser.getRealName());
            } else {
                productReportBO.refreshProductReport(report, number);
            }
        }
    }

    /**
     *  统计出货奖励
     * @create: 2018年9月2日 下午3:45:28 nyc
     * @history:
     */
    public void inOrderChAward() {
        logger.info("============云仓订单统计开始==========");
        // 清空所有代理的出货金额与奖励
        AgentReport rCondition = new AgentReport();
        List<AgentReport> reportList = agentReportBO
            .queryAgentReportList(rCondition);
        for (AgentReport agentReport : reportList) {
            agentReport.setSendAmount(0L);
            agentReportBO.refreshSendAward(agentReport);
        }

        Date startDate = DateUtil.getMonthStart();
        Date endDate = DateUtil.getMonthEnd();
        InOrder condition = new InOrder();
        condition.setStartDatetime(startDate);
        condition.setEndDatetime(endDate);

        condition.setStatus(EInOrderStatus.Received.getCode());
        List<InOrder> list = inOrderBO.queryInOrderListCount(condition);

        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.InOrder.getCode());

        Long allAward = 0L;
        for (InOrder data : list) {

            // 1、记录下个区间剩余出货金额
            Long orderAmount = data.getAllAmount();

            // 2、获取本等级所有区间奖励，已开始金额排序
            List<ChAward> awardList = chAwardBO
                .getChAwardByLevel(data.getLevel());
            for (ChAward award : awardList) {

                // 4、出货总金额大于某个区间最高金额
                if (orderAmount > award.getEndAmount()) {
                    allAward = allAward + (long) ((award.getEndAmount() - 1)
                            * (award.getPercent() / 100));
                    orderAmount = orderAmount - award.getEndAmount();

                    // 5、出货总金额位于某个区间之间
                } else if (award.getStartAmount() <= data.getAllAmount()
                        && data.getAllAmount() <= award.getEndAmount()) {
                    allAward = allAward + (long) ((data.getAllAmount()
                            - award.getStartAmount())
                            * (award.getPercent() / 100));
                }
            }

            AgentReport report = agentReportBO
                .getAgentReportByUser(data.getApplyUser());
            report.setSendAward(report.getSendAward() + allAward);
            agentReportBO.refreshSendAward(report);

            data.setPayGroup(payGroup);
            inOrderBO.updatePayGroup(data);

            // 发放奖励
            if (0 != allAward) {
                Account mkAccount = accountBO.getAccountByUser(
                    data.getApplyUser(), ECurrency.TX_CNY.getCode());
                accountBO.changeAmount(mkAccount.getAccountNumber(),
                    EChannelType.NBZ, null, payGroup, payGroup,
                    EBizType.AJ_CHJL_IN, EBizType.AJ_CHJL_IN.getValue(),
                    allAward);
            }

        }
        logger.info("============云仓订单统计结束==========");

    }
}
