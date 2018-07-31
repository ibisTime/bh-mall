package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ChAward;
import com.bh.mall.dto.req.XN627862Req;

/**
 * 出货奖励
 * @author: LENOVO 
 * @since: 2018年7月31日 下午9:12:06 
 * @history:
 */
@Component
public interface IChAwardAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增出货奖励
    public String addChAward(String level, Long startAmount, Long endAmount,
            String percent, String updater, String remark);

    // 删除出货奖励
    public void dropChAward(String code);

    // 修改出货奖励
    public void editChAward(XN627862Req req);

    // 分页查询出货奖励
    public Paginable<ChAward> queryChAwardPage(int start, int limit,
            ChAward condition);

    // 列表查询出货奖励
    public List<ChAward> queryChAwardList(ChAward condition);

    // 详情查询出货奖励
    public ChAward getChAward(String code);

}
