package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.BUser;

public interface IBuserDAO extends IBaseDAO<BUser> {
    String NAMESPACE = IBuserDAO.class.getName().concat(".");

    // 设置登录密码
    public int updateLoginPwd(BUser data);

    // 更新状态
    public int updateStatus(BUser data);

    // 更新角色
    public int updateRole(BUser data);

    // 更新用户名
    public int updateLoginName(BUser data);

    // 更新昵称
    public int updateNickname(BUser data);

    // 更新头像
    public int updatePhoto(BUser data);

    public int update(BUser data);

    public int updateLevel(BUser data);

    // 更新用户手机号和真实信息
    public int updateMobileIds(BUser data);

    // 微信登录更新用户信息
    public int updateWxInfo(BUser data);

    public int approveUser(BUser data);

    public void resetBindMobile(BUser user);

    public void updateInformation(BUser data);

    // 申请升级
    public void upgradeLevel(BUser data);

    public void updateLog(BUser data);

    // 查询下级代理
    public List<BUser> selectAgentFront(BUser condition, int start, int limit);

    // 意向分配
    public void applyIntent(BUser data);

    public void allotAgency(BUser data);

    // 申请代理
    public void toApply(BUser data);

    // 接受意向分配
    public void acceptIntention(BUser data);

    public void updateHighUser(BUser data);

    // 取消申请授权
    public void cancelImpower(BUser data);

    public void cancelUplevel(BUser data);

    // 审核升级
    public void approveUpgrade(BUser data);

    // 补充授权所需信息
    public void addInfo(BUser data);

    // 修改推荐的代理的上级
    public void updateHigh(BUser data);

    // public void updateHighUser(BUser data);
    public void ignore(BUser data);

    public void updateManager(BUser data);

    public void approveImpower(BUser data);

}
