package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SqForm;

public interface ISqFormDAO extends IBaseDAO<SqForm> {

    String NAMESPACE = ISYSConfigDAO.class.getName().concat(".");

    void cancelImpower(SqForm data);

    void approveImpower(SqForm data);

    void addInfo(SqForm data);

}
