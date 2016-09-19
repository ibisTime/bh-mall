package com.std.user.ao;

public interface IUserExtAO {

    /**
     * 更改用户信息
     * @param userId
     * @param gender
     * @param birthday
     * @param region
     * @param introduce
     * @return 
     * @create: 2016年9月19日 下午2:26:45 xieyj
     * @history:
     */
    public int refreshUserExt(String userId, String gender, String birthday,
            String region, String introduce);

    /** 
     * 更改用户头像
     * @param data
     * @return 
     * @create: 2016年9月18日 下午2:37:41 zuixian
     * @history: 
     */
    public int refreshUserExtPhoto(String userId, String photo);
}
