package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ChAward;

public interface IChAwardBO extends IPaginableBO<ChAward> {

    public void saveChAward(ChAward data);

    public void removeChAward(ChAward data);

    public void refreshChAward(ChAward data);

    public List<ChAward> queryChAwardList(ChAward condition);

    public ChAward getChAward(String code);

    public ChAward getChAwardByLevel(Integer level, Long amount);

}
