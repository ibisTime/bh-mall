/**
refreshHighUser * @Title UserBOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:21:25 
 * @version V1.0   
 */
package com.bh.mall.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.PwdUtil;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IUserDAO;
import com.bh.mall.domain.User;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:21:25 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public User doGetUserByOpenId(String h5OpenId, String companyCode,
            String systemCode) {
        User condition = new User();
        condition.setH5OpenId(h5OpenId);
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<User> userList = userDAO.selectList(condition);
        User user = null;
        if (CollectionUtils.isNotEmpty(userList)) {
            user = userList.get(0);
        }
        return user;
    }

    @Override
    public void isMobileExist(String mobile, String companyCode,
            String systemCode) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    @Override
    public String getUserId(String mobile, String kind, String companyCode,
            String systemCode) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                User data = list.get(0);
                userId = data.getUserId();
            }
        }
        return userId;
    }

    @Override
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String kind, String loginPwd, String nickname,
            String photo, String status, Integer level, String systemCode,
            String companyCode, String userReferee) {
        String userId = OrderNoGenerater.generate("U");
        User user = new User();
        user.setUserId(userId);
        user.setUnionId(unionId);
        user.setH5OpenId(h5OpenId);

        user.setAppOpenId(appOpenId);
        user.setLoginName(mobile);
        user.setMobile(mobile);
        user.setKind(kind);
        user.setUserReferee(userReferee);

        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        user.setNickname(nickname);
        user.setPhoto(photo);
        user.setStatus(status);

        Date date = new Date();
        user.setCreateDatetime(date);
        user.setCompanyCode(companyCode);
        user.setLevel(level);
        user.setSystemCode(systemCode);

        userDAO.insert(user);
        return userId;
    }

    @Override
    public String doRegister(String wxId, String level, String realName,
            String province, String city, String area, String address,
            String unionId, String h5OpenId, String appOpenId, String mobile,
            String kind, String loginPwd, String nickname, String photo,
            String userReferee, String lastAgentLog, String companyCode,
            String systemCode) {
        String userId = OrderNoGenerater.generate("U");
        User user = new User();
        user.setUserId(userId);
        user.setLoginName(mobile);
        user.setMobile(mobile);
        user.setWxId(wxId);
        user.setPhoto(photo);
        user.setNickname(nickname);
        user.setApplyLevel(StringValidater.toInteger(level));
        user.setRealName(realName);

        user.setStatus(EUserStatus.MIND.getCode());
        user.setApplyDatetime(new Date());
        user.setUnionId(unionId);
        user.setH5OpenId(h5OpenId);
        user.setAppOpenId(appOpenId);

        user.setKind(kind);
        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));

        user.setNickname(nickname);
        user.setPhoto(photo);
        user.setUserReferee(userReferee);
        user.setProvince(province);
        user.setCity(city);
        user.setArea(area);
        user.setAddress(address);

        user.setCreateDatetime(new Date());
        user.setLastAgentLog(lastAgentLog);
        user.setCompanyCode(companyCode);
        user.setSystemCode(systemCode);
        userDAO.insert(user);
        return userId;
    }

    @Override
    public String saveUser(String mobile, String kind, String companyCode,
            String systemCode) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            userId = OrderNoGenerater.generate("U");
            User data = new User();
            data.setUserId(userId);
            data.setMobile(mobile);
            data.setKind(kind);
            data.setCompanyCode(companyCode);
            data.setSystemCode(systemCode);
            userDAO.insert(data);
        }
        return userId;
    }

    @Override
    public User getUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户不存在");
            }
        }
        return data;
    }

    @Override
    public User getUserNoCheck(String userId) {
        User condition = new User();
        condition.setUserId(userId);
        return userDAO.select(condition);
    }

    @Override
    public User getCheckUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
            if (null == data) {
                throw new BizException("xn702002", userId + "用户不存在");
            }
        }
        return data;
    }

    @Override
    public List<User> getUsersByUserReferee(String userReferee) {
        List<User> userList = new ArrayList<User>();
        if (StringUtils.isNotBlank(userReferee)) {
            User condition = new User();
            condition.setUserReferee(userReferee);
            userList = userDAO.selectList(condition);
        }
        return userList;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByLoginName(String loginName, String systemCode) {
        User data = null;
        if (StringUtils.isNotBlank(loginName)) {
            User condition = new User();
            condition.setLoginName(loginName);
            List<User> list = userDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "登录名重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#queryUserList(com.User.pz.domain.UserDO)
     */
    @Override
    public List<User> queryUserList(User data) {
        return userDAO.selectList(data);
    }

    @Override
    public void resetAdminLoginPwd(User user, String loginPwd) {
        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        userDAO.updateLoginPwd(user);
    }

    @Override
    public void isLoginNameExist(String loginName, String kind,
            String companyCode, String systemCode) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            User condition = new User();
            condition.setLoginName(loginName);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    @Override
    public boolean isUserExist(String userId, String systemCode) {
        User condition = new User();
        condition.setUserId(userId);
        condition.setSystemCode(systemCode);
        if (userDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void checkUserReferee(String userReferee, String systemCode) {
        if (StringUtils.isNotBlank(userReferee)) {
            // 判断格式
            User condition = new User();
            condition.setUserId(userReferee);
            long count = getTotalCount(condition);
            if (count <= 0) {
                throw new BizException("li01003", "推荐人不存在");
            }
        }
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(tradePwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setTradePwd(MD5Util.md5(tradePwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "支付密码错误");
            }
        } else {
            throw new BizException("jd00001", "支付密码错误");
        }
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
            User condition = new User();
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

    @Override
    public void checkLoginPwd(String userId, String loginPwd, String alertStr) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", alertStr + "错误");
            }
        } else {
            throw new BizException("jd00001", alertStr + "错误");
        }
    }

    /** 
     * @see com.bh.mall.bo.IUserBO#checkIdentify(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName) {
        User condition = new User();
        condition.setKind(kind);
        condition.setIdKind(idKind);
        condition.setIdNo(idNo);
        condition.setRealName(realName);
        List<User> userList = userDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            User data = userList.get(0);
            throw new BizException("xn000001",
                "用户[" + data.getMobile() + "]已使用该身份信息，请重新填写");
        }
    }

    @Override
    public void refreshStatus(String userId, EUserStatus status, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setStatus(status.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateStatus(data);
        }
    }

    @Override
    public void refreshLoginName(String userId, String loginName) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginName(loginName);
            userDAO.updateLoginName(data);
        }
    }

    @Override
    public void refreshNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setNickname(nickname);
            userDAO.updateNickname(data);
        }
    }

    @Override
    public void refreshPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setPhoto(photo);
            userDAO.updatePhoto(data);
        }
    }

    /** 
     * @see com.bh.mall.bo.IUserBO#refreshUser(com.bh.mall.domain.User)
     */
    @Override
    public void refreshUser(User data) {
        if (data != null) {
            userDAO.update(data);
        }
    }

    @Override
    public void refreshLevel(User data) {
        userDAO.updateLevel(data);
    }

    @Override
    public void refreshWxInfo(String userId, String unionId, String h5OpenId,
            String appOpenId, String nickname, String photo) {
        User dbUser = getUser(userId);
        dbUser.setUnionId(unionId);
        if (StringUtils.isNotBlank(h5OpenId)) {
            dbUser.setH5OpenId(h5OpenId);
        }
        if (StringUtils.isNotBlank(appOpenId)) {
            dbUser.setAppOpenId(appOpenId);
        }
        dbUser.setNickname(nickname);
        dbUser.setPhoto(photo);
        userDAO.updateWxInfo(dbUser);
    }

    @Override
    public List<User> queryUserList(String mobile, String kind,
            String systemCode) {
        User condition = new User();
        condition.setMobile(mobile);
        condition.setKind(kind);
        condition.setSystemCode(systemCode);
        return userDAO.selectList(condition);
    }

    /** 
     * @see com.bh.mall.bo.IUserBO#doCheckOpenId(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckOpenId(String unionId, String h5OpenId, String appOpenId,
            String companyCode, String systemCode) {
        User condition = new User();
        condition.setUnionId(unionId);
        condition.setH5OpenId(h5OpenId);
        condition.setAppOpenId(appOpenId);
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        long count = getTotalCount(condition);
        if (count > 0) {
            throw new BizException("xn702002", "微信编号已存在");
        }
    }

    @Override
    public void refreshRole(String userId, String roleCode, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setRoleCode(roleCode);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateRole(data);
        }
    }

    @Override
    public void setTradePwd(User user, String tradePwd) {
        user.setTradePwd(MD5Util.md5(tradePwd));
        user.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePwd));
        userDAO.setTradePwd(user);
    }

    @Override
    public void resetBindMobile(User user, String newMobile) {
        user.setMobile(newMobile);
        userDAO.resetBindMobile(user);
    }

    @Override
    public String doRegister(String realName, String level, String wxId,
            String idBehind, String idFront, String introducer, String fromInfo,
            String userReferee, String mobile, String province, String city,
            String area, String address, String loginPwd, String photo,
            String nickname, String unionId, String h5OpenId,
            String companyCode, String systemCode, String logCode) {
        String userId = OrderNoGenerater.generate("U");
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);

        data.setIdBehind(idBehind);
        data.setIdFront(idFront);

        data.setLoginName(mobile);
        data.setMobile(mobile);
        data.setWxId(wxId);
        data.setPhoto(photo);
        data.setNickname(nickname);

        data.setStatus(EUserStatus.TO_APPROVE.getCode());
        data.setApplyDatetime(new Date());
        data.setUnionId(unionId);
        data.setH5OpenId(h5OpenId);

        data.setApplyLevel(StringValidater.toInteger(level));
        data.setKind(EUserKind.Merchant.getCode());
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));

        data.setUserReferee(userReferee);
        data.setProvince(province);
        data.setCity(city);
        data.setArea(area);
        data.setAddress(address);

        data.setCreateDatetime(new Date());
        data.setCompanyCode(companyCode);
        data.setSystemCode(systemCode);
        data.setLastAgentLog(logCode);
        userDAO.insert(data);

        return userId;

    }

    @Override
    public void allotAgency(User data) {
        userDAO.allotAgency(data);
    }

    @Override
    public void ignore(User data) {
        data.setStatus(EUserStatus.IGNORED.getCode());
        userDAO.ignore(data);
    }

    @Override
    public void updateInformation(User data) {
        userDAO.updateInformation(data);
    }

    @Override
    public void cancelImpower(User data) {
        userDAO.cancelImpower(data);
    }

    @Override
    public void approveImpower(User data) {
        userDAO.approveImpower(data);
    }

    @Override
    public void approveCanenl(User data) {
        userDAO.approveImpower(data);
    }

    @Override
    public void refreshHighUser(User data) {
        userDAO.updateHighUser(data);
    }

    @Override
    public void refreshUserReferee(User data, String userReferee,
            String updater) {
        data.setHighUserId(userReferee);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        userDAO.updateUserReferee(data);

    }

    @Override
    public void refreshManager(User data, String manager, String updater) {
        data.setHighUserId(manager);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        userDAO.updateManager(data);
    }

    @Override
    public void upgradeLevel(User data) {
        userDAO.upgradeLevel(data);
    }

    @Override
    public void approveUpgrade(User data) {
        userDAO.approveUpgrade(data);
    }

    @Override
    public List<User> selectList(User condition, int pageNo, int pageSize) {
        return userDAO.selectList(condition, pageNo, pageSize);
    }

    @Override
    public List<User> selectAgentFront(User condition, int start, int limit) {
        return userDAO.selectAgentFront(condition, start, limit);
    }

    @Override
    public void acceptIntention(User data) {
        userDAO.acceptIntention(data);
    }

    @Override
    public void applyIntent(User data) {
        userDAO.applyIntent(data);
    }

    @Override
    public User getUserName(String userId) {
        User condition = new User();
        condition.setUserId(userId);
        return userDAO.select(condition);
    }

    @Override
    public User getSysUser() {
        User condition = new User();
        condition.setKind(EUserKind.Plat.getCode());
        List<User> list = userDAO.selectList(condition);
        return list.get(0);
    }

    @Override
    public void doSaveUser(User data) {
        userDAO.insert(data);
    }

    @Override
    public void toApply(User data) {
        userDAO.toApply(data);
    }

    @Override
    public void addInfo(User data) {
        userDAO.addInfo(data);
    }

    @Override
    public User getUserByMobile(String introducer) {
        User data = null;
        if (StringUtils.isNotBlank(introducer)) {
            User condition = new User();
            condition.setMobile(introducer);
            data = userDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000000", "介绍人不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshHigh(User data) {
        userDAO.updateHigh(data);
    }

    @Override
    public User getUserByIdNo(String idNo) {
        User data = null;
        if (StringUtils.isNotBlank(idNo)) {
            User condition = new User();
            condition.setIdNo(idNo);
            data = userDAO.select(condition);
            if (data != null) {
                throw new BizException("xn000000", "身份证号已存在");
            }
        }
        return data;
    }

}
