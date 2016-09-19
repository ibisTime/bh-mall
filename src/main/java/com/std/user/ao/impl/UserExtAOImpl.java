package com.std.user.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.IUserExtAO;
import com.std.user.bo.IUserExtBO;
import com.std.user.domain.UserExt;

@Service
public class UserExtAOImpl implements IUserExtAO {

    @Autowired
    private IUserExtBO userExtBO;

    @Override
    public int refreshUserExt(String userId, String gender, String birthday,
            String region, String introduce) {
        UserExt data = new UserExt();
        data.setUserId(userId);
        data.setGender(gender);
        data.setBirthday(birthday);
        data.setRegion(region);
        data.setIntroduce(introduce);
        return userExtBO.refreshUserExt(data);
    }

    @Override
    public int refreshUserExtPhoto(String userId, String photo) {
        UserExt data = new UserExt();
        data.setUserId(userId);
        data.setPhoto(photo);
        return userExtBO.refreshUserPhoto(data);
    }

}
