package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.ISYSUserDAO;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.exception.BizException;

@Component
public class SYSUserBOImpl extends PaginableBOImpl<SYSUser>
        implements ISYSUserBO {

    @Autowired
    private ISYSUserDAO sysUserDAO;

    @Override
    public List<SYSUser> queryUserList(SYSUser data) {
        return sysUserDAO.selectList(data);
    }

    // 注册
    @Override
    public String doRegister(String loginName, String loginPwd,
            String systemCode, String companyCode) {
        String userId = OrderNoGenerater.generate("U");
        SYSUser sysUser = new SYSUser();
        sysUser.setUserId(userId);

        sysUser.setLoginName(loginName);

        sysUser.setCompanyCode(companyCode);

        sysUser.setSystemCode(systemCode);

        sysUserDAO.insert(sysUser);
        return userId;
    }

    @Override
    public void doSaveUser(SYSUser data) {
        sysUserDAO.insert(data);
    }

    // 保存用户
    @Override
    public String saveUser(String companyCode, String systemCode) {
        String userId = null;
        userId = OrderNoGenerater.generate("U");
        SYSUser data = new SYSUser();
        data.setUserId(userId); // kind
        data.setCompanyCode(companyCode);
        data.setSystemCode(systemCode);
        sysUserDAO.insert(data);
        return userId;
    }

    @Override
    public void isMobileExist(String mobile, String companyCode,
            String systemCode) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            SYSUser condition = new SYSUser();
            condition.setMobile(mobile);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    // 登录
    @Override
    public SYSUser getUserByLoginName(String loginName, String systemCode) {
        SYSUser data = null;
        if (StringUtils.isNotBlank(loginName)) {
            SYSUser condition = new SYSUser();
            condition.setLoginName(loginName);
            List<SYSUser> list = sysUserDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "登录名重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    // 登录判断
    @Override
    public SYSUser getCheckUser(String userId) {
        SYSUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            SYSUser condition = new SYSUser();
            condition.setUserId(userId);
            data = sysUserDAO.select(condition);
            if (null == data) {
                throw new BizException("xn702002", userId + "用户不存在");
            }
        }
        return data;
    }

    @Override
    public SYSUser getUser(String userId) {
        SYSUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            SYSUser condition = new SYSUser();
            condition.setUserId(userId);
            data = sysUserDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户不存在");
            }
        }
        return data;
    }

    @Override
    public boolean isUserExist(String userId, String systemCode) {
        SYSUser condition = new SYSUser();
        condition.setUserId(userId);
        condition.setSystemCode(systemCode);
        if (sysUserDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    // 密码检查
    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
            SYSUser condition = new SYSUser();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "原登录密码错误");
            }
        } else {
            throw new BizException("jd00001", "原登录密码错误");
        }
    }

    // 重置密码
    @Override
    public void resetAdminLoginPwd(SYSUser sysUser, String loginPwd) {
        sysUser.setLoginPwd(MD5Util.md5(loginPwd));
        sysUserDAO.updateLoginPwd(sysUser);
    }

    // 重置登录名
    @Override
    public void refreshLoginName(String userId, String loginName) {
        if (StringUtils.isNotBlank(userId)) {
            SYSUser data = new SYSUser();
            data.setUserId(userId);
            data.setLoginName(loginName);
            sysUserDAO.updateLoginName(data);
        }
    }

    @Override
    public void isLoginNameExist(String loginName, String kind,
            String companyCode, String systemCode) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            SYSUser condition = new SYSUser();
            condition.setLoginName(loginName);

            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    @Override
    public String getUserId(String companyCode, String systemCode) {
        String userId = null;
        SYSUser condition = new SYSUser();
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<SYSUser> list = sysUserDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            SYSUser data = list.get(0);
            userId = data.getUserId();
        }
        return userId;
    }

    //
    @Override
    public void resetBindMobile(SYSUser user, String newMobile) {
        user.setMobile(newMobile);
        sysUserDAO.resetBindMobile(user);
    }

    @Override
    public void refreshPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId)) {
            SYSUser data = new SYSUser();
            data.setUserId(userId);
            data.setPhoto(photo);
            sysUserDAO.updatePhoto(data);
        }
    }

}
