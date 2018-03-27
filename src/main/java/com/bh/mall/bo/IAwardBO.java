package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Award;
import com.bh.mall.dto.req.XN627548Req;

public interface IAwardBO extends IPaginableBO<Award> {

    public void removeAward(String productCode);

    public void refreshAwardList(List<XN627548Req> list);

    public List<Award> queryAwardList(Award condition);

    public Award getAward(String code);

    public void saveAward(String code, List<XN627548Req> awardList);

    public void refreshAward(Award data);

}
