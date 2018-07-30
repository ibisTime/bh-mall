package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Specs;

@Component
public interface ISpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProductSpecs(Specs data);

    public void dropProductSpecs(String code);

    public void editProductSpecs(Specs data);

    public Paginable<Specs> queryProductSpecsPage(int start, int limit,
            Specs condition);

    public List<Specs> queryProductSpecsList(Specs condition);

    public Specs getProductSpecs(String code);

}
