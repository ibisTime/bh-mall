package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IInOrderDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.enums.EInOrderStatus;
import com.bh.mall.enums.EIsPay;
import com.bh.mall.exception.BizException;

@Component
public class InOrderBOImpl extends PaginableBOImpl<InOrder>
        implements IInOrderBO {

    @Autowired
    IInOrderDAO inOrderDAO;

    @Override

    public String saveInOrder(Agent agent, String toUserName, String teamLeader,
            Product product, Specs specs, Long price, Integer quantity,
            String applyNote)

    {
        InOrder data = new InOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.InOrder.getCode());

        data.setCode(code);
        data.setApplyUser(agent.getUserId());
        data.setRealName(agent.getRealName());
        data.setLevel(agent.getLevel());
        data.setTeamName(agent.getTeamName());
        data.setToUserId(agent.getHighUserId());

        data.setToUserName(toUserName);
        data.setTeamName(agent.getTeamName());
        data.setTeamLeader(teamLeader);

        data.setProductCode(product.getCode());
        data.setProductName(product.getName());
        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());

        data.setQuantity(quantity);
        data.setPrice(price);
        data.setAmount(quantity * price);
        data.setPic(product.getPic());
        data.setStatus(EInOrderStatus.Unpaid.getCode());
        Date date = new Date();

        data.setIsPay(EIsPay.PAY_NO.getCode());
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
            if (null == data) {
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
    public long selectCount(InOrder oCondition) {
        return inOrderDAO.selectTotalCount(oCondition);
    }

    @Override
    public List<InOrder> queryToDealList(int pageNo, int pageSize,
            InOrder condition) {
        return inOrderDAO.queryToDealList(pageNo, pageSize, condition);
    }

    @Override
    public String addPayGroup(InOrder inOrder, String payGroup,
            String payType) {
        inOrder.setPayGroup(payGroup);
        inOrder.setPayType(payType);
        inOrderDAO.addPayGroup(inOrder);
        return payGroup;
    }

    @Override
    public List<InOrder> getInOrderByPayGroup(String payGroup) {

        InOrder condition = new InOrder();
        condition.setPayGroup(payGroup);
        return inOrderDAO.selectList(condition);
    }

    @Override
    public void paySuccess(InOrder data) {
        Date date = new Date();
        data.setPayAmount(data.getAmount());
        data.setPayDatetime(date);
        data.setStatus(EInOrderStatus.Received.getCode());
        inOrderDAO.paySuccess(data);
    }

    @Override
    public void payNo(InOrder data) {
        inOrderDAO.payNo(data);
    }

    @Override
    public List<InOrder> getProductQuantity(String userId, String specsCode,
            Date startDatetime, Date endDatetime) {
        InOrder condition = new InOrder();
        condition.setApplyUser(userId);
        condition.setSpecsCode(specsCode);
        condition.setStartDatetime(startDatetime);
        condition.setEndDatetime(endDatetime);
        condition.setStatus(EInOrderStatus.Received.getCode());
        return inOrderDAO.selectList(condition);
    }

    @Override
    public boolean getInOrderByUser(String userId, Date applyDatetime) {

        InOrder condition = new InOrder();
        condition.setApplyUser(userId);
        condition.setStartDatetime(applyDatetime);
        condition.setStatus(EInOrderStatus.Received.getCode());

        List<InOrder> list = inOrderDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
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

    public static void main(String[] args) {
        float i = 10;
        int j = 3;
        double d = i / j;
        System.out.println(d);
    }

    @Override
    public void addPayGroup(InOrder inOrder) {
        inOrderDAO.addPayGroup(inOrder);
    }

    @Override
    public List<InOrder> queryInOrderListCount(InOrder condition) {

        return inOrderDAO.selectorderCount(condition);
    }

    @Override
    public void updatePayGroup(InOrder data) {

        inOrderDAO.updatePayGroup(data);
    }

    @Override
    public List<InOrder> getChAmount(String applyUser) {
        InOrder condition = new InOrder();
        condition.setIsPay(EIsPay.PAY_NO.getCode());
        condition.setApplyUser(applyUser);
        condition.setStatus(EInOrderStatus.Received.getCode());
        condition.setEndDatetime(new Date());

        return inOrderDAO.selectList(condition);
    }

    @Override
    public void refreshIsPay(InOrder data) {
        data.setIsPay(EIsPay.PAY_YES.getCode());
        inOrderDAO.updateIsPay(data);
    }
}
