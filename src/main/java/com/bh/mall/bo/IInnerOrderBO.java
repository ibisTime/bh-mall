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

    public void payOrder(InnerOrder data);

    public void deliverInnerProduct(InnerOrder data);

    public void cancelInnerOrder(InnerOrder data);

    public void approveInnerOrder(InnerOrder data);

}
