package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.PwdUtil;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class SYSUserAOImpl implements ISYSUserAO {

    // 平台端登录接口api
    private static Logger logger = Logger.getLogger(SYSUserAOImpl.class);

    @Autowired
    ISYSUserBO sysUserBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IInOrderBO inOrderBO;

    @Autowired
    IInnerOrderBO innerOrderBO;

    @Autowired
    IYxFormBO yxFormBO;

    @Autowired
    ISjFormBO sjFormBO;

    @Autowired
    ISqFormBO sqFormBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    /*************** 注册 **********************/
    @Override
    public String addUser(String mobile, String loginPwd, String realName,
            String photo) {
        sysUserBO.isMobileExist(mobile, ESystemCode.BH.getCode());
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setMobile(mobile);
        data.setRealName(realName);
        data.setLoginName(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setPhoto(photo);
        data.setStatus(EUserStatus.NORMAL.getCode());
        data.setCreateDatetime(new Date());
        data.setSystemCode(ESystemCode.BH.getCode());
        sysUserBO.doSaveUser(data);
        return userId;
    }

    /*************** 登录 **********************/
    // 用户登录
    @Override
    public String doLogin(String loginName, String loginPwd) {
        SYSUser condition = new SYSUser();

        condition.setLoginName(loginName);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        SYSUser user = userList2.get(0);

        return user.getUserId();
    }

    /*************** 注销 / 激活 **********************/

    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        SYSUser user = sysUserBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(user.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = EUserStatus.NORMAL;
        }
        sysUserBO.refreshStatus(userId, userStatus, updater, remark);
        if (PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile,
                "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "805091");
        }

    }

    /*************** 设置角色**********************/
    // 设置角色
    @Override
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark) {
        SYSUser user = sysUserBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        sysUserBO.refreshRole(userId, roleCode, updater, remark);
    }

    /*************** 修改密码 **********************/
    // 重置登录密码
    @Override
    public void resetAdminLoginPwd(String userId, String newLoginPwd) {
        SYSUser user = sysUserBO.getCheckUser(userId);
        sysUserBO.resetAdminLoginPwd(user, newLoginPwd);
    }

    /*************** 验证密码 **********************/
    // 验证登录密码
    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        SYSUser condition = new SYSUser();
        condition.setUserId(userId);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    /*************** 修改手机号 **********************/
    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha) {
        SYSUser user = sysUserBO.getCheckUser(userId);
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        sysUserBO.isMobileExist(newMobile, user.getSystemCode());
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "627282",
            user.getSystemCode());
        sysUserBO.resetBindMobile(user, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已将手机号码改为" + newMobile + "，您的登录名更改为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "631072");

    }

    /*************** 修改登录名 **********************/

    /*************** 修改照片 **********************/
    @Override
    public void doModifyPhoto(String userId, String photo) {
        sysUserBO.refreshPhoto(userId, photo);
    }

    /*************** 查询 **********************/
    @Override
    public Paginable<SYSUser> queryUserPage(int start, int limit,
            SYSUser condition) {
        /*
         * if (condition.getCreateDatetimeStart() != null &&
         * condition.getApplyDatetimeEnd() != null &&
         * condition.getApplyDatetimeStart()
         * .after(condition.getApplyDatetimeEnd())) { throw new
         * BizException("xn00000", "开始时间不能大于结束时间"); }
         */
        Paginable<SYSUser> page = sysUserBO.getPaginable(start, limit,
            condition);

        return page;
    }

    @Override
    public void resetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd) {
    }

}
