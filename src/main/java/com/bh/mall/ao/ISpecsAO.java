package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Specs;

/**
 * 产品规格
 * @author: clockorange 
 * @since: Jul 31, 2018 5:48:31 PM 
 * @history:
 */
@Component
public interface ISpecsAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增产品规格
    public String addSpecs(Specs data);

    // 删除产品规格
    public void dropSpecs(String code);

    // 修改产品规格
    public void editSpecs(Specs data);

    // 分页查询
    public Paginable<Specs> querySpecsPage(int start, int limit,
            Specs condition);

    // 列表查询
    public List<Specs> querySpecsList(Specs condition);

    // 详细查询
    public Specs getSpecs(String code);

}
