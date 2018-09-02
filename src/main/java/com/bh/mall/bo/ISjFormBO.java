package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SjForm;

public interface ISjFormBO extends IPaginableBO<SjForm> {

    public String applySjForm(Agent data, String toUserId, String teamName,
            String newLevel, String idKind, String idNo, String idHand,
            String payPdf, String payAmount, String status);

    public String refreshSjForm(SjForm sjForm, Agent data, String toUserId,
            String teamName, String newLevel, String idKind, String idNo,
            String idHand, String payPdf, String payAmount, String status);

    public String approveSjForm(SjForm sjForm, Agent agent, String userId,
            String realName, String remark, String status);

    public List<SjForm> querySjFormList(SjForm condition);

    public SjForm getSjForm(String code);

    public boolean checkIsSj(String userId);

}
