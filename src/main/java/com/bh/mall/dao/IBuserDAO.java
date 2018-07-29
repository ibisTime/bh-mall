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

    public void updateLog(BUser data);

    // 查询下级代理
    public List<BUser> selectAgentFront(BUser condition, int start, int limit);

    // 修改推荐的代理的上级
    public void updateHigh(BUser data);

    public void updateManager(BUser data);

}
