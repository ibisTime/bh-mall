package com.std.user.ao;

import com.std.user.domain.UserExt;

public interface IUserExtAO {

    /**
     * 更改用户信息
     * @param data
     * @return 
     * @create: 2016年10月11日 下午5:29:11 xieyj
     * @history:
     */
    public int refreshUserExt(UserExt data);

    /** 
     * 更改用户头像
     * @param data
     * @return 
     * @create: 2016年9月18日 下午2:37:41 zuixian
     * @history: 
     */
    public int refreshUserExtPhoto(String userId, String photo);
}
