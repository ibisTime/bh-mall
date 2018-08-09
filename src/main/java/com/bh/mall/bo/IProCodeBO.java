package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ProCode;

public interface IProCodeBO extends IPaginableBO<ProCode> {

    public void saveProCode(String proCode, Date date);

    public void refreshProCode(ProCode data);

    public List<ProCode> queryProCodeList(ProCode condition);

    public ProCode getProCode(String code);

    public boolean checkCode(String code);

    public ProCode getNoUseProCode();

    public List<ProCode> queryCodeList();

    public void splitSingle(ProCode barData);

}
