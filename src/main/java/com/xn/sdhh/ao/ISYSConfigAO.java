package com.xn.sdhh.ao;

import java.util.Map;

import com.xn.sdhh.bo.base.Paginable;
import com.xn.sdhh.domain.SYSConfig;

/**
 *  系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午4:02:23 
 * @history:
 */
public interface ISYSConfigAO {

    String DEFAULT_ORDER_COLUMN = "id";

    // 修改系统参数
    public int editSYSConfig(SYSConfig data);

    // 分页查询
    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition);

    // 获取type下的值
    public Map<String, String> querySYSConfig(String type);

    // 通过id获取
    public SYSConfig getSYSConfig(Long id);

    // 通过ckey获取
    public SYSConfig getSYSConfig(String key);

}
