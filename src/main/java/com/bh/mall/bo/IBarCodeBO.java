package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.BarCode;

public interface IBarCodeBO extends IPaginableBO<BarCode> {

    public void saveBarCode(BarCode data);

    public void refreshBarCode(BarCode data);

    public List<BarCode> queryBarCodeList(BarCode condition);

    public BarCode getBarCode(String code);

    public boolean checkCode(String code);

    public BarCode getNoUseBarCode();

    public List<BarCode> queryCodeList();

}
