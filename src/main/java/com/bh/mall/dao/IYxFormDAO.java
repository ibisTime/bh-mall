package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.YxForm;

public interface IYxFormDAO extends IBaseDAO<YxForm> {

    String NAMESPACE = IYxFormDAO.class.getName().concat(".");

    public void allotYxForm(YxForm data);

    public void ignore(YxForm data);

    public void applyYxForm(YxForm data);

    public void toApply(YxForm data);

    public void acceptYxForm(YxForm data);

}
