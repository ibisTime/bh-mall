package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.ISYSMenuAO;
import com.bh.mall.bo.ISYSMenuBO;
import com.bh.mall.bo.ISYSMenuRoleBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.SYSMenu;
import com.bh.mall.domain.SYSMenuRole;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627050Req;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;

@Service
public class SYSMenuAOImpl implements ISYSMenuAO {

    @Autowired
    ISYSMenuBO sysMenuBO;

    @Autowired
    ISYSMenuRoleBO sysMenuRoleBO;

    @Autowired
    IUserBO userBO;

    @Override
    public String addSYSMenu(XN627050Req req) {
        if (!"0".equalsIgnoreCase(req.getParentCode())
                && !sysMenuBO.isSYSMenuExist(req.getParentCode())) {
            throw new BizException("lh0000", "父节点菜单编号不存在！");
        }
        SYSMenu data = new SYSMenu();
        String code = OrderNoGenerater.generate("SM");
        data.setCode(code);
        data.setName(req.getName());
        data.setType(req.getType());
        data.setUrl(req.getUrl());
        data.setParentCode(req.getParentCode());
        data.setOrderNo(req.getOrderNo());

        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        sysMenuBO.saveSYSMenu(data);
        List<User> userList = userBO.queryUserList(null,
            EUserKind.Plat.getCode(), req.getSystemCode());
        for (User user : userList) {
            SYSMenuRole sysMenuRole = new SYSMenuRole();
            sysMenuRole.setMenuCode(code);
            sysMenuRole.setRoleCode(user.getUserId());
            sysMenuRole.setUpdater(data.getUpdater());
            sysMenuRole.setUpdateDatetime(new Date());
            sysMenuRole.setRemark(data.getRemark());
            sysMenuRole.setSystemCode(data.getSystemCode());
            sysMenuRoleBO.saveSYSMenuRole(sysMenuRole);
        }
        return code;
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
