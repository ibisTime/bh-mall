package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SpecsLog;

/**
 * 库存记录
 * @author: clockorange 
 * @since: Jul 31, 2018 5:46:49 PM 
 * @history:
 */
@Component
public interface ISpecsLogAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 库存记录
    public void dropSpecstLog(String code);

    // 库存记录
    public void editSpecsLog(SpecsLog data);

    // 分页查询
    public Paginable<SpecsLog> querySpecsLogPage(int start, int limit,
            SpecsLog condition);

    // 列表查询
    public List<SpecsLog> querySpecsLogList(SpecsLog condition);

    // 详细查询
    public SpecsLog getSpecsLog(String code);

}
