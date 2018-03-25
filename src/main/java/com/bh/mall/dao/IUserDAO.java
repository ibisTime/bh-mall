/**
 * @Title IUserDAO.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:22:02 
 * @version V1.0   
 */
package com.bh.mall.dao;

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
}
