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

    void saveSpecsList(String code, List<XN627546Req> specList);

    List<Specs> getSpecsByProduct(String productCode);

    Specs getSpecs(String productSpecsCode);

    void refreshSpecs(XN627546Req psReq, List<XN627547Req> specsPriceList);

    // 获取最小规格数量
    public Integer getMinSpecsNumber(String productCode);

    void removeSpecs(Specs specs);

}
