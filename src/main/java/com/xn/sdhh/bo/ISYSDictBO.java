/**
 * @Title ISYSDictBO.java 
 * @Package com.xnjr.moom.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:40:19 
 * @version V1.0   
 */
package com.xn.sdhh.bo;

import java.util.List;

import com.xn.sdhh.bo.base.IPaginableBO;
import com.xn.sdhh.domain.SYSDict;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:40:19 
 * @history:
 */
public interface ISYSDictBO extends IPaginableBO<SYSDict> {

    public Long saveSYSDict(SYSDict data);

    public int removeSYSDict(Long id);

    /**
     * 修改数据字典
     * @param sysDict
     * @return 
     * @create: 2016年4月17日 下午2:41:36 haiqingzheng
     * @history:
     */
    public int refreshSYSDict(SYSDict data);

    public SYSDict getSYSDict(Long id);

    public List<SYSDict> querySYSDictList(SYSDict condition);
}
