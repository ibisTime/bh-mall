package com.xn.sdhh.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xn.sdhh.ao.ISYSMenuRoleAO;
import com.xn.sdhh.bo.ISYSMenuBO;
import com.xn.sdhh.bo.ISYSMenuRoleBO;
import com.xn.sdhh.bo.ISYSRoleBO;
import com.xn.sdhh.domain.SYSMenu;
import com.xn.sdhh.domain.SYSMenuRole;
import com.xn.sdhh.exception.BizException;

@Service
public class SYSMenuRoleAOImpl implements ISYSMenuRoleAO {

    @Autowired
    ISYSMenuRoleBO sysMenuRoleBO;

    @Autowired
    ISYSMenuBO sysMenuBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Override
    @Transactional
    public int addSYSMenuRole(SYSMenuRole data) {
        int count = 0;
        if (!sysRoleBO.isSYSRoleExist(data.getRoleCode())) {
            throw new BizException("lh0000", "角色编号不存在！");
        }
        // 删除角色所关联的菜单
        sysMenuRoleBO.removeSYSMenuRoleByRole(data.getRoleCode());
        // 重新分配角色菜单
        if (CollectionUtils.isNotEmpty(data.getMenuCodeList())) {
            for (String sysMenuCode : data.getMenuCodeList()) {
                if (!sysMenuBO.isSYSMenuExist(sysMenuCode)) {
                    throw new BizException("lh0000", "菜单编号不存在！");
                }
                SYSMenuRole sysMenuRole = new SYSMenuRole();
                sysMenuRole.setMenuCode(sysMenuCode);
                sysMenuRole.setRoleCode(data.getRoleCode());
                sysMenuRole.setUpdater(data.getUpdater());
                sysMenuRole.setUpdateDatetime(new Date());
                sysMenuRole.setRemark(data.getRemark());
                sysMenuRole.setSystemCode(data.getSystemCode());
                count += sysMenuRoleBO.saveSYSMenuRole(sysMenuRole);
            }
        }
        return count;
    }

    @Override
    public List<SYSMenu> querySYSMenuRoleList(SYSMenuRole data) {
        return sysMenuRoleBO.querySYSMenuList(data);
    }

}
