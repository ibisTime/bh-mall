package com.std.user.bo;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.UserExt;

public interface IUserExtBO extends IPaginableBO<UserExt> {

    /**
     * 新增用户扩展
     * @param userId
     * @return 
     * @create: 2016年9月19日 上午11:11:14 xieyj
     * @history:
     */
    public void saveUserExt(String userId);

    /**
     * 新增用户扩展,包含定位地址
     * @param userId
     * @param province
     * @param city
     * @param area 
     * @create: 2016年10月12日 上午12:20:42 xieyj
     * @history:
     */
    public void saveUserExt(String userId, String province, String city,
            String area);

    /**
     * 新增用户扩展
     * @param userId
     * @param photo
     * @param gender 
     * @create: 2016年11月17日 下午12:43:27 xieyj
     * @history:
     */
    public void saveUserExt(String userId, String photo, String gender);

    /**
     * 更新用户头像
     * @param userId
     * @param photo
     * @return 
     * @create: 2016年10月21日 下午7:47:19 xieyj
     * @history:
     */
    public int refreshUserPhoto(String userId, String photo);

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
    public UserExt getUserExt(String userId);
}
