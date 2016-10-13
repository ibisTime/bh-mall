package com.std.user.dao;

import com.std.user.dao.base.IBaseDAO;
import com.std.user.domain.CBanner;

public interface ICBannerDAO extends IBaseDAO<CBanner> {
    String NAMESPACE = ICBannerDAO.class.getName().concat(".");

    public int update(CBanner data);
}
