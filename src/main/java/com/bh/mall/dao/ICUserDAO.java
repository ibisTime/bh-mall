package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.CUser;

public interface ICUserDAO extends IBaseDAO<CUser> {
    String NAMESPACE = ICUserDAO.class.getName().concat(".");

    // 更新状态
    public int updateStatus(CUser data);

    // 更新昵称
    public int updateNickname(CUser data);

    // 更新头像
    public int updatePhoto(CUser data);

    public int update(CUser data);

    public int updateWxInfo(CUser data);

}
