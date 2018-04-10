package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ChangeProduct;

public interface IChangeProductBO extends IPaginableBO<ChangeProduct> {

    public void saveChangeProduct(ChangeProduct data);

    public int removeChangeProduct(String code);

    public int refreshChangeProduct(ChangeProduct data);

    public List<ChangeProduct> queryChangeProductList(ChangeProduct condition);

    public ChangeProduct getChangeProduct(String code);

}
