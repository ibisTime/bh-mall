package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IOrderReportBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IOrderReportDAO;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.OrderReport;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.enums.EOrderReportKind;
import com.bh.mall.exception.BizException;

@Component
public class OrderReportBOImpl extends PaginableBOImpl<OrderReport>
        implements IOrderReportBO {

    @Autowired
    private IOrderReportDAO orderReportDAO;

//    @Override
//    public void saveInOrderReport(InOrder inOrder) {
//        OrderReport data = new OrderReport();
//        data.setCode(inOrder.getCode());
//        data.setKind(EOrderReportKind.IN_ORDER.getCode());
//        data.setApplyUser(inOrder.getApplyUser());
//        data.setRealName(inOrder.getRealName());
//        data.setLevel(inOrder.getLevel());
//
//        data.setTeamName(inOrder.getTeamName());
//        data.setToUserId(inOrder.getToUserId());
//        data.setToUserName(inOrder.getToUserName());
//        data.setTeamName(inOrder.getTeamName());
//        data.setTeamLeader(inOrder.getTeamLeader());
//
//        data.setProductCode(inOrder.getProductCode());
//        data.setProductName(inOrder.getProductName());
//        data.setSpecsCode(inOrder.getSpecsCode());
//        data.setSpecsName(inOrder.getSpecsName());
//
//        data.setQuantity(inOrder.getQuantity());
//        data.setPrice(inOrder.getPrice());
//        data.setAmount(inOrder.getAmount());
//        data.setPic(inOrder.getPic());
//
//        data.setApplyDatetime(inOrder.getApplyDatetime());
//        data.setApplyNote(inOrder.getApplyNote());
//        orderReportDAO.insert(data);
//    }

    @Override
    public void saveOutOrderReport(OutOrder outOrder) {
        OrderReport data = new OrderReport();

        data.setCode(outOrder.getCode());
        data.setKind(outOrder.getKind());
        data.setType(outOrder.getKind());
        data.setApplyUser(outOrder.getApplyUser());
        data.setApplyDatetime(outOrder.getApplyDatetime());
        data.setRealName(outOrder.getRealName());
        data.setLevel(outOrder.getLevel());

        data.setTeamName(outOrder.getTeamName());
        data.setToUserId(outOrder.getToUserId());
        data.setToUserName(outOrder.getToUserName());
        data.setTeamName(outOrder.getTeamName());
        data.setTeamLeader(outOrder.getTeamLeader());

        data.setProductCode(outOrder.getProductCode());
        data.setProductName(outOrder.getProductName());
        data.setSpecsCode(outOrder.getSpecsCode());
        data.setSpecsName(outOrder.getSpecsName());

        data.setQuantity(outOrder.getQuantity());
        data.setPrice(outOrder.getPrice());
        data.setAmount(outOrder.getAmount());
        data.setPic(outOrder.getPic());

        data.setSigner(outOrder.getSigner());
        data.setMobile(outOrder.getMobile());
        data.setProvince(outOrder.getProvince());
        data.setCity(outOrder.getCity());
        data.setArea(outOrder.getArea());
        data.setAddress(outOrder.getAddress());

        data.setDeliver(outOrder.getDeliver());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(outOrder.getLogisticsCode());
        data.setLogisticsCompany(outOrder.getLogisticsCompany());
        data.setRemark(outOrder.getRemark());
        orderReportDAO.insert(data);
    }

    @Override
    public List<OrderReport> queryOrderReportList(OrderReport condition) {
        return orderReportDAO.selectList(condition);
    }

    @Override
    public OrderReport getOrderReport(String code) {
        OrderReport data = null;
        if (StringUtils.isNotBlank(code)) {
            OrderReport condition = new OrderReport();
            condition.setCode(code);
            data = orderReportDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单统计不存在");
            }
        }
        return data;
    }

}
