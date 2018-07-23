package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Order;

public interface IOrderBO extends IPaginableBO<Order> {

    public void saveOrder(Order data);

    public void refreshOrder(Order data);

    public List<Order> queryOrderList(Order condition);

    public Order getOrder(String code);

    public void payOrder(Order data);

    public void deliverOrder(Order data);

    public void approveOrder(Order data);

    public void cancelOrder(Order data);

    public void approveCancel(Order data);

    public void receivedOrder(Order data);

    public Order getCheckOrder(String userId, String kind);

    public long selectCount(Order oCondition);

    public List<Order> queryToDealList(int pageNo, int pageSize,
            Order condition);

    public String addPayGroup(Order order);

    public Order getInnerOrderByPayGroup(String payGroup);

    public void paySuccess(Order data);

    public void payNo(Order data);

    public boolean checkImpowerOrder(String applyUser, Date impoweDatetime);

    public boolean checkUpgradeOrder(String applyUser);

    // 根据时间查询订单数量
    public List<Order> getProductQuantity(String userId, Date startDatetime,
            Date endDatetime);

    public Long getOrderByUser(String userId);

    // 提货
    public String pickUpGoods(String productCode, String productName,
            String pic, String productSpecsCode, String productSpecsName,
            Integer singleNumber, Long price, Long amount, Long yunfei,
            String highUserId, String userId, String signer, String mobile,
            String province, String city, String area, String address,
            String kind);

}
