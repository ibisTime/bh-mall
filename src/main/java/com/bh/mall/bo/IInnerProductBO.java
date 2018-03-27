package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InnerProduct;

public interface IInnerProductBO extends IPaginableBO<InnerProduct> {

    void saveInnerProduct(InnerProduct data);

    void removeInnerProduct(InnerProduct data);

    void refreshInnerProduct(InnerProduct data);

    List<InnerProduct> queryInnerProductList(InnerProduct condition);

    InnerProduct getInnerProduct(String code);

    void putOnInnerProduct(InnerProduct data);

    void putdownInnerProduct(InnerProduct data);

    void changeQuantity(InnerProduct data);

}
