package com.std.user.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IUserExtBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.dao.IUserExtDAO;
import com.std.user.domain.UserExt;

@Component
public class UserExtBOImpl extends PaginableBOImpl<UserExt> implements
        IUserExtBO {

    @Autowired
    private IUserExtDAO userExtDAO;

    @Override
    public String saveUserExt(UserExt data) {
        if (data != null) {
            userExtDAO.insert(data);
        }
        return data.getUserId();
    }

    @Override
    public int refreshUserPhoto(UserExt data) {
        int count = 0;
        if (data != null && data.getUserId() != null) {
            count = userExtDAO.updateUserPhoto(data);
        }
        return count;
    }

    @Override
    public int refreshUser(UserExt data) {
        int count = 0;
        if (data != null && data.getUserId() != null) {
            count = userExtDAO.updateUser(data);
        }
        return count;
    }

}
