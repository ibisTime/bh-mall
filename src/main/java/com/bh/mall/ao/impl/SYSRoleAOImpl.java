package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.ISYSRoleAO;
import com.bh.mall.bo.ISYSMenuRoleBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627040Req;
import com.bh.mall.exception.BizException;

@Service
public class SYSRoleAOImpl implements ISYSRoleAO {

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    ISYSMenuRoleBO sysMenuRoleBO;

    @Override
    public String addSYSRole(XN627040Req req) {
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
        User condition = new User();
        condition.setRoleCode(roleCode);
        List<User> list = userBO.queryUserList(condition);
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
        return sysRoleBO.querySYSRoleList(condition);
    }

    @Override
    public Paginable<SYSRole> querySYSRolePage(int start, int limit,
            SYSRole condition) {
        return sysRoleBO.getPaginable(start, limit, condition);
    }

    /** 
     * @see com.bh.mall.ao.ISYSRoleAO#getSYSRole(java.lang.String)
     */
    @Override
    public SYSRole getSYSRole(String code) {
        if (!sysRoleBO.isSYSRoleExist(code)) {
            throw new BizException("lh4000", "角色编号不存在！");
        }
        return sysRoleBO.getSYSRole(code);
    }
}
