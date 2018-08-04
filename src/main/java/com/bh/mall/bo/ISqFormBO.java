package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.SqForm;

public interface ISqFormBO extends IPaginableBO<SqForm> {

    // 申请授权单
    public SqForm applySqForm(String userId, String realName, String mobile,
            String wxId, String applyLevel, String toUserId, String teamName,
            String introducer, String userRefree, String idKind, String idNo,
            String idHand, String province, String citt, String area,
            String address, String status);

    public SqForm refreshSqForm(SqForm data, String realName, String mobile,
            String wxId, String applyLevel, String toUserId, String teamName,
            String introducer, String userRefree, String idKind, String idNo,
            String idHand, String province, String city, String area,
            String address, String status);

    // 审核授权单
    public String approveSqForm(SqForm sqForm, String approver,
            String approveName, String remark, String status);

    public List<SqForm> querySqFormPage(int start, int limit, SqForm condition);

    public List<SqForm> querySqFormList(SqForm condition);

    public SqForm getSqForm(String code);

    // 申请退出
    public String cancelSqForm(SqForm sqForm, String status);

}
