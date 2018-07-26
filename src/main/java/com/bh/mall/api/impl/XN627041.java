package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSRoleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.dto.req.XN627041Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 角色-修改
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN627041 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN627041Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole data = new SYSRole();
        data.setCode(req.getCode());
        data.setName(req.getName());
        
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        return new BooleanRes(sysRoleAO.editSYSRole(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627041Req.class);
        ObjValidater.validateReq(req);
    }
}
