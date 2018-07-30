package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Specs;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.dto.req.XN627547Req;

public interface ISpecsBO extends IPaginableBO<Specs> {

    void removeProductSpecs(String productCode);

    List<Specs> queryProductSpecsList(Specs condition);

    void saveProductSpecs(String code, XN627546Req psReq);

    void saveProductSpecsList(String code, List<XN627546Req> specList);

    List<Specs> getProductSpecsByProduct(String productCode);

    Specs getProductSpecs(String productSpecsCode);

    void refreshProductSpecs(XN627546Req psReq,
            List<XN627547Req> specsPriceList);

    // 获取最小规格数量
    public Integer getMinSpecsNumber(String productCode);

}
