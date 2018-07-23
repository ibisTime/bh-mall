package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.CUser;

public interface ICuserDAO extends IBaseDAO<CUser> {
    String NAMESPACE = ICuserDAO.class.getName().concat(".");

    // 设置登录密码
    public int updateLoginPwd(CUser data);

    // 更新状态
    public int updateStatus(CUser data);

    // 更新用户名
    public int updateLoginName(CUser data);

    // 更新昵称
    public int updateNickname(CUser data);

    // 更新头像
    public int updatePhoto(CUser data);

    public int update(CUser data);

    // 更新用户手机号和真实信息
    public int updateMobileIds(CUser data);

    public void setTradePwd(CUser user);

    public int updateWxInfo(CUser data);

    public void resetBindMobile(CUser user);

}
