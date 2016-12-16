package com.std.user.ao;

import com.std.user.domain.UserExt;

public interface IUserExtAO {

    /**
     * 更改用户信息
     * @param data
     * @return 
     * @create: 2016年10月21日 下午6:53:13 xieyj
     * @history:
     */
    public int editUserExtAddJf(UserExt data);

    /**
     * 更改用户信息
     * @param data
     * @return 
     * @create: 2016年10月21日 下午6:53:13 xieyj
     * @history:
     */
    public int editUserExt(UserExt data);

    /**
     * 更改用户头像
     * @param userId
     * @param photo
     * @return 
     * @create: 2016年10月21日 下午6:53:07 xieyj
     * @history:
     */
    public int editUserExtPhotoAddJf(String userId, String photo);

    /**
     * 更改用户头像
     * @param userId
     * @param photo
     * @return 
     * @create: 2016年10月21日 下午6:53:07 xieyj
     * @history:
     */
    public int editUserExtPhoto(String userId, String photo);
}
