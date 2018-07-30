package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.TjAward;
import com.bh.mall.dto.req.XN627548Req;

public interface ITjAwardBO extends IPaginableBO<TjAward> {

    public void removeTjAward(String productCode);

    public void refreshTjAwardList(List<XN627548Req> list);

    public List<TjAward> queryTjAwardList(TjAward condition);

    public TjAward getTjAward(String code);

    public void saveTjAward(String code, List<XN627548Req> awardList);

    public void refreshTjAward(TjAward data);

    public TjAward getTjAwardByType(Integer level, String productCode, String type);

    public List<TjAward> queryTjAwardList(String type, String productCode,
            Integer level);

}
