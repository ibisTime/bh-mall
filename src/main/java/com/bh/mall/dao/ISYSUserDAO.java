package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.SYSUser;

public interface ISYSUserDAO extends IBaseDAO<SYSUser> {

    String NAMESPACE = ISYSUserDAO.class.getName().concat(".");

    // 设置登录密码
    public int updateLoginPwd(SYSUser data);

    // 更新状态

    // 更新角色

    // 更新用户名
    public int updateLoginName(SYSUser data);

    // 更新昵称
    public int updateNickname(SYSUser data);

    // 更新用户手机号和真实信息

    public int update(SYSUser data);

    public void resetBindMobile(SYSUser user);

    public void allotAgency(BUser data);

    public void ignore(BUser data);

    public void cancelImpower(BUser data);

    public void approveImpower(BUser data);

    public void approveUpgrade(BUser data);

    // 更新头像
    public int updatePhoto(SYSUser data);

}
