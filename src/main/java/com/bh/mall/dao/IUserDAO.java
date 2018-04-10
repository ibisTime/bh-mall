/**
 * @Title IUserDAO.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:22:02 
 * @version V1.0   
 */
package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.User;

/** 
 * @author: miyb 
 * @since: 2015-2-6 上午10:22:02 
 * @history:
 */
public interface IUserDAO extends IBaseDAO<User> {
    String NAMESPACE = IUserDAO.class.getName().concat(".");

    // 设置登录密码
    public int updateLoginPwd(User data);

    // 更新状态
    public int updateStatus(User data);

    // 更新角色
    public int updateRole(User data);

    // 更新用户名
    public int updateLoginName(User data);

    // 更新昵称
    public int updateNickname(User data);

    // 更新头像
    public int updatePhoto(User data);

    public int update(User data);

    public int updateLevel(User data);

    // 更新用户手机号和真实信息
    public int updateMobileIds(User data);

    // 微信登录更新用户信息
    public int updateWxInfo(User data);

    public int approveUser(User data);

    public void setTradePwd(User user);

    public void resetBindMobile(User user);

    public void allotAgency(User data);

    public void ignore(User data);

    public void updateInformation(User data);

    public void cancelImpower(User data);

    public void approveImpower(User data);

    public void updateHighUser(User data);

    public void updateUserReferee(User data);

    public void updateManager(User data);

    public void upgradeLevel(User data);

    public void approveUpgrade(User data);

    public List<User> selectAgentFront(User condition, int start, int limit);

    public void acceptIntention(User data);

    public void applyIntent(User data);
}
