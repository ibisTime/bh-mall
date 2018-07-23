package com.bh.mall.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.PwdUtil;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IBuserDAO;
import com.bh.mall.domain.BUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class BUserBOImpl extends PaginableBOImpl<BUser> implements IBuserBO {

    @Autowired
    private IBuserDAO buserDAO;

    /*************** 注册并保存新用户 **********************/
    // 注册
    @Override
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String kind, String loginPwd, String nickname,
            String photo, String status, Integer level, String userReferee) {
        String userId = OrderNoGenerater.generate("U");
        BUser buser = new BUser();
        buser.setUserId(userId);
        buser.setUnionId(unionId);
        buser.setH5OpenId(h5OpenId);

        buser.setAppOpenId(appOpenId);
        buser.setLoginName(mobile);
        buser.setMobile(mobile);
        buser.setKind(kind);
        buser.setUserReferee(userReferee);

        buser.setLoginPwd(MD5Util.md5(loginPwd));
        buser.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        buser.setNickname(nickname);
        buser.setPhoto(photo);
        buser.setStatus(status);

        Date date = new Date();
        buser.setCreateDatetime(date);

        buser.setLevel(level);
        buserDAO.insert(buser);
        return userId;
    }

    @Override
    public String doRegister(String wxId, String level, String realName,
            String province, String city, String area, String address,
            String unionId, String h5OpenId, String appOpenId, String mobile,
            String kind, String loginPwd, String nickname, String photo,
            String userReferee, String lastAgentLog) {
        String userId = OrderNoGenerater.generate("U");
        BUser buser = new BUser();
        buser.setUserId(userId);
        buser.setLoginName(mobile);
        buser.setMobile(mobile);
        buser.setWxId(wxId);
        buser.setPhoto(photo);
        buser.setNickname(nickname);
        buser.setApplyLevel(StringValidater.toInteger(level));
        buser.setRealName(realName);

        buser.setStatus(EUserStatus.MIND.getCode());
        buser.setApplyDatetime(new Date());
        buser.setUnionId(unionId);
        buser.setH5OpenId(h5OpenId);
        buser.setAppOpenId(appOpenId);

        buser.setKind(kind);
        buser.setLoginPwd(MD5Util.md5(loginPwd));
        buser.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));

        buser.setNickname(nickname);
        buser.setPhoto(photo);
        buser.setUserReferee(userReferee);
        buser.setProvince(province);
        buser.setCity(city);
        buser.setArea(area);
        buser.setAddress(address);

        buser.setCreateDatetime(new Date());
        buser.setLastAgentLog(lastAgentLog);

        buserDAO.insert(buser);
        return userId;
    }

    @Override
    public String doRegister(String realName, String level, String wxId,
            String idBehind, String idFront, String introducer, String fromInfo,
            String userReferee, String mobile, String province, String city,
            String area, String address, String loginPwd, String photo,
            String nickname, String unionId, String h5OpenId, String logCode) {
        String userId = OrderNoGenerater.generate("U");
        BUser data = new BUser();
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
        data.setLastAgentLog(logCode);
        buserDAO.insert(data);

        return userId;

    }

    // 保存新用户
    @Override
    public String saveUser(String mobile, String kind) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            userId = OrderNoGenerater.generate("U");
            BUser data = new BUser();
            data.setUserId(userId);
            data.setMobile(mobile);
            data.setKind(kind);
            buserDAO.insert(data);
        }
        return userId;
    }

    /*************** 获取数据库信息 **********************/

    @Override
    public BUser doGetUserByOpenId(String h5OpenId) {
        BUser condition = new BUser();
        condition.setH5OpenId(h5OpenId);
        List<BUser> userList = buserDAO.selectList(condition);
        BUser buser = null;
        if (CollectionUtils.isNotEmpty(userList)) {
            buser = userList.get(0);
        }
        return buser;
    }

    @Override
    public BUser getUser(String userId) {
        BUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            BUser condition = new BUser();
            condition.setUserId(userId);
            data = buserDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户不存在");
            }
        }
        return data;
    }

    @Override
    public BUser getCheckUser(String userId) {
        BUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            BUser condition = new BUser();
            condition.setUserId(userId);
            data = buserDAO.select(condition);
            if (null == data) {
                throw new BizException("xn702002", userId + "用户不存在");
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IBUserBO#queryBUserList(com.BUser.pz.domain.BUserDO)
     */
    @Override
    public List<BUser> queryUserList(BUser data) {
        return buserDAO.selectList(data);
    }

    @Override
    public List<BUser> queryUserList(String mobile, String kind) {
        BUser condition = new BUser();
        condition.setMobile(mobile);
        condition.setKind(kind);
        return buserDAO.selectList(condition);
    }

    /*************** 检查信息 **********************/
    /** 
     * @see com.bh.mall.bo.IBUserBO#doCheckOpenId(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId) {
        BUser condition = new BUser();
        condition.setUnionId(unionId);
        condition.setH5OpenId(h5OpenId);
        condition.setAppOpenId(appOpenId);
        long count = getTotalCount(condition);
        if (count > 0) {
            throw new BizException("xn702002", "微信编号已存在");
        }
    }

    // 检查手机号是否已存在
    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            BUser condition = new BUser();
            condition.setMobile(mobile);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    // 判断登录名
    @Override
    public String getUserId(String mobile, String kind) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            BUser condition = new BUser();
            condition.setMobile(mobile);
            condition.setKind(kind);
            List<BUser> list = buserDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                BUser data = list.get(0);
                userId = data.getUserId();
            }
        }
        return userId;
    }

    // 检查推荐人
    @Override
    public void checkUserReferee(String userReferee, String systemCode) {
        if (StringUtils.isNotBlank(userReferee)) {
            // 判断格式
            BUser condition = new BUser();
            condition.setUserId(userReferee);
            long count = getTotalCount(condition);
            if (count <= 0) {
                throw new BizException("li01003", "推荐人不存在");
            }
        }
    }

    @Override
    public List<BUser> getUsersByUserReferee(String userReferee) {
        List<BUser> userList = new ArrayList<BUser>();
        if (StringUtils.isNotBlank(userReferee)) {
            BUser condition = new BUser();
            condition.setUserReferee(userReferee);
            userList = buserDAO.selectList(condition);
        }
        return userList;
    }

    @Override
    public BUser getUserByMobile(String introducer) {
        BUser data = null;
        if (StringUtils.isNotBlank(introducer)) {
            BUser condition = new BUser();
            condition.setMobile(introducer);
            data = buserDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000000", "介绍人不存在");
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IBUserBO#getBUserByMobile(java.lang.String)
     */
    @Override
    public BUser getUserByLoginName(String loginName, String systemCode) {
        BUser data = null;
        if (StringUtils.isNotBlank(loginName)) {
            BUser condition = new BUser();
            condition.setLoginName(loginName);
            List<BUser> list = buserDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "登录名重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    @Override
    public void isLoginNameExist(String loginName, String kind) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            BUser condition = new BUser();
            condition.setLoginName(loginName);
            condition.setKind(kind);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    @Override
    public boolean isUserExist(String userId) {
        BUser condition = new BUser();
        condition.setUserId(userId);
        if (buserDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
            BUser condition = new BUser();
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
            BUser condition = new BUser();
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
     * @see com.bh.mall.bo.IBUserBO#checkIdentify(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName) {
        BUser condition = new BUser();
        condition.setKind(kind);
        condition.setIdKind(idKind);
        condition.setIdNo(idNo);
        condition.setRealName(realName);
        List<BUser> userList = buserDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            BUser data = userList.get(0);
            throw new BizException("xn000001",
                "用户[" + data.getMobile() + "]已使用该身份信息，请重新填写");
        }
    }

    // 检查团队名称
    @Override
    public void checkTeamName(String teamName) {
        List<BUser> list = null;
        if (StringUtils.isNotBlank(teamName)) {
            BUser condition = new BUser();
            condition.setTeamName(teamName);
            list = buserDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BizException("xn000000", "该团队名称已经存在喽，重新起一个吧！");
            }
        }
    }

    // 检查身份证号
    @Override
    public BUser getUserByIdNo(String idNo) {
        BUser data = null;
        if (StringUtils.isNotBlank(idNo)) {
            BUser condition = new BUser();
            condition.setIdNo(idNo);
            data = buserDAO.select(condition);
            if (data != null) {
                throw new BizException("xn000000", "身份证号已存在");
            }
        }
        return data;
    }

    /*************** 更新信息 **********************/

    @Override
    public void addInfo(BUser data) {
        buserDAO.addInfo(data);
    }

    // 重置密码
    @Override
    public void resetLoginPwd(BUser buser, String loginPwd) {
        buser.setLoginPwd(MD5Util.md5(loginPwd));
        buser.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        buserDAO.updateLoginPwd(buser);
    }

    // 更新状态
    @Override
    public void refreshStatus(String userId, EUserStatus status, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            BUser data = new BUser();
            data.setUserId(userId);
            data.setStatus(status.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            buserDAO.updateStatus(data);
        }
    }

    // 更新登录名
    @Override
    public void refreshLoginName(String userId, String loginName) {
        if (StringUtils.isNotBlank(userId)) {
            BUser data = new BUser();
            data.setUserId(userId);
            data.setLoginName(loginName);
            buserDAO.updateLoginName(data);
        }
    }

    // 更新昵称
    @Override
    public void refreshNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            BUser data = new BUser();
            data.setUserId(userId);
            data.setNickname(nickname);
            buserDAO.updateNickname(data);
        }
    }

    // 更新头像
    @Override
    public void refreshPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId)) {
            BUser data = new BUser();
            data.setUserId(userId);
            data.setPhoto(photo);
            buserDAO.updatePhoto(data);
        }
    }

    /** 
     * @see com.bh.mall.bo.IBUserBO#refreshBUser(com.bh.mall.domain.BUser)
     */
    @Override
    public void refreshUser(BUser data) {
        if (data != null) {
            buserDAO.update(data);
        }
    }

    @Override
    public void refreshWxInfo(String userId, String unionId, String h5OpenId,
            String appOpenId, String nickname, String photo) {
        BUser dbBUser = getUser(userId);
        dbBUser.setUnionId(unionId);
        if (StringUtils.isNotBlank(h5OpenId)) {
            dbBUser.setH5OpenId(h5OpenId);
        }
        if (StringUtils.isNotBlank(appOpenId)) {
            dbBUser.setAppOpenId(appOpenId);
        }
        dbBUser.setNickname(nickname);
        dbBUser.setPhoto(photo);
        buserDAO.updateWxInfo(dbBUser);
    }

    @Override
    public void refreshRole(String userId, String roleCode, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            BUser data = new BUser();
            data.setUserId(userId);
            data.setRoleCode(roleCode);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            buserDAO.updateRole(data);
        }
    }

    @Override
    public void resetBindMobile(BUser buser, String newMobile) {
        buser.setMobile(newMobile);
        buserDAO.resetBindMobile(buser);
    }

    /*************** 申请代理，审核，升级 **********************/

    // 申请代理
    @Override
    public void applyIntent(BUser data) {
        buserDAO.applyIntent(data);
    }

    @Override
    public void toApply(BUser data) {
        buserDAO.toApply(data);
    }

    @Override
    public void allotAgency(BUser data) {
        buserDAO.allotAgency(data);
    }

    // 接受意向分配
    @Override
    public void acceptIntention(BUser data) {
        buserDAO.acceptIntention(data);
    }

    // 向下分配
    @Override
    public void refreshHighUser(BUser data) {
        buserDAO.updateHigh(data);
    }

    // 申请升级
    @Override
    public void upgradeLevel(BUser data) {
        buserDAO.upgradeLevel(data);
    }

    // 取消授权申请
    @Override
    public void cancelImpower(BUser data) {
        buserDAO.cancelImpower(data);
    }

    @Override
    public void cancelUplevel(BUser data) {
        buserDAO.cancelUplevel(data);
    }

    @Override
    public void approveImpower(BUser data) {
        buserDAO.approveImpower(data);
    }

    @Override
    public void approveUpgrade(BUser data) {
        buserDAO.approveUpgrade(data);
    }

    @Override
    public void updateInformation(BUser data) {
        buserDAO.updateInformation(data);
    }

    @Override
    public void refreshManager(BUser data, String manager, String updater) {
        data.setHighUserId(manager);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        buserDAO.updateManager(data);
    }

    @Override
    public void ignore(BUser data) {
        data.setStatus(EUserStatus.IGNORED.getCode());
        buserDAO.ignore(data);
    }

    /*************** 查询 **********************/

    // 查询下级代理
    @Override
    public List<BUser> selectAgentFront(BUser condition, int start, int limit) {
        return buserDAO.selectAgentFront(condition, start, limit);
    }

    // 分页查询
    @Override
    public List<BUser> selectList(BUser condition, int pageNo, int pageSize) {
        return buserDAO.selectList(condition, pageNo, pageSize);
    }

    @Override
    public BUser getSysUser() {
        BUser condition = new BUser();
        condition.setKind(EUserKind.Plat.getCode());
        List<BUser> list = buserDAO.selectList(condition);
        return list.get(0);
    }

    @Override
    public BUser getUserName(String userId) {
        BUser condition = new BUser();
        condition.setUserId(userId);
        return buserDAO.select(condition);
    }

}
