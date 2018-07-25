package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSRoleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.dto.req.XN627046Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 角色-列表查询
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:24:46 
 * @history:
 */
public class XN627046 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN627046Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole condition = new SYSRole();
        condition.setName(req.getName());
        
        // condition.setUpdater(req.getUpdater());
        condition.setSystemCode(req.getSystemCode());
        return sysRoleAO.querySYSRoleList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627046Req.class);
        ObjValidater.validateReq(req);
    }
}
