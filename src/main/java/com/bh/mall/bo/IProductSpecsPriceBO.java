package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.dto.req.XN627547Req;

public interface IProductSpecsPriceBO extends IPaginableBO<ProductSpecsPrice> {

    public boolean isProductSpecsPriceExist(String code);

    public String saveProductSpecsPrice(ProductSpecsPrice data);

    public int removeProductSpecsPrice(String code);

    public void refreshProductSpecsPrice(List<XN627547Req> list);

    public List<ProductSpecsPrice> queryProductSpecsPriceList(
            ProductSpecsPrice pspConditon);

    public ProductSpecsPrice getProductSpecsPrice(String code);

    public ProductSpecsPrice getPriceBySpecsCode(String spcesCode);

    public Long getPriceByLevel(String productSpecsCode, Integer level);

}
