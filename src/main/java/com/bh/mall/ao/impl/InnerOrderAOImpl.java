package com.bh.mall.ao.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.ao.IWeChatAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IInnerProductBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.dto.req.XN627720Req;
import com.bh.mall.dto.req.XN627722Req;
import com.bh.mall.dto.req.XN627723Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EInnerOrderStatus;
import com.bh.mall.enums.EInnerProductStatus;
import com.bh.mall.enums.EProductYunFei;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;
import com.bh.mall.util.wechat.XMLUtil;

@Service
public class InnerOrderAOImpl implements IInnerOrderAO {

    @Autowired
    private IInnerOrderBO innerOrderBO;

    @Autowired
    private IInnerProductBO innerProductBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IWeChatAO weChatAO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public String addInnerOrder(XN627720Req req) {
        Agent user = agentBO.getAgent(req.getApplyUser());

        InnerProduct innerProduct = innerProductBO
            .getInnerProduct(req.getProductCode());
        if (!EInnerProductStatus.Shelf_YES.getCode()
            .equals(innerProduct.getStatus())) {
            throw new BizException("xn00000", "产品未上架，无法下单");
        }
        Integer quantity = StringValidater.toInteger(req.getQuantity());
        if (innerProduct.getQuantity() < quantity) {
            throw new BizException("xn0000", "产品库存不足");
        }
        InnerOrder data = new InnerOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.InnerOrder.getCode());
        data.setCode(code);
        data.setProductCode(req.getProductCode());
        data.setProductName(innerProduct.getProductName());
        data.setPic(innerProduct.getPic());
        data.setPrice(innerProduct.getPrice());

        data.setQuantity(quantity);

        Long amount = quantity * innerProduct.getPrice();
        data.setYunfei(0L);
        // 是否包邮
        if (EProductYunFei.YunFei_NO.getCode()
            .equals(innerProduct.getIsFree())) {
            SYSConfig sysConfig = sysConfigBO.getConfig(req.getProvince(),
                ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
            data.setYunfei(StringValidater.toLong(sysConfig.getCvalue()));
            amount = amount + StringValidater.toLong(sysConfig.getCvalue());
        }

        data.setAmount(amount);

        data.setApplyUser(req.getApplyUser());
        data.setLevel(user.getLevel());
        data.setTeamName(user.getTeamName());

        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());
        data.setProvince(req.getProvince());

        data.setArea(req.getArea());
        data.setCity(req.getCity());
        data.setAddress(req.getAddress());
        data.setMobile(req.getMobile());
        data.setSigner(req.getSigner());

        data.setStatus(EInnerOrderStatus.toPay.getCode());
        innerOrderBO.saveInnerOrder(data);
        return code;
    }

    @Override
    @Transactional
    public Object toPay(String code, String payType) {
        Object result = null;
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        InnerProduct innerProduct = innerProductBO
            .getInnerProduct(data.getProductCode());
        if (!EInnerProductStatus.Shelf_YES.getCode()
            .equals(innerProduct.getStatus())) {
            throw new BizException("xn00000", "产品未上架，无法完成支付");
        }
        if (EBoolean.NO.getCode().equals(payType)) {
            // 支付订单，更新订单状态
            accountBO.transAmountCZB(data.getApplyUser(),
                ECurrency.YJ_CNY.getCode(), ESysUser.SYS_USER_BH.getCode(),
                ECurrency.YJ_CNY.getCode(), data.getAmount(), EBizType.AJ_GMCP,
                EBizType.AJ_GMCP.getValue(), EBizType.AJ_GMCP.getValue(),
                data.getCode());

            data.setPayDatetime(new Date());
            data.setPayCode(data.getPayCode());
            data.setPayAmount(data.getAmount());

            data.setStatus(EInnerOrderStatus.Paid.getCode());
            innerOrderBO.paySuccess(data);
            result = new BooleanRes(true);
        } else if (EBoolean.YES.getCode().equals(payType)) {
            return this.payWXH5(data);
        }
        return result;
    }

    private Object payWXH5(InnerOrder order) {
        Long rmbAmount = order.getAmount() + order.getYunfei();
        Agent agent = agentBO.getAgent(order.getApplyUser());
        String payGroup = innerOrderBO.addPayGroup(order,
            EBoolean.YES.getCode());
        return weChatAO.getPrepayIdH5(agent.getUserId(),
            ESystemCode.BH.getCode(), payGroup, order.getCode(),
            EBizType.AJ_GMCP.getCode(), EBizType.AJ_GMCP.getValue(), rmbAmount,
            PropertiesUtil.Config.WECHAT_H5_CZ_BACKURL,
            EChannelType.WeChat_H5.getCode());
    }

    @Override
    public void paySuccess(String result) {
        Map<String, String> map = null;
        try {
            map = XMLUtil.doXMLParse(result);

            String wechatOrderNo = map.get("transaction_id");
            String outTradeNo = map.get("out_trade_no");
            InnerOrder data = null;
            boolean isSuccess = weChatAO.reqOrderquery(map,
                EChannelType.WeChat_H5.getCode());
            if (isSuccess) {
                data = innerOrderBO.getInnerOrderByPayGroup(outTradeNo);
                data.setPayCode(wechatOrderNo);
                data.setPayAmount(data.getAmount());

                Account account = accountBO.getSysAccountNumber(
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode(),
                    ECurrency.YJ_CNY);
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.WeChat_H5, wechatOrderNo, outTradeNo,
                    data.getCode(), EBizType.AJ_YCCH,
                    EBizType.AJ_YCCH.getValue(), data.getAmount());
                innerOrderBO.paySuccess(data);
            } else {
                innerOrderBO.payNo(data);
            }

        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }

    }

    @Override
    public void refreshAddress(XN627722Req req) {
        InnerOrder data = innerOrderBO.getInnerOrder(req.getCode());
        if (!EInnerOrderStatus.toPay.getCode().equals(data.getStatus())
                || !EInnerOrderStatus.Paid.getCode().equals(data.getStatus())) {
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
        data.setRemark(req.getRemark());
        innerOrderBO.refreshInnerOrder(data);

    }

    @Override
    public Paginable<InnerOrder> queryInnerOrderPage(int start, int limit,
            InnerOrder condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<InnerOrder> page = innerOrderBO.getPaginable(start, limit,
            condition);
        for (InnerOrder data : page.getList()) {
            if (StringUtils.isNotBlank(data.getUpdater())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
                data.setUpdater(sysUser.getRealName());

            }
            if (StringUtils.isNotBlank(data.getDeliver())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getDeliver());
                data.setDeliveName(sysUser.getRealName());
            }
        }
        return page;
    }

    @Override
    public List<InnerOrder> queryInnerOrderList(InnerOrder condition) {

        if (null != condition.getStartDatetime()
                && null != condition.getEndDatetime() && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<InnerOrder> list = innerOrderBO.queryInnerOrderList(condition);
        for (InnerOrder data : list) {
            if (StringUtils.isNotBlank(data.getUpdater())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
                data.setUpdater(sysUser.getRealName());

            }
            if (StringUtils.isNotBlank(data.getDeliver())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getDeliver());
                data.setDeliveName(sysUser.getRealName());
            }
        }
        return list;
    }

    @Override
    public InnerOrder getInnerOrder(String code) {
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        if (StringUtils.isNotBlank(data.getUpdater())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
            data.setUpdater(sysUser.getRealName());

        }
        if (StringUtils.isNotBlank(data.getDeliver())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getDeliver());
            data.setDeliveName(sysUser.getRealName());
        }
        return data;
    }

    @Override
    public void deliverInnerProduct(XN627723Req req) {
        InnerOrder data = innerOrderBO.getInnerOrder(req.getCode());
        if (!EInnerOrderStatus.Paid.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单未支付或已发货");
        }
        data.setDeliver(req.getDeliverer());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCode());
        data.setLogisticsCompany(req.getLogisticsCompany());
        data.setPdf(req.getPdf());
        data.setStatus(EInnerOrderStatus.TO_Deliver.getCode());
        data.setRemark(req.getRemark());
        innerOrderBO.deliverInnerProduct(data);

    }

    @Override
    public void cancelInnerOrder(String code) {
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        if (!EInnerOrderStatus.toPay.getCode().equals(data.getStatus())
                || !EInnerOrderStatus.Paid.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该订单无法申请取消");
        }
        data.setStatus(EInnerOrderStatus.TO_Cancel.getCode());
        innerOrderBO.cancelInnerOrder(data);
    }

    @Override
    public void approveInnerOrder(String code, String result, String updater,
            String remark) {
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        if (!EInnerOrderStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不处于待审核状态");
        }
        // 审核通过取消订单，退钱
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EInnerOrderStatus.Canceled.getCode());
            accountBO.transAmountCZB(ESysUser.SYS_USER_BH.getCode(),
                ECurrency.YJ_CNY.getCode(), data.getApplyUser(),
                ECurrency.YJ_CNY.getCode(), data.getAmount(), EBizType.AJ_GMCP,
                null, null, data.getCode());
        } else {
            data.setStatus(EInnerOrderStatus.toPay.getCode());
        }
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        innerOrderBO.approveInnerOrder(data);
    }

    @Override
    public void receiveInnerOrder(String code) {
        InnerOrder data = innerOrderBO.getInnerOrder(code);
        data.setStatus(EInnerOrderStatus.Delivered.getCode());
        innerOrderBO.receiveInnerOrder(data);
    }

    @Override
    public void batchApprove(List<String> codeList, String approver,
            String remark) {
        for (String code : codeList) {
            InnerOrder data = innerOrderBO.getInnerOrder(code);
            innerOrderBO.batchApprove(data, approver, remark);
        }
    }

}
