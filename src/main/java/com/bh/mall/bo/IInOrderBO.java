package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InOrder;

public interface IInOrderBO extends IPaginableBO<InOrder> {

    public String saveInOrder(String applyUser, String name, Integer level,
            String toUserId, String toUserName, String teamName,
            String teamLeader, String productCode, String productName,
            String specsCode, String specsName, String pic, Long price,
            Integer quantity, String applyNote);

    public List<InOrder> queryInOrderList(InOrder condition);

    public InOrder getInOrder(String code);

    public void payInOrder(InOrder data);

    public void cancelInOrder(InOrder data);

    public void approveCancel(InOrder data);

    public long selectCount(InOrder oCondition);

    public List<InOrder> queryToDealList(int pageNo, int pageSize,
            InOrder condition);

    public String addPayGroup(InOrder inOrder, String payGroup, String payType);

    public InOrder getInOrderByPayGroup(String payGroup);

    public void paySuccess(InOrder data);

    public void payNo(InOrder data);

    // 根据时间查询订单数量
    public List<InOrder> getProductQuantity(String userId, Date startDatetime,
            Date endDatetime);

    public boolean getInOrderByUser(String userId, Date applyDatetime);

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
