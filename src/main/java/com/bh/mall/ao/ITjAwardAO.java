package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.TjAward;

/**
 * 推荐奖设置表
 * @author: clockorange 
 * @since: Jul 31, 2018 4:49:50 PM 
 * @history:
 */
@Component
public interface ITjAwardAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 修改推荐奖设置
    public void editTjAward(String code, String value1, String value2,
            String value3);

    // 分页查询
    public Paginable<TjAward> queryTjAwardPage(int start, int limit,
            TjAward condition);

    // 列表查询
    public List<TjAward> queryTjAwardList(TjAward condition);

    // 详细查询
    public TjAward getTjAward(String code);

}
