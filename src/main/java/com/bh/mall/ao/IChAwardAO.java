package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ChAward;
import com.bh.mall.dto.req.XN627862Req;

@Component
public interface IChAwardAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addChAward(String level, Long startAmount,
            Long endAmount, String percent, String updater, String remark);

    public void dropChAward(String code);

    public void editChAward(XN627862Req req);

    public Paginable<ChAward> queryChAwardPage(int start, int limit,
            ChAward condition);

    public List<ChAward> queryChAwardList(ChAward condition);

    public ChAward getChAward(String code);

}
