package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.dto.req.XN627547Req;

public interface IProductSpecsBO extends IPaginableBO<ProductSpecs> {

    void removeProductSpecs(String productCode);

    void refreshProductSpecs(List<XN627546Req> list);

    List<ProductSpecs> queryProductSpecsList(ProductSpecs condition);

    ProductSpecs getProductSpecs(String code);

    void saveProductSpecs(String code, List<XN627546Req> specList,
            List<XN627547Req> specsPriceList);

}
