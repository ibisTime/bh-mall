/**
 * @Title UserDAOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:22:53 
 * @version V1.0   
 */
package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IUserDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.User;

/** 
 * @author: miyb 
 * @since: 2015-2-6 上午10:22:53 
 * @history:
 */
@Repository("userDAOImpl")
public class UserDAOImpl extends AMybatisTemplate implements IUserDAO {

    @Override
    public int insert(User data) {
        return super.insert(NAMESPACE.concat("insert_user"), data);
    }

    @Override
    public int delete(User data) {
        return 0;
    }

    @Override
    public User select(User condition) {
        return super.select(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    @Override
    public long selectTotalCount(User condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_user_count"),
            condition);
    }

    @Override
    public List<User> selectList(User condition) {
        return super.selectList(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    @Override
    public List<User> selectList(User condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_user"), start, count,
            condition, User.class);
    }

    @Override
    public int updateLoginPwd(User data) {
        return super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    @Override
    public int updateStatus(User data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateRole(User data) {
        return super.update(NAMESPACE.concat("update_role"), data);
    }

    /** 
     * @see com.bh.mall.dao.IUserDAO#updateLoginName(com.bh.mall.domain.User)
     */
    @Override
    public int updateLoginName(User data) {
        return super.update(NAMESPACE.concat("update_user_loginName"), data);
    }

    @Override
    public int updateNickname(User data) {
        return super.update(NAMESPACE.concat("update_user_nickname"), data);
    }

    @Override
    public int updatePhoto(User data) {
        return super.update(NAMESPACE.concat("update_user_photo"), data);
    }

    @Override
    public int update(User data) {
        return super.update(NAMESPACE.concat("update_user"), data);
    }

    @Override
    public int updateMobileIds(User data) {
        return super.update(NAMESPACE.concat("update_user_mobileIds"), data);
    }

    @Override
    public int updateLevel(User data) {
        return super.update(NAMESPACE.concat("update_level"), data);
    }

    @Override
    public int updateWxInfo(User data) {
        return super.update(NAMESPACE.concat("update_wx_info"), data);
    }

    @Override
    public int approveUser(User data) {
        return super.update(NAMESPACE.concat("approve_user"), data);
    }

    @Override
    public void setTradePwd(User user) {
        super.update(NAMESPACE.concat("update_trade_pwd"), user);
    }

    @Override
    public void resetBindMobile(User user) {
        super.update(NAMESPACE.concat("update_mobile"), user);
    }

    @Override
    public void allotAgency(User data) {
        super.update(NAMESPACE.concat("allot_gency"), data);
    }

    @Override
    public void ignore(User data) {
        super.update(NAMESPACE.concat("ignore_gency"), data);

    }

    @Override
    public void updateInformation(User data) {
        super.update(NAMESPACE.concat("update_information"), data);
    }

    @Override
    public void cancelImpower(User data) {
        super.update(NAMESPACE.concat("cancel_impower"), data);
    }

    @Override
    public void approveImpower(User data) {
        super.update(NAMESPACE.concat("approve_impower"), data);

    }

    @Override
    public void updateHighUser(User data) {
        super.update(NAMESPACE.concat("update_highUser"), data);
    }

    @Override
    public void updateUserReferee(User data) {
        super.update(NAMESPACE.concat("update_userReferee"), data);
    }

    @Override
    public void updateManager(User data) {
        super.update(NAMESPACE.concat("update_manager"), data);

    }

    @Override
    public void upgradeLevel(User data) {
        super.update(NAMESPACE.concat("upgrade_level"), data);
    }

    @Override
    public void approveUpgrade(User data) {
        super.update(NAMESPACE.concat("approve_upgrade"), data);
    }

    @Override
    public List<User> selectAgentFront(User condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_agent_front"), start,
            limit, condition, User.class);
    }

    @Override
    public void acceptIntention(User data) {
        super.update(NAMESPACE.concat("accept_intention"), data);
    }

    @Override
    public void applyIntent(User data) {
        super.update(NAMESPACE.concat("apply_intent"), data);

    }

    @Override
    public void toApply(User data) {
        super.update(NAMESPACE.concat("to_apply"), data);

    }
}
