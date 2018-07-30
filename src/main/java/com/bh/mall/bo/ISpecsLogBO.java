package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.SpecsLog;

public interface ISpecsLogBO extends IPaginableBO<SpecsLog> {

    public void removeProductLog(String code);

    public void refreshProductLog(SpecsLog data);

    public List<SpecsLog> queryProductLogList(SpecsLog condition);

    public SpecsLog getProductLog(String code);

    public void saveProductLog(String code, String updater, String virNumber);

    public void saveChangeLog(Product data, String type, Integer realNumber,
            Integer changeNumber, String updater);

    public void saveChangeProductLog(Product pData, String code,
            Integer realNumber, Integer quantity, String approver);

}
