package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.SpecsLog;

public interface ISpecsLogBO extends IPaginableBO<SpecsLog> {

    public void removeSpecsLog(String code);

    public void refreshSpecsLog(SpecsLog data);

    public List<SpecsLog> querySpecsLogList(SpecsLog condition);

    public SpecsLog getSpecsLog(String code);

    public void saveSpecsLog(String productCode, String productName,
            Specs specs, String type, Integer number, String updater,
            String remark);

}
