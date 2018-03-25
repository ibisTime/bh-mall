package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.ISYSMenuAO;
import com.bh.mall.bo.ISYSMenuBO;
import com.bh.mall.bo.ISYSMenuRoleBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SYSMenu;
import com.bh.mall.exception.BizException;

@Service
public class SYSMenuAOImpl implements ISYSMenuAO {

    @Autowired
    ISYSMenuBO sysMenuBO;

    @Autowired
    ISYSMenuRoleBO sysMenuRoleBO;

    @Override
    public String addSYSMenu(SYSMenu data) {
        if (data != null) {
            // 判断父编号是否存在
            if (!"0".equalsIgnoreCase(data.getParentCode())
                    && !sysMenuBO.isSYSMenuExist(data.getParentCode())) {
                throw new BizException("lh0000", "父节点菜单编号不存在！");
            }
            sysMenuBO.saveSYSMenu(data);
        } else {
            throw new BizException("lh0000", "菜单编号已经存在！");
        }
        return data.getCode();
    }

    @Override
    @Transactional
    public boolean dropSYSMenu(String code) {
        if (!sysMenuBO.isSYSMenuExist((code))) {
            throw new BizException("lh0000", "删除菜单不存在！");
        }
        sysMenuBO.removeSYSMenu(code);
        List<SYSMenu> childMenuList = sysMenuBO.querySYSMenuList(code);
        for (SYSMenu sysMenu : childMenuList) {
            sysMenuBO.removeSYSMenu(sysMenu.getCode());
        }
        sysMenuRoleBO.removeSYSMenuRoleByMenu(code);
        return true;
    }

    @Override
    public boolean editSYSMenu(SYSMenu data) {
        if (data != null && sysMenuBO.isSYSMenuExist(data.getCode())) {
            sysMenuBO.refreshSYSMenu(data);
        } else {
            throw new BizException("lh0000", "菜单编号不存在！");
        }
        return true;
    }

    @Override
    public Paginable<SYSMenu> querySYSMenuPage(int start, int limit,
            SYSMenu condition) {
        return sysMenuBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SYSMenu> querySYSMenuList(SYSMenu condition) {
        return sysMenuBO.querySYSMenuList(condition);
    }

    @Override
    public SYSMenu getSYSMenu(String code) {
        if (!sysMenuBO.isSYSMenuExist(code)) {
            throw new BizException("lh0000", "菜单编号不存在！");
        }
        return sysMenuBO.getSYSMenu(code);
    }
}
