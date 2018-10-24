package com.xn.sdhh.dao;

import java.util.List;

import com.xn.sdhh.dao.base.IBaseDAO;
import com.xn.sdhh.domain.SYSMenu;
import com.xn.sdhh.domain.SYSMenuRole;

/**
 * 角色菜单
 * @author: xieyj 
 * @since: 2017年8月23日 下午2:59:09 
 * @history:
 */
public interface ISYSMenuRoleDAO extends IBaseDAO<SYSMenuRole> {
    String NAMESPACE = ISYSMenuRoleDAO.class.getName().concat(".");

    public List<SYSMenu> selectSYSMenuList(SYSMenuRole condition);

    public int deleteSYSMenuRoleByRole(SYSMenuRole condition);

    public int deleteSYSMenuRoleByMenu(SYSMenuRole condition);
}
