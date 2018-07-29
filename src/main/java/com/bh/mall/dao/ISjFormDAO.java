package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SjForm;

public interface ISjFormDAO extends IBaseDAO<SjForm> {

    String NAMESPACE = ISjFormDAO.class.getName().concat(".");

    public void approveUpgrade(SjForm data);

    public void cancelUplevel(SjForm data);

    public void upgradeLevel(SjForm data);

    public void updateHighUser(SjForm data);

}
