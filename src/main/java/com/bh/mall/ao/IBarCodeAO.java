package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.BarCode;
import com.bh.mall.domain.SecurityTrace;

@Component
public interface IBarCodeAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void addBarCode(int number);

    public Paginable<BarCode> queryBarCodePage(int start, int limit,
            BarCode condition);

    public List<BarCode> queryBarCodeList(BarCode condition);

    public BarCode getBarCode(String code);

    public BarCode queryBarCode(int number);

    public void downLoad(String code);

    public boolean checkCode(String barCode, List<BarCode> barList,
            List<SecurityTrace> stList);

}
