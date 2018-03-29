package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.InnerOrder;

public interface IInnerOrderDAO extends IBaseDAO<InnerOrder> {
    String NAMESPACE = IInnerOrderDAO.class.getName().concat(".");

    void payOrder(InnerOrder data);

    void update(InnerOrder data);

    void deliverInnerProduct(InnerOrder data);

    void approveInnerOrder(InnerOrder data);

    void updateStatus(InnerOrder data);
}
