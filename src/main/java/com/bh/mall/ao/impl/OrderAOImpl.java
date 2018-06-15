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
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.IWareHouseSpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.callback.CallbackBzdhConroller;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Award;
import com.bh.mall.domain.Cart;
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
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.EProductIsTotal;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.EProductSpecsType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EProductYunFei;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
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
                .getPriceBySpecsCode(psData.getCode(), 6);
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
            // WareHouse whData =
            // wareHouseBO.getWareHouseByUser(user.getUserId(),
            // pData.getCode());
            // wareHouseSpecsBO.getWareHouseSpecsByCode(whData.getCode(),
            // psData.getCode());
            // wareHouseBO.changeWareHouse(whData.getCode(),
            // -order.getQuantity(),
            // EBizType.AJ_YCCH, EBizType.AJ_YCCH.getValue(), order.getCode());

            wareHouseBO.changeWareHouse(whData.getCode(), cart.getQuantity(),
                EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(), code);
        }
    }

    @Override
    public String addOrderNoCart(XN627641Req req) {
        ProductSpecs psData = productSpecsBO
            .getProductSpecs(req.getProductSpecsCode());
        Product pData = productBO.getProduct(psData.getProductCode());
        if (0 >= StringValidater.toInteger(req.getQuantity())) {
            throw new BizException("xn0000", "产品库存不足");
        }

        Integer nowNumber = pData.getRealNumber()
                - StringValidater.toInteger(req.getQuantity());
        // 库存产品数量是否充足
        if (0 > nowNumber) {
            throw new BizException("xn0000", "产品库存不足");
        }
        if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
            throw new BizException("xn0000", "产品包含未上架商品,不能下单");
        }

        User applyUser = userBO.getUser(req.getApplyUser());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());
        ProductSpecsPrice pspData = productSpecsPriceBO
            .getPriceBySpecsCode(psData.getCode(), applyUser.getLevel());
        // 判断代理状态
        String kind = EOrderKind.Normal_Order.getCode();
        if (EUserKind.Merchant.getCode().equals(applyUser.getKind())) {

            // 判断是否允许：普通，授权，升级下单
            if (EProductSpecsType.Apply_NO.getCode()
                .equals(psData.getIsNormalOrder())) {
                throw new BizException("xn0000", "该产品规格不予许普通单下单");
            }
            int quantity = StringValidater.toInteger(req.getQuantity())
                    * psData.getNumber();

            // 直接向平台下单
            if (StringUtils.isBlank(req.getToUser())
                    || EUser.ADMIN.getCode().equals(req.getToUser())) {
                // 修改产品库存记录
                productLogBO.saveChangeLog(pData,
                    EProductLogType.Order.getCode(), pData.getRealNumber(),
                    quantity, null);
                pData.setRealNumber(pData.getRealNumber() - quantity);
                productBO.refreshRealNumber(pData);
            } else {
                // 减少上级库存
                User toUser = userBO.getUser(req.getToUser());
                WareHouse whData = wareHouseBO.getWareHouseByProductSpec(
                    toUser.getUserId(), req.getProductSpecsCode());
                wareHouseBO.changeWareHouse(whData.getCode(),
                    -StringValidater.toInteger(req.getQuantity()),
                    EBizType.AJ_GMYC, EBizType.AJ_GMYC.getValue(), code);
                // WareHouse whData =
                // wareHouseBO.getWareHouseByUser(toUser.getUserId(),
                // pData.getCode());
                // wareHouseSpecsBO.getWareHouseSpecsByCode(whData.getCode(),
                // psData.getCode());
                // wareHouseBO.changeWareHouse(whData.getCode(),
                // -StringValidater.toInteger(req.getQuantity()),
                // EBizType.AJ_YCCH, EBizType.AJ_YCCH.getValue(),
                // order.getCode());
            }
        }

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
        data.setIsSendHome(req.getIsSendHome());
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
    @Transactional
    public Object payOrder(List<String> codeList, String payType) {
        Object result = null;
        for (String code : codeList) {
            Order data = orderBO.getOrder(code);
            if (!EOrderStatus.Unpaid.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "订单未处于待支付状态");
            }

            User uData = userBO.getUser(data.getApplyUser());
            if (EUserKind.Customer.getCode().equals(uData.getKind())) {
                data.setPayType(EPayType.WEIXIN_XCX.getCode());
                Object payResult = this.payWXH5(data);
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

                data.setPayDatetime(new Date());
                data.setPayCode(data.getCode());
                data.setPayAmount(data.getAmount());
                data.setStatus(EInnerOrderStatus.Paid.getCode());
                orderBO.paySuccess(data);

                result = new BooleanRes(true);
            } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {
                data.setPayType(payType);
                Object payResult = this.payWXH5(data);
                result = payResult;
            }

        }
        return result;

    }

    private Object payWXH5(Order data) {
        Long rmbAmount = data.getAmount() + data.getYunfei();
        User user = userBO.getCheckUser(data.getApplyUser());
        String payGroup = orderBO.addPayGroup(data);
        Account account = accountBO.getAccountByUser(data.getToUser(),
            ECurrency.YJ_CNY.getCode());
        return weChatAO.getPrepayIdH5(user.getUserId(),
            account.getAccountNumber(), payGroup, data.getCode(),
            EBizType.AJ_GMCP.getCode(), EBizType.AJ_GMCP.getValue(), rmbAmount,
            PropertiesUtil.Config.WECHAT_H5_CZ_BACKURL,
            EChannelType.WeChat_H5.getCode());
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
                data.setPayDatetime(new Date());
                data.setPayCode(wechatOrderNo);
                data.setPayAmount(data.getAmount());
                data.setStatus(EInnerOrderStatus.Paid.getCode());
                User user = userBO.getUser(data.getToUser());
                Account account = accountBO.getAccountByUser(user.getUserId(),
                    ECurrency.YJ_CNY.getCode());
                if (StringUtils.isBlank(account.getAccountNumber())) {
                    throw new BizException("xn0000", "收款人账户不存在");
                }
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.WeChat_H5, wechatOrderNo, data.getPayGroup(),
                    data.getCode(), EBizType.AJ_YCCH,
                    EBizType.AJ_YCCH.getValue(), data.getAmount());
                orderBO.paySuccess(data);
            } else {
                data.setStatus(EOrderStatus.Pay_NO.getCode());
                data.setPayDatetime(new Date());
                orderBO.payNo(data);
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
        // 订单归属人不是平台
        Product product = productBO.getProduct(data.getProductCode());
        // 获取订单归属人及其上级
        String toUserId = data.getApplyUser();
        User toUser = userBO.getUser(toUserId);
        String fromUserId = ESysUser.SYS_USER_BH.getCode();
        // 有上级，且不是平台
        if (StringUtils.isNotBlank(toUser.getHighUserId())
                && !EUser.ADMIN.getCode().equals(toUser.getHighUserId())) {
            fromUserId = toUser.getHighUserId();
        }

        // **********出货奖*******
        // 出货奖励,且产品计入出货
        if (EProductIsTotal.YES.getCode().equals(product.getIsTotal())) {
            Award aData = awardBO.getAwardByType(toUser.getLevel(),
                data.getProductCode(), EAwardType.SendAward.getCode());
            Long awardAmount = AmountUtil.mul(data.getAmount(),
                aData.getValue1());
            accountBO.transAmountCZB(fromUserId, ECurrency.YJ_CNY.getCode(),
                toUserId, ECurrency.YJ_CNY.getCode(), awardAmount,
                EBizType.AJ_CHJL, EBizType.AJ_CHJL.getValue(),
                EBizType.AJ_CHJL.getValue(), data.getCode());
        }

        // **********推荐奖**********
        // 是否有推荐人
        if (StringUtils.isNotBlank(toUser.getUserReferee())) {
            // 直接推荐人
            User firstUser = userBO.getUser(toUser.getUserReferee());
            Award aData = awardBO.getAwardByType(toUser.getLevel(),
                data.getProductCode(), EAwardType.DirectAward.getCode());

            Long amount = 0L;
            // 直接推荐奖
            if (firstUser != null) {
                amount = AmountUtil.mul(data.getAmount(), aData.getValue1());
                accountBO.transAmountCZB(fromUserId, ECurrency.YJ_CNY.getCode(),
                    firstUser.getUserId(), ECurrency.YJ_CNY.getCode(), amount,
                    EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                    EBizType.AJ_TJJL.getValue(), data.getCode());

                // 间接推荐奖
                if (StringUtils.isNotBlank(firstUser.getUserReferee())) {
                    User secondUser = userBO
                        .getUser(firstUser.getUserReferee());
                    amount = AmountUtil.mul(data.getAmount(),
                        aData.getValue2());
                    accountBO.transAmountCZB(fromUserId,
                        ECurrency.YJ_CNY.getCode(), secondUser.getUserId(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_TJJL,
                        EBizType.AJ_TJJL.getValue(),
                        EBizType.AJ_TJJL.getValue(), data.getCode());

                    // 次推荐奖
                    if (StringUtils.isNotBlank(secondUser.getUserReferee())) {
                        User thirdUser = userBO
                            .getUser(secondUser.getUserReferee());
                        amount = AmountUtil.mul(data.getAmount(),
                            aData.getValue3());
                        accountBO.transAmountCZB(fromUserId,
                            ECurrency.YJ_CNY.getCode(), thirdUser.getUserId(),
                            ECurrency.YJ_CNY.getCode(), amount,
                            EBizType.AJ_TJJL, EBizType.AJ_TJJL.getValue(),
                            EBizType.AJ_TJJL.getValue(), data.getCode());
                    }
                }
            }
        }

    }

    @Override
    @Transactional
    public void deliverOrder(XN627645Req req) {

        Order data = orderBO.getOrder(req.getCode());
        User fromUser = userBO.getUser(data.getApplyUser());
        if (EBoolean.YES.getCode().equals(req.getIsCompanySend())) {
            if (!data.getApplyUser().equals(data.getToUser())) {
                // 订单归属人云仓减少
                if (StringUtils.isNotBlank(data.getToUser())
                        && !EUser.ADMIN.getCode().equals(data.getToUser())) {

                    // 订单数量是否少于本等级最低发货数量
                    User toUser = userBO.getUser(data.getToUser());
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
                    wareHouseBO.changeWareHouse(toData.getCode(),
                        -data.getQuantity(), EBizType.AJ_QKYE,
                        EBizType.AJ_QKYE.getValue(), data.getCode());

                    // 卖货后赚的钱
                    Account account = accountBO.getAccountByUser(
                        data.getToUser(), ECurrency.YJ_CNY.getCode());
                    accountBO.changeAmount(account.getAccountNumber(),
                        EChannelType.NBZ, null, data.getPayGroup(),
                        data.getCode(), EBizType.AJ_CELR,
                        EBizType.AJ_CELR.getValue(), data.getPayAmount());

                    // 出货以及推荐奖励
                    if (EUserKind.Merchant.getCode()
                        .equals(fromUser.getKind())) {
                        this.payAward(data);
                    }

                }
            }

        }

        // 代理购买云仓
        if (EUserKind.Merchant.getCode().equals(fromUser.getKind())) {
            if (EBoolean.NO.getCode().equals(data.getIsSendHome())) {

                WareHouse wareHouse = wareHouseBO.getWareHouseByProductSpec(
                    fromUser.getUserId(), data.getProductSpecsCode());
                // // 云仓没有该产品
                // if (null == wareHouse) {
                // String code = OrderNoGenerater
                // .generate(EGeneratePrefix.WareHouse.getCode());
                // wareHouse = new WareHouse();
                // wareHouse.setCode(code);
                // wareHouse.setUserId(data.getApplyUser());
                // wareHouseSpecsBO.saveWareHouseSpecs(wareHouse, data);
                // } else {
                // WareHouseSpecs whs = wareHouseSpecsBO
                // .getWareHouseSpecsByCode(wareHouse.getCode(),
                // data.getProductSpecsCode());
                // if (null == whs) {
                // whs = new WareHouseSpecs();
                // whs.setWareHouseCode(whs.getCode());
                // whs.setProductSpecsCode(data.getProductSpecsCode());
                // whs.setPrice(data.getPrice());
                // whs.setQuantity(data.getQuantity());
                //
                // whs.setAmount(data.getAmount());
                // wareHouseSpecsBO.saveWareHouseSpecs(whs);
                //
                // wareHouseBO.changeWareHouse(wareHouse.getCode(),
                // data.getQuantity(), EBizType.AJ_GMYC,
                // EBizType.AJ_GMYC.getValue(), data.getCode());
                // wareHouseSpecsBO.refreshWareHouseSpecs(whs.getCode(),
                // data.getQuantity());
                // } else {
                //
                // wareHouseBO.changeWareHouse(wareHouse.getCode(), data,
                // -data.getQuantity(), EBizType.AJ_YCCH,
                // EBizType.AJ_YCCH.getValue());
                // wareHouseBO.changeWareHouse(wareHouse.getCode(),
                // data.getQuantity(), EBizType.AJ_GMYC,
                // EBizType.AJ_GMYC.getValue(), data.getCode());
                // wareHouseSpecsBO.refreshWareHouseSpecs(whs.getCode(),
                // data.getQuantity());
                // }
                // }

                // 没有该产品
                if (null == wareHouse) {
                    String code = OrderNoGenerater
                        .generate(EGeneratePrefix.WareHouse.getCode());
                    WareHouse whData = new WareHouse();
                    whData.setCode(code);
                    whData.setProductCode(data.getProductCode());
                    whData.setProductName(data.getProductName());
                    whData.setProductSpecsCode(data.getProductSpecsCode());
                    whData.setProductSpecsName(data.getProductSpecsName());

                    whData.setCurrency(ECurrency.YC_CNY.getCode());
                    whData.setUserId(fromUser.getUserId());
                    whData.setRealName(fromUser.getRealName());
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
                    wareHouseBO.changeWareHouse(wareHouse.getCode(),
                        data.getQuantity(), EBizType.AJ_GMYC,
                        EBizType.AJ_GMYC.getValue(), data.getCode());
                }
            }
        }

        data.setDeliver(req.getDeliver());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCode());
        data.setLogisticsCompany(req.getLogisticsCompany());
        data.setPdf(req.getPdf());

        data.setIsCompanySend(req.getIsCompanySend());
        data.setStatus(EOrderStatus.TO_Deliver.getCode());
        data.setRemark(req.getRemark());
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
        if (EOrderStatus.TO_Deliver.getCode().equals(data.getStatus())
                || EOrderStatus.Received.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该订单无法取消");
        }
        data.setStatus(EOrderStatus.TO_Cancel.getCode());

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

}
