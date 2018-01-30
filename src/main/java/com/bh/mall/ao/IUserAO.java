package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627201Req;
import com.bh.mall.dto.req.XN805043Req;
import com.bh.mall.dto.req.XN805095Req;
import com.bh.mall.dto.req.XN805170Req;
import com.bh.mall.dto.res.XN805041Res;
import com.bh.mall.dto.res.XN805170Res;

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

    // 注册前端用户
    public XN805041Res doRegister(String mobile, String loginPwd,
            String userReferee, String userRefereeKind, String smsCaptcha,
            String kind, String isRegHx, String province, String city,
            String area, String companyCode, String systemCode);

    // 代注册
    public String doAddUser(XN627201Req req);

    // 申请注册
    public String doApplyRegUser(XN805043Req req);

    // 微信注册/登录
    public XN805170Res doLoginWeChat(XN805170Req req);

    // 验证码登录注册
    public String doCaptchaLoginReg(String mobile, String kind,
            String smsCaptcha, String companyCode, String systemCode);

    // 检查登录密码是否正确
    public void doCheckLoginPwd(String userId, String password);

    // 修改头像
    public void doModifyPhoto(String userId, String photo);

    // 修改用户等级
    public void doUpLevel(String userId, String level);

    // 修改用户信息
    public void doModifyUser(XN805095Req req);

    public Paginable<User> queryUserPage(int start, int limit, User condition);

    public List<User> queryUserList(User condition);

    public User doGetUser(String userId);

    public List<User> getUserRefereeList(String userId);
}
