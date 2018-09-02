package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;

public interface IOutOrderBO extends IPaginableBO<OutOrder> {

    public String saveOutOrder(String applyUser, String name, Integer level,
            String toUserId, String toUserName, String highUserId,
            String teamName, String teamLeader, Product pData, Specs specs,
            Long price, Integer quantity, String applyNote, String isWareSend,
            String signer, String mobile, String province, String city,
            String area, String address, String status, String kind);

    public void refreshOutOrder(OutOrder data);

    public List<OutOrder> queryOutOrderList(OutOrder condition);

    public OutOrder getOutOrder(String code);

    public void payOutOrder(OutOrder data);

    public void deliverOutOrder(OutOrder data, String deliver,
            String logisticsCode, String logisticsCompany, String remark);

    public void approveOutOrder(OutOrder data);

    public void cancelOutOrder(OutOrder data);

    public void approveCancel(OutOrder data);

    public void receivedOutOrder(OutOrder data);

    public OutOrder getCheckOutOrder(String userId, String kind);

    public long selectCount(OutOrder oCondition);

    public List<OutOrder> queryToDealList(int pageNo, int pageSize,
            OutOrder condition);

    public String addPayGroup(OutOrder data, String payGroup, String payType);

    public OutOrder getOutOrderByPayGroup(String payGroup);

    public void paySuccess(OutOrder data);

    public void payNo(OutOrder data);

    public boolean checkImpower(String applyUser, Date impoweDatetime);

    public boolean checkUpgrade(String applyUser, Date datetime);

    public Long checkImpowerOrder(String applyUser, Date impoweDatetime);

    public Long checkUpgradeOrder(String applyUser, Date datetime);

    // 根据时间查询订单数量
    public List<OutOrder> getOutOrderQuantity(String userId, Date startDatetime,
            Date endDatetime);

    public Long getOutOrderByUser(String userId);

    // 提货
    public String pickUpGoods(String productCode, String productName,
            String pic, String specsCode, String specsName,
            Integer singleNumber, Long price, Long amount, Long yunfei,
            String highUserId, Agent agent, String teamLeader, String toUserId,
            String toUserName, String isWareSend, String signer, String mobile,
            String province, String city, String area, String address,
            String kind);

    // 作废订单
    public void invalidOutOrder(OutOrder data, String updater, String remark);

    // 查询订单
    public List<OutOrder> getProductQuantity(String agentId, Date startDatetime,
            Date endDatetime);

}
