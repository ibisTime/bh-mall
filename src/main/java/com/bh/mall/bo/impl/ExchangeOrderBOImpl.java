package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IExchangeOrderBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IExchangeOrderDAO;
import com.bh.mall.domain.ExchangeOrder;
import com.bh.mall.exception.BizException;

@Component
public class ExchangeOrderBOImpl extends PaginableBOImpl<ExchangeOrder>
        implements IExchangeOrderBO {

    @Autowired
    private IExchangeOrderDAO exchangeOrderDAO;

    @Override
    public void saveExchangeOrder(ExchangeOrder data) {
        exchangeOrderDAO.insert(data);

    }

    @Override
    public int removeExchangeOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ExchangeOrder data = new ExchangeOrder();
            data.setCode(code);
            count = exchangeOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshExchangeOrder(ExchangeOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
        }
        return count;
    }

    @Override
    public List<ExchangeOrder> queryExchangeOrderList(ExchangeOrder condition) {
        return exchangeOrderDAO.selectList(condition);
    }

    @Override
    public ExchangeOrder getExchangeOrder(String code) {
        ExchangeOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            ExchangeOrder condition = new ExchangeOrder();
            condition.setCode(code);
            data = exchangeOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "置换单不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshChangePrice(ExchangeOrder data) {
        exchangeOrderDAO.updateChangePrice(data);
    }

    @Override
    public void approveChange(ExchangeOrder data) {
        exchangeOrderDAO.approveChange(data);
    }

    @Override
    public List<ExchangeOrder> queryExchangeOrderPage(int start, int pageSize,
            ExchangeOrder condition) {
        return exchangeOrderDAO.queryExchangeOrderPage(start, pageSize,
            condition);
    }
}
