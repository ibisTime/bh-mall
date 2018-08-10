package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSRoleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627040Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 角色-新增
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:15 
 * @history:
 */
public class XN627040 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN627040Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(sysRoleAO.addSYSRole(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627040Req.class);
        ObjValidater.validateReq(req);
    }

}
