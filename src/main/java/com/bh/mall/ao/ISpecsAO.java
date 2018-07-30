package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Specs;

@Component
public interface ISpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSpecs(Specs data);

    public void dropSpecs(String code);

    public void editSpecs(Specs data);

    public Paginable<Specs> querySpecsPage(int start, int limit,
            Specs condition);

    public List<Specs> querySpecsList(Specs condition);

    public Specs getSpecs(String code);

}
