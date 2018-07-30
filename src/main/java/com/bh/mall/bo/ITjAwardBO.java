package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.TjAward;
import com.bh.mall.dto.req.XN627548Req;

public interface ITjAwardBO extends IPaginableBO<TjAward> {

    public void removeAward(String productCode);

    public void refreshAwardList(List<XN627548Req> list);

    public List<TjAward> queryAwardList(TjAward condition);

    public TjAward getAward(String code);

    public void saveAward(String code, List<XN627548Req> awardList);

    public void refreshAward(TjAward data);

    public TjAward getAwardByType(Integer level, String productCode, String type);

    public List<TjAward> queryAwardList(String type, String productCode,
            Integer level);

}
