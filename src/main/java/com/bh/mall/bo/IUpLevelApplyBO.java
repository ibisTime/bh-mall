package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.UpLevelApply;

public interface IUpLevelApplyBO extends IPaginableBO<UpLevelApply> {

    public UpLevelApply getUpLevelApply(String code);

    public List<UpLevelApply> queryUpLevelApplyPage(int start, int limit,
            UpLevelApply condition);

    public List<UpLevelApply> queryUpLevelApplyList(UpLevelApply condition);

    public String upgradeLevel(BUser data, String payPdf);

    public String approveUpgrade(BUser data, String status);

    public String approveCanenl(BUser data, String status);

    public String saveUpLevelApply(BUser data, String toUserId);

    public String addUplevelApply(UpLevelApply data);

}
