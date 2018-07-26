package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.enums.EUserStatus;

public interface ISYSUserBO extends IPaginableBO<SYSUser> {

    // 注册
    public String doRegister(String loginName, String loginPwd,
            String systemCode);

    // 保存
    public String saveUser(String mobile, String loginPwd, String photo,
            String loginName, String systemCode);

    public void doSaveUser(SYSUser data);

    // 登录判断
    public SYSUser getUserByLoginName(String loginName, String systemCode);

    public void checkLoginPwd(String userId, String loginPwd);

    public void isMobileExist(String mobile, String systemCode);

    // 判断登录名是否存在
    public void isLoginNameExist(String loginName, String systemCode);

    public String getUserId(String systemCode);

    public SYSUser getCheckUser(String userId);

    public SYSUser getUser(String userId);

    //
    public void refreshLoginName(String userId, String loginName);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId, String systemCode);

    public void resetAdminLoginPwd(SYSUser user, String loginPwd);

    public List<SYSUser> queryUserList(SYSUser condition);

    //
    public void refreshPhoto(String userId, String photo);

    //
    public void resetBindMobile(SYSUser user, String newMobile);

    public void refreshStatus(String userId, EUserStatus status, String updater,
            String remark);

}
