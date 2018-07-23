package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.ImpowerApply;

public interface IImpowerApplyBO extends IPaginableBO<ImpowerApply> {

    public List<ImpowerApply> queryAgencyLogList(ImpowerApply condition);

    public ImpowerApply getImpowerApply(String code);

    public List<ImpowerApply> queryImpowerApplyPage(int start, int limit,
            ImpowerApply condition);

    public String approveImpower(ImpowerApply log, BUser user);

    public String cancelImpower(BUser data);

    public String cancelImpower(BUser data, String status);

    public String saveImpowerApply(BUser data, String toUserId);

    public String impowerApply(BUser data, String payPdf);

    public String addImpowerApply(ImpowerApply data);

}
