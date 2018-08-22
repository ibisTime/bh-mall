package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IDeliveOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IDeliveOrderDAO;
import com.bh.mall.domain.DeliveOrder;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.enums.EOrderKind;
import com.bh.mall.enums.EOutOrderStatus;
import com.bh.mall.exception.BizException;

@Component
public class DeliveOrderBOImpl extends PaginableBOImpl<DeliveOrder>
        implements IDeliveOrderBO {

    @Autowired
    private IDeliveOrderDAO deliveOrderDAO;

    public void saveDeliveOrder(OutOrder outOrder) {
        DeliveOrder data = new DeliveOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.DeliveOrder.getCode());
        data.setCode(code);
        data.setProductCode(outOrder.getProductCode());
        data.setProductName(outOrder.getProductName());

        data.setSpecsCode(outOrder.getSpecsCode());
        data.setSpecsName(outOrder.getSpecsName());
        data.setQuantity(outOrder.getQuantity());
        data.setPrice(outOrder.getPrice());
        data.setPic(outOrder.getPic());
        data.setApplyUser(outOrder.getApplyUser());

        data.setRealName(outOrder.getRealName());
        data.setAmount(outOrder.getAmount());
        data.setStatus(outOrder.getStatus());
        data.setKind(EOrderKind.OUT_Order.getCode());
        data.setSigner(outOrder.getSigner());
        data.setProvince(outOrder.getProvince());

        data.setCity(outOrder.getCity());
        data.setArea(outOrder.getArea());
        data.setAddress(outOrder.getAddress());
        deliveOrderDAO.insert(data);
    }

    public void saveDeliveOrder(InnerOrder innerOrder) {
        DeliveOrder data = new DeliveOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.DeliveOrder.getCode());
        data.setCode(code);
        data.setProductCode(innerOrder.getProductCode());
        data.setProductName(innerOrder.getProductName());

        data.setSpecsCode(innerOrder.getSpecsCode());
        data.setSpecsName(innerOrder.getSpecsName());
        data.setQuantity(innerOrder.getQuantity());
        data.setPrice(innerOrder.getPrice());
        data.setPic(innerOrder.getPic());
        data.setApplyUser(innerOrder.getApplyUser());

        data.setRealName(innerOrder.getRealName());
        data.setAmount(innerOrder.getAmount());
        data.setStatus(innerOrder.getStatus());
        data.setKind(EOrderKind.OUT_Order.getCode());
        data.setSigner(innerOrder.getSigner());
        data.setProvince(innerOrder.getProvince());

        data.setCity(innerOrder.getCity());
        data.setArea(innerOrder.getArea());
        data.setAddress(innerOrder.getAddress());
        deliveOrderDAO.insert(data);
    }

    @Override
    public void refreshDeliveOrder(DeliveOrder data) {
    }

    @Override
    public List<DeliveOrder> queryDeliveOrderList(DeliveOrder condition) {
        return deliveOrderDAO.selectList(condition);
    }

    @Override
    public DeliveOrder getDeliveOrder(String code) {
        DeliveOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            DeliveOrder condition = new DeliveOrder();
            condition.setCode(code);
            data = deliveOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return data;
    }

    @Override
    public void deliverOrder(DeliveOrder data, String deliver,
            String logisticsCode, String logisticsCompany, String remark) {
        data.setDeliver(deliver);
        Date date = new Date();
        data.setDeliveDatetime(date);
        data.setLogisticsCode(logisticsCode);
        data.setLogisticsCompany(logisticsCompany);

        data.setStatus(EOutOrderStatus.TO_RECEIVE.getCode());
        deliveOrderDAO.deliverOrder(data);
    }
}
