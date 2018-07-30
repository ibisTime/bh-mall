package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.InOrder;

public interface IInOrderDAO extends IBaseDAO<InOrder> {
    String NAMESPACE = IInOrderDAO.class.getName().concat(".");

    void payInOrder(InOrder data);

    void approveOrder(InOrder data);

    void cancelOrder(InOrder data);

    void approveCancel(InOrder data);

    List<InOrder> queryToDealList(int pageNo, int pageSize, InOrder condition);

    void addPayGroup(InOrder data);

    List<InOrder> selectOrderPage(int pageNO, int pageSize, InOrder condition);

    void payNo(InOrder data);

    void paySuccess(InOrder data);

    void invalidOrder(InOrder data);
}
