package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.IBlacklistAO;
import com.std.user.bo.IBlacklistBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.Blacklist;
import com.std.user.domain.User;
import com.std.user.enums.EBlacklistStatus;
import com.std.user.exception.BizException;

@Service
public class BlacklistAOImpl implements IBlacklistAO {

    @Autowired
    private IBlacklistBO blacklistBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public void addBlacklist(String userId, String type, String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户编号不存在");
        }
        blacklistBO.saveBlacklist(user, type, remark);
    }

    @Override
    public boolean isBlacklist(String userId, String type) {
        boolean result = false;
        Blacklist condition = new Blacklist();
        condition.setUserId(userId);
        condition.setType(type);
        condition.setStatus(EBlacklistStatus.VALID.getCode());
        long count = blacklistBO.getTotalCount(condition);
        if (count > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public void dropBlacklist(Long id, String updater, String remark) {
        Blacklist blacklist = blacklistBO.getBlacklist(id);
        if (EBlacklistStatus.VALID.getCode().equals(blacklist.getStatus())) {
            blacklistBO.removeBlacklist(id, updater, remark);
        } else {
            throw new BizException("xn000000", "记录不存在");
        }
    }

    @Override
    public Paginable<Blacklist> queryBlacklistPage(int start, int limit,
            Blacklist condition) {
        Paginable<Blacklist> page = blacklistBO.getPaginable(start, limit,
            condition);
        List<Blacklist> list = page.getList();
        for (Blacklist blacklist : list) {
            User user = userBO.getUser(blacklist.getUserId());
            blacklist.setUser(user);
        }
        return page;
    }

    @Override
    public Blacklist getBlacklist(Long id) {
        Blacklist blacklist = blacklistBO.getBlacklist(id);
        User user = userBO.getUser(blacklist.getUserId());
        blacklist.setUser(user);
        return blacklist;
    }

}
