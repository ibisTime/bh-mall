package com.bh.mall.ao.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.ICompanyChannelBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.callback.CallbackBzdhConroller;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.Award;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.CompanyChannel;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.User;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627643Req;
import com.bh.mall.dto.req.XN627645Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.enums.EAccountStatus;
import com.bh.mall.enums.EAwardType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EInnerOrderStatus;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EProductIsTotal;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.EProductSpecsType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EProductYunFei;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserLevel;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.util.wechat.WXOrderQuery;
import com.bh.mall.util.wechat.XMLUtil;

@Service
public class OrderAOImpl implements IOrderAO {
    private static Logger logger = Logger
        .getLogger(CallbackBzdhConroller.class);

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private ICartBO cartBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IProductSpecsPriceBO productSpecsPriceBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IAwardBO awardBO;

    @Autowired
    private IAgencyLogBO agencyLogBO;

    @Autowired
    private IWareHouseBO wareHouseBO;

    @Autowired
    private IWeChatAO weChatAO;

    @Autowired
    private IProductLogBO productLogBO;

    @Autowired
    private ICompanyChannelBO companyChannelBO;

    // C端下单
    @Override
    public void addOrder(XN627640Req req) {
        for (Cart cart : req.getCartList()) {
            Order order = new Order();
            Product pData = productBO.getProduct(cart.getProductCode());
            if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
                throw new BizException("xn0000", "产品包含未上架商品,不能下单");
            }

            ProductSpecs psData = productSpecsBO
                .getProductSpecs(cart.getProductSpecsCode());
            ProductSpecsPrice pspData = productSpecsPriceBO
                .getPriceBySpecsCode(psData.getCode(), null);
            order.setKind(EOrderKind.Normal_Order.getCode());

            String code = OrderNoGenerater
                .generate(EGeneratePrefix.Order.getCode());

            order.setCode(code);
            order.setProductCode(pData.getCode());
            order.setProductName(pData.getName());
            order.setPic(pData.getAdvPic());

            order.setProductSpecsCode(psData.getCode());
            order.setProductSpecsName(pData.getName());
            order.setQuantity(cart.getQuantity());
            order.setPrice(pspData.getPrice());

            Long amount = AmountUtil
                .eraseLiUp(cart.getQuantity() * pspData.getPrice());

            // 是否包邮
            Long yunfei = 0L;
            if (EProductYunFei.YunFei_NO.getCode().equals(pData.getIsFree())) {
                SYSConfig sysConfig = sysConfigBO.getConfig(req.getProvince(),
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                yunfei = StringValidater.toLong(sysConfig.getCvalue());
                amount = amount + StringValidater.toLong(sysConfig.getCvalue());
            }
            order.setYunfei(yunfei);
            order.setAmount(amount);
            order.setApplyUser(req.getApplyUser());

            order.setApplyDatetime(new Date());
            order.setApplyNote(req.getApplyNote());
            order.setToUser(req.getToUser());
            order.setSigner(req.getSigner());
            order.setMobile(req.getMobile());

            order.setAddress(req.getAddress());
            order.setArea(req.getArea());
            order.setCity(req.getCity());
            order.setKind(EOrderKind.Normal_Order.getCode());
            order.setProvince(req.getProvince());

            order.setStatus(EOrderStatus.Unpaid.getCode());
            order.setIsSendHome(req.getIsSendHome());
            orderBO.saveOrder(order);
            // 删除购物车记录
            cartBO.removeCart(cart);
            User user = userBO.getUser(req.getToUser());

            WareHouse whData = wareHouseBO
                .getWareHouseByProductSpec(user.getUserId(), psData.getCode());

            wareHouseBO.changeWareHouse(whData.getCode(), cart.getQuantity(),
                EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(), code);
            // 修改产品数量
            productBO.refreshRealNumber(pData);
        }
    }

    @Override
    public String addOrderNoCart(XN627641Req req) {
        ProductSpecs psData = productSpecsBO
            .getProductSpecs(req.getProductSpecsCode());
        Product pData = productBO.getProduct(psData.getProductCode());
        if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
            throw new BizException("xn0000", "产品包含未上架商品,不能下单");
        }

        User uLevel = userBO.getUser(req.getApplyUser());

        ProductSpecsPrice pspData = productSpecsPriceBO
            .getPriceBySpecsCode(psData.getCode(), uLevel.getLevel());
        // 判断代理状态
        String kind = EOrderKind.Normal_Order.getCode();
        if (EUserKind.Plat.getCode().equals(uLevel.getKind())) {

            // 是否有过授权单
            Order condition = new Order();
            condition.setApplyUser(req.getApplyUser());
            condition.setKind(EOrderKind.Impower_Order.getCode());
            long count = orderBO.getTotalCount(condition);
            if (count == 0) {
                if (EProductSpecsType.Apply_NO.getCode()
                    .equals(psData.getIsImpowerOrder())) {
                    throw new BizException("xn0000", "该产品规格不予许授权单下单");
                } else {
                    kind = EOrderKind.Impower_Order.getCode();
                }
            }
            // 是否升级
            User user = userBO.getUser(req.getApplyUser());
            AgencyLog agencyLog = agencyLogBO
                .getAgencyLog(user.getLastAgentLog());

            if (EUserStatus.UPGRADED.getCode().equals(agencyLog.getStatus())) {
                condition.setKind(EOrderKind.Upgrade_Order.getCode());
                count = orderBO.getTotalCount(condition);
                if (count == 0) {
                    if (EProductSpecsType.Apply_NO.getCode()
                        .equals(psData.getIsUpgradeOrder())) {
                        throw new BizException("xn0000", "该产品规格不予许升级单下单");
                    } else {
                        kind = EOrderKind.Upgrade_Order.getCode();
                    }
                }
            }

            // 判断是否允许：普通，授权，升级下单
            if (EProductSpecsType.Apply_NO.getCode()
                .equals(psData.getIsNormalOrder())) {
                throw new BizException("xn0000", "该产品规格不予许普通单下单");
            }

            // 下单代理是一级
            if (StringValidater.toInteger(EUserLevel.ONE.getCode()) == user
                .getLevel()) {
                // 产品总数量
                int quantity = StringValidater.toInteger(req.getQuantity())
                        * psData.getNumber();

                if (quantity > pData.getRealNumber()) {
                    throw new BizException("xn0000", "产品库存不足");
                }
                // 修改产品库存记录
                productLogBO.saveChangeLog(pData,
                    EProductLogType.Order.getCode(), pData.getRealNumber(),
                    quantity, null);
                pData.setRealNumber(pData.getRealNumber() - quantity);
                productBO.refreshRealNumber(pData);

                // WareHouse whData = wareHouseBO.getWareHouseByProductSpec(
                // user.getUserId(), req.getProductSpecsCode());
                //
                // wareHouseBO.changeWareHouse(whData.getCode(),
                // StringValidater.toInteger(req.getQuantity()),
                // EBizType.AJ_GMYC,
                // EBizType.AJ_GMYC.getValue(), code);
            } else {
                // User fromUser = userBO.getCheckUser(req.getToUser());
                // User toUser = userBO.getCheckUser(req.getApplyUser());
                // wareHouseBO.transQuantity(fromUser.getUserId(),
                // psData.getCode(),
                // toUser.getUserId(), psData.getCode(),
                // StringValidater.toInteger(req.getQuantity()),
                // EBizType.AJ_GMYC,
                // EBizType.AJ_YCCH, EBizType.AJ_GMYC.getValue(),
                // EBizType.AJ_YCCH.getValue(), code);
            }
        }

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        Order data = new Order();
        data.setCode(code);
        data.setProductCode(pData.getCode());
        data.setProductName(pData.getName());
        data.setPic(pData.getAdvPic());
        data.setProductSpecsCode(psData.getCode());

        data.setProductSpecsName(pData.getName());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        data.setPrice(pspData.getPrice());
        Long amount = AmountUtil.eraseLiUp(
            StringValidater.toInteger(req.getQuantity()) * pspData.getPrice());
        // 是否包邮
        Long yunfei = 0L;
        if (EBoolean.YES.getCode().equals(req.getIsSendHome())) {
            if (EProductYunFei.YunFei_NO.getCode().equals(pData.getIsFree())) {
                SYSConfig sysConfig = sysConfigBO.getConfig(req.getProvince(),
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                yunfei = StringValidater.toLong(sysConfig.getCvalue());
                amount = amount + StringValidater.toLong(sysConfig.getCvalue());
            }
        }
        data.setYunfei(yunfei);
        data.setAmount(amount);
        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());

        data.setApplyNote(req.getApplyNote());
        data.setToUser(req.getToUser());
        data.setSigner(req.getSigner());
        data.setMobile(req.getMobile());
        data.setAddress(req.getAddress());

        data.setArea(req.getArea());
        data.setCity(req.getCity());
        data.setKind(kind);
        data.setProvince(req.getProvince());
        data.setStatus(EOrderStatus.Unpaid.getCode());

        orderBO.saveOrder(data);
        return code;

    }

    @Override
    public Object payOrder(String codeList, String payType) {
        Object result = null;
        if (StringUtils.isNotBlank(codeList)) {
            String[] group = codeList.split(",");
            for (String code : group) {
                Order data = orderBO.getOrder(code);
                if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                    throw new BizException("xn0000", "订单未处于待支付状态");
                }

                User uData = userBO.getUser(data.getApplyUser());
                Account accountData = null;
                if (EUserKind.Customer.getCode().equals(uData.getKind())) {
                    Object payResult = this.payWXH5(data);
                    result = payResult;

                } else if (EBoolean.NO.getCode().equals(payType)) {
                    accountBO.getAccountByUser(data.getApplyUser(),
                        ECurrency.MK_CNY.getCode());
                    String toUserId = data.getToUser();
                    if (isPlat(data.getToUser())) {
                        toUserId = ESysUser.SYS_USER_BH.getCode();
                    }

                    // 进价
                    User toUser = userBO.getUser(data.getToUser());
                    ProductSpecsPrice inPrice = productSpecsPriceBO
                        .getPriceByLevel(data.getProductSpecsCode(),
                            toUser.getLevel());

                    // 总利润
                    Long awardAmount = (data.getPrice() - inPrice.getPrice())
                            * data.getQuantity();
                    awardAmount = AmountUtil.eraseLiUp(awardAmount);
                    // 差价利润
                    accountBO.changeAmount(accountData.getAccountNumber(),
                        EChannelType.NBZ, null, data.getCode(), data.getCode(),
                        EBizType.AJ_CELR, EBizType.AJ_CELR.getValue(),
                        awardAmount);

                    // 出货以及推荐奖励
                    this.payAward(data);

                    accountBO.transAmountCZB(data.getApplyUser(),
                        ECurrency.MK_CNY.getCode(), toUserId,
                        ECurrency.MK_CNY.getCode(), data.getAmount(),
                        EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(),
                        EBizType.AJ_GMYC.getValue(), data.getCode());
                    result = new BooleanRes(true);
                } else if (EBoolean.YES.getCode().equals(payType)) {
                    Object payResult = this.payWXH5(data);
                    result = payResult;
                }

            }
        }
        return result;
    }

    private Object payWXH5(Order order) {
        Long rmbAmount = order.getAmount() + order.getYunfei();
        User user = userBO.getCheckUser(order.getApplyUser());
        String payGroup = orderBO.addPayGroup(order, EBoolean.YES.getCode());
        Account account = accountBO.getAccountByUser(order.getToUser(),
            ECurrency.YJ_CNY.getCode());
        return weChatAO.getPrepayIdH5(user.getUserId(),
            account.getAccountNumber(), payGroup, order.getCode(),
            EBizType.AJ_GMCP.getCode(), EBizType.AJ_GMCP.getValue(), rmbAmount,
            PropertiesUtil.Config.WECHAT_H5_BACKURL);
    }

    @Override
    public void paySuccess(String result) {
        Map<String, String> map = null;
        try {
            map = XMLUtil.doXMLParse(result);
            String attach = map.get("attach");
            String[] codes = attach.split("\\|\\|");
            String systemCode = codes[0];
            String companyCode = codes[1];
            String bizBackUrl = codes[2];
            String wechatOrderNo = map.get("transaction_id");
            String outTradeNo = map.get("out_trade_no");

            // 此处调用订单查询接口验证是否交易成功
            boolean isSucc = weChatAO.reqOrderquery(map,
                EChannelType.WeChat_H5.getCode());
            if (isSucc) {

                Order data = orderBO.getInnerOrderByPayGroup(outTradeNo);
                data.setPayDatetime(new Date());
                data.setPayCode(wechatOrderNo);
                data.setPayAmount(data.getAmount());
                data.setStatus(EInnerOrderStatus.Paid.getCode());
                User user = userBO.getUser(data.getToUser());
                logger.info("toUser:" + user.getUserId());
                Account account = accountBO.getAccountByUser(user.getUserId(),
                    ECurrency.YJ_CNY.getCode());
                if (StringUtils.isBlank(account.getAccountNumber())) {
                    throw new BizException("xn0000", "收款人账户不存在");
                }
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.WeChat_H5, null, data.getPayGroup(),
                    data.getCode(), EBizType.AJ_YCCH,
                    EBizType.AJ_YCCH.getValue(), data.getAmount());
                orderBO.paySuccess(data);
            }

        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }

        // accountBO.changeAmount(account.getAccountNumber(),
        // EChannelType.WeChat_H5, null, payGroup, data.getCode(),
        // EBizType.AJ_YCCH, EBizType.AJ_YCCH.getValue(), amount);
        // orderBO.paySuccess(data);
        //
        // Order data = orderBO.getInnerOrderByPayGroup(payGroup);
        // data.setPayDatetime(new Date());
        // data.setPayCode(payCode);
        // data.setPayAmount(amount);
        // data.setStatus(EInnerOrderStatus.Paid.getCode());
        // User user = userBO.getUser(data.getToUser());
        // logger.info("toUser:" + user.getUserId());
        // Account account = accountBO.getAccountByUser(user.getUserId(),
        // ECurrency.YJ_CNY.getCode());
        // if (StringUtils.isBlank(account.getAccountNumber())) {
        // throw new BizException("xn0000", "收款人账户不存在");
        // }
        // accountBO.changeAmount(account.getAccountNumber(),
        // EChannelType.WeChat_H5, null, payGroup, data.getCode(),
        // EBizType.AJ_YCCH, EBizType.AJ_YCCH.getValue(), amount);
        // orderBO.paySuccess(data);

    }

    @Override
    public void dropOrder(String code) {
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
            User user = userBO.getCheckUser(order.getApplyUser());
            order.setUser(user);
            String approveUser = this.getName(order.getApprover());
            order.setApproveName(approveUser);
            String deliveName = this.getName(order.getDeliver());
            order.setDeliveName(deliveName);
            String updateName = this.getName(order.getUpdater());
            order.setUpdater(updateName);
            String toUserName = this.getName(order.getToUser());
            order.setToUserName(toUserName);

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
            User user = userBO.getCheckUser(order.getApplyUser());
            order.setUser(user);
            String approveUser = this.getName(order.getApprover());
            order.setApproveName(approveUser);
            String deliveName = this.getName(order.getDeliver());
            order.setDeliveName(deliveName);
            String updateName = this.getName(order.getUpdater());
            order.setUpdater(updateName);
        }
        return list;
    }

    @Override
    public Order getOrder(String code) {
        Order order = orderBO.getOrder(code);
        User user = userBO.getCheckUser(order.getApplyUser());
        order.setUser(user);
        String approveUser = this.getName(order.getApprover());
        order.setApproveName(approveUser);
        String deliveName = this.getName(order.getDeliver());
        order.setDeliveName(deliveName);
        String updateName = this.getName(order.getUpdater());
        order.setUpdater(updateName);
        return order;
    }

    @Override
    public void editOrder(XN627643Req req) {
        Order data = orderBO.getOrder(req.getCode());
        if (EBoolean.NO.getCode().equals(data.getIsSendHome())) {
            throw new BizException("xn0000", "该订单是代理购买的存仓产品,地址无法修改");
        }
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
        Product product = productBO.getProduct(data.getProductCode());
        User user = userBO.getUser(data.getToUser());
        // 获取出货人的上级
        String fromUser = user.getHighUserId();
        if (isPlat(fromUser)) {
            fromUser = ESysUser.SYS_USER_BH.getCode();
        }
        // 出货奖励,且产品计入出货
        if (EProductIsTotal.YES.getCode().equals(product.getIsTotal())) {
            Award aData = awardBO.getAwardByType(user.getLevel(),
                data.getProductCode(), EAwardType.SendAward.getCode());
            Long awardAmount = AmountUtil.mul(data.getAmount(),
                aData.getValue1());
            accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                data.getToUser(), ECurrency.YJ_CNY.getCode(), awardAmount,
                EBizType.AJ_CHJL, EBizType.AJ_CHJL.getValue(),
                EBizType.AJ_CHJL.getValue(), data.getCode());
        }

        // 直接推荐人
        User firstUser = userBO.getUser(user.getUserReferee());

        Award aData = awardBO.getAwardByType(user.getLevel(),
            data.getProductCode(), EAwardType.DirectAward.getCode());

        Long amount = 0L;
        // 直接推荐奖
        if (firstUser != null) {
            amount = AmountUtil.mul(data.getAmount(), aData.getValue1());
            accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                firstUser.getUserId(), ECurrency.YJ_CNY.getCode(), amount,
                EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                EBizType.AJ_TJJL.getValue(), data.getCode());

            // 间接推荐奖
            User secondUser = userBO.getUser(firstUser.getUserReferee());
            if (secondUser != null) {
                amount = AmountUtil.mul(data.getAmount(), aData.getValue2());
                accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                    secondUser.getUserId(), ECurrency.YJ_CNY.getCode(), amount,
                    EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                    EBizType.AJ_TJJL.getValue(), data.getCode());
                // 次推荐奖
                User thirdUser = userBO.getUser(secondUser.getUserReferee());
                if (thirdUser != null) {
                    amount = AmountUtil.mul(data.getAmount(),
                        aData.getValue3());
                    accountBO.transAmountCZB(fromUser,
                        ECurrency.YJ_CNY.getCode(), thirdUser.getUserId(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_TJJL,
                        EBizType.AJ_TJJL.getValue(),
                        EBizType.AJ_TJJL.getValue(), data.getCode());
                }
            }
        }

    }

    @Override
    @Transactional
    public void deliverOrder(XN627645Req req) {
        Order data = orderBO.getOrder(req.getCode());
        if (!EOrderStatus.TO_Apprvoe.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单未支付或已发货");
        }

        User toUser = userBO.getUser(data.getApplyUser());
        User fromUser = userBO.getUser(data.getToUser());
        WareHouse fromData = wareHouseBO.getWareHouseByProductSpec(
            fromUser.getUserId(), data.getProductSpecsCode());
        WareHouse toData = wareHouseBO.getWareHouseByProductSpec(
            toUser.getUserId(), data.getProductSpecsCode());
        if (fromData == null) {
            throw new BizException("xn000", toData.getUserId() + ",您仓库中没有该产品");
        }
        if (fromData.getQuantity() < 0) {
            throw new BizException("xn000",
                toData.getRealName() + ",您仓库中该改规格测产品不足");
        }
        // 代理购买云仓
        if (EUserKind.Merchant.getCode().equals(toUser.getKind())
                && EBoolean.NO.getCode().equals(data.getIsSendHome())) {
            // 没有该产品
            if (toData == null) {
                String code = OrderNoGenerater
                    .generate(EGeneratePrefix.WareHouse.getCode());
                WareHouse whData = new WareHouse();
                whData.setCode(code);
                whData.setProductCode(data.getProductCode());
                whData.setProductName(data.getProductName());
                whData.setProductSpecsCode(data.getProductSpecsCode());
                whData.setProductSpecsName(data.getProductSpecsName());

                whData.setCurrency(ECurrency.YC_CNY.getCode());
                whData.setUserId(toUser.getUserId());
                whData.setRealName(toUser.getRealName());
                whData.setCreateDatetime(new Date());
                whData.setPrice(data.getPrice());

                whData.setQuantity(data.getQuantity());
                Long amount = data.getQuantity() * data.getPrice();
                whData.setAmount(amount);
                whData.setStatus(EAccountStatus.NORMAL.getCode());
                whData.setCompanyCode(ESystemCode.BH.getCode());
                whData.setSystemCode(ESystemCode.BH.getCode());
                wareHouseBO.saveWareHouse(whData, data.getQuantity(),
                    EBizType.AJ_GMYC, "购买" + data.getProductName(),
                    data.getCode());

            } else {
                wareHouseBO.changeWareHouse(fromData.getCode(),
                    data.getQuantity(), EBizType.AJ_GMYC,
                    EBizType.AJ_GMYC.getValue(), data.getCode());
            }

        }

        data.setDeliver(req.getDeliver());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCode());
        data.setLogisticsCompany(req.getLogisticsCompany());
        data.setPdf(req.getPdf());
        data.setStatus(EInnerOrderStatus.TO_Deliver.getCode());
        data.setRemark(req.getRemark());
        orderBO.deliverOrder(data);
    }

    @Override
    public void approveOrder(List<Order> codeList, String approver,
            String approveNote) {
        for (Order order : codeList) {
            Order data = orderBO.getOrder(order.getCode());
            data.setApprover(approver);
            data.setApproveDatetime(new Date());
            data.setApproveNote(approveNote);
            orderBO.approveOrder(data);
        }

    }

    @Override
    public void cancelOrder(String code) {
        Order data = orderBO.getOrder(code);
        User user = userBO.getCheckUser(data.getApplyUser());
        if (EUserKind.Customer.getCode().equals(user.getKind())) {
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "该订单无法申请取消");
            }
            data.setStatus(EOrderStatus.Canceled.getCode());
        } else {
            if (EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                data.setStatus(EOrderStatus.Canceled.getCode());
            } else
                data.setStatus(EOrderStatus.TO_Cancel.getCode());
        }

        orderBO.cancelOrder(data);
    }

    @Override
    public void approveCancel(String code, String result, String updater,
            String remark) {
        Order data = orderBO.getOrder(code);
        if (!EOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不处于待审核状态");
        }
        // 审核通过取消订单，退钱
        data.setStatus(EOrderStatus.Paid.getCode());
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EOrderStatus.Canceled.getCode());
            accountBO.transAmountCZB(ESysUser.SYS_USER_BH.getCode(),
                ECurrency.YJ_CNY.getCode(), data.getApplyUser(),
                ECurrency.YJ_CNY.getCode(), data.getAmount(),
                EBizType.AJ_GMCP_TK, EBizType.AJ_GMCP_TK.getValue(),
                EBizType.AJ_GMCP_TK.getValue(), data.getCode());
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

    private boolean isPlat(String userId) {
        boolean flag = false;
        User user = userBO.getCheckUser(userId);
        if (EUserKind.Plat.getCode().equals(user.getKind())) {
            flag = true;
        }
        return flag;
    }

    private String getName(String user) {

        if (StringUtils.isBlank(user)) {
            return null;
        }
        if ("admin".equals(user)) {
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

    public boolean reqOrderquery(Map<String, String> map, String channelType) {
        System.out.println("******* 开始订单查询 ******");
        WXOrderQuery orderQuery = new WXOrderQuery();
        orderQuery.setAppid(map.get("appid"));
        orderQuery.setMch_id(map.get("mch_id"));
        orderQuery.setTransaction_id(map.get("transaction_id"));
        orderQuery.setOut_trade_no(map.get("out_trade_no"));
        orderQuery.setNonce_str(map.get("nonce_str"));

        String attach = map.get("attach");
        System.out.println("attcah=" + attach);
        String[] codes = attach.split("\\|\\|");
        System.out
            .println("companyCode=" + codes[0] + " systemCode=" + codes[1]);
        CompanyChannel companyChannel = companyChannelBO
            .getCompanyChannel(codes[0], codes[1], channelType);

        // 此处需要密钥PartnerKey，此处直接写死，自己的业务需要从持久化中获取此密钥，否则会报签名错误
        orderQuery.setPartnerKey(companyChannel.getPrivateKey1());

        Map<String, String> orderMap = orderQuery.reqOrderquery();
        // 此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
        if (orderMap.get("return_code") != null
                && orderMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
            if (orderMap.get("trade_state") != null && orderMap
                .get("trade_state").equalsIgnoreCase("SUCCESS")) {
                String total_fee = map.get("total_fee");
                String order_total_fee = map.get("total_fee");
                if (Integer.parseInt(order_total_fee) >= Integer
                    .parseInt(total_fee)) {
                    System.out.println("******* 开订单查询结束，结果正确 ******");
                    return true;
                }
            }
        }
        System.out.println("******* 开订单查询结束，结果不正确 ******");
        return false;
    }

}
