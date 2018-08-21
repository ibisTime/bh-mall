package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IDeliveOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IDeliveOrderDAO;
import com.bh.mall.domain.DeliveOrder;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.exception.BizException;

@Component
public class DeliveOrderBOImpl extends PaginableBOImpl<DeliveOrder>
        implements IDeliveOrderBO {

    @Autowired
    private IDeliveOrderDAO deliveOrderDAO;

    public void saveDeliveOrder(OutOrder outOrder) {
        DeliveOrder data = new DeliveOrder();
    }

    public void saveDeliveOrder(InnerOrder innerOrder) {
        String code = null;
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
}
