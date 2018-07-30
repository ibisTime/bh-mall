package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InOrder;

public interface IInOrderBO extends IPaginableBO<InOrder> {

    public void saveInOrder(InOrder data);

    public List<InOrder> queryInOrderList(InOrder condition);

    public InOrder getInOrder(String code);

    public void payInOrder(InOrder data);

    public void cancelInOrder(InOrder data);

    public void approveCancel(InOrder data);

    public InOrder getCheckInOrder(String userId, String kind);

    public long selectCount(InOrder oCondition);

    public List<InOrder> queryToDealList(int pageNo, int pageSize,
            InOrder condition);

    public String addPayGroup(InOrder inOrder);

    public InOrder getInOrderByPayGroup(String payGroup);

    public void paySuccess(InOrder data);

    public void payNo(InOrder data);

    // 根据时间查询订单数量
    public List<InOrder> getProductQuantity(String userId, Date startDatetime,
            Date endDatetime);

    public Long getInOrderByUser(String userId);

    // 提货
    public String pickUpGoods(String productCode, String productName,
            String pic, String productSpecsCode, String productSpecsName,
            Integer singleNumber, Long price, Long amount, Long yunfei,
            String highUserId, String userId, String signer, String mobile,
            String province, String city, String area, String address,
            String kind);

    // 作废订单
    public void invalidOrder(InOrder data, String updater, String remark);

    // 删除未支付订单
    public void removeOrder(InOrder data);

}