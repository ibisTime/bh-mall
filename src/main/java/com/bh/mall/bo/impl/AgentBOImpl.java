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
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.SqForm;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Component
public class AgentBOImpl extends PaginableBOImpl<Agent> implements IAgentBO {

    @Autowired
    IAgentDAO agentDAO;

    @Autowired
    IAgentLogBO agentLogBO;

    /*************** 注册并保存新用户 **********************/
    // 注册
    @Override
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String loginPwd, String nickname, String photo,
            String status, Integer level, String fromUserId) {
        String userId = OrderNoGenerater.generate("U");
        Agent buser = new Agent();
        buser.setUserId(userId);
        buser.setUnionId(unionId);
        buser.setH5OpenId(h5OpenId);

        buser.setAppOpenId(appOpenId);
        buser.setMobile(mobile);

        buser.setFromUserId(fromUserId);

        buser.setNickname(nickname);
        buser.setPhoto(photo);
        buser.setStatus(status);

        Date date = new Date();
        buser.setCreateDatetime(date);

        buser.setLevel(level);
        agentDAO.insert(buser);
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
    public List<Agent> queryAgentList(Agent data) {
        return agentDAO.selectList(data);
    }

    @Override
    public List<Agent> queryAgentList(String mobile, String kind) {
        Agent condition = new Agent();
        condition.setMobile(mobile);
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
    public void checkAgentReferee(String userReferee, String systemCode) {
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
    public List<Agent> getAgentByUserReferee(String userReferee) {
        List<Agent> userList = new ArrayList<Agent>();
        if (StringUtils.isNotBlank(userReferee)) {
            Agent condition = new Agent();
            condition.setReferrer(userReferee);
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

    @Override
    public boolean isUserExist(String userId) {
        Agent condition = new Agent();
        condition.setUserId(userId);
        if (agentDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    /** 
     * @see com.bh.mall.bo.IAgentBO#checkIdentify(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName) {
        Agent condition = new Agent();
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
    public Agent getAgentByIdNo(String idNo) {
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
    public void refreshAgent(Agent data, String wxId, String mobile,
            String realName, String teamName, String province, String city,
            String area, String address) {
        data.setWxId(wxId);
        data.setMobile(mobile);
        data.setRealName(realName);
        data.setTeamName(teamName);
        data.setProvince(province);

        data.setCity(city);
        data.setArea(area);
        data.setAddress(address);
        agentDAO.update(data);
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
    public void resetBindMobile(Agent buser, String newMobile) {
        buser.setMobile(newMobile);
        agentDAO.resetBindMobile(buser);
    }

    @Override
    public void refreshHighUser(Agent data, String highUser, String updater,
            String remark) {

        data.setHighUserId(highUser);
        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(remark);

        String logCode = agentLogBO.updateAgent(data);
        data.setLastAgentLog(logCode);
        agentDAO.updateHigh(data);

    }

    @Override
    public void refreshReferee(Agent data, String referrer, String updater,
            String remark) {

        data.setReferrer(referrer);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        String logCode = agentLogBO.updateAgent(data);
        data.setLastAgentLog(logCode);
        agentDAO.updateUserReferee(data);

    }

    @Override
    public void refreshManager(Agent data, String manager, String updater) {
        data.setManager(manager);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        agentDAO.updateManager(data);

    }

    /*************** 查询 **********************/

    // 分页查询
    @Override
    public List<Agent> selectList(Agent condition, int pageNo, int pageSize) {
        return agentDAO.selectList(condition, pageNo, pageSize);
    }

    @Override
    public String getAgentName(String userId) {
        Agent condition = new Agent();
        condition.setUserId(userId);
        Agent data = agentDAO.select(condition);
        if (null == data) {
            return null;
        }
        return data.getRealName();
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

    @Override
    public void refreshInfo(Agent data) {
        data.setManager(null);
        data.setIdNo(null);
        data.setTeamName(null);
        data.setReferrer(null);

        agentDAO.updateInfo(data);
    }

    @Override
    public void refreshTeamName(Agent data, String teamName) {
        data.setTeamName(teamName);
        agentDAO.updateTeamName(data);
    }

    @Override
    public void applyAgent(Agent data, String realName, String wxId,
            String mobile, String province, String city, String area,
            String address, String logCode) {

        data.setRealName(realName);
        data.setWxId(wxId);
        data.setMobile(mobile);
        data.setProvince(province);
        data.setCity(city);

        data.setArea(area);
        data.setAddress(address);
        data.setLastAgentLog(logCode);
        agentDAO.applyAgent(data);

    }

    @Override
    public void refreshLastLog(Agent data, String status, String approver,
            String approveName, String logCode) {
        if (StringUtils.isNotEmpty(status)) {
            data.setStatus(status);
        }
        data.setApproveDatetime(new Date());
        data.setApprover(approver);
        data.setApproveName(approveName);
        data.setLastAgentLog(logCode);

        agentDAO.updateLastLog(data);
    }

    @Override
    public void refreshAgent(SqForm sqForm) {
        Agent data = this.getAgent(sqForm.getUserId());
        data.setRealName(sqForm.getRealName());
        data.setWxId(sqForm.getWxId());
        data.setReferrer(sqForm.getReferrer());
        data.setIntroducer(sqForm.getIntroducer());

        data.setTeamName(sqForm.getTeamName());
        data.setIdKind(sqForm.getIdKind());
        data.setIdNo(sqForm.getIdNo());
        data.setIdHand(sqForm.getIdHand());
        data.setProvince(sqForm.getProvince());

        data.setCity(sqForm.getCity());
        data.setArea(sqForm.getArea());
        data.setAddress(sqForm.getAddress());
        agentDAO.update(data);
    }

    @Override
    public void resetInfo(Agent data) {
        data.setMobile(null);
        data.setIdNo(null);
        data.setReferrer(null);
        data.setHighUserId(null);
        data.setTeamName(null);

        agentDAO.resetInfo(data);
    }

    @Override
    public void sqSuccess(SqForm sqForm) {
        Agent agent = getAgent(sqForm.getUserId());
        agent.setRealName(sqForm.getRealName());
        agent.setLevel(sqForm.getApplyLevel());
        agent.setReferrer(sqForm.getReferrer());
        agent.setIntroducer(sqForm.getIntroducer());

        agent.setIdKind(sqForm.getIdKind());
        agent.setIdNo(sqForm.getIdNo());
        agent.setIdHand(sqForm.getIdHand());
        agent.setTeamName(sqForm.getTeamName());
        agent.setHighUserId(sqForm.getToUserId());

        agent.setStatus(sqForm.getStatus());
        agent.setProvince(sqForm.getProvince());
        agent.setCity(sqForm.getCity());
        agent.setArea(sqForm.getArea());
        agent.setAddress(sqForm.getAddress());

        agent.setApprover(sqForm.getApprover());
        agent.setApproveName(sqForm.getApproveName());
        agent.setRemark(sqForm.getRemark());
        agent.setImpowerDatetime(sqForm.getImpowerDatetime());
        agentDAO.sqSuccess(agent);
    }

    @Override
    public void sjSuccess(SjForm sjForm) {
        Agent agent = getAgent(sjForm.getUserId());
        agent.setIdKind(sjForm.getIdKind());
        agent.setIdNo(sjForm.getIdNo());
        agent.setIdHand(sjForm.getIdHand());
        agent.setTeamName(sjForm.getTeamName());

        agent.setLevel(sjForm.getApplyLevel());
        agent.setApprover(sjForm.getApprover());
        agent.setApproveName(sjForm.getApproveName());
        agent.setRemark(sjForm.getRemark());
        agentDAO.sjSuccess(agent);
    }

    @Override
    public void refreshAgent(SqForm sqForm, String logCode) {
        Agent data = getAgent(sqForm.getUserId());
        data.setIdKind(sqForm.getIdKind());
        data.setIdNo(sqForm.getIdNo());
        data.setIdHand(sqForm.getIdHand());
        data.setTeamName(sqForm.getTeamName());

        data.setIntroducer(sqForm.getIntroducer());
        agentDAO.addInfo(data);
    }

    @Override
    public void resetUserReferee(String userId) {
        Agent condition = new Agent();
        condition.setReferrer(userId);
        List<Agent> list = agentDAO.selectList(condition);
        for (Agent agent : list) {
            agent.setReferrer(null);
            agentDAO.resetUserReferee(agent);
        }

    }

    @Override
    public void refreshLog(Agent data, String logCode) {
        data.setLastAgentLog(logCode);
        agentDAO.updateLastLog(data);
    }

}
