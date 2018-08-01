package com.bh.mall.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IOutOrderDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EOutOrderKind;
import com.bh.mall.exception.BizException;

@Component
public class OutOrderBOImpl extends PaginableBOImpl<OutOrder>
        implements IOutOrderBO {

    @Autowired
    IOutOrderDAO outOrderDAO;

    @Override
    public String saveOutOrder(Agent applyUser, Product pData, Specs specs,
            Long price, Integer quantity, String applyNote, String signer,
            String mobile, String province, String city, String area,
            String address, String status, String kind) {

        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OutOrder.getCode());
        data.setCode(code);
        data.setProductCode(pData.getCode());
        data.setProductName(pData.getName());

        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());
        data.setToUserId(applyUser.getHighUserId());
        data.setQuantity(quantity);
        data.setPrice(price);

        data.setPic(pData.getAdvPic());
        data.setApplyUser(applyUser.getUserId());
        data.setAmount(price * quantity);
        data.setApplyDatetime(new Date());
        data.setApplyNote(applyNote);

        data.setStatus(status);
        data.setKind(kind);
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
    public void payOutOrder(OutOrder data) {
        outOrderDAO.payOutOrder(data);
    }

    @Override
    public void deliverOutOrder(OutOrder data) {
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
    public String addPayGroup(OutOrder OutOrder) {
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());
        OutOrder.setPayGroup(payGroup);
        outOrderDAO.addPayGroup(OutOrder);
        return payGroup;
    }

    @Override
    public OutOrder getOutOrderByPayGroup(String payGroup) {
        OutOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            OutOrder condition = new OutOrder();
            condition.setPayGroup(payGroup);
            data = outOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void paySuccess(OutOrder data) {
        outOrderDAO.paySuccess(data);
    }

    @Override
    public void payNo(OutOrder data) {
        outOrderDAO.payNo(data);
    }

    @Override
    public boolean checkImpowerOrder(String applyUser, Date impoweDatetime) {
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOrderStatus.Paid.getCode());
        statusList.add(EOrderStatus.TO_Apprvoe.getCode());
        statusList.add(EOrderStatus.TO_Deliver.getCode());
        statusList.add(EOrderStatus.Received.getCode());

        OutOrder condition = new OutOrder();
        condition.setApplyUser(applyUser);
        condition.setKind(EOutOrderKind.Impower_Order.getCode());
        condition.setStatusList(statusList);
        condition.setStartDatetime(impoweDatetime);

        List<OutOrder> list = outOrderDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUpgradeOrder(String applyUser) {
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOrderStatus.Paid.getCode());
        statusList.add(EOrderStatus.TO_Apprvoe.getCode());
        statusList.add(EOrderStatus.TO_Deliver.getCode());
        statusList.add(EOrderStatus.Received.getCode());

        OutOrder condition = new OutOrder();
        condition.setApplyUser(applyUser);
        condition.setKind(EOutOrderKind.Upgrade_Order.getCode());
        condition.setStatusList(statusList);
        List<OutOrder> list = outOrderDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
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
        statusList.add(EOrderStatus.Paid.getCode());
        statusList.add(EOrderStatus.TO_Apprvoe.getCode());
        statusList.add(EOrderStatus.TO_Deliver.getCode());
        statusList.add(EOrderStatus.Received.getCode());

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
            String highUserId, String userId, String signer, String mobile,
            String province, String city, String area, String address,
            String kind) {
        OutOrder data = new OutOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        data.setCode(code);
        data.setProductCode(productCode);
        data.setProductName(productName);
        data.setPic(pic);

        data.setSpecsCode(productSpecsCode);
        data.setSpecsName(productSpecsName);
        data.setQuantity(singleNumber);
        data.setPrice(price);

        // data.setToUser(toUser.getUserId());
        data.setYunfei(yunfei);
        data.setAmount(amount + yunfei);
        data.setApplyUser(userId);
        data.setApplyDatetime(new Date());

        data.setSigner(signer);
        data.setMobile(mobile);
        data.setProvince(province);
        data.setCity(city);
        data.setArea(area);
        data.setAddress(address);

        data.setKind(kind);
        data.setStatus(EOrderStatus.Paid.getCode());
        outOrderDAO.insert(data);
        return code;

    }

    @Override
    public void invalidOutOrder(OutOrder data, String updater, String remark) {
        Date date = new Date();
        data.setStatus(EOrderStatus.Canceled.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(date);
        data.setRemark(remark);

        outOrderDAO.invalidOutOrder(data);
    }

    @Override
    public void removeOutOrder(OutOrder data) {
    }

}
