package com.bh.mall.ao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISYSConfigAO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.exception.BizException;

/**
 * 系统参数
 * @author: xieyj 
 * @since: 2016年7月23日 下午3:50:20 
 * @history:
 */
@Service
public class SYSConfigAOImpl implements ISYSConfigAO {

    @Autowired
    ISYSConfigBO sysConfigBO;

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
        return sysConfigBO.getPaginable(start, limit, condition);
    }

    // 详细查询系统参数
    @Override
    public SYSConfig getSYSConfig(Long id) {
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
