package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Specs;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.dto.req.XN627547Req;

public interface ISpecsBO extends IPaginableBO<Specs> {

    void removeSpecsByProduct(String productCode);

    List<Specs> querySpecsList(Specs condition);

    void saveSpecs(String code, XN627546Req psReq);

    void saveSpecsList(String productCode, String productName,
            List<XN627546Req> specList, String updater, String remark);

    List<Specs> getSpecsByProduct(String productCode);

    Specs getSpecs(String code);

    void refreshSpecs(XN627546Req psReq, List<XN627547Req> specsPriceList);

    void removeSpecs(Specs specs);

    void refreshRepertory(String productName, Specs specs, String type,
            Integer number, String updater, String remark);

    List<Specs> querySpecsListByB(Specs psCondition);

    Integer getMinSpecsNumber(Specs data, int number);

    List<Specs> querySpecsPage(int start, int pageSize, Specs condition);

}
