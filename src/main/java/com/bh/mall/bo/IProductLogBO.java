package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductLog;

public interface IProductLogBO extends IPaginableBO<ProductLog> {

    public void removeProductLog(String code);

    public void refreshProductLog(ProductLog data);

    public List<ProductLog> queryProductLogList(ProductLog condition);

    public ProductLog getProductLog(String code);

    public void saveProductLog(String code, String updater, String virNumber);

    public void saveChangeLog(Product data, String type, Integer realNumber,
            Integer changeNumber, String updater);

    public void saveChangeProductLog(Product pData, String code,
            Integer realNumber, Integer quantity, String approver);

}
