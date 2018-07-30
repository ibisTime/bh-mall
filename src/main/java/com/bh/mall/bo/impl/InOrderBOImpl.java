package com.bh.mall.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IInOrderDAO;
import com.bh.mall.domain.InOrder;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.exception.BizException;

@Component
public class InOrderBOImpl extends PaginableBOImpl<InOrder>
        implements IInOrderBO {

    @Autowired
    IInOrderDAO inOrderDAO;

    @Override
    public void saveInOrder(InOrder data) {
        inOrderDAO.insert(data);
    }

    @Override
    public List<InOrder> queryInOrderList(InOrder condition) {
        return inOrderDAO.selectList(condition);
    }

    @Override
    public InOrder getInOrder(String code) {
        InOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            InOrder condition = new InOrder();
            condition.setCode(code);
            data = inOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void payInOrder(InOrder data) {
        inOrderDAO.payInOrder(data);
    }

    @Override
    public void cancelInOrder(InOrder data) {
        inOrderDAO.cancelOrder(data);

    }

    @Override
    public void approveCancel(InOrder data) {
        inOrderDAO.approveCancel(data);
    }

    @Override
    public InOrder getCheckInOrder(String userId, String kind) {
        InOrder condition = new InOrder();
        condition.setApplyUser(userId);
        condition.setKind(kind);
        return inOrderDAO.select(condition);
    }

    @Override
    public long selectCount(InOrder oCondition) {
        return inOrderDAO.selectTotalCount(oCondition);
    }

    @Override
    public List<InOrder> queryToDealList(int pageNo, int pageSize,
            InOrder condition) {
        return inOrderDAO.queryToDealList(pageNo, pageSize, condition);
    }

    @Override
    public String addPayGroup(InOrder inOrder) {
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());
        inOrder.setPayGroup(payGroup);
        inOrderDAO.addPayGroup(inOrder);
        return payGroup;
    }

    @Override
    public InOrder getInOrderByPayGroup(String payGroup) {
        InOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            InOrder condition = new InOrder();
            condition.setPayGroup(payGroup);
            data = inOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void paySuccess(InOrder data) {
        inOrderDAO.paySuccess(data);
    }

    @Override
    public void payNo(InOrder data) {
        inOrderDAO.payNo(data);
    }

    @Override
    public List<InOrder> getProductQuantity(String userId, Date startDatetime,
            Date endDatetime) {
        InOrder condition = new InOrder();
        condition.setApplyUser(userId);
        condition.setStartDatetime(startDatetime);
        condition.setEndDatetime(endDatetime);
        return inOrderDAO.selectList(condition);
    }

    @Override
    public Long getInOrderByUser(String userId) {
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOrderStatus.Paid.getCode());
        statusList.add(EOrderStatus.TO_Apprvoe.getCode());
        statusList.add(EOrderStatus.TO_Deliver.getCode());
        statusList.add(EOrderStatus.Received.getCode());

        InOrder condition = new InOrder();
        condition.setApplyUser(userId);
        List<InOrder> list = inOrderDAO.selectList(condition);
        condition.setKind(EOrderKind.Normal_Order.getCode());
        Long amount = 0L;
        for (InOrder inOrder : list) {
            amount = amount + inOrder.getAmount();
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
        InOrder data = new InOrder();
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

        data.setKind(kind);
        data.setStatus(EOrderStatus.Paid.getCode());
        inOrderDAO.insert(data);
        return code;

    }

    @Override
    public void invalidOrder(InOrder data, String updater, String remark) {
        Date date = new Date();
        data.setStatus(EOrderStatus.Canceled.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(date);
        data.setRemark(remark);

        inOrderDAO.invalidOrder(data);
    }

    @Override
    public void removeOrder(InOrder data) {
        inOrderDAO.delete(data);
    }

}
