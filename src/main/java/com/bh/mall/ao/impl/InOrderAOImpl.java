package com.bh.mall.ao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.ITjAwardBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.callback.CallbackBzdhConroller;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.ChAward;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Product;
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
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.EProductIsTotal;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
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

    @Override
    @Transactional
    public List<String> addInOrder(List<String> codeList, String applyUser,
            String applyNote) {
        // 下单人及下单代理
        Agent applyAgent = agentBO.getAgent(applyUser);

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

        List<String> list = new ArrayList<String>();
        for (String code : codeList) {
            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            Specs specs = specsBO.getSpecs(cart.getSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }
            AgentPrice agentPrice = agentPriceBO
                .getPriceByLevel(specs.getCode(), applyAgent.getLevel());

            // 检查是否可购买
            if (EBoolean.NO.getCode().equals(agentPrice.getIsBuy())) {
                throw new BizException("xn0000", "您的等级无法购买该规格的产品");
            }

            // 检查限购
            this.checkLimitNumber(applyAgent, specs, agentPrice,
                cart.getQuantity());

            String orderCode = inOrderBO.saveInOrder(applyAgent.getUserId(),
                applyAgent.getRealName(), applyAgent.getLevel(),
                applyAgent.getTeamName(), applyAgent.getHighUserId(),
                toUserName, teamLeader.getRealName(), pData.getCode(),
                pData.getName(), specs.getCode(), specs.getName(),
                pData.getAdvPic(), agentPrice.getPrice(), cart.getQuantity(),
                applyNote);
            list.add(orderCode);
            // 删除购物车记录
            cartBO.removeCart(cart);
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

        // 获取该产品中最小规格的数量
        int minNumber = specsBO.getMinSpecsNumber(pData.getCode());
        AgentLevel agentLevel = agentLevelBO
            .getAgentByLevel(applyAgent.getLevel());

        // 非最高等级代理，检查上级云仓
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != agentLevel
            .getLevel()) {
            wareBO.checkProduct(applyAgent.getUserId(), specsCode);
        } else {
            // 非最高等级代理，扣减产品库存
            Integer nowNumber = pData.getRealNumber() - (quantity * minNumber);
            if (nowNumber < 0) {
                throw new BizException("xn00000", "产品库存不足");
            }
        }

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

        // 检查起购数量
        if (agentPrice.getStartNumber() > quantity) {
            throw new BizException("xn0000",
                "您购买的数量不能低于" + agentPrice.getStartNumber() + "]");
        }

        return inOrderBO.saveInOrder(applyAgent.getUserId(),
            applyAgent.getRealName(), applyAgent.getLevel(),
            applyAgent.getTeamName(), applyAgent.getHighUserId(), toUserName,
            teamLeader.getRealName(), pData.getCode(), pData.getName(),
            specs.getCode(), specs.getName(), pData.getAdvPic(),
            agentPrice.getPrice(), quantity, applyNote);
    }

    @Override
    @Transactional
    public Object payInOrder(List<String> codeList, String payType) {
        Object result = null;
        for (String code : codeList) {
            InOrder data = inOrderBO.getInOrder(code);
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单未处于待支付状态");
            }
            Agent uData = agentBO.getAgent(data.getApplyUser());
            if (EPayType.RMB_YE.getCode().equals(payType)) {
                // 账户扣钱
                String payGroup = inOrderBO.addPayGroup(data);
                Account mkAccount = accountBO.getAccountByUser(
                    data.getApplyUser(), ECurrency.MK_CNY.getCode());
                data.setPayType(EChannelType.NBZ.getCode());
                accountBO.changeAmount(mkAccount.getAccountNumber(),
                    EChannelType.NBZ, null, payGroup, data.getCode(),
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                    -data.getAmount());

                // 扣减上级云仓
                Product pData = productBO.getProduct(data.getProductCode());
                Specs specs = specsBO.getSpecs(data.getSpecsCode());
                this.changeProductNumber(uData, pData, specs, data,
                    -data.getQuantity(), data.getCode());

                // 购买云仓
                wareBO.buyWare(data, uData);
                // 出货以及推荐奖励
                this.payAward(data);

                data.setPayCode(data.getCode());
                inOrderBO.paySuccess(data);

                result = new BooleanRes(true);
            } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
                Object payResult = this.payWXH5(data,
                    PropertiesUtil.Config.WECHAT_H5_ORDER_BACKURL);
                result = payResult;
            }
        }
        return result;

    }

    private Object payWXH5(InOrder data, String callBackUrl) {
        Long rmbAmount = data.getAmount();
        Agent agent = agentBO.getAgent(data.getApplyUser());
        String payGroup = inOrderBO.addPayGroup(data);
        agentBO.getAgent(data.getToUserId());
        Account account = accountBO.getAccountByUser(data.getToUserId(),
            ECurrency.YJ_CNY.getCode());
        return weChatAO.getPrepayIdH5(agent.getUserId(),
            account.getAccountNumber(), payGroup, data.getCode(),
            EBizType.AJ_GMCP.getCode(), EBizType.AJ_GMCP.getValue(), rmbAmount,
            callBackUrl, data.getPayType());
    }

    @Override
    public void paySuccess(String result) {
        Map<String, String> map = null;
        try {
            logger.info("========回调信息=================");
            map = XMLUtil.doXMLParse(result);
            String attach = map.get("attach");
            String[] codes = attach.split("\\|\\|");
            String systemCode = codes[0];
            String companyCode = codes[1];
            String bizBackUrl = codes[2];
            String wechatOrderNo = map.get("transaction_id");
            String outTradeNo = map.get("out_trade_no");

            InOrder data = inOrderBO.getInOrder(outTradeNo);
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单已支付");
            }

            // 此处调用订单查询接口验证是否交易成功
            boolean isSucc = weChatAO.reqOrderquery(map,
                EChannelType.WeChat_H5.getCode());
            if (isSucc) {

                Agent agent = agentBO.getAgent(data.getApplyUser());
                // 账户收钱
                this.payOrder(agent, data, wechatOrderNo);

                // 扣减上级云仓
                Product pData = productBO.getProduct(data.getProductCode());
                Specs specs = specsBO.getSpecs(data.getSpecsCode());
                this.changeProductNumber(agent, pData, specs, data,
                    -data.getQuantity(), data.getCode());

                // 购买云仓
                wareBO.buyWare(data, agent);
                // 出货以及推荐奖励
                this.payAward(data);
                data.setPayCode(wechatOrderNo);
                inOrderBO.paySuccess(data);
            } else {

                data.setStatus(EOrderStatus.Pay_NO.getCode());
                data.setPayDatetime(new Date());
                inOrderBO.payNo(data);
            }
        } catch (JDOMException |

                IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }
    }

    @Override
    public Paginable<InOrder> queryInOrderPage(int start, int limit,
            InOrder condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<InOrder> page = inOrderBO.getPaginable(start, limit,
            condition);
        for (InOrder inOrder : page.getList()) {
            // 下单人
            Agent agent = agentBO.getAgent(inOrder.getApplyUser());
            inOrder.setAgent(agent);

            // 团队长,一级代理自己是团队长
            if (1 != agent.getLevel()) {
                Agent teamLeader = agentBO.getTeamLeader(agent.getTeamName());
                inOrder.setLeaderName(teamLeader.getRealName());
                inOrder.setLeaderMobile(teamLeader.getMobile());
            } else {
                inOrder.setLeaderName(agent.getRealName());
                inOrder.setLeaderMobile(agent.getMobile());
            }

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

            // 团队长,一级代理自己是团队长
            if (1 != agent.getLevel()) {
                Agent teamLeader = agentBO.getTeamLeader(agent.getTeamName());
                inOrder.setLeaderName(teamLeader.getRealName());
                inOrder.setLeaderMobile(teamLeader.getMobile());
            } else {
                inOrder.setLeaderName(agent.getRealName());
                inOrder.setLeaderMobile(agent.getMobile());
            }

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

    private void payAward(InOrder data) {

        Product product = productBO.getProduct(data.getProductCode());
        Agent applyUser = agentBO.getAgent(data.getApplyUser());
        Long orderAmount = data.getAmount();

        // 下单代理不是一级代理
        String fromUserId = ESysUser.SYS_USER_BH.getCode();
        if (!(StringValidater
            .toInteger(EAgentLevel.ONE.getCode()) == (applyUser.getLevel()))) {
            fromUserId = applyUser.getHighUserId();
        }

        // **********出货奖*******
        // 出货奖励,且产品计入出货
        if (EProductIsTotal.YES.getCode().equals(product.getIsTotal())) {
            ChAward award = chAwardBO.getChAwardByLevel(applyUser.getLevel(),
                data.getAmount());
            if (award != null) {
                Long awardAmount = AmountUtil.mul(orderAmount,
                    award.getPercent() / 100);
                accountBO.transAmountCZB(fromUserId, ECurrency.YJ_CNY.getCode(),
                    applyUser.getUserId(), ECurrency.YJ_CNY.getCode(),
                    awardAmount, EBizType.AJ_CHJL, EBizType.AJ_CHJL.getValue(),
                    EBizType.AJ_CHJL.getValue(), data.getCode());
            }
        }

        // **********推荐奖**********
        // 是否有推荐人
        if (this.checkAward(applyUser)) {
            if (StringUtils.isNotBlank(applyUser.getReferrer())) {
                // 直接推荐人
                Agent firstReferee = agentBO.getAgent(applyUser.getReferrer());
                TjAward tjAward = tjAwardBO.getAwardByLevel(
                    applyUser.getLevel(), data.getProductCode());

                Long amount = 0L;
                // 直接推荐奖
                if (null != firstReferee) {
                    amount = AmountUtil.mul(orderAmount,
                        tjAward.getValue1() / 100);
                    accountBO.transAmountCZB(fromUserId,
                        ECurrency.YJ_CNY.getCode(), firstReferee.getUserId(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_TJJL,
                        EBizType.AJ_TJJL.getValue(),
                        EBizType.AJ_TJJL.getValue(), data.getCode());

                    // 间接推荐奖
                    if (StringUtils.isNotBlank(firstReferee.getReferrer())) {
                        Agent secondReferee = agentBO
                            .getAgent(firstReferee.getReferrer());
                        amount = AmountUtil.mul(orderAmount,
                            tjAward.getValue2() / 100);
                        accountBO.transAmountCZB(fromUserId,
                            ECurrency.YJ_CNY.getCode(),
                            secondReferee.getUserId(),
                            ECurrency.YJ_CNY.getCode(), amount,
                            EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                            EBizType.AJ_TJJL.getValue(), data.getCode());

                        // 次推荐奖
                        if (StringUtils
                            .isNotBlank(secondReferee.getReferrer())) {
                            Agent thirdReferee = agentBO
                                .getAgent(secondReferee.getReferrer());
                            amount = AmountUtil.mul(orderAmount,
                                tjAward.getValue3() / 100);
                            accountBO.transAmountCZB(fromUserId,
                                ECurrency.YJ_CNY.getCode(),
                                thirdReferee.getUserId(),
                                ECurrency.YJ_CNY.getCode(), amount,
                                EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                                EBizType.AJ_TJJL.getValue(), data.getCode());
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
                || EOrderStatus.Canceled.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已申请取消喽，请勿重复申请！");
        }
        // 订单已发货或已收货无法取消
        if (!EInOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已收货，无法申请取消");
        }

        data.setStatus(EOrderStatus.TO_Cancel.getCode());
        inOrderBO.cancelInOrder(data);
    }

    @Override
    public void approveCancel(String code, String result, String updater,
            String remark) {
        InOrder data = inOrderBO.getInOrder(code);
        if (!EOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该订单未申请取消");
        }

        data.setStatus(EOrderStatus.Paid.getCode());
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EOrderStatus.Canceled.getCode());
            // 云仓提货，归还云仓库存
            if (EChannelType.NBZ.getCode().equals(data.getPayType())) {
                String toUser = data.getToUserId();
                if (StringUtils.isBlank(toUser)) {
                    toUser = ESysUser.SYS_USER_BH.getCode();
                }

                accountBO.transAmountCZB(toUser, ECurrency.YJ_CNY.getCode(),
                    data.getApplyUser(), ECurrency.YJ_CNY.getCode(),
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
            condition.setStatus(EOrderStatus.Received.getCode());
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

    private void payOrder(Agent agent, InOrder data, String wechatOrderNo) {

        // 改变产品数量
        Product pData = productBO.getProduct(data.getProductCode());
        Specs psData = specsBO.getSpecs(data.getSpecsCode());
        this.changeProductNumber(agent, pData, psData, data,
            -data.getQuantity(), data.getCode());

        // 订单归属人不是平台，托管账户与代理账户同时加钱
        if (!(StringValidater.toInteger(EAgentLevel.ONE.getCode()) == agent
            .getLevel())) {
            Account account = accountBO.getAccountByUser(agent.getUserId(),
                ECurrency.YJ_CNY.getCode());
            // 收款方账户价钱
            accountBO.changeAmount(account.getAccountNumber(),
                EChannelType.WeChat_H5, wechatOrderNo, data.getPayGroup(),
                data.getCode(), EBizType.AJ_YCCH, EBizType.AJ_YCCH.getValue(),
                data.getAmount());
            // 托管账户加钱
            accountBO.changeAmount(ESystemCode.BH.getCode(),
                EChannelType.getEChannelType(data.getPayType()), wechatOrderNo,
                data.getPayGroup(), data.getCode(), EBizType.AJ_GMYC,
                EBizType.AJ_GMYC.getValue(), data.getAmount());
        } else {
            // 订单归属人是平台，只有托管账户加钱
            accountBO.changeAmount(ESystemCode.BH.getCode(),
                EChannelType.getEChannelType(data.getPayType()), wechatOrderNo,
                data.getPayGroup(), data.getCode(), EBizType.AJ_GMYC,
                EBizType.AJ_GMYC.getValue(), data.getAmount());
        }
    }

    @Override
    @Transactional
    public void invalidInOrder(String code, String updater, String remark) {
        InOrder data = inOrderBO.getInOrder(code);
        // 非待支付与未审核订单无法作废
        if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该订单无法作废");
        }
        inOrderBO.invalidOrder(data, updater, remark);

    }

    // 删除未支付订单
    public void removeOrderTimer() {
        // 每十二个小时执行一次，删除是个小时前未支付的订单
        Date date = new Date();
        InOrder condition = new InOrder();
        condition.setStatus(EOrderStatus.Unpaid.getCode());
        condition.setEndDatetime(date);
        List<InOrder> list = inOrderBO.queryInOrderList(condition);
        for (InOrder data : list) {
            inOrderBO.removeOrder(data);
        }
    }

    // 变动产品数量
    private void changeProductNumber(Agent agent, Product pData, Specs psData,
            InOrder inOrder, Integer number, String code) {
        int minNumber = specsBO.getMinSpecsNumber(pData.getCode());
        int quantity = number * minNumber;
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
                    throw new BizException("xn00000", "上级代理云仓中没有该产品");
                } else {
                    // 改变上级云仓
                    wareBO.changeWare(toWare.getCode(), number,
                        EBizType.AJ_YCCH, EBizType.AJ_YCCH.getValue(),
                        inOrder.getCode());
                }

            } else {
                // 无上级代理,扣减产品实际库存
                specsLogBO.saveExchangeLog(pData,
                    EProductLogType.Order.getCode(), pData.getRealNumber(),
                    quantity, null);
                pData.setRealNumber(pData.getRealNumber() + quantity);
                productBO.refreshRealNumber(pData);
            }
        }
    }

    // 检查限购
    private void checkLimitNumber(Agent agent, Specs specs, AgentPrice price,
            Integer quantity) {

        // 今日已购数量
        int number = getNumber(agent.getUserId(), DateUtil.getTodayStart(),
            DateUtil.getTodayEnd()) + quantity;
        // 日限购
        if (0 != price.getDailyNumber() && number > price.getDailyNumber()) {
            throw new BizException("xn00000",
                "您今日的购买数量不能多于[" + price.getDailyNumber() + "]");
        }

        // 本周已购数量
        number = getNumber(agent.getUserId(), DateUtil.getWeeklyStart(),
            DateUtil.getWeeklyEnd()) + quantity;
        // 周限购
        if (0 != price.getWeeklyNumber() && number > price.getWeeklyNumber()) {
            throw new BizException("xn00000",
                "您今日的购买数量不能多于[" + price.getWeeklyNumber() + "]");
        }

        // 本月已购数量
        number = getNumber(agent.getUserId(), DateUtil.getMonthStart(),
            DateUtil.getMonthEnd()) + quantity;
        // 月限购
        if (0 != price.getMonthlyNumber()
                && number > price.getMonthlyNumber()) {
            throw new BizException("xn00000",
                "您今日的购买数量不能多于[" + price.getMonthlyNumber() + "]");
        }
    }

    // 日、周、月已购数量
    private int getNumber(String agentId, Date startDatetime,
            Date endDatetime) {
        int number = 0;
        List<InOrder> list = inOrderBO.getProductQuantity(agentId,
            startDatetime, endDatetime);
        for (InOrder inOrder : list) {
            Specs specs = specsBO.getSpecs(inOrder.getSpecsCode());
            number = number + specs.getNumber();
        }
        return number;
    }

}
