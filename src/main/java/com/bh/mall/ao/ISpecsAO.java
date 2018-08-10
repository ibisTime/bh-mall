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

    // 修改产品库存
    public void editRepertory(String code, String type, Integer number,
            String updater);

    // 分页查询
    public Paginable<Specs> querySpecsPage(int start, int limit,
            Specs condition);

    // 列表查询
    public List<Specs> querySpecsList(Specs condition);

    // 详细查询
    public Specs getSpecs(String code);

}
