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
import com.bh.mall.enums.EInOrderStatus;
import com.bh.mall.exception.BizException;

@Component
public class InOrderBOImpl extends PaginableBOImpl<InOrder>
        implements IInOrderBO {

    @Autowired
    IInOrderDAO inOrderDAO;

    @Override
    public String saveInOrder(String applyUser, String realName, Integer level,
            String toUserId, String toUserName, String teamName,
            String teamLeader, String productCode, String productName,
            String specsCode, String specsName, String pic, Long price,
            Integer quantity, String applyNote) {
        InOrder data = new InOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Order.getCode());

        data.setCode(code);
        data.setApplyUser(applyUser);
        data.setRealName(realName);
        data.setLevel(level);
        data.setTeamName(teamName);
        data.setToUserId(toUserId);

        data.setToUserName(toUserName);
        data.setTeamName(teamName);
        data.setTeamLeader(teamLeader);

        data.setProductCode(productCode);
        data.setProductName(productName);
        data.setSpecsCode(specsCode);
        data.setSpecsName(specsName);

        data.setQuantity(quantity);
        data.setPrice(price);
        data.setAmount(quantity * price);
        data.setPic(pic);
        data.setStatus(EInOrderStatus.Unpaid.getCode());
        Date date = new Date();

        data.setApplyDatetime(date);
        data.setApplyNote(applyNote);
        inOrderDAO.insert(data);
        return code;
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
        data.setStatus(EInOrderStatus.TO_Cancel.getCode());
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
        Date date = new Date();
        data.setPayDatetime(date);
        data.setStatus(EInOrderStatus.Received.getCode());
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
        statusList.add(EInOrderStatus.Received.getCode());

        InOrder condition = new InOrder();
        condition.setApplyUser(userId);
        List<InOrder> list = inOrderDAO.selectList(condition);
        Long amount = 0L;
        for (InOrder inOrder : list) {
            amount = amount + inOrder.getAmount();
        }
        return amount;
    }

    @Override
    public String pickUpGoods(String productCode, String productName,
            String pic, String productSpecsCode, String specsName,
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

        data.setSpecsCode(productSpecsCode);
        data.setSpecsName(specsName);
        data.setQuantity(singleNumber);
        data.setPrice(price);

        data.setStatus(EInOrderStatus.Received.getCode());
        inOrderDAO.insert(data);
        return code;

    }

    @Override
    public void invalidOrder(InOrder data, String approver, String remark) {
        Date date = new Date();
        data.setStatus(EInOrderStatus.Canceled.getCode());
        data.setApprover(approver);
        data.setApproveDatetime(date);
        data.setRemark(remark);

        inOrderDAO.invalidOrder(data);
    }

    @Override
    public void removeOrder(InOrder data) {
        inOrderDAO.delete(data);
    }

}
