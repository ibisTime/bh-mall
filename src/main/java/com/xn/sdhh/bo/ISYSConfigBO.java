/**
 * @Title ISYSConfigBO.java 
 * @Package com.xnjr.moom.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:40:52 
 * @version V1.0   
 */
package com.xn.sdhh.bo;

import java.util.Map;

import com.xn.sdhh.bo.base.IPaginableBO;
import com.xn.sdhh.domain.SYSConfig;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:40:52 
 * @history:
 */
public interface ISYSConfigBO extends IPaginableBO<SYSConfig> {
    public int refreshSYSConfig(SYSConfig data);

    public SYSConfig getConfig(Long id);

    /**
     * 根据key获取value
     * @param ckey
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2017年4月23日 下午8:08:12 xieyj
     * @history:
     */
    public SYSConfig getConfig(String ckey, String companyCode,
            String systemCode);

    public SYSConfig getConfig(String ckey);

    /**
     * 获取所有配置
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2017年7月14日 下午7:03:12 xieyj
     * @history:
     */
    public Map<String, String> getConfigsMap(String type, String companyCode,
            String systemCode);

    public Map<String, String> getConfigsMap(String type);

}
