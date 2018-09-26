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
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.SqForm;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAgentLogType;
import com.bh.mall.enums.EAgentStatus;
import com.bh.mall.enums.EIsImpower;
import com.bh.mall.enums.EIsTrader;
import com.bh.mall.enums.ESjFormStatus;
import com.bh.mall.enums.EYxFormStatus;
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
            String status, String fromUserId) {
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
                throw new BizException("xn0000", "用户" + userId + "不存在");
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

        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(remark);
        String logCode = agentLogBO.refreshAgent(data,
            EAgentLogType.OUT.getCode(), EAgentStatus.CANCELED.getCode());

        data.setStatus(EAgentStatus.MIND.getCode());
        data.setLastAgentLog(logCode);
        data.setMobile(null);
        data.setHighUserId(null);
        data.setReferrer(null);
        data.setIntroducer(null);
        data.setIdNo(null);
        data.setLevel(null);

        agentDAO.updateStatus(data);

    }

    // 更新头像
    @Override
    public void refreshPhoto(Agent buser, String photo) {
        buser.setPhoto(photo);
        agentDAO.updatePhoto(buser);
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
    public void resetBindMobile(Agent buser, String newMobile) {
        buser.setMobile(newMobile);
        agentDAO.resetBindMobile(buser);
    }

    @Override
    public void refreshLevel(Agent buser) {
        agentDAO.refreshLevel(buser);
    }

    @Override
    public void refreshHighUser(Agent data, String highUser, String updater,
            String remark) {

        data.setHighUserId(highUser);
        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(remark);

        String logCode = agentLogBO.refreshAgent(data,
            EAgentLogType.Update.getCode(), data.getStatus());
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

        data.setStatus(EAgentStatus.CANCELED.getCode());
        data.setArea(area);
        data.setAddress(address);
        data.setLastAgentLog(logCode);
        agentDAO.applyAgent(data);

    }

    @Override
    public void refreshYx(Agent data, String status, String approver,
            String approveName, String logCode) {

        if (EYxFormStatus.IGNORED.getCode().equals(status)) {
            data.setStatus(status);
            data.setMobile(null);
        } else if (EYxFormStatus.ACCEPT.getCode().equals(status)) {
            data.setStatus(EAgentStatus.ADD_INFO.getCode());
        }

        data.setApproveDatetime(new Date());
        data.setApprover(approver);
        data.setApproveName(approveName);
        data.setLastAgentLog(logCode);

        agentDAO.updateLastLog(data);
    }

    @Override
    public void resetInfo(Agent data) {
        data.setMobile(null);
        data.setIdNo(null);
        data.setReferrer(null);
        data.setIntroducer(null);
        data.setHighUserId(null);

        data.setTeamName(null);
        data.setLevel(null);

        data.setStatus(EAgentStatus.MIND.getCode());
        agentDAO.resetInfo(data);
    }

    @Override
    public void refreshAgent(SqForm sqForm, String logCode, String status) {
        Agent data = getAgent(sqForm.getUserId());

        data.setHighUserId(sqForm.getToUserId());
        data.setRealName(sqForm.getRealName());
        data.setIdKind(sqForm.getIdKind());
        data.setIdNo(sqForm.getIdNo());
        data.setIdHand(sqForm.getIdHand());
        data.setWxId(sqForm.getWxId());

        data.setMobile(sqForm.getMobile());
        data.setProvince(sqForm.getProvince());
        data.setCity(sqForm.getCity());
        data.setArea(sqForm.getCity());
        data.setAddress(sqForm.getAddress());

        data.setStatus(status);
        data.setIntroducer(sqForm.getIntroducer());
        data.setLastAgentLog(logCode);
        agentDAO.addInfo(data);
    }

    @Override
    public void resetUserReferee(Agent agent, String userReferee) {

        Agent data = getAgent(agent.getUserId());
        data.setReferrer(userReferee);
        agentDAO.resetUserReferee(data);

        Agent condition = new Agent();
        condition.setReferrer(agent.getUserId());
        List<Agent> list = agentDAO.selectList(condition);
        for (Agent referrer : list) {
            referrer.setReferrer(null);
            agentDAO.resetUserReferee(referrer);
        }

    }

    @Override
    public void refreshLog(Agent data, String logCode) {
        data.setLastAgentLog(logCode);
        agentDAO.updateLastLog(data);
    }

    @Override
    public void addInfo(SqForm sqForm, String logCode, String status) {
        Agent agent = getAgent(sqForm.getUserId());
        agent.setRealName(sqForm.getRealName());
        agent.setIntroducer(sqForm.getIntroducer());

        agent.setHighUserId(sqForm.getUserId());
        agent.setIdKind(sqForm.getIdKind());
        agent.setIdNo(sqForm.getIdNo());
        agent.setIdHand(sqForm.getIdHand());

        agent.setStatus(status);
        agent.setProvince(sqForm.getProvince());
        agent.setCity(sqForm.getCity());
        agent.setArea(sqForm.getArea());
        agent.setAddress(sqForm.getAddress());
        agent.setLastAgentLog(logCode);
        agentDAO.applyAgent(agent);
    }

    @Override
    public void refreshSq(Agent data, SqForm sqForm, String manager,
            String highUserId, String teamName, Integer level, String status,
            String approver, String approveName, String logCode, Date date) {

        data.setHighUserId(highUserId);

        if (EAgentStatus.IMPOWERED.getCode().equals(status)) {
            data.setLevel(sqForm.getApplyLevel());
            data.setImpowerDatetime(date);
            data.setManager(manager);
            data.setIdKind(sqForm.getIdKind());

            data.setIdNo(sqForm.getIdNo());
            data.setIdHand(sqForm.getIdHand());
            data.setStatus(status);
            data.setIntroducer(sqForm.getIntroducer());
            data.setReferrer(sqForm.getReferrer());

            data.setIsImpower(EIsImpower.NO_CHARGE.getCode());
        } else if (EAgentStatus.CANCELED.getCode().equals(status)) {
            data.setHighUserId(null);
            data.setLevel(null);
            data.setStatus(EAgentStatus.MIND.getCode());
        } else {
            data.setStatus(status);
        }
        data.setIsTrader(EIsTrader.TRADER_NO.getCode());
        data.setTeamName(teamName);

        data.setApprover(approver);
        data.setApproveName(approveName);
        data.setApproveDatetime(sqForm.getApproveDatetime());
        data.setRemark(sqForm.getRemark());
        data.setLastAgentLog(logCode);

        agentDAO.updateSq(data);
    }

    @Override
    public void refreshSj(Agent data, SjForm sjForm, String approver,
            String approveName, String remark, String status, String logCode) {

        if (ESjFormStatus.THROUGH_YES.getCode().equals(status)) {
            data.setLevel(sjForm.getApplyLevel());
            data.setTeamName(sjForm.getTeamName());
            if (StringUtils.isNotBlank(sjForm.getIdNo())) {
                data.setIdKind(sjForm.getIdKind());
                data.setIdNo(sjForm.getIdNo());
                data.setIdHand(sjForm.getIdHand());
            }
            data.setHighUserId(sjForm.getToUserId());

            data.setIsImpower(EIsImpower.NO_Upgrade.getCode());
        } else if (ESjFormStatus.IMPOWERED.getCode().equals(status)) {
            status = EAgentStatus.IMPOWERED.getCode();
        }

        data.setStatus(status);
        Date date = new Date();
        data.setApprover(approver);
        data.setApproveName(approveName);
        data.setApproveDatetime(date);
        data.setRemark(remark);

        data.setLastAgentLog(logCode);
        agentDAO.updateSj(data);

    }

    @Override
    public void refreshStatus(Agent data, String status) {
        data.setStatus(status);
        agentDAO.updateStatus(data);
    }

    @Override
    public void refreshIsImpower(Agent agent, String isImpower) {
        agent.setIsImpower(isImpower);
        agentDAO.updateImpower(agent);
    }

    @Override
    public void doSetTrader(Agent data, String updater, String remark) {
        Date date = new Date();
        data.setIsTrader(EIsTrader.TRADER_YES.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(date);
        data.setRemark(remark);
        agentLogBO.refreshAgent(data, EAgentLogType.Trader.getCode(),
            data.getStatus());
        agentDAO.updaterTrader(data);

    }

    @Override
    public void doCancelTrader(Agent data, String updater, String remark) {
        Date date = new Date();
        data.setIsTrader(EIsTrader.TRADER_NO.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(date);
        data.setRemark(remark);
        agentLogBO.refreshAgent(data, EAgentLogType.Trader.getCode(),
            data.getStatus());
        agentDAO.updaterTrader(data);

    }

}
