package com.xn.sdhh.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xn.sdhh.bo.ISYSConfigBO;
import com.xn.sdhh.bo.base.PaginableBOImpl;
import com.xn.sdhh.dao.ISYSConfigDAO;
import com.xn.sdhh.domain.SYSConfig;

/**
 * @author: xieyj 
 * @since: 2017年4月23日 下午6:19:30 
 * @history:
 */
@Component
public class SYSConfigBOImpl extends PaginableBOImpl<SYSConfig>
        implements ISYSConfigBO {
    @Autowired
    private ISYSConfigDAO sysConfigDAO;

    @Override
    public int refreshSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = sysConfigDAO.updateValue(data);
        }
        return count;
    }

    @Override
    public SYSConfig getConfig(Long id) {
        SYSConfig sysConfig = null;
        if (id != null) {
            SYSConfig condition = new SYSConfig();
            condition.setId(id);
            sysConfig = sysConfigDAO.select(condition);
        }
        return sysConfig;
    }

    @Override
    public SYSConfig getConfig(String ckey, String companyCode,
            String systemCode) {
        SYSConfig result = null;
        if (ckey != null && StringUtils.isNotBlank(companyCode)
                && StringUtils.isNotBlank(systemCode)) {
            SYSConfig condition = new SYSConfig();
            condition.setCkey(ckey);
            result = sysConfigDAO.select(condition);
        }
        return result;
    }

    @Override
    public SYSConfig getConfig(String ckey) {
        SYSConfig result = null;
        if (ckey != null) {
            SYSConfig condition = new SYSConfig();
            condition.setCkey(ckey);
            result = sysConfigDAO.select(condition);
        }
        return result;
    }

    @Override
    public Map<String, String> getConfigsMap(String type, String companyCode,
            String systemCode) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotBlank(systemCode)) {
            SYSConfig condition = new SYSConfig();
            condition.setType(type);
            List<SYSConfig> list = sysConfigDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                for (SYSConfig sysConfig : list) {
                    map.put(sysConfig.getCkey(), sysConfig.getCvalue());
                }
            }
        }
        return map;

    }

    @Override
    public Map<String, String> getConfigsMap(String type) {
        Map<String, String> map = new HashMap<String, String>();
        SYSConfig condition = new SYSConfig();
        condition.setType(type);
        List<SYSConfig> list = sysConfigDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (SYSConfig sysConfig : list) {
                map.put(sysConfig.getCkey(), sysConfig.getCvalue());
            }
        }
        return map;
    }
}
