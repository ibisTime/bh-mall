package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.YxForm;

public interface IYxFormDAO extends IBaseDAO<YxForm> {
    String NAMESPACE = IYxFormDAO.class.getName().concat(".");

    public void allotYxForm(YxForm data);

    public void update(YxForm data);

    public void updateStatus(YxForm data);

}
