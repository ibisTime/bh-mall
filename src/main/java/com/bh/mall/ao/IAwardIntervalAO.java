package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AwardInterval;
import com.bh.mall.dto.req.XN627862Req;

@Component
public interface IAwardIntervalAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addAwardInterval(String level, Long startAmount,
            Long endAmount, String percent, String updater, String remark);

    public void dropAwardInterval(String code);

    public void editAwardInterval(XN627862Req req);

    public Paginable<AwardInterval> queryAwardIntervalPage(int start, int limit,
            AwardInterval condition);

    public List<AwardInterval> queryAwardIntervalList(AwardInterval condition);

    public AwardInterval getAwardInterval(String code);

}
