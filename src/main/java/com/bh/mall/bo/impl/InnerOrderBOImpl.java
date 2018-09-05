package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IInnerOrderDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.enums.EInnerOrderStatus;
import com.bh.mall.exception.BizException;

@Component
public class InnerOrderBOImpl extends PaginableBOImpl<InnerOrder>
        implements IInnerOrderBO {

    @Autowired
    private IInnerOrderDAO innerOrderDAO;

    @Override
    public String saveInnerOrder(InnerProduct innerProduct, InnerSpecs specs,
            Agent agent, Integer quantity, Long yunfei, String signer,
            String mobile, String province, String area, String city,
            String address, String applyNote) {
        InnerOrder data = new InnerOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.InnerOrder.getCode());
        data.setCode(code);
        data.setProductCode(innerProduct.getCode());
        data.setProductName(innerProduct.getName());

        data.setSpecsCode(specs.getCode());
        data.setSpecsName(specs.getName());
        data.setPic(innerProduct.getPic());
        data.setPrice(specs.getPrice());
        data.setQuantity(quantity);

        Long amount = quantity * specs.getPrice();
        data.setYunfei(yunfei);

        data.setAmount(amount);
        data.setApplyUser(agent.getUserId());
        data.setRealName(agent.getRealName());
        data.setLevel(agent.getLevel());
        data.setTeamName(agent.getTeamName());
        data.setApplyDatetime(new Date());

        data.setApplyNote(applyNote);
        data.setProvince(province);
        data.setArea(area);
        data.setCity(city);
        data.setAddress(address);

        data.setMobile(mobile);
        data.setSigner(signer);
        data.setStatus(EInnerOrderStatus.Unpaid.getCode());
        innerOrderDAO.insert(data);

        return code;
    }

    @Override
    public void removeInnerOrder(String code) {
    }

    @Override
    public void refreshInnerOrder(InnerOrder data) {
        innerOrderDAO.update(data);
    }

    @Override
    public List<InnerOrder> queryInnerOrderList(InnerOrder condition) {
        return innerOrderDAO.selectList(condition);
    }

    @Override
    public InnerOrder getInnerOrder(String code) {
        InnerOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            InnerOrder condition = new InnerOrder();
            condition.setCode(code);
            data = innerOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void deliverInnerProduct(InnerOrder data, String deliver,
            String logisticsCode, String logisticsCompany, String remark) {
        data.setDeliver(deliver);
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(logisticsCode);
        data.setLogisticsCompany(logisticsCompany);
        data.setStatus(EInnerOrderStatus.TO_RECEIVE.getCode());
        data.setRemark(remark);
        innerOrderDAO.deliverInnerProduct(data);
    }

    @Override
    public void cancelInnerOrder(InnerOrder data) {
        innerOrderDAO.updateStatus(data);
    }

    @Override
    public void approveInnerOrder(InnerOrder data) {
        innerOrderDAO.approveInnerOrder(data);
    }

    @Override
    public void receiveInnerOrder(InnerOrder data) {
        innerOrderDAO.updateStatus(data);
    }

    @Override
    public long selectCount(InnerOrder ioCondition) {
        return innerOrderDAO.selectTotalCount(ioCondition);
    }

    @Override
    public List<InnerOrder> getInnerOrderByPayGroup(String payGroup) {
        InnerOrder condition = new InnerOrder();
        condition.setPayGroup(payGroup);
        return innerOrderDAO.selectList(condition);
    }

    @Override
    public String addPayGroup(InnerOrder data, String payType,
            String payGroup) {
        data.setPayGroup(payGroup);
        data.setPayType(payType);
        innerOrderDAO.addPayGroup(data);
        return payGroup;
    }

    @Override
    public void paySuccess(InnerOrder data) {
        data.setStatus(EInnerOrderStatus.TO_APPROVE.getCode());
        innerOrderDAO.paySuccess(data);
    }

    @Override
    public List<InnerOrder> queryInnerOrderPage(int start, int pageSize,
            InnerOrder condition) {

        return innerOrderDAO.selectInnerOrderPage(start, pageSize, condition);
    }

    @Override
    public void payNo(InnerOrder data) {
        data.setStatus(EInnerOrderStatus.PAY_FAIL.getCode());
        innerOrderDAO.payNo(data);
    }

    @Override
    public void batchApprove(InnerOrder data, String approver,
            String approveNote) {
        data.setStatus(EInnerOrderStatus.TO_SEND.getCode());
        data.setUpdater(approver);
        Date date = new Date();
        data.setApprover(approver);
        data.setApplyDatetime(date);
        data.setApproveNote(approveNote);

        innerOrderDAO.batchApprove(data);

    }

}
