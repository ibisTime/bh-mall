/**
 * @Title ISYSDictAO.java 
 * @Package com.xnjr.moom.ao 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:12:19 
 * @version V1.0   
 */
package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SYSDict;

/** 
 * 数据字典
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:12:19 
 * @history:
 */
public interface ISYSDictAO {

    static String DEFAULT_ORDER_COLUMN = "id";

    // 新增数据字典
    public Long addSYSDict(String type, String parentKey, String key,
            String value, String updater, String remark);

    // 删除数据字典
    public int dropSYSDict(Long id);

    // 修改数据字典
    public int editSYSDict(Long id, String value, String updater,
            String remark);

    // 分页查询
    public Paginable<SYSDict> querySYSDictPage(int start, int limit,
            SYSDict condition);

    // 列表查询
    public List<SYSDict> querySysDictList(SYSDict condition);

    // 详细查询
    public SYSDict getSYSDict(Long id);
}
