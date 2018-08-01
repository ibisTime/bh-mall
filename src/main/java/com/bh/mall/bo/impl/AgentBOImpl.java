package com.bh.mall.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.PwdUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAgentType;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class AgentBOImpl extends PaginableBOImpl<Agent> implements IAgentBO {

    @Autowired
    private IAgentDAO agentDAO;

    @Autowired
    private IAgentLogBO agentLogBO;

    /*************** 注册并保存新用户 **********************/
    // 注册
    @Override
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String loginPwd, String nickname, String photo,
            String status, Integer level, String userReferee) {
        String userId = OrderNoGenerater.generate("U");
        Agent buser = new Agent();
        buser.setUserId(userId);
        buser.setUnionId(unionId);
        buser.setH5OpenId(h5OpenId);

        buser.setAppOpenId(appOpenId);
        buser.setLoginName(mobile);
        buser.setMobile(mobile);

        buser.setUserReferee(userReferee);

        buser.setLoginPwd(MD5Util.md5(loginPwd));
        buser.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        buser.setNickname(nickname);
        buser.setPhoto(photo);
        buser.setStatus(status);

        Date date = new Date();
        buser.setCreateDatetime(date);

        buser.setLevel(level);
        agentDAO.insert(buser);
        return userId;
    }

    // 保存新用户
    @Override
    public String saveUser(String mobile, String kind) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            userId = OrderNoGenerater.generate("U");
            Agent data = new Agent();
            data.setUserId(userId);
            data.setMobile(mobile);
            data.setKind(kind);
            agentDAO.insert(data);
        }
        return userId;
    }

    /*************** 获取数据库信息 **********************/

    @Override
    public Agent doGetUserByOpenId(String h5OpenId) {
        Agent condition = new Agent();
        condition.setH5OpenId(h5OpenId);
        List<Agent> userList = agentDAO.selectList(condition);
        Agent buser = null;
        if (CollectionUtils.isNotEmpty(userList)) {
            buser = userList.get(0);
        }
        return buser;
    }

    @Override
    public Agent getAgent(String userId) {
        Agent data = null;
        if (StringUtils.isNotBlank(userId)) {
            Agent condition = new Agent();
            condition.setUserId(userId);
            data = agentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户不存在");
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IAgentBO#queryAgentLevelList(com.Agent.pz.domain.AgentDO)
     */
    @Override
    public List<Agent> queryUserList(Agent data) {
        return agentDAO.selectList(data);
    }

    @Override
    public List<Agent> queryUserList(String mobile, String kind) {
        Agent condition = new Agent();
        condition.setMobile(mobile);
        condition.setKind(kind);
        return agentDAO.selectList(condition);
    }

    /*************** 检查信息 **********************/
    /** 
     * @see com.bh.mall.bo.IAgentBO#doCheckOpenId(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId) {
        Agent condition = new Agent();
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
            Agent condition = new Agent();
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
            Agent condition = new Agent();
            condition.setMobile(mobile);
            condition.setKind(kind);
            List<Agent> list = agentDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                Agent data = list.get(0);
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
            Agent condition = new Agent();
            condition.setUserId(userReferee);
            long count = getTotalCount(condition);
            if (count <= 0) {
                throw new BizException("li01003", "推荐人不存在");
            }
        }
    }

    @Override
    public List<Agent> getUsersByUserReferee(String userReferee) {
        List<Agent> userList = new ArrayList<Agent>();
        if (StringUtils.isNotBlank(userReferee)) {
            Agent condition = new Agent();
            condition.setUserReferee(userReferee);
            userList = agentDAO.selectList(condition);
        }
        return userList;
    }

    @Override
    public Agent getAgentByMobile(String introducer) {
        Agent data = null;
        if (StringUtils.isNotBlank(introducer)) {
            Agent condition = new Agent();
            condition.setMobile(introducer);
            data = agentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000000", "介绍人不存在");
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IAgentBO#getAgentByMobile(java.lang.String)
     */
    @Override
    public Agent getUserByLoginName(String loginName, String systemCode) {
        Agent data = null;
        if (StringUtils.isNotBlank(loginName)) {
            Agent condition = new Agent();
            condition.setLoginName(loginName);
            List<Agent> list = agentDAO.selectList(condition);
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
            Agent condition = new Agent();
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
        Agent condition = new Agent();
        condition.setUserId(userId);
        if (agentDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
            Agent condition = new Agent();
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
            Agent condition = new Agent();
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
     * @see com.bh.mall.bo.IAgentBO#checkIdentify(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName) {
        Agent condition = new Agent();
        condition.setKind(kind);
        condition.setIdKind(idKind);
        condition.setIdNo(idNo);
        condition.setRealName(realName);
        List<Agent> userList = agentDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            Agent data = userList.get(0);
            throw new BizException("xn000001",
                "用户[" + data.getMobile() + "]已使用该身份信息，请重新填写");
        }
    }

    // 检查团队名称
    @Override
    public void checkTeamName(String teamName) {
        List<Agent> list = null;
        if (StringUtils.isNotBlank(teamName)) {
            Agent condition = new Agent();
            condition.setTeamName(teamName);
            list = agentDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BizException("xn000000", "该团队名称已经存在喽，重新起一个吧！");
            }
        }
    }

    // 检查身份证号
    @Override
    public Agent getUserByIdNo(String idNo) {
        Agent data = null;
        if (StringUtils.isNotBlank(idNo)) {
            Agent condition = new Agent();
            condition.setIdNo(idNo);
            data = agentDAO.select(condition);
            if (data != null) {
                throw new BizException("xn000000", "身份证号已存在");
            }
        }
        return data;
    }

    /*************** 更新信息 **********************/

    // 重置密码
    @Override
    public void resetLoginPwd(Agent buser, String loginPwd) {
        buser.setLoginPwd(MD5Util.md5(loginPwd));
        buser.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        agentDAO.updateLoginPwd(buser);
    }

    // 更新状态
    @Override
    public void refreshStatus(Agent data, String updater, String remark) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        data.setUserId(data.getUserId());
        data.setStatus(EUserStatus.CANCELED.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setLastAgentLog(code);
        data.setRemark(remark);
        agentDAO.updateStatus(data);

        // insert new agent log
        agentLogBO.saveAgentLog(data, null);
    }

    // 更新登录名
    @Override
    public void refreshLoginName(String userId, String loginName) {
        if (StringUtils.isNotBlank(userId)) {
            Agent data = new Agent();
            data.setUserId(userId);
            data.setLoginName(loginName);
            agentDAO.updateLoginName(data);
        }
    }

    // 更新昵称
    @Override
    public void refreshNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            Agent data = new Agent();
            data.setUserId(userId);
            data.setNickname(nickname);
            agentDAO.updateNickname(data);
        }
    }

    // 更新头像
    @Override
    public void refreshPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId)) {
            Agent data = new Agent();
            data.setUserId(userId);
            data.setPhoto(photo);
            agentDAO.updatePhoto(data);
        }
    }

    /** 
     * @see com.bh.mall.bo.IAgentBO#refreshAgent(com.bh.mall.domain.Agent)
     */
    @Override
    public void refreshAgent(Agent data) {
        if (data != null) {
            agentDAO.update(data);
        }
    }

    @Override
    public void refreshWxInfo(String userId, String unionId, String h5OpenId,
            String appOpenId, String nickname, String photo) {
        Agent dbAgent = getAgent(userId);
        dbAgent.setUnionId(unionId);
        if (StringUtils.isNotBlank(h5OpenId)) {
            dbAgent.setH5OpenId(h5OpenId);
        }
        if (StringUtils.isNotBlank(appOpenId)) {
            dbAgent.setAppOpenId(appOpenId);
        }
        dbAgent.setNickname(nickname);
        dbAgent.setPhoto(photo);
        agentDAO.updateWxInfo(dbAgent);
    }

    @Override
    public void refreshRole(String userId, String roleCode, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            Agent data = new Agent();
            data.setUserId(userId);
            data.setRoleCode(roleCode);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            agentDAO.updateRole(data);
        }
    }

    @Override
    public void resetBindMobile(Agent buser, String newMobile) {
        buser.setMobile(newMobile);
        agentDAO.resetBindMobile(buser);
    }

    @Override
    public void refreshHighUser(Agent data, String highUser, String updater) {

        String code = OrderNoGenerater
<<<<<<< HEAD
            .generate(EGeneratePrefix.AgentLog.getCode());

=======
            .generate(EGeneratePrefix.Agent.getCode());
>>>>>>> refs/remotes/origin/master
        Agent alData = new Agent();
        alData.setLastAgentLog(code);
        alData.setUserId(data.getUserId());
        alData.setStatus(EAgentType.Update.getCode());
        Date date = new Date();
        alData.setApplyDatetime(date);
        agentDAO.updateHigh(alData);

        // insert new log
        agentLogBO.saveAgentLog(data, null);

    }

    @Override
    public void refreshReferee(Agent data, String userReferee, String updater) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        data.setLastAgentLog(code);
        data.setUserReferee(userReferee);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        agentDAO.updateUserReferee(data);

        // insert new log
        agentLogBO.saveAgentLog(data, null);

    }

    @Override
    public void refreshManager(Agent data, String manager, String updater) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AgentLog.getCode());

        data.setLastAgentLog(code);
        data.setHighUserId(manager);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        agentDAO.updateManager(data);

        // insert new log
        agentLogBO.saveAgentLog(data, null);
    }

    /*************** 查询 **********************/

    // 查询下级代理
    @Override
    public List<Agent> selectAgentFront(Agent condition, int start, int limit) {
        return agentDAO.selectAgentFront(condition, start, limit);
    }

    // 分页查询
    @Override
    public List<Agent> selectList(Agent condition, int pageNo, int pageSize) {
        return agentDAO.selectList(condition, pageNo, pageSize);
    }

    @Override
    public Agent getAgentName(String userId) {
        Agent condition = new Agent();
        condition.setUserId(userId);
        return agentDAO.select(condition);
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(tradePwd)) {
            Agent condition = new Agent();
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

    // TODO
    @Override
    public Agent getTeamLeader(String teamName) {
        Agent condition = new Agent();
        condition.setTeamName(teamName);
        condition
            .setLevel(StringValidater.toInteger(EAgentLevel.ONE.getCode()));
        List<Agent> list = agentDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "该团队没有团队长");
        }
        return list.get(0);
    }

    @Override
    public boolean isHighest(String userId) {
        Agent data = this.getAgent(userId);
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel()) {
            return true;
        }
        return false;
    }

}
