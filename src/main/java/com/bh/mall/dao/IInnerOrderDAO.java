package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.InnerOrder;

public interface IInnerOrderDAO extends IBaseDAO<InnerOrder> {
    String NAMESPACE = IInnerOrderDAO.class.getName().concat(".");

    void update(InnerOrder data);

    void deliverInnerProduct(InnerOrder data);

    void approveInnerOrder(InnerOrder data);

    void updateStatus(InnerOrder data);

    void addPayGroup(InnerOrder data);

    void paySuccess(InnerOrder data);

    List<InnerOrder> selectInnerOrderPage(int start, int pageSize,
            InnerOrder condition);

    void payNo(InnerOrder data);

    void batchApprove(InnerOrder data);
}
