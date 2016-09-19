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
    public void saveUserExt(UserExt data);

    /**
     * 新增用户扩展
     * @param userId
     * @return 
     * @create: 2016年9月19日 上午11:11:14 xieyj
     * @history:
     */
    public void saveUserExt(String userId);

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
    public int refreshUserExt(UserExt data);

    /**
     * 获取扩展信息
     * @param userId
     * @return 
     * @create: 2016年9月19日 上午10:47:01 xieyj
     * @history:
     */
    public UserExt doGetUserExt(String userId);
}
