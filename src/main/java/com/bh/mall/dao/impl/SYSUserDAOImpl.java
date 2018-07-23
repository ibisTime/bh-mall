package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISYSUserDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.SYSUser;

@Repository("sysuserDAOImpl")
public class SYSUserDAOImpl extends AMybatisTemplate implements ISYSUserDAO {

    @Override
    public int insert(SYSUser data) {
        return super.insert(NAMESPACE.concat("insert_sysuser"), data);
    }

    @Override
    public SYSUser select(SYSUser condition) {
        return super.select(NAMESPACE.concat("select_sysuser"), condition,
            SYSUser.class);
    }

    @Override
    public List<SYSUser> selectList(SYSUser condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sysuser"), start,
            count, condition, SYSUser.class);
    }

    @Override
    public List<SYSUser> selectList(SYSUser condition) {
        return super.selectList(NAMESPACE.concat("select_sysuser"), condition,
            SYSUser.class);
    }

    @Override
    public long selectTotalCount(SYSUser condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_sysuser_count"),
            condition);
    }

    @Override
    public int updateLoginName(SYSUser data) {
        return super.update(NAMESPACE.concat("update_sysuser_loginName"), data);
    }

    @Override
    public int updateLoginPwd(SYSUser data) {
        return super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    @Override
    public int updateNickname(SYSUser data) {
        return super.update(NAMESPACE.concat("update_user_nickname"), data);
    }

    @Override
    public int update(SYSUser data) {
        return super.update(NAMESPACE.concat("update_sysuser"), data);
    }

    @Override
    public void resetBindMobile(SYSUser user) {
        super.update(NAMESPACE.concat("update_mobile"), user);
    }

    @Override
    public int updatePhoto(SYSUser data) {
        return super.update(NAMESPACE.concat("update_sysuser_photo"), data);
    }

    @Override
    public void ignore(BUser data) {
        super.update(NAMESPACE.concat("ignore_gency"), data);

    }

    @Override
    public void cancelImpower(BUser data) {
        super.update(NAMESPACE.concat("cancel_impower"), data);
    }

    @Override
    public void approveImpower(BUser data) {
        super.update(NAMESPACE.concat("approve_impower"), data);

    }

    @Override
    public void approveUpgrade(BUser data) {
        super.update(NAMESPACE.concat("approve_upgrade"), data);
    }

    @Override
    public void allotAgency(BUser data) {
        super.update(NAMESPACE.concat("allot_gency"), data);
    }

    @Override
    public int delete(SYSUser data) {
        return 0;
    }

}
