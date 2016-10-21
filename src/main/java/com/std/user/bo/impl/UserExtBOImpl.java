package com.std.user.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IUserExtBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.dao.IUserExtDAO;
import com.std.user.domain.UserExt;
import com.std.user.exception.BizException;

@Component
public class UserExtBOImpl extends PaginableBOImpl<UserExt> implements
        IUserExtBO {

    @Autowired
    private IUserExtDAO userExtDAO;

    /** 
     * @see com.std.user.bo.IUserExtBO#saveUserExt(java.lang.String)
     */
    @Override
    public void saveUserExt(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            UserExt data = new UserExt();
            data.setUserId(userId);
            userExtDAO.insert(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserExtBO#saveUserExt(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void saveUserExt(String userId, String province, String city,
            String area) {
        if (StringUtils.isNotBlank(userId)) {
            UserExt data = new UserExt();
            data.setUserId(userId);
            data.setProvince(province);
            data.setCity(city);
            data.setArea(area);
            userExtDAO.insert(data);
        }
    }

    /**
     * 更新用户头像
     * @param userId
     * @param photo
     * @return 
     * @create: 2016年10月21日 下午7:47:19 xieyj
     * @history:
     */
    @Override
    public int refreshUserPhoto(String userId, String photo) {
        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(photo)) {
            UserExt data = new UserExt();
            data.setUserId(userId);
            data.setPhoto(photo);
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
    public UserExt getUserExt(String userId) {
        UserExt result = null;
        if (StringUtils.isNotBlank(userId)) {
            UserExt condition = new UserExt();
            condition.setUserId(userId);
            result = userExtDAO.select(condition);
            if (result == null) {
                throw new BizException("xn000000", "用户扩展信息不存在");
            }
        }
        return result;
    }
}
