package com.bh.mall.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IOrderDAO;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.User;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.exception.BizException;

@Component
public class OrderBOImpl extends PaginableBOImpl<Order> implements IOrderBO {

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IUserBO userBO;

    @Override
    public void saveOrder(Order data) {
        orderDAO.insert(data);
    }

    @Override
    public void refreshOrder(Order data) {
        orderDAO.update(data);
    }

    @Override
    public List<Order> queryOrderList(Order condition) {
        return orderDAO.selectList(condition);
    }

    @Override
    public Order getOrder(String code) {
        Order data = null;
        if (StringUtils.isNotBlank(code)) {
            Order condition = new Order();
            condition.setCode(code);
            data = orderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void payOrder(Order data) {
        orderDAO.payOrder(data);
    }

    @Override
    public void deliverOrder(Order data) {
        orderDAO.deliverOrder(data);
    }

    @Override
    public void approveOrder(Order data) {
        orderDAO.approveOrder(data);
    }

    @Override
    public void cancelOrder(Order data) {
        orderDAO.cancelOrder(data);

    }

    @Override
    public void approveCancel(Order data) {
        orderDAO.approveCancel(data);
    }

    @Override
    public void receivedOrder(Order data) {
        orderDAO.receivedOrder(data);

    }

    @Override
    public Order getCheckOrder(String userId, String kind) {
        Order condition = new Order();
        condition.setApplyUser(userId);
        condition.setKind(kind);
        return orderDAO.select(condition);
    }

    @Override
    public long selectCount(Order oCondition) {
        return orderDAO.selectTotalCount(oCondition);
    }

    @Override
    public List<Order> queryToDealList(int pageNo, int pageSize,
            Order condition) {
        return orderDAO.queryToDealList(pageNo, pageSize, condition);
    }

    @Override
    public String addPayGroup(Order order) {
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());
        order.setPayGroup(payGroup);
        orderDAO.addPayGroup(order);
        return payGroup;
    }

    @Override
    public Order getInnerOrderByPayGroup(String payGroup) {
        Order data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            Order condition = new Order();
            condition.setPayGroup(payGroup);
            data = orderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void paySuccess(Order data) {
        orderDAO.paySuccess(data);
    }

    @Override
    public void payNo(Order data) {
        orderDAO.payNo(data);
    }

    @Override
    public boolean checkImpowerOrder(String applyUser, Date impoweDatetime) {
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOrderStatus.Paid.getCode());
        statusList.add(EOrderStatus.TO_Apprvoe.getCode());
        statusList.add(EOrderStatus.TO_Deliver.getCode());
        statusList.add(EOrderStatus.Received.getCode());

        Order condition = new Order();
        condition.setApplyUser(applyUser);
        condition.setKind(EOrderKind.Impower_Order.getCode());
        condition.setStatusList(statusList);
        condition.setStartDatetime(impoweDatetime);

        List<Order> list = orderDAO.selectList(condition);
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

        Order condition = new Order();
        condition.setApplyUser(applyUser);
        condition.setKind(EOrderKind.Upgrade_Order.getCode());
        condition.setStatusList(statusList);
        List<Order> list = orderDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getProductQuantity(String userId, Date startDatetime,
            Date endDatetime) {
        Order condition = new Order();
        condition.setApplyUser(userId);
        condition.setStartDatetime(startDatetime);
        condition.setEndDatetime(endDatetime);
        return orderDAO.selectList(condition);
    }

    @Override
    public Long getOrderByUser(String userId) {
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOrderStatus.Paid.getCode());
        statusList.add(EOrderStatus.TO_Apprvoe.getCode());
        statusList.add(EOrderStatus.TO_Deliver.getCode());
        statusList.add(EOrderStatus.Received.getCode());

        Order condition = new Order();
        condition.setApplyUser(userId);
        List<Order> list = orderDAO.selectList(condition);
        condition.setKind(EOrderKind.Normal_Order.getCode());
        Long amount = 0L;
        for (Order order : list) {
            amount = amount + order.getAmount();
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
        Order data = new Order();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        data.setCode(code);
        data.setProductCode(productCode);
        data.setProductName(productName);
        data.setPic(pic);

        data.setProductSpecsCode(productSpecsCode);
        data.setProductSpecsName(productSpecsName);
        data.setQuantity(singleNumber);
        data.setPrice(price);

        User toUser = userBO.getSysUser();
        data.setToUser(toUser.getUserId());
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
        orderDAO.insert(data);
        return code;

    }

}
