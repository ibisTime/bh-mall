package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AwardInterval;

public interface IAwardIntervalBO extends IPaginableBO<AwardInterval> {

    public void saveAwardInterval(AwardInterval data);

    public void removeAwardInterval(AwardInterval data);

    public void refreshAwardInterval(AwardInterval data);

    public List<AwardInterval> queryAwardIntervalList(AwardInterval condition);

    public AwardInterval getAwardInterval(String code);

    public AwardInterval getAwardIntervalByLevel(Integer level, Long amount);

}
