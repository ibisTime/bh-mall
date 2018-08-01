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

import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.ao.IWeChatAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.IMiniCodeBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.IProCodeBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.ITjAwardBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.callback.CallbackBzdhConroller;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.ChAward;
import com.bh.mall.domain.MiniCode;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.ProCode;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.TjAward;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.req.XN627645Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EOutOrderStatus;
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.EProductIsTotal;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.EProductSpecsType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
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

    @Override
    @Transactional
    public List<String> addOutOrder(XN627640Req req) {
        List<String> list = new ArrayList<String>();
        for (String code : req.getCartList()) {

            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            Specs specs = specsBO.getSpecs(cart.getSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }

            Agent applyUser = agentBO.getAgent(req.getApplyUser());
            AgentPrice apData = agentPriceBO.getPriceByLevel(specs.getCode(),
                applyUser.getLevel());

            // 检查是否可购买
            if (EBoolean.NO.getCode().equals(apData.getIsBuy())) {
                throw new BizException("xn0000", "您的等级无法购买该规格的产品");
            }
            // 订单拆单
            if (EBoolean.YES.getCode().equals(specs.getIsSingle())) {
                for (int i = 0; i < cart.getQuantity(); i++) {
                    list.add(this.addOrder(applyUser, pData, specs,
                        cart.getQuantity(), req.getApplyNote(), req.getSigner(),
                        req.getMobile(), req.getProvince(), req.getCity(),
                        req.getArea(), req.getAddress()));
                }
            } else {
                list.add(this.addOrder(applyUser, pData, specs,
                    cart.getQuantity(), req.getApplyNote(), req.getSigner(),
                    req.getMobile(), req.getProvince(), req.getCity(),
                    req.getArea(), req.getAddress()));
            }
            // 删除购物车记录
            cartBO.removeCart(cart);
        }
        return list;
    }

    @Override
    public List<String> addOutOrderC(XN627640Req req) {
        List<String> list = new ArrayList<String>();
        for (String code : req.getCartList()) {
            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            Specs psData = specsBO.getSpecs(cart.getSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }
            Agent applyUser = agentBO.getAgent(req.getApplyUser());

            // 订单拆单
            if (EBoolean.YES.getCode().equals(psData.getIsSingle())) {
                for (int i = 0; i < cart.getQuantity(); i++) {
                    outOrderBO.saveOutOrder(applyUser, pData, psData,
                        cart.getQuantity(), req.getApplyNote(), req.getSigner(),
                        req.getMobile(), req.getProvince(), req.getCity(),
                        req.getArea(), req.getAddress()));
                }
            } else {
                list.add(this.addOrder(applyUser, pData, psData,
                    cart.getQuantity(), req.getApplyNote(), req.getSigner(),
                    req.getMobile(), req.getProvince(), req.getCity(),
                    req.getArea(), req.getAddress()));
            }
            // 删除购物车记录
            cartBO.removeCart(cart);
        }
        return list;
    }

    @Override
    @Transactional
    public List<String> addOutOrderNoCart(XN627641Req req) {
        Agent applyUser = agentBO.getAgent(req.getApplyUser());
        Specs specs = specsBO.getSpecs(req.getSpecsCode());
        Product pData = productBO.getProduct(specs.getProductCode());

        List<String> list = new ArrayList<String>();
        // 获取该产品中最小规格的数量
        int minNumber = specsBO.getMinSpecsNumber(pData.getCode());
        Integer nowNumber = pData.getRealNumber()
                - (StringValidater.toInteger(req.getQuantity()) * minNumber);

        // 库存产品数量是否充足
        if (0 > nowNumber) {
            throw new BizException("xn0000", "产品库存不足");
        }
        if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
            throw new BizException("xn0000", "产品包含未上架商品,不能下单");
        }

        // 是否为授权单
        AgentLevel agent = agentLevelBO.getAgentByLevel(applyUser.getLevel());
        String kind = EOrderKind.WH_Order.getCode();

        // 未开云仓的代理，判断是否为授权单
        if (EBoolean.NO.getCode().equals(agent.getIsWare())) {
            if (outOrderBO.checkImpowerOrder(applyUser.getUserId(),
                applyUser.getImpowerDatetime())) {
                kind = EOrderKind.Impower_Order.getCode();

                // 订单金额
                AgentPrice apData = agentPriceBO
                    .getPriceByLevel(specs.getCode(), applyUser.getLevel());
                Long orderAmount = apData.getPrice()
                        * StringValidater.toInteger(req.getQuantity());
                // 订单金额不能低于授权单金额
                if (agent.getAmount() > orderAmount) {
                    throw new BizException("xn00000", agent.getName()
                            + "授权单金额为[" + agent.getAmount() / 1000 + "]元");
                }

            } else {
                kind = EOrderKind.Normal_Order.getCode();
            }
        }

        // 门槛余额是否高于限制
        AgentPrice apData = agentPriceBO.getPriceByLevel(specs.getCode(),
            applyUser.getLevel());
        Long amount = StringValidater.toInteger(req.getQuantity())
                * apData.getPrice();
        Account account = accountBO.getAccountByUser(applyUser.getUserId(),
            ECurrency.MK_CNY.getCode());

        // 门槛最低余额为零
        Long restAmount = account.getAmount() - amount;
        if (0 == agent.getMinSurplus()) {
            if (restAmount > agent.getMinSurplus()) {
                throw new BizException("xn0000",
                    "剩余门槛不能大于[" + agent.getMinSurplus() / 1000 + "]元，目前余额还有["
                            + restAmount / 1000 + "]元");
            }

        } else if (restAmount >= agent.getMinSurplus()) {
            throw new BizException("xn0000",
                "剩余门槛不能大于[" + agent.getMinSurplus() / 1000 + "]元，目前余额还有["
                        + restAmount / 1000 + "]元");
        }

        // 检查起购数量
        int minQuantity = agentPriceBO.checkMinQuantity(apData.getCode(),
            applyUser.getLevel());
        if (minQuantity > StringValidater.toInteger(req.getQuantity())) {
            throw new BizException("xn0000", "您购买的数量不能低于" + minQuantity + "]");
        }

        // 订单拆单
        if (EBoolean.YES.getCode().equals(specs.getIsSingle())
                && EBoolean.NO.getCode().equals(agent.getIsWare())) {

            int singleNumber = StringValidater.toInteger(req.getQuantity())
                    / specs.getSingleNumber();

            for (int i = 0; i < singleNumber; i++) {
                String orderCode = this.addOrder(applyUser, pData, specs,
                    specs.getSingleNumber(), req.getApplyNote(),
                    req.getSigner(), req.getMobile(), req.getProvince(),
                    req.getCity(), req.getArea(), req.getAddress());

                list.add(orderCode);
            }
        } else {
            String orderCode = this.addOrder(applyUser, pData, specs,
                StringValidater.toInteger(req.getQuantity()),
                req.getApplyNote(), req.getSigner(), req.getMobile(),
                req.getProvince(), req.getCity(), req.getArea(),
                req.getAddress());

            list.add(orderCode);
        }

        return list;

    }

    @Override
    @Transactional
    public Object payOutOrder(List<String> codeList, String payType) {
        Object result = null;
        for (String code : codeList) {
            OutOrder data = outOrderBO.getOutOrder(code);
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单未处于待支付状态");
            }
            Agent uData = agentBO.getAgent(data.getApplyUser());
            if (EUserKind.Customer.getCode().equals(uData.getKind())) {
                data.setPayType(EChannelType.WeChat_XCX.getCode());
                Object payResult = this.payWXH5(data,
                    PropertiesUtil.Config.WECHAT_XCX_ORDER_BACKURL);
                result = payResult;

            } else if (EPayType.RMB_YE.getCode().equals(payType)) {
                // 账户扣钱
                String payGroup = outOrderBO.addPayGroup(data);
                Account mkAccount = accountBO.getAccountByUser(
                    data.getApplyUser(), ECurrency.MK_CNY.getCode());
                data.setPayType(EChannelType.NBZ.getCode());
                accountBO.changeAmount(mkAccount.getAccountNumber(),
                    EChannelType.NBZ, null, payGroup, data.getCode(),
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                    -data.getAmount());
                String status = EOrderStatus.Paid.getCode();

                data.setPayDatetime(new Date());
                data.setPayCode(data.getCode());
                data.setPayAmount(data.getAmount());
                data.setStatus(status);
                outOrderBO.paySuccess(data);

                result = new BooleanRes(true);
            } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
                Object payResult = this.payWXH5(data,
                    PropertiesUtil.Config.WECHAT_H5_ORDER_BACKURL);
                result = payResult;
            }
        }
        return result;

    }

    private Object payWXH5(OutOrder data, String callBackUrl) {
        Long rmbAmount = data.getAmount();
        Agent agent = agentBO.getAgent(data.getApplyUser());
        String payGroup = outOrderBO.addPayGroup(data);
        agentBO.getAgent(data.getToUser());
        Account account = accountBO.getAccountByUser(data.getToUser(),
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

            OutOrder data = outOrderBO.getOutOrder(outTradeNo);
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

                String status = EOrderStatus.Paid.getCode();

                data.setPayDatetime(new Date());
                data.setPayCode(wechatOrderNo);
                data.setPayAmount(data.getAmount());
                data.setStatus(status);

                outOrderBO.paySuccess(data);
            } else {

                data.setStatus(EOrderStatus.Pay_NO.getCode());
                data.setPayDatetime(new Date());
                outOrderBO.payNo(data);
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

        // 获取开放云仓的等级
        List<Integer> levelList = new ArrayList<Integer>();
        List<AgentLevel> agentList = agentLevelBO.getAgentHaveWH();
        for (AgentLevel agent : agentList) {
            levelList.add(agent.getLevel());
        }
        condition.setLevelList(levelList);

        Paginable<OutOrder> page = outOrderBO.getPaginable(start, limit,
            condition);
        List<OutOrder> list = page.getList();
        for (OutOrder OutOrder : list) {
            // 下单人
            Agent agent = agentBO.getAgent(OutOrder.getApplyUser());
            OutOrder.setAgent(agent);

            // 团队长,一级代理自己是团队长
            if (1 != agent.getLevel()) {
                Agent teamLeader = agentBO.getTeamLeader(agent.getTeamName());
                OutOrder.setLeaderName(teamLeader.getRealName());
                OutOrder.setLeaderMobile(teamLeader.getMobile());
            } else {
                OutOrder.setLeaderName(agent.getRealName());
                OutOrder.setLeaderMobile(agent.getMobile());
            }

            // 订单归属人
            String toUserName = this.getName(OutOrder.getToUser());
            OutOrder.setToUserName(toUserName);

            // 收货人
            String signeName = this.getName(OutOrder.getSigner());
            OutOrder.setSigneName(signeName);

            // 审核人
            String approveUser = this.getName(OutOrder.getApprover());
            OutOrder.setApproveName(approveUser);

            // 发货人
            String deliveName = this.getName(OutOrder.getDeliver());
            OutOrder.setDeliveName(deliveName);

            // 更新人
            String updateName = this.getName(OutOrder.getUpdater());
            OutOrder.setUpdater(updateName);

            // 产品信息
            Product product = productBO.getProduct(OutOrder.getProductCode());
            OutOrder.setProduct(product);

            OutOrder.setPic(product.getAdvPic());
        }
        page.setList(list);
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
        for (OutOrder OutOrder : list) {
            // 下单人
            Agent agent = agentBO.getAgent(OutOrder.getApplyUser());
            OutOrder.setAgent(agent);

            // 团队长,一级代理自己是团队长
            if (1 != agent.getLevel()) {
                Agent teamLeader = agentBO.getTeamLeader(agent.getTeamName());
                OutOrder.setLeaderName(teamLeader.getRealName());
                OutOrder.setLeaderMobile(teamLeader.getMobile());
            } else {
                OutOrder.setLeaderName(agent.getRealName());
                OutOrder.setLeaderMobile(agent.getMobile());
            }

            // 订单归属人
            String toUserName = this.getName(OutOrder.getToUser());
            OutOrder.setToUserName(toUserName);

            // 收货人
            String signeName = this.getName(OutOrder.getSigner());
            OutOrder.setSigneName(signeName);

            // 审核人
            String approveUser = this.getName(OutOrder.getApprover());
            OutOrder.setApproveName(approveUser);

            // 发货人
            String deliveName = this.getName(OutOrder.getDeliver());
            OutOrder.setDeliveName(deliveName);

            // 更新人
            String updateName = this.getName(OutOrder.getUpdater());
            OutOrder.setUpdater(updateName);

            // 产品信息
            Product product = productBO.getProduct(OutOrder.getProductCode());
            OutOrder.setProduct(product);
        }
        return list;
    }

    @Override
    public OutOrder getOutOrder(String code) {
        OutOrder OutOrder = outOrderBO.getOutOrder(code);
        // 下单人
        Agent agent = agentBO.getAgent(OutOrder.getApplyUser());
        OutOrder.setAgent(agent);

        // 订单归属人
        String toUserName = this.getName(OutOrder.getToUser());
        OutOrder.setToUserName(toUserName);

        // 收货人
        String signeName = this.getName(OutOrder.getSigner());
        OutOrder.setSigneName(signeName);

        // 审核人
        String approveUser = this.getName(OutOrder.getApprover());
        OutOrder.setApproveName(approveUser);

        // 发货人
        String deliveName = this.getName(OutOrder.getDeliver());
        OutOrder.setDeliveName(deliveName);

        // 更新人
        String updateName = this.getName(OutOrder.getUpdater());
        OutOrder.setUpdater(updateName);

        // 产品信息
        Product product = productBO.getProduct(OutOrder.getProductCode());
        OutOrder.setProduct(product);
        return OutOrder;
    }

    @Override
    public void editOutOrder(XN627643Req req) {
        OutOrder data = outOrderBO.getOutOrder(req.getCode());
        if (EOrderStatus.TO_Deliver.getCode().equals(data.getStatus())
                || EOrderStatus.Received.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已发货");
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
        outOrderBO.refreshOutOrder(data);
    }

    private void payAward(OutOrder data) {

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
            if (StringUtils.isNotBlank(applyUser.getUserReferee())) {
                // 直接推荐人
                Agent firstReferee = agentBO
                    .getAgent(applyUser.getUserReferee());
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
                    if (StringUtils.isNotBlank(firstReferee.getUserReferee())) {
                        Agent secondReferee = agentBO
                            .getAgent(firstReferee.getUserReferee());
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
                            .isNotBlank(secondReferee.getUserReferee())) {
                            Agent thirdReferee = agentBO
                                .getAgent(secondReferee.getUserReferee());
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
    @Transactional
    public void deliverOutOrder(XN627645Req req) {
        OutOrder data = outOrderBO.getOutOrder(req.getCode());
        Agent toUser = agentBO.getAgent(data.getToUser());

        if (EBoolean.YES.getCode().equals(req.getIsCompanySend())) {
            // C端产品无法云仓发
            if (EUserKind.Customer.getCode().equals(data.getKind())) {
                throw new BizException("xn00000", "非代理的订单无法从云仓发货哦！");
            }
            if (EUserKind.Merchant.getCode().equals(toUser.getKind())) {

                AgentPrice price = agentPriceBO
                    .getPriceByLevel(data.getSpecsCode(), toUser.getLevel());
                Specs specs = specsBO.getSpecs(price.getSpecsCode());
                if (price.getMinNumber() > data.getQuantity()) {
                    throw new BizException("xn00000",
                        "该产品云仓发货不能少于" + price.getMinNumber() + specs.getName());
                }

                Ware toData = wareBO.getWareByProductSpec(data.getToUser(),
                    data.getSpecsCode());
                if (null == toData) {
                    throw new BizException("xn00000", "您的云仓中没有该规格的产品");
                }

                // 下单人为代理
                Agent applyUser = agentBO.getAgent(data.getApplyUser());
                if (EUserKind.Merchant.getCode().equals(applyUser.getKind())) {
                    AgentLevel agent = agentLevelBO
                        .getAgentByLevel(applyUser.getLevel());

                    // 没有开启云仓的发放奖励
                    if (EBoolean.NO.getCode().equals(agent.getIsWare())) {
                        // 出货以及推荐奖励
                        this.payAward(data);
                    }
                }
            }
        } else {
            // 自发
        }

        data.setDeliver(req.getDeliver());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCode());
        data.setLogisticsCompany(req.getLogisticsCompany());

        data.setIsCompanySend(req.getIsCompanySend());
        data.setStatus(EOrderStatus.TO_Deliver.getCode());
        data.setRemark(req.getRemark());

        // 是否有填写箱码或盒码
        if (StringUtils.isEmpty(req.getProCode())
                && CollectionUtils.isEmpty(req.getTraceCodeList())) {
            throw new BizException("xn000000", "请输入一个箱码或盒码！");
        }

        // 订单与箱码关联（整箱发货）
        if (StringUtils.isNotBlank(req.getProCode())) {
            data.setProCode(req.getProCode());
            // 修改箱码状态
            ProCode barData = proCodeBO.getProCode(req.getProCode());
            if (ECodeStatus.USE_YES.equals(barData.getStatus())) {
                throw new BizException("xn00000", "该箱码已经使用过");
            }
            if (ECodeStatus.SPLIT_SINGLE.equals(barData.getStatus())) {
                throw new BizException("xn00000", "该箱码已拆分");
            }
            proCodeBO.refreshProCode(barData);

            // 更新箱码关联的盒码与订单编号
            List<MiniCode> stList = miniCodeBO
                .getMiniCodeByproCode(barData.getCode());
            for (MiniCode miniCode : stList) {
                miniCodeBO.refreshStatus(miniCode, data.getCode());
            }
        }

        // 订单与盒码关联（盒装发货）
        if (CollectionUtils.isNotEmpty(req.getTraceCodeList())) {
            for (String stCode : req.getTraceCodeList()) {
                MiniCode stData = miniCodeBO.getMiniCode(stCode);
                if (EBoolean.YES.getCode().equals(stData.getStatus())) {
                    throw new BizException("xn00000", "该盒码已被使用");
                }
                miniCodeBO.refreshStatus(stData, data.getCode());
            }

            MiniCode stData = miniCodeBO
                .getMiniCode(req.getTraceCodeList().get(0));
            ProCode barData = proCodeBO.getProCode(stData.getRefCode());
            // 关联箱码不是未使用和已拆分
            if (ECodeStatus.USE_YES.getCode().equals(barData.getCode())) {
                throw new BizException("xn00000", "该箱码已被使用");
            }
            // 更新关联的箱码状态
            proCodeBO.splitSingle(barData);
        }

        outOrderBO.deliverOutOrder(data);

    }

    @Override
    public void approveOutOrder(List<String> codeList, String approver,
            String approveNote) {
        for (String code : codeList) {
            OutOrder data = outOrderBO.getOutOrder(code);
            if (!EOrderStatus.Paid.getCode().equals(data.getStatus())) {
                throw new BizException("xn00000", "该订单不处于待审核状态");
            }
            data.setStatus(EOrderStatus.TO_Apprvoe.getCode());
            data.setApprover(approver);
            data.setApproveDatetime(new Date());
            data.setApproveNote(approveNote);
            outOrderBO.approveOutOrder(data);
        }

    }

    @Override
    public void cancelOutOrder(String code) {
        OutOrder data = outOrderBO.getOutOrder(code);

        // 订单已申请取消或已取消
        if (EOrderStatus.TO_Cancel.getCode().equals(data.getStatus())
                || EOrderStatus.Canceled.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已申请取消喽，请勿重复申请！");
        }
        // 订单已发货或已收货无法取消
        if (EOrderStatus.TO_Deliver.getCode().equals(data.getStatus())
                || EOrderStatus.Received.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "订单已发货或已收货，无法申请取消");
        }

        // 授权单无法取消
        if (EOrderKind.Impower_Order.getCode().equals(data.getKind())) {
            throw new BizException("xn00000", "授权单无法取消哦！");
        }

        data.setStatus(EOrderStatus.TO_Cancel.getCode());
        outOrderBO.cancelOutOrder(data);
    }

    @Override
    public void approveCancel(String code, String result, String updater,
            String remark) {
        OutOrder data = outOrderBO.getOutOrder(code);
        if (!EOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该订单未申请取消");
        }

        data.setStatus(EOrderStatus.Paid.getCode());
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EOrderStatus.Canceled.getCode());
            if (EChannelType.NBZ.getCode().equals(data.getPayType())) {
                String toUser = data.getToUser();
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
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        outOrderBO.approveCancel(data);

    }

    @Override
    public void receivedOutOrder(String code) {
        OutOrder data = outOrderBO.getOutOrder(code);
        data.setStatus(EOrderStatus.Received.getCode());
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        outOrderBO.receivedOutOrder(data);
    }

    private String getName(String agent) {
        if (StringUtils.isBlank(agent)) {
            return null;
        }
        if (EUser.ADMIN.getCode().equals(agent)) {
            return agent;
        }
        String name = agent;
        Agent data = agentBO.getAgent(agent);
        if (data != null) {
            name = data.getRealName();
            if (EUserKind.Plat.getCode().equals(data.getKind())
                    && StringUtils.isBlank(data.getRealName())) {
                name = data.getLoginName();
            }
        }
        return name;
    }

    private String checkOrder(Agent applyUser, Specs specs) {
        String kind = EOrderKind.Normal_Order.getCode();
        // 是否完成授权单
        if (EUserStatus.IMPOWERED.getCode().equals(applyUser.getStatus())) {
            if (!outOrderBO.checkImpowerOrder(applyUser.getUserId(),
                applyUser.getImpowerDatetime())) {
                kind = EOrderKind.Impower_Order.getCode();
                if (EProductSpecsType.Apply_NO.getCode()
                    .equals(specs.getIsImpowerOrder())) {
                    throw new BizException("xn0000", "该产品规格不予授权单单下单");
                }
            }
        }

        // 是否完成升级单
        // boolean upgradeOrder = this.CheckImpowerOrder(applyUser);
        if (EUserStatus.UPGRADED.getCode().equals(applyUser.getStatus())) {
            // if (!upgradeOrder) {
            // kind = EOrderKind.Upgrade_Order.getCode();
            // }
            if (EProductSpecsType.Apply_NO.getCode()
                .equals(specs.getIsUpgradeOrder())) {
                throw new BizException("xn0000", "该产品规格不予授权单单下单");
            }
        }

        // 是否允许普通单下单
        if (EProductSpecsType.Apply_NO.getCode()
            .equals(specs.getIsNormalOrder())) {
            throw new BizException("xn0000", "该产品规格不予许普通单下单");
        }
        return kind;
    }

    // 变动产品数量
    private void changeProductNumber(Agent agent, Product pData, Specs specs,
            OutOrder OutOrder, Integer number, String code) {
        int minNumber = specsBO.getMinSpecsNumber(pData.getCode());
        int quantity = number * minNumber;

        // 有上级代理,扣减上级代理云仓,且自己开启云仓
        AgentLevel agentLevel = agentLevelBO.getAgentByLevel(agent.getLevel());
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == agent
            .getLevel()
                && EBoolean.YES.getCode().equals(agentLevel.getIsWare())) {
            Ware toWare = wareBO.getWareByProductSpec(OutOrder.getToUser(),
                OutOrder.getSpecsCode());
            // 上级云仓没有该产品
            if (null == toWare) {
                throw new BizException("xn00000", "上级代理云仓中没有该产品");
            } else {
                // 改变上级云仓
                wareBO.changeWare(toWare.getCode(), number, EBizType.AJ_YCCH,
                    EBizType.AJ_YCCH.getValue(), OutOrder.getCode());
            }

        } else if (EBoolean.YES.getCode().equals(agentLevel.getIsWare())) {
            // 无上级代理,扣减产品实际库存
            specsLogBO.saveExchangeLog(pData, EProductLogType.Order.getCode(),
                pData.getRealNumber(), quantity, null);
            pData.setRealNumber(pData.getRealNumber() + quantity);
            productBO.refreshRealNumber(pData);
        }
    }

    // 检查介绍奖与推荐奖是否同时存在
    private boolean checkAward(Agent agent) {
        // 介绍人与推荐人同时存在
        if (StringUtils.isNotBlank(agent.getIntroducer())
                && StringUtils.isNotBlank(agent.getUserReferee())) {
            // 下单金额是否超过授权金额
            List<String> statusList = new ArrayList<String>();
            statusList.add(EOrderStatus.Paid.getCode());
            statusList.add(EOrderStatus.TO_Apprvoe.getCode());
            statusList.add(EOrderStatus.TO_Deliver.getCode());
            statusList.add(EOrderStatus.Received.getCode());

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

    private String addOrder(Agent applyUser, Product pData, Specs specs,
            int quantity, String applyNote, String signer, String mobile,
            String province, String city, String area, String address) {

        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        data.setCode(code);
        data.setProductCode(pData.getCode());
        data.setProductName(pData.getName());
        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());

        data.setToUser(applyUser.getHighUserId());
        data.setQuantity(quantity);
        // data.setPrice(apData.getPrice());
        // Long amount = quantity * apData.getPrice();

        data.setPic(pData.getAdvPic());
        data.setApplyUser(applyUser.getUserId());
        // data.setAmount(amount);
        data.setApplyDatetime(new Date());
        data.setApplyNote(applyNote);
        data.setStatus(EOutOrderStatus.Unpaid.getCode());

        outOrderBO.saveOutOrder(data);
        return code;
    }

    private void payOrder(Agent agent, OutOrder data, String wechatOrderNo) {

        // 改变产品数量
        Product pData = productBO.getProduct(data.getProductCode());
        Specs specs = specsBO.getSpecs(data.getSpecsCode());
        this.changeProductNumber(agent, pData, specs, data, -data.getQuantity(),
            data.getCode());

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
    public void invalidOutOrder(String code, String updater, String remark) {

        OutOrder data = outOrderBO.getOutOrder(code);
        // 非待支付与未审核订单无法作废
        if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())
                || !EOrderStatus.Paid.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该订单无法作废");
        }
        outOrderBO.invalidOutOrder(data, updater, remark);

    }

    @Override
    public void checkLimitNumber(Agent agent, Specs psData, AgentPrice pspData,
            Integer quantity) {
    }

    // 删除未支付订单
    public void removeOrderTimer() {
        // 每十二个小时执行一次，删除是个小时前未支付的订单
        Date date = new Date();
        OutOrder condition = new OutOrder();
        condition.setStatus(EOrderStatus.Unpaid.getCode());
        condition.setEndDatetime(date);
        List<OutOrder> list = outOrderBO.queryOutOrderList(condition);
        for (OutOrder data : list) {
            outOrderBO.removeOutOrder(data);
        }
    }
}
