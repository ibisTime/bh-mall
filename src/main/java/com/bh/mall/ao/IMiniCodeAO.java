package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.MiniCode;

@Component
public interface IMiniCodeAO {
    static final String DEFAULT_ORDER_COLUMN = "security_code";

    public Paginable<MiniCode> queryMiniCodePage(int start, int limit,
            MiniCode condition);

    public List<MiniCode> queryMiniCodeList(MiniCode condition);

    public MiniCode getMiniCode(String code);

    public void addMiniCode(int number);

    public int getSecurity(String miniCode);

    public MiniCode getTrace(String traceCode);

}
