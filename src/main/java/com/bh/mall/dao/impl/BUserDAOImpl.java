package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IBuserDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.BUser;

@Repository("buserDAOImpl")
public class BUserDAOImpl extends AMybatisTemplate implements IBuserDAO {

    // IBaseDAO
    @Override
    public BUser select(BUser condition) {
        return super.select(NAMESPACE.concat("select_buser"), condition,
            BUser.class);
    }

    @Override
    public long selectTotalCount(BUser condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_buser_count"),
            condition);
    }

    @Override
    public List<BUser> selectList(BUser condition) {
        return super.selectList(NAMESPACE.concat("select_buser"), condition,
            BUser.class);
    }

    @Override
    public List<BUser> selectList(BUser condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_buser"), start, count,
            condition, BUser.class);
    }

    @Override
    public int insert(BUser data) {
        return super.insert(NAMESPACE.concat("insert_buser"), data);
    }

    @Override
    public int delete(BUser data) {
        return 0;
    }

    // IBuserDAO

    @Override
    public int updateLoginPwd(BUser data) {
        return super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    @Override
    public int updateStatus(BUser data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateRole(BUser data) {
        return super.update(NAMESPACE.concat("update_role"), data);
    }

    @Override
    public void updateLog(BUser data) {
        super.update(NAMESPACE.concat("update_log"), data);
    }

    /** 
     * @see com.bh.mall.dao.IUserDAO#updateLoginName(com.bh.mall.domain.User)
     */
    @Override
    public int updateLoginName(BUser data) {
        return super.update(NAMESPACE.concat("update_buser_loginName"), data);
    }

    @Override
    public int updateNickname(BUser data) {
        return super.update(NAMESPACE.concat("update_buser_nickname"), data);
    }

    @Override
    public int updatePhoto(BUser data) {
        return super.update(NAMESPACE.concat("update_buser_photo"), data);
    }

    @Override
    public int updateMobileIds(BUser data) {
        return super.update(NAMESPACE.concat("update_buser_mobileIds"), data);
    }

    @Override
    public int updateWxInfo(BUser data) {
        return super.update(NAMESPACE.concat("update_wx_info"), data);
    }

    @Override
    public void resetBindMobile(BUser buser) {
        super.update(NAMESPACE.concat("update_mobile"), buser);
    }

    @Override
    public int approveUser(BUser data) {
        return super.update(NAMESPACE.concat("approve_buser"), data);
    }

    @Override
    public void updateHigh(BUser data) {
        super.update(NAMESPACE.concat("update_high"), data);
    }

    @Override
    public List<BUser> selectAgentFront(BUser condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_agent_front"), start,
            limit, condition, BUser.class);
    }

    @Override
    public int updateLevel(BUser data) {
        return super.update(NAMESPACE.concat("update_level"), data);
    }

    @Override
    public int update(BUser data) {
        return super.update(NAMESPACE.concat("update_buser"), data);
    }

    @Override
    public void updateInformation(BUser data) {
        super.update(NAMESPACE.concat("update_information"), data);
    }

    @Override
    public void updateManager(BUser data) {
        super.update(NAMESPACE.concat("update_manager"), data);

    }

}
