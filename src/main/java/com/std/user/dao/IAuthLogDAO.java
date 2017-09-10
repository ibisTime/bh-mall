package com.std.user.dao;

import com.std.user.dao.base.IBaseDAO;
import com.std.user.domain.AuthLog;

public interface IAuthLogDAO extends IBaseDAO<AuthLog> {
    String NAMESPACE = IAuthLogDAO.class.getName().concat(".");

    public int reApplyAuth(AuthLog data);

    public int approveAuth(AuthLog data);
}
