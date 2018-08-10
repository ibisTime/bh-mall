package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.MiniCode;

public interface IMiniCodeBO extends IPaginableBO<MiniCode> {

    public void saveMiniCode(String miniCode, String traceCode, String proCode,
            Date date);

    public List<MiniCode> queryMiniCodeList(MiniCode condition);

    public MiniCode getMiniCode(String code);

    public List<MiniCode> getMiniCodeByProCode(String code);

    public List<MiniCode> queryCodeList();

    public MiniCode getNoUseMiniCode();

    public void refreshMiniCode(MiniCode trace);

    void refreshStatus(MiniCode data, String orderCode);

    public MiniCode getSecurity(String miniCode);

    public MiniCode getTrace(String traceCode);

    public int refreshSecurity(MiniCode data);

}
