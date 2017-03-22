package com.std.user.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.IUserExtAO;
import com.std.user.bo.IAJourBO;
import com.std.user.bo.IRuleBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.IUserExtBO;
import com.std.user.domain.User;
import com.std.user.domain.UserExt;
import com.std.user.enums.EBizType;
import com.std.user.enums.ERuleKind;
import com.std.user.enums.ERuleType;

@Service
public class UserExtAOImpl implements IUserExtAO {

    @Autowired
    private IUserExtBO userExtBO;

    @Autowired
    protected IUserBO userBO;

    @Autowired
    protected IRuleBO ruleBO;

    @Autowired
    protected IAJourBO aJourBO;

    @Override
    public int editUserExtAddJf(UserExt data) {
        User user = userBO.getUser(data.getUserId());
        UserExt userExt = userExtBO.getUserExt(data.getUserId());
        if (StringUtils.isBlank(userExt.getGender())
                && StringUtils.isBlank(userExt.getBirthday())
                && StringUtils.isBlank(userExt.getEmail())) {
            // 完善信息送积分
            Long amount = ruleBO.getRuleByCondition(ERuleKind.JF,
                ERuleType.ZLWS, user.getLevel());
            if (amount != null && amount > 0) {
                userBO.refreshAmount(data.getUserId(), amount, null,
                    EBizType.AJ_SR, ERuleType.ZLWS.getValue());
            }
        }
        return userExtBO.refreshUserExt(data);
    }

    @Override
    public int editUserExt(UserExt data) {
        userBO.getUser(data.getUserId());
        return userExtBO.refreshUserExt(data);
    }

    @Override
    public int editUserExtPhoto(String userId, String photo) {
        userBO.getUser(userId);
        return userExtBO.refreshUserPhoto(userId, photo);
    }

    @Override
    public int editUserExtPhotoAddJf(String userId, String photo) {
        User user = userBO.getUser(userId);
        UserExt userExt = userExtBO.getUserExt(userId);
        if (StringUtils.isBlank(userExt.getPhoto())) {
            // 首次上传头像送积分
            Long amount = ruleBO.getRuleByCondition(ERuleKind.JF,
                ERuleType.SCTX, user.getLevel());
            if (amount != null && amount > 0) {
                userBO.refreshAmount(userId, amount, null, EBizType.AJ_SR,
                    ERuleType.SCTX.getValue());
            }
        }
        return userExtBO.refreshUserPhoto(userId, photo);
    }

    /** 
     * @see com.std.user.ao.IUserExtAO#editUserExtLngLat(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int editUserExtLngLat(String userId, String longitude,
            String latitude) {
        userBO.getUser(userId);
        return userExtBO.refreshUserExtLngLat(userId, longitude, latitude);
    }

    /** 
     * @see com.std.user.ao.IUserExtAO#queryUserExtList(com.std.user.domain.UserExt)
     */
    @Override
    public List<UserExt> queryUserExtList(UserExt condition) {
        return userExtBO.queryUserExtList(condition);
    }
}
