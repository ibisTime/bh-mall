package com.xn.sdhh.ao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.sdhh.ao.ISYSConfigAO;
import com.xn.sdhh.bo.ISYSConfigBO;
import com.xn.sdhh.bo.ISYSUserBO;
import com.xn.sdhh.bo.base.Paginable;
import com.xn.sdhh.domain.SYSConfig;
import com.xn.sdhh.domain.SYSUser;
import com.xn.sdhh.exception.BizException;

@Service
public class SYSConfigAOImpl implements ISYSConfigAO {

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    ISYSUserBO sysUserBO;

    // 修改系统参数
    @Override
    public int editSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null) {
            count = sysConfigBO.refreshSYSConfig(data);
        } else {
            throw new BizException("lh5031", "系统参数ID不存在！");
        }
        return count;
    }

    // 分页查询系统参数
    @Override
    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition) {
        Paginable<SYSConfig> page = sysConfigBO.getPaginable(start, limit,
            condition);
        // 更新人转义
        for (SYSConfig data : page.getList()) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
            data.setUpdateName(sysUser.getRealName());
        }

        return page;
    }

    // 详细查询系统参数
    @Override
    public SYSConfig getSYSConfig(Long id) {
        SYSConfig data = sysConfigBO.getConfig(id);
        // 更新人转义
        SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
        data.setUpdateName(sysUser.getRealName());
        return sysConfigBO.getConfig(id);
    }

    // 根据ckey查询系统参数
    @Override
    public SYSConfig getSYSConfig(String key) {
        return sysConfigBO.getConfig(key);
    }

    // 根据类型查询系统参数
    @Override
    public Map<String, String> querySYSConfig(String type) {
        return sysConfigBO.getConfigsMap(type);
    }

}
