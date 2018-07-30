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
import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.ISYSConfigBO;
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
import com.bh.mall.domain.Award;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.ChAward;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAwardType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.EProductIsTotal;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
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
    IProductSpecsBO productSpecsBO;

    @Autowired
    IProductSpecsPriceBO productSpecsPriceBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IAwardBO awardBO;

    @Autowired
    IWareBO wareBO;

    @Autowired
    IWeChatAO weChatAO;

    @Autowired
    IProductLogBO productLogBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IChAwardBO chAwardBO;

    @Override
    @Transactional
    public List<String> addInOrder(XN627640Req req) {
        List<String> list = new ArrayList<String>();
        for (String code : req.getCartList()) {
            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            ProductSpecs psData = productSpecsBO
                .getProductSpecs(cart.getProductSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }
            Agent applyUser = agentBO.getAgent(req.getApplyUser());

            // 订单拆单
            if (EBoolean.YES.getCode().equals(psData.getIsSingle())) {
                for (int i = 0; i < cart.getQuantity(); i++) {
                    list.add(this.addOrder(applyUser, pData, psData,
                        cart.getQuantity(), req.getApplyNote(), req.getSigner(),
                        req.getMobile(), req.getProvince(), req.getCity(),
                        req.getArea(), req.getAddress(), null));
                }
            } else {
                list.add(this.addOrder(applyUser, pData, psData,
                    cart.getQuantity(), req.getApplyNote(), req.getSigner(),
                    req.getMobile(), req.getProvince(), req.getCity(),
                    req.getArea(), req.getAddress(), null));
            }
            // 删除购物车记录
            cartBO.removeCart(cart);
        }
        return list;
    }

    @Override
    @Transactional
    public List<String> addInOrderNoCart(XN627641Req req) {
        Agent applyUser = agentBO.getAgent(req.getApplyUser());
        ProductSpecs psData = productSpecsBO
            .getProductSpecs(req.getProductSpecsCode());
        Product pData = productBO.getProduct(psData.getProductCode());

        List<String> list = new ArrayList<String>();
        // 获取该产品中最小规格的数量
        int minNumber = productSpecsBO.getMinSpecsNumber(pData.getCode());
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
            if (inOrderBO.checkImpowerOrder(applyUser.getUserId(),
                applyUser.getImpowerDatetime())) {
                kind = EOrderKind.Impower_Order.getCode();

                // 订单金额
                ProductSpecsPrice pspData = productSpecsPriceBO
                    .getPriceByLevel(psData.getCode(), applyUser.getLevel());
                Long orderAmount = pspData.getPrice()
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
        ProductSpecsPrice pspData = productSpecsPriceBO
            .getPriceByLevel(psData.getCode(), applyUser.getLevel());
        Long amount = StringValidater.toInteger(req.getQuantity())
                * pspData.getPrice();
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
        int minQuantity = productSpecsPriceBO
            .checkMinQuantity(pspData.getCode(), applyUser.getLevel());
        if (minQuantity > StringValidater.toInteger(req.getQuantity())) {
            throw new BizException("xn0000", "您购买的数量不能低于" + minQuantity + "]");
        }

        // 订单拆单
        if (EBoolean.YES.getCode().equals(psData.getIsSingle())
                && EBoolean.NO.getCode().equals(agent.getIsWare())) {

            int singleNumber = StringValidater.toInteger(req.getQuantity())
                    / psData.getSingleNumber();

            for (int i = 0; i < singleNumber; i++) {
                String orderCode = this.addOrder(applyUser, pData, psData,
                    psData.getSingleNumber(), req.getApplyNote(),
                    req.getSigner(), req.getMobile(), req.getProvince(),
                    req.getCity(), req.getArea(), req.getAddress(), kind);

                list.add(orderCode);
            }
        } else {
            String orderCode = this.addOrder(applyUser, pData, psData,
                StringValidater.toInteger(req.getQuantity()),
                req.getApplyNote(), req.getSigner(), req.getMobile(),
                req.getProvince(), req.getCity(), req.getArea(),
                req.getAddress(), kind);

            list.add(orderCode);
        }

        return list;

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
            if (EUserKind.Customer.getCode().equals(uData.getKind())) {
                data.setPayType(EChannelType.WeChat_XCX.getCode());
                Object payResult = this.payWXH5(data,
                    PropertiesUtil.Config.WECHAT_XCX_ORDER_BACKURL);
                result = payResult;

            } else if (EPayType.RMB_YE.getCode().equals(payType)) {
                // 账户扣钱
                String payGroup = inOrderBO.addPayGroup(data);
                Account mkAccount = accountBO.getAccountByUser(
                    data.getApplyUser(), ECurrency.MK_CNY.getCode());
                data.setPayType(EChannelType.NBZ.getCode());
                accountBO.changeAmount(mkAccount.getAccountNumber(),
                    EChannelType.NBZ, null, payGroup, data.getCode(),
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                    -data.getAmount());
                String status = EOrderStatus.Paid.getCode();

                // 代理下单
                if (EUserKind.Merchant.getCode().equals(uData.getKind())) {
                    // 该等级是否启用云仓
                    AgentLevel agent = agentLevelBO
                        .getAgentByLevel(uData.getLevel());
                    if (EBoolean.YES.getCode().equals(agent.getIsWare())) {
                        status = EOrderStatus.Received.getCode();
                        // 购买云仓
                        wareBO.buyWare(data, uData);
                        // 出货以及推荐奖励
                        this.payAward(data);
                    }
                }

                data.setPayDatetime(new Date());
                data.setPayCode(data.getCode());
                data.setPayAmount(data.getAmount());
                data.setStatus(status);
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

                String status = EOrderStatus.Paid.getCode();
                // 代理进货且是购买云仓
                if (EUserKind.Merchant.getCode().equals(agent.getKind())) {
                    AgentLevel agentLevel = agentLevelBO
                        .getAgentByLevel(agent.getLevel());
                    if (EBoolean.YES.getCode().equals(agentLevel.getIsWare())) {
                        status = EOrderStatus.Received.getCode();
                        // 购买云仓
                        wareBO.buyWare(data, agent);
                        // 出货以及推荐奖励
                        this.payAward(data);
                    }
                }

                data.setPayDatetime(new Date());
                data.setPayCode(wechatOrderNo);
                data.setPayAmount(data.getAmount());
                data.setStatus(status);

                inOrderBO.paySuccess(data);
            } else {

                data.setStatus(EOrderStatus.Pay_NO.getCode());
                data.setPayDatetime(new Date());
                inOrderBO.payNo(data);
            }
        } catch (JDOMException | IOException e) {
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

        // 获取开放云仓的等级
        List<Integer> levelList = new ArrayList<Integer>();
        List<AgentLevel> agentList = agentLevelBO.getAgentHaveWH();
        for (AgentLevel agent : agentList) {
            levelList.add(agent.getLevel());
        }
        condition.setLevelList(levelList);

        Paginable<InOrder> page = inOrderBO.getPaginable(start, limit,
            condition);
        List<InOrder> list = page.getList();
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

            // 订单归属人
            String toUserName = this.getName(inOrder.getToUser());
            inOrder.setToUserName(toUserName);

            // 更新人
            String updateName = this.getName(inOrder.getUpdater());
            inOrder.setUpdater(updateName);

            // 产品信息
            Product product = productBO.getProduct(inOrder.getProductCode());
            inOrder.setProduct(product);

            // TODO 图片名字乱码
            inOrder.setPic(product.getAdvPic());
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<InOrder> queryInOrderList(InOrder condition) {
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

            // 订单归属人
            String toUserName = this.getName(inOrder.getToUser());
            inOrder.setToUserName(toUserName);

            // 更新人
            String updateName = this.getName(inOrder.getUpdater());
            inOrder.setUpdater(updateName);

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

        // 订单归属人
        String toUserName = this.getName(inOrder.getToUser());
        inOrder.setToUserName(toUserName);

        // 更新人
        String updateName = this.getName(inOrder.getUpdater());
        inOrder.setUpdater(updateName);

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
            if (StringUtils.isNotBlank(applyUser.getUserReferee())) {
                // 直接推荐人
                Agent firstReferee = agentBO
                    .getAgent(applyUser.getUserReferee());
                Award aData = awardBO.getAwardByType(applyUser.getLevel(),
                    data.getProductCode(), EAwardType.DirectAward.getCode());

                Long amount = 0L;
                // 直接推荐奖
                if (null != firstReferee) {
                    amount = AmountUtil.mul(orderAmount,
                        aData.getValue1() / 100);
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
                            aData.getValue2() / 100);
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
                                aData.getValue3() / 100);
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
            if (EOrderKind.Pick_Up.getCode().equals(data.getKind())) {
                Agent applyUser = agentBO.getAgent(data.getApplyUser());
                wareBO.buyWare(data, applyUser);
            } else if (EChannelType.NBZ.getCode().equals(data.getPayType())) {
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
        inOrderBO.approveCancel(data);

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

    // 日、周、月已购数量
    private int getNumber(String agentId, Date startDatetime,
            Date endDatetime) {
        int number = 0;
        List<InOrder> list = inOrderBO.getProductQuantity(agentId,
            startDatetime, endDatetime);
        for (InOrder inOrder : list) {
            ProductSpecs ps = productSpecsBO
                .getProductSpecs(inOrder.getProductSpecsCode());
            number = number + ps.getNumber();
        }
        return number;
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

            InOrder condition = new InOrder();
            condition.setApplyUser(agent.getUserId());
            condition.setStatus(EOrderStatus.Received.getCode());
            condition.setStatusList(statusList);

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

    private String addOrder(Agent applyUser, Product pData, ProductSpecs psData,
            int quantity, String applyNote, String signer, String mobile,
            String province, String city, String area, String address,
            String kind) {

        InOrder inOrder = new InOrder();
        ProductSpecsPrice pspData = productSpecsPriceBO
            .getPriceByLevel(psData.getCode(), applyUser.getLevel());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        inOrder.setCode(code);
        inOrder.setProductCode(pData.getCode());
        inOrder.setProductName(pData.getName());
        inOrder.setProductSpecsCode(psData.getCode());
        inOrder.setProductSpecsName(psData.getName());

        inOrder.setToUser(applyUser.getHighUserId());
        inOrder.setQuantity(quantity);
        inOrder.setPrice(pspData.getPrice());
        Long amount = quantity * pspData.getPrice();
        Long yunfei = 0L;

        // 下单人是否是代理
        if (EUserKind.Merchant.getCode().equals(applyUser.getKind())) {

            // 检查是否可购买
            if (EBoolean.NO.getCode().equals(pspData.getIsBuy())) {

                throw new BizException("xn0000", "您的等级无法购买该规格的产品");
            }

            AgentLevel agent = agentLevelBO
                .getAgentByLevel(applyUser.getLevel());
            if (EBoolean.NO.getCode().equals(agent.getIsWare())) {

                // 产品不包邮，计算运费
                if (EBoolean.NO.getCode().equals(pData.getIsFree())) {
                    SYSConfig sysConfig = sysConfigBO.getConfig(province,
                        ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                    yunfei = StringValidater.toLong(sysConfig.getCvalue());
                }
            }
        }

        inOrder.setKind(kind);
        inOrder.setPic(pData.getAdvPic());
        inOrder.setApplyUser(applyUser.getUserId());
        inOrder.setStatus(EOrderStatus.Unpaid.getCode());

        inOrderBO.saveInOrder(inOrder);
        return code;
    }

    private void payOrder(Agent agent, InOrder data, String wechatOrderNo) {

        // 改变产品数量
        Product pData = productBO.getProduct(data.getProductCode());
        ProductSpecs psData = productSpecsBO
            .getProductSpecs(data.getProductSpecsCode());
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
        if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())
                || !EOrderStatus.Paid.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该订单无法作废");
        }
        inOrderBO.invalidOrder(data, updater, remark);

        // 提货单归还库存
        if (EOrderKind.Pick_Up.getCode().equals(data.getKind())) {
            Agent agent = agentBO.getAgent(data.getApplyUser());
            wareBO.buyWare(data, agent);
        }
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

}
