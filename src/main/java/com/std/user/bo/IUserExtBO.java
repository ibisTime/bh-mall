package com.std.user.bo;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.UserExt;

public interface IUserExtBO extends IPaginableBO<UserExt> {

    /** 
     * 新增用户扩展
     * @param data
     * @return 
     * @create: 2016年9月18日 下午2:23:31 zuixian
     * @history: 
     */
    public String saveUserExt(UserExt data);

    /** 
     * 更新用户头像
     * @param data
     * @return 
     * @create: 2016年9月18日 下午2:25:47 zuixian
     * @history: 
     */
    public int refreshUserPhoto(UserExt data);

    /** 
     * 更新用户信息
     * @param data
     * @return 
     * @create: 2016年9月18日 下午2:26:06 zuixian
     * @history: 
     */
    public int refreshUser(UserExt data);
}
