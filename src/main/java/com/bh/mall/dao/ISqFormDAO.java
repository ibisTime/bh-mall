package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SqForm;

public interface ISqFormDAO extends IBaseDAO<SqForm> {

    String NAMESPACE = ISYSConfigDAO.class.getName().concat(".");

    void cancelSqForm(SqForm data);

    void approveSqForm(SqForm data);

    void addInfo(SqForm data);

}
