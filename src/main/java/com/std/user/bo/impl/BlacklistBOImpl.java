package com.std.user.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IBlacklistBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.dao.IBlacklistDAO;
import com.std.user.domain.Blacklist;
import com.std.user.enums.EBlacklistStatus;
import com.std.user.exception.BizException;

@Component
public class BlacklistBOImpl extends PaginableBOImpl<Blacklist> implements
        IBlacklistBO {

    @Autowired
    private IBlacklistDAO blacklistDAO;

    @Override
    public boolean isBlacklistExist(Long id) {
        Blacklist condition = new Blacklist();
        condition.setId(id);
        if (blacklistDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAdded(String userId, String type, String systemCode) {
        Blacklist condition = new Blacklist();
        condition.setUserId(userId);
        condition.setType(type);
        condition.setSystemCode(systemCode);
        if (blacklistDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Long saveBlacklist(String userId, String type, String remark,
            String systemCode) {
        Long id = null;
        if (StringUtils.isNotBlank(userId)) {
            Blacklist data = new Blacklist();
            data.setUserId(userId);
            data.setType(type);
            data.setStatus(EBlacklistStatus.VALID.getCode());
            data.setCreateDatetime(new Date());
            data.setRemark(remark);
            data.setSystemCode(systemCode);
            blacklistDAO.insert(data);
            // 对应 mapper insert方法中必须配置 useGeneratedKeys="true" keyProperty="id"
            id = data.getId();
        }
        return id;
    }

    @Override
    public int removeBlacklist(Long id, String updater, String remark) {
        int count = 0;
        if (id != null) {
            Blacklist data = new Blacklist();
            data.setId(id);
            data.setStatus(EBlacklistStatus.DELETE.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            count = blacklistDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<Blacklist> queryBlacklistList(Blacklist condition) {
        return blacklistDAO.selectList(condition);
    }

    @Override
    public Blacklist getBlacklist(Long id) {
        Blacklist data = null;
        if (id != null) {
            Blacklist condition = new Blacklist();
            condition.setId(id);
            data = blacklistDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "黑名单记录不存在");
            }
        }
        return data;
    }

}
