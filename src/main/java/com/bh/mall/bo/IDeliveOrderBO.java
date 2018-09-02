package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.DeliveOrder;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.OutOrder;

public interface IDeliveOrderBO extends IPaginableBO<DeliveOrder> {

    public void saveDeliveOrder(OutOrder outOrder);

    public void saveDeliveOrder(InnerOrder innerOrder);

    public void refreshDeliveOrder(DeliveOrder data);

    public List<DeliveOrder> queryDeliveOrderList(DeliveOrder condition);

    public DeliveOrder getDeliveOrder(String code);

    public void deliverOrder(DeliveOrder data, String proCode, String deliver,
            String logisticsCode, String logisticsCompany, String remark);

}
