package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.OutOrder;

public interface IOutOrderDAO extends IBaseDAO<OutOrder> {
    String NAMESPACE = IOutOrderDAO.class.getName().concat(".");

    void update(OutOrder data);

    void deliverOutOrder(OutOrder data);

    void approveOutOrder(OutOrder data);

    void cancelOutOrder(OutOrder data);

    void approveCancel(OutOrder data);

    void receivedOutOrder(OutOrder data);

    List<OutOrder> queryToDealList(int pageNo, int pageSize,
            OutOrder condition);

    void addPayGroup(OutOrder data);

    List<OutOrder> selectOutOrderPage(int pageNO, int pageSize,
            OutOrder condition);

    void payNo(OutOrder data);

    void paySuccess(OutOrder data);

    void invalidOutOrder(OutOrder data);

    List<OutOrder> selectOutOrderListCount(OutOrder condition);

    void updatePayGroup(OutOrder data);

    void updateIsPay(OutOrder data);
}
