package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.SjForm;

public interface ISjFormBO extends IPaginableBO<SjForm> {

    public SjForm getSjForm(String code);

    public List<SjForm> querySjFormPage(int start, int limit, SjForm condition);

    public List<SjForm> querySjFormList(SjForm condition);

    public String applySjForm(SjForm upData);

    public String approveSjForm(SjForm data);

    public String approveCanenlSjForm(SjForm data);

    public String saveSjForm(SjForm data, String toUserId);

    public String addSjForm(SjForm data);

}
