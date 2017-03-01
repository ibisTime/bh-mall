/**
 * @Title UserBOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:21:25 
 * @version V1.0   
 */
package com.std.user.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IUserBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.common.DateUtil;
import com.std.user.common.MD5Util;
import com.std.user.common.PhoneUtil;
import com.std.user.common.PwdUtil;
import com.std.user.core.OrderNoGenerater;
import com.std.user.dao.IAJourDAO;
import com.std.user.dao.ILevelRuleDAO;
import com.std.user.dao.IUserDAO;
import com.std.user.domain.AccountJour;
import com.std.user.domain.LevelRule;
import com.std.user.domain.User;
import com.std.user.enums.EAccountJourStatus;
import com.std.user.enums.EBizType;
import com.std.user.enums.EUserLevel;
import com.std.user.enums.EUserStatus;
import com.std.user.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:21:25 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {
    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IAJourDAO aJourDAO;

    @Autowired
    private ILevelRuleDAO levelRuleDAO;

    @Override
    public void isMobileExist(String mobile, String systemCode) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind("ff3");
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    @Override
    public void isMobileExist(String mobile, String kind, String systemCode) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#isMobileExist(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void isMobileExist(String mobile, String kind, String companyCode,
            String systemCode) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    /**
     * @see com.ibis.pz.user.IUserBO#refreshIdentity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateIdentity(data);
        }
        return count;
    }

    @Override
    public int refreshRealName(String userId, String realName) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateRealName(data);
        }
        return count;
    }

    @Override
    public int refreshTradePwd(String userId, String tradePwd,
            String tradePwdStrength) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setTradePwd(MD5Util.md5(tradePwd));
            data.setTradePwdStrength(tradePwdStrength);
            count = userDAO.updateTradePwd(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUser(java.lang.String)
     */
    @Override
    public User getUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
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

    @Override
    public User getUser(String userId, String systemCode) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setSystemCode(systemCode);
            data = userDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000000", "该用户编号[" + userId + "]不存在!");
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByMobile(String mobile, String systemCode) {
        User data = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByMobileAndKind(String mobile, String kind,
            String systemCode) {
        User data = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setSystemCode(systemCode);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByMobileAndKind(String mobile, String kind,
            String companyCode, String systemCode) {
        User data = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
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

    /**
     * 
     * @see com.ibis.pz.user.IUserBO#refreshLoginPwd(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshLoginPwd(String userId, String loginPwd,
            String loginPwdStrength) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginPwd(loginPwd);
            data.setLoginPwdStrength(loginPwdStrength);
            count = userDAO.updateLoginPwd(data);
        }
        return count;
    }

    @Override
    public int refreshMobile(String userId, String mobile) {
        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(mobile)) {
            User data = new User();
            data.setUserId(userId);
            data.setMobile(mobile);
            count = userDAO.updateMobile(data);
        }
        return count;
    }

    @Override
    public int refreshBindMobile(String userId, String loginName,
            String mobile, String loginPwd, String loginPwdStrength) {
        int count = 0;
        User data = new User();
        data.setUserId(userId);
        data.setLoginName(loginName);
        data.setMobile(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(loginPwdStrength);
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateBindMobile(data);
        }
        return count;
    }

    /** 
     * @see com.std.user.bo.IUserBO#isLoginNameExist(java.lang.String)
     */
    @Override
    public void isLoginNameExist(String loginName, String kind,
            String systemCode) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            User condition = new User();
            condition.setLoginName(loginName);
            condition.setKind(kind);
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

    /** 
     * @see com.std.user.bo.IUserBO#checkUserReferee(java.lang.String)
     */
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
    public String doRegister(String loginName, String nickname, String mobile,
            String loginPwd, String loginPwdStrength, String userReferee,
            String kind, String level, Long amount, String companyCode,
            String openId, String jpushId, String systemCode) {
        String userId = null;
        if (StringUtils.isNotBlank(loginPwdStrength)) {
            User user = new User();
            userId = OrderNoGenerater.generate("U");
            user.setUserId(userId);
            user.setLoginName(loginName);
            user.setLoginPwd(MD5Util.md5(loginPwd));

            user.setLoginPwdStrength(loginPwdStrength);
            if (StringUtils.isBlank(nickname)) {
                user.setNickname(userId.substring(userId.length() - 8,
                    userId.length()));
            } else {
                user.setNickname(nickname);
            }
            user.setKind(kind);
            user.setLevel(EUserLevel.ONE.getCode());
            if (StringUtils.isNotBlank(level)) {
                user.setLevel(level);
            }
            user.setUserReferee(userReferee);
            user.setMobile(mobile);
            // String tradePsd = EUserPwd.InitPwd.getCode();
            // user.setTradePwd(MD5Util.md5(tradePsd));
            // user.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePsd));
            user.setStatus(EUserStatus.NORMAL.getCode());// 0正常;1程序锁定;2人工锁定
            user.setUpdater(userId);
            user.setUpdateDatetime(new Date());
            if (amount == null) {
                amount = 0L;
            }
            user.setAmount(amount);
            user.setLjAmount(amount);
            user.setCompanyCode(companyCode);
            user.setOpenId(openId);
            user.setJpushId(jpushId);
            user.setSystemCode(systemCode);
            userDAO.insert(user);
        }
        return userId;
    }

    @Override
    public String doRegister(String userId, String loginName, String nickname,
            String mobile, String loginPwd, String loginPwdStrength,
            String userReferee, String kind, String level, Long amount,
            String companyCode, String openId, String jpushId, String systemCode) {
        if (StringUtils.isNotBlank(loginPwdStrength)) {
            User user = new User();
            user.setUserId(userId);
            user.setLoginName(loginName);
            user.setLoginPwd(MD5Util.md5(loginPwd));

            user.setLoginPwdStrength(loginPwdStrength);
            if (StringUtils.isBlank(nickname)) {
                user.setNickname(userId.substring(userId.length() - 8,
                    userId.length()));
            } else {
                user.setNickname(nickname);
            }
            user.setKind(kind);
            user.setLevel(EUserLevel.ONE.getCode());
            if (StringUtils.isNotBlank(level)) {
                user.setLevel(level);
            }
            user.setUserReferee(userReferee);
            user.setMobile(mobile);
            // String tradePsd = EUserPwd.InitPwd.getCode();
            // user.setTradePwd(MD5Util.md5(tradePsd));
            // user.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePsd));
            user.setStatus(EUserStatus.NORMAL.getCode());// 0正常;1程序锁定;2人工锁定
            user.setUpdater(userId);
            user.setUpdateDatetime(new Date());
            if (amount == null) {
                amount = 0L;
            }
            user.setAmount(amount);
            user.setLjAmount(amount);
            user.setCompanyCode(companyCode);
            user.setOpenId(openId);
            user.setJpushId(jpushId);
            user.setSystemCode(systemCode);
            userDAO.insert(user);
        }
        return userId;
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(tradePwd)) {
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
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
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
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
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
     * @see com.std.user.bo.IUserBO#checkIdentify(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void checkIdentify(String idKind, String idNo, String realName) {
        User condition = new User();
        condition.setIdKind(idKind);
        condition.setIdNo(idNo);
        condition.setRealName(realName);
        List<User> userList = userDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            User user = userList.get(0);
            throw new BizException("xn000001", "用户[" + user.getMobile()
                    + "]已使用该身份信息，请重新填写");
        }
    }

    @Override
    public String doAddUser(String loginName, String mobile, String loginPsd,
            String userReferee, String realName, String idKind, String idNo,
            String tradePsd, String kind, String level, String remark,
            String updater, String pdf, String roleCode, String systemCode) {
        String userId = null;
        if (StringUtils.isNotBlank(loginName) || StringUtils.isNotBlank(mobile)) {
            User user = new User();
            userId = OrderNoGenerater.generate("U");

            user.setUserId(userId);
            user.setLoginName(loginName);
            user.setNickname(userId.substring(userId.length() - 8,
                userId.length()));
            user.setLoginPwd(MD5Util.md5(loginPsd));
            if (StringUtils.isNotBlank(loginPsd)) {
                user.setLoginPwdStrength(PwdUtil
                    .calculateSecurityLevel(loginPsd));
            }
            user.setKind(kind);

            user.setLevel(level);
            user.setUserReferee(userReferee);
            user.setMobile(mobile);
            user.setIdKind(idKind);
            user.setIdNo(idNo);

            user.setRealName(realName);
            user.setTradePwd(MD5Util.md5(tradePsd));
            if (StringUtils.isNotBlank(tradePsd)) {
                user.setTradePwdStrength(PwdUtil
                    .calculateSecurityLevel(tradePsd));
            }
            user.setStatus(EUserStatus.NORMAL.getCode());// 0正常;1程序锁定;2人工锁定
            user.setUpdater(updater);

            user.setUpdateDatetime(new Date());
            user.setRemark(remark);
            user.setPdf(pdf);
            user.setRoleCode(roleCode);
            user.setSystemCode(systemCode);
            userDAO.insertRen(user);
        }
        return userId;
    }

    /** 
     * @see com.std.user.bo.IUserBO#doAddUser(com.std.user.domain.User)
     */
    @Override
    public String doAddUser(User data) {
        String userId = null;
        if (null != data) {
            userId = OrderNoGenerater.generate("U");
            data.setUserId(userId);
            data.setLoginPwd(MD5Util.md5(data.getLoginPwd()));
            data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(data
                .getLoginPwd()));
            data.setLevel(EUserLevel.ZERO.getCode());
            data.setStatus(EUserStatus.NORMAL.getCode());
            userDAO.insertRen(data);
        }
        return userId;
    }

    @Override
    public void refreshStatus(String userId, EUserStatus status,
            String updater, String remark) {
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

    /** 
     * @see com.std.user.bo.IUserBO#refreshStatus(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void refreshPdf(String userId, String pdf, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setPdf(pdf);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updatePdf(data);
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

    /** 
     * @see com.std.user.bo.IUserBO#refreshAmount(java.lang.String, java.lang.Long, java.lang.String, com.std.user.enums.EBizType, java.lang.String)
     */
    @Override
    public void refreshAmount(String userId, Long transAmount, String refNo,
            EBizType bizType, String remark) {
        if (transAmount != null && transAmount != 0L) {
            User dbUser = this.getUser(userId);
            Long nowAmount = dbUser.getAmount() + transAmount;
            if (nowAmount < 0) {
                throw new BizException("li779001", "亲，余额不足了哦");
            }
            User user = new User();
            user.setUserId(userId);
            user.setAmount(nowAmount);
            // 设置原来的用户等级
            user.setLevel(dbUser.getLevel());
            Long ljAmount = dbUser.getLjAmount();
            if (transAmount > 0) {
                ljAmount = ljAmount + transAmount;
                user.setLjAmount(ljAmount);
                // 重新设置等级
                LevelRule lrCondition = new LevelRule();
                List<LevelRule> lrList = levelRuleDAO.selectList(lrCondition);
                for (LevelRule levelRule : lrList) {
                    if (ljAmount >= levelRule.getAmountMin()
                            && ljAmount < levelRule.getAmountMax()) {
                        user.setLevel(levelRule.getCode());
                        break;
                    }
                }
            } else {
                user.setLjAmount(ljAmount);
            }
            userDAO.updateAmount(user);
            // 记录流水
            AccountJour accountJour = new AccountJour();
            accountJour.setBizType(bizType.getCode());
            accountJour.setRefNo(refNo);
            accountJour.setTransAmount(transAmount);
            accountJour.setPreAmount(dbUser.getAmount());
            accountJour.setPostAmount(nowAmount);
            accountJour.setRemark(remark);
            accountJour.setCreateDatetime(new Date());
            accountJour.setAccountNumber(userId);
            accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
            accountJour.setWorkDate(DateUtil
                .getToday(DateUtil.DB_DATE_FORMAT_STRING));
            aJourDAO.insert(accountJour);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshUser(com.std.user.domain.User)
     */
    @Override
    public void refreshUser(User data) {
        if (data != null) {
            userDAO.update(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshUserSupple(com.std.user.domain.User)
     */
    @Override
    public void refreshUserSupple(User data) {
        if (null != data) {
            userDAO.updateSupple(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#saveUser(com.std.user.domain.User)
     */
    @Override
    public String saveUser(User data) {
        String userId = null;
        if (data != null) {
            userId = OrderNoGenerater.generate("U");
            data.setUserId(userId);
            userDAO.insert(data);
        }
        return userId;
    }
}
