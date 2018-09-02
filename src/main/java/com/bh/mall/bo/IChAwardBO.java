package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ChAward;

public interface IChAwardBO extends IPaginableBO<ChAward> {

    public String saveChAward(String level, Long startAmount, Long endAmount,
            String percent, String updater, String remark);

    public void removeChAward(ChAward data);

    public void refreshChAward(ChAward data, Long startAmount, Long endAmount,
            String percent, String updater, String remark);

    public List<ChAward> queryChAwardList(ChAward condition);

    public ChAward getChAward(String code);

    public ChAward getChAwardByLevel(Integer level, Long amount);

    public ChAward isChAwardExist(Integer level, Long startAmount);

}
