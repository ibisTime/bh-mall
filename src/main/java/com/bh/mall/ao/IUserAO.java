package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627301Req;
import com.bh.mall.dto.req.XN627302Req;
import com.bh.mall.dto.res.XN627302Res;

/**
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:46:32 
 * @history:
 */
public interface IUserAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    // 用户登录
    public String doLogin(String loginName, String loginPwd, String kind,
            String companyCode, String systemCode);

    // 管理端代注册
    public String doAddUser(XN627301Req req);

    // C端微信注册/登录
    public XN627302Res doLoginWeChat(XN627302Req req);

    // 注销/激活用户
    public void doCloseOpen(String userId, String updater, String remark);

    // 设置角色
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark);

    // 重置登录密码
    public void resetAdminLoginPwd(String userId, String newLoginPwd);

    // 设置交易密码
    public void setTradePwd(String userId, String smsCaptcha, String tradePwd);

    // 修改头像
    public void doModifyPhoto(String userId, String photo);

    // 检查登录密码是否正确
    public void doCheckLoginPwd(String userId, String password);

    // 更换绑定手机号
    public void resetBindMobile(String userId, String kind,
            String oldSmsCaptcha, String newMobile, String newSmsCaptcha);

    // 修改用户等级
    public void doUpLevel(String userId, String level);

    public Paginable<User> queryUserPage(int start, int limit, User condition);

    public List<User> queryUserList(User condition);

    public User doGetUser(String userId);

    public List<User> getUserRefereeList(String userId);

    // 代理申请
    public XN627302Res applyIntent(XN627250Req req);
}
