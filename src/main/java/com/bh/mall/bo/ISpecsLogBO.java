package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.SpecsLog;

public interface ISpecsLogBO extends IPaginableBO<SpecsLog> {

    public void removeSpecsLog(String code);

    public void refreshSpecsLog(SpecsLog data);

    public List<SpecsLog> querySpecsLogList(SpecsLog condition);

    public SpecsLog getSpecsLog(String code);

    public void saveSpecsLog(String code, String updater, String virNumber);

    public void saveExchangeLog(Product data, String type, Integer realNumber,
            Integer changeNumber, String updater);

    public void saveExchangeProductLog(Product pData, String code,
            Integer realNumber, Integer quantity, String approver);

}
