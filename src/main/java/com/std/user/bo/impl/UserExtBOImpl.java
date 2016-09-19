package com.std.user.bo.impl;

import org.apache.commons.lang3.StringUtils;
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
    public void saveUserExt(UserExt data) {
        if (data != null) {
            userExtDAO.insert(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserExtBO#saveUserExt(java.lang.String)
     */
    @Override
    public void saveUserExt(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            UserExt data = new UserExt();
            data.setUserId(userId);
            if (data != null) {
                userExtDAO.insert(data);
            }
        }
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
    public int refreshUserExt(UserExt data) {
        int count = 0;
        if (data != null && data.getUserId() != null) {
            count = userExtDAO.updateUserExt(data);
        }
        return count;
    }

    /** 
     * @see com.std.user.bo.IUserExtBO#doGetUserExt(java.lang.String)
     */
    @Override
    public UserExt doGetUserExt(String userId) {
        UserExt result = null;
        if (StringUtils.isNotBlank(userId)) {
            UserExt condition = new UserExt();
            condition.setUserId(userId);
            result = userExtDAO.select(condition);
        }
        return result;
    }
}
