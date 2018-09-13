package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.CUser;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.req.XN627815Req;

public interface IOutOrderBO extends IPaginableBO<OutOrder> {

    public String saveOutOrder(Agent applyUser, String toUserId,
            String toUserName, String teamLeader, Product pData, Specs specs,
            Long price, int quantity, String kind, XN627640Req req);

    public String saveOutOrder(Agent applyUser, String highUserId,
            String toUserName, String realName, Product pData, Specs specs,
            Long price, int singleNumber, String kind, XN627641Req req);

    public String saveOutOrder(CUser cUser, String toUserId, String toUserName,
            Product product, Specs specs, Long price, int quantity,
            XN627640Req req, String kind);

    public String saveOutOrder(CUser cUser, String toUserId, String toUserName,
            Product product, Specs specs, Long price, int quantity,
            XN627641Req req, String kind);

    public void refreshOutOrder(OutOrder data);

    public List<OutOrder> queryOutOrderList(OutOrder condition);

    public OutOrder getOutOrder(String code);

    public void deliverOutOrder(OutOrder data, String status, String deliver,
            String logisticsCode, String logisticsCompany, Date deliveDatetime,
            String remark);

    public void approveOutOrder(OutOrder data);

    public void cancelOutOrder(OutOrder data);

    public void approveCancel(OutOrder data);

    public void receivedOutOrder(OutOrder data);

    public OutOrder getCheckOutOrder(String userId, String kind);

    public long selectCount(OutOrder oCondition);

    public List<OutOrder> queryToDealList(int pageNo, int pageSize,
            OutOrder condition);

    public String addPayGroup(OutOrder data, String payGroup, String payType);

    public List<OutOrder> getOutOrderByPayGroup(String payGroup);

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

    public String pickUpGoods(Agent agent, String teamLeader, String toUserId,
            String toUserName, Ware ware, String pic, int quantity, Long yunfei,
            XN627815Req req, String kind);

    // 作废订单
    public void invalidOutOrder(OutOrder data, String updater, String remark);

    // 查询订单
    public List<OutOrder> getProductQuantity(String agentId, Date startDatetime,
            Date endDatetime);

    public List<OutOrder> queryOutOrderListCount(OutOrder condition);

    public void updatePayGroup(OutOrder data);

    public List<OutOrder> getChAmount(String userId);

    public void refreshIsPay(OutOrder outOrder);

}
