package com.xn.sdhh.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xn.sdhh.ao.ISYSRoleAO;
import com.xn.sdhh.bo.ISYSMenuRoleBO;
import com.xn.sdhh.bo.ISYSRoleBO;
import com.xn.sdhh.bo.ISYSUserBO;
import com.xn.sdhh.bo.base.Paginable;
import com.xn.sdhh.core.OrderNoGenerater;
import com.xn.sdhh.domain.SYSRole;
import com.xn.sdhh.domain.SYSUser;
import com.xn.sdhh.dto.req.XN301000Req;
import com.xn.sdhh.exception.BizException;

@Service
public class SYSRoleAOImpl implements ISYSRoleAO {

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISYSUserBO sysUserBO;

    @Autowired
    ISYSMenuRoleBO sysMenuRoleBO;

    @Override
    public String addSYSRole(XN301000Req req) {
        String code = OrderNoGenerater.generate("SYSR");
        SYSRole data = new SYSRole();
        data.setCode(code);
        data.setName(req.getName());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        sysRoleBO.saveSYSRole(data);
        return code;
    }

    @Override
    @Transactional
    public boolean dropSYSRole(String roleCode) {
        if (!sysRoleBO.isSYSRoleExist(roleCode)) {
            throw new BizException("lh4000", "角色编号不存在！");
        }
        SYSUser condition = new SYSUser();
        condition.setRoleCode(roleCode);
        List<SYSUser> list = sysUserBO.queryUserList(condition);
        if (!CollectionUtils.sizeIsEmpty(list)) {
            throw new BizException("lh4000", "该角色已在使用，无法删除！");
        }
        // 删除角色和角色菜单关联表
        sysRoleBO.removeSYSRole(roleCode);
        sysMenuRoleBO.removeSYSMenuRoleByRole(roleCode);
        return true;
    }

    @Override
    public boolean editSYSRole(SYSRole data) {
        if (data != null && sysRoleBO.isSYSRoleExist(data.getCode())) {
            sysRoleBO.refreshSYSRole(data);
        } else {
            throw new BizException("lh4000", "角色编号不存在！");
        }

        return true;
    }

    @Override
    public List<SYSRole> querySYSRoleList(SYSRole condition) {
        List<SYSRole> list = sysRoleBO.querySYSRoleList(condition);
        for (SYSRole data : list) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
            data.setUpdateName(sysUser.getRealName());
        }

        return list;
    }

    @Override
    public Paginable<SYSRole> querySYSRolePage(int start, int limit,
            SYSRole condition) {
        Paginable<SYSRole> page = sysRoleBO.getPaginable(start, limit,
            condition);
        for (SYSRole data : page.getList()) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
            data.setUpdateName(sysUser.getRealName());
        }
        return page;
    }

    /** 
     * @see com.xn.sdhh.ao.ISYSRoleAO#getSYSRole(java.lang.String)
     */
    @Override
    public SYSRole getSYSRole(String code) {
        if (!sysRoleBO.isSYSRoleExist(code)) {
            throw new BizException("lh4000", "角色编号不存在！");
        }
        SYSRole data = sysRoleBO.getSYSRole(code);
        SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
        data.setUpdateName(sysUser.getRealName());
        return data;
    }
}
