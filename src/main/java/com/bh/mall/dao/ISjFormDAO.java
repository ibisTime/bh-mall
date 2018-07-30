package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SjForm;

public interface ISjFormDAO extends IBaseDAO<SjForm> {

    String NAMESPACE = ISjFormDAO.class.getName().concat(".");

    public void approveSjForm(SjForm data);

    public void cancelSjForm(SjForm data);

    public void applySjForm(SjForm data);

    public void updateHighUser(SjForm data);

}
