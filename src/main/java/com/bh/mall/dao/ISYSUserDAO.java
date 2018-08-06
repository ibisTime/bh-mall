package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SYSUser;

public interface ISYSUserDAO extends IBaseDAO<SYSUser> {

    String NAMESPACE = ISYSUserDAO.class.getName().concat(".");

    // 设置登录密码
    public int updateLoginPwd(SYSUser data);

    // 更新状态
    public void updateStatus(SYSUser data);

    // 更新角色
    public int updateRole(SYSUser data);

    // 更新用户名
    public int updateLoginName(SYSUser data);

    // 更新昵称
    public int updateNickname(SYSUser data);

    // 更新用户手机号和真实信息

    public int update(SYSUser data);

    public void resetBindMobile(SYSUser user);

    // 更新头像
    public int updatePhoto(SYSUser data);

}
