package com.bh.mall.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IOutOrderDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.CUser;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627815Req;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EIsPay;
import com.bh.mall.enums.EOutOrderKind;
import com.bh.mall.enums.EOutOrderStatus;
import com.bh.mall.exception.BizException;

@Component
public class OutOrderBOImpl extends PaginableBOImpl<OutOrder>
        implements IOutOrderBO {

    @Autowired
    IOutOrderDAO outOrderDAO;

    @Autowired
    IProductBO productBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public String saveOutOrder(Agent applyUser, String toUserId,
            String toUserName, String teamLeader, Product pData, Specs specs,
            Long price, int quantity, String kind, XN627640Req req) {

        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());
        data.setCode(code);
        data.setProductCode(pData.getCode());
        data.setProductName(pData.getName());
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setHighUserId(applyUser.getHighUserId());

        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());
        data.setToUserId(toUserId);
        data.setToUserName(toUserName);

        data.setQuantity(quantity);
        data.setPrice(price);
        data.setPic(pData.getPic());
        data.setApplyUser(applyUser.getUserId());
        data.setLevel(applyUser.getLevel());

        data.setRealName(applyUser.getRealName());
        data.setTeamName(applyUser.getTeamName());
        data.setTeamLeader(teamLeader);
        data.setYunfei(0L);
        data.setAmount(price * quantity);
        data.setApplyDatetime(new Date());

        data.setApplyNote(req.getApplyNote());
        data.setStatus(EOutOrderStatus.Unpaid.getCode());
        data.setKind(kind);
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setSigner(req.getSigner());
        data.setProvince(req.getProvince());

        data.setMobile(req.getMobile());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setIsPay(EIsPay.PAY_NO.getCode());

        outOrderDAO.insert(data);
        return code;
    }

    @Override
    public void refreshOutOrder(OutOrder data) {
        outOrderDAO.update(data);
    }

    @Override
    public List<OutOrder> queryOutOrderList(OutOrder condition) {
        return outOrderDAO.selectList(condition);
    }

    @Override
    public OutOrder getOutOrder(String code) {
        OutOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            OutOrder condition = new OutOrder();
            condition.setCode(code);
            data = outOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void deliverOutOrder(OutOrder data, String status, String deliver,
            String logisticsCode, String logisticsCompany, Date deliveDatetime,
            String remark) {
        data.setDeliver(deliver);
        data.setLogisticsCode(logisticsCode);
        data.setDeliveDatetime(deliveDatetime);
        data.setLogisticsCompany(logisticsCompany);

        data.setIsPay(EIsPay.PAY_NO.getCode());
        data.setStatus(status);
        data.setRemark(remark);
        outOrderDAO.deliverOutOrder(data);
    }

    @Override
    public void approveOutOrder(OutOrder data) {
        outOrderDAO.approveOutOrder(data);
    }

    @Override
    public void cancelOutOrder(OutOrder data) {
        outOrderDAO.cancelOutOrder(data);

    }

    @Override
    public void approveCancel(OutOrder data) {
        outOrderDAO.approveCancel(data);
    }

    @Override
    public void receivedOutOrder(OutOrder data) {
        outOrderDAO.receivedOutOrder(data);

    }

    @Override
    public OutOrder getCheckOutOrder(String userId, String kind) {
        OutOrder condition = new OutOrder();
        condition.setApplyUser(userId);
        condition.setKind(kind);
        return outOrderDAO.select(condition);
    }

    @Override
    public long selectCount(OutOrder oCondition) {
        return outOrderDAO.selectTotalCount(oCondition);
    }

    @Override
    public String addPayGroup(OutOrder data, String payGroup, String payType) {
        data.setPayType(payType);
        data.setPayGroup(payGroup);
        outOrderDAO.addPayGroup(data);
        return payGroup;
    }

    @Override
    public List<OutOrder> getOutOrderByPayGroup(String payGroup) {
        OutOrder condition = new OutOrder();
        condition.setPayGroup(payGroup);
        return outOrderDAO.selectList(condition);
    }

    @Override
    public void paySuccess(OutOrder data) {
        data.setStatus(EOutOrderStatus.TO_SEND.getCode());
        outOrderDAO.paySuccess(data);
    }

    @Override
    public void payNo(OutOrder data) {
        outOrderDAO.payNo(data);
    }

    @Override
    public Long checkImpowerOrder(String applyUser, Date impoweDatetime) {

        OutOrder condition = new OutOrder();
        condition.setApplyUser(applyUser);
        condition.setKind(EOutOrderKind.Impower_Order.getCode());
        condition.setStartDatetime(impoweDatetime);

        List<OutOrder> list = outOrderDAO.selectList(condition);
        Long impowerAmount = 0L;
        for (OutOrder data : list) {
            impowerAmount = impowerAmount + data.getAmount();
        }

        return impowerAmount;
    }

    @Override
    public Long checkUpgradeOrder(String applyUser, Date datetime) {

        OutOrder condition = new OutOrder();
        condition.setStartDatetime(datetime);
        condition.setApplyUser(applyUser);
        condition.setKind(EOutOrderKind.Upgrade_Order.getCode());

        List<OutOrder> list = outOrderDAO.selectList(condition);
        Long impowerAmount = 0L;
        for (OutOrder data : list) {
            impowerAmount = impowerAmount + data.getAmount();
        }

        return impowerAmount;
    }

    @Override
    public List<OutOrder> getOutOrderQuantity(String userId, Date startDatetime,
            Date endDatetime) {
        OutOrder condition = new OutOrder();
        condition.setApplyUser(userId);
        condition.setStartDatetime(startDatetime);
        condition.setEndDatetime(endDatetime);
        return outOrderDAO.selectList(condition);
    }

    @Override
    public Long getOutOrderByUser(String userId) {
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOutOrderStatus.TO_APPROVE.getCode());
        statusList.add(EOutOrderStatus.TO_SEND.getCode());
        statusList.add(EOutOrderStatus.TO_RECEIVE.getCode());
        statusList.add(EOutOrderStatus.RECEIVED.getCode());

        OutOrder condition = new OutOrder();
        condition.setApplyUser(userId);
        List<OutOrder> list = outOrderDAO.selectList(condition);
        condition.setKind(EOutOrderKind.Normal_Order.getCode());
        Long amount = 0L;
        for (OutOrder OutOrder : list) {
            amount = amount + OutOrder.getAmount();
        }
        return amount;
    }

    @Override
    public void invalidOutOrder(OutOrder data, String updater, String remark) {
        Date date = new Date();
        data.setStatus(EOutOrderStatus.CANCELED.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(date);
        data.setRemark(remark);

        outOrderDAO.invalidOutOrder(data);
    }

    @Override
    public List<OutOrder> getProductQuantity(String agentId, Date startDatetime,
            Date endDatetime) {

        OutOrder condition = new OutOrder();
        condition.setApplyUser(agentId);
        condition.setStartDatetime(startDatetime);
        condition.setEndDatetime(endDatetime);

        List<String> statusList = new ArrayList<String>();
        statusList.add(EOutOrderStatus.TO_APPROVE.getCode());
        statusList.add(EOutOrderStatus.TO_SEND.getCode());
        statusList.add(EOutOrderStatus.TO_RECEIVE.getCode());
        statusList.add(EOutOrderStatus.RECEIVED.getCode());
        condition.setStatusList(statusList);

        return outOrderDAO.selectList(condition);
    }

    @Override
    public boolean checkImpower(String applyUser, Date datetime) {
        OutOrder condition = new OutOrder();
        condition.setStartDatetime(datetime);
        condition.setApplyUser(applyUser);
        condition.setKind(EOutOrderKind.Impower_Order.getCode());

        List<String> statusList = new ArrayList<String>();
        statusList.add(EOutOrderStatus.TO_APPROVE.getCode());
        statusList.add(EOutOrderStatus.TO_SEND.getCode());
        statusList.add(EOutOrderStatus.TO_RECEIVE.getCode());
        statusList.add(EOutOrderStatus.RECEIVED.getCode());
        condition.setStatusList(statusList);

        List<OutOrder> list = outOrderDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUpgrade(String applyUser, Date datetime) {
        OutOrder condition = new OutOrder();
        condition.setStartDatetime(datetime);
        condition.setApplyUser(applyUser);

        condition.setKind(EOutOrderKind.Upgrade_Order.getCode());
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOutOrderStatus.TO_APPROVE.getCode());
        statusList.add(EOutOrderStatus.TO_SEND.getCode());
        statusList.add(EOutOrderStatus.TO_RECEIVE.getCode());
        statusList.add(EOutOrderStatus.RECEIVED.getCode());
        condition.setStatusList(statusList);

        List<OutOrder> list = outOrderDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
    }

    @Override
    public List<OutOrder> queryOutOrderListCount(OutOrder condition) {
        return outOrderDAO.selectOutOrderListCount(condition);
    }

    @Override
    public void updatePayGroup(OutOrder data) {
        outOrderDAO.updatePayGroup(data);
    }

    @Override
    public List<OutOrder> getChAmount(String userId) {
        OutOrder condition = new OutOrder();

        List<String> statusList = new ArrayList<String>();
        statusList.add(EOutOrderStatus.TO_SEND.getCode());
        statusList.add(EOutOrderStatus.TO_RECEIVE.getCode());
        statusList.add(EOutOrderStatus.RECEIVED.getCode());
        condition.setStatusList(statusList);

        condition.setIsPay(EIsPay.PAY_NO.getCode());
        condition.setApplyUser(userId);
        condition.setEndDatetime(new Date());

        return outOrderDAO.selectList(condition);
    }

    @Override
    public void refreshIsPay(OutOrder outOrder) {
        outOrder.setIsPay(EIsPay.PAY_YES.getCode());
        outOrderDAO.updateIsPay(outOrder);
    }

    @Override
    public String saveOutOrder(Agent applyUser, String toUserId,
            String toUserName, String teamLeader, Product pData, Specs specs,
            Long price, int quantity, String kind, XN627641Req req) {
        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());
        data.setCode(code);
        data.setProductCode(pData.getCode());
        data.setProductName(pData.getName());
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setHighUserId(applyUser.getHighUserId());

        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());
        data.setToUserId(toUserId);
        data.setToUserName(toUserName);

        data.setQuantity(quantity);
        data.setPrice(price);
        data.setPic(pData.getPic());
        data.setApplyUser(applyUser.getUserId());
        data.setLevel(applyUser.getLevel());

        data.setRealName(applyUser.getRealName());
        data.setTeamName(applyUser.getTeamName());
        data.setTeamLeader(teamLeader);
        data.setYunfei(0L);
        data.setAmount(price * quantity);
        data.setApplyDatetime(new Date());

        data.setApplyNote(req.getApplyNote());
        data.setStatus(EOutOrderStatus.Unpaid.getCode());
        data.setKind(kind);
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setSigner(req.getSigner());
        data.setProvince(req.getProvince());

        data.setMobile(req.getMobile());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setIsPay(EIsPay.PAY_NO.getCode());

        outOrderDAO.insert(data);
        return code;
    }

    @Override
    public String saveOutOrder(CUser cUser, String toUserId, String toUserName,
            Product product, Specs specs, Long price, int quantity,
            XN627640Req req, String kind) {
        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());
        data.setCode(code);
        data.setProductCode(product.getCode());
        data.setProductName(product.getName());
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setHighUserId(null);

        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());
        data.setToUserId(toUserId);
        data.setToUserName(toUserName);

        data.setQuantity(quantity);
        data.setPrice(price);
        data.setPic(product.getPic());
        data.setApplyUser(cUser.getUserId());
        data.setLevel(null);

        data.setRealName(cUser.getNickname());
        data.setTeamName(null);
        data.setTeamLeader(null);
        data.setYunfei(0L);
        data.setAmount(price * quantity);
        data.setApplyDatetime(new Date());

        data.setApplyNote(req.getApplyNote());
        data.setStatus(EOutOrderStatus.Unpaid.getCode());
        data.setKind(kind);
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setSigner(req.getSigner());
        data.setProvince(req.getProvince());

        data.setMobile(req.getMobile());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setIsPay(EIsPay.PAY_NO.getCode());

        outOrderDAO.insert(data);
        return code;
    }

    @Override
    public String saveOutOrder(CUser cUser, String toUserId, String toUserName,
            Product product, Specs specs, Long price, int quantity,
            XN627641Req req, String kind) {
        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());
        data.setCode(code);
        data.setProductCode(product.getCode());
        data.setProductName(product.getName());
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setHighUserId(null);

        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());
        data.setToUserId(toUserId);
        data.setToUserName(toUserName);

        data.setQuantity(quantity);
        data.setPrice(price);
        data.setPic(product.getPic());
        data.setApplyUser(cUser.getUserId());
        data.setLevel(null);

        data.setRealName(cUser.getNickname());
        data.setTeamName(null);
        data.setTeamLeader(null);
        data.setYunfei(0L);
        data.setAmount(price * quantity);
        data.setApplyDatetime(new Date());

        data.setApplyNote(req.getApplyNote());
        data.setStatus(EOutOrderStatus.Unpaid.getCode());
        data.setKind(kind);
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setSigner(req.getSigner());
        data.setProvince(req.getProvince());

        data.setMobile(req.getMobile());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setIsPay(EIsPay.PAY_NO.getCode());

        outOrderDAO.insert(data);
        return code;
    }

    @Override
    public String pickUpGoods(Agent agent, String teamLeader, String toUserId,
            String toUserName, Ware ware, String pic, int quantity, Long yunfei,
            XN627815Req req, String kind) {
        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());

        data.setCode(code);
        data.setProductCode(ware.getProductCode());
        data.setProductName(ware.getProductName());
        data.setPic(pic);

        data.setSpecsCode(ware.getSpecsCode());
        data.setSpecsName(ware.getSpecsName());
        data.setQuantity(quantity);
        data.setPrice(ware.getPrice());

        data.setIsWareSend(EBoolean.YES.getCode());
        data.setToUserId(toUserId);
        data.setToUserName(toUserName);
        data.setAmount(quantity * ware.getPrice());

        data.setYunfei(yunfei);
        data.setPayAmount(data.getAmount() + yunfei);
        data.setApplyUser(agent.getUserId());
        data.setLevel(agent.getLevel());
        data.setRealName(agent.getRealName());
        data.setTeamName(agent.getTeamName());

        data.setTeamLeader(teamLeader);
        data.setHighUserId(agent.getHighUserId());
        data.setApplyDatetime(new Date());
        data.setIsWareSend(EBoolean.YES.getCode());
        data.setSigner(req.getSigner());
        data.setMobile(req.getMobile());

        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setKind(kind);

        data.setIsPay(EIsPay.PAY_YES.getCode());
        data.setStatus(EOutOrderStatus.TO_APPROVE.getCode());
        outOrderDAO.insert(data);
        return code;
    }

    @Override
    public List<OutOrder> queryOutOrderList(int start, int pageNO,
            OutOrder condition) {
        return outOrderDAO.selectList(condition, start, pageNO);
    }

}
