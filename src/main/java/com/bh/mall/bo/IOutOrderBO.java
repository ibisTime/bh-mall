package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;

public interface IOutOrderBO extends IPaginableBO<OutOrder> {

    public void saveOutOrder(Agent applyUser, Product pData, Specs psData,
            Integer quantity, String applyNote, String signer, String mobile,
            String province, String city, String area, String address);

    public void refreshOutOrder(OutOrder data);

    public List<OutOrder> queryOutOrderList(OutOrder condition);

    public OutOrder getOutOrder(String code);

    public void payOutOrder(OutOrder data);

    public void deliverOutOrder(OutOrder data);

    public void approveOutOrder(OutOrder data);

    public void cancelOutOrder(OutOrder data);

    public void approveCancel(OutOrder data);

    public void receivedOutOrder(OutOrder data);

    public OutOrder getCheckOutOrder(String userId, String kind);

    public long selectCount(OutOrder oCondition);

    public List<OutOrder> queryToDealList(int pageNo, int pageSize,
            OutOrder condition);

    public String addPayGroup(OutOrder inOrder);

    public OutOrder getOutOrderByPayGroup(String payGroup);

    public void paySuccess(OutOrder data);

    public void payNo(OutOrder data);

    public boolean checkImpowerOrder(String applyUser, Date impoweDatetime);

    public boolean checkUpgradeOrder(String applyUser);

    // 根据时间查询订单数量
    public List<OutOrder> getOutOrderQuantity(String userId, Date startDatetime,
            Date endDatetime);

    public Long getOutOrderByUser(String userId);

    // 提货
    public String pickUpGoods(String productCode, String productName,
            String pic, String productSpecsCode, String productSpecsName,
            Integer singleNumber, Long price, Long amount, Long yunfei,
            String highUserId, String userId, String signer, String mobile,
            String province, String city, String area, String address,
            String kind);

    // 作废订单
    public void invalidOutOrder(OutOrder data, String updater, String remark);

    // 删除未支付订单
    public void removeOutOrder(OutOrder data);

}
