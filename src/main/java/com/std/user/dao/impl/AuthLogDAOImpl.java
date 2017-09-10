package com.std.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.user.dao.IAuthLogDAO;
import com.std.user.dao.base.support.AMybatisTemplate;
import com.std.user.domain.AuthLog;

@Repository("authLogDAOImpl")
public class AuthLogDAOImpl extends AMybatisTemplate implements IAuthLogDAO {

    @Override
    public int insert(AuthLog data) {
        return super.insert(NAMESPACE.concat("insert_authLog"), data);
    }

    @Override
    public int approveAuth(AuthLog data) {
        return super.update(NAMESPACE.concat("approve_auth"), data);
    }

    @Override
    public int reApplyAuth(AuthLog data) {
        return super.update(NAMESPACE.concat("update_reapply"), data);
    }

    @Override
    public int delete(AuthLog data) {
        return 0;
    }

    @Override
    public AuthLog select(AuthLog condition) {
        return super.select(NAMESPACE.concat("select_authLog"), condition,
            AuthLog.class);
    }

    @Override
    public long selectTotalCount(AuthLog condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_authLog_count"),
            condition);
    }

    @Override
    public List<AuthLog> selectList(AuthLog condition) {
        return super.selectList(NAMESPACE.concat("select_authLog"), condition,
            AuthLog.class);
    }

    @Override
    public List<AuthLog> selectList(AuthLog condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_authLog"), start,
            count, condition, AuthLog.class);
    }
}
