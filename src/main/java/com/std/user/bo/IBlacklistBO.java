package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.Blacklist;

public interface IBlacklistBO extends IPaginableBO<Blacklist> {

    public boolean isBlacklistExist(Long id);

    public boolean isAdded(String userId, String type, String systemCode);

    public Long saveBlacklist(String userId, String type, String remark,
            String systemCode);

    public int removeBlacklist(Long id, String updater, String remark);

    public List<Blacklist> queryBlacklistList(Blacklist condition);

    public Blacklist getBlacklist(Long id);

}
