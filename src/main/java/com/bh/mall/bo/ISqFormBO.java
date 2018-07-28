package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.SqForm;

public interface ISqFormBO extends IPaginableBO<SqForm> {

    public List<SqForm> queryAgencyLogList(SqForm condition);

    public SqForm getImpowerApply(String code);

    public List<SqForm> queryImpowerApplyPage(int start, int limit,
            SqForm condition);

    public String approveImpower(SqForm user);

    public String cancelImpower(SqForm data);

    public String cancelImpower(SqForm data, String status);

    public String saveImpowerApply(SqForm data, String toUserId);

    public String impowerApply(SqForm data);

    public String addImpowerApply(SqForm data);

    public String toApply(SqForm data);

    public String addInfo(SqForm data);
}
