package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ICuserDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.CUser;

@Repository("cuserDAOImpl")
public class CUserDAOImpl extends AMybatisTemplate implements ICuserDAO {

    @Override
    public int insert(CUser data) {
        return super.insert(NAMESPACE.concat("insert_cuser"), data);
    }

    @Override
    public int delete(CUser data) {
        return 0;
    }

    @Override
    public CUser select(CUser condition) {
        return super.select(NAMESPACE.concat("select_cuser"), condition,
            CUser.class);
    }

    @Override
    public long selectTotalCount(CUser condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cuser_count"),
            condition);
    }

    @Override
    public List<CUser> selectList(CUser condition) {
        return super.selectList(NAMESPACE.concat("select_cuser"), condition,
            CUser.class);
    }

    @Override
    public List<CUser> selectList(CUser condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cuser"), start, count,
            condition, CUser.class);
    }

    @Override
    public int updateLoginPwd(CUser data) {
        return super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    @Override
    public int updateStatus(CUser data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateLoginName(CUser data) {
        return super.update(NAMESPACE.concat("update_cuser_loginName"), data);
    }

    @Override
    public int updateNickname(CUser data) {
        return super.update(NAMESPACE.concat("update_cuser_nickname"), data);
    }

    @Override
    public int updatePhoto(CUser data) {
        return super.update(NAMESPACE.concat("update_cuser_photo"), data);
    }

    @Override
    public int update(CUser data) {
        return super.update(NAMESPACE.concat("update_cuser"), data);
    }

    @Override
    public int updateMobileIds(CUser data) {
        return super.update(NAMESPACE.concat("update_cuser_mobileIds"), data);
    }

    @Override
    public void setTradePwd(CUser user) {
        super.update(NAMESPACE.concat("update_trade_pwd"), user);
    }

    @Override
    public void resetBindMobile(CUser user) {
        super.update(NAMESPACE.concat("update_mobile"), user);

    }

    @Override
    public int updateWxInfo(CUser data) {
        return super.update(NAMESPACE.concat("update_wx_info"), data);
    }

}
