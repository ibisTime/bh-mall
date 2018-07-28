package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.SjForm;

public interface ISjFormBO extends IPaginableBO<SjForm> {

    public SjForm getUpLevelApply(String code);

    public List<SjForm> queryUpLevelApplyPage(int start, int limit,
            SjForm condition);

    public List<SjForm> queryUpLevelApplyList(SjForm condition);

    public String upgradeLevel(SjForm data);

    public String approveUpgrade(SjForm data);

    public String approveCanenl(SjForm data);

    public String saveUpLevelApply(SjForm data, String toUserId);

    public String addUplevelApply(SjForm data);

}
