package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InnerOrder;

public interface IInnerOrderBO extends IPaginableBO<InnerOrder> {

    public void saveInnerOrder(InnerOrder data);

    public void removeInnerOrder(String code);

    public void refreshInnerOrder(InnerOrder data);

    public List<InnerOrder> queryInnerOrderList(InnerOrder condition);

    public InnerOrder getInnerOrder(String code);

    public void deliverInnerProduct(InnerOrder data, String deliver,
            String logisticsCode, String logisticsCompany, String remark);

    public void cancelInnerOrder(InnerOrder data);

    public void approveInnerOrder(InnerOrder data);

    public void receiveInnerOrder(InnerOrder data);

    public long selectCount(InnerOrder ioCondition);

    public InnerOrder getInnerOrderByPayGroup(String payGroup);

    public String addPayGroup(InnerOrder data, String payType);

    public void paySuccess(InnerOrder data);

    public List<InnerOrder> queryInnerOrderPage(int start, int pageSize,
            InnerOrder condition);

    public void payNo(InnerOrder data);

    public void batchApprove(InnerOrder data, String approver, String remark);

}
