package com.bh.mall.ao.impl;

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
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SYSMenu;
import com.bh.mall.dto.req.XN627050Req;
import com.bh.mall.exception.BizException;

@Service
public class SYSMenuAOImpl implements ISYSMenuAO {

    @Autowired
    ISYSMenuBO sysMenuBO;

    @Autowired
    ISYSMenuRoleBO sysMenuRoleBO;

    @Autowired
    IUserBO userBO;

    // 新增菜单
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
        data.setType(req.getType()); // 菜单类型（按钮 / 图片）
        data.setUrl(req.getUrl());
        data.setParentCode(req.getParentCode());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        sysMenuBO.saveSYSMenu(data);

        return code;

    }

    // 删除菜单
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

    // 修改菜单
    @Override
    public boolean editSYSMenu(SYSMenu data) {
        if (data != null && sysMenuBO.isSYSMenuExist(data.getCode())) {
            sysMenuBO.refreshSYSMenu(data);
        } else {
            throw new BizException("lh0000", "菜单编号不存在！");
        }
        return true;
    }

    // 分页查询
    @Override
    public Paginable<SYSMenu> querySYSMenuPage(int start, int limit,
            SYSMenu condition) {
        return sysMenuBO.getPaginable(start, limit, condition);
    }

    // 列表查询
    @Override
    public List<SYSMenu> querySYSMenuList(SYSMenu condition) {
        return sysMenuBO.querySYSMenuList(condition);
    }

    // 详细查询
    @Override
    public SYSMenu getSYSMenu(String code) {
        if (!sysMenuBO.isSYSMenuExist(code)) {
            throw new BizException("lh0000", "菜单编号不存在！");
        }
        return sysMenuBO.getSYSMenu(code);
    }
}
