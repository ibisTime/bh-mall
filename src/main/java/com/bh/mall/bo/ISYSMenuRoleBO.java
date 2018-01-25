/**
 * @Title ISYSMenuRoleBO.java 
 * @Package com.std.security.bo 
 * @Description 
 * @author xieyj  
 * @date 2016年4月17日 上午8:57:11 
 * @version V1.0   
 */
package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.SYSMenu;
import com.bh.mall.domain.SYSMenuRole;

/** 
 * 菜单角色
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:57:11 
 * @history:
 */
public interface ISYSMenuRoleBO extends IPaginableBO<SYSMenuRole> {
    public int saveSYSMenuRole(SYSMenuRole data);

    public int removeSYSMenuRoleByRole(String roleCode);

    public int removeSYSMenuRoleByMenu(String menuCode);

    public List<SYSMenu> querySYSMenuList(SYSMenuRole data);
}
