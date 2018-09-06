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
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.enums.EBoolean;
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
    public String saveOutOrder(String applyUser, String name, Integer level,
            String toUserId, String toUserName, String highUserId,
            String teamName, String teamLeader, Product pData, Specs specs,
            Long price, Integer quantity, String applyNote, String isWareSend,
            String signer, String mobile, String province, String city,
            String area, String address, String status, String kind) {

        // 产品不包邮，计算运费
        Long yunfei = 0L;
        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());
        data.setCode(code);
        data.setProductCode(pData.getCode());
        data.setProductName(pData.getName());
        data.setIsWareSend(EBoolean.NO.getCode());
        data.setHighUserId(highUserId);

        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());
        data.setToUserId(toUserId);
        data.setToUserName(toUserName);

        data.setQuantity(quantity);
        data.setPrice(price);
        data.setPic(pData.getPic());
        data.setApplyUser(applyUser);
        data.setLevel(level);

        data.setRealName(name);
        data.setTeamName(teamName);
        data.setTeamLeader(teamLeader);
        data.setYunfei(yunfei);
        data.setAmount(price * quantity + yunfei);
        data.setApplyDatetime(new Date());

        data.setApplyNote(applyNote);
        data.setStatus(status);
        data.setKind(kind);
        data.setIsWareSend(isWareSend);
        data.setSigner(signer);
        data.setProvince(province);

        data.setMobile(mobile);
        data.setCity(city);
        data.setArea(area);
        data.setAddress(address);
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
    public void deliverOutOrder(OutOrder data, String deliver,
            String logisticsCode, String logisticsCompany, String remark) {
        data.setDeliver(deliver);
        Date date = new Date();
        data.setDeliveDatetime(date);
        data.setLogisticsCode(logisticsCode);
        data.setLogisticsCompany(logisticsCompany);

        data.setStatus(EOutOrderStatus.TO_RECEIVE.getCode());
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
    public List<OutOrder> queryToDealList(int pageNo, int pageSize,
            OutOrder condition) {
        return outOrderDAO.queryToDealList(pageNo, pageSize, condition);
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
    public String pickUpGoods(String productCode, String productName,
            String pic, String productSpecsCode, String productSpecsName,
            Integer singleNumber, Long price, Long amount, Long yunfei,
            String highUserId, Agent agent, String teamLeader, String toUserId,
            String toUserName, String isWareSend, String signer, String mobile,
            String province, String city, String area, String address,
            String kind) {
        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());

        data.setCode(code);
        data.setProductCode(productCode);
        data.setProductName(productName);
        data.setPic(pic);

        data.setSpecsCode(productSpecsCode);
        data.setSpecsName(productSpecsName);
        data.setQuantity(singleNumber);
        data.setPrice(price);

        data.setIsWareSend(EBoolean.YES.getCode());
        data.setToUserId(toUserId);
        data.setToUserName(toUserName);

        data.setYunfei(yunfei);
        data.setAmount(amount + yunfei);
        data.setApplyUser(agent.getUserId());
        data.setLevel(agent.getLevel());
        data.setRealName(agent.getRealName());
        data.setTeamName(agent.getTeamName());

        data.setTeamLeader(teamLeader);
        data.setHighUserId(agent.getHighUserId());
        data.setApplyDatetime(new Date());
        data.setIsWareSend(isWareSend);
        data.setSigner(signer);
        data.setMobile(mobile);

        data.setProvince(province);
        data.setCity(city);
        data.setArea(area);
        data.setAddress(address);
        data.setKind(kind);

        data.setStatus(EOutOrderStatus.TO_APPROVE.getCode());
        outOrderDAO.insert(data);
        return code;

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
        return outOrderDAO.selectList(condition);
    }

    @Override
    public boolean checkImpower(String applyUser, Date datetime) {
        OutOrder condition = new OutOrder();
        condition.setStartDatetime(datetime);
        condition.setApplyUser(applyUser);
        condition.setKind(EOutOrderKind.Impower_Order.getCode());

        List<String> statusList = new ArrayList<String>();
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

}
