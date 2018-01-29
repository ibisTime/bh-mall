package com.bh.mall.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.bh.mall.ao.ISYSMenuRoleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SYSMenuRole;
import com.bh.mall.dto.req.XN627920ZReq;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 菜单角色-增加菜单角色
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN627920Z extends AProcessor {
    private ISYSMenuRoleAO sysMenuRoleAO = SpringContextHolder
        .getBean(ISYSMenuRoleAO.class);

    private XN627920ZReq req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenuRole data = new SYSMenuRole();
        data.setRoleCode(req.getRoleCode());
        data.setMenuCodeList(req.getMenuCodeList());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        sysMenuRoleAO.addSYSMenuRole(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627920ZReq.class);
        StringValidater.validateBlank(req.getRoleCode(), req.getUpdater(),
            req.getSystemCode());
        if (CollectionUtils.isEmpty(req.getMenuCodeList())) {
            throw new BizException("xnlh4000", "菜单列表不能为空");
        }
    }
}
