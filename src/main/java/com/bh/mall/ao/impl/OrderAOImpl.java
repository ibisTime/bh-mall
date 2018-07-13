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

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.ao.IWeChatAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.IAwardIntervalBO;
import com.bh.mall.bo.IBarCodeBO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISecurityTraceBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.IWareHouseSpecsBO;
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
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.Award;
import com.bh.mall.domain.AwardInterval;
import com.bh.mall.domain.BarCode;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.SecurityTrace;
import com.bh.mall.domain.User;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.req.XN627645Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.enums.EAwardType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderStatus;
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
public class OrderAOImpl implements IOrderAO {
    private static Logger logger = Logger
        .getLogger(CallbackBzdhConroller.class);

    @Autowired
    IOrderBO orderBO;

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
    IUserBO userBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IAwardBO awardBO;

    @Autowired
    IAgencyLogBO agencyLogBO;

    @Autowired
    IWareHouseBO wareHouseBO;

    @Autowired
    IWeChatAO weChatAO;

    @Autowired
    IProductLogBO productLogBO;

    @Autowired
    IWareHouseSpecsBO wareHouseSpecsBO;

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAwardIntervalBO awardIntervalBO;

    @Autowired
    IBarCodeBO barCodeBO;

    @Autowired
    ISecurityTraceBO securityTraceBO;

    @Override
    @Transactional
    public List<String> addOrder(XN627640Req req) {
        List<String> list = new ArrayList<String>();
        for (String code : req.getCartList()) {
            Cart cart = cartBO.getCart(code);
            Product pData = productBO.getProduct(cart.getProductCode());
            ProductSpecs psData = productSpecsBO
                .getProductSpecs(cart.getProductSpecsCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }
            User applyUser = userBO.getUser(req.getApplyUser());

            // 订单拆单
            if (EBoolean.YES.getCode().equals(psData.getIsSingle())) {
                for (int i = 0; i < cart.getQuantity(); i++) {
                    list.add(this.addOrder(applyUser, pData, psData,
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
    public List<String> addOrderNoCart(XN627641Req req) {
        User applyUser = userBO.getUser(req.getApplyUser());
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
        Agent agent = agentBO.getAgentByLevel(applyUser.getLevel());
        if (orderBO.checkImpowerOrder(applyUser.getUserId())) {
            // 订单金额
            ProductSpecsPrice pspData = productSpecsPriceBO
                .getPriceByLevel(psData.getCode(), applyUser.getLevel());
            Long orderAmount = pspData.getPrice()
                    * StringValidater.toInteger(req.getQuantity());
            // 订单金额不能低于授权单金额
            if (agent.getAmount() > orderAmount) {
                throw new BizException("xn00000", agent.getName() + "授权单金额为["
                        + agent.getAmount() / 1000 + "]元");
            }

        }

        // 订单拆单
        if (EBoolean.YES.getCode().equals(psData.getIsSingle())
                && EBoolean.NO.getCode().equals(agent.getIsWareHouse())) {

            int singleNumber = StringValidater.toInteger(req.getQuantity())
                    / psData.getSingleNumber();

            for (int i = 0; i < StringValidater
                .toInteger(req.getQuantity()); i++) {
                list.add(this.addOrder(applyUser, pData, psData, singleNumber,
                    req.getApplyNote(), req.getSigner(), req.getMobile(),
                    req.getProvince(), req.getCity(), req.getArea(),
                    req.getAddress()));
            }
        } else {
            list.add(this.addOrder(applyUser, pData, psData,
                StringValidater.toInteger(req.getQuantity()),
                req.getApplyNote(), req.getSigner(), req.getMobile(),
                req.getProvince(), req.getCity(), req.getArea(),
                req.getAddress()));
        }
        return list;

    }

    @Override
    @Transactional
    public Object payOrder(List<String> codeList, String payType) {
        Object result = null;
        for (String code : codeList) {
            System.out.println(code);
            Order data = orderBO.getOrder(code);
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单未处于待支付状态");
            }
            User uData = userBO.getUser(data.getApplyUser());
            if (EUserKind.Customer.getCode().equals(uData.getKind())) {
                data.setPayType(EChannelType.WeChat_XCX.getCode());
                Object payResult = this.payWXH5(data,
                    PropertiesUtil.Config.WECHAT_XCX_ORDER_BACKURL);
                result = payResult;

            } else if (EPayType.RMB_YE.getCode().equals(payType)) {
                // 账户扣钱
                String payGroup = orderBO.addPayGroup(data);
                Account mkAccount = accountBO.getAccountByUser(
                    data.getApplyUser(), ECurrency.MK_CNY.getCode());
                accountBO.changeAmount(mkAccount.getAccountNumber(),
                    EChannelType.NBZ, null, payGroup, data.getCode(),
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                    -data.getAmount());
                String status = EOrderStatus.Paid.getCode();

                // 代理下单
                if (EUserKind.Merchant.getCode().equals(uData.getKind())) {

                    // 该等级是否启用云仓
                    Agent agent = agentBO.getAgentByLevel(uData.getLevel());
                    if (EBoolean.YES.getCode().equals(agent.getIsWareHouse())) {
                        status = EOrderStatus.Received.getCode();
                        // 购买云仓
                        wareHouseBO.buyWareHouse(data, uData);
                        // 出货以及推荐奖励
                        this.payAward(data);
                    }

                }
                data.setPayDatetime(new Date());
                data.setPayCode(data.getCode());
                data.setPayAmount(data.getAmount());
                data.setStatus(status);
                orderBO.paySuccess(data);

                result = new BooleanRes(true);
            } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
                data.setPayType(EChannelType.WeChat_H5.getCode());
                Object payResult = this.payWXH5(data,
                    PropertiesUtil.Config.WECHAT_H5_ORDER_BACKURL);
                result = payResult;
            }

        }
        return result;

    }

    private Object payWXH5(Order data, String callBackUrl) {
        Long rmbAmount = data.getAmount();
        User user = userBO.getCheckUser(data.getApplyUser());
        String payGroup = orderBO.addPayGroup(data);
        userBO.getCheckUser(data.getToUser());
        Account account = accountBO.getAccountByUser(data.getToUser(),
            ECurrency.YJ_CNY.getCode());
        System.out.println(data.getPayType());
        return weChatAO.getPrepayIdH5(user.getUserId(),
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

            Order data = orderBO.getOrder(outTradeNo);
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单已支付");
            }

            // 此处调用订单查询接口验证是否交易成功
            boolean isSucc = weChatAO.reqOrderquery(map,
                EChannelType.WeChat_H5.getCode());
            if (isSucc) {

                User user = userBO.getUser(data.getToUser());
                Account account = accountBO.getAccountByUser(user.getUserId(),
                    ECurrency.YJ_CNY.getCode());
                if (StringUtils.isBlank(account.getAccountNumber())) {
                    throw new BizException("xn0000", "收款人账户不存在");
                }
                // 收款方账户价钱
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.WeChat_H5, wechatOrderNo, data.getPayGroup(),
                    data.getCode(), EBizType.AJ_YCCH,
                    EBizType.AJ_YCCH.getValue(), data.getAmount());
                // 托管账户加钱
                accountBO.changeAmount(ESystemCode.BH.getCode(),
                    EChannelType.getEChannelType(data.getPayType()),
                    wechatOrderNo, data.getPayGroup(), data.getCode(),
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                    data.getAmount());

                String status = EOrderStatus.Paid.getCode();
                // 代理进货且是购买云仓
                User applyUser = userBO.getUser(data.getApplyUser());
                if (EUserKind.Merchant.getCode().equals(applyUser.getKind())) {
                    Agent agent = agentBO.getAgentByLevel(user.getLevel());
                    if (EBoolean.YES.getCode().equals(agent.getIsWareHouse())) {
                        status = EOrderStatus.Received.getCode();
                        // 购买云仓
                        wareHouseBO.buyWareHouse(data, applyUser);
                        // 出货以及推荐奖励
                        this.payAward(data);
                    }

                }

                data.setPayDatetime(new Date());
                data.setPayCode(wechatOrderNo);
                data.setPayAmount(data.getAmount());
                data.setStatus(status);

                orderBO.paySuccess(data);
            } else {
                data.setStatus(EOrderStatus.Pay_NO.getCode());
                data.setPayDatetime(new Date());
                orderBO.payNo(data);
            }

        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }

    }

    @Override
    public Paginable<Order> queryOrderPage(int start, int limit,
            Order condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<Order> page = orderBO.getPaginable(start, limit, condition);
        List<Order> list = page.getList();
        for (Order order : list) {
            // 下单人
            User user = userBO.getCheckUser(order.getApplyUser());
            order.setUser(user);

            // 订单归属人
            String toUserName = this.getName(order.getToUser());
            order.setToUserName(toUserName);

            // 收货人
            String signeName = this.getName(order.getSigner());
            order.setSigneName(signeName);

            // 审核人
            String approveUser = this.getName(order.getApprover());
            order.setApproveName(approveUser);

            // 发货人
            String deliveName = this.getName(order.getDeliver());
            order.setDeliveName(deliveName);

            // 更新人
            String updateName = this.getName(order.getUpdater());
            order.setUpdater(updateName);

            // 产品信息
            Product product = productBO.getProduct(order.getProductCode());
            order.setProduct(product);
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<Order> queryOrderList(Order condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<Order> list = orderBO.queryOrderList(condition);
        for (Order order : list) {
            // 下单人
            User user = userBO.getCheckUser(order.getApplyUser());
            order.setUser(user);

            // 订单归属人
            String toUserName = this.getName(order.getToUser());
            order.setToUserName(toUserName);

            // 收货人
            String signeName = this.getName(order.getSigner());
            order.setSigneName(signeName);

            // 审核人
            String approveUser = this.getName(order.getApprover());
            order.setApproveName(approveUser);

            // 发货人
            String deliveName = this.getName(order.getDeliver());
            order.setDeliveName(deliveName);

            // 更新人
            String updateName = this.getName(order.getUpdater());
            order.setUpdater(updateName);

            // 产品信息
            Product product = productBO.getProduct(order.getProductCode());
            order.setProduct(product);
        }
        return list;
    }

    @Override
    public Order getOrder(String code) {
        Order order = orderBO.getOrder(code);
        // 下单人
        User user = userBO.getCheckUser(order.getApplyUser());
        order.setUser(user);

        // 订单归属人
        String toUserName = this.getName(order.getToUser());
        order.setToUserName(toUserName);

        // 收货人
        String signeName = this.getName(order.getSigner());
        order.setSigneName(signeName);

        // 审核人
        String approveUser = this.getName(order.getApprover());
        order.setApproveName(approveUser);

        // 发货人
        String deliveName = this.getName(order.getDeliver());
        order.setDeliveName(deliveName);

        // 更新人
        String updateName = this.getName(order.getUpdater());
        order.setUpdater(updateName);

        // 产品信息
        Product product = productBO.getProduct(order.getProductCode());
        order.setProduct(product);
        return order;
    }

    @Override
    public void editOrder(XN627643Req req) {
        Order data = orderBO.getOrder(req.getCode());
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
        orderBO.refreshOrder(data);
    }

    private void payAward(Order data) {
        // 订单归属人不是平台
        Product product = productBO.getProduct(data.getProductCode());
        // 获取订单归属人及其上级
        User toUser = userBO.getUser(data.getApplyUser());
        String fromUserId = ESysUser.SYS_USER_BH.getCode();
        Long orderAmount = data.getAmount();
        // 有上级，且不是平台
        if (StringUtils.isNotBlank(toUser.getHighUserId())
                && !EUser.ADMIN.getCode().equals(toUser.getHighUserId())) {
            User highUser = userBO.getUser(toUser.getHighUserId());
            if (EUserKind.Merchant.getCode().equals(highUser.getKind())) {
                fromUserId = toUser.getHighUserId();
            }
        }

        // **********出货奖*******
        // 出货奖励,且产品计入出货
        if (EProductIsTotal.YES.getCode().equals(product.getIsTotal())) {
            AwardInterval award = awardIntervalBO
                .getAwardIntervalByLevel(toUser.getLevel(), data.getAmount());
            if (award != null) {
                Long awardAmount = AmountUtil.mul(orderAmount,
                    award.getPercent() / 100);
                accountBO.transAmountCZB(fromUserId, ECurrency.YJ_CNY.getCode(),
                    toUser.getUserId(), ECurrency.YJ_CNY.getCode(), awardAmount,
                    EBizType.AJ_CHJL, EBizType.AJ_CHJL.getValue(),
                    EBizType.AJ_CHJL.getValue(), data.getCode());
            }

        }

        // **********推荐奖**********
        // 是否有推荐人
        if (this.checkAward(toUser)) {
            if (StringUtils.isNotBlank(toUser.getUserReferee())) {
                // 直接推荐人
                User firstUser = userBO.getUser(toUser.getUserReferee());
                Award aData = awardBO.getAwardByType(toUser.getLevel(),
                    data.getProductCode(), EAwardType.DirectAward.getCode());

                Long amount = 0L;
                // 直接推荐奖
                if (firstUser != null) {
                    amount = AmountUtil.mul(orderAmount,
                        aData.getValue1() / 100);
                    accountBO.transAmountCZB(fromUserId,
                        ECurrency.YJ_CNY.getCode(), firstUser.getUserId(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_TJJL,
                        EBizType.AJ_TJJL.getValue(),
                        EBizType.AJ_TJJL.getValue(), data.getCode());

                    // 间接推荐奖
                    if (StringUtils.isNotBlank(firstUser.getUserReferee())) {
                        User secondUser = userBO
                            .getUser(firstUser.getUserReferee());
                        amount = AmountUtil.mul(orderAmount,
                            aData.getValue2() / 100);
                        accountBO.transAmountCZB(fromUserId,
                            ECurrency.YJ_CNY.getCode(), secondUser.getUserId(),
                            ECurrency.YJ_CNY.getCode(), amount,
                            EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                            EBizType.AJ_TJJL.getValue(), data.getCode());

                        // 次推荐奖
                        if (StringUtils
                            .isNotBlank(secondUser.getUserReferee())) {
                            User thirdUser = userBO
                                .getUser(secondUser.getUserReferee());
                            amount = AmountUtil.mul(orderAmount,
                                aData.getValue3() / 100);
                            accountBO.transAmountCZB(fromUserId,
                                ECurrency.YJ_CNY.getCode(),
                                thirdUser.getUserId(),
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
    public void deliverOrder(XN627645Req req) {

        Order data = orderBO.getOrder(req.getCode());
        String toUserId = ESysUser.SYS_USER_BH.getCode();
        if (EBoolean.YES.getCode().equals(req.getIsCompanySend())) {
            // C端产品无法云仓发
            if (EUserKind.Customer.getCode().equals(data.getKind())) {
                throw new BizException("xn00000", "非代理的订单无法从云仓发货哦！");
            }

            User toUser = userBO.getUser(data.getToUser());
            if (EUserKind.Merchant.getCode().equals(toUser.getKind())) {

                toUserId = toUser.getUserId();
                ProductSpecsPrice psp = productSpecsPriceBO.getPriceByLevel(
                    data.getProductSpecsCode(), toUser.getLevel());
                ProductSpecs ps = productSpecsBO
                    .getProductSpecs(psp.getProductSpecsCode());
                if (psp.getMinNumber() > data.getQuantity()) {
                    throw new BizException("xn00000",
                        "该产品云仓发货不能少于" + psp.getMinNumber() + ps.getName());
                }

                WareHouse toData = wareHouseBO.getWareHouseByProductSpec(
                    data.getToUser(), data.getProductSpecsCode());
                if (null == toData) {
                    throw new BizException("xn00000", "您的云仓中没有该规格的产品");
                }

                User applyUser = userBO.getUser(data.getApplyUser());
                Agent agent = agentBO.getAgentByLevel(applyUser.getLevel());
                // 没有开启云仓的发放奖励
                if (EBoolean.NO.getCode().equals(agent.getIsWareHouse())) {
                    // 出货以及推荐奖励
                    this.payAward(data);
                }
            }
        }

        // 卖货后赚的钱
        Account account = accountBO.getAccountByUser(toUserId,
            ECurrency.YJ_CNY.getCode());
        accountBO.changeAmount(account.getAccountNumber(), EChannelType.NBZ,
            null, data.getPayGroup(), data.getCode(), EBizType.AJ_CELR,
            EBizType.AJ_CELR.getValue(), data.getAmount());

        data.setDeliver(req.getDeliver());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCode());
        data.setLogisticsCompany(req.getLogisticsCompany());
        data.setPdf(req.getPdf());

        data.setIsCompanySend(req.getIsCompanySend());
        data.setStatus(EOrderStatus.TO_Deliver.getCode());
        data.setRemark(req.getRemark());

        // 是否有填写箱码或盒码
        if (StringUtils.isEmpty(req.getBarCode())
                && CollectionUtils.isEmpty(req.getTraceCodeList())) {
            throw new BizException("xn000000", "请输入一个箱码或盒码！");
        }

        // 订单与箱码关联（整箱发货）
        if (StringUtils.isNotBlank(req.getBarCode())) {
            data.setBarCode(req.getBarCode());
            // 修改箱码状态
            BarCode barData = barCodeBO.getBarCode(req.getBarCode());
            if (ECodeStatus.USE_YES.equals(barData.getStatus())) {
                throw new BizException("xn00000", "该箱码已经使用过");
            }
            if (ECodeStatus.SPLIT_SINGLE.equals(barData.getStatus())) {
                throw new BizException("xn00000", "该箱码已拆分");
            }
            barCodeBO.refreshBarCode(barData);

            // 更新箱码关联的盒码与订单编号
            List<SecurityTrace> stList = securityTraceBO
                .getSecurityTraceByBarCode(barData.getCode());
            for (SecurityTrace securityTrace : stList) {
                securityTraceBO.refreshStatus(securityTrace, data.getCode());
            }
        }

        // 订单与盒码关联（盒装发货）
        if (CollectionUtils.isNotEmpty(req.getTraceCodeList())) {
            for (String stCode : req.getTraceCodeList()) {
                SecurityTrace stData = securityTraceBO.getSecurityTrace(stCode);
                if (EBoolean.YES.getCode().equals(stData.getStatus())) {
                    throw new BizException("xn00000", "该盒码已被使用");
                }
                securityTraceBO.refreshStatus(stData, data.getCode());
            }

            SecurityTrace stData = securityTraceBO
                .getSecurityTrace(req.getTraceCodeList().get(0));
            BarCode barData = barCodeBO.getBarCode(stData.getRefCode());
            // 关联箱码不是未使用和已拆分
            if (ECodeStatus.USE_YES.getCode().equals(barData.getCode())) {
                throw new BizException("xn00000", "该箱码已被使用");
            }
            // 更新关联的箱码状态
            barCodeBO.splitSingle(barData);
        }

        orderBO.deliverOrder(data);

    }

    @Override
    public void approveOrder(List<String> codeList, String approver,
            String approveNote) {
        for (String code : codeList) {
            Order data = orderBO.getOrder(code);
            if (!EOrderStatus.Paid.getCode().equals(data.getStatus())) {
                throw new BizException("xn00000", "该订单不处于待审核状态");
            }
            data.setStatus(EOrderStatus.TO_Apprvoe.getCode());
            data.setApprover(approver);
            data.setApproveDatetime(new Date());
            data.setApproveNote(approveNote);
            orderBO.approveOrder(data);
        }

    }

    @Override
    public void cancelOrder(String code) {
        Order data = orderBO.getOrder(code);

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
        orderBO.cancelOrder(data);
    }

    @Override
    public void approveCancel(String code, String result, String updater,
            String remark) {
        Order data = orderBO.getOrder(code);
        if (!EOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该订单未申请取消");
        }

        data.setStatus(EOrderStatus.Paid.getCode());
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EOrderStatus.Canceled.getCode());
            // 云仓提货，归还云仓库存
            // if (EOrderKind.Pick_Up.getCode().equals(data.getKind())) {
            //
            // }else if(){
            //
            // }

            String toUser = data.getToUser();
            if (StringUtils.isBlank(toUser)) {
                toUser = ESysUser.SYS_USER_BH.getCode();
            }
            accountBO.transAmountCZB(toUser, ECurrency.YJ_CNY.getCode(),
                data.getApplyUser(), ECurrency.YJ_CNY.getCode(),
                data.getAmount(), EBizType.AJ_GMCP_TK,
                EBizType.AJ_GMCP_TK.getValue(), EBizType.AJ_GMCP_TK.getValue(),
                data.getCode());
        }
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        orderBO.approveCancel(data);

    }

    @Override
    public void receivedOrder(String code) {
        Order data = orderBO.getOrder(code);
        data.setStatus(EOrderStatus.Received.getCode());
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        orderBO.receivedOrder(data);
    }

    private String getName(String user) {
        if (StringUtils.isBlank(user)) {
            return null;
        }
        if (EUser.ADMIN.getCode().equals(user)) {
            return user;
        }
        String name = null;
        User data = userBO.getUserNoCheck(user);
        if (data != null) {
            name = data.getRealName();
            if (EUserKind.Plat.getCode().equals(data.getKind())
                    && StringUtils.isBlank(data.getRealName())) {
                name = data.getLoginName();
            }
        }
        return name;
    }

    private String checkOrder(User applyUser, ProductSpecs psData) {
        String kind = EOrderKind.Normal_Order.getCode();
        // 是否完成授权单
        if (EUserStatus.IMPOWERED.getCode().equals(applyUser.getStatus())) {
            if (!orderBO.checkImpowerOrder(applyUser.getUserId())) {
                kind = EOrderKind.Impower_Order.getCode();
                if (EProductSpecsType.Apply_NO.getCode()
                    .equals(psData.getIsImpowerOrder())) {
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
                .equals(psData.getIsUpgradeOrder())) {
                throw new BizException("xn0000", "该产品规格不予授权单单下单");
            }
        }

        // 是否允许普通单下单
        if (EProductSpecsType.Apply_NO.getCode()
            .equals(psData.getIsNormalOrder())) {
            throw new BizException("xn0000", "该产品规格不予许普通单下单");
        }
        return kind;
    }

    // 变动产品数量
    private void changeProductNumber(User applyUser, Product pData,
            ProductSpecs psData, Order order, String code) {
        int minNumber = productSpecsBO.getMinSpecsNumber(pData.getCode());
        int quantity = order.getQuantity() * minNumber;

        // 有上级代理,扣减上级代理云仓
        User toUser = userBO.getUser(order.getToUser());
        if (EUserKind.Merchant.getCode().equals(toUser.getKind())) {
            WareHouse toWareHouse = wareHouseBO.getWareHouseByProductSpec(
                order.getToUser(), order.getProductSpecsCode());
            // 上级云仓没有该产品
            if (null == toWareHouse) {
                throw new BizException("xn00000", "上级代理云仓中没有该产品");
            } else {
                // 扣减上级云仓
                wareHouseBO.changeWareHouse(toWareHouse.getCode(),
                    -order.getQuantity(), EBizType.AJ_YCCH,
                    EBizType.AJ_YCCH.getValue(), order.getCode());

            }
            wareHouseBO.buyWareHouse(order, applyUser);

        } else {
            // 无上级代理,扣减产品实际库存
            productLogBO.saveChangeLog(pData, EProductLogType.Order.getCode(),
                pData.getRealNumber(), quantity, null);
            pData.setRealNumber(pData.getRealNumber() - quantity);
            productBO.refreshRealNumber(pData);
        }
    }

    // 检查限购数量
    @Override
    public void checkLimitNumber(User applyUser, ProductSpecs psData,
            ProductSpecsPrice pspData, Integer quantity) {

        // 每日限购
        if (0 != pspData.getDailyNumber()) {
            int dailyNumber = this.getNumber(applyUser.getUserId(),
                DateUtil.getTodayStart(), DateUtil.getTodayEnd());
            if (quantity + dailyNumber > pspData.getDailyNumber()) {
                throw new BizException("xn0000",
                    "您今日还能购买" + (pspData.getDailyNumber() - dailyNumber));
            }
        }

        // 周限购
        if (0 != pspData.getWeeklyNumber()) {
            int weeklyNumber = this.getNumber(applyUser.getUserId(),
                DateUtil.getWeeklyStart(), DateUtil.getWeeklyEnd());
            if ((quantity + weeklyNumber) > pspData.getDailyNumber()) {
                throw new BizException("xn0000",
                    "您本周还能购买" + (pspData.getDailyNumber() - weeklyNumber));
            }
        }

        // 月限购
        if (0 != pspData.getMonthlyNumber()) {
            int monthlyNumber = this.getNumber(applyUser.getUserId(),
                DateUtil.getMonthStart(), DateUtil.getMonthEnd());

            if ((quantity + monthlyNumber) > pspData.getDailyNumber()) {
                throw new BizException("xn0000",
                    "您本月还能购买" + (pspData.getDailyNumber() - monthlyNumber));
            }
        }

    }

    // 日、周、月已购数量
    private int getNumber(String userId, Date startDatetime, Date endDatetime) {
        int number = 0;
        List<Order> list = orderBO.getProductQuantity(userId, startDatetime,
            endDatetime);
        for (Order order : list) {
            ProductSpecs ps = productSpecsBO
                .getProductSpecs(order.getProductSpecsCode());
            number = number + ps.getNumber();
        }
        return number;
    }

    // 检查介绍奖与推荐奖是否同时存在
    private boolean checkAward(User user) {
        // 介绍人与推荐人同时存在
        if (StringUtils.isNotBlank(user.getIntroducer())
                && StringUtils.isNotBlank(user.getUserReferee())) {
            // 下单金额是否超过授权金额
            List<String> statusList = new ArrayList<String>();
            statusList.add(EOrderStatus.Paid.getCode());
            statusList.add(EOrderStatus.TO_Apprvoe.getCode());
            statusList.add(EOrderStatus.TO_Deliver.getCode());
            statusList.add(EOrderStatus.Received.getCode());

            Order condition = new Order();
            condition.setApplyUser(user.getUserId());
            condition.setStatus(EOrderStatus.Received.getCode());
            condition.setStatusList(statusList);

            List<Order> list = orderBO.queryOrderList(condition);
            Long amount = 0L;
            for (Order order : list) {
                amount = amount + order.getAmount();
            }

            AgentImpower impower = agentImpowerBO
                .getAgentImpowerByLevel(user.getLevel());

            if (impower.getMinCharge() >= amount) {
                return false;
            }
        }
        return true;
    }

    private String addOrder(User applyUser, Product pData, ProductSpecs psData,
            int quantity, String applyNote, String signer, String mobile,
            String province, String city, String area, String address) {

        Order order = new Order();
        ProductSpecsPrice pspData = productSpecsPriceBO
            .getPriceByLevel(psData.getCode(), applyUser.getLevel());
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        order.setCode(code);
        order.setProductCode(pData.getCode());
        order.setProductName(pData.getName());
        order.setProductSpecsCode(psData.getCode());
        order.setProductSpecsName(psData.getName());

        User highUser = userBO.getUser(applyUser.getHighUserId());
        order.setToUser(highUser.getUserId());
        order.setQuantity(quantity);
        Long amount = quantity * pspData.getPrice();
        Long yunfei = 0L;

        String kind = EOrderKind.Normal_Order.getCode();
        // 下单人是否是代理
        if (EUserKind.Merchant.getCode().equals(applyUser.getKind())) {

            // 检查是否可购买
            if (EBoolean.NO.getCode().equals(pspData.getIsBuy())) {
                throw new BizException("xn0000", "您的等级无法购买该规格的产品");
            }
            // 检查起购数量
            int minQuantity = productSpecsPriceBO
                .checkMinQuantity(pspData.getCode(), applyUser.getLevel());
            if (minQuantity > quantity) {
                throw new BizException("xn0000",
                    "您购买的数量不能低于" + minQuantity + "]");
            }

            // 门槛余额是否高于限制
            Agent agent = agentBO.getAgentByLevel(applyUser.getLevel());
            Account account = accountBO.getAccountByUser(applyUser.getUserId(),
                ECurrency.MK_CNY.getCode());

            // 门槛最低余额为零
            Long restAmount = account.getAmount() - amount;
            if (0 == agent.getMinSurplus()) {
                if (restAmount > agent.getMinSurplus()) {
                    throw new BizException("xn0000",
                        "剩余门槛不能大于[" + agent.getMinSurplus() / 1000
                                + "]元，目前余额还有[" + restAmount / 1000 + "]元");
                }

            } else if (restAmount >= agent.getMinSurplus()) {
                throw new BizException("xn0000",
                    "剩余门槛不能大于[" + agent.getMinSurplus() / 1000 + "]元，目前余额还有["
                            + restAmount / 1000 + "]元");
            }

            // 是否开启云仓
            boolean flag = orderBO.checkImpowerOrder(applyUser.getUserId());
            if (EBoolean.YES.getCode().equals(agent.getIsWareHouse())) {
                // 改变产品数量
                this.changeProductNumber(applyUser, pData, psData, order, code);
            } else if (flag) {
                kind = EOrderKind.Impower_Order.getCode();
                // 产品不包邮，计算运费
                if (EBoolean.NO.getCode().equals(pData.getIsFree())) {
                    SYSConfig sysConfig = sysConfigBO.getConfig(province,
                        ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                    yunfei = StringValidater.toLong(sysConfig.getCvalue());
                }
            }
        }

        order.setKind(kind);
        order.setPic(pData.getAdvPic());
        order.setPrice(pspData.getPrice());
        order.setApplyUser(applyUser.getUserId());
        order.setYunfei(yunfei);

        order.setAmount(amount + yunfei);
        order.setApplyDatetime(new Date());
        order.setApplyNote(applyNote);
        order.setSigner(signer);
        order.setMobile(mobile);

        order.setProvince(province);
        order.setCity(city);
        order.setArea(area);
        order.setAddress(address);
        order.setStatus(EOrderStatus.Unpaid.getCode());

        orderBO.saveOrder(order);
        return code;
    }

}
