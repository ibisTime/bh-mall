package com.xn.sdhh.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xn.sdhh.bo.ISYSMenuBO;
import com.xn.sdhh.bo.base.PaginableBOImpl;
import com.xn.sdhh.core.OrderNoGenerater;
import com.xn.sdhh.dao.ISYSMenuDAO;
import com.xn.sdhh.domain.SYSMenu;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午7:56:45 
 * @history:
 */
@Component
public class SYSMenuBOImpl extends PaginableBOImpl<SYSMenu>
        implements ISYSMenuBO {

    @Autowired
    private ISYSMenuDAO sysMenuDAO;

    /** 
     * @see com.xn.sdhh.bo.ISYSMenuBO#isSYSMenuExist(java.lang.String)
     */
    @Override
    public boolean isSYSMenuExist(String code) {
        SYSMenu menu = new SYSMenu();
        menu.setCode(code);
        if (sysMenuDAO.selectTotalCount(menu) == 1) {
            return true;
        }
        return false;
    }

    /** 
     * @see com.xn.sdhh.bo.ISYSMenuBO#saveSYSMenu(com.xnjr.std.security.domain.SYSMenu)
     */
    // 新增菜单
    @Override
    public int saveSYSMenu(SYSMenu data) {
        int count = 0;
        if (data != null) {
            data.setCode(OrderNoGenerater.generate("SM"));
            data.setUpdateDatetime(new Date());
            count = sysMenuDAO.insert(data);
        }
        return count;
    }

    /** 
     * @see com.xn.sdhh.bo.ISYSMenuBO#removeSYSMenu(java.lang.String)
     */
    @Override
    public int removeSYSMenu(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            SYSMenu data = new SYSMenu();
            data.setCode(code);
            count = sysMenuDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.xn.sdhh.bo.ISYSMenuBO#refreshSYSMenu(com.xnjr.std.security.domain.SYSMenu)
     */
    @Override
    public int refreshSYSMenu(SYSMenu data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            data.setUpdateDatetime(new Date());
            count = sysMenuDAO.update(data);
        }
        return count;
    }

    /** 
     * @see com.xn.sdhh.bo.ISYSMenuBO#getSYSMenu(java.lang.String)
     */
    @Override
    public SYSMenu getSYSMenu(String code) {
        SYSMenu data = null;
        if (StringUtils.isNotBlank(code)) {
            SYSMenu condition = new SYSMenu();
            condition.setCode(code);
            data = sysMenuDAO.select(condition);
        }
        return data;
    }

    @Override
    public List<SYSMenu> querySYSMenuList(SYSMenu data) {
        return sysMenuDAO.selectList(data);
    }

    @Override
    public List<SYSMenu> querySYSMenuList(String parentCode) {
        SYSMenu condition = new SYSMenu();
        condition.setParentCode(parentCode);
        return sysMenuDAO.selectList(condition);
    }
}
