package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProCode;
import com.bh.mall.domain.MiniCode;

@Component
public interface IProCodeAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void addProCode(int number);

    public Paginable<ProCode> queryProCodePage(int start, int limit,
            ProCode condition);

    public List<ProCode> queryProCodeList(ProCode condition);

    public ProCode getProCode(String code);

    public ProCode queryProCode();

    public List<ProCode> downLoad(int number, int quantity);

    public boolean checkCode(String proCode, List<ProCode> barList,
            List<MiniCode> stList);

}
