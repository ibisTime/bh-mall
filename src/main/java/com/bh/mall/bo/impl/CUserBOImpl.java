package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ICUserBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.ICUserDAO;
import com.bh.mall.domain.CUser;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class CUserBOImpl extends PaginableBOImpl<CUser> implements ICUserBO {

    @Autowired
    private ICUserDAO cuserDAO;

    @Override
    public CUser doGetUserByOpenId(String h5OpenId) {
        CUser condition = new CUser();
        condition.setH5OpenId(h5OpenId);
        List<CUser> userList = cuserDAO.selectList(condition);
        CUser cuser = null;
        if (CollectionUtils.isNotEmpty(userList)) {
            cuser = userList.get(0);
        }
        return cuser;
    }

    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            CUser condition = new CUser();

            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    @Override
    public String getUserId(String mobile) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile)) {
            CUser condition = new CUser();

            List<CUser> list = cuserDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                CUser data = list.get(0);
                userId = data.getUserId();
            }
        }
        return userId;
    }

    @Override
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String loginPwd, String nickname, String photo,
            String status) {
        String userId = OrderNoGenerater.generate("U");
        CUser user = new CUser();
        user.setUserId(userId);
        user.setUnionId(unionId);
        user.setH5OpenId(h5OpenId);
        user.setAppOpenId(appOpenId);

        user.setNickname(nickname);
        user.setPhoto(photo);
        user.setStatus(status);

        Date date = new Date();
        user.setCreateDatetime(date);

        cuserDAO.insert(user);
        return userId;
    }

    @Override
    public String doRegister(String wxId, String unionId, String h5OpenId,
            String appOpenId, String mobile, String kind, String loginPwd,
            String nickname, String photo) {
        String userId = OrderNoGenerater.generate("U");
        CUser user = new CUser();
        user.setUserId(userId);

        user.setWxId(wxId);
        user.setPhoto(photo);
        user.setNickname(nickname);

        user.setStatus(EUserStatus.MIND.getCode());
        user.setUnionId(unionId);
        user.setH5OpenId(h5OpenId);
        user.setAppOpenId(appOpenId);

        user.setNickname(nickname);
        user.setPhoto(photo);
        user.setCreateDatetime(new Date());

        cuserDAO.insert(user);
        return userId;
    }

    @Override
    public String saveUser(String mobile) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile)) {
            userId = OrderNoGenerater.generate("U");
            CUser data = new CUser();
            data.setUserId(userId);

            cuserDAO.insert(data);
        }
        return userId;
    }

    @Override
    public CUser getUser(String userId) {
        CUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            CUser condition = new CUser();
            condition.setUserId(userId);
            data = cuserDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户不存在");
            }
        }
        return data;
    }

    @Override
    public CUser getUserNoCheck(String userId) {
        CUser condition = new CUser();
        condition.setUserId(userId);
        return cuserDAO.select(condition);
    }

    @Override
    public CUser getCheckUser(String userId) {
        CUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            CUser condition = new CUser();
            condition.setUserId(userId);
            data = cuserDAO.select(condition);
            if (null == data) {
                throw new BizException("xn702002", userId + "用户不存在");
            }
        }
        return data;
    }

    @Override
    public CUser getUserByLoginName(String loginName) {
        CUser data = null;
        if (StringUtils.isNotBlank(loginName)) {
            CUser condition = new CUser();

            List<CUser> list = cuserDAO.selectList(condition);
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
     * @see com.ibis.pz.user.IUserBO#queryAgentList(com.User.pz.domain.UserDO)
     */
    @Override
    public List<CUser> queryUserList(CUser data) {
        return cuserDAO.selectList(data);
    }

    @Override
    public void isLoginNameExist(String loginName) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            CUser condition = new CUser();

            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    @Override
    public boolean isUserExist(String userId) {
        CUser condition = new CUser();
        condition.setUserId(userId);

        if (cuserDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(tradePwd)) {
            CUser condition = new CUser();
            condition.setUserId(userId);

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
            CUser condition = new CUser();
            condition.setUserId(userId);

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
            CUser condition = new CUser();
            condition.setUserId(userId);

            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", alertStr + "错误");
            }
        } else {
            throw new BizException("jd00001", alertStr + "错误");
        }
    }

    @Override
    public void refreshStatus(String userId, EUserStatus status) {
        if (StringUtils.isNotBlank(userId)) {
            CUser data = new CUser();
            data.setUserId(userId);
            data.setStatus(status.getCode());

            cuserDAO.updateStatus(data);
        }
    }

    @Override
    public void refreshNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            CUser data = new CUser();
            data.setUserId(userId);
            data.setNickname(nickname);
            cuserDAO.updateNickname(data);
        }
    }

    @Override
    public void refreshUser(CUser data) {
        if (data != null) {
            cuserDAO.update(data);
        }
    }

    @Override
    public void refreshWxInfo(String userId, String unionId, String h5OpenId,
            String appOpenId, String nickname, String photo) {
        CUser dbUser = getUser(userId);
        dbUser.setUnionId(unionId);
        if (StringUtils.isNotBlank(h5OpenId)) {
            dbUser.setH5OpenId(h5OpenId);
        }
        if (StringUtils.isNotBlank(appOpenId)) {
            dbUser.setAppOpenId(appOpenId);
        }
        dbUser.setNickname(nickname);
        dbUser.setPhoto(photo);
        cuserDAO.updateWxInfo(dbUser);
    }

    @Override
    public List<CUser> queryUserList(String mobile) {
        CUser condition = new CUser();

        return cuserDAO.selectList(condition);
    }

    /** 
     * @see com.bh.mall.bo.IUserBO#doCheckOpenId(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId) {
        CUser condition = new CUser();
        condition.setUnionId(unionId);
        condition.setH5OpenId(h5OpenId);
        condition.setAppOpenId(appOpenId);

        long count = getTotalCount(condition);
        if (count > 0) {
            throw new BizException("xn702002", "微信编号已存在");
        }
    }

}
