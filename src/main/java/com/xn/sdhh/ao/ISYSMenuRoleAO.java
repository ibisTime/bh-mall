package com.xn.sdhh.ao;

import java.util.List;

import com.xn.sdhh.domain.SYSMenu;
import com.xn.sdhh.domain.SYSMenuRole;

/**
 * 系统菜单角色
 * @author: Gejin 
 * @since: 2016年4月16日 下午10:37:44 
 * @history:
 */
public interface ISYSMenuRoleAO {

    // 新增系统菜单角色
    public int addSYSMenuRole(SYSMenuRole data);

    // 列表查询系统菜单角色
    public List<SYSMenu> querySYSMenuRoleList(SYSMenuRole data);

}
