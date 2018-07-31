package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareLog;

/**
 * 云仓记录
 * @author: clockorange 
 * @since: Jul 31, 2018 4:46:51 PM 
 * @history:
 */

@Component
public interface IWareLogAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 分页查询
    public Paginable<WareLog> queryWareLogPage(int start, int limit,
            WareLog condition);

    // 列表查询
    public List<WareLog> queryWareLogList(WareLog condition);

    // 详细查询
    public WareLog getWareLog(String code);

}
